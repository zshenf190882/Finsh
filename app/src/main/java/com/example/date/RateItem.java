package com.example.date;

import java.util.HashMap;

public class RateItem {
    private int id;
    private String curName;
    private String curRate;
    private String url;

    public RateItem() {
        super();
        curName = "";
        curRate = "";
        url = "";

    }

    public RateItem(String curName , String curRate) {
        super();
        this.curName = curName;
        this.curRate = curRate;
//        this.url = url;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setCurName(String curName) {
        this.curName = curName;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCurRate() {
        return curRate;
    }

    public void setCurRate(String curRate) {
        this.curRate = curRate;
    }

    public String getCurName() {
        return curName;
    }
}
