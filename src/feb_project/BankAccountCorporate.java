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
public class BankAccountCorporate extends BankAccount {

    private DBAccess dba = new DBAccess();

    public BankAccountCorporate(double ballance, String holder, int holderId) {
        super(ballance, holder, holderId);
    }

    public void withdrawFromOther(double amount, int id) throws Exception {
        super.setBallance(super.getBallance() + amount);
        String query1 = "Update accounts set amount = amount - " + amount + " where user_id=" + id + ";";
        String query2 = "Update accounts set amount = amount +" + amount + " where user_id=" + super.getHolderId() + ";";
        int result = dba.updateDataBase(query1, query2);
        if (result == 0) {
            System.out.println("Transfer was unsuccesfull");
            super.setBallance(super.getBallance() - amount);
        }

    }
}
