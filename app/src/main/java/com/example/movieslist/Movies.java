package com.example.movieslist;

public class Movies {
    private String title;
    private String cardImg;
    private String type;
    private String desc;


    public Movies(){}
    public Movies(String title, String cardImg , String type ){
        this.title = title;
        this.cardImg = cardImg;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
