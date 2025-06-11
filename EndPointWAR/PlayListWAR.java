package EndPointWAR;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sHEJB.PlayListEJB;
import sHJPA.Music;
import sHJPA.Playlist;

@Path("/playlist")
@Stateless
public class PlayListWAR {

    private static final Logger LOGGER = Logger.getLogger(PlayListWAR.class.getName());

    @Inject
    private PlayListEJB playListEjb;

    @POST
    @Path("/addPlayList/{label}/auditor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlayList(@PathParam("label") String label, @PathParam("id") Integer id) {
        LOGGER.info("Ajout d'une playlist : " + label + " pour l'auditeur ID = " + id);
        try {
            playListEjb.addPlaylist(label, id);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'ajout de la playlist", e);
            return Response.serverError().entity("Erreur serveur").build();
        }
    }

    @DELETE
    @Path("/deletePlayList/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlayList(@PathParam("id") Integer id) {
        LOGGER.info("Suppression de la playlist ID = " + id);
        try {
            playListEjb.deletePlaylist(id);
            return Response.ok("Playlist supprimée").build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression de la playlist", e);
            return Response.serverError().entity("Erreur serveur").build();
        }
    }

    @GET
    @Path("/getPlayListByUserId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayListByUserId(@PathParam("id") Integer id) {
        LOGGER.info("Récupération des playlists pour l'utilisateur ID = " + id);
        try {
            List<Playlist> playlists = playListEjb.getPlayListByIdAuditor(id);
            return Response.ok(playlists).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération des playlists", e);
            return Response.serverError().entity("Erreur serveur").build();
        }
    }

    @GET
    @Path("/getMusicsByPlaylistId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMusicByPlaylistId(@PathParam("id") Integer id) {
        LOGGER.info("Récupération des musiques de la playlist ID = " + id);
        try {
            List<Music> musics = playListEjb.getMusicByPlaylistId(id);
            return Response.ok(musics).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération des musiques", e);
            return Response.serverError().entity("Erreur serveur").build();
        }
    }

    @POST
    @Path("/addMusicToPlaylist/{id_music}/playList/{id_playlist}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMusicToPlayList(@PathParam("id_music") Integer id_music, @PathParam("id_playlist") Integer id_playlist) {
        LOGGER.info("Ajout musique ID = " + id_music + " à la playlist ID = " + id_playlist);
        try {
            playListEjb.addMusicToPlaylist(id_music, id_playlist);
            return Response.ok("Musique ajoutée à la playlist").build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'ajout de musique à la playlist", e);
            return Response.serverError().entity("Erreur serveur").build();
        }
    }

    @POST
    @Path("/updatePlaylist/{label}/playlist/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(@PathParam("label") String label, @PathParam("id") Integer id) {
        LOGGER.info("Mise à jour de la playlist ID = " + id + " avec le label : " + label);
        try {
            playListEjb.updateLabelPlayList(label, id);
            return Response.ok("Playlist mise à jour").build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la mise à jour de la playlist", e);
            return Response.serverError().entity("Erreur serveur").build();
        }
    }
}