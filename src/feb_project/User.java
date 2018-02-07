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
public abstract class User {

    private int id;
    private String name;
    private int userLevel;

    public User(int id, String name, int userLevel) {
        this.id = id;
        this.name = name;
        this.userLevel = userLevel;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserLevel() {
        return userLevel;
    }

}
