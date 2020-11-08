package de.daskabelgaming.user;

import de.daskabelgaming.Main;

public class User {
    int userId;
    String username, group,firstName,lastName;
    byte[] password,keyPass;

    public User(int userId, String username,String password, String group,String firstName,String lastName,byte[] keyPass) {
        this.userId = userId;
        this.username = username;
        this.password = Main.getPasswordHandler().getEncryptedPassword(password,keyPass);
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.keyPass = keyPass;
    }

    public User(String username, String password, String group,String firstName, String lastName, byte[] keyPass) {
        this.username = username;
        this.password = Main.getPasswordHandler().getEncryptedPassword(password,keyPass);
        this.group = group;
        this.firstName = firstName;
        this.lastName = lastName;
        this.keyPass = keyPass;
    }

    public User(String username,String password,String group,String firstName,String lastName) {
        this.username = username;
        byte[] keyPass = Main.getPasswordHandler().generateKeyPass();
        this.password = Main.getPasswordHandler().getEncryptedPassword(password, keyPass);
        this.group = group;
        this.firstName = firstName;
        this.lastName = lastName;
        this.keyPass = keyPass;
    }

    public User(int userId, String username, byte[] password, String group,String firstName, String lastName, byte[] keyPass) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.group = group;
        this.firstName = firstName;
        this.lastName = lastName;
        this.keyPass = keyPass;
    }

    public int getId() {return userId;}

    public void setName(String username) {
        this.username = username;
    }

    public String getName() {
        return username;
    }

    public void setPassword(String password) {
        this.keyPass = Main.getPasswordHandler().generateKeyPass();
        this.password = Main.getPasswordHandler().getEncryptedPassword(password,this.keyPass);
    }

    public byte[] getPassword() {
        return password;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean memberOfGroup(String group) {
        return this.group.equals(group);
    }

    public byte[] getKeyPass() {
        return keyPass;
    }

    public void setKeyPass(byte[] keyPass) {
        this.keyPass = keyPass;
    }

    public String toString() {
        return "ID: "+userId+"\nUsername: "+username+"\nGruppe: "+group;
    }


}
