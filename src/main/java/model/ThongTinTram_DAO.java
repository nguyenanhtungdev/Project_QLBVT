package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;

public class ThongTinTram_DAO {
	
    private static ThongTinTram_DAO instance;

    public static ThongTinTram_DAO getInstance() {
        if (instance == null)
            instance = new ThongTinTram_DAO();
        return instance;
    }
    
    public ThongTinTram layThongTinTramTheoMa(String maNhaGa) {
        ThongTinTram thongTinTram = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ConnectDB.getInstance();
            con = ConnectDB.getInstance().getConnection();

            String sql = "SELECT maNhaGa, tenNhaGa, diaChi, dienThoai, email, tenNganHang, soTaiKhoan, maSoThue "
                       + "FROM ThongTinTram WHERE maNhaGa = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, maNhaGa);

            rs = ps.executeQuery();
            
            if (rs.next()) {
                String tenNhaGa = rs.getString("tenNhaGa");
                String diaChi = rs.getString("diaChi");
                String dienThoai = rs.getString("dienThoai");
                String email = rs.getString("email");
                String tenNganHang = rs.getString("tenNganHang");
                String soTaiKhoan = rs.getString("soTaiKhoan");
                String maSoThue = rs.getString("maSoThue");

                thongTinTram = new ThongTinTram(maNhaGa, tenNhaGa, diaChi, dienThoai, email, tenNganHang, soTaiKhoan, maSoThue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thongTinTram;
    }
}
