
import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CrDossier {
    public  static String medcin="";
    public  static int prix=0;
    public  static String  medicament="vide";
    public  static  int id_patient=0;
    public  static  int prix_médicament	=0;
    public  static  int prix_scanner=0;
    public  static  int prix_analyse=0;
    public static String code_dossier;
    public  static String num_dossier="";
    Scanner  sc=new Scanner(System.in);
    public void saisirM()
    {
        System.out.println("Saisir  le matricule  de membre:");
        code_dossier= sc.nextLine();
    }
    public void AjoutAutre() throws MessagingException, IOException {
        System.out.println("Saisir votre choix : 1)Ajouter autre document      2)Enregistrer ");
        int choix = sc.nextInt();
        if(choix==1)
        {
            ajoutConsultation();
        }
        else if(choix==2) {
            System.out.println("==========================================Enregistrer ========================================================");
            AgentDashboard agentDashboard = new AgentDashboard();
            agentDashboard.afficher();
        }
        else {
            System.out.println("choix incorrect");
            AgentDashboard agentDashboard = new AgentDashboard();
            agentDashboard.afficher();
        }

    }
    public void ajoutD() throws MessagingException, IOException{
            saisirM();
            ajoutConsultation();
    }

    public void ajoutConsultation() throws MessagingException, IOException{
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Saisir IDE de medcin:");
            medcin = scanner.nextLine();
            System.out.println("entrez Prix de consultation:");
            prix = scanner.nextInt();
            Connexion connexion = new Connexion();
            connexion.connecterBD();
            ResultSet rs=connexion.stmt.executeQuery("select * from patient WHERE matricule = '" + code_dossier + "'");
            if(rs.next()){
                id_patient = rs.getInt(1);
                num_dossier=code_dossier;
            }
            System.out.println(id_patient);
            //insere les consulation
            String insert = "INSERT INTO consulation (medcin,prix_consulation,id_patient,num_dossier) VALUES ('" + medcin + "', '" + prix + "','" + id_patient + "','" + num_dossier + "')";
            connexion.stmt.executeUpdate(insert);
            //saisir dossiers
            System.out.println(num_dossier);
            String sql = "INSERT INTO  dossier (id_patient,num_dossier ) VALUES ('" + id_patient + "','" + num_dossier + "')";
            connexion.stmt.executeUpdate(sql);
            //fin
            System.out.println("============================Ajouter l'ordonnance =======================");
            System.out.println("Saissir votre choix: 1)oui      2)non");
            int choix = sc.nextInt();
            if(choix==1) {
                ajouttraitement();
            }else if(choix==2){
                AjoutAutre();
            }else{
                System.out.println("choix incorrect");
                AjoutAutre();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void ajouttraitement() throws MessagingException, IOException{
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Saisir médicament:");
            medicament = scanner.nextLine();
            System.out.println("Saisir  Prix médicament:");
            prix_médicament=scanner.nextInt();
            System.out.println("vous avez un Un radio ou scanner:");
            System.out.println("saisir votre choix: 1)oui       2)Non");
            int choix;
            choix = scanner.nextInt();
            if(choix==1){
                System.out.println("Saisir Prix:");
                prix_scanner=scanner.nextInt();
            } else if (choix==2) {
                prix_scanner=0;
            }else {
                System.out.println("choix incorrect:");
            }
            System.out.println("vous avez un  Analyse laboratoire:");
            System.out.println("saisir votre choix: 1)oui       2)Non");
            int choixanalyse;
            choixanalyse = scanner.nextInt();
            if( choixanalyse==1){
                System.out.println("Saisir Prix:");
                prix_analyse=scanner.nextInt();

            } else if ( choixanalyse==2) {
                prix_analyse=0;
            }else {
                System.out.println("choix incorrect:");
            }
            Connexion connexion = new Connexion();
            connexion.connecterBD();
            ResultSet rs=connexion.stmt.executeQuery("select * from patient WHERE matricule = '" + code_dossier + "'");
            if(rs.next()){
                id_patient = rs.getInt(1);
                num_dossier=code_dossier;
            }
            System.out.println(id_patient);
            String insert = "INSERT INTO traitement (medicament,prix_médicament,prix_scanner,prix_analyse,id_patient,num_dossier) VALUES ('" + medicament + "','" + prix_médicament + "', '" + prix_analyse + "', '" + prix_scanner + "','" + id_patient + "','" + num_dossier + "')";
            connexion.stmt.executeUpdate(insert);
            //fin
            System.out.println("============================Ajouter autre doucument =======================");
            AjoutAutre();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
