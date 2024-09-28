package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class KhachHang_DAO {

    public ArrayList<KhachHang> getalltbKH() {
        ArrayList<KhachHang> khachHangs = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        
        try (Connection con = ConnectDB.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                String maKhachHang = resultSet.getString(1);
                String hoTen = resultSet.getString(2);
                String soDienThoai = resultSet.getString(3);
                String email = resultSet.getString(4);
                boolean gioiTinh = resultSet.getBoolean(5);
                String CCCD = resultSet.getString(6);
                LocalDate ngaySinh = resultSet.getDate(7).toLocalDate();
                String loaiKH = resultSet.getString(8);
                
                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh, loaiKH);
                khachHangs.add(khachHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return khachHangs;
    }
}
