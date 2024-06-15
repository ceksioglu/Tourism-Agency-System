package entity;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private int stars;
    private String facilities;
    private String pensionTypes;

    public Hotel(int id, String name, String address, String email, String phone, int stars, String facilities, String pensionTypes) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.stars = stars;
        this.facilities = facilities;
        this.pensionTypes = pensionTypes;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getPensionTypes() {
        return pensionTypes;
    }

    public void setPensionTypes(String pensionTypes) {
        this.pensionTypes = pensionTypes;
    }
}
