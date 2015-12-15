package MadJavaBeta;

/**
 * Project: BetaEmailValidation
 * Class: Email
 * Created by EAlbee
 * 11/25/2015
 */

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Email {
    private String emailAddress;
    private boolean isValid;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
