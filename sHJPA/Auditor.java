/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sHJPA;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;



@Entity
@Table(name="auditor")
public class Auditor extends User implements Serializable {
    
}
