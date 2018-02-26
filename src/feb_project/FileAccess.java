/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feb_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    private PrintWriter pw;
    DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
    Date date = new Date();

    public FileAccess(SimpleUser user, ArrayList<String> al) throws FileNotFoundException {
        WritetoFile(1, user.getName(), al);
    }

    public FileAccess(AdminUser user, ArrayList<String> al) throws FileNotFoundException {
        WritetoFile(2, user.getName(), al);
    }

    public void WritetoFile(int accessLevel, String user, ArrayList<String> al) throws FileNotFoundException {

        if (accessLevel == 1) {
            StringBuilder sb = new StringBuilder(user);
            sb = sb.insert(sb.length() - 1, '_');
            File file = new File("statement_" + sb.toString() + "_" + dateFormat.format(date) + ".txt");
            pw = new PrintWriter(new FileOutputStream(file, true));
        } else {
            File file = new File("statement_admin_" + dateFormat.format(date) + ".txt");
            pw = new PrintWriter(new FileOutputStream(file, true));
        }
        for (int i = 0; i < al.size(); i++) {
            pw.println(al.get(i).toString());
        }
        pw.close();
    }
}
