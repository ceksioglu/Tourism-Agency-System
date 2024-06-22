package dao;

import entity.Hotel;
import entity.Hotel.Facility;
import entity.Hotel.PensionType;
import entity.Season;
import core.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing hotel data.
 */
public class HotelDAO {

    /**
     * Fetches all hotels from the database.
     *
     * @return a list of all hotels
     */
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

    /**
     * Fetches a hotel by its unique identifier.
     *
     * @param id the unique identifier of the hotel
     * @return the hotel with the specified id, or null if not found
     */
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

    /**
     * Fetches the facilities of a hotel by its unique identifier.
     *
     * @param hotelId the unique identifier of the hotel
     * @return a list of facilities for the specified hotel
     */
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

    /**
     * Fetches the pension types of a hotel by its unique identifier.
     *
     * @param hotelId the unique identifier of the hotel
     * @return a list of pension types for the specified hotel
     */
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

    /**
     * Fetches the seasons of a hotel by its unique identifier.
     *
     * @param hotelId the unique identifier of the hotel
     * @return a list of seasons for the specified hotel
     */
    public List<Season> getSeasonsByHotelId(int hotelId) {
        List<Season> seasons = new ArrayList<>();
        String query = "SELECT public.season.id, public.season.hotel_id, public.season.start_date, public.season.end_date, public.hotel.name AS hotel_name " +
                "FROM public.season " +
                "JOIN public.hotel ON public.season.hotel_id = public.hotel.id " +
                "WHERE public.season.hotel_id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, hotelId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int fetchedHotelId = rs.getInt("hotel_id");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                String hotelName = rs.getString("hotel_name");

                Season season = new Season(id, fetchedHotelId, hotelName, startDate, endDate);
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasons;
    }

    /**
     * Adds a new hotel to the database.
     *
     * @param hotel the hotel to add
     * @return the unique identifier of the newly added hotel
     */
    public int addHotel(Hotel hotel) {
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
                return hotelId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Adds facilities for a hotel.
     *
     * @param hotelId    the unique identifier of the hotel
     * @param facilities the list of facilities to add
     */
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

    /**
     * Adds pension types for a hotel.
     *
     * @param hotelId      the unique identifier of the hotel
     * @param pensionTypes the list of pension types to add
     */
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

    /**
     * Updates a hotel in the database.
     *
     * @param hotel the hotel to update
     */
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

    /**
     * Deletes a hotel from the database.
     *
     * @param id the unique identifier of the hotel to delete
     */
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

    /**
     * Deletes facilities for a hotel by its unique identifier.
     *
     * @param hotelId the unique identifier of the hotel
     */
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

    /**
     * Deletes pension types for a hotel by its unique identifier.
     *
     * @param hotelId the unique identifier of the hotel
     */
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

    /**
     * Fetches a hotel by its name.
     *
     * @param hotelName the name of the hotel
     * @return the hotel with the specified name, or null if not found
     */
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
