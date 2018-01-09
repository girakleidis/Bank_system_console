/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feb_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 *
 * @author George
 */
public class DBAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void updateDataBase(String query) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/afdemp_java_1?useSSL=false", "dbuser", "1234");
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            throw e;
        } finally {
            this.close();
        }

    }

    ;

    public ArrayList readDataBase(String query, int columns) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/afdemp_java_1?useSSL=false", "dbuser", "1234");
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery(query);

            //    resultSet.last();
            //    System.out.println(resultSet.getRow());
            ArrayList al = new ArrayList();
            while (resultSet.next()) {
                if (columns == 1) {
                    al.add(resultSet.getString(1));
                } else {
                    ArrayList al2 = new ArrayList();
                    for (int i = 1; i <= columns; i++) {
                        al2.add(resultSet.getString(i));
                    }
                    al.add(al2);
                }

            }
            //System.out.println(al.size());
            return al;

        } catch (Exception e) {
            throw e;
        } finally {
            this.close();
        }
    }

//    public void writeResultSet(ResultSet resultSet) throws SQLException {
//        // ResultSet is initially before the first data set
//        while (resultSet.next()) {
//
    // It is possible to get the columns via name
    // also possible to get the columns via the column number
    // which starts at 1
    // e.g. resultSet.getSTring(2);
//            String user = resultSet.getString("myuser");
//            String website = resultSet.getString("webpage");
//            String summary = resultSet.getString("summary");
//            Date date = resultSet.getDate("datum");
//            String comment = resultSet.getString("comments");
//            System.out.println("User: " + user);
//            System.out.println("Website: " + website);
//            System.out.println("summary: " + summary);
//            System.out.println("Date: " + date);
//            System.out.println("Comment: " + comment);
//            System.out.println(resultSet.getString(1));
//        }
//    }
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
        }
    }
}
