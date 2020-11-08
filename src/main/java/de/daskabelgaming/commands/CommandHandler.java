package de.daskabelgaming.commands;

import de.daskabelgaming.Main;
import de.daskabelgaming.mysql.database.TimeController;
import de.daskabelgaming.security.LoginHandler;
import de.daskabelgaming.time.TimeHandler;
import de.daskabelgaming.time.TimeManager;
import de.daskabelgaming.user.User;
import de.daskabelgaming.user.UserHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CommandHandler {

    String[] command;
    String commandName;
    String lable;
    String option;


    public CommandHandler(String placeholder) {
        while (LoginHandler.getLoggedIn()) {
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine();
            command = cmd.split(" ");
            if(Main.getThisUser().memberOfGroup("admin")) {

            }
        }
    }

    public CommandHandler() {
        while (LoginHandler.getLoggedIn()) {
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine();
            command = cmd.split(" ");
            commandName = command[0];
            if (Main.getThisUser().memberOfGroup("admin")) {
                adminHandler();
            } else {
                userHandler();
            }
            System.out.print("> ");
        }
    }

    public void adminHandler() {
        switch (commandName) {
            case "help":
                new HelpCommand().getHelp(Main.getThisUser().getGroup());
                break;
            case "user":
                if(command.length > 1) {
                    lable = command[1];
                    adminUser();
                } else new HelpCommand().getUserHelp();
                break;
            case "times":
                if(command.length > 1) {
                    lable = command[1];
                    adminTime();
                } else new HelpCommand().getTimeHelp();
                break;
            case "logout":
                LoginHandler.setLoggedIn(false);
                LoginHandler.logIn();
                break;
            case "exit":
                System.out.println("Programm würde beendet werden");
                break;
            default:
                System.out.println("Unbekanter Befehlt");
                break;
        }
    }

    public void userHandler() {
        switch (commandName) {
            case "help":
                new HelpCommand().getHelp(Main.getThisUser().getGroup());
                break;
            case "edit":
                if(command.length == 3) {
                    lable = command[1];
                    userEdit();
                }else new HelpCommand().getEditHelp();
                break;
            case "times":
                userTime();
                break;
            case "logout":
                LoginHandler.setLoggedIn(false);
                LoginHandler.logIn();
                break;
            case "exit":
                System.out.println("Programm würde beendet werden");
                break;
        }
    }

    public void adminUser() {
        switch (lable) {
            case "register":
                new RegisterCommand();
                break;
            case "delete":
                if(command.length == 3) {
                    option = command[2];
                    new DeleteCommand(option);
                } else {
                    System.out.println("Zu wenig Argumente");
                }
                break;
            case "edit":
                if (command.length == 5) {
                    String user, option, value;
                    user = command[2];
                    option = command[3].toLowerCase();
                    value = command[4];
                    new EditUserCommand(user, option, value);
                } else {
                    System.out.println("Zu wenig Argumente");
                }
                break;
            default:
                break;
        }
    }

    public void adminTime() {
        if(command.length == 2) {
            String user = command[1];
            new TimesCommand(user);
        } else if(command.length == 3) {
            String user = command[1];
            int month = Integer.parseInt(command[2]);
            new TimesCommand(user,month);
        } else {
            System.out.println("Dieser Befehl exestiert nicht");
        }
    }

    public void userEdit() {
        lable = command[1];
        option = command[2];
        switch (lable.toLowerCase()) {
            case "password":
                Main.getThisUser().setPassword(option);
                System.out.println("Passwort wurde erfolgreich geändert");
                break;
            case "firstname":
                Main.getThisUser().setFirstName(option);
                System.out.println("Vorname wurde erfolgreich zu "+option+" geändert");
                break;
            case "lastname":
                Main.getThisUser().setLastName(option);
                System.out.println("Nachname wurde erfolgreich zu "+option+" geändert");
                break;
            default:
                System.out.println("Dieser Befehl exestiert nicht");
                break;
        }
    }

    public void userTime() {
        if(command.length > 1) {
            lable = command[1];
            if (lable.toLowerCase().equals("get")) {
                if (command.length == 2) {
                    new TimesCommand(Main.getThisUser().getName());

                } else if (command.length == 3) {
                    lable = command[1];
                    int value = Integer.parseInt(command[2]);
                    new TimesCommand(Main.getThisUser().getName(), value);
                } else {
                    System.out.println("Zu wenige Argumente");
                }
            } else if (lable.toLowerCase().equals("set")) {
                if (command.length == 4) {
                    Calendar start = Calendar.getInstance();
                    Calendar stop = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    try {
                        start.setTime(sdf.parse(command[2]));
                        stop.setTime(sdf.parse(command[3]));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    new TimeManager(Main.getThisUser(), start, stop);
                }
            } else new HelpCommand().getTimeUserHelp();
        } else new HelpCommand().getTimeUserHelp();
    }
}
