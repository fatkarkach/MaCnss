import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DossierMedical {
    Map<String, Integer> Medicament = new HashMap<String, Integer>();
    Map<String, String> MedcinsG = new HashMap<String, String>();
    Map<String, String> MedcinsS = new HashMap<String, String>();
    public static double  remourcement_consultation=0;

    public static double  remourcement_traitement=0;
    public static double  remourcement_totale=0;
    public String code_dossier;

    public  void taraiterdossier(){
        try (Scanner scanner = new Scanner(System.in)) {
            //medicament avec code bare
            Medicament.put("01000005", 80);
            Medicament.put("01000002", 90);
            Medicament.put("01000003", 100);
            Medicament.put("01000004", 40);
            //Medcin Général
            MedcinsG.put("01000005", "fatma karkach");
            MedcinsG.put("01000002", "Lhoussaine hssini");
            MedcinsG.put("01000003", "Badia outad");
            MedcinsG.put("01000004", "Mohamed karkach");
            //Medcien Speciale
            MedcinsS.put("02000005", "faho karkach");
            MedcinsS.put("02000002", "khadija hssini");
            MedcinsS.put("02000003", "boutaina karkach");
            MedcinsS.put("02000004", "jannat karkach");
            //cONNEXION
            Connexion connexion = new Connexion();
            connexion.connecterBD();
            System.out.println("Saisir  le code   de dossier :");
            code_dossier = scanner.nextLine();
            ResultSet rs = connexion.stmt.executeQuery("select * from consulation WHERE num_dossier = '" + code_dossier + "'");
            while(rs.next()) {
                System.out.println(rs.getString(2) + "  " + rs.getInt(3) + "  " + rs.getString(5));
                if (MedcinsG.containsKey(rs.getString(2))) {
                    remourcement_consultation += rs.getInt(3) * 80 / 100;
                } else if (MedcinsS.containsKey(rs.getString(2))) {
                    remourcement_consultation += rs.getInt(3) * 70 / 100;
                }
            }
                System.out.println(remourcement_consultation);
                //remboursement de traitement
                ResultSet rt = connexion.stmt.executeQuery("select * from traitement WHERE num_dossier = '" + code_dossier + "'");
                while(rt.next())
                {
                    System.out.println(rt.getString(2) + "  " + rt.getInt(3) + "  " + rt.getInt(4) + "  " + rt.getInt(5));
                    if (Medicament.containsKey(rt.getString(2))) {
                        remourcement_traitement+=(rt.getInt(3)*Medicament.get(rt.getString(2))/100)+(rt.getInt(4)+rt.getInt(5))*70/100;
                    }
                    else{
                        System.out.println("cette medicament  n'a pas rembource");
                    }
                }

                System.out.println(remourcement_traitement);

                remourcement_totale=remourcement_consultation+remourcement_traitement;
                if(remourcement_totale==0){
                    String insert = "INSERT INTO remboursement (prix_rembource,num_dossier,status,) VALUES ('" + remourcement_totale + "','" + code_dossier + "', 'Rejeté')";
                    connexion.stmt.executeUpdate(insert);
                } else if (remourcement_totale>1) {
                    String insert = "INSERT INTO remboursement (prix_rembource,num_dossier,status) VALUES ('" + remourcement_totale + "','" + code_dossier + "', 'Validé')";
                    connexion.stmt.executeUpdate(insert);
                }else {
                    String insert = "INSERT INTO remboursement (prix_rembource,status) VALUES ('" + remourcement_totale + "','" + code_dossier + "', 'incompli')";
                    connexion.stmt.executeUpdate(insert);

                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    }
