/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feb_project;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author George
 */
public class LoginScreen {

    private String loggedUser;
    // 1 for normal user 2 for Admin user
    private int loggedUserLevel;
    private DBAccess dba = new DBAccess();

    public LoginScreen() {
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public int getLoggedUserLevel() {
        return loggedUserLevel;
    }

    public void welcomeScreen(Scanner sc) throws Exception {
        boolean correctCredentials = false;
        String tempUsername = "";
        String tempPassword = "";

        while (correctCredentials == false) {
            System.out.println("Please give Credentials");

            System.out.print("Username: ");
            tempUsername = sc.nextLine();
            System.out.print("Password: ");
            tempPassword = sc.nextLine();

            ArrayList al = new ArrayList();
            al = dba.readDataBase("select username from users where username='" + tempUsername + "'" + "AND password='" + tempPassword + "'");
            if (al.size() != 0) {
                correctCredentials = true;
            }
        }
        this.loggedUser = tempUsername;
        if (this.loggedUser.equals("admin")) {
            this.loggedUserLevel = 2;
        } else {
            this.loggedUserLevel = 1;
        }
    }
}
