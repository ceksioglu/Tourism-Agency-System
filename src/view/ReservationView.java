package view;

import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Room;
import entity.User;
import view.Layout;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

// ReservationView.java
public class ReservationView extends Layout {
    private User currentUser;
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
        this.currentUser = currentUser;
        reservationManager = new ReservationManager();
        roomManager = new RoomManager();

        Helper.setupWindow(this, container, "Reservation", 500, 500);

        button_get_rooms.addActionListener(e -> loadRooms());
        button_calculate.addActionListener(e -> calculateTotalPrice());
        button_save.addActionListener(e -> saveReservation());
        button_exit.addActionListener(e -> dispose());
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
        BigDecimal totalPrice = adultPrice.multiply(BigDecimal.valueOf(adultCount)).add(childPrice.multiply(BigDecimal.valueOf(childCount)));
        field_total_price.setText(totalPrice.toString());
    }

    private void saveReservation() {
        int roomId = Integer.parseInt((String) Objects.requireNonNull(combo_room_id.getSelectedItem()));
        int userId = currentUser.getId(); // Use the current user's ID
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

        if (roomManager.checkRoomAvailability(roomId, startDate, endDate)) {
            reservationManager.addReservation(roomId, userId, startDate, endDate, adultCount, childCount, guestName, guestSurname, guestIdentityNumber, hotelId, guestPhone, totalPrice);
            roomManager.decreaseRoomStock(roomId, adultCount + childCount);
            JOptionPane.showMessageDialog(this, "Reservation saved successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Not enough rooms available.");
        }
    }

    public static void main(String[] args) {
        Helper.setNimbusLookAndFeel();
        User dummyUser = new User();
        dummyUser.setId(5); // Set a dummy user ID
        new ReservationView(dummyUser);
    }
}
