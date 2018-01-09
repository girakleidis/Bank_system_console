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

    public void setHolder(String Holder) {
        this.holder = holder;
    }

    public void withdraw() {
        DBAccess dba = new DBAccess();
    }

    public void transferToAccount(double amount, int id) throws Exception {
        this.ballance -= amount;
        DBAccess dba = new DBAccess();
        ArrayList al = new ArrayList();
        dba.updateDataBase("Update accounts set amount = amount -" + amount + " where user_id=" + this.holderId + ";");
        dba.updateDataBase("Update accounts set amount = amount + " + amount + " where user_id=" + id + ";");
    }

    ;

    public void deposit() {
        DBAccess dba = new DBAccess();
    }

    public double showBalance(int id) throws Exception {
        DBAccess dba = new DBAccess();
        ArrayList al = new ArrayList();
        al = dba.readDataBase("Select amount from accounts where id = " + id + ";", 1);
        return Double.parseDouble(al.get(0).toString());
    }
}
