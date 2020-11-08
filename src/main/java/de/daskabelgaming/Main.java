package de.daskabelgaming;

import de.daskabelgaming.commands.CommandHandler;
import de.daskabelgaming.mysql.MySQL;
import de.daskabelgaming.mysql.database.TableController;
import de.daskabelgaming.mysql.database.TimeController;
import de.daskabelgaming.mysql.database.UpdateController;
import de.daskabelgaming.mysql.database.UserController;
import de.daskabelgaming.security.LoginHandler;
import de.daskabelgaming.security.PasswordHandler;
import de.daskabelgaming.time.AutoTime;
import de.daskabelgaming.user.User;
import de.daskabelgaming.user.UserHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {

    private static MySQL mySQL;
    private static PasswordHandler passwordHandler;
    private static UserController userController;
    private static TimeController timeController;
    private static UpdateController updateController;
    public static User thisUser;

    public static void main(String[] args) throws Exception {
        System.out.println("Programm wird geladen");
        init();
        LoginHandler.logIn();
        new CommandHandler();
    }

    private static void init() throws Exception {
        mySQL = new MySQL();
        Thread.sleep(50);
        new TableController();
        userController = new UserController();
        timeController = new TimeController();
        passwordHandler = new PasswordHandler();
        updateController = new UpdateController();
        Thread.sleep(210L);
        System.out.println("Bitte warten");
        UserHandler.loadUsers();
        Thread.sleep(100L);
        User newAdmin = new User("admin","!admin","admin","admin","admin");
        UserHandler.registerUser(newAdmin);
        User admin = UserHandler.getUser("admin");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar start = Calendar.getInstance();
        start.setTime(sdf.parse("08:05"));
        Calendar stop = Calendar.getInstance();
        stop.setTime(sdf.parse("17:00"));
        new AutoTime();
    }

    public static MySQL getMySQL() {
        return mySQL;
    }

    public static PasswordHandler getPasswordHandler() {
        return passwordHandler;
    }

    public static User getThisUser() {
        return thisUser;
    }

    public static UserController getUserController() {return userController;}

    public static TimeController getTimeController() {return timeController;}

    public static UpdateController getUpdateController() {return updateController;}
}
