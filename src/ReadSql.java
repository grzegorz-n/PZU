import javax.swing.*;
import java.sql.*;

public class ReadSql {
    private static String CONNECTION_STRING;

    private static String TABLE_TOW_UBE = "Tow_Ube";
    private static String COLUMN_TOW_UBE_ID = "KOD";
    private static String COLUMN_TOW_UBE_NAZWA = "NAZWA";
    private static String COLUMN_TOW_UBE_STATUS = "STATUS";
    private static int INDEX_TOW_UBE_ID = 1;
    private static int INDEX_TOW_UBE_NAZWA = 2;
    private static int INDEX_TOW_UBE_STATUS = 3;

    private static String TABLE_PRO_UBEZ = "Pro_ubez";
    private static String COLUMN_PRO_UBEZ_ID = "KOD";
    private static String COLUMN_PRO_UBEZ_OPIS = "OPIS";
    private static String COLUMN_PRO_UBEZ_TOW_UBE_ID = "TOW_UBE_KOD";
    private static String COLUMN_PRO_UBEZ_STATUS = "STATUS";
    private static int INDEX_PRO_UBEZ_ID = 1;
    private static int INDEX_PRO_UBEZ_OPIS = 2;
    private static int INDEX_PRO_UBEZ_TOW_UBE_ID = 3;

    private static String TABLE_POLISA = "Polisa";
    private static String COLUMN_POLISA_ID = "KOD";
    private static String COLUMN_POLISA_DATA = "DATA_WYSTAWIENIA";
    private static String COLUMN_POLISA_WYSOKOSC = "WYSOKOSC_UBEZPIECZENIA";
    private static String COLUMN_POLISA_PRO_UBEZ_ID = "PRO_UBEZ_KOD";
    private static String COLUMN_POLISA_AGENCI_ID = "AGENCI_KOD";
    private static String COLUMN_POLISA_KLIENCI_ID = "KLIENCI_KOD";
    private static int INDEX_POLISA_ID = 1;
    private static int INDEX_POLISA_DATA = 2;
    private static int INDEX_POLISA_WYSOKOSC = 3;
    private static int INDEX_POLISA_PRO_UBEZ_ID = 4;
    private static int INDEX_POLISA_AGENCI_ID = 5;
    private static int INDEX_POLISA_KLIENCI_ID = 6;

    private static String TABLE_SKLADKI = "SkŁadki";
    private static String COLUMN_SKLADKI_ID = "KOD";
    private static String COLUMN_SKLADKI_KWOTA = "KWOTA";
    private static String COLUMN_SKLADKI_DATA = "DATA";
    private static String COLUMN_SKLADKI_POLISA_ID = "POLSIA_KOD";
    private static int INDEX_SKLADKI_ID = 1;
    private static int INDEX_SKLADKI_KWOTA = 2;
    private static int INDEX_SKLADKI_DATA = 3;
    private static int INDEX_SKLADKI_POLISA_ID = 4;

    private static String TABLE_RELATION = "Relation_3";
    private static String COLUMN_RELATION_AGENCI_ID = "AGENCI_KOD";
    private static String COLUMN_RELATION_PRO_UBEZ_ID = "PRO_UBEZ_KOD";
    private static int INDEX_RELATION_AGENCI_ID = 1;
    private static int INDEX_RELATION_PRO_UBEZ_ID = 2;

    private static String TABLE_AGENCI = "Agenci";
    private static String COLUMN_AGENCI_ID = "KOD";
    private static String COLUMN_AGENCI_NAZWISKO = "NAZWISKO";
    private static String COLUMN_AGENCI_ADRES = "ADRES";
    private static String COLUMN_AGENCI_STATUS = "STATUS";
    private static int INDEX_AGENCI_ID = 1;
    private static int INDEX_AGENCI_NAZWISKO = 2;
    private static int INDEX_AGENCI_ADRES = 3;

    private static String TABLE_KLIENCI = "Klienci";
    private static String COLUMN_KLIENCI_ID = "KOD";
    private static String COLUMN_KLIENCI_NAZWISKO = "NAZWISKO";
    private static String COLUMN_KLIENCI_ADRES = "ADRES";
    private static String COLUMN_KLIENCI_STATUS = "STATUS";
    private static int INDEX_KLIENCI_ID = 1;
    private static int INDEX_KLIENCI_NAZWISKO = 2;
    private static int INDEX_KLIENCI_ADRES = 3;

    Connection connection;

    public ReadSql(String CONNECTION_STRING) throws SQLException {
        this.CONNECTION_STRING = CONNECTION_STRING;
        connection = DriverManager.getConnection(CONNECTION_STRING);
    }

    public void addTowUbe(String name) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM " + TABLE_TOW_UBE);
        resultSet.next();
        int rows = resultSet.getInt(1);
        rows = rows + 1;
        statement.execute(" INSERT INTO " + TABLE_TOW_UBE + " VALUES (" + rows + ", '" + name + "', 'true')");
    }

    public void deleteTowUbe(int kod) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE " + TABLE_TOW_UBE + " SET " + COLUMN_TOW_UBE_STATUS + " = 'FALSE' WHERE " + COLUMN_TOW_UBE_ID + " = " + kod);
    }

    public void editTowUbe(String name, int kod) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE " + TABLE_TOW_UBE + " SET " + COLUMN_TOW_UBE_NAZWA + " = '" + name + "' WHERE " + COLUMN_TOW_UBE_ID + " = " + kod);
    }

    public DefaultListModel<TowUbe> loadTowUbe() throws SQLException {
        DefaultListModel<TowUbe> list = new DefaultListModel<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT " + COLUMN_TOW_UBE_NAZWA + ", " + COLUMN_TOW_UBE_ID + " FROM " + TABLE_TOW_UBE + " WHERE " + COLUMN_TOW_UBE_STATUS + "= 'TRUE'");
        int i = 0;
        while (resultSet.next()) {
            list.add(i, new TowUbe(resultSet.getString(1), resultSet.getInt(2)));
            i++;
        }
        return list;
    }

    public DefaultListModel<Costumer> loadCostumers() throws SQLException {
        DefaultListModel<Costumer> list = new DefaultListModel<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT " + COLUMN_KLIENCI_ID + ", " + COLUMN_KLIENCI_NAZWISKO + ", " + COLUMN_KLIENCI_ADRES + " FROM " + TABLE_KLIENCI + " WHERE STATUS = 'TRUE'");
        int i = 0;
        while (resultSet.next()) {
            list.add(i, new Costumer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            i++;
        }
        return list;

    }

    public DefaultListModel<Products> loadProducts(int towUbe) throws SQLException {
        DefaultListModel<Products> list = new DefaultListModel<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT " + COLUMN_PRO_UBEZ_ID + ", " + COLUMN_PRO_UBEZ_OPIS + ", " + COLUMN_PRO_UBEZ_TOW_UBE_ID + " FROM " + TABLE_PRO_UBEZ + " WHERE STATUS = 'TRUE' AND " + COLUMN_PRO_UBEZ_TOW_UBE_ID + " = " + towUbe);
        int i = 0;
        while (resultSet.next()) {
            list.add(i, new Products(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            i++;
        }
        return list;
    }

    public DefaultListModel<Agent> loadAgents() throws SQLException {
        DefaultListModel<Agent> list = new DefaultListModel<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT " + COLUMN_AGENCI_ID + ", " + COLUMN_AGENCI_NAZWISKO + ", " + COLUMN_AGENCI_ADRES + " FROM " + TABLE_AGENCI + " WHERE STATUS = 'TRUE'");
        int i = 0;
        while (resultSet.next()) {
            list.add(i, new Agent(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            i++;
        }
        return list;
    }

    public void addCostumer(String name, String address) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM " + TABLE_KLIENCI);
        resultSet.next();
        int rows = resultSet.getInt(1);
        rows = rows + 1;
        statement.execute(" INSERT INTO " + TABLE_KLIENCI + " VALUES (" + rows + ", '" + name + "', '" + address + "', 'true')");
    }

    public void deleteCostumer(int kod) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE " + TABLE_KLIENCI + " SET " + COLUMN_KLIENCI_STATUS + " = 'FALSE' WHERE " + COLUMN_KLIENCI_ID + " = " + kod);
    }

    public void editCostumer(String name, String address, int kod) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE " + TABLE_KLIENCI + " SET " + COLUMN_KLIENCI_NAZWISKO + " = '" + name + "' WHERE " + COLUMN_KLIENCI_ID + " = " + kod);
        statement.execute("UPDATE " + TABLE_KLIENCI + " SET " + COLUMN_KLIENCI_ADRES + " = '" + address + "' WHERE " + COLUMN_KLIENCI_ID + " = " + kod);
    }

    public void addProducts(String description, int towUbeId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM " + TABLE_PRO_UBEZ);
        resultSet.next();
        int rows = resultSet.getInt(1);
        rows = rows + 1;
        statement.execute(" INSERT INTO " + TABLE_PRO_UBEZ + " VALUES (" + rows + ", '" + description + "', '" + towUbeId + "', 'true')");
    }

    public void deleteProducts(int kod) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE " + TABLE_PRO_UBEZ + " SET " + COLUMN_PRO_UBEZ_STATUS + " = 'FALSE' WHERE " + COLUMN_PRO_UBEZ_ID + " = " + kod);
    }

    public void editProducts(String name, int kod) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE " + TABLE_PRO_UBEZ + " SET " + COLUMN_PRO_UBEZ_OPIS + " = '" + name + "' WHERE " + COLUMN_PRO_UBEZ_ID + " = " + kod);
    }

    public void addAgent(String description, String address) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM " + TABLE_AGENCI);
        resultSet.next();
        int rows = resultSet.getInt(1);
        rows = rows + 1;
        statement.execute(" INSERT INTO " + TABLE_AGENCI + " VALUES (" + rows + ", '" + description + "', '" + address + "', 'true')");
    }

    public void deleteAgent(int kod) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE " + TABLE_AGENCI + " SET " + COLUMN_AGENCI_STATUS + " = 'FALSE' WHERE " + COLUMN_AGENCI_ID + " = " + kod);
    }

    public void editAgent(String name, String address, int kod) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE " + TABLE_AGENCI + " SET " + COLUMN_AGENCI_NAZWISKO + " = '" + name + "' WHERE " + COLUMN_AGENCI_ID + " = " + kod);
        statement.execute("UPDATE " + TABLE_AGENCI + " SET " + COLUMN_AGENCI_ADRES + " = '" + address + "' WHERE " + COLUMN_AGENCI_ID + " = " + kod);
    }

    public void sellPolicy(String date, int value, int product, int costumer, int agent) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM " + TABLE_POLISA);
        resultSet.next();
        int rows = resultSet.getInt(1);
        rows = rows + 1;
        statement.execute(" INSERT INTO " + TABLE_POLISA + " VALUES (" + rows + ", '" + date + "', " + value + ", " + product + ", " + agent + ", " + costumer + ")");
    }

    public DefaultListModel<Policy> loadPolicy(int prduct, int costumer, int agent) throws SQLException {
        DefaultListModel<Policy> list = new DefaultListModel<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_POLISA + " WHERE " + COLUMN_POLISA_PRO_UBEZ_ID + " = " + prduct + " AND " + COLUMN_POLISA_AGENCI_ID + " = " + agent + " AND " + COLUMN_POLISA_KLIENCI_ID + " = " + costumer);
        int i = 0;
        while (resultSet.next()) {
            list.add(i, new Policy(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6)));
            i++;
        }
        System.out.println("SELECT * FROM " + TABLE_POLISA + " WHERE " + COLUMN_POLISA_PRO_UBEZ_ID + " = " + prduct + " AND " + COLUMN_POLISA_AGENCI_ID + " = " + agent + " AND " + COLUMN_POLISA_KLIENCI_ID + " = " + costumer);
        return list;
    }
}
