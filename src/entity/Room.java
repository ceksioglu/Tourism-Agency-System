package entity;

import java.math.BigDecimal;

/**
 * Represents a room in a hotel.
 */

public class Room {

    public enum RoomType {
        SINGLE,
        DOUBLE,
        JUNIOR_SUITE,
        SUITE
    }

    private int id;
    private int hotelId;
    private int seasonId;
    private int pensionTypeId;
    private String hotelName;
    private String hotelCity;
    private String season;
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

    /**
     * Constructs a new Room object with the specified parameters.
     *
     * @param id            the unique identifier for the room
     * @param hotelId       the unique identifier for the hotel
     * @param seasonId      the unique identifier for the season
     * @param pensionTypeId the unique identifier for the pension type
     * @param hotelName     the name of the hotel
     * @param hotelCity     the city where the hotel is located
     * @param season        the season for the room
     * @param pensionType   the type of pension for the room
     * @param roomType      the type of the room
     * @param bedCount      the number of beds in the room
     * @param size          the size of the room
     * @param tv            whether the room has a TV
     * @param minibar       whether the room has a minibar
     * @param gameConsole   whether the room has a game console
     * @param safe          whether the room has a safe
     * @param projector     whether the room has a projector
     * @param adultPrice    the price for adults
     * @param childPrice    the price for children
     * @param stock         the stock of available rooms
     */

    public Room(int id, int hotelId, int seasonId, int pensionTypeId, String hotelName, String hotelCity, String season, String pensionType, RoomType roomType, int bedCount, int size, boolean tv, boolean minibar, boolean gameConsole, boolean safe, boolean projector, BigDecimal adultPrice, BigDecimal childPrice, int stock) {
        this.id = id;
        this.hotelId = hotelId;
        this.seasonId = seasonId;
        this.pensionTypeId = pensionTypeId;
        this.hotelName = hotelName;
        this.hotelCity = hotelCity;
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

    // Getters and setters

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

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getPensionTypeId() {
        return pensionTypeId;
    }

    public void setPensionTypeId(int pensionTypeId) {
        this.pensionTypeId = pensionTypeId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
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
