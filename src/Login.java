
import java.sql.ResultSet;
import java.util.Scanner;
import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
public class Login {
    public static Timer timer = new Timer(); // creating timer
    public static TimerTask task = new MyTask(); // creating timer task
    public static void connecterchoix()throws MessagingException, IOException{
;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=======================Bienvenue au Macnss===================");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("=======================Vous vous êtes qui?===================");
        System.out.println("Saisir votre choix: 1)Administrateur     2)AgentCNSS      3)Membre Assures     3)quitter");
        int choix = scanner.nextInt();
        switch(choix){

            case 1:
                connecterAD();

                break;

            case 2:
                connecterAG();

                break;

            case 3:
                connectermembre();

                break;
            default:
                System.out.println("Choix incorrect");
                break;
        }
    }
    //admin
    public static void connecterAD() {

        try {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print(" Entrez Adresse Email => ");
                String email = scanner.nextLine();

                System.out.print(" Enter Mode passe => ");
                String password = scanner.nextLine();
                Connexion connexion=new Connexion();
                connexion.connecterBD();
                ResultSet rs=connexion.stmt.executeQuery("select * from users");
                boolean loginA=false;
                // partie admin
                while(rs.next()) {
                    if (rs.getString(2).equals(email) && rs.getString(3).equals(password) && rs.getInt(4)==0) {
                        loginA = true;
                    }
                }
                if (loginA) {
                   AdminDashboard adminDashboard=new AdminDashboard();
                   adminDashboard.afficher();

                }
                else{
                    System.out.println("Ereure Essayer avec autre compte");
                    connecterchoix();
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    //agentcnss
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
    public static void connecterAG()throws MessagingException, IOException{

        try {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print(" Entrez Adresse Email => ");
                String email = scanner.nextLine();

                System.out.print(" Enter Mode passe => ");
                String mode_passe = scanner.nextLine();
                Connexion connexion=new Connexion();
                connexion.connecterBD();
                ResultSet rs=connexion.stmt.executeQuery("select * from users");
                boolean loginU=false;
                // partie admin
                while(rs.next()) {
                    if (rs.getString(2).equals(email) && rs.getString(3).equals(mode_passe) && rs.getInt(4)==1) {
                        String coede_active = generate_code(mode_passe);
                        timer.schedule(task,1000,5*60*1000);
                        Fatma_send_mail_active_account.go(email, coede_active);
                        // scheduling the task at the specified time at fixed-rate
                        System.out.println("set code activate  :");
                        Scanner sc = new Scanner(System.in);
                        String password = sc.nextLine();
                        if (password.equals(coede_active)){
                            System.out.println(" fatma your account is active  ");
                            timer.cancel();
                            timer.purge();
                            loginU = true;
                        }else
                            System.out.println(" fatma your account not active  ");
                    }
                }
                if (loginU) {
                    AgentDashboard agentDashboard=new AgentDashboard();
                    agentDashboard.afficher();

                }
                else{
                    System.out.println("Ereure Essayer avec autre compte");
                    connecterchoix();
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    //membre
    public static void connectermembre() {

        try {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print(" Entrez Adresse Email => ");
                String email = scanner.nextLine();
                System.out.println(email);
                System.out.print(" Enter Mode passe => ");
                String password = scanner.nextLine();
                System.out.println(password);
                Connexion connexion=new Connexion();
                connexion.connecterBD();
                ResultSet rs=connexion.stmt.executeQuery("select * from patient");
                boolean loginM=false;
                // partie admin
                while(rs.next()) {
                    if (rs.getString(4).equals(email) && rs.getString(5).equals(password)) {
                        loginM = true;
                    }
                }
                if (!loginM) {
                    DashboardPatient dashboardPatient=new DashboardPatient();
                    dashboardPatient.afficherP();

                }
                else{
                    System.out.println("Ereure Essayer avec autre compte");
                    connecterchoix();
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
