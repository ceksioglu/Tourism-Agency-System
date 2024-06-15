package dao;

import entity.Season;
import core.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeasonDAO {

    public List<Season> getAllSeasons() {
        List<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM season";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Season season = new Season(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasons;
    }

    public Season getSeasonById(int id) {
        String query = "SELECT * FROM season WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Season(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addSeason(Season season) {
        String query = "INSERT INTO season (hotel_id, start_date, end_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, season.getHotelId());
            pstmt.setDate(2, season.getStartDate());
            pstmt.setDate(3, season.getEndDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSeason(Season season) {
        String query = "UPDATE season SET hotel_id = ?, start_date = ?, end_date = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, season.getHotelId());
            pstmt.setDate(2, season.getStartDate());
            pstmt.setDate(3, season.getEndDate());
            pstmt.setInt(4, season.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSeason(int id) {
        String query = "DELETE FROM season WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}