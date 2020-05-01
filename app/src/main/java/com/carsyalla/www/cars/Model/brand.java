package com.carsyalla.www.cars.Model;
public class brand {
    String id,iamge,Name,centersNum;

    public brand(String id, String iamge, String name, String centersNum) {
        this.id = id;
        this.iamge = iamge;
        Name = name;
        this.centersNum = centersNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIamge() {
        return iamge;
    }

    public void setIamge(String iamge) {
        this.iamge = iamge;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCentersNum() {
        return centersNum;
    }

    public void setCentersNum(String centersNum) {
        this.centersNum = centersNum;
    }
}
