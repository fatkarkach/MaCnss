import javax.swing.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminDashboard {

    public static Scanner sc = new Scanner(System.in);
    static Admin admin=new Admin();

    public static Login login=new Login();
    public static void afficher() throws SQLException{
        System.out.println("============================Bienvenue au AdminDashboard=======================");
        System.out.println("Saisir votre choix: 1)Ajouter un agent      2)Afficher des Agents       3)Déconnexion");
        try{
        int choix = sc.nextInt();
        switch(choix){

            case 1:
                System.out.println("==========================================Ajout d'un Agent==========================================");
                admin.ajouter();
                break;

            case 2:
                System.out.println("==========================================Afficher des Agents ========================================================");
                admin.afficherAgent();
                break;

            case 3:
                System.out.println("==========================================Déconnexion========================================================");
                login.connecterchoix();
                break;
            default:
                System.out.println("Choix incorrect");
                break;
        }}catch ( Exception e)
        {
            System.out.println("Something went wrong.");
        }

    }

}
