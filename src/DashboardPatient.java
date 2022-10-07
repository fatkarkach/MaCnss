import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DashboardPatient {
    public static void afficherP() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        String code_dossier;
        System.out.println("=======================Bienvenue au PatienDashboard===================");
        Connexion connexion = new Connexion();
        connexion.connecterBD();
        System.out.println("Saisir  le code   de dossier :");
        code_dossier = scanner.nextLine();
        ResultSet rs = connexion.stmt.executeQuery("select * from remboursement WHERE num_dossier = '" + code_dossier + "'");
        while(rs.next()) {
            System.out.println(" Rembourcement :"+ rs.getInt(2) + " num_dossier " + rs.getString(3) + " Status " + rs.getString(4));
        }
    }
}
