package business;

import dao.RoomDAO;
import entity.Room;

import java.util.List;

public class RoomManager {
    private RoomDAO roomDAO;

    public RoomManager() {
        this.roomDAO = new RoomDAO();
    }

    public List<Room> getAllRooms() {
        return this.roomDAO.getAllRooms();
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
