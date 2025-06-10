/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sHEJB;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import sHJPA.Genre;

@Stateless
public class GenreEJB {
 
    @PersistenceContext(unitName="spotiHome-ejbPU")
    private EntityManager em;
 
    public void addGenre(String label){
        Genre addGenre = new Genre();
        addGenre.setLabel(label);
        
        em.persist(addGenre);
    }    
    
    public void deleteGenre(Integer id){
        em.createNamedQuery("Genre.deleteGenreById").setParameter("id", id).executeUpdate();
    }
    
    public List<Genre> getAllGenre(){
        return em.createNamedQuery("Genre.getAllGenre", Genre.class).getResultList();
    }
    
    public void updateLabelGenre(String label, Integer id){
        em.createNamedQuery("Genre.updateNameGenre").setParameter("label", label).setParameter("id", id).executeUpdate();
    }
}
    