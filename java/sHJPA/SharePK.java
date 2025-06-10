/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sHJPA;

import jakarta.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
public class SharePK {

    private Integer id_musique;
    private Integer id_auditeur;
    private Timestamp date_now;

    public Integer getId_musique() {
        return id_musique;
    }

    public void setId_musique(Integer id_musique) {
        this.id_musique = id_musique;
    }

    public Integer getId_auditeur() {
        return id_auditeur;
    }

    public void setId_auditeur(Integer id_auditeur) {
        this.id_auditeur = id_auditeur;
    }

    public Timestamp getDateNow() {
        return date_now;
    }

    public void setDateNow(Timestamp date_now) {
        this.date_now = date_now;
    }    
}
