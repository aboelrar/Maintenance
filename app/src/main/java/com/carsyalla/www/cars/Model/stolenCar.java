package com.carsyalla.www.cars.Model;

public class stolenCar {
    String id,carImg,carModel,palteNumber,brand,phone,name,color,serial,yearModel,address;

    public stolenCar(String id, String carImg, String carModel, String palteNumber,String brand,String phone,String name,String color,String serial,String yearModel,String address) {
        this.id = id;
        this.carImg = carImg;
        this.carModel = carModel;
        this.palteNumber = palteNumber;
        this.brand=brand;
        this.phone=phone;
        this.name=name;
        this.color=color;
        this.serial=serial;
        this.yearModel=yearModel;
        this.address=address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarImg() {
        return carImg;
    }

    public void setCarImg(String carImg) {
        this.carImg = carImg;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getPalteNumber() {
        return palteNumber;
    }

    public void setPalteNumber(String palteNumber) {
        this.palteNumber = palteNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getYearModel() {
        return yearModel;
    }

    public void setYearModel(String yearModel) {
        this.yearModel = yearModel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
