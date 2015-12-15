# BetaEmailValidation

Using BetaEmailValidation

SETUP

clone project from https://github.com/MadJavaTeamBeta/BetaEmailValidation
run the EmailValidationBeta. This will host the web service on a Jersey server at  http://localhost:9012/EmailValidation

CONSUMPTION {emailtest} will be replaced with the String value of the request i.e. "dbsullivan@madisoncollege.edu"

for JSON    @Path("/json/{emailAddress}")

for XML     @Path("/xml/{emailAddress}")

An Example JSON call and JSON result:
```
 Can call from the browser like this example: http://localhost:9012/EmailValidation/json/dbsullivan@madisoncollege.edu
 {"emailAddress":"dbsullivan@madisoncollege.edu","isValid":true}
```

An Example call using 

 1. Jackson (for the JSON object mapping to the Email object)   
 2. Jersey (for the Restful client)

```
package com.TennisApp.java.utilities;

import com.TennisApp.java.entity.Email;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 * Created by Dave on 12/06/2015.
 * This class uses a webservice developed internally, indicated in the URL, to validate the email passed.
 * Requires the Email object imported in the class that handle either JSON or XML webservice return types.
 * An Example JSON result: {"emailAdress":"dbsullivan@madisoncollege.edu","isValid":true}
 */
public class WebServiceEmailValidationJSON {
    private final Logger logger = Logger.getLogger(this.getClass());

    private Client client;
    private String REST_SERVICE_URL = "http://localhost:9012/EmailValidation";
    Email email = new Email();

    /**
     * This method will return a Boolean result, returnValue after calling the webservice to validate the
     * email input parameter, emailToTest.
     * @param emailToTest
     * @return returnValue
     */
    public Boolean isValidEmail (String emailToTest)  {

        Boolean returnValue=false;

        this.client = ClientBuilder.newClient();
        String callResponse = client
                .target(REST_SERVICE_URL)
                .path("/json/{emailAddress}")
                .resolveTemplate("emailAddress", emailToTest)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);


        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            this.email = jsonMapper.readValue(callResponse, Email.class);
        } catch (JsonMappingException e) {
            logger.error("Exception:", e);
        } catch (JsonParseException e) {
            logger.error("Exception:", e);
        } catch (IOException e) {
            logger.error("Exception:", e);
        }

        returnValue = email.getIsValid();

        return returnValue;
    }
}

```

```
import com.TennisApp.java.utilities.WebServiceEmailValidationJSON;

    public void performValidations (String firstName, String lastName, String email, String gender, String ntrpLevel, String phoneNumber) {

//        WebServiceEmailValidation webServiceEmailValidation = new WebServiceEmailValidation(); // this is String version
        WebServiceEmailValidationJSON webServiceEmailValidation = new WebServiceEmailValidationJSON(); // this is JSON version

        if (firstName == null || firstName.equals("")) {
            AddMessage = "Please enter missing First Name.";
            ErrorType="firstNameErr";
        } else if (lastName == null || lastName.equals("")) {
            AddMessage = "Please enter missing Last Name.";
            ErrorType="lastNameErr";
        } else if (email == null || email.equals("")) {
            AddMessage = "Please enter missing email.";
            ErrorType="emailErr";
            //Now validate using WebService call, since email has passed as Not NULL or blank, call method passing email string
        } else if (!webServiceEmailValidation.isValidEmail(email)) {
            AddMessage = "Please enter valid email.";
            ErrorType="emailErr";
        ...
        
```
