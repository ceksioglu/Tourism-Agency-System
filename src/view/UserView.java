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
import java.util.List;
import java.util.stream.Collectors;

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
    private JComboBox<Hotel.PensionType> combo_type_filter;
    private JComboBox<Hotel.Facility> combo_facility_filter;
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

    private HotelManager hotelManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;
    private SeasonManager seasonManager;
    private DefaultTableModel hotelTableModel;
    private DefaultTableModel roomTableModel;
    private DefaultTableModel reservationTableModel;
    private DefaultTableModel seasonTableModel;
    private DefaultTableModel facilityTableModel;
    private User currentUser;

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

    private void initializeTables() {
        hotelTableModel = new DefaultTableModel(new String[]{"ID", "Name", "City", "Region", "Address", "Email", "Phone", "Stars"}, 0);
        table_hotel.setModel(hotelTableModel);
        TableColumnModel columnModelHotel = table_hotel.getColumnModel();
        columnModelHotel.getColumn(0).setPreferredWidth(2);
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
    }

    private void initializeFilters() {
        combo_type_filter.setModel(new DefaultComboBoxModel<>(Hotel.PensionType.values()));
        combo_facility_filter.setModel(new DefaultComboBoxModel<>(Hotel.Facility.values()));

        field_city_filter.setColumns(10); // Limit the size of the city filter text field

        button_search.addActionListener(e -> filterHotels());
        button_clear.addActionListener(e -> clearHotelFilters());
        button_room_search.addActionListener(e -> filterRooms());
        button_room_clear.addActionListener(e -> clearRoomFilters());
    }

    private void filterHotels() {
        String city = field_city_filter.getText().trim();
        Hotel.PensionType pensionType = (Hotel.PensionType) combo_type_filter.getSelectedItem();
        Hotel.Facility facility = (Hotel.Facility) combo_facility_filter.getSelectedItem();

        List<Hotel> filteredHotels = hotelManager.getAllHotels().stream()
                .filter(hotel -> city.isEmpty() || hotel.getCity().equalsIgnoreCase(city))
                .filter(hotel -> pensionType == null || hotel.getPensionTypes().contains(pensionType))
                .filter(hotel -> facility == null || hotel.getFacilities().contains(facility))
                .collect(Collectors.toList());

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

    private void filterRooms() {
        String hotelName = field_hotel_name.getText().trim();
        String city = field_city.getText().trim();
        String startDate = field_start_date.getText().trim();
        String endDate = field_end_date.getText().trim();

        List<Room> filteredRooms = roomManager.getAllRooms().stream()
                .filter(room -> hotelName.isEmpty() || room.getHotelName().equalsIgnoreCase(hotelName))
                .filter(room -> city.isEmpty() || room.getHotelCity().equalsIgnoreCase(city))
                .filter(room -> startDate.isEmpty() || room.getSeason().startsWith(startDate))
                .filter(room -> endDate.isEmpty() || room.getSeason().endsWith(endDate))
                .collect(Collectors.toList());

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

    private void clearHotelFilters() {
        field_city_filter.setText("");
        combo_type_filter.setSelectedIndex(0);
        combo_facility_filter.setSelectedIndex(0);
        loadHotels();
    }

    private void clearRoomFilters() {
        field_hotel_name.setText("");
        field_city.setText("");
        field_start_date.setText("");
        field_end_date.setText("");
        loadRooms();
    }

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

    private void showRoomContextMenu(MouseEvent e) {
        JPopupMenu contextMenu = new JPopupMenu();
        JMenuItem createItem = new JMenuItem("Create Room");
        JMenuItem updateItem = new JMenuItem("View / Edit Room");
        JMenuItem deleteItem = new JMenuItem("Delete Room");

        createItem.addActionListener(evt -> openRoomView(null));
        updateItem.addActionListener(evt -> openRoomView(getSelectedRoom()));
        deleteItem.addActionListener(evt -> deleteSelectedRoom());

        contextMenu.add(createItem);
        contextMenu.add(updateItem);
        contextMenu.add(deleteItem);

        contextMenu.show(e.getComponent(), e.getX(), e.getY());
    }

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

    private void showSeasonContextMenu(MouseEvent e) {
        JPopupMenu contextMenu = new JPopupMenu();
        JMenuItem createItem = new JMenuItem("Create Season");
        JMenuItem updateItem = new JMenuItem("Update Season");
        JMenuItem deleteItem = new JMenuItem("Delete Season");

        createItem.addActionListener(evt -> openSeasonView(null));
        updateItem.addActionListener(evt -> openSeasonView(getSelectedSeason()));
        deleteItem.addActionListener(evt -> deleteSelectedSeason());

        contextMenu.add(createItem);
        contextMenu.add(updateItem);
        contextMenu.add(deleteItem);

        contextMenu.show(e.getComponent(), e.getX(), e.getY());
    }

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

    // Methods to open respective views for CRUD operations
    private void openHotelView(Hotel hotel) {
        new HotelView(hotel);
    }

    private void openRoomView(Room room) {
        // Open RoomView for creating/updating a room
        new RoomView(room);
    }

    private void openReservationView(Reservation reservation) {
        new ReservationView(currentUser);
    }

    private void openSeasonView(Season season) {
        // Open SeasonView for creating/updating a season
        new SeasonView(season);
    }

    // Methods to get selected entities from tables
    private Hotel getSelectedHotel() {
        int selectedRow = table_hotel.getSelectedRow();
        if (selectedRow == -1) return null;
        int hotelId = (int) table_hotel.getValueAt(selectedRow, 0);
        return hotelManager.getHotelById(hotelId);
    }

    private Room getSelectedRoom() {
        int selectedRow = table_room.getSelectedRow();
        if (selectedRow == -1) return null;
        int roomId = (int) table_room.getValueAt(selectedRow, 0);
        return roomManager.getRoomById(roomId);
    }

    private Reservation getSelectedReservation() {
        int selectedRow = table_reservation.getSelectedRow();
        if (selectedRow == -1) return null;
        int reservationId = (int) table_reservation.getValueAt(selectedRow, 0);
        return reservationManager.getReservationById(reservationId);
    }

    private Season getSelectedSeason() {
        int selectedRow = table_season.getSelectedRow();
        if (selectedRow == -1) return null;
        int seasonId = (int) table_season.getValueAt(selectedRow, 0);
        return seasonManager.getSeasonById(seasonId);
    }

    // Methods to delete selected entities
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

    private void deleteSelectedRoom() {
        Room room = getSelectedRoom();
        if (room != null) {
            roomManager.deleteRoom(room.getId());
            loadRooms();
        }
    }

    private void deleteSelectedReservation() {
        Reservation reservation = getSelectedReservation();
        if (reservation != null) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this reservation?", "Confirm Deletion", JOptionPane.YES_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                reservationManager.deleteReservation(reservation.getId());
                roomManager.increaseRoomStock(reservation.getRoomId(), reservation.getAdultCount() + reservation.getChildCount());
                loadReservations();
            }
        }
    }

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

    // Main method to launch the UserView
    public static void main(String[] args) {
        Helper.setNimbusLookAndFeel();
        User tempUser = new User();
        tempUser.setUsername("personnel2");
        tempUser.setRole("PERSONNEL");
        new UserView(tempUser);
    }
}
