import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args)  {

        try {
            ReadSql readSql = new ReadSql("jdbc:sqlserver://ASUS;Database=pzu;user=java;password=1234;trustServerCertificate=true;");
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        new LogWindow("Towarysztwo Ubez", readSql);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            System.out.println("Baza nie odczytała się: " + e.getMessage());
        }
    }
}