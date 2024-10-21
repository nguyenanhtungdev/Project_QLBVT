package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class ToaTau_DAO {
	public ArrayList<ToaTau> getalltbTT() {
		ArrayList<ToaTau> toaTaus = new ArrayList<>() ;
		String sql = "Select * FROM ToaTau" ; 
		Connection con;
		
		 try {
			 con = ConnectDB.getInstance().getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
			 while(resultSet.next()) {
				 String maToaTau = resultSet.getString(1) ;
				 String tenToaTau = resultSet.getString(2) ;
				 int soThuTuToa = resultSet.getInt(3) ; 
				 String loaiToa = resultSet.getString(4) ; 
				 int soLuongGhe = resultSet.getInt(5) ; 
				 boolean trangThai = resultSet.getBoolean(6) ; 
				 				 
				 ToaTau toatau = new ToaTau(maToaTau, tenToaTau, soThuTuToa, loaiToa, soLuongGhe, trangThai) ; 
				 toaTaus.add(toatau) ; 
			 }
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		 return toaTaus ; 
	}
}
