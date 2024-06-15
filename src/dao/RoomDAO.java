package dao;

import entity.Room;
import core.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM room";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("season_id"),
                        rs.getInt("pension_type_id"),
                        rs.getString("room_type"),
                        rs.getInt("bed_count"),
                        rs.getInt("size"),
                        rs.getBoolean("tv"),
                        rs.getBoolean("minibar"),
                        rs.getBoolean("game_console"),
                        rs.getBoolean("safe"),
                        rs.getBoolean("projector"),
                        rs.getBigDecimal("adult_price"),
                        rs.getBigDecimal("child_price"),
                        rs.getInt("stock")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public Room getRoomById(int id) {
        String query = "SELECT * FROM room WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Room(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("season_id"),
                        rs.getInt("pension_type_id"),
                        rs.getString("room_type"),
                        rs.getInt("bed_count"),
                        rs.getInt("size"),
                        rs.getBoolean("tv"),
                        rs.getBoolean("minibar"),
                        rs.getBoolean("game_console"),
                        rs.getBoolean("safe"),
                        rs.getBoolean("projector"),
                        rs.getBigDecimal("adult_price"),
                        rs.getBigDecimal("child_price"),
                        rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addRoom(Room room) {
        String query = "INSERT INTO room (hotel_id, season_id, pension_type_id, room_type, bed_count, size, tv, minibar, game_console, safe, projector, adult_price, child_price, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, room.getHotelId());
            pstmt.setInt(2, room.getSeasonId());
            pstmt.setInt(3, room.getPensionTypeId());
            pstmt.setString(4, room.getRoomType());
            pstmt.setInt(5, room.getBedCount());
            pstmt.setInt(6, room.getSize());
            pstmt.setBoolean(7, room.isTv());
            pstmt.setBoolean(8, room.isMinibar());
            pstmt.setBoolean(9, room.isGameConsole());
            pstmt.setBoolean(10, room.isSafe());
            pstmt.setBoolean(11, room.isProjector());
            pstmt.setBigDecimal(12, room.getAdultPrice());
            pstmt.setBigDecimal(13, room.getChildPrice());
            pstmt.setInt(14, room.getStock());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRoom(Room room) {
        String query = "UPDATE room SET hotel_id = ?, season_id = ?, pension_type_id = ?, room_type = ?, bed_count = ?, size = ?, tv = ?, minibar = ?, game_console = ?, safe = ?, projector = ?, adult_price = ?, child_price = ?, stock = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, room.getHotelId());
            pstmt.setInt(2, room.getSeasonId());
            pstmt.setInt(3, room.getPensionTypeId());
            pstmt.setString(4, room.getRoomType());
            pstmt.setInt(5, room.getBedCount());
            pstmt.setInt(6, room.getSize());
            pstmt.setBoolean(7, room.isTv());
            pstmt.setBoolean(8, room.isMinibar());
            pstmt.setBoolean(9, room.isGameConsole());
            pstmt.setBoolean(10, room.isSafe());
            pstmt.setBoolean(11, room.isProjector());
            pstmt.setBigDecimal(12, room.getAdultPrice());
            pstmt.setBigDecimal(13, room.getChildPrice());
            pstmt.setInt(14, room.getStock());
            pstmt.setInt(15, room.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(int id) {
        String query = "DELETE FROM room WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
