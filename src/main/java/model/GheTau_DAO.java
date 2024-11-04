package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class GheTau_DAO {
	
	// get toaTau theo maTau
	public ArrayList<GheTau> getGheTauTheoMaToaTau(String maToaTau) {
	    ArrayList<GheTau> gheTaus = new ArrayList<>();
	    String sql = "SELECT * FROM GheTau WHERE maToaTau = ?";
	    Connection con;

	    try {
	        con = ConnectDB.getInstance().getConnection();
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        preparedStatement.setString(1, maToaTau);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            String maGheTau = resultSet.getString(1);
	            String tenLoaiGhe = resultSet.getString(2);
	            int soThuTuGhe = resultSet.getInt(3);
	            String trangThai = resultSet.getString(4);
	            
	            ToaTau toaTau = new ToaTau(maToaTau);
	            GheTau gheTau = new GheTau(maGheTau, tenLoaiGhe, soThuTuGhe, trangThai, toaTau);
	            gheTaus.add(gheTau);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return gheTaus;
	}

}
