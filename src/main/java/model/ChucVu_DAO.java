package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class ChucVu_DAO {
	public ArrayList<ChucVu> getAllChucVu() {
        ArrayList<ChucVu> dsChucVu = new ArrayList<>();
        String sql = "SELECT * FROM ChucVu";
        
        try (Connection con = ConnectDB.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                String maChucVu = resultSet.getString(1);
                String tenChucVu = resultSet.getString(2);	               
                LocalDate ngayNhanChuc = resultSet.getDate(7).toLocalDate();
                
                ChucVu chucvu = new ChucVu(maChucVu, tenChucVu, ngayNhanChuc);
                dsChucVu.add(chucvu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dsChucVu;
    }
	
	public boolean themChucVu(ChucVu chucVu) {
	    String sql = "INSERT INTO ChucVu (maChucVu, tenChucVu, ngayNhanChuc) VALUES (?, ?, ?)";
	    
	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement stmt = con.prepareStatement(sql)) {
	        
	        stmt.setString(1, chucVu.getMaChucVu());
	        stmt.setString(2, chucVu.getTenChucVu());
	        stmt.setDate(3, Date.valueOf(chucVu.getNgayNhanChuc()));
	        
	        return stmt.executeUpdate()>0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	 
	public boolean capNhapChucVu(ChucVu chucVu) {
	    String sql = "UPDATE ChucVu SET tenChucVu = ?, ngayNhanChuc = ? WHERE maChucVu = ?";
	    
	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement stmt = con.prepareStatement(sql)) {
	        
	        stmt.setString(1, chucVu.getTenChucVu());
	        stmt.setDate(2, Date.valueOf(chucVu.getNgayNhanChuc()));
	        stmt.setString(3, chucVu.getMaChucVu());
	        
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
