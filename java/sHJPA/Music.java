/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sHJPA;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Entity
@NamedQueries({
    @NamedQuery(name="Music.getAllMusics", query="SELECT m FROM Music m"),
    @NamedQuery(name="Music.deleteMusic", query="DELETE FROM Music m WHERE m.id = :id"),
    @NamedQuery(name="Music.searchMusicsByType", query="SELECT m FROM Music m WHERE m.type.id = :id"),
    @NamedQuery(name="Music.searchMusicsByAutor", query="SELECT m FROM Music m WHERE m.auteur = :auteur")
})

@Table(name="music")
public class Music implements Serializable{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="label")
    private String label;
    
    @Column(name="auteur")
    private String auteur;
    
    @Column(name="lien_audio")
    private String lien_audio;
    
    @ManyToOne
    @JoinColumn(name="id_administrateur")
    private Administrator administrator;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "reference",
        joinColumns = @JoinColumn(name = "id_musique"),
        inverseJoinColumns = @JoinColumn(name = "id_genre")
    )
    private List<Genre> genres;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
        name = "belong",
        joinColumns = @JoinColumn(name = "id_musique"),
        inverseJoinColumns = @JoinColumn(name = "id_type")
    )
    private Type type;
    


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

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLien_audio() {
        return lien_audio;
    }

    public void setLien_audio(String lien_audio) {
        this.lien_audio = lien_audio;
    } 
}
