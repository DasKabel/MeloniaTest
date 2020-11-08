package de.daskabelgaming.mysql.database;

import de.daskabelgaming.Main;
import de.daskabelgaming.mysql.MySQL;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;

public class TableController {

    MySQL mySQL = Main.getMySQL();

    public TableController() {
        try {
            createUser();
            createGroup();
            createPass();
            createKey();
            createTime();
            createData();

        }catch (Exception exception) {exception.printStackTrace();}
    }

    private void createUser() throws SQLException {
        String userTable =  "CREATE TABLE IF NOT EXISTS users (" +
                            "ID INT(6) UNIQUE AUTO_INCREMENT PRIMARY KEY, " +
                            "username VARCHAR(30) NOT NULL)";
        PreparedStatement user = MySQL.getConnection().prepareStatement(userTable);
        mySQL.update(user);
    }

    private void createPass() throws SQLException {
        String passTable =  "CREATE TABLE IF NOT EXISTS pass (" +
                            "user_id INT(6) UNIQUE NOT NULL, " +
                            "pass VARBINARY(160) NOT NULL, " +
                            "CONSTRAINT fp_user " +
                            "FOREIGN KEY (user_id) " +
                            "REFERENCES users(ID) ON DELETE CASCADE)";
        PreparedStatement pass = MySQL.getConnection().prepareStatement(passTable);
        mySQL.update(pass);
    }

    private void createGroup() throws SQLException {
        String passTable =  "CREATE TABLE IF NOT EXISTS memberGroup (" +
                "user_id INT(6) UNIQUE NOT NULL, " +
                "user_group VARBINARY(160) NOT NULL, " +
                "CONSTRAINT fg_user " +
                "FOREIGN KEY (user_id) " +
                "REFERENCES users(ID) ON DELETE CASCADE)";
        PreparedStatement pass = MySQL.getConnection().prepareStatement(passTable);
        mySQL.update(pass);
    }

    private void createKey() throws SQLException {
        String keypassTable =   "CREATE TABLE IF NOT EXISTS keypass " +
                                "(user_id INT(6) UNIQUE NOT NULL, " +
                                "keypass VARBINARY(30) NOT NULL, " +
                                "CONSTRAINT fs_users " +
                                "FOREIGN KEY (user_id) " +
                                "REFERENCES users(ID) ON DELETE CASCADE)";
        PreparedStatement key = MySQL.getConnection().prepareStatement(keypassTable);
        mySQL.update(key);
    }

    private void createTime() throws SQLException {
        String timeTable =  "CREATE TABLE IF NOT EXISTS time " +
                            "(user_id INT(6) NOT NULL, " +
                            "date DATE NOT NULL, " +
                            "start_time TIME NOT NULL, " +
                            "end_time TIME NOT NULL, " +
                            "working_hours BIGINT(16) NOT NULL," +
                            "CONSTRAINT ft_users " +
                            "FOREIGN KEY (user_id) " +
                            "REFERENCES users(ID) ON DELETE CASCADE)";
        PreparedStatement time = MySQL.getConnection().prepareStatement(timeTable);
        mySQL.update(time);
    }

    private void createData() throws SQLException{
        String userDataTable =  "CREATE TABLE IF NOT EXISTS userData " +
                                "(user_id INT(6) UNIQUE NOT NULL, " +
                                "firstName VARCHAR(30), " +
                                "lastName VARCHAR(30), " +
                                "CONSTRAINT  ud_users " +
                                "FOREIGN KEY (user_id) " +
                                "REFERENCES users(ID) ON DELETE CASCADE)";
        PreparedStatement date = MySQL.getConnection().prepareStatement(userDataTable);
        mySQL.update(date);
    }
}
