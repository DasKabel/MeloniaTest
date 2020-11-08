package de.daskabelgaming.mysql.database;

import de.daskabelgaming.Main;
import de.daskabelgaming.mysql.MySQL;
import de.daskabelgaming.security.PasswordHandler;
import de.daskabelgaming.user.User;

import java.sql.PreparedStatement;

public class UpdateController {

    private final UserController userController = Main.getUserController();
    private final MySQL mySQL = Main.getMySQL();
    private final PasswordHandler passwordHandler = Main.getPasswordHandler();

    public void updateName(User user, String username) {
        String update = "UPDATE users SET username = ? WHERE ID = ?";

        try {
            PreparedStatement updater = MySQL.getConnection().prepareStatement(update);
            if(userController.isUserRegistered(user.getName())) {
                updater.setString(1, username);
                updater.setInt(2, user.getId());
                mySQL.update(updater);
                System.out.println("Benutzername geändert");
            } else {System.out.println("Benutzer exestiert nicht!");}
        }catch (Exception exception) {exception.printStackTrace();}
    }

    public void updateFirstName(User user, String firstName) {
        String update = "UPDATE userdata SET firstName = ? WHERE user_id = ?";
        try {
            PreparedStatement updater = MySQL.getConnection().prepareStatement(update);
            if(userController.isUserRegistered(user.getName())) {
                updater.setString(1, firstName);
                updater.setInt(2, user.getId());
                mySQL.update(updater);
                System.out.println("Vorname geändert");
            } else {System.out.println("Benutzer exestiert nicht!");}
        }catch (Exception exception ){exception.printStackTrace();}
    }

    public void updateLastName(User user, String lastName) {
        String update = "UPDATE userdata SET lastName = ? WHERE user_id = ?";
        try {
            PreparedStatement updater = MySQL.getConnection().prepareStatement(update);
            if(userController.isUserRegistered(user.getName())) {
                updater.setString(1, lastName);
                updater.setInt(2, user.getId());
                mySQL.update(updater);
                System.out.println("Nachname geändert");
            } else {System.out.println("Benutzer exestiert nicht!");}
        }catch (Exception exception ){exception.printStackTrace();}
    }

    public void updateGroup(User user, String group) {
        String update = "UPDATE membergroup SET user_group = ? WHERE user_id = ?";
        try {
            if(userController.isUserRegistered(user.getName())) {
                PreparedStatement updater = MySQL.getConnection().prepareStatement(update);
                updater.setString(1, group);
                updater.setInt(2, user.getId());
                mySQL.update(updater);
                System.out.println("Gruppe geändert");
            } else {System.out.println("Benutzer exestiert nicht!");}
        }catch (Exception exception) {exception.printStackTrace();}
    }

    public void updatePassword(User user, String password) {
        String update = "UPDATE pass SET pass = ? WHERE user_id = ?";
        try {
            PreparedStatement updater = MySQL.getConnection().prepareStatement(update);
            if(userController.isUserRegistered(user.getName())) {
                byte[] key = passwordHandler.generateKeyPass();
                updater.setBytes(1, passwordHandler.getEncryptedPassword(password,key));
                updater.setInt(2, user.getId());
                mySQL.update(updater);
                updateKey(user,key);
                System.out.println("Passwort geändert");
            } else {System.out.println("Benutzer exestiert nicht!");}
        }catch (Exception exception ){exception.printStackTrace();}
    }

    public void updateKey(User user,byte[] key) {
        String update = "UPDATE keypass SET keypass = ? WHERE user_id = ?";
        try {
            PreparedStatement updater = MySQL.getConnection().prepareStatement(update);
            if(userController.isUserRegistered(user.getName())) {
                updater.setBytes(1, key);
                updater.setInt(2, user.getId());
                mySQL.update(updater);
            } else {System.out.println("Benutzer exestiert nicht!");}
        }catch (Exception exception ){exception.printStackTrace();}
    }
}
