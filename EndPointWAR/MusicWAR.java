package EndPointWAR;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import sHEJB.MusicEJB;
import sHJPA.Music;
import java.sql.Timestamp;

@Path("/music")
@Stateless
public class MusicWAR {

    private static final Logger LOGGER = Logger.getLogger(MusicWAR.class.getName());

    @Inject
    private MusicEJB musicEjb;

    @GET
    @Path("/getMusics")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Music> getAllMusic() {
        LOGGER.info("Appel à /getMusics");
        return musicEjb.getAllMusics();
    }

    @GET
    @Path("/getMusicsByType/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Music> getMusicsByType(@PathParam("id") Integer id) {
        LOGGER.info("Appel à /getMusicsByType avec ID = " + id);
        return musicEjb.getMusicsByType(id);
    }

    @GET
    @Path("/getMusicsByAutor/{autor}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Music> getMusicsByAutor(@PathParam("autor") String autor) {
        LOGGER.info("Appel à /getMusicsByAutor avec auteur = " + autor);
        return musicEjb.getMusicsByAutor(autor);
    }

    @DELETE
    @Path("/deleteMusic/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMusic(@PathParam("id") Integer id) {
        LOGGER.info("Appel à /deleteMusic avec ID = " + id);
        try {
            musicEjb.deleteSingleMusicById(id);
            return Response.ok("Musique supprimée").build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression de musique ID = " + id, e);
            return Response.serverError().entity("Erreur : " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/getMusic/{fileName}")
    @Produces("audio/mpeg")
    public Response getMusic(@PathParam("fileName") String fileName) {
        LOGGER.info("Lecture du fichier audio : " + fileName);
        try {
            File music = new File("/Users/d4cd13ger/Projets/SpotiHome-JEE/spotiHome/upload/" + fileName + ".mp3");
            if (!music.exists()) {
                LOGGER.warning("Fichier audio introuvable : " + fileName);
                return Response.status(Response.Status.NOT_FOUND).entity("Fichier introuvable").build();
            }

            return Response.ok(music)
                    .header("Content-Disposition", "inline; filename=\"" + music.getName() + "\"")
                    .build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la lecture du fichier audio", e);
            return Response.serverError().entity("Erreur serveur : " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/addMusic/{fileName}/{id_administrator}/{label}/{auteur}/{id_type}/{id_genre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMusicFromParams(
            @PathParam("fileName") String fileName,
            @PathParam("id_administrator") Integer id_administrator,
            @PathParam("label") String label,
            @PathParam("auteur") String auteur,
            @PathParam("id_type") Integer id_type,
            @PathParam("id_genre") Integer id_genre) {

        LOGGER.info("Ajout de musique via paramètres : " + label);
        try {
            musicEjb.addMusic(fileName, id_administrator, label, auteur, id_type, id_genre);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'ajout de musique (params)", e);
            return Response.serverError().entity("Erreur : " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultipartFormDataInput input) {
        LOGGER.info("Appel à /upload pour fichier MP3");

        String fileName = "";
        String contentDisposition;

        try {
            Map<String, List<InputPart>> formParts = input.getFormDataMap();
            List<InputPart> fileParts = formParts.get("file");

            if (fileParts != null && !fileParts.isEmpty()) {
                InputPart filePart = fileParts.get(0);
                contentDisposition = filePart.getHeaders().getFirst("Content-Disposition");
                fileName = Base64.getEncoder().encodeToString(extractFileName(contentDisposition).getBytes()) + ".mp3";

                InputStream fileContent = filePart.getBody(InputStream.class, null);

                String targetDir = "/Users/d4cd13ger/Projets/SpotiHome-JEE/spotiHome/upload/";
                File targetFile = new File(targetDir, fileName);

                Files.copy(fileContent, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                LOGGER.info("Fichier sauvegardé : " + fileName);
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'upload de fichier", e);
            return Response.serverError().entity("Erreur lors de l'upload : " + e.getMessage()).build();
        }

        return Response.ok("Fichier enregistré sous : " + fileName).build();
    }

    private String extractFileName(String contentDisposition) {
        for (String part : contentDisposition.split(";")) {
            if (part.trim().startsWith("filename")) {
                return part.substring(part.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "inconnu";
    }
    
    @POST
    @Path("/share/{id_auditeur}/{id_musique}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response partagerMusique(
            @PathParam("id_auditeur") int idAuditeur,
            @PathParam("id_musique") int idMusique){
        musicEjb.shareMusic(idAuditeur, idMusique);
        return Response.ok().build();
    }
    
    @GET
    @Path("/readShare/{id_auditeur}/{id_musique}/{dateShare}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lirePartage(
            @PathParam("id_auditeur") Integer idAuditeur,
            @PathParam("id_musique") Integer idMusique,
            @PathParam("dateShare") String dateShare){
        
           
        Timestamp originalTimestamp = Timestamp.valueOf(dateShare);
        Long milliseconds = (originalTimestamp.getTime() / 1000) * 1000;
        Timestamp preciseTimestamp = new Timestamp(milliseconds);
        
        return Response.ok(musicEjb.verifShare(idAuditeur, idMusique, preciseTimestamp)).build();
        
    }
    
    @POST
    @Path("/updateMsuic/{idMusic}/{auteur}/{name}/{idGenre}/{idType}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateMusic(
            @PathParam("idMusic") Integer idMusic,
            @PathParam("auteur") String auteur,
            @PathParam("name") String name,
            @PathParam("idGenre") Integer idGenre,
            @PathParam("idType") Integer idType){
        
        musicEjb.updateMusic(idMusic, auteur, name, idGenre, idType);
        
    }
}