import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connexion {
    public static Statement stmt;
    public  static  Connection con = null;
    public  void connecterBD() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cnss", "root", "");
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }



}
