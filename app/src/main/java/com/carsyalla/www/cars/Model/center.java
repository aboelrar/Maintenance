package com.carsyalla.www.cars.Model;

import java.util.ArrayList;

public class center {
    String id,Name,Address,Image,Special;
    boolean fav=false;
    int favNum,rating;
    ArrayList<icons>mylist;
    String discount;
    public center(boolean fav) {
        this.fav = fav;
    }

    public center(String id, String name, String address, String image,int favNum,int rating,ArrayList<icons>mylist,String discount,String Special) {
        this.id = id;
        Name = name;
        Address = address;
        Image = image;
        this.rating=rating;
        this.favNum=favNum;
        this.mylist=mylist;
        this.discount=discount;
        this.Special=Special;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public int getFavNum() {
        return favNum;
    }

    public void setFavNum(int favNum) {
        this.favNum = favNum;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ArrayList<icons> getMylist() {
        return mylist;
    }

    public void setMylist(ArrayList<icons> mylist) {
        this.mylist = mylist;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSpecial() {
        return Special;
    }

    public void setSpecial(String special) {
        Special = special;
    }
}
