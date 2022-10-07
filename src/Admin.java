import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
    Agents agents=new Agents();
    public  static String email="";
    public static String mode_passe="";
    public static  AdminDashboard adminDashboard=new AdminDashboard();
    public void ajouter(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Saisir nom:");
            String nom = scanner.nextLine();
            System.out.println("Saisir prénom:");
            String prenom = scanner.nextLine();
            email=nom+"."+prenom+"@Cnss.com";
            System.out.println("Saisir un  Mode passe :");
            String password=scanner.nextLine();
            mode_passe =password;
            System.out.println(mode_passe);
            Connexion connexion = new Connexion();
            connexion.connecterBD();
            String insert = "INSERT INTO users (email, mode_passe, type) VALUES ('" + email + "', '" + mode_passe + "', " + 1 + ")";
            connexion.stmt.executeUpdate(insert);
            adminDashboard.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //afficher les agents
    public void afficherAgent() throws SQLException {
        Connexion connexion = new Connexion();
        connexion.connecterBD();
        ResultSet rs=connexion.stmt.executeQuery("select * from users");
        int i=0;
        while(rs.next())
            if(rs.getInt(4)==1) {
                System.out.println("Adresse Email:" + rs.getString(2) + "             " + "Mode passe :" + rs.getString(3));
                System.out.println("____________________________________________"+i+"____________________________________________");
                i++;
            }
        adminDashboard.afficher();
    }
}
