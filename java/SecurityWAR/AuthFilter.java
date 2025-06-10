/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecurityWAR;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.crypto.SecretKey;

/**
 *
 * @author d4cd13ger
 */
@Secured
@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
                
        // A mettre dans un fichier de CONF 
        SecretKey key = Keys.hmacShaKeyFor("MaSuperCleSecreteTrèsLongueEtSécurisée123!".getBytes(StandardCharsets.UTF_8));
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if(!(authHeader == null)) {                                
            try{
                
                // Doc JJWT link: https://github.com/jwtk/jjwt?tab=readme-ov-file
                
                Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(authHeader);                           
                
            }            
            catch (JwtException e) {
                
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Invalid Token").build());
                
            }                  
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Token is NULL").build());
        }
    }
}