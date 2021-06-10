package com.example.demo;

import java.io.Serializable;

public class CouponDTO implements Serializable {

    private String title;
    private int id;

    public CouponDTO() {
        super();
    }

    public CouponDTO(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyMessage [id=" + id + ", title=" + title + "]";
    }

}