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
    private int userID;
    private DBAccess dba = new DBAccess();

    public LoginScreen() {
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
        int retries = 0;
        String tempUsername = "";
        String tempPassword = "";
        // Repeat until correct credentials
        while (correctCredentials == false && retries < 3) {
            retries++;
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
            al = dba.credentialsCheck(tempUsername, tempPassword);
            if (al.size() == 1) {
                correctCredentials = true;
                this.userID = Integer.parseInt(al.get(0).toString());
                this.loggedUser = tempUsername;
            }
        }

        if (retries == 3) {
            System.out.println("Maximum number of login attempts reached Program Aborting");
            System.exit(0);
        }

        if (this.loggedUser.equals("admin")) {
            this.loggedUserLevel = 2;
        } else {
            this.loggedUserLevel = 1;
        }
        ArrayList al;
        al = dba.readDataBase("select amount from accounts  where user_id='" + this.userID + "';", 1);

        double amount = Double.parseDouble(al.get(0).toString());
        if (this.loggedUserLevel == 2) {
            AdminUser adminUser = new AdminUser(this.userID, this.loggedUser, this.loggedUserLevel, amount);
            AppMenu am = new AppMenu(adminUser);
            am.selectMenu(sc);
        } else {
            SimpleUser simpleUser = new SimpleUser(this.userID, this.loggedUser, this.loggedUserLevel, amount);
            AppMenu am = new AppMenu(simpleUser);
            am.selectMenu(sc);
        }
    }
}
