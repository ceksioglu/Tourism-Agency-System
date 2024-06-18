package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Reservation {
    private int id;
    private int roomId;
    private int userId;
    private Date startDate;
    private Date endDate;
    private int adultCount;
    private int childCount;
    private BigDecimal totalPrice;
    private String guestName;
    private String guestSurname;
    private String guestIdentityNumber;
    private int hotelId;

    public Reservation() {}

    public Reservation(int id, int roomId, int userId, Date startDate, Date endDate, int adultCount, int childCount, BigDecimal totalPrice, String guestName, String guestSurname, String guestIdentityNumber, int hotelId) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.totalPrice = totalPrice;
        this.guestName = guestName;
        this.guestSurname = guestSurname;
        this.guestIdentityNumber = guestIdentityNumber;
        this.hotelId = hotelId;
    }

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

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestSurname() {
        return guestSurname;
    }

    public void setGuestSurname(String guestSurname) {
        this.guestSurname = guestSurname;
    }

    public String getGuestIdentityNumber() {
        return guestIdentityNumber;
    }

    public void setGuestIdentityNumber(String guestIdentityNumber) {
        this.guestIdentityNumber = guestIdentityNumber;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
