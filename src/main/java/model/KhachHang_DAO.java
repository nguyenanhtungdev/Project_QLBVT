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

	private static KhachHang_DAO instance;

	public static KhachHang_DAO getInstance() {
		if (instance == null)
			instance = new KhachHang_DAO();
		return instance;
	}

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

				KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh,
						loaiKH);
				khachHangs.add(khachHang);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (con != null)
					con.close(); // Đóng kết nối sau khi sử dụng
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return khachHangs;
	}

	public boolean insertKhachHang(KhachHang khachHang) {
	    Connection con = null;
	    PreparedStatement preparedStatement = null;
	    String sql = "INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh, loaiKhachHang) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        ConnectDB.getInstance();
	        con = ConnectDB.getInstance().getConnection();
	        preparedStatement = con.prepareStatement(sql);

	        // Thiết lập các giá trị cho câu lệnh SQL
	        preparedStatement.setString(1, khachHang.getMaKhachHang());
	        preparedStatement.setString(2, khachHang.getHoTen());
	        preparedStatement.setString(3, khachHang.getSoDienThoai());
	        preparedStatement.setString(4, khachHang.getEmail());
	        preparedStatement.setBoolean(5, khachHang.isGioiTinh());
	        preparedStatement.setString(6, khachHang.getCCCD());
	        preparedStatement.setDate(7, java.sql.Date.valueOf(khachHang.getNgaySinh()));
	        preparedStatement.setString(8, khachHang.getLoaiKH().name());

	        // Thực thi câu lệnh SQL
	        int rowsInserted = preparedStatement.executeUpdate();

	        // Trả về true nếu có hàng được thêm
	        return rowsInserted > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public String getMaKHMax() {
		ArrayList<VeTau> khachHangs = new ArrayList<>();
		String sql = "SELECT MAX(maKH) FROM KhachHang";
		Connection con;
		String maKH = null;
		try {
			con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				maKH = resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maKH;
	}
	
	public KhachHang findKhachHangByCCCDOrSDT(String cccd, String sdt) {
	    String sql = "SELECT * FROM KhachHang WHERE CCCD = ? OR SoDienThoai = ?";
	    Connection con = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        ConnectDB.getInstance();
	        con = ConnectDB.getInstance().getConnection();
	        preparedStatement = con.prepareStatement(sql);
	        
	        preparedStatement.setString(1, cccd);
	        preparedStatement.setString(2, sdt);

	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
				String maKhachHang = resultSet.getString(1);
				String hoTen = resultSet.getString(2);
				String soDienThoai = resultSet.getString(3);
				String email = resultSet.getString(4);
				boolean gioiTinh = resultSet.getBoolean(5);
				String CCCD = resultSet.getString(6);
				LocalDate ngaySinh = resultSet.getDate(7).toLocalDate();
				String loaiKHStr = resultSet.getString(8);

				KhachHang.LoaiKhachHang loaiKH = KhachHang.LoaiKhachHang.valueOf(loaiKHStr);

				KhachHang khachHang = new KhachHang(maKhachHang, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh,
						loaiKH);
	            return khachHang;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
