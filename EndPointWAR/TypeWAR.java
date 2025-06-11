package EndPointWAR;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sHEJB.TypeEJB;
import sHJPA.Type;

@Path("/type")
@Stateless
public class TypeWAR {

    private static final Logger LOGGER = Logger.getLogger(TypeWAR.class.getName());

    @Inject
    private TypeEJB typeEjb;

    @POST
    @Path("/addType/{label}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addType(@PathParam("label") String label) {
        LOGGER.info("Ajout d'un type avec le label : " + label);
        try {
            typeEjb.addType(label);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'ajout du type", e);
            return Response.serverError().entity("Erreur serveur").build();
        }
    }

    @DELETE
    @Path("/deleteType/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteType(@PathParam("id") Integer id) {
        LOGGER.info("Suppression du type avec ID = " + id);
        try {
            typeEjb.deleteType(id);
            return Response.ok("Type supprimé").build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression du type", e);
            return Response.serverError().entity("Erreur serveur").build();
        }
    }

    @GET
    @Path("/getAllType")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllType() {
        LOGGER.info("Récupération de tous les types");
        try {
            List<Type> types = typeEjb.getAllType();
            return Response.ok(types).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération des types", e);
            return Response.serverError().entity("Erreur serveur").build();
        }
    }

    @POST
    @Path("/updateType/{label}/type/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateType(@PathParam("label") String label, @PathParam("id") Integer id) {
        LOGGER.info("Mise à jour du label du type ID = " + id + " avec le label : " + label);
        try {
            typeEjb.updateLabelType(label, id);
            return Response.ok("Type mis à jour").build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la mise à jour du type", e);
            return Response.serverError().entity("Erreur serveur").build();
        }
    }
}