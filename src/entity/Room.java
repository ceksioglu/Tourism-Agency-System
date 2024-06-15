package entity;

import java.math.BigDecimal;

public class Room {
    private int id;
    private int hotelId;
    private String roomType;
    private String features;
    private int stock;
    private BigDecimal priceAdult;
    private BigDecimal priceChild;

    public Room(int id, int hotelId, String roomType, String features, int stock, BigDecimal priceAdult, BigDecimal priceChild) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomType = roomType;
        this.features = features;
        this.stock = stock;
        this.priceAdult = priceAdult;
        this.priceChild = priceChild;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPriceAdult() {
        return priceAdult;
    }

    public void setPriceAdult(BigDecimal priceAdult) {
        this.priceAdult = priceAdult;
    }

    public BigDecimal getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(BigDecimal priceChild) {
        this.priceChild = priceChild;
    }
}