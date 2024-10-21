package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                String maChucVu = resultSet.getString(11);
                
                ChucVu chucVu = new ChucVu(maChucVu);
                NhanVien nhanVien  = new NhanVien(maNV, hoTenNV, ngaySinh, soDienThoai, email, diaChi, gioiTinh, CCCD, heSoLuong, trangThai, chucVu);
                nhanViens.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return nhanViens;
    }
    
    public boolean updateNhanVien(NhanVien nhanVien) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE NhanVien SET hoTenNV = ?, ngaySinh = ?, soDienThoai = ?, email = ?, diaChi = ?, gioiTinh = ?, CCCD = ?, heSoLuong = ?, trangThai = ?, maChucVu = ? WHERE maNV = ?";

        try {
            ConnectDB.getInstance();
            con = ConnectDB.getInstance().getConnection();
            preparedStatement = con.prepareStatement(sql);

            // Thiết lập các giá trị cho câu lệnh SQL
            preparedStatement.setString(1, nhanVien.getHoTenNV());
            preparedStatement.setDate(2, java.sql.Date.valueOf(nhanVien.getNgaySinh()));
            preparedStatement.setString(3, nhanVien.getSoDienThoai());
            preparedStatement.setString(4, nhanVien.getEmail());
            preparedStatement.setString(5, nhanVien.getDiaChi());
            preparedStatement.setBoolean(6, nhanVien.isGioiTinh());
            preparedStatement.setString(7, nhanVien.getCCCD());
            preparedStatement.setFloat(8, nhanVien.getHeSoLuong());
            preparedStatement.setBoolean(9, nhanVien.isTrangThai());
            preparedStatement.setString(10, nhanVien.getChucVu().getMaChucVu());
            preparedStatement.setString(11, nhanVien.getMaNV());

            // Thực thi câu lệnh SQL
            int rowsUpdated = preparedStatement.executeUpdate();

            // Trả về true nếu có hàng được cập nhật
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
