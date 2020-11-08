package de.daskabelgaming.commands;

public class HelpCommand {

    public void getHelp(String group) {
        if(group.equals("admin")) {
            System.out.println( "[] - Benötigt | <> - Optional\n"+
                                "help -> Zeigt diese Hilfe \n" +
                                "user register -> Startet die Benutzer regestrierung\n"+
                                "user edit [username/firstname/lastname] [Benutzername] [Wert] Bsp.: user edit lastname Mustermann\n" +
                                "user edit [password/group] [Benutzername] [Wert] Bsp.: user edit group user admin\n"+
                                "user delete [Benutzername] Bsp.: user delete user\n" +
                                "times [Benuzername] <Monatszahl> Bsp.: times user 11");
        } else {
            System.out.println(   "[] - Benötigt | <> - Optional\n"+
                                "help -> Zeigt diese Hilfe \n" +
                                "edit [firstname/lastname/password] [Wert] Bsp.: edit password 123456\n" +
                                "times get <Monatszahl> Bsp.: times get 11\n" +
                                "times set [Start Uhrzeit] [End UhrZeit] Bsp.: time set 08:00 17:30");
        }
    }

    public void getEditHelp() {
        System.out.println( "edit [firstname/lastname/password] [Wert]\n");
    }

    public void getUserHelp() {
        System.out.println( "user register\n" +
                            "user edit [username/firstname/lastname] [Benutzername] [Wert]\n" +
                            "user edit [group/password] [Benutzername] [Wert]\n" +
                            "user delete [Benutzername]");
    }

    public void getTimeHelp() {
        System.out.println( "times [Benuzername]\n" +
                            "times [Benuzername] <Monatszahl>");
    }

    public void getTimeUserHelp() {
        System.out.println("times set [Start Uhrzeit] [End Uhrzeit]");
        System.out.println("times get");
        System.out.println("times get <Monatszahl> ");
    }
}
