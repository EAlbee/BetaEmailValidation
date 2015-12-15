package MadJavaBeta;

/**
 * Created by EAlbee, DSullivan, MGundrum on 11/25/2015.
 * @author MadJavaBeta
 */

import javax.ws.rs.*;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;

// The Java class will be hosted at the URI path "/EmailValidation"
@Path("/EmailValidation")
public class EmailValidationBeta {
    private final Logger logger = Logger.getLogger(this.getClass());

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces({"application/json", "text/xml"})
    @Path("/json/{emailAddress}")
    public Email isEmailValidJson(@PathParam("emailAddress") String emailAddress) {
        Email email = new Email();
        try {
            email.setEmailAddress(emailAddress);
            email.setIsValid(parseRegex(emailAddress));
        } catch (Exception e) {
            logger.error("isEmailValidJson", e);
            email.setIsValid(false);
        }
        return email;
    }

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/xml")
    @Path("/xml/{emailAddress}")
    public Email isEmailValidHtml(@PathParam("emailAddress") String emailAddress) {
        Email email = new Email();
        try {
            email.setEmailAddress(emailAddress);
            email.setIsValid(parseRegex(emailAddress));
        } catch (Exception e) {
            logger.error("isEmailValidHtml", e);
            email.setIsValid(false);
        }
        return email;
    }

    public boolean parseRegex(String emailAddress) {
        java.util.regex.Matcher m = null;
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        try {
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            m = p.matcher(emailAddress);
        } catch (Exception e) {
            logger.error("parseRegex exception", e);
        }
        return m.matches();
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9012/");
        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9012/EmailValidation");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
