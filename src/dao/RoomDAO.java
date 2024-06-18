package dao;

import entity.Room;
import core.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT public.room.id, public.hotel.name as hotel_name, " +
                "public.season.start_date || ' to ' || public.season.end_date as season, " +
                "public.hotel_pension_type.type as pension_type, public.room.room_type, public.room.bed_count, public.room.size, public.room.tv, public.room.minibar, public.room.game_console, " +
                "public.room.safe, public.room.projector, public.room.adult_price, public.room.child_price, public.room.stock " +
                "FROM public.room " +
                "JOIN public.hotel ON public.room.hotel_id = public.hotel.id " +
                "JOIN public.season ON public.room.season_id = public.season.id " +
                "JOIN public.hotel_pension_type ON public.room.pension_type_id = public.hotel_pension_type.id";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("id"),
                        rs.getString("hotel_name"),
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
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public Room getRoomById(int id) {
        String query = "SELECT public.room.id, public.hotel.name as hotel_name, " +
                "public.season.start_date || ' to ' || public.season.end_date as season, " +
                "public.hotel_pension_type.type as pension_type, public.room.room_type, public.room.bed_count, public.room.size, public.room.tv, public.room.minibar, public.room.game_console, " +
                "public.room.safe, public.room.projector, public.room.adult_price, public.room.child_price, public.room.stock " +
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
                        rs.getString("hotel_name"),
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

    public void addRoom(Room room) {
        String query = "INSERT INTO public.room (hotel_id, season_id, pension_type_id, room_type, bed_count, size, tv, minibar, game_console, safe, projector, adult_price, child_price, stock) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, room.getHotelId());
            pstmt.setInt(2, room.getSeasonId());
            pstmt.setInt(3, room.getPensionTypeId());
            pstmt.setString(4, room.getRoomType().name());
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
        String query = "UPDATE public.room SET hotel_id = ?, season_id = ?, pension_type_id = ?, room_type = ?, bed_count = ?, size = ?, tv = ?, minibar = ?, game_console = ?, safe = ?, projector = ?, adult_price = ?, child_price = ?, stock = ? " +
                "WHERE id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, room.getHotelId());
            pstmt.setInt(2, room.getSeasonId());
            pstmt.setInt(3, room.getPensionTypeId());
            pstmt.setString(4, room.getRoomType().name());
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
        String query = "DELETE FROM public.room WHERE id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
