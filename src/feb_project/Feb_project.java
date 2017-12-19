/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feb_project;

import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author George
 */
public class Feb_project {

    public static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        DBAccess dba = new DBAccess();
        Scanner sc = new Scanner(System.in);
        LoginScreen ls = null;
//
        String userName = sc.nextLine();
        String passWord = sc.nextLine();

        ResultSet rs = null;
        rs = dba.readDataBase("select * from users where username='" + userName + "'" + "AND password='" + passWord + "'");
        if (rs.next()) {
            ls = new LoginScreen(1);
            System.out.println("succeed");
        } else {
            System.out.println("wrong");
        }

        AppMenu am = new AppMenu(ls);
        am.showMenu();
    }
}
