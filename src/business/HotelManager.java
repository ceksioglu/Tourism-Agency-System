package business;

import dao.HotelDAO;
import entity.Hotel;
import entity.Hotel.Facility;
import entity.Hotel.PensionType;
import entity.Season;

import java.util.List;

/**
 * Business logic class for managing hotels.
 */
public class HotelManager {
    private final HotelDAO hotelDAO;

    /**
     * Constructs a new HotelManager instance and initializes the HotelDAO.
     */
    public HotelManager() {
        this.hotelDAO = new HotelDAO();
    }

    /**
     * Retrieves all hotels.
     *
     * @return a list of all hotels
     */
    public List<Hotel> getAllHotels() {
        return this.hotelDAO.getAllHotels();
    }

    /**
     * Retrieves a hotel by its unique identifier.
     *
     * @param id the unique identifier of the hotel
     * @return the hotel with the specified id, or null if not found
     */
    public Hotel getHotelById(int id) {
        return this.hotelDAO.getHotelById(id);
    }

    /**
     * Retrieves a hotel by its name.
     *
     * @param hotelName the name of the hotel
     * @return the hotel with the specified name, or null if not found
     */
    public Hotel getHotelByName(String hotelName) {
        return hotelDAO.getHotelByName(hotelName);
    }

    /**
     * Adds a new hotel.
     *
     * @param hotel the hotel to add
     * @return the unique identifier of the newly added hotel
     */
    public int addHotel(Hotel hotel) {
        return this.hotelDAO.addHotel(hotel);
    }

    /**
     * Updates an existing hotel.
     *
     * @param hotel the hotel to update
     */
    public void updateHotel(Hotel hotel) {
        this.hotelDAO.updateHotel(hotel);
    }

    /**
     * Deletes a hotel by its unique identifier.
     *
     * @param id the unique identifier of the hotel to delete
     */
    public void deleteHotel(int id) {
        this.hotelDAO.deleteHotel(id);
    }

    /**
     * Retrieves the facilities of a hotel by its unique identifier.
     *
     * @param hotelId the unique identifier of the hotel
     * @return a list of facilities for the specified hotel
     */
    public List<Facility> getFacilitiesByHotelId(int hotelId) {
        return this.hotelDAO.getFacilitiesByHotelId(hotelId);
    }

    /**
     * Adds facilities to a hotel.
     *
     * @param hotelId    the unique identifier of the hotel
     * @param facilities the list of facilities to add
     */
    public void addFacilities(int hotelId, List<Facility> facilities) {
        this.hotelDAO.addFacilities(hotelId, facilities);
    }

    /**
     * Deletes all facilities of a hotel by its unique identifier.
     *
     * @param hotelId the unique identifier of the hotel
     */
    public void deleteFacilitiesByHotelId(int hotelId) {
        this.hotelDAO.deleteFacilitiesByHotelId(hotelId);
    }

    /**
     * Retrieves the pension types of a hotel by its unique identifier.
     *
     * @param hotelId the unique identifier of the hotel
     * @return a list of pension types for the specified hotel
     */
    public List<PensionType> getPensionTypesByHotelId(int hotelId) {
        return this.hotelDAO.getPensionTypesByHotelId(hotelId);
    }

    /**
     * Adds pension types to a hotel.
     *
     * @param hotelId      the unique identifier of the hotel
     * @param pensionTypes the list of pension types to add
     */
    public void addPensionTypes(int hotelId, List<PensionType> pensionTypes) {
        this.hotelDAO.addPensionTypes(hotelId, pensionTypes);
    }

    /**
     * Deletes all pension types of a hotel by its unique identifier.
     *
     * @param hotelId the unique identifier of the hotel
     */
    public void deletePensionTypesByHotelId(int hotelId) {
        this.hotelDAO.deletePensionTypesByHotelId(hotelId);
    }

    /**
     * Retrieves the seasons of a hotel by its unique identifier.
     *
     * @param hotelId the unique identifier of the hotel
     * @return a list of seasons for the specified hotel
     */
    public List<Season> getSeasonsByHotelId(int hotelId) {
        return this.hotelDAO.getSeasonsByHotelId(hotelId);
    }
}
