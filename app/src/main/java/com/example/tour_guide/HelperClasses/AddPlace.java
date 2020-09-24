package com.example.tour_guide.HelperClasses;

public class AddPlace {
    private String PlaceName, Province, Description, ImageUrl;

    public AddPlace() { }

    public AddPlace(String placeName, String province, String description, String imageUrl) {
        PlaceName = placeName;
        Province = province;
        Description = description;
        ImageUrl = imageUrl;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
