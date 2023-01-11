package banking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class CardQuery {

    public static void createTableCard() throws SQLException {
        Statement statement = JDBC.connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number TEXT," +
                "pin TEXT," +
                "balance INTEGER DEFAULT 0)");
    }
    public static void insertIntoCard(String accountNumber, String pin) {

        try {
            String insert = "INSERT INTO card (number, pin) VALUES(?,?)";
            PreparedStatement insertStatement = JDBC.connection.prepareStatement(insert);
            insertStatement.setString(1, accountNumber);
            insertStatement.setString(2,pin);

            insertStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkForAccount(String accountNumber, String pin) {

        boolean foundAccount = false;

        try {
            String sql = "SELECT * FROM card WHERE number = ? AND pin = ?";
            PreparedStatement selectStatement = JDBC.connection.prepareStatement(sql);
            selectStatement.setString(1, accountNumber);
            selectStatement.setString(2, pin);
            ResultSet rs = selectStatement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String pinNumber = rs.getString("pin");
                int balance = rs.getInt("balance");
                // System.out.println(id + " " + number + " " + pinNumber + " " + balance);
                foundAccount = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundAccount;

    }
    public static int getAccountBalance(String accountNumber,String pin) {
        int balance = 0;
        try {
            String sql = "SELECT balance FROM card WHERE number = ? AND pin = ?";
            PreparedStatement selectStatement = JDBC.connection.prepareStatement(sql);
            selectStatement.setString(1, accountNumber);
            selectStatement.setString(2, pin);
            ResultSet rs = selectStatement.executeQuery();

            while(rs.next()) {
                balance = rs.getInt("balance");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }
    public static void addToAccountBalance(String accountNumber, int income) {

        try {
            String sql = "UPDATE card SET balance = balance + ? WHERE number = ?";
            PreparedStatement updateStatement = JDBC.connection.prepareStatement(sql);
            updateStatement.setInt(1, income);
            updateStatement.setString(2, accountNumber);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deductAccountBalance(String accountNumber, int income) {
        try {
            String sql = "UPDATE card SET balance = balance - ? WHERE number = ?";
            PreparedStatement updateStatement = JDBC.connection.prepareStatement(sql);
            updateStatement.setInt(1, income);
            updateStatement.setString(2, accountNumber);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAccount(String accountNumber) {

        try {
            String sql = "DELETE FROM card WHERE number = ?";
            PreparedStatement deleteStatement = JDBC.connection.prepareStatement(sql);
            deleteStatement.setString(1, accountNumber);
            deleteStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean accountLookup(String accountNumber) {

        boolean foundAccount = false;

        try {
            String sql = "SELECT * FROM card WHERE number = ?";
            PreparedStatement selectStatement = JDBC.connection.prepareStatement(sql);
            selectStatement.setString(1, accountNumber);
            ResultSet rs = selectStatement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String pinNumber = rs.getString("pin");
                int balance = rs.getInt("balance");
                // System.out.println(id + " " + number + " " + pinNumber + " " + balance);
                foundAccount = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundAccount;

    }
}
