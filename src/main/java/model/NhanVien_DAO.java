package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class NhanVien_DAO {
    public ArrayList<NhanVien> getalltbNV() {
        ArrayList<NhanVien> nhanViens = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
        	 ConnectDB.getInstance();
             con = ConnectDB.getInstance().getConnection();
             String sql = "SELECT * FROM NhanVien";
             statement = con.createStatement();
             resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                String maNV = resultSet.getString(1);
                String hoTenNV = resultSet.getString(2);
                LocalDate ngaySinh = resultSet.getDate(3).toLocalDate();
                String soDienThoai = resultSet.getString(4);
                String email = resultSet.getString(5);
                String diaChi = resultSet.getString(6);
                boolean gioiTinh = resultSet.getBoolean(7);
                String CCCD = resultSet.getString(8);
                float heSoLuong = resultSet.getFloat(9);
                boolean trangThai = resultSet.getBoolean(10);
                
                NhanVien nhanVien  = new NhanVien(maNV, hoTenNV, ngaySinh, soDienThoai, email, diaChi, gioiTinh, CCCD, heSoLuong, trangThai);
                nhanViens.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return nhanViens;
    }
}
