package entity;

import java.sql.Date;

/**
 * Represents a season associated with a hotel.
 */

public class Season {
    private int id;
    private int hotelId;
    private String hotelName;
    private Date startDate;
    private Date endDate;

    public Season() {
    }

    /**
     * Constructs a new Season object with the specified parameters.
     *
     * @param id        the unique identifier for the season
     * @param hotelId   the unique identifier for the hotel
     * @param hotelName the name of the hotel
     * @param startDate the start date of the season
     * @param endDate   the end date of the season
     */

    public Season(int id, int hotelId, String hotelName, Date startDate, Date endDate) {
        this.id = id;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
