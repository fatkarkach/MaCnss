import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Scanner;

public class AgentDashboard {
    public static Scanner sc = new Scanner(System.in);

    public static Login login=new Login();
    public static void afficher() throws MessagingException, IOException
    {
        System.out.println("=======================Bienvenue au AgentDashboard===================");
        System.out.println("Saisir votre choix: 1)Ajouter un nouveau membre      2)Ajouter un dossier medicale      3)Remboursement       4)Déconnexion");
        int choix = sc.nextInt();
        switch(choix){

            case 1:
                System.out.println("==========================================Ajouter un nouveau membre==========================================");
                Agents agents=new Agents();
                agents.ajouterMembre();

                break;

            case 2:
                System.out.println("==========================================Ajouter un dossier medicale========================================================");
                CrDossier crDossier=new CrDossier();
                crDossier.ajoutD();
                break;

            case 3:
                System.out.println("==========================================Remboursement========================================================");
                DossierMedical dossierMedical =new DossierMedical();
                dossierMedical.taraiterdossier();

                break;
            case 4:
                System.out.println("==========================================Déconnexion========================================================");
                login.connecterchoix();
                break;
            default:
                System.out.println("Choix incorrect");
                break;
        }
    }
}
