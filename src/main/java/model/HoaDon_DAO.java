package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import connectDB.ConnectDB;

public class HoaDon_DAO {
	public ArrayList<HoaDon> getalltbHD() { 
        ArrayList<HoaDon> hoaDons = new ArrayList<>();
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            ConnectDB.getInstance();
            con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM HoaDon";
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                String maHoaDon = resultSet.getString(1);
                Timestamp timestamp = resultSet.getTimestamp(2);
                LocalDateTime ngayLapHoaDon = timestamp.toLocalDateTime();
                String ghiChu = resultSet.getString(3);
                float thueVAT = resultSet.getFloat(4);
                String phuongThucThanhToan = resultSet.getString(5);
                String loaiHoaDon = resultSet.getString(6);
                String maKH = resultSet.getString(7);
                String maNhaGa = resultSet.getString(8);
                String maNV = resultSet.getString(9);
                String maThongTinGiuCho = resultSet.getString(10);
                
                KhachHang khachHang = new KhachHang(maKH);
                ThongTinTram thongTinTram = new ThongTinTram(maNhaGa);
                NhanVien nhanVien = new NhanVien(maNV);
                ThongTinGiuCho thongTinGiuCho = new ThongTinGiuCho(maThongTinGiuCho);
                
                HoaDon hoaDon = new HoaDon(maHoaDon, ngayLapHoaDon, ghiChu, thueVAT, phuongThucThanhToan, loaiHoaDon, khachHang, thongTinTram, nhanVien, thongTinGiuCho);
                hoaDons.add(hoaDon);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDons;
    }
}


