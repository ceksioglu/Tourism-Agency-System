package dao;

import entity.Hotel;
import core.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM hotels";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Hotel hotel = new Hotel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("stars"),
                        rs.getString("facilities")
                );
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    public void addHotel(Hotel hotel) {
        String query = "INSERT INTO hotels (name, address, email, phone, stars, facilities) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, hotel.getName());
            pstmt.setString(2, hotel.getAddress());
            pstmt.setString(3, hotel.getEmail());
            pstmt.setString(4, hotel.getPhone());
            pstmt.setInt(5, hotel.getStars());
            pstmt.setString(6, hotel.getFacilities());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHotel(Hotel hotel) {
        String query = "UPDATE hotels SET name = ?, address = ?, email = ?, phone = ?, stars = ?, facilities = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, hotel.getName());
            pstmt.setString(2, hotel.getAddress());
            pstmt.setString(3, hotel.getEmail());
            pstmt.setString(4, hotel.getPhone());
            pstmt.setInt(5, hotel.getStars());
            pstmt.setString(6, hotel.getFacilities());
            pstmt.setInt(7, hotel.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHotel(int id) {
        String query = "DELETE FROM hotels WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
