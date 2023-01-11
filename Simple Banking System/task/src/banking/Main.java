package banking;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        CardQuery.createTableCard();

        Scanner scanner = new Scanner(System.in);
        AccountServices initialize = new AccountServices(scanner);

        initialize.mainMenu();

        JDBC.closeConnection();
    }

}