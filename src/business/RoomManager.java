package business;

import dao.RoomDAO;
import entity.Room;

import java.util.List;

/**
 * Business logic class for managing rooms.
 */
public class RoomManager {
    private final RoomDAO roomDAO;

    /**
     * Constructs a new RoomManager instance and initializes the RoomDAO.
     */
    public RoomManager() {
        this.roomDAO = new RoomDAO();
    }

    /**
     * Retrieves all rooms.
     *
     * @return a list of all rooms
     */
    public List<Room> getAllRooms() {
        return this.roomDAO.getAllRooms();
    }

    /**
     * Retrieves rooms by hotel ID.
     *
     * @param hotelId the unique identifier of the hotel
     * @return a list of rooms associated with the specified hotel ID
     */
    public List<Room> getRoomsByHotelId(int hotelId) {
        return roomDAO.getRoomsByHotelId(hotelId);
    }

    /**
     * Checks if the total number of guests (adults and children) can be accommodated in the specified room.
     *
     * @param roomId the unique identifier of the room
     * @param adultCount the number of adults
     * @param childCount the number of children
     * @return true if the room can accommodate the total number of guests, false otherwise
     */
    public boolean checkBedCount(int roomId, int adultCount, int childCount) {
        Room room = roomDAO.getRoomById(roomId);
        if (room == null) {
            return false;
        }
        int totalGuests = adultCount + childCount;
        return room.getBedCount() >= totalGuests;
    }

    /**
     * Decreases the stock of the specified room by the given amount.
     *
     * @param roomId the unique identifier of the room
     * @param amount the amount to decrease the stock by
     */
    public void decreaseRoomStock(int roomId, int amount) {
        Room room = roomDAO.getRoomById(roomId);
        if (room != null) {
            room.setStock(room.getStock() - amount);
            roomDAO.updateRoom(room);
        }
    }

    /**
     * Increases the stock of the specified room by the given amount.
     *
     * @param roomId the unique identifier of the room
     * @param amount the amount to increase the stock by
     */
    public void increaseRoomStock(int roomId, int amount) {
        Room room = roomDAO.getRoomById(roomId);
        if (room != null) {
            room.setStock(room.getStock() + amount);
            roomDAO.updateRoom(room);
        }
    }

    /**
     * Retrieves a room by its unique identifier.
     *
     * @param id the unique identifier of the room
     * @return the room with the specified id, or null if not found
     */
    public Room getRoomById(int id) {
        return this.roomDAO.getRoomById(id);
    }

    /**
     * Adds a new room.
     *
     * @param room the room to add
     */
    public void addRoom(Room room) {
        this.roomDAO.addRoom(room);
    }

    /**
     * Updates an existing room.
     *
     * @param room the room to update
     */
    public void updateRoom(Room room) {
        this.roomDAO.updateRoom(room);
    }

    /**
     * Deletes a room by its unique identifier.
     *
     * @param id the unique identifier of the room to delete
     */
    public void deleteRoom(int id) {
        this.roomDAO.deleteRoom(id);
    }
}
