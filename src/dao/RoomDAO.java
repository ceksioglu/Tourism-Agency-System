package dao;

import entity.Room;
import core.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getString("room_type"),
                        rs.getString("features"),
                        rs.getInt("stock"),
                        rs.getBigDecimal("price_adult"),
                        rs.getBigDecimal("price_child")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public void addRoom(Room room) {
        String query = "INSERT INTO rooms (hotel_id, room_type, features, stock, price_adult, price_child) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, room.getHotelId());
            pstmt.setString(2, room.getRoomType());
            pstmt.setString(3, room.getFeatures());
            pstmt.setInt(4, room.getStock());
            pstmt.setBigDecimal(5, room.getPriceAdult());
            pstmt.setBigDecimal(6, room.getPriceChild());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRoom(Room room) {
        String query = "UPDATE rooms SET hotel_id = ?, room_type = ?, features = ?, stock = ?, price_adult = ?, price_child = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, room.getHotelId());
            pstmt.setString(2, room.getRoomType());
            pstmt.setString(3, room.getFeatures());
            pstmt.setInt(4, room.getStock());
            pstmt.setBigDecimal(5, room.getPriceAdult());
            pstmt.setBigDecimal(6, room.getPriceChild());
            pstmt.setInt(7, room.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(int id) {
        String query = "DELETE FROM rooms WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
