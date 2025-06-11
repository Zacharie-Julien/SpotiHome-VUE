/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConfigWAR;

import java.util.Set;

/**
 *
 * @author d4cd13ger
 */
@jakarta.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends jakarta.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(EndPointWAR.GenreWAR.class);
        resources.add(EndPointWAR.MusicWAR.class);
        resources.add(EndPointWAR.PlayListWAR.class);
        resources.add(EndPointWAR.TypeWAR.class);
        resources.add(SecurityWAR.AuthFilter.class);
        resources.add(SecurityWAR.AuthWAR.class);
        resources.add(SecurityWAR.CorsFilter.class);
    }
    
}
