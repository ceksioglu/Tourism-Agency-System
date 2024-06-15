package business;

import dao.RoomDAO;
import entity.Room;

import java.util.List;

public class RoomManager {
    private final RoomDAO roomDAO;

    public RoomManager() {
        roomDAO = new RoomDAO();
    }

    public List<Room> getAllRooms() {
        return roomDAO.getAllRooms();
    }

    public Room getRoomById(int id) {
        return roomDAO.getRoomById(id);
    }

    public void addRoom(Room room) {
        roomDAO.addRoom(room);
    }

    public void updateRoom(Room room) {
        roomDAO.updateRoom(room);
    }

    public void deleteRoom(int id) {
        roomDAO.deleteRoom(id);
    }
}
