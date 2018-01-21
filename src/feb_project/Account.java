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
public abstract class Account {

    private double ballance;
    private String holder;
    private int holderId;
    private DBAccess dba = new DBAccess();

    public Account(double ballance, String holder, int holderId) {
        this.ballance = ballance;
        this.holder = holder;
        this.holderId = holderId;
    }

    public double getBallance() {
        return ballance;
    }

    public void setBallance(double ballance) {
        this.ballance = ballance;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public int getHolderId() {
        return holderId;
    }

    public void setHolderId(int holderId) {
        this.holderId = holderId;
    }

    public void transferToAccount(double amount, int id) throws Exception {
        try {
            this.ballance -= amount;
            int result = 0, result2 = 0;
            result = dba.updateDataBase("Update accounts set amount = amount -" + amount + " where user_id=" + this.holderId + ";");
            result2 = dba.updateDataBase("Update accounts set amount = amount + " + amount + " where user_id=" + id + ";");
        } catch (Exception ex) {
            System.out.println("Transfer was unsuccesfull");
            throw ex;
        } finally {

        }
    }

    public double showBalance(int id) throws Exception {
        ArrayList al = new ArrayList();
        al = dba.readDataBase("Select amount from accounts where id = " + id + ";", 1);
        return Double.parseDouble(al.get(0).toString());
    }

    public abstract void withdrawFromOther(double amount, int id) throws Exception;
}
