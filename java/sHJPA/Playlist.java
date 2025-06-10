/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sHJPA;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity 
@NamedQueries({
    @NamedQuery(name="PlayList.deletePlayListById", query="DELETE FROM Playlist p WHERE p.id = :id"),
    @NamedQuery(name="PlayList.updateNamePlayList", query="UPDATE Playlist p SET p.label = :label WHERE p.id = :id"),
    @NamedQuery(name="PlayList.getPlayListByAuditorId", query="SELECT p FROM Playlist p LEFT JOIN FETCH p.musiques WHERE p.auditeur.id = :id"),
    @NamedQuery(name="PlayList.getMusicByPlayList", query="SELECT m FROM Playlist p JOIN p.musiques m WHERE p.id = :id")
})
@Table(name="playlist")
public class Playlist implements Serializable{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="label")
    private String label;
    
    @OneToOne
    @JoinColumn(name="id_auditeur")
    private Auditor auditeur;
    
    @OneToMany
    @JoinTable(
     name = "possess",
        joinColumns = @JoinColumn(name = "id_playlist"),
        inverseJoinColumns = @JoinColumn(name = "id_musique")
    )
    private List<Music> musiques;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Auditor getAuditeur() {
        return auditeur;
    }

    public void setAuditeur(Auditor auditeur) {
        this.auditeur = auditeur;
    }       

    public List<Music> getMusiques() {
        return musiques;
    }

    public void setMusiques(List<Music> musiques) {
        this.musiques = musiques;
    }
    
    public void addMusiques(Music music){
        this.musiques.add(music);
    }
}
