package MadJavaBeta;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

/**
 * Created by EAlbee, DSullivan, MGundrum on 11/25/2015.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/EmailValidation")
public class EmailValidationBeta {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/json")
    @Path("/json/{emailAddress}")
    public String isEmailValidJson(@PathParam("emailAddress") String emailAddress) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Matcher m = null;
        try {

            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            m = p.matcher(emailAddress);
            return "[isValid] : " + m.matches();

        } catch (Exception e) {
            return "[isValid] : false";
        }
    }

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/html")
    @Path("/html/{emailAddress}")
    public String isEmailValidHtml(@PathParam("emailAddress") String emailAddress) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Matcher m = null;
        try {

            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            m = p.matcher(emailAddress);
            return "[isValid] : " + m.matches();

        } catch (Exception e) {
            return "[isValid] : false";
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
