/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feb_project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author George
 */
public class AppMenu {

    // private LoginScreen ls;
    private AdminUser adminUser;
    private SimpleUser simpleUser;
    private ArrayList<String> arrayForFile;
    private FileAccess fa;
    //private BankAccount ba;
    //private BankAccountCorporate bac;
    DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");

    public AppMenu(SimpleUser user) {
        // this.ls = ls;
        this.simpleUser = user;
        this.arrayForFile = new ArrayList<String>();
    }

    public AppMenu(AdminUser user) {
        // this.ls = ls;
        this.adminUser = user;
        this.arrayForFile = new ArrayList<String>();
    }

    DBAccess db = new DBAccess();

    /**
     *
     */
    public void showMenu() {

        if (simpleUser != null) {
            System.out.println("");
            System.out.println("Simple User Menu Choose Action");
            System.out.println("1.View your bank account");
            System.out.println("2.Deposit to Cooperative's internal bank account");
            System.out.println("3.Deposit to another Members bank account");
            System.out.println("4.Send to file today’s transactions and log out");
        } else {
            System.out.println("");
            System.out.println("Admin Menu Choose Action");
            System.out.println("1.View Cooperative's internal bank account");
            System.out.println("2.View bank account of other members");
            System.out.println("3.Deposit to other members bank account");
            System.out.println("4.Withdraw from Member's bank account");
            System.out.println("5.Send to file today's transactions and log out");
        }
    }

    public int validateIntInput(Scanner sc, int min, int max) {
        boolean correctInput = false;
        int i = 0;
        while (correctInput == false) {
            System.out.println("Input a number from " + "\u001B[31m" + min + "\u001B[0m" + " to " + "\u001B[31m" + max + "\u001B[0m");
            if (!sc.hasNextInt()) {
                sc.next();
                continue;
            }
            i = sc.nextInt();
            if (i <= max && i >= min) {
                correctInput = true;
            }
        }
        return i;
    }

    public double validateDoubleInput(Scanner sc, double min, double max) {
        boolean correctInput = false;
        double i = 0.0;
        while (correctInput == false) {
            System.out.println("Input a number from " + min + " to " + max);
            if (!sc.hasNextDouble()) {
                sc.next();
                continue;
            }
            i = sc.nextDouble();
            if (i <= max && i >= min) {
                correctInput = true;
            }
        }
        return i;
    }

    public void selectMenu(Scanner sc) throws Exception {
        boolean repeatMenu = true;
        while (repeatMenu == true) {
            showMenu();

            if (simpleUser != null) {
                int choice = validateIntInput(sc, 1, 4);

                switch (choice) {
                    case 1:
                        System.out.println("The balance of your account is: " + simpleUser.getBa().getBallance());
                        arrayForFile.add("Balance request " + dateFormat.format(new Date()));
                        break;
                    case 2:
                        if (simpleUser.getBa().getBallance() > 0) {
                            System.out.println("Please input amount to transfer");
                            double transferAmountChoice = validateDoubleInput(sc, 0.01, simpleUser.getBa().getBallance());

                            ArrayList al2 = db.readDataBase("select id from users where username ='admin';", 1);
                            int adminId = Integer.parseInt(al2.get(0).toString());
                            simpleUser.getBa().transferToAccount(transferAmountChoice, adminId);

                            System.out.println("Transfered to Cooperative's bank account " + transferAmountChoice + " €");
                            arrayForFile.add("Transfer to Cooperative’s bank account " + transferAmountChoice + " € at " + dateFormat.format(new Date()));
                        } else {
                            System.out.println("Not enough");
                            arrayForFile.add("Not enough for transfer " + dateFormat.format(new Date()));
                        }
                        break;
                    case 3:
                        if (simpleUser.getBa().getBallance() > 0) {
                            ArrayList<ArrayList> al2 = db.readDataBase("select username, u.id from users as u inner join accounts as a on u.id=a.user_id "
                                    + "where username <>'admin' and u.id <>'" + simpleUser.getId() + "';", 2);
                            for (int j = 0; j < al2.size(); j++) {
                                System.out.println(j + 1 + " " + al2.get(j).get(0));
                            }

                            int choiceUser = validateIntInput(sc, 1, al2.size());
                            System.out.println("Please input amount to transfer");
                            double transferAmountChoice = validateDoubleInput(sc, 0.01, simpleUser.getBa().getBallance());
                            int id = Integer.parseInt(al2.get(choiceUser - 1).get(1).toString());
                            simpleUser.getBa().transferToAccount(transferAmountChoice, id);

                            System.out.println("Transfered to " + al2.get(choiceUser - 1).get(0).toString()
                                    + "'s bank account " + transferAmountChoice + " €");
                            arrayForFile.add("Transfer to " + al2.get(choiceUser - 1).get(0).toString()
                                    + "'s bank account " + transferAmountChoice + " € " + dateFormat.format(new Date()));
                        } else {
                            System.out.println("Not enough");
                            arrayForFile.add("Not enough for transfer " + dateFormat.format(new Date()));
                        }
                        break;
                    case 4:
                        repeatMenu = false;
                        fa = new FileAccess(simpleUser, arrayForFile);
                        break;
                    default:
                }
            } else {
                int choice = validateIntInput(sc, 1, 5);

                switch (choice) {
                    case 1:
                        System.out.println("The balance of your account is: " + adminUser.getBa().getBallance());
                        arrayForFile.add("Βalance request for self bank account " + dateFormat.format(new Date()));
                        break;

                    case 2:
                        ArrayList<ArrayList> al = db.readDataBase("select username, u.id from users as u inner join accounts as a on u.id=a.user_id where u.id <>'" + adminUser.getId() + "';", 2);
                        for (int j = 0; j < al.size(); j++) {
                            System.out.println(j + 1 + " " + al.get(j).get(0));
                        }
                        int choiceUser = validateIntInput(sc, 1, al.size());
                        int id = Integer.parseInt(al.get(choiceUser - 1).get(1).toString());
                        double amount = adminUser.getBa().showBalance(id);

                        System.out.println("The balance of user " + al.get(choiceUser - 1).get(0) + " is: " + amount);
                        arrayForFile.add("Ballance request for " + al.get(choiceUser - 1).get(0) + "'s bank account "
                                + dateFormat.format(new Date()));
                        break;
                    case 3:
                        if (adminUser.getBa().getBallance() > 0) {
                            ArrayList<ArrayList> al2 = db.readDataBase("select username, u.id from users as u inner join accounts as a on u.id=a.user_id where u.id <>'" + adminUser.getId() + "';", 2);
                            for (int j = 0; j < al2.size(); j++) {
                                System.out.println(j + 1 + " " + al2.get(j).get(0));
                            }

                            choiceUser = validateIntInput(sc, 1, al2.size());

                            System.out.println("Please input amount to transfer");
                            double transferAmountChoice = validateDoubleInput(sc, 0.01, adminUser.getBa().getBallance());
                            id = Integer.parseInt(al2.get(choiceUser - 1).get(1).toString());
                            adminUser.getBa().transferToAccount(transferAmountChoice, id);

                            System.out.println("Transfered to " + al2.get(choiceUser - 1).get(0).toString()
                                    + "'s bank account " + transferAmountChoice + " € ");
                            arrayForFile.add("Transfer to " + al2.get(choiceUser - 1).get(0).toString()
                                    + "'s bank account " + transferAmountChoice + " € " + dateFormat.format(new Date()));
                        } else {
                            System.out.println("Not enough");
                            arrayForFile.add("Not enough for transfer " + dateFormat.format(new Date()));
                        }
                        break;
                    case 4:
                        ArrayList<ArrayList> al3 = db.readDataBase("select username, u.id from users as u inner join accounts as a on u.id=a.user_id "
                                + "where u.id <>'" + adminUser.getId() + "' and amount > 0;", 2);

                        if (al3.size() > 1) {
                            System.out.println("Users with enouqh balance");
                        } else if (al3.size() > 0) {
                            System.out.println("User with enouqh balance");
                        } else {
                            System.out.println("No user has enough balance");
                            arrayForFile.add("No user has enough for withdraw " + dateFormat.format(new Date()));
                            break;
                        }

                        for (int j = 0; j < al3.size(); j++) {
                            System.out.println(j + 1 + " " + al3.get(j).get(0));
                        }

                        choiceUser = validateIntInput(sc, 1, al3.size());
                        id = Integer.parseInt(al3.get(choiceUser - 1).get(1).toString());
                        amount = adminUser.getBa().showBalance(id);

                        System.out.println("Please input amount to transfer");
                        double transferAmountChoice = validateDoubleInput(sc, 0.01, amount);

                        id = Integer.parseInt(al3.get(choiceUser - 1).get(1).toString());
                        adminUser.getBa().withdrawFromOther(transferAmountChoice, id);

                        System.out.println("Withdraw from " + al3.get(choiceUser - 1).get(0).toString() + "'s account " + transferAmountChoice + " €");
                        arrayForFile.add("Withdraw from " + al3.get(choiceUser - 1).get(0).toString() + "'s account " + transferAmountChoice + " €" + dateFormat.format(new Date()));
                        break;
                    case 5:
                        repeatMenu = false;
                        fa = new FileAccess(adminUser, arrayForFile);
                        break;
                    default:
                }
            }
        }
    }
}
