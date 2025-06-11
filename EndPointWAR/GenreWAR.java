package EndPointWAR;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;
import sHEJB.GenreEJB;
import sHJPA.Genre;

@Path("/genre")
@Stateless
public class GenreWAR {

    private static final Logger LOGGER = Logger.getLogger(GenreWAR.class.getName());

    @Inject
    private GenreEJB genreEjb;

    @POST
    @Path("/addGenre/{label}")
    @Produces(MediaType.APPLICATION_JSON)
    public void addGenre(@PathParam("label") String label) {
        LOGGER.info("Ajout d’un genre avec le label : " + label);
        try {
            genreEjb.addGenre(label);
            LOGGER.info("Genre ajouté avec succès : " + label);
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de l’ajout du genre : " + e.getMessage());
        }
    }

    @GET
    @Path("/getAllGenre")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Genre> addGenre() {
        LOGGER.info("Récupération de tous les genres.");
        try {
            return genreEjb.getAllGenre();
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la récupération des genres : " + e.getMessage());
            return null;
        }
    }

    @POST
    @Path("/updateGenre/{label}/genre/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateGenre(@PathParam("label") String label, @PathParam("id") Integer id) {
        LOGGER.info("Mise à jour du genre ID " + id + " avec le nouveau label : " + label);
        try {
            genreEjb.updateLabelGenre(label, id);
            LOGGER.info("Genre mis à jour avec succès.");
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la mise à jour du genre : " + e.getMessage());
        }
    }

    @DELETE
    @Path("/deleteGenre/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteGenre(@PathParam("id") Integer id) {
        LOGGER.info("Suppression du genre ID : " + id);
        try {
            genreEjb.deleteGenre(id);
            LOGGER.info("Genre supprimé avec succès.");
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la suppression du genre : " + e.getMessage());
        }
    }
}