package hotel;

public class hotel {
    private String HotelName, Province, Description, ImageUrl;

    public hotel() {
    }

    public hotel(String hotelName, String province, String description, String imageUrl) {
        HotelName = hotelName;
        Province = province;
        Description = description;
        ImageUrl = imageUrl;
    }

    public String getHotelName () {
        return HotelName;}

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
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


