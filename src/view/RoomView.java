package view;

import business.HotelManager;
import business.RoomManager;
import core.Helper;
import entity.Hotel;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The RoomView class provides an interface for managing rooms.
 * Users can create, update, and view room details.
 */
public class RoomView extends Layout {
    private JPanel container;
    private JLabel label_banner;
    private JLabel label_hotel_name;
    private JLabel label_city;
    private JTextField field_hotel_name;
    private JTextField field_city;
    private JLabel label_start_date;
    private JLabel label_end_date;
    private JTextField field_start_date;
    private JTextField field_end_date;
    private JLabel label_pension;
    private JLabel label_room_type;
    private JComboBox<Room.RoomType> combo_room_type;
    private JComboBox<String> combo_pension_type;
    private JLabel label_bed;
    private JLabel label_room_size;
    private JTextField field_bed;
    private JTextField field_room_size;
    private JLabel label_adult_price;
    private JLabel field_child_price;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label_room_amenities;
    private JCheckBox checkbox_tv;
    private JCheckBox checkbox_minibar;
    private JCheckBox checkbox_gameconsole;
    private JCheckBox checkbox_projector;
    private JCheckBox checkbox_safe;
    private JButton button_save;
    private JButton button_exit;
    private JTextField field_stock;
    private JLabel label_stock;

    private final RoomManager roomManager;
    private final HotelManager hotelManager;

    private Room room;

    /**
     * Constructor for creating a RoomView with a specified room.
     *
     * @param room The room to be managed
     */
    public RoomView(Room room) {
        this.room = room != null ? room : new Room();
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();

        Helper.setupWindow(this, container, "Room Management", 500, 600);

        label_banner.setText(room == null ? "Add Room" : "Edit Room");

        button_exit.addActionListener(e -> dispose());

        button_save.addActionListener(e -> saveRoom());

        initializeComboBoxes();
        populateRoomData();
    }

    /**
     * Initializes the combo boxes with room types and pension types.
     */
    private void initializeComboBoxes() {
        combo_room_type.setModel(new DefaultComboBoxModel<>(Room.RoomType.values()));
        combo_pension_type.setModel(new DefaultComboBoxModel<>(getPensionTypes()));
    }

    /**
     * Retrieves the pension types as an array of strings.
     *
     * @return An array of pension type names
     */
    private String[] getPensionTypes() {
        Hotel.PensionType[] pensionTypes = Hotel.PensionType.values();
        String[] pensionTypeNames = new String[pensionTypes.length];
        for (int i = 0; i < pensionTypes.length; i++) {
            pensionTypeNames[i] = pensionTypes[i].name();
        }
        return pensionTypeNames;
    }

    /**
     * Populates the room data fields with the information from the given room.
     */
    private void populateRoomData() {
        if (room.getId() != 0 || room.getHotelId() != 0) {
            field_hotel_name.setText(room.getHotelName());
            field_city.setText(room.getHotelCity()); // Set the city name
            field_start_date.setText(room.getSeason().split(" to ")[0]);
            field_end_date.setText(room.getSeason().split(" to ")[1]);
            combo_pension_type.setSelectedItem(room.getPensionType());
            combo_room_type.setSelectedItem(room.getRoomType());
            field_bed.setText(String.valueOf(room.getBedCount()));
            field_room_size.setText(String.valueOf(room.getSize()));
            textField1.setText(room.getAdultPrice() != null ? room.getAdultPrice().toString() : "");
            textField2.setText(room.getChildPrice() != null ? room.getChildPrice().toString() : "");
            field_stock.setText(String.valueOf(room.getStock()));
            checkbox_tv.setSelected(room.isTv());
            checkbox_minibar.setSelected(room.isMinibar());
            checkbox_gameconsole.setSelected(room.isGameConsole());
            checkbox_projector.setSelected(room.isProjector());
            checkbox_safe.setSelected(room.isSafe());
        }
    }

    /**
     * Saves the room. If it is a new room, it will be created. Otherwise, it will be updated.
     */
    private void saveRoom() {
        if (Helper.checkAndShowEmptyFields(this, field_hotel_name, field_city, field_start_date, field_end_date, field_bed, field_room_size, textField1, textField2, field_stock)) {
            return;
        }

        String hotelName = field_hotel_name.getText();
        Hotel hotel = hotelManager.getHotelByName(hotelName);

        if (hotel == null) {
            Helper.showMessage(this, "Hotel not found. Please enter a valid hotel name.");
            return;
        }

        String selectedPensionType = (String) combo_pension_type.getSelectedItem();
        List<Hotel.PensionType> validPensionTypes = hotelManager.getPensionTypesByHotelId(hotel.getId());

        if (!isValidPensionType(selectedPensionType, validPensionTypes)) {
            Helper.showMessage(this, "Invalid pension type for the selected hotel. Please select a valid pension type.");
            return;
        }

        int hotelId = hotel.getId();
        room.setHotelId(hotelId);
        room.setSeasonId(getSeasonIdByDate(field_start_date.getText(), field_end_date.getText(), hotelId));
        room.setPensionType(selectedPensionType);
        room.setPensionTypeId(getPensionTypeIdByName(hotelId, selectedPensionType));

        room.setRoomType((Room.RoomType) combo_room_type.getSelectedItem());
        room.setBedCount(Integer.parseInt(field_bed.getText()));
        room.setSize(Integer.parseInt(field_room_size.getText()));
        room.setAdultPrice(new BigDecimal(textField1.getText()));
        room.setChildPrice(new BigDecimal(textField2.getText()));
        room.setStock(Integer.parseInt(field_stock.getText()));
        room.setTv(checkbox_tv.isSelected());
        room.setMinibar(checkbox_minibar.isSelected());
        room.setGameConsole(checkbox_gameconsole.isSelected());
        room.setSafe(checkbox_safe.isSelected());
        room.setProjector(checkbox_projector.isSelected());

        if (room.getId() == 0) {
            roomManager.addRoom(room);
        } else {
            roomManager.updateRoom(room);
        }
        dispose();
    }

    /**
     * Checks if the selected pension type is valid for the given hotel.
     *
     * @param selectedPensionType The selected pension type
     * @param validPensionTypes   The list of valid pension types for the hotel
     * @return True if the selected pension type is valid, otherwise false
     */
    private boolean isValidPensionType(String selectedPensionType, List<Hotel.PensionType> validPensionTypes) {
        for (Hotel.PensionType type : validPensionTypes) {
            if (type.name().equals(selectedPensionType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the season ID based on the start and end dates for a given hotel.
     *
     * @param startDate The start date of the season
     * @param endDate   The end date of the season
     * @param hotelId   The ID of the hotel
     * @return The season ID
     */
    private int getSeasonIdByDate(String startDate, String endDate, int hotelId) {
        List<Season> seasons = hotelManager.getSeasonsByHotelId(hotelId);

        for (Season season : seasons) {
            if (season.getStartDate().toString().equals(startDate) && season.getEndDate().toString().equals(endDate)) {
                return season.getId();
            }
        }
        return 1; // Default to 1
    }

    /**
     * Retrieves the pension type ID based on the name for a given hotel.
     *
     * @param hotelId         The ID of the hotel
     * @param pensionTypeName The name of the pension type
     * @return The pension type ID
     */
    private int getPensionTypeIdByName(int hotelId, String pensionTypeName) {
        List<Hotel.PensionType> pensionTypes = hotelManager.getPensionTypesByHotelId(hotelId);

        for (Hotel.PensionType type : pensionTypes) {
            if (type.name().equals(pensionTypeName)) {
                return type.ordinal() + 1; // Assume ordinal + 1 maps to actual DB ID
            }
        }
        return 1; // Default to 1
    }
}
