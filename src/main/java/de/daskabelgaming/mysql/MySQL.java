package de.daskabelgaming.mysql;

import java.sql.*;

public class MySQL {

    public static String    host = "localhost",
                            user = "melonia",
                            password = "melonia!",
                            database = "melonia",
                            port = "3306";
    private static Connection connection;
    private static RequestQueue requestQ;
    private static ResultQueue resultQueue;

    public MySQL() {
        requestQ = new RequestQueue();
        requestQ.setRunning(true);
        resultQueue = new ResultQueue();
        resultQueue.setRunning(true);
        connect();

    }

    public static Connection getConnection() {
        return connection;
    }

    public void connect() {
        if (connection == null)
            try {
                System.out.print("Connecting to Database..");
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
                System.out.println("Connected!");
            } catch (SQLException exception) {exception.printStackTrace();}
    }

    public void close()
    {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException exception) {exception.printStackTrace();}
    }

    public void update(PreparedStatement qry)
    {
        requestQ.addToQueue(qry);
    }

    public ResultSet result(PreparedStatement statement) {
            return resultQueue.getResult(statement);
    }
}
