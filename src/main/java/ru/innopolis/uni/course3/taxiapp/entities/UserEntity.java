package ru.innopolis.uni.course3.taxiapp.entities;



/**
 * Created on 25.12.2016.
 *
 * @author Julia Savicheva
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class UserEntity {
    @Id
    @Column(name= "ID_USER")
    private long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FIRST_NAME")
    private String firstname;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "USER_TYPE")
    private String usertype;


    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public UserEntity() {
    }

    public UserEntity(String username, String password, String firstname, String phone, String usertype) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.phone = phone;
        this.usertype = usertype;
    }

    public UserEntity(String username, String firstname, String phone, String usertype, long id) {
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
