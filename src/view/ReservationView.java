package view;

import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Reservation;
import entity.Room;
import entity.User;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * The ReservationView class provides an interface for managing reservations.
 * Users can create, update, and view reservation details.
 */
public class ReservationView extends Layout {
    private User currentUser;
    private Reservation reservation;
    private JPanel container;
    private JLabel field_banner;
    private JLabel label_name;
    private JLabel label_surname;
    private JLabel label_start_date;
    private JLabel label_end_date;
    private JLabel label_hotel_id;
    private JLabel label_children_count;
    private JLabel label_adult_count;
    private JLabel label_room_id;
    private JLabel label_phone;
    private JLabel label_total;
    private JLabel label_guest_id;
    private JTextField field_name;
    private JTextField field_surname;
    private JTextField field_start_date;
    private JTextField field_end_date;
    private JTextField field_hotel_id;
    private JButton button_get_rooms;
    private JButton button_calculate;
    private JButton button_save;
    private JButton button_exit;
    private JTextField field_children_count;
    private JTextField field_adult_count;
    private JTextField field_total_price;
    private JTextField field_guest_id;
    private JTextField field_phone;
    private JComboBox<String> combo_room_id;

    private final ReservationManager reservationManager;
    private final RoomManager roomManager;

    /**
     * Constructor for creating a new ReservationView with the current user.
     *
     * @param currentUser The current logged-in user
     */
    public ReservationView(User currentUser) {
        this(currentUser, null);
    }

    /**
     * Constructor for creating a ReservationView with the current user and an existing reservation.
     *
     * @param currentUser The current logged-in user
     * @param reservation The reservation to be managed
     */
    public ReservationView(User currentUser, Reservation reservation) {
        this.currentUser = currentUser;
        this.reservation = reservation != null ? reservation : new Reservation();
        reservationManager = new ReservationManager();
        roomManager = new RoomManager();

        Helper.setupWindow(this, container, "Reservation", 500, 500);

        button_get_rooms.addActionListener(e -> loadRooms());
        button_calculate.addActionListener(e -> calculateTotalPrice());
        button_save.addActionListener(e -> saveReservation());
        button_exit.addActionListener(e -> dispose());

        if (reservation != null) {
            populateReservationData();
        }
    }

    /**
     * Loads the available rooms for the hotel specified by the hotel ID.
     */
    private void loadRooms() {
        try {
            int hotelId = Integer.parseInt(field_hotel_id.getText());
            List<Room> rooms = roomManager.getRoomsByHotelId(hotelId);
            combo_room_id.removeAllItems();
            for (Room room : rooms) {
                combo_room_id.addItem(String.valueOf(room.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the total price for the reservation based on the room price, number of guests, and duration of stay.
     */
    private void calculateTotalPrice() {
        try {
            int roomId = Integer.parseInt((String) Objects.requireNonNull(combo_room_id.getSelectedItem()));
            Room room = roomManager.getRoomById(roomId);
            int adultCount = Integer.parseInt(field_adult_count.getText());
            int childCount = Integer.parseInt(field_children_count.getText());
            BigDecimal adultPrice = room.getAdultPrice();
            BigDecimal childPrice = room.getChildPrice();
            long daysBetween = LocalDate.parse(field_start_date.getText(), DateTimeFormatter.ISO_DATE)
                    .until(LocalDate.parse(field_end_date.getText(), DateTimeFormatter.ISO_DATE))
                    .getDays();
            BigDecimal totalPrice = (adultPrice.multiply(BigDecimal.valueOf(adultCount))
                    .add(childPrice.multiply(BigDecimal.valueOf(childCount))))
                    .multiply(BigDecimal.valueOf(daysBetween));
            field_total_price.setText(totalPrice.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the reservation. If it is a new reservation, it will be created. Otherwise, it will be updated.
     */
    private void saveReservation() {
        try {
            int roomId = Integer.parseInt((String) Objects.requireNonNull(combo_room_id.getSelectedItem()));
            int userId = currentUser.getId();
            LocalDate startDate = LocalDate.parse(field_start_date.getText(), DateTimeFormatter.ISO_DATE);
            LocalDate endDate = LocalDate.parse(field_end_date.getText(), DateTimeFormatter.ISO_DATE);
            int adultCount = Integer.parseInt(field_adult_count.getText());
            int childCount = Integer.parseInt(field_children_count.getText());
            String guestName = field_name.getText();
            String guestSurname = field_surname.getText();
            String guestIdentityNumber = field_guest_id.getText();
            int hotelId = Integer.parseInt(field_hotel_id.getText());
            String guestPhone = field_phone.getText();
            BigDecimal totalPrice = new BigDecimal(field_total_price.getText());

            // Check room availability
            if (!roomManager.checkBedCount(roomId, adultCount, childCount)) {
                JOptionPane.showMessageDialog(this, "The total number of guests exceeds the bed count for the selected room.", "Room Unavailable", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Reservation newReservation = new Reservation(
                    reservation != null ? reservation.getId() : 0,
                    roomId,
                    userId,
                    startDate,
                    endDate,
                    adultCount,
                    childCount,
                    totalPrice,
                    guestName,
                    guestSurname,
                    guestIdentityNumber,
                    hotelId,
                    guestPhone,
                    reservation != null ? reservation.getHotelName() : "Default Hotel" // Default hotel name if not set
            );

            if (reservation.getId() == 0) {
                System.out.println("Executing insert query: " + newReservation);
                reservationManager.addReservation(newReservation);
            } else {
                System.out.println("Executing update query: " + newReservation);
                reservationManager.updateReservation(newReservation);
            }

            JOptionPane.showMessageDialog(this, "Reservation saved successfully!");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving reservation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Populates the reservation data fields with the information from the given reservation.
     */
    private void populateReservationData() {
        try {
            field_name.setText(reservation.getGuestName());
            field_surname.setText(reservation.getGuestSurname());
            field_start_date.setText(reservation.getStartDate().toString());
            field_end_date.setText(reservation.getEndDate().toString());
            field_hotel_id.setText(String.valueOf(reservation.getHotelId()));
            field_children_count.setText(String.valueOf(reservation.getChildCount()));
            field_adult_count.setText(String.valueOf(reservation.getAdultCount()));
            field_guest_id.setText(reservation.getGuestIdentityNumber());
            field_phone.setText(reservation.getGuestPhone());
            field_total_price.setText(reservation.getTotalPrice() != null ? reservation.getTotalPrice().toString() : "");
            button_get_rooms.doClick(); // Automatically load the rooms for the hotel
            combo_room_id.setSelectedItem(String.valueOf(reservation.getRoomId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main entry point of the application. Sets the Nimbus look and feel and starts the ReservationView.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Helper.setNimbusLookAndFeel();
        User dummyUser = new User();
        dummyUser.setId(5); // Set a dummy user ID
        new ReservationView(dummyUser);
    }
}
