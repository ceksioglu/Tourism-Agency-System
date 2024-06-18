package business;

import dao.ReservationDAO;
import entity.Reservation;

import java.util.List;

public class ReservationManager {
    private ReservationDAO reservationDAO;

    public ReservationManager() {
        this.reservationDAO = new ReservationDAO();
    }

    public List<Reservation> getAllReservations() {
        return this.reservationDAO.getAllReservations();
    }

    public Reservation getReservationById(int id) {
        return this.reservationDAO.getReservationById(id);
    }

    public void addReservation(Reservation reservation) {
        this.reservationDAO.addReservation(reservation);
    }

    public void updateReservation(Reservation reservation) {
        this.reservationDAO.updateReservation(reservation);
    }

    public void deleteReservation(int id) {
        this.reservationDAO.deleteReservation(id);
    }
}
