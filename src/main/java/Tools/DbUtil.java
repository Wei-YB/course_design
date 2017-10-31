package main.java.Tools;

import java.sql.*;

public class DbUtil {

    private Connection conn = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

//    private static String DBDriver = "com.mysql.jdbc.Driver";
//    private static String DBUrl = "jdbc:mysql://138.197.217.138:3306/";
    private static String DBDriver = "org.mariadb.jdbc.Driver";
    private static String DBUrl = "jdbc:mariadb://localhost:3306/";
    private static String DBName = "course_design";
    private static String DBUser = "root";
    private static String DBPassword = "jsyishan";

    private static DbUtil instance = null;

    private DbUtil() {
        try {
            Class.forName(DBDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Instance
     * Thread Security
     */
    public static synchronized DbUtil getInstance() {

        if(instance == null) {
            instance = new DbUtil();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        try {
            conn = DriverManager.getConnection(DBUrl + DBName, DBUser, DBPassword);
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public synchronized void closeConnection() throws SQLException{
        if (resultSet != null) {
            resultSet.close();
            resultSet = null;
        }
        if (statement != null) {
            statement.close();
            statement = null;
        }
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    public synchronized ResultSet execQuery(String sql) throws SQLException {
        getStatement();
        if(resultSet != null && !resultSet.isClosed()) {
            resultSet.close();
        }
        resultSet = null;
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public synchronized int execUpdate(String sql) throws SQLException {
        int result = 0;
        getStatement();
        result = statement.executeUpdate(sql);
        return result;
    }

    private synchronized void getStatement() throws SQLException {
        getConnection();
        if (statement == null || statement.isClosed()) {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
    }

}
