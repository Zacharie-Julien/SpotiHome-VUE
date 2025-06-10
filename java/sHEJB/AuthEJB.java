package sHEJB;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sHJPA.Administrator;
import sHJPA.User;

import java.util.logging.Logger;

/**
 *
 * @author d4cd13ger
 */
@Stateless
public class AuthEJB {

    private static final Logger LOGGER = Logger.getLogger(AuthEJB.class.getName());

    @Inject 
    private UsersEJB ejb;

    @PersistenceContext(unitName = "spotiHome-ejbPU")
    private EntityManager em;

    // Le type Boolean est éphémère ce n'est que pour les tests
    public Boolean verifyAuth(String login, String passwd) {
        LOGGER.info("Vérification de l'authentification pour l'utilisateur : " + login);
        Boolean response = false;

        try {
            User user = ejb.getUserByLogin(login);

            if (user != null && user.getPasswd().equals(passwd)) {
                LOGGER.info("Authentification réussie pour l'utilisateur : " + login);
                response = true;
            } else {
                LOGGER.warning("Échec d'authentification pour l'utilisateur : " + login);
            }

        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la vérification de l'authentification : " + e.getMessage());
        }

        return response;
    }

    public String verifAdmin(User user) {
        LOGGER.info("Vérification du rôle administrateur pour l'utilisateur ID : " + user.getId());
        String response = "auditor";

        try {
            Administrator searchedAdministrator = em.find(Administrator.class, user.getId());

            if (searchedAdministrator != null) {
                response = "admin";
                LOGGER.info("Utilisateur ID " + user.getId() + " est administrateur.");
            } else {
                LOGGER.info("Utilisateur ID " + user.getId() + " est auditeur.");
            }
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la vérification du rôle : " + e.getMessage());
        }

        return response;
    }
}