/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feb_project;

import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author George
 */
public class Feb_project {

    // public static Scanner sc = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        Scanner sc = new Scanner(System.in).useLocale(Locale.ENGLISH);;
        LoginScreen ls = new LoginScreen();
        ls.welcomeScreen(sc);
        AppMenu am = new AppMenu(ls);
        am.selectMenu(sc);
    }
}
