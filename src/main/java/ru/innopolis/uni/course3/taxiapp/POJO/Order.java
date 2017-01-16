package ru.innopolis.uni.course3.taxiapp.POJO;

import javax.persistence.*;

/**
 * Created on 26.12.2016.
 *
 * @authot Julia Savicheva
 */
//@Entity
//@Table(name = "ORDER")
public class Order {

//    @Id
//    @Column(name = "ID_ORDER")
//    @GeneratedValue
    private long id;
//    @Column(name = "START")
    private String start;
//    @Column(name = "FNISH")
    private String finish;
//    @Column(name = "STATUS")
    private String status;
//    @Column(name = "ID_CLIENT")
    private long clientId;
//    @Column(name = "ID_DRIVER")
    private long driverId;

    public Order() {
    }

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

    public void setStart(String start) {
        this.start = start;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
}
