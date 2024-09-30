package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class GiaVe_DAO {
	public ArrayList<GiaVe> getalltbKH() {
        ArrayList<GiaVe> dsGiaVe = new ArrayList<>();
        String sql = "SELECT * FROM GiaVe";
        
        try (Connection con = ConnectDB.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                String maGiaVe = resultSet.getString(1);
                double giaVe = resultSet.getDouble(2);
                float tiLeTangGia = resultSet.getFloat(3);
                LocalDateTime ngayCapNhap = resultSet.getTimestamp(4).toLocalDateTime();
                String ghiChu = resultSet.getString(5);            
                
                GiaVe giave = new GiaVe(maGiaVe, giaVe, tiLeTangGia, ngayCapNhap, ghiChu);
                dsGiaVe.add(giave);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dsGiaVe;
    }
	public boolean themGiaVe(GiaVe giaVe) {
	    String sql = "INSERT INTO GiaVe (maGiaVe, giaVe, tiLeTangGia, ngayCapNhap, ghiChu) VALUES (?, ?, ?, ?, ?)";

	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, giaVe.getMaGiaVe());
	        stmt.setDouble(2, giaVe.getGiaVe());
	        stmt.setFloat(3, giaVe.getTiLeTangGia());
	        stmt.setTimestamp(4, Timestamp.valueOf(giaVe.getNgayCapNhap()));
	        stmt.setString(5, giaVe.getGhiChu());

	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean capNhapGiaVe(GiaVe giaVe) {
	    String sql = "UPDATE GiaVe SET giaVe = ?, tiLeTangGia = ?, ngayCapNhap = ?, ghiChu = ? WHERE maGiaVe = ?";

	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setDouble(1, giaVe.getGiaVe());
	        stmt.setFloat(2, giaVe.getTiLeTangGia());
	        stmt.setTimestamp(3, Timestamp.valueOf(giaVe.getNgayCapNhap()));
	        stmt.setString(4, giaVe.getGhiChu());
	        stmt.setString(5, giaVe.getMaGiaVe());

	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
