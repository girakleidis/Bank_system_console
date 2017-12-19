/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feb_project;

/**
 *
 * @author George
 */
public class AppMenu {

    private LoginScreen ls;

    public AppMenu(LoginScreen ls) {
        this.ls = ls;
    }

    /**
     *
     */
    public void showMenu() {
        if (ls.getLoggedUserLevel() == 1) {
            System.out.println("Choose Action");
            System.out.println("1.View your bank account");
            System.out.println("2.Deposit to Cooperative’s internal bank account");
            System.out.println("3.Deposit to another Member’s bank account");
            System.out.println("4.Send to file today’s transactions and log out");
        } else if (ls.getLoggedUserLevel() == 2) {
            System.out.println("Choose Action");
            System.out.println("1.View Cooperative’s internal bank account");
            System.out.println("2.View your bank account");
            System.out.println("3.Deposit to your bank account");
            System.out.println("4.Withdraw from Member’s bank account");
            System.out.println("5.Send to file today’s transactions and log out");
        }
    }
}
