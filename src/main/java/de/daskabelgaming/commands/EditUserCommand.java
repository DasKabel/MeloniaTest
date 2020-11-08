package de.daskabelgaming.commands;

import de.daskabelgaming.Main;
import de.daskabelgaming.mysql.database.UpdateController;
import de.daskabelgaming.user.User;
import de.daskabelgaming.user.UserHandler;

public class EditUserCommand {

    public EditUserCommand(String name,String option, String value) {
        User user = UserHandler.getUser(name);
        UpdateController updateController = Main.getUpdateController();
        switch (option) {
            case "username":
                updateController.updateName(user,value);
                break;
            case "firstname":
                updateController.updateFirstName(user,value);
                break;
            case "lastname":
                updateController.updateLastName(user,value);
                break;
            case "password":
                updateController.updatePassword(user,value);
                break;
            case "group":
                updateController.updateGroup(user,value);
                break;
            default:
                System.out.println("Unbekannter Wert "+option);
                System.out.println("Mögliche Werte: username,firstname,lastname,password,group");
                break;
        }
    }
}
