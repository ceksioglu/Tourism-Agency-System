package dao;

import entity.Reservation;
import core.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM public.reservation";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getInt("user_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getInt("adult_count"),
                        rs.getInt("child_count"),
                        rs.getBigDecimal("total_price"),
                        rs.getString("guest_name"),
                        rs.getString("guest_surname"),
                        rs.getString("guest_identity_number"),
                        rs.getInt("hotel_id")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public Reservation getReservationById(int id) {
        String query = "SELECT * FROM public.reservation WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                        rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getInt("user_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getInt("adult_count"),
                        rs.getInt("child_count"),
                        rs.getBigDecimal("total_price"),
                        rs.getString("guest_name"),
                        rs.getString("guest_surname"),
                        rs.getString("guest_identity_number"),
                        rs.getInt("hotel_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO public.reservation (room_id, user_id, start_date, end_date, adult_count, child_count, total_price, guest_name, guest_surname, guest_identity_number, hotel_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, reservation.getRoomId());
            pstmt.setInt(2, reservation.getUserId());
            pstmt.setDate(3, reservation.getStartDate());
            pstmt.setDate(4, reservation.getEndDate());
            pstmt.setInt(5, reservation.getAdultCount());
            pstmt.setInt(6, reservation.getChildCount());
            pstmt.setBigDecimal(7, reservation.getTotalPrice());
            pstmt.setString(8, reservation.getGuestName());
            pstmt.setString(9, reservation.getGuestSurname());
            pstmt.setString(10, reservation.getGuestIdentityNumber());
            pstmt.setInt(11, reservation.getHotelId());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                reservation.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReservation(Reservation reservation) {
        String query = "UPDATE public.reservation SET room_id = ?, user_id = ?, start_date = ?, end_date = ?, adult_count = ?, child_count = ?, total_price = ?, guest_name = ?, guest_surname = ?, guest_identity_number = ?, hotel_id = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, reservation.getRoomId());
            pstmt.setInt(2, reservation.getUserId());
            pstmt.setDate(3, reservation.getStartDate());
            pstmt.setDate(4, reservation.getEndDate());
            pstmt.setInt(5, reservation.getAdultCount());
            pstmt.setInt(6, reservation.getChildCount());
            pstmt.setBigDecimal(7, reservation.getTotalPrice());
            pstmt.setString(8, reservation.getGuestName());
            pstmt.setString(9, reservation.getGuestSurname());
            pstmt.setString(10, reservation.getGuestIdentityNumber());
            pstmt.setInt(11, reservation.getHotelId());
            pstmt.setInt(12, reservation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation(int id) {
        String query = "DELETE FROM public.reservation WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
