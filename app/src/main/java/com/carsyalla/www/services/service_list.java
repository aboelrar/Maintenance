package com.carsyalla.www.services;

public class service_list {
    String id,title,Image,brand_id,centers;

    public service_list(String id, String title,String Image,String brand_id,String centers) {
        this.id = id;
        this.title = title;
        this.Image=Image;
        this.brand_id=brand_id;
        this.centers=centers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getCenters() {
        return centers;
    }

    public void setCenters(String centers) {
        this.centers = centers;
    }
}
