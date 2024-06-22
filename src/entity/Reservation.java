package entity;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Represents a reservation made by a guest at a hotel.
 */

public class Reservation {
    private int id;
    private int roomId;
    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int adultCount;
    private int childCount;
    private BigDecimal totalPrice;
    private String guestName;
    private String guestSurname;
    private String guestIdentityNumber;
    private int hotelId;
    private String guestPhone;
    private String hotelName;

    public Reservation() {
    }

    /**
     * Constructs a new Reservation object with the specified parameters.
     *
     * @param id                  the unique identifier for the reservation
     * @param roomId              the unique identifier for the room
     * @param userId              the unique identifier for the user making the reservation
     * @param startDate           the start date of the reservation
     * @param endDate             the end date of the reservation
     * @param adultCount          the number of adults in the reservation
     * @param childCount          the number of children in the reservation
     * @param totalPrice          the total price of the reservation
     * @param guestName           the name of the guest
     * @param guestSurname        the surname of the guest
     * @param guestIdentityNumber the identity number of the guest
     * @param hotelId             the unique identifier for the hotel
     * @param guestPhone          the phone number of the guest
     * @param hotelName           the name of the hotel
     */

    public Reservation(int id, int roomId, int userId, LocalDate startDate, LocalDate endDate, int adultCount, int childCount, BigDecimal totalPrice, String guestName, String guestSurname, String guestIdentityNumber, int hotelId, String guestPhone, String hotelName) {
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
        this.guestPhone = guestPhone;
        this.hotelName = hotelName;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
