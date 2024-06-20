package business;

import dao.ReservationDAO;
import entity.Reservation;

import java.util.List;

public class ReservationManager {
    private ReservationDAO reservationDAO;

    public ReservationManager() {
        this.reservationDAO = new ReservationDAO();
    }

    public void addReservation(Reservation reservation) {
        reservationDAO.addReservation(reservation);
    }

    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    public void deleteReservation(int id) {
        reservationDAO.deleteReservation(id);
    }

    public Reservation getReservationById(int id) {
        return reservationDAO.getReservationById(id);
    }

    public List<Reservation> getAllReservationsWithHotelName() {
        return reservationDAO.getAllReservationsWithHotelName();
    }
}
