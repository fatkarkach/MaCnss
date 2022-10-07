import javax.mail.MessagingException;
import java.io.IOException;

public class Main {
    public static void main(String[] args)throws MessagingException, IOException {
        Login login=new Login();
        login.connecterchoix();
    }
}