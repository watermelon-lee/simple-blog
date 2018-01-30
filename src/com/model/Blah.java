package com.model;

import java.io.Serializable;
import java.util.Date;

public class Blah implements Serializable{
    private String username;
    private String txt;
    private Date date;

    public Blah() {
    }

    public Blah(String username, String txt, Date date) {
        this.username = username;
        this.txt = txt;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
