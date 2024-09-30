package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import model.Tau.TrangThaiChuyenTau;

public class Tau_DAO {
	// Lấy danh sách tất cả các tàu
    public List<Tau> getAllTau() throws SQLException {
        List<Tau> dsTau = new ArrayList<>();
        String sql = "SELECT * FROM Tau";
        
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Tau tau = new Tau(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getString(5),
                    resultSet.getFloat(6),
                    resultSet.getFloat(7),
                    TrangThaiChuyenTau.valueOf(resultSet.getString(8)),
                    resultSet.getString(9)
                );
                dsTau.add(tau);
            }
        }
        return dsTau;
    }
	
	// Thêm thông tin tàu
    public void themTau(Tau tau) throws SQLException {
        String sql = "INSERT INTO Tau (maTau, tenTau, soToa, namSanXuat, nhaSanXuat, tocDoTB, tocDoToiDa, trangThai, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tau.getMaTau());
            stmt.setString(2, tau.getTenTau());
            stmt.setInt(3, tau.getSoToa());
            stmt.setDate(4, Date.valueOf(tau.getNamSanXuat()));
            stmt.setString(5, tau.getNhaSanXuat());
            stmt.setFloat(6, tau.getTocDoTB());
            stmt.setFloat(7, tau.getTocDoToiDa());
            stmt.setString(8, tau.getTrangThai().name());
            stmt.setString(9, tau.getGhiChu());
            
            stmt.executeUpdate();
        }
    }

    // Cập nhật thông tin tàu
    public void capNhapTau(Tau tau) throws SQLException {
        String sql = "UPDATE Tau SET tenTau = ?, soToa = ?, namSanXuat = ?, nhaSanXuat = ?, tocDoTB = ?, tocDoToiDa = ?, trangThai = ?, ghiChu = ? WHERE maTau = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tau.getTenTau());
            stmt.setInt(2, tau.getSoToa());
            stmt.setDate(3, Date.valueOf(tau.getNamSanXuat()));
            stmt.setString(4, tau.getNhaSanXuat());
            stmt.setFloat(5, tau.getTocDoTB());
            stmt.setFloat(6, tau.getTocDoToiDa());
            stmt.setString(7, tau.getTrangThai().name());
            stmt.setString(8, tau.getGhiChu());
            stmt.setString(9, tau.getMaTau());
            
            stmt.executeUpdate();
        }
    }
    
    //Cập nhập trạng thái tàu
    public void capNhapTTTau(String maTau) throws SQLException {
        String sql = "UPDATE Tau SET trangThai = ? WHERE maTau = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, TrangThaiChuyenTau.DUNG_HOAT_DONG.name());
            stmt.setString(2, maTau);

            stmt.executeUpdate();
        }
    }

    // Lấy thông tin tàu theo mã tàu
    public Tau layTTTauTheoMa(String maTau) throws SQLException {
        String sql = "SELECT * FROM Tau WHERE maTau = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, maTau);
            ResultSet resultSet = stmt.executeQuery();
            
            if (resultSet.next()) {
                return new Tau(
                    resultSet.getString("maTau"),
                    resultSet.getString("tenTau"),
                    resultSet.getInt("soToa"),
                    resultSet.getDate("namSanXuat").toLocalDate(),
                    resultSet.getString("nhaSanXuat"),
                    resultSet.getFloat("tocDoTB"),
                    resultSet.getFloat("tocDoToiDa"),
                    TrangThaiChuyenTau.valueOf(resultSet.getString("trangThai")),
                    resultSet.getString("ghiChu")
                );
            }
        }
        return null;
    }

    
}
