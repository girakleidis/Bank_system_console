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
public class SimpleUser extends User {

    private BankAccount ba;

    public SimpleUser(int id, String name, int userLevel, double amount) {
        super(id, name, userLevel);
        ba = new BankAccount(amount, name, id);
    }

    public BankAccount getBa() {
        return ba;
    }

}
