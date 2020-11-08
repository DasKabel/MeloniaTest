package de.daskabelgaming.commands;

import de.daskabelgaming.Main;
import de.daskabelgaming.mysql.database.UserController;
import de.daskabelgaming.user.User;
import de.daskabelgaming.user.UserHandler;

import java.util.Scanner;

public class DeleteCommand implements Runnable{

    private final UserController userController = Main.getUserController();
    private final User deleteUser;
    String username;

    public DeleteCommand(String username) {
        this.username = username;
        deleteUser = UserHandler.getUser(username);
        run();
    }

    @Override
    public void run() {
        System.out.println("Bist du dir sicher ? [j/n]");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.next();
        if(confirmation.equals("j") || confirmation.equals("y")) {
            userController.deleteUser(deleteUser);
            System.out.println(username+" wurde erfolgreich gelöscht!");
        } else {
            System.out.println("Löschen wurde abgebrochen");
        }
    }
}
