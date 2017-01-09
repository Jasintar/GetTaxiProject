package ru.innopolis.uni.course3.taxiapp;

/**
 * Created on 26.12.2016.
 *
 * @authot Julia Savicheva
 */
public class Order {
    private long id;
    private String start;
    private String finish;
    private String status;
    private long clientId;
    private long driverId;

    public Order(String start, String finish, long clientId) {
        this.start = start;
        this.finish = finish;
        this.clientId = clientId;
    }

    public Order(String start, String finish, String status, long clientId) {
        this.start = start;
        this.finish = finish;
        this.status = status;
        this.clientId = clientId;
    }

    public Order(long id, String start, String finish, String status, long clientId) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.status = status;
        this.clientId = clientId;
    }

    public Order(long id, String start, String finish, String status, long clientId, long driverId) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.status = status;
        this.clientId = clientId;
        this.driverId = driverId;
    }


    public void setDriverId(long driver) {
        this.driverId = driver;
    }

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public String getStatus() {
        return status;
    }

    public long getDriverId() {
        return driverId;
    }

    public long getClientId() {
        return clientId;
    }

    public long getId() {
        return id;
    }
}
