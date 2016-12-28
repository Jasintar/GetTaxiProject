package ru.innopolis.uni.course3.taxiapp;

/**
 * Created on 25.12.2016.
 *
 * @author Julia Savicheva
 */
public class User {
    private String username;
    private String password;
    private String firstname;
    private String phone;
    private String usertype;
    private long id;


    public User() {

    }
    public User(String username, String password, String firstname, String phone, String usertype) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.phone = phone;
        this.usertype = usertype;
    }

    public User(String username, String firstname, String phone, String usertype, long id) {
        this.username = username;
        this.firstname = firstname;
        this.phone = phone;
        this.usertype = usertype;
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
