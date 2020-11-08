package de.daskabelgaming.mysql.database;

import de.daskabelgaming.Main;
import de.daskabelgaming.mysql.MySQL;
import de.daskabelgaming.user.User;
import de.daskabelgaming.user.UserHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserController {

    private MySQL mySQL = Main.getMySQL();

    public boolean isUserRegistered(String username) {
        String sql = "SELECT EXISTS(SELECT ID FROM users WHERE username = ?)";
        try {

            PreparedStatement statement = MySQL.getConnection().prepareStatement(sql);
            statement.setString(1,username);
            ResultSet resultSet = mySQL.result(statement);
            resultSet.next();
            int id = resultSet.getInt(1);
            resultSet.close();
            return id != 0;

        }catch (Exception exception) {exception.printStackTrace();}
        return false;
    }

    public void registerUser(String username,String group, String firstName, String lastName, byte[] password, byte[]keyPass) {
        if(!isUserRegistered(username)) {
            String userRegister = "INSERT INTO users (username) VALUES (?)";
            String passRegister = "INSERT INTO pass (pass,user_id) VALUES (?,?)";
            String groupRegister = "INSERT INTO memberGroup (user_group,user_id) VALUES (?,?)";
            String keyPassRegister = "INSERT INTO keyPass (keyPass,user_id) VALUES (?,?)";
            String userDataRegister = "INSERT INTO userData (firstName,lastName,user_id) VALUES (?,?,?)";
            try {
                PreparedStatement userS = MySQL.getConnection().prepareStatement(userRegister);
                userS.setString(1, username);
                mySQL.update(userS);
                Thread.sleep(100);
                int id = getUserId(username);
                PreparedStatement passS = MySQL.getConnection().prepareStatement(passRegister);
                passS.setBytes(1, password);
                passS.setInt(2, id);
                PreparedStatement groupS = MySQL.getConnection().prepareStatement(groupRegister);
                groupS.setString(1, group);
                groupS.setInt(2, id);
                PreparedStatement keyPassS = MySQL.getConnection().prepareStatement(keyPassRegister);
                keyPassS.setBytes(1, keyPass);
                keyPassS.setInt(2, id);
                PreparedStatement userDataS = MySQL.getConnection().prepareStatement(userDataRegister);
                userDataS.setString(1,firstName);
                userDataS.setString(2,lastName);
                userDataS.setInt(3,id);
                mySQL.update(passS);
                mySQL.update(groupS);
                mySQL.update(keyPassS);
                mySQL.update(userDataS);

            } catch (Exception exception) {exception.printStackTrace();}
        }
    }

    public void deleteUser(User user) {
        String deleteUser = "DELETE FROM users WHERE ID = ?";
        try {
            PreparedStatement delete = MySQL.getConnection().prepareStatement(deleteUser);
            delete.setInt(1,user.getId());
            mySQL.update(delete);
        }catch (Exception exception) {exception.printStackTrace();}
    }

    public void loadUser(String username) {
        String loadUser =
                "SELECT users.ID, memberGroup.user_group, pass.pass, keyPass.keypass, userData.firstName, userData.lastName FROM users " +
                        "LEFT JOIN memberGroup " +
                        "ON users.ID = memberGroup.user_id " +
                        "LEFT JOIN pass " +
                        "ON users.ID = pass.user_id " +
                        "LEFT JOIN keyPass " +
                        "ON users.ID = keyPass.user_id " +
                        "LEFT JOIN userData " +
                        "ON users.ID = userData.user_id " +
                        "WHERE username = ?";
        try {
            PreparedStatement load = MySQL.getConnection().prepareStatement(loadUser);
            load.setString(1,username);

            ResultSet resultSet = mySQL.result(load);
            resultSet.next();
            int id = resultSet.getInt("ID");
            String group = resultSet.getString("user_group");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            byte[] pass = resultSet.getBytes("pass");
            byte[] keyPass = resultSet.getBytes("keypass");

            User newUser = new User(id, username, pass, group, firstName, lastName, keyPass);
            UserHandler.getUsers().put(username, newUser);
        }catch (Exception exception) {exception.printStackTrace();}
    }

    public void loadUsers() {
        String loadUsers =
                "SELECT users.ID, users.username, memberGroup.user_group, pass.pass, keyPass.keypass, userData.firstName, userData.lastName FROM users " +
                "LEFT JOIN memberGroup " +
                "ON users.ID = memberGroup.user_id " +
                "LEFT JOIN pass " +
                "ON users.ID = pass.user_id " +
                "LEFT JOIN keyPass " +
                "ON users.ID = keyPass.user_id " +
                "LEFT JOIN userData " +
                "ON users.ID = userData.user_id";
        try {
            PreparedStatement load = MySQL.getConnection().prepareStatement(loadUsers);
            ResultSet resultSet = mySQL.result(load);
            if(resultSet != null) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        String name = resultSet.getString("username");
                        String group = resultSet.getString("user_group");
                        String firstName = resultSet.getString("firstName");
                        String lastName = resultSet.getString("lastName");
                        byte[] pass = resultSet.getBytes("pass");
                        byte[] keyPass = resultSet.getBytes("keypass");
                        User newUser = new User(id, name, pass, group, firstName, lastName, keyPass);
                        UserHandler.getUsers().put(name, newUser);
                }
            }
        }catch (Exception exception) {exception.printStackTrace();}
    }

    public int getUserId(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {

            PreparedStatement statement = MySQL.getConnection().prepareStatement(sql);
            statement.setString(1,username);
            ResultSet resultSet = mySQL.result(statement);
            resultSet.next();
            int id = resultSet.getInt(1);
            resultSet.close();
            return id;

        }catch (Exception exception) {exception.printStackTrace();}
        return 0;
    }
}
