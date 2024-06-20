package business;

import dao.RoomDAO;
import entity.Room;

import java.time.LocalDate;
import java.util.List;

public class RoomManager {
    private final RoomDAO roomDAO;

    public RoomManager() {
        this.roomDAO = new RoomDAO();
    }

    public List<Room> getAllRooms() {
        return this.roomDAO.getAllRooms();
    }

    public List<Room> getRoomsByHotelId(int hotelId) {
        return roomDAO.getRoomsByHotelId(hotelId);
    }

    public boolean checkRoomAvailability(int roomId, LocalDate startDate, LocalDate endDate) {
        Room room = roomDAO.getRoomById(roomId);
        if (room == null) {
            return false;
        }

        int bookedCount = roomDAO.getBookedRoomCount(roomId, startDate, endDate);
        return room.getStock() > bookedCount;
    }

    public void decreaseRoomStock(int roomId, int amount) {
        Room room = roomDAO.getRoomById(roomId);
        if (room != null) {
            room.setStock(room.getStock() - amount);
            roomDAO.updateRoom(room);
        }
    }

    public void increaseRoomStock(int roomId, int amount) {
        Room room = roomDAO.getRoomById(roomId);
        if (room != null) {
            room.setStock(room.getStock() + amount);
            roomDAO.updateRoom(room);
        }
    }

    public Room getRoomById(int id) {
        return this.roomDAO.getRoomById(id);
    }

    public void addRoom(Room room) {
        this.roomDAO.addRoom(room);
    }

    public void updateRoom(Room room) {
        this.roomDAO.updateRoom(room);
    }

    public void deleteRoom(int id) {
        this.roomDAO.deleteRoom(id);
    }
}
