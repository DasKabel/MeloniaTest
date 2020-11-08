package de.daskabelgaming.security;

import de.daskabelgaming.Main;
import de.daskabelgaming.user.User;
import de.daskabelgaming.user.UserHandler;

import java.util.Scanner;

public class LoginHandler {

    private static boolean loggedIn = false;
    private static final PasswordHandler passwordHandler = Main.getPasswordHandler();

    public static void logIn() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Bitte melde dich mit deinem Benutzernamen und deinem Password an!");
        while (!loggedIn) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Benutername: ");
            String username = scanner.nextLine();
            if(UserHandler.isUserRegistered(username)) {
                User user = UserHandler.getUser(username);
                Main.thisUser = user;
                System.out.print("Password: ");
                String password = scanner.nextLine();
                if(passwordHandler.authenticate(password, user.getPassword(), user.getKeyPass())) {
                    loggedIn = true;
                    if(loggedIn = true) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println( "Du hast dich erfolgreich angemeldet\n" +
                                            "help fuer eine Uebersicht");
                        System.out.print("Wilkommen "+user.getName()+"\n> ");
                    }
                } else {
                    System.out.println("Benutzername oder Passwort falsch");
                }
            } else {
                System.out.println("Benutzername oder Passwort falsch");
            }
        }
    }

    public static boolean getLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        LoginHandler.loggedIn = loggedIn;
    }


}
