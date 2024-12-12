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

	public List<NhanVien> findNhanVienByTen(String ten) {
		String sql = "SELECT * FROM NhanVien WHERE hoTenNV = ?";
		Connection con = null;
		PreparedStatement preparedStatement = null;
		List<NhanVien> list = new ArrayList<>();

		try {
			con = Database.getInstance().getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ten);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String maNhanVien = resultSet.getString(1);
				String hoTenNV = resultSet.getString(2);
				LocalDate ngaySinh = resultSet.getDate(3).toLocalDate();
				String soDienThoai = resultSet.getString(4);
				String email = resultSet.getString(5);
				String diaChi = resultSet.getString(6);
				boolean gioiTinh = resultSet.getBoolean(7);
				String CCCD = resultSet.getString(8);
				float heSoLuong = resultSet.getFloat(9);
				boolean trangThai = resultSet.getBoolean(10);
				String maChucVu = resultSet.getString(12);
				LocalDate ngayVaoLam = resultSet.getDate(11).toLocalDate();

				NhanVien nhanVien = new NhanVien(maNhanVien, hoTenNV, ngaySinh, soDienThoai, email, diaChi, gioiTinh,
						CCCD, heSoLuong, trangThai, maChucVu, ngayVaoLam);
				list.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<NhanVien> findNhanVienBySdt(String sdt) {
		String sql = "SELECT * FROM NhanVien WHERE soDienThoai = ?";
		Connection con = null;
		PreparedStatement preparedStatement = null;
		List<NhanVien> list = new ArrayList<>();

		try {
			con = Database.getInstance().getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sdt);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String maNhanVien = resultSet.getString(1);
				String hoTenNV = resultSet.getString(2);
				LocalDate ngaySinh = resultSet.getDate(3).toLocalDate();
				String soDienThoai = resultSet.getString(4);
				String email = resultSet.getString(5);
				String diaChi = resultSet.getString(6);
				boolean gioiTinh = resultSet.getBoolean(7);
				String CCCD = resultSet.getString(8);
				float heSoLuong = resultSet.getFloat(9);
				boolean trangThai = resultSet.getBoolean(10);
				String maChucVu = resultSet.getString(12);
				LocalDate ngayVaoLam = resultSet.getDate(11).toLocalDate();

				NhanVien nhanVien = new NhanVien(maNhanVien, hoTenNV, ngaySinh, soDienThoai, email, diaChi, gioiTinh,
						CCCD, heSoLuong, trangThai, maChucVu, ngayVaoLam);
				list.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<NhanVien> findNhanVienByCCCD(String cccd) {
		String sql = "SELECT * FROM NhanVien WHERE CCCD = ?";
		Connection con = null;
		PreparedStatement preparedStatement = null;
		List<NhanVien> list = new ArrayList<>();

		try {
			con = Database.getInstance().getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, cccd);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String maNhanVien = resultSet.getString(1);
				String hoTenNV = resultSet.getString(2);
				LocalDate ngaySinh = resultSet.getDate(3).toLocalDate();
				String soDienThoai = resultSet.getString(4);
				String email = resultSet.getString(5);
				String diaChi = resultSet.getString(6);
				boolean gioiTinh = resultSet.getBoolean(7);
				String CCCD = resultSet.getString(8);
				float heSoLuong = resultSet.getFloat(9);
				boolean trangThai = resultSet.getBoolean(10);
				String maChucVu = resultSet.getString(12);
				LocalDate ngayVaoLam = resultSet.getDate(11).toLocalDate();

				NhanVien nhanVien = new NhanVien(maNhanVien, hoTenNV, ngaySinh, soDienThoai, email, diaChi, gioiTinh,
						CCCD, heSoLuong, trangThai, maChucVu, ngayVaoLam);
				list.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean insertNhanVien(NhanVien nhanVien) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO NhanVien (maNV,hoTenNV,ngaySinh,soDienThoai,email,diaChi,gioiTinh,CCCD,heSoLuong,trangThai,ngayVaoLam,tenChucVu) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			con = Database.getInstance().getConnection();
			preparedStatement = con.prepareStatement(sql);

			preparedStatement.setString(1, nhanVien.getMaNV());
			preparedStatement.setString(2, nhanVien.getHoTenNV());
			preparedStatement.setDate(3, java.sql.Date.valueOf(nhanVien.getNgaySinh()));
			preparedStatement.setString(4, nhanVien.getSoDienThoai());
			preparedStatement.setString(5, nhanVien.getEmail());
			preparedStatement.setString(6, nhanVien.getDiaChi());
			preparedStatement.setBoolean(7, nhanVien.isGioiTinh());
			preparedStatement.setString(8, nhanVien.getCCCD());
			preparedStatement.setFloat(9, nhanVien.getHeSoLuong());
			preparedStatement.setBoolean(10, nhanVien.isTrangThai());
			preparedStatement.setDate(11, java.sql.Date.valueOf(nhanVien.getNgayVaoLam()));
			preparedStatement.setString(12, nhanVien.getTenChucVu());

			int rowsInserted = preparedStatement.executeUpdate();

			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertNhanVien(Object[] nhanVien) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO NhanVien (hoTenNV,soDienThoai,email,CCCD,gioiTinh,ngaySinh) Where maNV = ? VALUES (?,?,?,?,?,?)";

		try {
			con = Database.getInstance().getConnection();
			preparedStatement = con.prepareStatement(sql);

			preparedStatement.setString(1, (String) nhanVien[1]);
			preparedStatement.setString(2, (String) nhanVien[2]);
			preparedStatement.setString(3, (String) nhanVien[3]);
			preparedStatement.setString(4, (String) nhanVien[4]);
			preparedStatement.setBoolean(5, (Boolean) nhanVien[5]);
			preparedStatement.setDate(6, java.sql.Date.valueOf((LocalDate) nhanVien[7]));
			preparedStatement.setString(7, (String) nhanVien[0]);

			int rowsInserted = preparedStatement.executeUpdate();

			return rowsInserted > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
