package business;

import dao.ReservationDAO;
import entity.Reservation;

import java.util.List;

/**
 * Business logic class for managing reservations.
 */
public class ReservationManager {
    private final ReservationDAO reservationDAO;
    private final RoomManager roomManager;

    /**
     * Constructs a new ReservationManager instance and initializes the ReservationDAO and RoomManager.
     */
    public ReservationManager() {
        this.reservationDAO = new ReservationDAO();
        this.roomManager = new RoomManager();
    }

    /**
     * Retrieves all reservations with hotel names.
     *
     * @return a list of all reservations with associated hotel names
     */
    public List<Reservation> getAllReservationsWithHotelName() {
        return reservationDAO.getAllReservationsWithHotelName();
    }

    /**
     * Retrieves a reservation by its unique identifier.
     *
     * @param id the unique identifier of the reservation
     * @return the reservation with the specified id, or null if not found
     */
    public Reservation getReservationById(int id) {
        return reservationDAO.getReservationById(id);
    }

    /**
     * Adds a new reservation and decreases the stock of the associated room by 1.
     *
     * @param reservation the reservation to add
     */
    public void addReservation(Reservation reservation) {
        this.reservationDAO.addReservation(reservation);
        this.roomManager.decreaseRoomStock(reservation.getRoomId(), 1);
    }

    /**
     * Updates an existing reservation.
     *
     * @param reservation the reservation to update
     */
    public void updateReservation(Reservation reservation) {
        this.reservationDAO.updateReservation(reservation);
    }

    /**
     * Deletes a reservation by its unique identifier and increases the stock of the associated room by 1.
     *
     * @param id the unique identifier of the reservation to delete
     */
    public void deleteReservation(int id) {
        Reservation reservation = reservationDAO.getReservationById(id);
        if (reservation != null) {
            this.reservationDAO.deleteReservation(id);
            this.roomManager.increaseRoomStock(reservation.getRoomId(), 1);
        }
    }
}
