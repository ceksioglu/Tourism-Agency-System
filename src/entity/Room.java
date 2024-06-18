package entity;

import java.math.BigDecimal;

public class Room {

    public enum RoomType {
        SINGLE,
        DOUBLE,
        JUNIOR_SUITE,
        SUITE
    }

    private int id;
    private int hotelId;
    private String hotelName;
    private int seasonId;
    private String season;
    private int pensionTypeId;
    private String pensionType;
    private RoomType roomType;
    private int bedCount;
    private int size;
    private boolean tv;
    private boolean minibar;
    private boolean gameConsole;
    private boolean safe;
    private boolean projector;
    private BigDecimal adultPrice;
    private BigDecimal childPrice;
    private int stock;

    public Room() {
    }

    // Constructor for database interaction (with IDs)
    public Room(int id, int hotelId, int seasonId, int pensionTypeId, RoomType roomType, int bedCount, int size, boolean tv, boolean minibar, boolean gameConsole, boolean safe, boolean projector, BigDecimal adultPrice, BigDecimal childPrice, int stock) {
        this.id = id;
        this.hotelId = hotelId;
        this.seasonId = seasonId;
        this.pensionTypeId = pensionTypeId;
        this.roomType = roomType;
        this.bedCount = bedCount;
        this.size = size;
        this.tv = tv;
        this.minibar = minibar;
        this.gameConsole = gameConsole;
        this.safe = safe;
        this.projector = projector;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.stock = stock;
    }

    // Constructor for UI interaction (with names)
    public Room(int id, String hotelName, String season, String pensionType, RoomType roomType, int bedCount, int size, boolean tv, boolean minibar, boolean gameConsole, boolean safe, boolean projector, BigDecimal adultPrice, BigDecimal childPrice, int stock) {
        this.id = id;
        this.hotelName = hotelName;
        this.season = season;
        this.pensionType = pensionType;
        this.roomType = roomType;
        this.bedCount = bedCount;
        this.size = size;
        this.tv = tv;
        this.minibar = minibar;
        this.gameConsole = gameConsole;
        this.safe = safe;
        this.projector = projector;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.stock = stock;
    }

    // Getters and setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getPensionTypeId() {
        return pensionTypeId;
    }

    public void setPensionTypeId(int pensionTypeId) {
        this.pensionTypeId = pensionTypeId;
    }

    public String getPensionType() {
        return pensionType;
    }

    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(boolean gameConsole) {
        this.gameConsole = gameConsole;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public boolean isProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
