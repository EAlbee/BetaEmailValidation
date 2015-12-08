package MadJavaBeta;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import java.io.IOException;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

/**
 * Created by EAlbee, DSullivan, MGundrum on 11/25/2015.
 * @Author MadJavaBeta
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/EmailValidation")
public class EmailValidationBeta {
    private final Logger logger = Logger.getLogger(this.getClass());

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces({"application/json", "text/xml"})
    @Path("/json/{emailAddress}")
    public Email isEmailValidJson(@PathParam("emailAddress") String emailAddress) {
        //String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        //java.util.regex.Matcher m = null;
        Email email = new Email();
        try {

            email.setEmailAdress(emailAddress);


            //java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            //m = p.matcher(email.getEmailAdress());
            //m = parseRegex(emailAddress);

            email.setIsValid(parseRegex(emailAddress));

            return email;

        } catch (Exception e) {
            logger.error("isEmailValidJson", e);
            email.setIsValid(false);
            return email;
        }
    }

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/xml")
    @Path("/xml/{emailAddress}")
    public Email isEmailValidHtml(@PathParam("emailAddress") String emailAddress) {
//        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        //java.util.regex.Matcher m = null;
        Email email = new Email();
        try {

            email.setEmailAdress(emailAddress);


            //java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            //m = p.matcher(email.getEmailAdress());
            //m = parseRegex(emailAddress);

            email.setIsValid(parseRegex(emailAddress));

            return email;

        } catch (Exception e) {
            logger.error("isEmailValidHtml", e);
            email.setIsValid(false);
            return email;
        }
    }

    public boolean parseRegex(String emailAddress) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Matcher m = null;

        try {

            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            m = p.matcher(emailAddress);

        } catch (Exception e) {
            logger.error("parseRegex exception", e);
        } finally   {
            return m.matches();
        }

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
