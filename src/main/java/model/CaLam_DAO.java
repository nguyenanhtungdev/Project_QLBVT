package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;

public class CaLam_DAO {

	private static CaLam_DAO instance;

	public static CaLam_DAO getInstance() {
		return instance == null ? instance = new CaLam_DAO() : instance;
	}

	public List<CaLam> getAll() {
		String sql = "SELECT * FROM CaLam";
		List<CaLam> list = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				String maCa = resultSet.getString("maCa");
				String tenCa = resultSet.getNString("tenCa");
				LocalTime thoiGianBatDau = resultSet.getTime("thoiGianBatDau").toLocalTime();
				LocalTime thoiGianKetThuc = resultSet.getTime("thoiGianKetThuc").toLocalTime();
				String ghiChu = resultSet.getNString("ghiChu");

				list.add(new CaLam(maCa, tenCa, thoiGianBatDau, thoiGianKetThuc, ghiChu));
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

	public CaLam getByMaCa(String maCaLam) {
		String sql = "SELECT * FROM CaLam WHERE maCa = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maCaLam);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String tenCa = resultSet.getNString("tenCa");
				LocalTime thoiGianBatDau = resultSet.getTime("thoiGianBatDau").toLocalTime();
				LocalTime thoiGianKetThuc = resultSet.getTime("thoiGianKetThuc").toLocalTime();
				String ghiChu = resultSet.getNString("ghiChu");

				return new CaLam(maCaLam, tenCa, thoiGianBatDau, thoiGianKetThuc, ghiChu);
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

	public boolean add(CaLam entity) {
		String sql = "INSERT INTO CaLam(maCa, tenCa, thoiGianBatDau, thoiGianKetThuc, ghiChu) VALUES(?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, entity.getMaCa());
			statement.setNString(2, entity.getTenCa());
			statement.setTime(3, Time.valueOf(entity.getThoiGianBatDau()));
			statement.setTime(4, Time.valueOf(entity.getThoiGianKetThuc()));
			statement.setNString(5, entity.getGhiChu());
			int count = statement.executeUpdate();

			return count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean update(CaLam entity) {
		String sql = "UPDATE CaLam SET tenCa = ?, thoiGianBatDau = ?, thoiGianKetThuc = ?, ghiChu = ? WHERE maCa = ?";

		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setNString(1, entity.getTenCa());
			statement.setTime(2, Time.valueOf(entity.getThoiGianBatDau()));
			statement.setTime(3, Time.valueOf(entity.getThoiGianKetThuc()));
			statement.setNString(4, entity.getGhiChu());
			statement.setString(5, entity.getMaCa());
			int count = statement.executeUpdate();

			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
