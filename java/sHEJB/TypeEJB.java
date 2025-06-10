package sHEJB;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sHJPA.Type;

@Stateless
public class TypeEJB {

    private static final Logger LOGGER = Logger.getLogger(TypeEJB.class.getName());

    @PersistenceContext(unitName = "spotiHome-ejbPU")
    private EntityManager em;

    public void addType(String label) {
        LOGGER.info("Ajout d'un type avec le label : " + label);
        try {
            Type addType = new Type();
            addType.setLabel(label);
            em.persist(addType);
            LOGGER.info("Type ajouté avec succès : " + label);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'ajout du type : " + label, e);
        }
    }

    public void deleteType(Integer id) {
        LOGGER.info("Suppression du type ID = " + id);
        try {
            int deleted = em.createNamedQuery("Type.deleteTypeById")
                    .setParameter("id", id)
                    .executeUpdate();
            if (deleted > 0) {
                LOGGER.info("Type supprimé avec succès.");
            } else {
                LOGGER.warning("Aucun type trouvé avec l'ID : " + id);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression du type ID = " + id, e);
        }
    }

    public List<Type> getAllType() {
        LOGGER.info("Récupération de tous les types");
        try {
            return em.createNamedQuery("Type.getAllTypeById", Type.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération des types", e);
            return List.of(); // évite NullPointerException côté appelant
        }
    }

    public void updateLabelType(String label, Integer id) {
        LOGGER.info("Mise à jour du label du type ID = " + id + " vers : " + label);
        try {
            int updated = em.createNamedQuery("Type.updateNameType")
                    .setParameter("label", label)
                    .setParameter("id", id)
                    .executeUpdate();
            if (updated > 0) {
                LOGGER.info("Label du type mis à jour avec succès.");
            } else {
                LOGGER.warning("Aucun type trouvé à mettre à jour avec l'ID : " + id);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la mise à jour du label du type ID = " + id, e);
        }
    }
}