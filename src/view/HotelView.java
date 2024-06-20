package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;
import entity.Hotel.Facility;
import entity.Hotel.PensionType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The HotelView class provides the user interface for managing hotel entries.
 * It allows the user to create new hotels or update existing ones.
 * The user can input hotel details such as name, city, region, address, email, phone, stars,
 * pension types, and facilities.
 */
public class HotelView extends JFrame {
    private JPanel container;
    private JLabel label_hotelview_banner;
    private JLabel label_hotel_name;
    private JTextField field_hotel_name;
    private JLabel label_city;
    private JTextField field_city;
    private JLabel label_region;
    private JTextField field_region;
    private JLabel label_stars;
    private JTextField field_stars;
    private JLabel label_pension;
    private JLabel label_facilities;
    private JLabel label_adress;
    private JTextField field_adress;
    private JButton button_exit;
    private JButton button_save;
    private JLabel label_email;
    private JTextField field_email;
    private JLabel label_phone;
    private JTextField field_phone;
    private JScrollPane scroll_pension;
    private JList<PensionType> list_pension;
    private JScrollPane scroll_facilities;
    private JList<Facility> list_facilities;

    private HotelManager hotelManager;
    private Hotel currentHotel;

    /**
     * Constructs a new HotelView.
     *
     * @param hotel the hotel to be edited, or null if creating a new hotel.
     */
    public HotelView(Hotel hotel) {
        hotelManager = new HotelManager();
        currentHotel = hotel;

        Helper.setupWindow(this, container, "Hotel Management", 600, 400);

        list_pension.setListData(PensionType.values());
        list_facilities.setListData(Facility.values());

        if (currentHotel != null) {
            loadHotelData();
        }

        button_save.addActionListener(e -> saveHotel());

        button_exit.addActionListener(e -> dispose());
    }

    /**
     * Loads the current hotel data into the form fields.
     */
    private void loadHotelData() {
        field_hotel_name.setText(currentHotel.getName());
        field_city.setText(currentHotel.getCity());
        field_region.setText(currentHotel.getRegion());
        field_adress.setText(currentHotel.getAddress());
        field_email.setText(currentHotel.getEmail());
        field_phone.setText(currentHotel.getPhone());
        field_stars.setText(String.valueOf(currentHotel.getStars()));

        list_pension.setSelectedIndices(getSelectedIndices(list_pension, currentHotel.getPensionTypes()));
        list_facilities.setSelectedIndices(getSelectedIndices(list_facilities, currentHotel.getFacilities()));
    }

    /**
     * Saves the hotel data from the form fields.
     * If the current hotel is null, a new hotel is created.
     * Otherwise, the existing hotel is updated.
     */
    private void saveHotel() {
        String name = field_hotel_name.getText().trim();
        String city = field_city.getText().trim();
        String region = field_region.getText().trim();
        String address = field_adress.getText().trim();
        String email = field_email.getText().trim();
        String phone = field_phone.getText().trim();
        int stars;

        try {
            stars = Integer.parseInt(field_stars.getText().trim());
        } catch (NumberFormatException e) {
            Helper.showMessage(this, "Stars must be a number.");
            return;
        }

        List<PensionType> selectedPensionTypes = list_pension.getSelectedValuesList();
        List<Facility> selectedFacilities = list_facilities.getSelectedValuesList();

        if (name.isEmpty() || city.isEmpty() || region.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty() || selectedPensionTypes.isEmpty() || selectedFacilities.isEmpty()) {
            Helper.showMessage(this, "Please fill in all fields and select at least one pension type and one facility.");
            return;
        }

        if (currentHotel == null) {
            currentHotel = new Hotel(0, name, city, region, address, email, phone, stars, selectedFacilities, selectedPensionTypes);
            hotelManager.addHotel(currentHotel);
        } else {
            currentHotel.setName(name);
            currentHotel.setCity(city);
            currentHotel.setRegion(region);
            currentHotel.setAddress(address);
            currentHotel.setEmail(email);
            currentHotel.setPhone(phone);
            currentHotel.setStars(stars);
            currentHotel.setFacilities(selectedFacilities);
            currentHotel.setPensionTypes(selectedPensionTypes);
            hotelManager.updateHotel(currentHotel);
        }

        Helper.showMessage(this, "Hotel saved successfully.");
        dispose();
    }

    /**
     * Gets the selected indices of the items in the JList.
     *
     * @param list the JList component.
     * @param selectedItems the list of selected items.
     * @param <T> the type of the items in the JList.
     * @return an array of selected indices.
     */
    private <T> int[] getSelectedIndices(JList<T> list, List<T> selectedItems) {
        ListModel<T> model = list.getModel();
        int[] indices = new int[selectedItems.size()];
        for (int i = 0; i < selectedItems.size(); i++) {
            for (int j = 0; j < model.getSize(); j++) {
                if (model.getElementAt(j).equals(selectedItems.get(i))) {
                    indices[i] = j;
                    break;
                }
            }
        }
        return indices;
    }
}
