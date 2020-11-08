package de.daskabelgaming.commands;

import de.daskabelgaming.user.User;
import de.daskabelgaming.user.UserHandler;

import java.io.Console;
import java.util.Scanner;

public class RegisterCommand implements Runnable{

    String username,password,group,firstName,lastName;
    Scanner scanner;
    boolean onCommand = false;

    public RegisterCommand() {
        onCommand = true;
        run();
    }

    @Override
    public void run() {
        int state = 0;
        System.out.print("Trage im folgenden alle Daten des Benutzers ein\n");

        while (onCommand) {
            switch (state) {

                case 0: //Benutzernamen Abfragen

                    System.out.print("\tBenutzername: ");
                    scanner = new Scanner(System.in);
                    username = scanner.nextLine();

                    if (!UserHandler.isUserRegistered(username)) {
                        state++;
                    } else {System.out.println("Dieser Benutzername exestiert bereits");}
                    break;

                case 1: //Passwort Abfragen
                    System.out.print("\tPasswort: ");
                    scanner = new Scanner(System.in);
                    password = scanner.nextLine();

                    if (password.length() >= 5 || password.equals(username)) {
                        state++;
                    }else {System.out.println("\tPasswort ist nicht Sicher genug!");}
                    break;

                case 2: //Gruppe Abfragen
                    System.out.print("\tGruppe: ");
                    scanner = new Scanner(System.in);
                    group = scanner.nextLine();

                    if (group.equals("admin") || group.equals("user")) {
                        state++;
                    } else {System.out.println("\tDiese Gruppe existiert nicht");}
                    break;

                case 3: //Vorname Abfragen
                    System.out.print("\tVorname: ");
                    scanner = new Scanner(System.in);
                    firstName = scanner.nextLine();

                    if(firstName.length() >= 3) {
                        state++;
                    }else {System.out.println("\tVorname zu kurz");}
                    break;

                case 4: //Nachname Abfragen
                    System.out.print("\tNachname: ");
                    scanner = new Scanner(System.in);
                    lastName = scanner.nextLine();

                    if(lastName.length() >= 3) {
                        state++;
                    }else {System.out.println("\tNachname zu kurz");}
                    break;

                case 5: //Benutzer regestrieren
                    User user = new User(username,password,group,firstName,lastName);
                    UserHandler.registerUser(user);
                    state++;
                    break;

                default:
                    onCommand = false;
                    state = 0;
                    break;
            }
        }
    }
}
