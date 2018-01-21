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
        int result = 0, result2 = 0;
        result = dba.updateDataBase("Update accounts set amount = amount +" + amount + " where user_id=" + super.getHolderId() + ";");
        if (result == 1) {
            result2 = dba.updateDataBase("Update accounts set amount = amount - " + amount + " where user_id=" + id + ";");
        }
        if (result2 != 1) {
            System.out.println("Transfer was unsuccesfull");
        }
    }
}
