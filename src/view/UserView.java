package view;

import business.HotelManager;
import business.ReservationManager;
import business.SeasonManager;
import business.PensionTypeManager;
import business.RoomManager;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Season;
import entity.PensionType;
import entity.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * UserView sınıfı, kullanıcıların otel ve rezervasyon işlemlerini yönetmesini sağlar.
 * Bu sınıf, otelleri, sezonları, pansiyon tiplerini, odaları ve rezervasyonları görüntülemek
 * için çeşitli tablolar içerir. Kullanıcı, bu bileşenler aracılığıyla otel ve oda bilgilerini
 * görüntüleyebilir, rezervasyon yapabilir ve mevcut rezervasyonları inceleyebilir.
 */
public class UserView extends Layout {
    private JPanel container;
    private JLabel label_banner;  // Kullanıcıya hoş geldiniz mesajı gösteren etiket
    private JTable table_hotels;  // Otel bilgilerini gösteren tablo
    private JTable table_seasons;  // Sezon bilgilerini gösteren tablo
    private JTable table_pension_type;  // Pansiyon tipi bilgilerini gösteren tablo
    private JTable table_rooms;  // Oda bilgilerini gösteren tablo
    private JTable table_reservations;  // Rezervasyon bilgilerini gösteren tablo
    private JScrollPane scroll_hotels;  // Otel tablosu için kaydırma alanı
    private JScrollPane scroll_seasons;  // Sezon tablosu için kaydırma alanı
    private JScrollPane scroll_pension_type;  // Pansiyon tipi tablosu için kaydırma alanı
    private JScrollPane scroll_rooms;  // Oda tablosu için kaydırma alanı
    private JScrollPane scroll_reservations;  // Rezervasyon tablosu için kaydırma alanı
    private JTabbedPane tabbed_pane_main;  // Ana sekme paneli
    private JPanel panel_hotels;  // Otel bilgilerini içeren panel
    private JPanel panel_seasons;  // Sezon bilgilerini içeren panel
    private JPanel panel_pension_type;  // Pansiyon tipi bilgilerini içeren panel
    private JPanel panel_rooms;  // Oda bilgilerini içeren panel
    private JPanel panel_reservations;  // Rezervasyon bilgilerini içeren panel
    private JPanel panel_tabspace;  // Sekme alanını içeren panel
    private JButton button_exit;  // Çıkış butonu

    private HotelManager hotelManager;  // Otel verilerini yöneten sınıf
    private SeasonManager seasonManager;  // Sezon verilerini yöneten sınıf
    private PensionTypeManager pensionTypeManager;  // Pansiyon tipi verilerini yöneten sınıf
    private RoomManager roomManager;  // Oda verilerini yöneten sınıf
    private ReservationManager reservationManager;  // Rezervasyon verilerini yöneten sınıf
    private DefaultTableModel hotelTableModel;  // Otel tablosu modelini yöneten sınıf
    private DefaultTableModel seasonTableModel;  // Sezon tablosu modelini yöneten sınıf
    private DefaultTableModel pensionTypeTableModel;  // Pansiyon tipi tablosu modelini yöneten sınıf
    private DefaultTableModel roomTableModel;  // Oda tablosu modelini yöneten sınıf
    private DefaultTableModel reservationTableModel;  // Rezervasyon tablosu modelini yöneten sınıf

    /**
     * UserView sınıfı, kullanıcı işlemleri arayüzünü başlatır ve gerekli bileşenleri ayarlar.
     * Kullanıcı adı parametresi ile birlikte kullanıcıya hoş geldiniz mesajı gösterilir.
     *
     * @param username Giriş yapan kullanıcının kullanıcı adı
     */
    public UserView(String username) {
        hotelManager = new HotelManager();
        seasonManager = new SeasonManager();
        pensionTypeManager = new PensionTypeManager();
        roomManager = new RoomManager();
        reservationManager = new ReservationManager();
        Helper.setupWindow(this, container, "User Panel", 800, 600);

        hotelTableModel = new DefaultTableModel();
        table_hotels.setModel(hotelTableModel);
        hotelTableModel.addColumn("ID");
        hotelTableModel.addColumn("Name");
        hotelTableModel.addColumn("Address");
        hotelTableModel.addColumn("Email");
        hotelTableModel.addColumn("Phone");
        hotelTableModel.addColumn("Stars");

        seasonTableModel = new DefaultTableModel();
        table_seasons.setModel(seasonTableModel);
        seasonTableModel.addColumn("ID");
        seasonTableModel.addColumn("Hotel ID");
        seasonTableModel.addColumn("Start Date");
        seasonTableModel.addColumn("End Date");

        pensionTypeTableModel = new DefaultTableModel();
        table_pension_type.setModel(pensionTypeTableModel);
        pensionTypeTableModel.addColumn("ID");
        pensionTypeTableModel.addColumn("Hotel ID");
        pensionTypeTableModel.addColumn("Type");

        roomTableModel = new DefaultTableModel();
        table_rooms.setModel(roomTableModel);
        roomTableModel.addColumn("ID");
        roomTableModel.addColumn("Hotel ID");
        roomTableModel.addColumn("Season ID");
        roomTableModel.addColumn("Pension Type ID");
        roomTableModel.addColumn("Type");
        roomTableModel.addColumn("Stock");
        roomTableModel.addColumn("Price (Adult)");
        roomTableModel.addColumn("Price (Child)");

        reservationTableModel = new DefaultTableModel();
        table_reservations.setModel(reservationTableModel);
        reservationTableModel.addColumn("ID");
        reservationTableModel.addColumn("Hotel ID");
        reservationTableModel.addColumn("User ID");
        reservationTableModel.addColumn("Start Date");
        reservationTableModel.addColumn("End Date");
        reservationTableModel.addColumn("Total Price");

        if (label_banner != null) {
            label_banner.setText("Welcome back, " + username + "!");
        }

        loadHotels();
        loadSeasons();
        loadPensionTypes();
        loadRooms();
        loadReservations();

        button_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Tüm otel bilgilerini yükler ve otel tablosuna ekler.
     */
    private void loadHotels() {
        try {
            List<Hotel> hotels = hotelManager.getAllHotels();
            hotelTableModel.setRowCount(0); // Tablodaki mevcut verileri temizle
            for (Hotel hotel : hotels) {
                hotelTableModel.addRow(new Object[]{
                        hotel.getId(),
                        hotel.getName(),
                        hotel.getAddress(),
                        hotel.getEmail(),
                        hotel.getPhone(),
                        hotel.getStars()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.showMessage(this, "Error loading hotels: " + e.getMessage());
        }
    }

    /**
     * Tüm sezon bilgilerini yükler ve sezon tablosuna ekler.
     */
    private void loadSeasons() {
        try {
            List<Season> seasons = seasonManager.getAllSeasons();
            seasonTableModel.setRowCount(0); // Tablodaki mevcut verileri temizle
            for (Season season : seasons) {
                seasonTableModel.addRow(new Object[]{
                        season.getId(),
                        season.getHotelId(),
                        season.getStartDate(),
                        season.getEndDate()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.showMessage(this, "Error loading seasons: " + e.getMessage());
        }
    }

    /**
     * Tüm pansiyon tipi bilgilerini yükler ve pansiyon tipi tablosuna ekler.
     */
    private void loadPensionTypes() {
        try {
            List<PensionType> pensionTypes = pensionTypeManager.getAllPensionTypes();
            pensionTypeTableModel.setRowCount(0); // Tablodaki mevcut verileri temizle
            for (PensionType pensionType : pensionTypes) {
                pensionTypeTableModel.addRow(new Object[]{
                        pensionType.getId(),
                        pensionType.getHotelId(),
                        pensionType.getType()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.showMessage(this, "Error loading pension types: " + e.getMessage());
        }
    }

    /**
     * Tüm oda bilgilerini yükler ve oda tablosuna ekler.
     */
    private void loadRooms() {
        try {
            List<Room> rooms = roomManager.getAllRooms();
            roomTableModel.setRowCount(0); // Tablodaki mevcut verileri temizle
            for (Room room : rooms) {
                roomTableModel.addRow(new Object[]{
                        room.getId(),
                        room.getHotelId(),
                        room.getSeasonId(),
                        room.getPensionTypeId(),
                        room.getRoomType(),
                        room.getStock(),
                        room.getAdultPrice(),
                        room.getChildPrice()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.showMessage(this, "Error loading rooms: " + e.getMessage());
        }
    }

    /**
     * Tüm rezervasyon bilgilerini yükler ve rezervasyon tablosuna ekler.
     */
    private void loadReservations() {
        try {
            List<Reservation> reservations = reservationManager.getAllReservations();
            reservationTableModel.setRowCount(0); // Tablodaki mevcut verileri temizle
            for (Reservation reservation : reservations) {
                reservationTableModel.addRow(new Object[]{
                        reservation.getId(),
                        reservation.getHotelId(),
                        reservation.getUserId(),
                        reservation.getStartDate(),
                        reservation.getEndDate(),
                        reservation.getTotalPrice()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.showMessage(this, "Error loading reservations: " + e.getMessage());
        }
    }

    /**
     * Uygulamayı başlatan ana metot.
     * Nimbus görünümünü ayarlar ve UserView penceresini oluşturur.
     *
     * @param args Komut satırı argümanları
     */
    public static void main(String[] args) {
        Helper.setNimbusLookAndFeel();
        new UserView("User");
    }
}
