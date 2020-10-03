package com.example.tour_guide.HelperClasses;

public class viewReviews {

    private String RName;
    private String RMail;
    private static String RPlace;
    private static String RDescription;


    public viewReviews() {
    }

    public viewReviews(String rName, String rPlace, String rMail, String rDescription) {
        RName = rName;
        RPlace = rPlace;
        RMail = rMail;
        RDescription = rDescription;
    }

    public String getRName() {
        return RName;
    }

    public void setRName(String rName) {
        RName = rName;
    }

    public String getRPlace() {
        return RPlace;
    }

    public void setRPlace(String rPlace) {
        RPlace = rPlace;
    }

    public String getRMail() {
        return RMail;
    }

    public void setRMail(String rMail) {
        RMail = rMail;
    }

    public  String getRDescription() {
        return RDescription;
    }

    public void setRDescription(String rDescription) {
        RDescription = rDescription;
    }

}
