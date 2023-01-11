package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class JDBC {

    private static final String url = "jdbc:sqlite:card.s3db";
    public static Connection connection;

    public static void openConnection() {
        try{
            SQLiteDataSource dataSource = new SQLiteDataSource();
            dataSource.setUrl(url);
            connection = dataSource.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeConnection(){
        try{
            connection.close();

        }catch(Exception e){
            System.out.println("Failed " + e.getMessage());
        }
    }

}
