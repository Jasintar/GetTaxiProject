package ru.innopolis.uni.course3.taxiapp;

/**
 * Created on 25.12.2016.
 *
 * @authot Julia Savicheva
 */
public class User {
    private String username;
    private String firstname;
    private String phone;
    private String usertype;
    private long id;


    public User(String username, String firstname, String phone, String usertype, long id) {
        this.username = username;
        this.firstname = firstname;
        this.phone = phone;
        this.usertype = usertype;
        this.id = id;
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
}
