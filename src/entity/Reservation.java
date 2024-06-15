package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Reservation {
    private int id;
    private int roomId;
    private int userId;
    private Date startDate;
    private Date endDate;
    private int guestsAdult;
    private int guestsChild;
    private BigDecimal totalPrice;

    public Reservation(int id, int roomId, int userId, Date startDate, Date endDate, int guestsAdult, int guestsChild, BigDecimal totalPrice) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestsAdult = guestsAdult;
        this.guestsChild = guestsChild;
        this.totalPrice = totalPrice;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getGuestsAdult() {
        return guestsAdult;
    }

    public void setGuestsAdult(int guestsAdult) {
        this.guestsAdult = guestsAdult;
    }

    public int getGuestsChild() {
        return guestsChild;
    }

    public void setGuestsChild(int guestsChild) {
        this.guestsChild = guestsChild;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
