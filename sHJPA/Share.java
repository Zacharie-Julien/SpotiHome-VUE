/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sHJPA;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="share")
public class Share {
    
    @EmbeddedId
    private SharePK sharePk;
    
    @MapsId("id_musique")
    @ManyToOne
    @JoinColumn(name = "id_musique")
    private Music musique;

    @MapsId("id_auditeur")
    @ManyToOne
    @JoinColumn(name = "id_auditeur")
    private Auditor auditeur;       
    
    @Column(name="date_end")
    private Timestamp dateEnd;

    public SharePK getSharePk() {
        return sharePk;
    }

    public void setSharePk(SharePK sharePk) {
        this.sharePk = sharePk;
    }

    public Music getMusique() {
        return musique;
    }

    public void setMusique(Music musique) {
        this.musique = musique;
    }

    public Auditor getAuditeur() {
        return auditeur;
    }

    public void setAuditeur(Auditor auditeur) {
        this.auditeur = auditeur;
    }

    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }        
    
    public Timestamp getDateNow() {
        return (sharePk != null) ? sharePk.getDateNow() : null;
    }

    public void setDateNow(Timestamp dateNow) {
        if (sharePk == null) {
            sharePk = new SharePK();
        }
        sharePk.setDateNow(dateNow);
    }
}
