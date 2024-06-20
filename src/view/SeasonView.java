package view;

import business.SeasonManager;
import business.HotelManager;
import core.Helper;
import entity.Season;
import entity.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class SeasonView extends Layout {
    private JPanel container;
    private JLabel label_banner;
    private JLabel label_hotel_id;
    private JTextField field_hotel_id;
    private JTextField field_start_date;
    private JLabel label_start_date;
    private JButton button_get_hotel_name;
    private JTextField field_end_date;
    private JLabel label_end_date;
    private JLabel label_hotel_name;
    private JTextField field_hotel_name;
    private JButton button_save;
    private JButton button_exit;

    private final SeasonManager seasonManager;
    private final HotelManager hotelManager;
    private Season season;

    public SeasonView(Season season) {
        this.season = season != null ? season : new Season(0, 0, "", null, null);
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();

        Helper.setupWindow(this, container, "Season Management", 500, 320);

        label_banner.setText(season == null ? "Add Season" : "Edit Season");

        button_exit.addActionListener(e -> dispose());

        button_get_hotel_name.addActionListener(e -> getHotelName());

        button_save.addActionListener(e -> saveSeason());

        populateSeasonData();
    }

    private void getHotelName() {
        String hotelIdText = field_hotel_id.getText();
        if (hotelIdText.isEmpty()) {
            Helper.showMessage(this, "Please enter a hotel ID.");
            return;
        }

        int hotelId = Integer.parseInt(hotelIdText);
        Hotel hotel = hotelManager.getHotelById(hotelId);

        if (hotel == null) {
            Helper.showMessage(this, "Hotel not found. Please enter a valid hotel ID.");
        } else {
            field_hotel_name.setText(hotel.getName());
        }
    }

    private void populateSeasonData() {
        if (season.getId() != 0) {
            field_hotel_id.setText(String.valueOf(season.getHotelId()));
            field_start_date.setText(season.getStartDate().toString());
            field_end_date.setText(season.getEndDate().toString());
            field_hotel_name.setText(season.getHotelName());
        }
    }

    private void saveSeason() {
        if (!Helper.checkAndShowEmptyFields(this, field_hotel_id, field_start_date, field_end_date)) {
            return;
        }

        int hotelId = Integer.parseInt(field_hotel_id.getText());
        Date startDate = Date.valueOf(field_start_date.getText());
        Date endDate = Date.valueOf(field_end_date.getText());

        season.setHotelId(hotelId);
        season.setStartDate(startDate);
        season.setEndDate(endDate);

        if (season.getId() == 0) {
            seasonManager.addSeason(season);
        } else {
            seasonManager.updateSeason(season);
        }
        dispose();
    }
}
