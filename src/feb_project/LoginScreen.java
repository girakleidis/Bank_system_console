/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feb_project;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author George
 */
public class LoginScreen {

    private String loggedUser;
    // 1 for normal user 2 for Admin user
    private int loggedUserLevel;
    private BankAccount ba;
    private int userID;
    private DBAccess dba = new DBAccess();

    public LoginScreen() {
    }

    public BankAccount getBa() {
        return ba;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public int getLoggedUserLevel() {
        return loggedUserLevel;
    }

    public int getUserID() {
        return userID;
    }

    public void welcomeScreen(Scanner sc) throws Exception {

        boolean correctCredentials = false;
        String tempUsername = "";
        String tempPassword = "";
        // Eπανάληψη μέχρι να δώσει σωστά credentials ο χρήστης
        while (correctCredentials == false) {
            System.out.println("Please give Credentials");

            System.out.print("Username: ");
            tempUsername = sc.nextLine();
            System.out.print("Password: ");
            tempPassword = sc.nextLine();
            // For CMD only
//            Console console = System.console();
//            char[] password = console.readPassword("Password: ");
//            tempPassword = String.valueOf(password);

            ArrayList al = new ArrayList();
            al = dba.readDataBase("select id from users where username='" + tempUsername + "'" + "AND password='" + tempPassword + "'", 1);
            if (al.size() != 0) {
                correctCredentials = true;
                this.userID = Integer.parseInt(al.get(0).toString());
            }
        }
        this.loggedUser = tempUsername;
        if (this.loggedUser.equals("admin")) {
            this.loggedUserLevel = 2;
        } else {
            this.loggedUserLevel = 1;
        }
        ArrayList al = new ArrayList();
        al = dba.readDataBase("select amount from accounts  where user_id='" + this.userID + "';", 1);

        double amount = Double.parseDouble(al.get(0).toString());
        ba = new BankAccount(amount, this.loggedUser, this.userID);
    }
}
