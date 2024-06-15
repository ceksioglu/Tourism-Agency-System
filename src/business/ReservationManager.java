package business;

import dao.ReservationDAO;
import entity.Reservation;

import java.util.List;

public class ReservationManager {
    private final ReservationDAO reservationDAO;

    public ReservationManager() {
        reservationDAO = new ReservationDAO();
    }

    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    public Reservation getReservationById(int id) {
        return reservationDAO.getReservationById(id);
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
}
