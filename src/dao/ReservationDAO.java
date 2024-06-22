package dao;

import core.DatabaseManager;
import entity.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    // Fetch all reservations with hotel names
    public List<Reservation> getAllReservationsWithHotelName() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT public.reservation.id, public.reservation.room_id, public.reservation.user_id, public.reservation.start_date, public.reservation.end_date, public.reservation.adult_count, public.reservation.child_count, public.reservation.total_price, public.reservation.guest_name, public.reservation.guest_surname, public.reservation.guest_identity_number, public.reservation.hotel_id, public.reservation.guest_phone, public.hotel.name AS hotel_name " +
                "FROM public.reservation " +
                "JOIN public.hotel ON public.reservation.hotel_id = public.hotel.id";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getInt("user_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getInt("adult_count"),
                        rs.getInt("child_count"),
                        rs.getBigDecimal("total_price"),
                        rs.getString("guest_name"),
                        rs.getString("guest_surname"),
                        rs.getString("guest_identity_number"),
                        rs.getInt("hotel_id"),
                        rs.getString("guest_phone"),
                        rs.getString("hotel_name")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Fetch a reservation by ID
    public Reservation getReservationById(int id) {
        String query = "SELECT public.reservation.id, public.reservation.room_id, public.reservation.user_id, public.reservation.start_date, public.reservation.end_date, public.reservation.adult_count, public.reservation.child_count, public.reservation.total_price, public.reservation.guest_name, public.reservation.guest_surname, public.reservation.guest_identity_number, public.reservation.hotel_id, public.reservation.guest_phone, public.hotel.name AS hotel_name " +
                "FROM public.reservation " +
                "JOIN public.hotel ON public.reservation.hotel_id = public.hotel.id " +
                "WHERE public.reservation.id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                        rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getInt("user_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getInt("adult_count"),
                        rs.getInt("child_count"),
                        rs.getBigDecimal("total_price"),
                        rs.getString("guest_name"),
                        rs.getString("guest_surname"),
                        rs.getString("guest_identity_number"),
                        rs.getInt("hotel_id"),
                        rs.getString("guest_phone"),
                        rs.getString("hotel_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Add a new reservation
    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO public.reservation (room_id, user_id, start_date, end_date, adult_count, child_count, total_price, guest_name, guest_surname, guest_identity_number, hotel_id, guest_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, reservation.getRoomId());
            pstmt.setInt(2, reservation.getUserId());
            pstmt.setDate(3, Date.valueOf(reservation.getStartDate()));
            pstmt.setDate(4, Date.valueOf(reservation.getEndDate()));
            pstmt.setInt(5, reservation.getAdultCount());
            pstmt.setInt(6, reservation.getChildCount());
            pstmt.setBigDecimal(7, reservation.getTotalPrice());
            pstmt.setString(8, reservation.getGuestName());
            pstmt.setString(9, reservation.getGuestSurname());
            pstmt.setString(10, reservation.getGuestIdentityNumber());
            pstmt.setInt(11, reservation.getHotelId());
            pstmt.setString(12, reservation.getGuestPhone());

            System.out.println("Executing query: " + query);
            System.out.println("With parameters: " + reservation);

            int affectedRows = pstmt.executeUpdate();

            System.out.println("Rows affected by insert: " + affectedRows);

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                reservation.setId(generatedId);
                System.out.println("Generated Reservation ID: " + generatedId);
            } else {
                System.out.println("No generated ID returned after inserting reservation.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a reservation
    public void updateReservation(Reservation reservation) {
        String query = "UPDATE public.reservation SET room_id = ?, user_id = ?, start_date = ?, end_date = ?, adult_count = ?, child_count = ?, total_price = ?, guest_name = ?, guest_surname = ?, guest_identity_number = ?, hotel_id = ?, guest_phone = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, reservation.getRoomId());
            pstmt.setInt(2, reservation.getUserId());
            pstmt.setDate(3, Date.valueOf(reservation.getStartDate()));
            pstmt.setDate(4, Date.valueOf(reservation.getEndDate()));
            pstmt.setInt(5, reservation.getAdultCount());
            pstmt.setInt(6, reservation.getChildCount());
            pstmt.setBigDecimal(7, reservation.getTotalPrice());
            pstmt.setString(8, reservation.getGuestName());
            pstmt.setString(9, reservation.getGuestSurname());
            pstmt.setString(10, reservation.getGuestIdentityNumber());
            pstmt.setInt(11, reservation.getHotelId());
            pstmt.setString(12, reservation.getGuestPhone());
            pstmt.setInt(13, reservation.getId());

            System.out.println("Executing update query: " + query);
            System.out.println("With parameters: " + reservation);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected by update: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a reservation
    public void deleteReservation(int id) {
        String query = "DELETE FROM public.reservation WHERE id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);

            System.out.println("Executing delete query: " + query);
            System.out.println("With ID: " + id);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected by delete: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
