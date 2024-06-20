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

    private ReservationManager reservationManager;
    private RoomManager roomManager;

    public ReservationView(User currentUser) {
        this(currentUser, null);
    }

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

        populateReservationData();
    }

    private void loadRooms() {
        int hotelId = Integer.parseInt(field_hotel_id.getText());
        List<Room> rooms = roomManager.getRoomsByHotelId(hotelId);
        combo_room_id.removeAllItems();
        for (Room room : rooms) {
            combo_room_id.addItem(String.valueOf(room.getId()));
        }
    }

    private void calculateTotalPrice() {
        int roomId = Integer.parseInt((String) Objects.requireNonNull(combo_room_id.getSelectedItem()));
        Room room = roomManager.getRoomById(roomId);
        int adultCount = Integer.parseInt(field_adult_count.getText());
        int childCount = Integer.parseInt(field_children_count.getText());
        BigDecimal adultPrice = room.getAdultPrice();
        BigDecimal childPrice = room.getChildPrice();
        BigDecimal totalPrice = adultPrice.multiply(BigDecimal.valueOf(adultCount))
                .add(childPrice.multiply(BigDecimal.valueOf(childCount)))
                .multiply(BigDecimal.valueOf(calculateNumberOfDays()));
        field_total_price.setText(totalPrice.toString());
    }

    private long calculateNumberOfDays() {
        LocalDate startDate = LocalDate.parse(field_start_date.getText(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(field_end_date.getText(), DateTimeFormatter.ISO_DATE);
        return startDate.datesUntil(endDate).count();
    }

    private void saveReservation() {
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
                reservation.getHotelName() // Assuming this is set somewhere else in the UI
        );

        if (reservation.getId() == 0) {
            reservationManager.addReservation(newReservation);
        } else {
            reservationManager.updateReservation(newReservation);
        }

        roomManager.decreaseRoomStock(roomId, 1);
        JOptionPane.showMessageDialog(this, "Reservation saved successfully!");
        dispose();
    }

    private void populateReservationData() {
        if (reservation.getId() != 0) {
            combo_room_id.setSelectedItem(String.valueOf(reservation.getRoomId()));
            field_start_date.setText(reservation.getStartDate().toString());
            field_end_date.setText(reservation.getEndDate().toString());
            field_adult_count.setText(String.valueOf(reservation.getAdultCount()));
            field_children_count.setText(String.valueOf(reservation.getChildCount()));
            field_total_price.setText(reservation.getTotalPrice().toString());
            field_name.setText(reservation.getGuestName());
            field_surname.setText(reservation.getGuestSurname());
            field_guest_id.setText(reservation.getGuestIdentityNumber());
            field_hotel_id.setText(String.valueOf(reservation.getHotelId()));
            field_phone.setText(reservation.getGuestPhone());
        } else {
            // If it's a new reservation, populate fields from the reservation object
            if (reservation != null) {
                field_hotel_id.setText(String.valueOf(reservation.getHotelId()));
                field_start_date.setText(reservation.getStartDate() != null ? reservation.getStartDate().toString() : "");
                field_end_date.setText(reservation.getEndDate() != null ? reservation.getEndDate().toString() : "");
            }
        }
    }

    public static void main(String[] args) {
        Helper.setNimbusLookAndFeel();
        User dummyUser = new User();
        dummyUser.setId(5); // Set a dummy user ID
        new ReservationView(dummyUser);
    }
}
