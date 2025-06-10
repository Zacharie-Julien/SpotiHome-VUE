





import jakarta.ws.rs.FormParam;
import jakarta.servlet.http.Part;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class UploadForm {

    @FormParam("file")
    @PartType("application/octet-stream")
    public Part file;

    @FormParam("description")
    @PartType("text/plain")
    public String description;
}