package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class GheTau_DAO {

	private static GheTau_DAO instance;

	public static GheTau_DAO getInstance() {
		if (instance == null)
			instance = new GheTau_DAO();
		return instance;
	}

	public ArrayList<GheTau> getalltbGheTau() {
		ArrayList<GheTau> gheTaus = new ArrayList<>();
		String sql = "Select * FROM GheTau";
		Connection con;

		try {
			con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String maGheTau = resultSet.getString(1);
				String tenLoaiGheTau = resultSet.getString(2);
				int soThuTuGhe = resultSet.getInt(3);
				String trangThai = resultSet.getString(4);
				String maToaTau = resultSet.getString(5);
				ToaTau toatau = new ToaTau(maToaTau);
				GheTau gheTau = new GheTau(maGheTau, tenLoaiGheTau, soThuTuGhe, trangThai, toatau);
				gheTaus.add(gheTau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gheTaus;
	}

	public ArrayList<GheTau> getDsGheTau(String maToaTau) {
		ArrayList<GheTau> gheTaus = new ArrayList<>();
		String sql = "SELECT * FROM GheTau GT JOIN ToaTau TT ON GT.maToaTau = TT.maToaTau WHERE GT.maToaTau = ?";
		Connection con;

		try {
			con = ConnectDB.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maToaTau);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String maGheTau = resultSet.getString(1);
				String tenLoaiGheTau = resultSet.getString(2);
				int soThuTuGhe = resultSet.getInt(3);
				String trangThai = resultSet.getString(4);
				String maToaTauSQL = resultSet.getString(5);
				ToaTau toatau = new ToaTau(maToaTau);
				GheTau gheTau = new GheTau(maGheTau, tenLoaiGheTau, soThuTuGhe, trangThai, toatau);
				gheTaus.add(gheTau);
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
			con = ConnectDB.getInstance().getConnection();
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

	// get toaTau theo maTau
	public ArrayList<GheTau> getGheTauTheoMaToaTau(String maToaTau) {
		ArrayList<GheTau> gheTaus = new ArrayList<>();
		String sql = "SELECT * FROM GheTau WHERE maToaTau = ?";
		Connection con;

		try {
			con = ConnectDB.getInstance().getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maToaTau);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String maGheTau = resultSet.getString(1);
				String tenLoaiGhe = resultSet.getString(2);
				int soThuTuGhe = resultSet.getInt(3);
				String trangThai = resultSet.getString(4);

				ToaTau toaTau = new ToaTau(maToaTau);
				GheTau gheTau = new GheTau(maGheTau, tenLoaiGhe, soThuTuGhe, trangThai, toaTau);
				gheTaus.add(gheTau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gheTaus;
	}
}