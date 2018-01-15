/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feb_project;

import java.util.ArrayList;

/**
 *
 * @author George
 */
public class BankAccount {

    private double ballance;
    private String holder;
    private int holderId;
    private DBAccess dba = new DBAccess();

    public BankAccount(double ballance, String holder, int holderId) {
        this.ballance = ballance;
        this.holder = holder;
        this.holderId = holderId;
    }

    public double getBallance() {
        return ballance;
    }

    public String getHolder() {
        return holder;
    }

    public void withdrawFromOther(double amount, int id) throws Exception {
        int result = 0, result2 = 0;
        result = dba.updateDataBase("Update accounts set amount = amount +" + amount + " where user_id=" + this.holderId + ";");
        if (result == 1) {
            result2 = dba.updateDataBase("Update accounts set amount = amount - " + amount + " where user_id=" + id + ";");
        }
        if (result2 != 1) {
            System.out.println("Transfer was unsuccesfull");
        }
    }

    public void transferToAccount(double amount, int id) throws Exception {
        this.ballance -= amount;
        int result = 0, result2 = 0;
        result = dba.updateDataBase("Update accounts set amount = amount -" + amount + " where user_id=" + this.holderId + ";");
        if (result == 1) {
            result2 = dba.updateDataBase("Update accounts set amount = amount + " + amount + " where user_id=" + id + ";");
        }
        if (result2 != 1) {
            System.out.println("Transfer was unsuccesfull");
        }
    }

    public double showBalance(int id) throws Exception {
        ArrayList al = new ArrayList();
        al = dba.readDataBase("Select amount from accounts where id = " + id + ";", 1);
        return Double.parseDouble(al.get(0).toString());
    }
}
