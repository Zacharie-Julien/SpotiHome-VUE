package sHEJB;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sHJPA.Administrator;
import sHJPA.User;

@Stateless
public class UsersEJB {

    private static final Logger LOGGER = Logger.getLogger(UsersEJB.class.getName());

    @PersistenceContext(unitName = "spotiHome-ejbPU")
    private EntityManager em;

    public Administrator getSingleAdministratorById(Integer id) {
        LOGGER.info("Recherche de l'administrateur avec l'ID : " + id);
        try {
            Administrator admin = em.createNamedQuery("Administrator.getAdminById", Administrator.class)
                    .setParameter("id", id)
                    .getSingleResult();
            LOGGER.info("Administrateur trouvé : ID = " + id);
            return admin;
        } catch (NoResultException e) {
            LOGGER.warning("Aucun administrateur trouvé pour l'ID : " + id);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la recherche de l'administrateur ID = " + id, e);
            return null;
        }
    }

    public User getUserByLogin(String login) {
        LOGGER.info("Recherche d'utilisateur avec le login : " + login);
        try {
            User user = em.createNamedQuery("User.getUserByLogin", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
            LOGGER.info("Utilisateur trouvé avec le login : " + login);
            return user;
        } catch (NoResultException e) {
            LOGGER.warning("Aucun utilisateur trouvé avec le login : " + login);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération de l'utilisateur avec le login : " + login, e);
            return null;
        }
    }
}