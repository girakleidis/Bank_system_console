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
public class AdminUser extends User {

    private BankAccountCorporate ba;

    public AdminUser(int id, String name, int userLevel, double amount) {
        super(id, name, userLevel);
        ba = new BankAccountCorporate(amount, name, id);
    }

    public BankAccountCorporate getBa() {
        return ba;
    }

}
