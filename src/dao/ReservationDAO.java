package dao;

import entity.Reservation;
import core.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";
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
                        rs.getInt("guests_adult"),
                        rs.getInt("guests_child"),
                        rs.getBigDecimal("total_price")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO reservations (room_id, user_id, start_date, end_date, guests_adult, guests_child, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, reservation.getRoomId());
            pstmt.setInt(2, reservation.getUserId());
            pstmt.setDate(3, reservation.getStartDate());
            pstmt.setDate(4, reservation.getEndDate());
            pstmt.setInt(5, reservation.getGuestsAdult());
            pstmt.setInt(6, reservation.getGuestsChild());
            pstmt.setBigDecimal(7, reservation.getTotalPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReservation(Reservation reservation) {
        String query = "UPDATE reservations SET room_id = ?, user_id = ?, start_date = ?, end_date = ?, guests_adult = ?, guests_child = ?, total_price = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, reservation.getRoomId());
            pstmt.setInt(2, reservation.getUserId());
            pstmt.setDate(3, reservation.getStartDate());
            pstmt.setDate(4, reservation.getEndDate());
            pstmt.setInt(5, reservation.getGuestsAdult());
            pstmt.setInt(6, reservation.getGuestsChild());
            pstmt.setBigDecimal(7, reservation.getTotalPrice());
            pstmt.setInt(8, reservation.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation(int id) {
        String query = "DELETE FROM reservations WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
