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
import java.sql.SQLException;
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

    /**
     * Using Transactions in case of something happens
     *
     * @param query1
     * @param query2
     * @return
     * @throws SQLException
     */
    public int updateDataBase(String query1, String query2) throws SQLException {
        int i = 0;
        try {

            connect = DriverManager.getConnection("jdbc:mysql://localhost/afdemp_java_1?useSSL=false", "dbuser", "1234");
            connect.setAutoCommit(false);

            preparedStatement = connect.prepareStatement(query1);
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement(query2);
            preparedStatement.executeUpdate();
            connect.commit();
            i = 1;
        } catch (SQLException e) {
            connect.rollback();
            System.out.println("Error With Databse Connection Program Aborting");
            System.exit(0);
        } finally {
            this.close();
        }
        return i;
    }

    public ArrayList readDataBase(String query, int columns) throws SQLException {
        ArrayList al = new ArrayList();
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/afdemp_java_1?useSSL=false", "dbuser", "1234");
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

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

        } catch (SQLException se) {
            System.out.println("Error With Databse Connection Program Aborting");
            System.exit(0);
        } finally {
            this.close();
            return al;
        }
    }

    public ArrayList credentialsCheck(String userName, String password) throws SQLException {
        ArrayList al = new ArrayList();
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/afdemp_java_1?useSSL=false", "dbuser", "1234");
            PreparedStatement psmt = connect.prepareStatement("select id from users where username=? AND password=?");
            psmt.setString(1, userName);
            psmt.setString(2, password);

            resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                al.add(resultSet.getString(1));
            }

        } catch (SQLException se) {
            System.out.println("Error With Databse Connection Program Aborting");
            System.exit(0);
        } finally {
            this.close();
            return al;
        }

    }

    private void close() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connect != null) {
            connect.close();
        }

    }
}
