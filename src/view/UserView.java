package view;

import business.HotelManager;
import business.ReservationManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * UserView class provides an interface for users to manage hotels, rooms, reservations, and seasons.
 */
public class UserView extends Layout {
    private JPanel container;
    private JLabel label_welcome;
    private JButton button_exit;
    private JTabbedPane tabpane_main;
    private JPanel panel_hotel;
    private JTable table_hotel;
    private JScrollPane scroll_hotel;
    private JPanel panel_room;
    private JLabel label_filters;
    private JComboBox<String> combo_type_filter;
    private JComboBox<String> combo_facility_filter;
    private JLabel label_city;
    private JLabel label_pension_type;
    private JLabel label_facility;
    private JTable table_room;
    private JTextField field_hotel_name;
    private JTextField field_city;
    private JTextField field_start_date;
    private JTextField field_end_date;
    private JLabel label_hotel_name;
    private JLabel label_hotel_location;
    private JLabel label_hotel;
    private JLabel label_end_date;
    private JScrollPane scroll_room;
    private JPanel panel_reservation;
    private JLabel label_user;
    private JTable table_reservation;
    private JScrollPane scroll_reservation;
    private JPanel panel_season;
    private JTable table_season;
    private JTable table_facility_prop;
    private JLabel label_seasons;
    private JLabel label_facility_prop;
    private JScrollPane scroll_season;
    private JScrollPane scroll_facility_prop;
    private JTextField field_city_filter;
    private JButton button_clear;
    private JButton button_search;
    private JButton button_room_clear;
    private JButton button_room_search;
    private JButton button_create;
    private JTextField field_hotel_filter;
    private JLabel label_hotel_filter;
    private JButton button_refresh;
    private JButton button_refresh_season;

    private final HotelManager hotelManager;
    private final RoomManager roomManager;
    private final ReservationManager reservationManager;
    private final SeasonManager seasonManager;
    private DefaultTableModel hotelTableModel;
    private DefaultTableModel roomTableModel;
    private DefaultTableModel reservationTableModel;
    private DefaultTableModel seasonTableModel;
    private User currentUser;

    /**
     * Constructor for creating the UserView with a specified user.
     *
     * @param user The current user
     */
    public UserView(User user) {
        hotelManager = new HotelManager();
        roomManager = new RoomManager();
        reservationManager = new ReservationManager();
        seasonManager = new SeasonManager();

        this.currentUser = user;

        Helper.setupWindow(this, container, "User Panel", 1500, 800);
        label_welcome.setText("Welcome, " + currentUser.getUsername());

        button_exit.addActionListener(e -> System.exit(0));

        initializeTables();
        initializeListeners();
        initializeFilters();

        loadHotels();
        loadRooms();
        loadReservations();
        loadSeasons();
    }

    /**
     * Initializes the table models and sets the table properties.
     */
    private void initializeTables() {
        hotelTableModel = new DefaultTableModel(new String[]{"ID", "Name", "City", "Region", "Address", "Email", "Phone", "Stars"}, 0);
        table_hotel.setModel(hotelTableModel);
        TableColumnModel columnModelHotel = table_hotel.getColumnModel();
        columnModelHotel.getColumn(0).setPreferredWidth(5);
        this.table_hotel.getTableHeader().setReorderingAllowed(false);

        roomTableModel = new DefaultTableModel(new String[]{"ID", "Hotel Name", "City", "Season", "Pension Type", "Room Type", "Bed Count", "Size", "TV", "Minibar", "Game Console", "Safe", "Projector", "Adult Price", "Child Price", "Stock"}, 0);
        table_room.setModel(roomTableModel);
        TableColumnModel columnModelRoom = table_room.getColumnModel();
        columnModelRoom.getColumn(1).setPreferredWidth(200);
        this.table_room.getTableHeader().setReorderingAllowed(false);

        reservationTableModel = new DefaultTableModel(new String[]{"ID", "Room ID", "User ID", "Start Date", "End Date", "Adult Count", "Child Count", "Total Price", "Guest Name", "Guest Surname", "Guest Identity Number", "Hotel Name", "Guest Phone"}, 0);
        table_reservation.setModel(reservationTableModel);
        this.table_reservation.getTableHeader().setReorderingAllowed(false);

        seasonTableModel = new DefaultTableModel(new String[]{"ID", "Hotel Name", "Start Date", "End Date"}, 0);
        table_season.setModel(seasonTableModel);
        this.table_season.getTableHeader().setReorderingAllowed(false);
    }

    /**
     * Initializes the listeners for the table context menus and buttons.
     */
    private void initializeListeners() {
        // Add right-click menu for hotel table
        table_hotel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    showHotelContextMenu(e);
                }
            }
        });

        // Add right-click menu for room table
        table_room.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    showRoomContextMenu(e);
                }
            }
        });

        // Add right-click menu for reservation table
        table_reservation.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    showReservationContextMenu(e);
                }
            }
        });

        // Add right-click menu for season table
        table_season.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    showSeasonContextMenu(e);
                }
            }
        });

        // Add action listener for create button
        button_create.addActionListener(e -> openReservationView(null));

        // Add action listener for refresh button
        button_refresh.addActionListener(e -> loadReservations());

        // Add action listener for refresh season button
        button_refresh_season.addActionListener(e -> loadSeasons());
    }

    /**
     * Initializes the filters for searching hotels and rooms.
     */
    private void initializeFilters() {
        combo_type_filter.setModel(new DefaultComboBoxModel<>(getPensionTypesWithNone()));
        combo_facility_filter.setModel(new DefaultComboBoxModel<>(getFacilitiesWithNone()));

        field_city_filter.setColumns(10); // Limit the size of the city filter text field

        button_search.addActionListener(e -> filterHotels());
        button_clear.addActionListener(e -> clearHotelFilters());
        button_room_search.addActionListener(e -> filterRooms());
        button_room_clear.addActionListener(e -> clearRoomFilters());
    }

    /**
     * Gets the pension types with an additional "None" option.
     *
     * @return An array of pension type names including "None"
     */
    private String[] getPensionTypesWithNone() {
        Hotel.PensionType[] pensionTypes = Hotel.PensionType.values();
        List<String> typesWithNone = new ArrayList<>();
        typesWithNone.add("None");
        for (Hotel.PensionType type : pensionTypes) {
            typesWithNone.add(type.name());
        }
        return typesWithNone.toArray(new String[0]);
    }

    /**
     * Gets the facilities with an additional "None" option.
     *
     * @return An array of facility names including "None"
     */
    private String[] getFacilitiesWithNone() {
        Hotel.Facility[] facilities = Hotel.Facility.values();
        List<String> facilitiesWithNone = new ArrayList<>();
        facilitiesWithNone.add("None");
        for (Hotel.Facility facility : facilities) {
            facilitiesWithNone.add(facility.name());
        }
        return facilitiesWithNone.toArray(new String[0]);
    }

    /**
     * Filters hotels based on the specified criteria.
     */
    private void filterHotels() {
        String city = field_city_filter.getText().trim();
        String hotelName = field_hotel_filter.getText().trim();
        String pensionType = (String) combo_type_filter.getSelectedItem();
        String facility = (String) combo_facility_filter.getSelectedItem();

        List<Hotel> filteredHotels = hotelManager.getAllHotels().stream()
                .filter(hotel -> city.isEmpty() || hotel.getCity().equalsIgnoreCase(city))
                .filter(hotel -> hotelName.isEmpty() || hotel.getName().equalsIgnoreCase(hotelName))
                .filter(hotel -> "None".equals(pensionType) || hotel.getPensionTypes().contains(Hotel.PensionType.valueOf(pensionType)))
                .filter(hotel -> "None".equals(facility) || hotel.getFacilities().contains(Hotel.Facility.valueOf(facility)))
                .toList();

        hotelTableModel.setRowCount(0); // Clear existing data

        for (Hotel hotel : filteredHotels) {
            hotelTableModel.addRow(new Object[]{
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getCity(),
                    hotel.getRegion(),
                    hotel.getAddress(),
                    hotel.getEmail(),
                    hotel.getPhone(),
                    hotel.getStars()
            });
        }
    }

    /**
     * Filters rooms based on the specified criteria.
     */
    private void filterRooms() {
        String hotelName = field_hotel_name.getText().trim();
        String city = field_city.getText().trim();
        String startDate = field_start_date.getText().trim();
        String endDate = field_end_date.getText().trim();

        // Get all rooms and filter based on the specified criteria
        List<Room> filteredRooms = roomManager.getAllRooms().stream()
                // Filter rooms by hotel name. If hotelName is empty, the filter passes all rooms (no filtering).
                // Otherwise, it filters rooms where the hotel's name matches the provided hotelName (case-insensitive).
                .filter(room -> hotelName.isEmpty() || room.getHotelName().equalsIgnoreCase(hotelName))

                // Filter rooms by city. If city is empty, the filter passes all rooms (no filtering).
                // Otherwise, it filters rooms where the hotel's city matches the provided city (case-insensitive).
                .filter(room -> city.isEmpty() || room.getHotelCity().equalsIgnoreCase(city))

                // Filter rooms by start date. If startDate is empty, the filter passes all rooms (no filtering).
                // Otherwise, it filters rooms where the season's start date matches the provided startDate.
                // Note: room.getSeason() returns a string in the format "YYYY-MM-DD to YYYY-MM-DD".
                // The startsWith method checks if this string starts with the provided startDate.
                .filter(room -> startDate.isEmpty() || room.getSeason().startsWith(startDate))

                // Filter rooms by end date. If endDate is empty, the filter passes all rooms (no filtering).
                // Otherwise, it filters rooms where the season's end date matches the provided endDate.
                // Note: room.getSeason() returns a string in the format "YYYY-MM-DD to YYYY-MM-DD".
                // The endsWith method checks if this string ends with the provided endDate.
                .filter(room -> endDate.isEmpty() || room.getSeason().endsWith(endDate))

                // Collect the filtered stream into a list
                .toList();


        roomTableModel.setRowCount(0); // Clear existing data

        for (Room room : filteredRooms) {
            roomTableModel.addRow(new Object[]{
                    room.getId(),
                    room.getHotelName(),
                    room.getHotelCity(),
                    room.getSeason(),
                    room.getPensionType(),
                    room.getRoomType(),
                    room.getBedCount(),
                    room.getSize(),
                    room.isTv(),
                    room.isMinibar(),
                    room.isGameConsole(),
                    room.isSafe(),
                    room.isProjector(),
                    room.getAdultPrice(),
                    room.getChildPrice(),
                    room.getStock()
            });
        }
    }

    /**
     * Clears the hotel filters and reloads the hotels.
     */
    private void clearHotelFilters() {
        field_city_filter.setText("");
        field_hotel_filter.setText("");
        combo_type_filter.setSelectedIndex(0);
        combo_facility_filter.setSelectedIndex(0);
        loadHotels();
    }

    /**
     * Clears the room filters and reloads the rooms.
     */
    private void clearRoomFilters() {
        field_hotel_name.setText("");
        field_city.setText("");
        field_start_date.setText("");
        field_end_date.setText("");
        loadRooms();
    }

    /**
     * Shows the context menu for the hotel table.
     *
     * @param e The mouse event
     */
    private void showHotelContextMenu(MouseEvent e) {
        JPopupMenu contextMenu = new JPopupMenu();
        JMenuItem createItem = new JMenuItem("Create Hotel");
        JMenuItem updateItem = new JMenuItem("Update Hotel");
        JMenuItem deleteItem = new JMenuItem("Delete Hotel");

        createItem.addActionListener(evt -> openHotelView(null));
        updateItem.addActionListener(evt -> openHotelView(getSelectedHotel()));
        deleteItem.addActionListener(evt -> deleteSelectedHotel());

        contextMenu.add(createItem);
        contextMenu.add(updateItem);
        contextMenu.add(deleteItem);

        contextMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * Shows the context menu for the room table.
     *
     * @param e The mouse event
     */
    private void showRoomContextMenu(MouseEvent e) {
        JPopupMenu contextMenu = new JPopupMenu();
        JMenuItem createItem = new JMenuItem("Create Room");
        JMenuItem createReservationItem = new JMenuItem("Create New Reservation from Selected Room");
        JMenuItem updateItem = new JMenuItem("View / Edit Room");
        JMenuItem deleteItem = new JMenuItem("Delete Room");

        createItem.addActionListener(evt -> openRoomView(null));
        createReservationItem.addActionListener(evt -> openReservationViewFromRoom(getSelectedRoom()));
        updateItem.addActionListener(evt -> openRoomView(getSelectedRoom()));
        deleteItem.addActionListener(evt -> deleteSelectedRoom());

        contextMenu.add(createItem);
        contextMenu.add(createReservationItem);
        contextMenu.add(updateItem);
        contextMenu.add(deleteItem);

        contextMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * Opens the ReservationView for creating a new reservation from the selected room.
     *
     * @param room The selected room
     */
    private void openReservationViewFromRoom(Room room) {
        if (room != null) {
            Reservation reservation = new Reservation();
            reservation.setRoomId(room.getId());
            reservation.setHotelId(room.getHotelId());
            reservation.setHotelName(room.getHotelName());

            // Debug: Print room details
            System.out.println("Room ID: " + room.getId());
            System.out.println("Hotel ID: " + room.getHotelId());
            System.out.println("Hotel Name: " + room.getHotelName());
            System.out.println("Season ID: " + room.getSeasonId());

            // Retrieve the season data
            Season season = seasonManager.getSeasonById(room.getSeasonId());
            if (season != null) {
                reservation.setStartDate(season.getStartDate().toLocalDate());
                reservation.setEndDate(season.getEndDate().toLocalDate());

                // Debug: Print season details
                System.out.println("Season Start Date: " + season.getStartDate());
                System.out.println("Season End Date: " + season.getEndDate());
            } else {
                // Debug: Print if no season is found
                System.out.println("No season found for Season ID: " + room.getSeasonId());
            }

            new ReservationView(currentUser, reservation);
        }
    }

    /**
     * Shows the context menu for the reservation table.
     *
     * @param e The mouse event
     */
    private void showReservationContextMenu(MouseEvent e) {
        JPopupMenu contextMenu = new JPopupMenu();
        JMenuItem createItem = new JMenuItem("Create Reservation");
        JMenuItem updateItem = new JMenuItem("Update Reservation");
        JMenuItem deleteItem = new JMenuItem("Delete Reservation");

        createItem.addActionListener(evt -> openReservationView(null));
        updateItem.addActionListener(evt -> openReservationView(getSelectedReservation()));
        deleteItem.addActionListener(evt -> deleteSelectedReservation());

        contextMenu.add(createItem);
        contextMenu.add(updateItem);
        contextMenu.add(deleteItem);

        contextMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * Shows the context menu for the season table.
     *
     * @param e The mouse event
     */
    private void showSeasonContextMenu(MouseEvent e) {
        JPopupMenu contextMenu = new JPopupMenu();
        JMenuItem createItem = new JMenuItem("Create Season");
        JMenuItem updateItem = new JMenuItem("Update Season");
        JMenuItem deleteItem = new JMenuItem("Delete Season");
        JMenuItem createRoomItem = new JMenuItem("Create New Room from Season");

        createItem.addActionListener(evt -> openSeasonView(null));
        updateItem.addActionListener(evt -> openSeasonView(getSelectedSeason()));
        deleteItem.addActionListener(evt -> deleteSelectedSeason());
        createRoomItem.addActionListener(evt -> openRoomViewFromSeason(getSelectedSeason()));

        contextMenu.add(createItem);
        contextMenu.add(createRoomItem);
        contextMenu.add(updateItem);
        contextMenu.add(deleteItem);

        contextMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * Opens the RoomView for creating a new room from the selected season.
     *
     * @param season The selected season
     */
    private void openRoomViewFromSeason(Season season) {
        if (season != null) {
            Room room = new Room();
            room.setHotelId(season.getHotelId());
            room.setSeasonId(season.getId());
            room.setHotelName(season.getHotelName());
            room.setSeason(season.getStartDate().toString() + " to " + season.getEndDate().toString());
            room.setHotelCity(getHotelCityById(season.getHotelId()));
            new RoomView(room);
        }
    }

    /**
     * Gets the city of a hotel by its ID.
     *
     * @param hotelId The hotel ID
     * @return The city of the hotel
     */
    private String getHotelCityById(int hotelId) {
        Hotel hotel = hotelManager.getHotelById(hotelId);
        return hotel != null ? hotel.getCity() : "";
    }

    /**
     * Loads the hotels into the hotel table.
     */
    private void loadHotels() {
        List<Hotel> hotels = hotelManager.getAllHotels();
        hotelTableModel.setRowCount(0); // Clear existing data

        for (Hotel hotel : hotels) {
            hotelTableModel.addRow(new Object[]{
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getCity(),
                    hotel.getRegion(),
                    hotel.getAddress(),
                    hotel.getEmail(),
                    hotel.getPhone(),
                    hotel.getStars()
            });
        }
    }

    /**
     * Loads the rooms into the room table.
     */
    private void loadRooms() {
        List<Room> rooms = roomManager.getAllRooms();
        roomTableModel.setRowCount(0); // Clear existing data

        for (Room room : rooms) {
            roomTableModel.addRow(new Object[]{
                    room.getId(),
                    room.getHotelName(),
                    room.getHotelCity(),
                    room.getSeason(),
                    room.getPensionType(),
                    room.getRoomType(),
                    room.getBedCount(),
                    room.getSize(),
                    room.isTv(),
                    room.isMinibar(),
                    room.isGameConsole(),
                    room.isSafe(),
                    room.isProjector(),
                    room.getAdultPrice(),
                    room.getChildPrice(),
                    room.getStock()
            });
        }
    }

    /**
     * Loads the reservations into the reservation table.
     */
    private void loadReservations() {
        List<Reservation> reservations = reservationManager.getAllReservationsWithHotelName();
        reservationTableModel.setRowCount(0); // Clear existing data

        for (Reservation reservation : reservations) {
            reservationTableModel.addRow(new Object[]{
                    reservation.getId(),
                    reservation.getRoomId(),
                    reservation.getUserId(),
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getAdultCount(),
                    reservation.getChildCount(),
                    reservation.getTotalPrice(),
                    reservation.getGuestName(),
                    reservation.getGuestSurname(),
                    reservation.getGuestIdentityNumber(),
                    reservation.getHotelName(),
                    reservation.getGuestPhone()
            });
        }
    }

    /**
     * Loads the seasons into the season table.
     */
    private void loadSeasons() {
        List<Season> seasons = seasonManager.getAllSeasons();
        seasonTableModel.setRowCount(0); // Clear existing data

        for (Season season : seasons) {
            seasonTableModel.addRow(new Object[]{
                    season.getId(),
                    season.getHotelName(),
                    season.getStartDate(),
                    season.getEndDate()
            });
        }
    }

    /**
     * Opens the HotelView for creating or updating a hotel.
     *
     * @param hotel The hotel to be edited, or null to create a new hotel
     */
    private void openHotelView(Hotel hotel) {
        new HotelView(hotel);
    }

    /**
     * Opens the RoomView for creating or updating a room.
     *
     * @param room The room to be edited, or null to create a new room
     */
    private void openRoomView(Room room) {
        // Open RoomView for creating/updating a room
        new RoomView(room);
    }

    /**
     * Opens the ReservationView for creating or updating a reservation.
     *
     * @param reservation The reservation to be edited, or null to create a new reservation
     */
    private void openReservationView(Reservation reservation) {
        new ReservationView(currentUser, reservation);
    }

    /**
     * Opens the SeasonView for creating or updating a season.
     *
     * @param season The season to be edited, or null to create a new season
     */
    private void openSeasonView(Season season) {
        // Open SeasonView for creating/updating a season
        new SeasonView(season);
    }

    /**
     * Gets the selected hotel from the hotel table.
     *
     * @return The selected hotel, or null if no hotel is selected
     */
    private Hotel getSelectedHotel() {
        int selectedRow = table_hotel.getSelectedRow();
        if (selectedRow == -1) return null;
        int hotelId = (int) table_hotel.getValueAt(selectedRow, 0);
        return hotelManager.getHotelById(hotelId);
    }

    /**
     * Gets the selected room from the room table.
     *
     * @return The selected room, or null if no room is selected
     */
    private Room getSelectedRoom() {
        int selectedRow = table_room.getSelectedRow();
        if (selectedRow == -1) return null;
        int roomId = (int) table_room.getValueAt(selectedRow, 0);
        return roomManager.getRoomById(roomId);
    }

    /**
     * Gets the selected reservation from the reservation table.
     *
     * @return The selected reservation, or null if no reservation is selected
     */
    private Reservation getSelectedReservation() {
        int selectedRow = table_reservation.getSelectedRow();
        if (selectedRow == -1) return null;
        int reservationId = (int) table_reservation.getValueAt(selectedRow, 0);
        return reservationManager.getReservationById(reservationId);
    }

    /**
     * Gets the selected season from the season table.
     *
     * @return The selected season, or null if no season is selected
     */
    private Season getSelectedSeason() {
        int selectedRow = table_season.getSelectedRow();
        if (selectedRow == -1) return null;
        int seasonId = (int) table_season.getValueAt(selectedRow, 0);
        return seasonManager.getSeasonById(seasonId);
    }

    /**
     * Deletes the selected hotel.
     */
    private void deleteSelectedHotel() {
        Hotel hotel = getSelectedHotel();
        if (hotel != null) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this hotel?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                hotelManager.deleteHotel(hotel.getId());
                loadHotels();
            }
        }
    }

    /**
     * Deletes the selected room.
     */
    private void deleteSelectedRoom() {
        Room room = getSelectedRoom();
        if (room != null) {
            roomManager.deleteRoom(room.getId());
            loadRooms();
        }
    }

    /**
     * Deletes the selected reservation.
     */
    private void deleteSelectedReservation() {
        Reservation reservation = getSelectedReservation();
        if (reservation != null) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this reservation?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                reservationManager.deleteReservation(reservation.getId());
                roomManager.increaseRoomStock(reservation.getRoomId(), 1);
                loadReservations();
            }
        }
    }

    /**
     * Deletes the selected season.
     */
    private void deleteSelectedSeason() {
        Season season = getSelectedSeason();
        if (season != null) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this season?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                seasonManager.deleteSeason(season.getId());
                loadSeasons();
            }
        }
    }

    /**
     * Main method to launch the UserView.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Helper.setNimbusLookAndFeel();
        User tempUser = new User();
        tempUser.setUsername("personnel2");
        tempUser.setRole("PERSONNEL");
        tempUser.setId(4);
        new UserView(tempUser);
    }
}
