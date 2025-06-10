package sHEJB;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;
import sHJPA.*;

@Stateless
public class MusicEJB {

    private static final Logger LOGGER = Logger.getLogger(MusicEJB.class.getName());

    @PersistenceContext(unitName = "spotiHome-ejbPU")
    private EntityManager em;

    public void addMusic(Music music) {
        LOGGER.info("Ajout de musique via objet Music");

        try {
            Music addMusic = new Music();
            Administrator admin = em.find(Administrator.class, music.getAdministrator().getId());
            Type type = em.find(Type.class, music.getType().getId());
            List<Genre> genres = new ArrayList<>();

            for (Genre genre : music.getGenres()) {
                genres.add(genre);
            }

            addMusic.setAdministrator(admin);
            addMusic.setGenres(genres);
            addMusic.setLabel(music.getLabel());
            addMusic.setAuteur(music.getAuteur());
            addMusic.setType(type);

            em.persist(addMusic);
            LOGGER.info("Musique ajoutée avec succès : " + music.getLabel());

        } catch (Exception e) {
            LOGGER.severe("Erreur lors de l’ajout de la musique : " + e.getMessage());
        }
    }

    public List<Music> getAllMusics() {
        LOGGER.info("Récupération de toutes les musiques");
        try {
            return em.createNamedQuery("Music.getAllMusics", Music.class).getResultList();
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la récupération des musiques : " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Music> getMusicsByType(Integer id) {
        LOGGER.info("Récupération des musiques par type : ID = " + id);
        try {
            return em.createNamedQuery("Music.searchMusicsByType", Music.class)
                     .setParameter("id", id)
                     .getResultList();
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la recherche des musiques par type : " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Music> getMusicsByAutor(String autor) {
        LOGGER.info("Récupération des musiques par auteur : " + autor);
        try {
            return em.createNamedQuery("Music.searchMusicsByAutor", Music.class)
                     .setParameter("auteur", autor)
                     .getResultList();
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la recherche des musiques par auteur : " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void deleteSingleMusicById(Integer id) {
        LOGGER.info("Suppression de la musique ID = " + id);
        try {
            em.createNamedQuery("Music.deleteMusic")
              .setParameter("id", id)
              .executeUpdate();
            LOGGER.info("Musique supprimée avec succès");
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la suppression de la musique : " + e.getMessage());
        }
    }

    public void addMusic(String fileName, Integer id_administrator, String label, String auteur, Integer id_type, Integer id_genre) {
        LOGGER.info("Ajout de musique via paramètres simples : " + label);
        try {
            Administrator newAdministrator = em.find(Administrator.class, id_administrator);
            Type newType = em.find(Type.class, id_type);
            Genre newGenre = em.find(Genre.class, id_genre);

            List<Genre> genres = new ArrayList<>();
            genres.add(newGenre);

            Music newMusic = new Music();
            newMusic.setAdministrator(newAdministrator);
            newMusic.setLabel(label);
            newMusic.setLien_audio(Base64.getEncoder().encodeToString(fileName.getBytes()));
            newMusic.setAuteur(auteur);
            newMusic.setType(newType);
            newMusic.setGenres(genres);

            em.persist(newMusic);
            LOGGER.info("Musique ajoutée avec succès : " + label);
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de l’ajout de musique (via params) : " + e.getMessage());
        }
    }
}