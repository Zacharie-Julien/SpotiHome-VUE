package sHEJB;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sHJPA.Auditor;
import sHJPA.Music;
import sHJPA.Playlist;

@Stateless
public class PlayListEJB {

    private static final Logger LOGGER = Logger.getLogger(PlayListEJB.class.getName());

    @PersistenceContext(unitName = "spotiHome-ejbPU")
    private EntityManager em;

    public void addPlaylist(String label, Integer id_auditeur) {
        LOGGER.info("Ajout d'une nouvelle playlist : " + label + " pour l'auditeur ID = " + id_auditeur);
        try {
            Playlist addPlayList = new Playlist();
            Auditor auditeur = em.find(Auditor.class, id_auditeur);

            if (auditeur == null) {
                LOGGER.warning("Auditeur introuvable avec l'ID : " + id_auditeur);
                return;
            }

            addPlayList.setLabel(label);
            addPlayList.setAuditeur(auditeur);

            em.persist(addPlayList);
            LOGGER.info("Playlist ajoutée avec succès.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'ajout de la playlist : " + label, e);
        }
    }

    public void deletePlaylist(Integer id) {
        LOGGER.info("Suppression de la playlist ID = " + id);
        try {
            int deleted = em.createNamedQuery("PlayList.deletePlayListById")
                    .setParameter("id", id)
                    .executeUpdate();
            if (deleted > 0) {
                LOGGER.info("Playlist supprimée avec succès.");
            } else {
                LOGGER.warning("Aucune playlist trouvée avec l'ID : " + id);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression de la playlist ID = " + id, e);
        }
    }

    public List<Playlist> getPlayListByIdAuditor(Integer id) {
        LOGGER.info("Récupération des playlists pour l'auditeur ID = " + id);
        try {
            return em.createNamedQuery("PlayList.getPlayListByAuditorId", Playlist.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération des playlists de l'auditeur ID = " + id, e);
            return List.of();
        }
    }

    public void addMusicToPlaylist(Integer id_music, Integer id_playlist) {
        LOGGER.info("Ajout de la musique ID = " + id_music + " à la playlist ID = " + id_playlist);
        try {
            Music addMusic = em.find(Music.class, id_music);
            Playlist currentPlaylist = em.find(Playlist.class, id_playlist);

            if (addMusic == null || currentPlaylist == null) {
                LOGGER.warning("Musique ou playlist introuvable (ID musique = " + id_music + ", ID playlist = " + id_playlist + ")");
                return;
            }

            currentPlaylist.addMusiques(addMusic);
            em.persist(currentPlaylist);
            LOGGER.info("Musique ajoutée à la playlist avec succès.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'ajout de musique à la playlist", e);
        }
    }

    public void updateLabelPlayList(String label, Integer id) {
        LOGGER.info("Mise à jour du nom de la playlist ID = " + id + " vers : " + label);
        try {
            int updated = em.createNamedQuery("PlayList.updateNamePlayList")
                    .setParameter("label", label)
                    .setParameter("id", id)
                    .executeUpdate();
            if (updated > 0) {
                LOGGER.info("Nom de la playlist mis à jour avec succès.");
            } else {
                LOGGER.warning("Aucune playlist trouvée avec l'ID : " + id);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la mise à jour de la playlist", e);
        }
    }

    public List<Music> getMusicByPlaylistId(Integer id) {
        LOGGER.info("Récupération des musiques pour la playlist ID = " + id);
        try {
            return em.createNamedQuery("PlayList.getMusicByPlayList", Music.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération des musiques pour la playlist ID = " + id, e);
            return List.of();
        }
    }
}