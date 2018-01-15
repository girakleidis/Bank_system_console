/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feb_project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author George
 */
public class FileAccess {

    private LoginScreen ls;
    private PrintWriter pw;
    DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
    Date date = new Date();

    public FileAccess(LoginScreen ls, ArrayList<String> al) throws FileNotFoundException {
        this.ls = ls;
        if (ls.getLoggedUserLevel() == 1) {
            pw = new PrintWriter("e:\\statement_" + ls.getLoggedUser() + "_" + dateFormat.format(date) + ".txt");
        } else {
            pw = new PrintWriter("e:\\statement_admin_" + dateFormat.format(date) + ".txt");
        }
        for (int i = 0; i < al.size(); i++) {
            pw.println(al.get(i).toString());
        }
        pw.close();
    }
}
