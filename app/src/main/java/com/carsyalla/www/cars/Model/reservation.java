package com.carsyalla.www.cars.Model;

public class reservation {
    String id,Name,Address,Image,reservatioDate,canCancel,canRate,currentStatus,centerName,userName,center_id,id_reservation_status;
    Double latitude,lontiude;
    boolean fav=false;
    int favNum,rating;
    public reservation(boolean fav) {
        this.fav = fav;
    }

    public reservation(String id, String name, String address, String image, int favNum, int rating,String reservatioDate,Double latitude,Double lontiude,String canCancel,String canRate,String currentStatus,String userName,String centerName,String center_id,String id_reservation_status) {
        this.id = id;
        Name = name;
        Address = address;
        Image = image;
        this.rating=rating;
        this.latitude=latitude;
        this.lontiude=lontiude;
        this.canCancel=canCancel;
        this.reservatioDate=reservatioDate;
        this.favNum=favNum;
        this.canRate=canRate;
        this.centerName=centerName;
        this.userName=userName;
        this.currentStatus=currentStatus;
        this.center_id=center_id;
        this.id_reservation_status=id_reservation_status;
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

    public String getReservatioDate() {
        return reservatioDate;
    }

    public void setReservatioDate(String reservatioDate) {
        this.reservatioDate = reservatioDate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLontiude() {
        return lontiude;
    }

    public void setLontiude(Double lontiude) {
        this.lontiude = lontiude;
    }

    public String getCanCancel() {
        return canCancel;
    }

    public void setCanCancel(String canCancel) {
        this.canCancel = canCancel;
    }

    public String getCanRate() {
        return canRate;
    }

    public void setCanRate(String canRate) {
        this.canRate = canRate;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }

    public String getId_reservation_status() {
        return id_reservation_status;
    }

    public void setId_reservation_status(String id_reservation_status) {
        this.id_reservation_status = id_reservation_status;
    }
}
