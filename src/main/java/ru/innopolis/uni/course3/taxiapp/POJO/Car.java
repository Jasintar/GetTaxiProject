package ru.innopolis.uni.course3.taxiapp.POJO;

import javax.persistence.*;

/**
 * Created on 28.12.2016.
 *
 * @authot Julia Savicheva
 */

//@Entity
//@Table(name = "CAR")
public class Car {
//    @Id
//    @Column(name = "ID_CAR")
//    @GeneratedValue
    private long id;
//    @Column(name = "BREND")
    private String brand;
//    @Column(name = "\'number\'")
    private String number;
//    @Column(name = "ID_USER")
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Car(String brand, String number, long userId) {
        this.brand = brand;
        this.number = number;
        this.userId = userId;
    }

    public Car(String brand, String number) {
        this.brand = brand;
        this.number = number;
    }

    public Car() {
    }

    public Car(long id, String brand, String number, long userId) {
        this.id = id;
        this.brand = brand;
        this.number = number;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setId(long id) {
        this.id = id;
    }
}
