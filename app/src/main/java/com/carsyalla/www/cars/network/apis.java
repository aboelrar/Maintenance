package com.carsyalla.www.cars.network;

public class apis {

    String userName="54732964";
    String password="259743";

    public String carsyall ="https://www.carsyalla.com/carsyallaapi/";

    public String Login=carsyall+"User/login/"+userName+"/"+password;

    public String SignUp=carsyall+"/User/register/"+userName+"/"+password+"/";

    public String Brands=carsyall+"brand/getAll/"+userName+"/"+password;

    public String Centers=carsyall+"brand/getAllBrandCenters/"+userName+"/"+password+"/";

    public String specialCenters=carsyall+"center/getAllSpecialCenters/"+userName+"/"+password+"/";

    public String slider=carsyall+"Mobile_home_slider/getAll/"+userName+"/"+password;

    public String services=carsyall+"Service/getAll/"+userName+"/"+password;

    public String addCenter=carsyall+"Center/add/"+userName+"/"+password;

    public String gouvernate=carsyall+"Governate/getAll/"+userName+"/"+password;

    public String city=carsyall+"city/getAllCitiesByGovernateId/"+userName+"/"+password;

    public String centerDetails=carsyall+"center/show_selected/"+userName+"/"+password;

    public String addFavoriteCenter=carsyall+"User/addFavoriteCenter/"+userName+"/"+password;

    public String deleteFavouriteCenter=carsyall+"User/deleteFavoriteCenters/"+userName+"/"+password;

    public String reserve=carsyall+"Reservation/make_reservation/"+userName+"/"+password;

    public String models=carsyall+"Car_model/getAllModelsByBrandId/"+userName+"/"+password;

    public String Reservation_details=carsyall+"Reservation/view_reservation/"+userName+"/"+password;

    public String center_search=carsyall+"center/searchForCenters/"+userName+"/"+password;

    public String center_search_byName=carsyall+"center/searchForCentersByName/"+userName+"/"+password;

    public String center_comments=carsyall+"center/getCenterComments/"+userName+"/"+password;

    public String my_reservation=carsyall+"Reservation/view_user_reservations/"+userName+"/"+password;

    public String addStolenCar=carsyall+"Stolencar/add/"+userName+"/"+password;

    public String stolenCar=carsyall+"Stolencar/getAll/"+userName+"/"+password;

    public String chassisNumberSearch=carsyall+"Stolencar/search_by_chassis/"+userName+"/"+password;

    public String stolenCarDetails=carsyall+"Stolencar/show_selected/"+userName+"/"+password;

    public String addReportedCar=carsyall+"ReportedCar/add/"+userName+"/"+password;

    public String ReportedCar=carsyall+"ReportedCar/getAll/"+userName+"/"+password;

    public String reportedCarDetails=carsyall+"ReportedCar/show_selected/"+userName+"/"+password;

    public String reportedCarSearch=carsyall+"ReportedCar/search_by_plate/"+userName+"/"+password;

    public String userProfile=carsyall+"User/view/"+userName+"/"+password;

    public String addRate=carsyall+"User/addRate/"+userName+"/"+password;

    public String myFavourite=carsyall+"User/getFavoriteCenters/"+userName+"/"+password;

    public String faceBook_Login=carsyall+"user/loginWithFaceBook/"+userName+"/"+password;

    public String nearByCenters=carsyall+"brand/getAllBrandNearbyCenters/"+userName+"/"+password;

    public String rate=carsyall+"User/addRate/"+userName+"/"+password;

    public String ads=carsyall+"Popup_ads/getActiveAds/"+userName+"/"+password;

    public String problems=carsyall+"Messages/send_message/"+userName+"/"+password;

    public String cancelResevation=carsyall+"Reservation/changeReservationStatus/"+userName+"/"+password;

    public String forget_password=carsyall+"User/forget_pass/"+userName+"/"+password;

    public String edit_profile=carsyall+"user/editProfile/"+userName+"/"+password;

    public String change_pass=carsyall+"User/update_password/"+userName+"/"+password;

    public String nearby_services=carsyall+"brand/getAllBrandNearbyCentersBYServiceAndBrand/"+userName+"/"+password;

    public String service_byBrandId=carsyall+"Service/getServicesByBrandId/"+userName+"/"+password;
}
