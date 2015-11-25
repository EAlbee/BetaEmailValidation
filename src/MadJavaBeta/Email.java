package MadJavaBeta;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by EAlbee on 11/25/2015.
 */
@XmlRootElement
public class Email {
    private String emailAdress;
    private boolean isValid;

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String email) {
        this.emailAdress = email;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
