package dao;

import entity.Hotel;
import entity.Hotel.Facility;
import entity.Hotel.PensionType;
import core.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    // Fetch all hotels
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM public.hotel";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                String region = rs.getString("region");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int stars = rs.getInt("stars");
                List<Facility> facilities = getFacilitiesByHotelId(id);
                List<PensionType> pensionTypes = getPensionTypesByHotelId(id);

                Hotel hotel = new Hotel(id, name, city, region, address, email, phone, stars, facilities, pensionTypes);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    // Fetch hotel by ID
    public Hotel getHotelById(int id) {
        String query = "SELECT * FROM public.hotel WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("city");
                String region = rs.getString("region");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int stars = rs.getInt("stars");
                List<Facility> facilities = getFacilitiesByHotelId(id);
                List<PensionType> pensionTypes = getPensionTypesByHotelId(id);

                return new Hotel(id, name, city, region, address, email, phone, stars, facilities, pensionTypes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Fetch facilities by hotel ID
    public List<Facility> getFacilitiesByHotelId(int hotelId) {
        List<Facility> facilities = new ArrayList<>();
        String query = "SELECT facility FROM public.hotel_facility WHERE hotel_id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, hotelId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                facilities.add(Facility.valueOf(rs.getString("facility")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilities;
    }

    // Fetch pension types by hotel ID
    public List<PensionType> getPensionTypesByHotelId(int hotelId) {
        List<PensionType> pensionTypes = new ArrayList<>();
        String query = "SELECT type FROM public.hotel_pension_type WHERE hotel_id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, hotelId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pensionTypes.add(PensionType.valueOf(rs.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionTypes;
    }

    // Add a new hotel
    public void addHotel(Hotel hotel) {
        String query = "INSERT INTO public.hotel (name, city, region, address, email, phone, stars) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, hotel.getName());
            pstmt.setString(2, hotel.getCity());
            pstmt.setString(3, hotel.getRegion());
            pstmt.setString(4, hotel.getAddress());
            pstmt.setString(5, hotel.getEmail());
            pstmt.setString(6, hotel.getPhone());
            pstmt.setInt(7, hotel.getStars());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int hotelId = rs.getInt(1);
                addFacilities(hotelId, hotel.getFacilities());
                addPensionTypes(hotelId, hotel.getPensionTypes());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add facilities for a hotel
    public void addFacilities(int hotelId, List<Facility> facilities) {
        String query = "INSERT INTO public.hotel_facility (hotel_id, facility) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (Facility facility : facilities) {
                pstmt.setInt(1, hotelId);
                pstmt.setString(2, facility.name());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add pension types for a hotel
    public void addPensionTypes(int hotelId, List<PensionType> pensionTypes) {
        String query = "INSERT INTO public.hotel_pension_type (hotel_id, type) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (PensionType pensionType : pensionTypes) {
                pstmt.setInt(1, hotelId);
                pstmt.setString(2, pensionType.name());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a hotel
    public void updateHotel(Hotel hotel) {
        String query = "UPDATE public.hotel SET name = ?, city = ?, region = ?, address = ?, email = ?, phone = ?, stars = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, hotel.getName());
            pstmt.setString(2, hotel.getCity());
            pstmt.setString(3, hotel.getRegion());
            pstmt.setString(4, hotel.getAddress());
            pstmt.setString(5, hotel.getEmail());
            pstmt.setString(6, hotel.getPhone());
            pstmt.setInt(7, hotel.getStars());
            pstmt.setInt(8, hotel.getId());
            pstmt.executeUpdate();

            // Update facilities and pension types
            deleteFacilitiesByHotelId(hotel.getId());
            addFacilities(hotel.getId(), hotel.getFacilities());

            deletePensionTypesByHotelId(hotel.getId());
            addPensionTypes(hotel.getId(), hotel.getPensionTypes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a hotel
    public void deleteHotel(int id) {
        String query = "DELETE FROM public.hotel WHERE id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete facilities by hotel ID
    public void deleteFacilitiesByHotelId(int hotelId) {
        String query = "DELETE FROM public.hotel_facility WHERE hotel_id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, hotelId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete pension types by hotel ID
    public void deletePensionTypesByHotelId(int hotelId) {
        String query = "DELETE FROM public.hotel_pension_type WHERE hotel_id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, hotelId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Hotel getHotelByName(String hotelName) {
        String query = "SELECT * FROM public.hotel WHERE name = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, hotelName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                String region = rs.getString("region");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int stars = rs.getInt("stars");

                List<Facility> facilities = getFacilitiesByHotelId(id);
                List<PensionType> pensionTypes = getPensionTypesByHotelId(id);

                return new Hotel(id, name, city, region, address, email, phone, stars, facilities, pensionTypes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
