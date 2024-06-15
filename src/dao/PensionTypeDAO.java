package dao;

import entity.PensionType;
import core.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PensionTypeDAO {

    public List<PensionType> getAllPensionTypes() {
        List<PensionType> pensionTypes = new ArrayList<>();
        String query = "SELECT * FROM pension_type";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                PensionType pensionType = new PensionType(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getString("type")
                );
                pensionTypes.add(pensionType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionTypes;
    }

    public PensionType getPensionTypeById(int id) {
        String query = "SELECT * FROM pension_type WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new PensionType(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getString("type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addPensionType(PensionType pensionType) {
        String query = "INSERT INTO pension_type (hotel_id, type) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, pensionType.getHotelId());
            pstmt.setString(2, pensionType.getType());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePensionType(PensionType pensionType) {
        String query = "UPDATE pension_type SET hotel_id = ?, type = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, pensionType.getHotelId());
            pstmt.setString(2, pensionType.getType());
            pstmt.setInt(3, pensionType.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePensionType(int id) {
        String query = "DELETE FROM pension_type WHERE id = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
