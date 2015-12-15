package MadJavaBeta;

/**
 * Project: BetaEmailValidation
 * Class:
 * Created by Student
 * 12/7/2015
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class EmailValidationBetaTest {

    @Test
    public void testIsEmailValidJson() throws Exception {
    }

    @Test
    public void testIsEmailValidHtml() throws Exception {
    }

    @Test
    public void testParseRegex() throws Exception {
        EmailValidationBeta validate = new EmailValidationBeta();
        Email email = new Email();
            email.setEmailAddress("EAlbee@madisoncollege.edu");
            email.setIsValid(validate.parseRegex(email.getEmailAddress()));

        assertEquals(email.getIsValid(), true);
    }

    @Test
    public void testParseRegex2() throws Exception {
        EmailValidationBeta validate = new EmailValidationBeta();
        Email email = new Email();
            email.setEmailAddress("EAlbee@madisoncollege");
            email.setIsValid(validate.parseRegex(email.getEmailAddress()));

        assertEquals(email.getIsValid(), false);
    }
}