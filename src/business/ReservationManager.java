package business;

import dao.ReservationDAO;
import entity.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReservationManager {
    private ReservationDAO reservationDAO;

    public ReservationManager() {
        this.reservationDAO = new ReservationDAO();
    }

    public List<Reservation> getAllReservationsWithHotelName() {
        return this.reservationDAO.getAllReservationsWithHotelName();
    }

    public Reservation getReservationById(int id) {
        return this.reservationDAO.getReservationById(id);
    }

    public void addReservation(int roomId, int userId, LocalDate startDate, LocalDate endDate, int adultCount, int childCount, String guestName, String guestSurname, String guestIdentityNumber, int hotelId, String guestPhone, BigDecimal totalPrice) {
        Reservation reservation = new Reservation();
        reservation.setRoomId(roomId);
        reservation.setUserId(userId);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setAdultCount(adultCount);
        reservation.setChildCount(childCount);
        reservation.setGuestName(guestName);
        reservation.setGuestSurname(guestSurname);
        reservation.setGuestIdentityNumber(guestIdentityNumber);
        reservation.setHotelId(hotelId);
        reservation.setGuestPhone(guestPhone);
        reservation.setTotalPrice(totalPrice);

        this.reservationDAO.addReservation(reservation);
    }

    public void updateReservation(Reservation reservation) {
        this.reservationDAO.updateReservation(reservation);
    }

    public void deleteReservation(int id) {
        this.reservationDAO.deleteReservation(id);
    }
}
