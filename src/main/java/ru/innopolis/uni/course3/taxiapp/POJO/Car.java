package ru.innopolis.uni.course3.taxiapp.POJO;

/**
 * Created on 28.12.2016.
 *
 * @authot Julia Savicheva
 */
public class Car {
    private long id;
    private String brand;
    private String number;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private long userId;

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
