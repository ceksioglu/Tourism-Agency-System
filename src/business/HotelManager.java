package business;

import dao.HotelDAO;
import entity.Hotel;

import java.util.List;

public class HotelManager {
    private final HotelDAO hotelDAO;

    public HotelManager() {
        hotelDAO = new HotelDAO();
    }

    public List<Hotel> getAllHotels() {
        return hotelDAO.getAllHotels();
    }

    public Hotel getHotelById(int id) {
        return hotelDAO.getHotelById(id);
    }

    public void addHotel(Hotel hotel) {
        hotelDAO.addHotel(hotel);
    }

    public void updateHotel(Hotel hotel) {
        hotelDAO.updateHotel(hotel);
    }

    public void deleteHotel(int id) {
        hotelDAO.deleteHotel(id);
    }
}
