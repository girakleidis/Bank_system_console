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

    public int updateDataBase(String query) throws Exception {
        try {
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/afdemp_java_1?useSSL=false", "dbuser", "1234");
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            int i = statement.executeUpdate(query);
            return i;
        } catch (Exception e) {
            throw e;
        } finally {
            this.close();
        }
    }

    public ArrayList readDataBase(String query, int columns) throws Exception {
        try {
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
            return al;

        } catch (Exception e) {
            throw e;
        } finally {
            this.close();
        }
    }

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
