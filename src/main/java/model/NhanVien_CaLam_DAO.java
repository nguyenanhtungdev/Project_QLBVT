package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class NhanVien_CaLam_DAO {
	public ArrayList<NhanVien_CaLam> getalltbNVCL() {
		ArrayList<NhanVien_CaLam> nvcls = new ArrayList<>() ;
		String sql = "Select * FROM NhanVien_CaLam" ;
		Connection con;
		
		 try {
			 con = ConnectDB.getInstance().getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
	             
			 while(resultSet.next()) {
				 LocalDateTime thoiGianNhanCa = resultSet.getTimestamp(1).toLocalDateTime() ;
				 LocalDateTime thoiGianKetThucCa = resultSet.getTimestamp(2).toLocalDateTime() ;
				 
				 NhanVien_CaLam nvcl = new NhanVien_CaLam(thoiGianNhanCa, thoiGianKetThucCa) ; 
				 nvcls.add(nvcl) ; 
			 }
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		 return nvcls ; 
	}
}
