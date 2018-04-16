package ua.od.hillel.model.helpers;

import java.sql.*;

/**
 * Created by ruslangramatic on 4/14/18.
 */
public abstract class QueryHelper {
    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String MYSQL_URL = "jdbc:mysql://localhost/?user=root&password=root";
    public static final String MYSQL_DATABASE_URL = "jdbc:mysql://localhost/card_battle_rts?user=root&password=";

    static {
        try {
            Class.forName(MYSQL_DRIVER);// This will load the MySQL driver, each DB has its own driver
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    protected abstract void executeQuery(Statement statement) throws SQLException;

    public void run() {
        Connection connection = null;
        try {
            // create a database connection or setup the connection with the DB
            connection = DriverManager.getConnection(MYSQL_URL);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            executeQuery(statement);
        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if(connection != null)
                    connection.close();
            } catch(SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}
