import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class Agents {
    public  static String nom="";
    public  static String prenom="";
    public  static String email="";
    public static String mode_passe="";
    public static String CiN="";
    public static String matricule="";
    public static String generate_code(String input)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32)
            {
                hashtext = "0" + hashtext;
            }
            return hashtext.substring(0,5);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
    //ajouter un membre
    public void ajouterMembre() throws MessagingException, IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Saisir nom:");
            nom = scanner.nextLine();
            System.out.println("Saisir prénom:");
            prenom = scanner.nextLine();
            System.out.println("Saisir CIN:");
                 CiN = scanner.nextLine();
            email=nom+"."+prenom+"@MaCnss.com";
            System.out.println("Saisir un  Mode passe :");
            String password=scanner.nextLine();
            mode_passe =password;
            System.out.println(mode_passe);
            matricule=generate_code(nom+prenom);
            Connexion connexion = new Connexion();
            connexion.connecterBD();
            String insert = "INSERT INTO patient (nom,prenom,CIN,email,mode_passe,matricule) VALUES ('" + nom + "', '" + prenom + "', '" + CiN + "','" + email + "','" + mode_passe + "','" + matricule + "')";
            connexion.stmt.executeUpdate(insert);
            AgentDashboard agentDashboard=new AgentDashboard();
            agentDashboard.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
