package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;

public class KhachHang_DAO {

	private static KhachHang_DAO instance;

	public static KhachHang_DAO getInstance() {
		return instance == null ? instance = new KhachHang_DAO() : instance;
	}

	public List<KhachHang> getAll() {
		String sql = "SELECT * FROM KhachHang";

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			List<KhachHang> list = new ArrayList<>();
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

				list.add(new KhachHang(maKhachHang, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh, loaiKH));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public KhachHang getByMaKhachHang(String maKhachHang) {
		String sql = "SELECT * FROM KhachHang WHERE maKH = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maKhachHang);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String hoTen = resultSet.getString("hoTen");
				String soDienThoai = resultSet.getString("soDienThoai");
				String email = resultSet.getString("email");
				boolean gioiTinh = resultSet.getBoolean("gioiTinh");
				String CCCD = resultSet.getString("CCCD");
				LocalDate ngaySinh = resultSet.getDate("ngaySinh").toLocalDate();
				KhachHang.LoaiKhachHang loaiKH = KhachHang.LoaiKhachHang.valueOf(resultSet.getString("loaiKH"));

				return new KhachHang(maKhachHang, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh, loaiKH);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public boolean insertKhachHang(KhachHang khachHang) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh, loaiKhachHang) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Database.getInstance();
			con = Database.getInstance().getConnection();
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
			con = Database.getInstance().getConnection();
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
			Database.getInstance();
			con = Database.getInstance().getConnection();
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
