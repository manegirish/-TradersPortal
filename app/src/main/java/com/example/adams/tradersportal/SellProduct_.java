package com.example.adams.tradersportal;

/**
 * Created by GirishM on 27-12-2017.
 */

public class SellProduct_ {

    private int id;
    private String price;
    private String name;
    private long timestamp;

    /* Setter Methods */

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /* Getter Methods */

    public int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
