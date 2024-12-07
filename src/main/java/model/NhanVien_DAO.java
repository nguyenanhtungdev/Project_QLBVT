package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;

public class NhanVien_DAO {

	private static NhanVien_DAO instance;

	public static NhanVien_DAO getInstance() {
		return instance == null ? instance = new NhanVien_DAO() : instance;
	}

	public List<NhanVien> getAll() {
		String sql = "SELECT * FROM NhanVien";

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			List<NhanVien> list = new ArrayList<>();
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
				String tenChucVu = resultSet.getString("tenChucVu");
				LocalDate ngayVaoLam = resultSet.getDate("ngayVaoLam").toLocalDate();

				list.add(new NhanVien(maNV, hoTenNV, ngaySinh, soDienThoai, email, diaChi, gioiTinh, CCCD, heSoLuong,
						trangThai, tenChucVu, ngayVaoLam));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public NhanVien getByMaNhanVien(String maNhanVien) {
		String sql = "SELECT * FROM NhanVien WHERE maNV = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maNhanVien);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
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
				String tenChucVu = resultSet.getString("tenChucVu");
				LocalDate ngayVaoLam = resultSet.getDate("ngayVaoLam").toLocalDate();

				return new NhanVien(maNV, hoTenNV, ngaySinh, soDienThoai, email, diaChi, gioiTinh, CCCD, heSoLuong,
						trangThai, tenChucVu, ngayVaoLam);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public boolean update(NhanVien nhanVien) {
		String sql = "UPDATE NhanVien SET hoTenNV = ?, ngaySinh = ?, soDienThoai = ?, email = ?, diaChi = ?, gioiTinh = ?, CCCD = ?, heSoLuong = ?, trangThai = ?, tenChucVu = ?, ngayVaoLam = ? WHERE maNV = ?";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);

			statement.setString(1, nhanVien.getHoTenNV());
			statement.setDate(2, java.sql.Date.valueOf(nhanVien.getNgaySinh()));
			statement.setString(3, nhanVien.getSoDienThoai());
			statement.setString(4, nhanVien.getEmail());
			statement.setString(5, nhanVien.getDiaChi());
			statement.setBoolean(6, nhanVien.isGioiTinh());
			statement.setString(7, nhanVien.getCCCD());
			statement.setFloat(8, nhanVien.getHeSoLuong());
			statement.setBoolean(9, nhanVien.isTrangThai());
			statement.setString(10, nhanVien.getTenChucVu());
			statement.setDate(11, Date.valueOf(nhanVien.getNgayVaoLam()));
			statement.setString(12, nhanVien.getMaNV());
			int rowsUpdated = statement.executeUpdate();

			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
