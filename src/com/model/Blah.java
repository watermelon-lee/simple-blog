package com.model;

import java.io.Serializable;
import java.util.Date;

public class Blah implements Serializable{
    private String username;
    private String txt;
    private Date date;

    @Override
    public int hashCode() {
        final int prime=31;
        int result=1;
        result=prime*result+((date==null)?0:date.hashCode());
        result=prime*result+((username==null?0:username.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(obj==null)
            return false;
        if(getClass()!=obj.getClass())
            return false;
        Blah other=(Blah)obj;
        if(date==null){
            if(other.date!=null)
                return false;
        }
        else if(!date.equals(other.date))
            return false;
        if(username==null){
            if(other.username!=null)
                return false;
        }
        else if(!username.equals(other.username))
            return false;
        return true;
    }

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
