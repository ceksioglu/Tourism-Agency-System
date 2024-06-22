package business;

import dao.HotelDAO;
import entity.Hotel;
import entity.Hotel.Facility;
import entity.Hotel.PensionType;
import entity.Season;

import java.util.List;

public class HotelManager {
    private final HotelDAO hotelDAO;

    public HotelManager() {
        this.hotelDAO = new HotelDAO();
    }

    public List<Hotel> getAllHotels() {
        return this.hotelDAO.getAllHotels();
    }

    public Hotel getHotelById(int id) {
        return this.hotelDAO.getHotelById(id);
    }

    public Hotel getHotelByName(String hotelName) {
        return hotelDAO.getHotelByName(hotelName);
    }

    public int addHotel(Hotel hotel) {
        return this.hotelDAO.addHotel(hotel);
    }

    public void updateHotel(Hotel hotel) {
        this.hotelDAO.updateHotel(hotel);
    }

    public void deleteHotel(int id) {
        this.hotelDAO.deleteHotel(id);
    }

    // Methods for managing facilities
    public List<Facility> getFacilitiesByHotelId(int hotelId) {
        return this.hotelDAO.getFacilitiesByHotelId(hotelId);
    }

    public void addFacilities(int hotelId, List<Facility> facilities) {
        this.hotelDAO.addFacilities(hotelId, facilities);
    }

    public void deleteFacilitiesByHotelId(int hotelId) {
        this.hotelDAO.deleteFacilitiesByHotelId(hotelId);
    }

    // Methods for managing pension types
    public List<PensionType> getPensionTypesByHotelId(int hotelId) {
        return this.hotelDAO.getPensionTypesByHotelId(hotelId);
    }

    public void addPensionTypes(int hotelId, List<PensionType> pensionTypes) {
        this.hotelDAO.addPensionTypes(hotelId, pensionTypes);
    }

    public void deletePensionTypesByHotelId(int hotelId) {
        this.hotelDAO.deletePensionTypesByHotelId(hotelId);
    }

    // Methods for managing seasons
    public List<Season> getSeasonsByHotelId(int hotelId) {
        return this.hotelDAO.getSeasonsByHotelId(hotelId);
    }
}
