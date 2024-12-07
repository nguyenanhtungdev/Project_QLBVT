package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;

public class GheTau_DAO {

	private static GheTau_DAO instance;

	public static GheTau_DAO getInstance() {
		return instance == null ? instance = new GheTau_DAO() : instance;
	}

	public List<GheTau> getAll() {
		String sql = "Select * FROM GheTau";

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			List<GheTau> list = new ArrayList<>();
			while (resultSet.next()) {
				String maGheTau = resultSet.getString("maGheTau");
				String tenLoaiGheTau = resultSet.getString("tenLoaiGheTau");
				int soThuTuGhe = resultSet.getInt("soThuTuGhe");
				String trangThai = resultSet.getString("trangThai");
				String maToaTau = resultSet.getString("maToaTau");

				ToaTau toatau = new ToaTau(maToaTau);

				list.add(new GheTau(maGheTau, tenLoaiGheTau, soThuTuGhe, trangThai, toatau));
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

	public GheTau getByMaGheTau(String maGheTau) {
		String sql = "Select * FROM GheTau WHERE maGheTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maGheTau);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String tenLoaiGheTau = resultSet.getString("tenLoaiGheTau");
				int soThuTuGhe = resultSet.getInt("soThuTuGhe");
				String trangThai = resultSet.getString("trangThai");
				String maToaTau = resultSet.getString("maToaTau");

				ToaTau toatau = new ToaTau(maToaTau);

				return new GheTau(maGheTau, tenLoaiGheTau, soThuTuGhe, trangThai, toatau);
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

	public ArrayList<GheTau> getDsGheTau(String maToaTau) {
		ArrayList<GheTau> gheTaus = new ArrayList<>();
		String sql = "SELECT * FROM GheTau GT JOIN ToaTau TT ON GT.maToaTau = TT.maToaTau WHERE GT.maToaTau = ?";
		Connection con;

		try {
			con = Database.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maToaTau);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String maGheTau = resultSet.getString(1);
				String tenLoaiGheTau = resultSet.getString(2);
				int soThuTuGhe = resultSet.getInt(3);
				String trangThai = resultSet.getString(4);

				ToaTau toatau = new ToaTau(maToaTau);

				gheTaus.add(new GheTau(maGheTau, tenLoaiGheTau, soThuTuGhe, trangThai, toatau));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gheTaus;
	}

	public boolean updateTrangThaiGheTau(String maGheTau, String trangThaiMoi) {
		String sql = "UPDATE GheTau SET trangThai = ? WHERE maGheTau = ?";
		Connection con;

		try {
			con = Database.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, trangThaiMoi);
			statement.setString(2, maGheTau);

			int rowsUpdated = statement.executeUpdate();

			// Kiểm tra nếu cập nhật thành công ít nhất một dòng
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<GheTau> getGheTauTheoMaToaTau(String maToaTau) {
		String sql = "SELECT * FROM GheTau WHERE maToaTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maToaTau);
			resultSet = statement.executeQuery();

			List<GheTau> list = new ArrayList<>();
			while (resultSet.next()) {
				String maGheTau = resultSet.getString(1);
				String tenLoaiGhe = resultSet.getString(2);
				int soThuTuGhe = resultSet.getInt(3);
				String trangThai = resultSet.getString(4);

				ToaTau toaTau = new ToaTau(maToaTau);

				list.add(new GheTau(maGheTau, tenLoaiGhe, soThuTuGhe, trangThai, toaTau));
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
}