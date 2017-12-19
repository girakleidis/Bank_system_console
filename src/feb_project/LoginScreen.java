/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feb_project;

import java.util.Scanner;

/**
 *
 * @author George
 */
public class LoginScreen {

    private String loggedUser;
    private int loggedUserLevel;
    private DBAccess dba;

    public void enterCredentials(Scanner sc) {

    }

    public LoginScreen(int loggedUserLevel) {
        this.loggedUserLevel = loggedUserLevel;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public int getLoggedUserLevel() {
        return loggedUserLevel;
    }

}
