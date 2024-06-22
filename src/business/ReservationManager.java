package business;

import dao.ReservationDAO;
import entity.Reservation;

import java.util.List;

public class ReservationManager {
    private final ReservationDAO reservationDAO;
    private final RoomManager roomManager;

    public ReservationManager() {
        this.reservationDAO = new ReservationDAO();
        this.roomManager = new RoomManager();
    }

    public List<Reservation> getAllReservationsWithHotelName() {
        return reservationDAO.getAllReservationsWithHotelName();
    }

    public Reservation getReservationById(int id) {
        return reservationDAO.getReservationById(id);
    }

    public void addReservation(Reservation reservation) {
        reservationDAO.addReservation(reservation);
        roomManager.decreaseRoomStock(reservation.getRoomId(), 1);
    }

    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    public void deleteReservation(int id) {
        Reservation reservation = reservationDAO.getReservationById(id);
        if (reservation != null) {
            reservationDAO.deleteReservation(id);
            roomManager.increaseRoomStock(reservation.getRoomId(), 1);
        }
    }
}
