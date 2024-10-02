package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class KhachHang_DAO {

    public ArrayList<KhachHang> getalltbKH() {
        ArrayList<KhachHang> khachHangs = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            ConnectDB.getInstance();
            con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM KhachHang";
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                String maKhachHang = resultSet.getString(1);
                String hoTen = resultSet.getString(2);
                String soDienThoai = resultSet.getString(3);
                String email = resultSet.getString(4);
                boolean gioiTinh = resultSet.getBoolean(5);
                String CCCD = resultSet.getString(6);
                LocalDate ngaySinh = resultSet.getDate(7).toLocalDate();
                String loaiKHStr = resultSet.getString(8);
                
                KhachHang.LoaiKhachHang loaiKH = KhachHang.LoaiKhachHang.valueOf(loaiKHStr);           

                KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh, loaiKH);
                khachHangs.add(khachHang);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (con != null) con.close();  // Đóng kết nối sau khi sử dụng
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return khachHangs;
    }
}
