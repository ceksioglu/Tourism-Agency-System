package dao;

import core.DatabaseManager;
import entity.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    // Get all rooms
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT public.room.id, public.room.hotel_id, public.room.season_id, public.room.pension_type_id, public.room.room_type, " +
                "public.room.bed_count, public.room.size, public.room.tv, public.room.minibar, public.room.game_console, public.room.safe, " +
                "public.room.projector, public.room.adult_price, public.room.child_price, public.room.stock, " +
                "public.hotel.name AS hotel_name, public.hotel.city AS hotel_city, public.season.start_date, public.season.end_date, " +
                "public.hotel_pension_type.type AS pension_type " +
                "FROM public.room " +
                "JOIN public.hotel ON public.room.hotel_id = public.hotel.id " +
                "JOIN public.season ON public.room.season_id = public.season.id " +
                "JOIN public.hotel_pension_type ON public.room.pension_type_id = public.hotel_pension_type.id " +
                "ORDER BY public.hotel.name";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("season_id"),
                        rs.getInt("pension_type_id"),
                        rs.getString("hotel_name"),
                        rs.getString("hotel_city"),
                        rs.getString("start_date") + " to " + rs.getString("end_date"),
                        rs.getString("pension_type"),
                        Room.RoomType.valueOf(rs.getString("room_type")),
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

    // Get room by ID
    public Room getRoomById(int id) {
        String query = "SELECT public.room.*, public.hotel.name AS hotel_name, public.hotel.city AS hotel_city, " +
                "public.season.start_date || ' to ' || public.season.end_date AS season, public.hotel_pension_type.type AS pension_type " +
                "FROM public.room " +
                "JOIN public.hotel ON public.room.hotel_id = public.hotel.id " +
                "JOIN public.season ON public.room.season_id = public.season.id " +
                "JOIN public.hotel_pension_type ON public.room.pension_type_id = public.hotel_pension_type.id " +
                "WHERE public.room.id = ?";
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
                        rs.getString("hotel_name"),
                        rs.getString("hotel_city"),
                        rs.getString("season"),
                        rs.getString("pension_type"),
                        Room.RoomType.valueOf(rs.getString("room_type")),
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

    // Get rooms by hotel ID
    public List<Room> getRoomsByHotelId(int hotelId) {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT public.room.id, public.room.hotel_id, public.room.season_id, public.room.pension_type_id, public.room.room_type, " +
                "public.room.bed_count, public.room.size, public.room.tv, public.room.minibar, public.room.game_console, public.room.safe, " +
                "public.room.projector, public.room.adult_price, public.room.child_price, public.room.stock, " +
                "public.hotel.name AS hotel_name, public.hotel.city AS hotel_city, public.season.start_date, public.season.end_date, " +
                "public.hotel_pension_type.type AS pension_type " +
                "FROM public.room " +
                "JOIN public.hotel ON public.room.hotel_id = public.hotel.id " +
                "JOIN public.season ON public.room.season_id = public.season.id " +
                "JOIN public.hotel_pension_type ON public.room.pension_type_id = public.hotel_pension_type.id " +
                "WHERE public.room.hotel_id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, hotelId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("season_id"),
                        rs.getInt("pension_type_id"),
                        rs.getString("hotel_name"),
                        rs.getString("hotel_city"),
                        rs.getString("start_date") + " to " + rs.getString("end_date"),
                        rs.getString("pension_type"),
                        Room.RoomType.valueOf(rs.getString("room_type")),
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

    // Add a room
    public void addRoom(Room room) {
        String query = "INSERT INTO public.room (hotel_id, season_id, pension_type_id, room_type, bed_count, size, tv, minibar, game_console, safe, projector, adult_price, child_price, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, room.getHotelId());
            ps.setInt(2, room.getSeasonId());
            ps.setInt(3, room.getPensionTypeId());
            ps.setString(4, room.getRoomType().name());
            ps.setInt(5, room.getBedCount());
            ps.setInt(6, room.getSize());
            ps.setBoolean(7, room.isTv());
            ps.setBoolean(8, room.isMinibar());
            ps.setBoolean(9, room.isGameConsole());
            ps.setBoolean(10, room.isSafe());
            ps.setBoolean(11, room.isProjector());
            ps.setBigDecimal(12, room.getAdultPrice());
            ps.setBigDecimal(13, room.getChildPrice());
            ps.setInt(14, room.getStock());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a room
    public void updateRoom(Room room) {
        String query = "UPDATE public.room SET hotel_id = ?, season_id = ?, pension_type_id = ?, room_type = ?, bed_count = ?, size = ?, tv = ?, minibar = ?, game_console = ?, safe = ?, projector = ?, adult_price = ?, child_price = ?, stock = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, room.getHotelId());
            ps.setInt(2, room.getSeasonId());
            ps.setInt(3, room.getPensionTypeId());
            ps.setString(4, room.getRoomType().name());
            ps.setInt(5, room.getBedCount());
            ps.setInt(6, room.getSize());
            ps.setBoolean(7, room.isTv());
            ps.setBoolean(8, room.isMinibar());
            ps.setBoolean(9, room.isGameConsole());
            ps.setBoolean(10, room.isSafe());
            ps.setBoolean(11, room.isProjector());
            ps.setBigDecimal(12, room.getAdultPrice());
            ps.setBigDecimal(13, room.getChildPrice());
            ps.setInt(14, room.getStock());
            ps.setInt(15, room.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a room
    public void deleteRoom(int id) {
        String query = "DELETE FROM public.room WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get booked room count
    public int getBookedRoomCount(int roomId, LocalDate startDate, LocalDate endDate) {
        String query = "SELECT COUNT(*) AS booked_count FROM public.reservation WHERE room_id = ? AND (start_date <= ? AND end_date >= ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, roomId);
            ps.setDate(2, Date.valueOf(endDate));
            ps.setDate(3, Date.valueOf(startDate));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("booked_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
