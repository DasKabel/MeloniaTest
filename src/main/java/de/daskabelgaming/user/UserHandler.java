package de.daskabelgaming.user;

import de.daskabelgaming.mysql.database.UserController;

import java.util.HashMap;

public class UserHandler {

    private static final HashMap<String, User> USERS = new HashMap<>();
    private static final UserController userController = new UserController();

     public static boolean isUserRegistered(String username) {
        return USERS.containsKey(username);
    }

    public static void registerUser(User user) {
        if(!isUserRegistered(user.username)) {
            int userId = user.userId;
            String userName = user.username;
            String group = user.group;
            String firstName = user.firstName;
            String lastName = user.lastName;
            byte[] password = user.password;
            byte[] keyPass = user.keyPass;
            userController.registerUser(userName, group, firstName, lastName, password, keyPass);
            loadUser(user.username);
            User registered = UserHandler.getUser(user.username);
            if(registered.getId() != 0) {
                System.out.println("Benutzer erfolgreich regestriert");
            } else {
                System.out.println("Es ist ein Fehler aufgetreten");
            }
        }
    }

    public static User getUser(String name) {
        return USERS.get(name);
    }

    public static void updateUser(User user) {

    }

    public static void updatePassword(User user) {

    }

    public static void updateKeyPass(User user) {

    }

    public static HashMap<String, User> getUsers() {
        return USERS;
    }

    public static void loadUsers() {
        userController.loadUsers();
    }

    public static void loadUser(String name) {
         userController.loadUser(name);
    }
}
