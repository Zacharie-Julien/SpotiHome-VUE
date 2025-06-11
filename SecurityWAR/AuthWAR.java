/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package SecurityWAR;
import static SecurityWAR.HashAlgo.generateSHA256Hash;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import sHEJB.AuthEJB;
import sHEJB.UsersEJB;
import sHJPA.User;


/**
 * REST Web Service
 *
 * @author d4cd13ger
 */
@Path("/auth")
@Stateless
public class AuthWAR {
    
    @Inject
    private UsersEJB ejb;
    
    @Inject
    private AuthEJB authEjb;   
    
    @GET
    @Path("/login/{login}/passwd/{passwd}")
    public Response getAuthLogin(@PathParam("login") String login, @PathParam("passwd") String passwd, @Context HttpServletResponse response) {
        
        
        // A mettre dans un fichier de CONF 
        Key key = Keys.hmacShaKeyFor("MaSuperCleSecreteTrèsLongueEtSécurisée123!".getBytes(StandardCharsets.UTF_8));
        if (authEjb.verifyAuth(login, generateSHA256Hash(passwd))) {
                 
            User user = ejb.getUserByLogin(login);
            String role = authEjb.verifAdmin(user);
            
            String token = Jwts.builder()
                    .issuer("d4d13ger.fr")
                    .subject("utilisateur")
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 3600000)) // 1h
                    .claim("id", user.getId())
                    .claim("role", role) // A modifier 
                    .claim("login", user.getLogin())
                    .signWith(key)
                    .compact();          
            Cookie cookie = new Cookie("JWT", token);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(3600); // Le cookie est valable dans le naviguateur pendant un durée d'une heure
            response.addHeader("Set-Cookie","JWT=" + token + "; Path=/; Max-Age=3600; Secure; SameSite=None");
            return Response.ok().build();
            
        } 
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
