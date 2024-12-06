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

	public List<CaLam> getAll() throws SQLException {
		String sql = "SELECT * FROM CaLam";

		Connection con = Database.getInstance().getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<CaLam> list = new ArrayList<>();
		while (resultSet.next()) {
			String maCa = resultSet.getString("maCa");
			String tenCa = resultSet.getNString("tenCa");
			LocalTime thoiGianBatDau = resultSet.getTime("thoiGianBatDau").toLocalTime();
			LocalTime thoiGianKetThuc = resultSet.getTime("thoiGianKetThuc").toLocalTime();
			String ghiChu = resultSet.getNString("ghiChu");

			list.add(new CaLam(maCa, tenCa, thoiGianBatDau, thoiGianKetThuc, ghiChu));
		}

		resultSet.close();
		statement.close();
		return list;
	}

	public CaLam getByMaCa(String maCaLam) throws SQLException {
		String sql = "SELECT * FROM CaLam WHERE maCa = ?";

		Connection con = Database.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, maCaLam);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String tenCa = resultSet.getNString("tenCa");
			LocalTime thoiGianBatDau = resultSet.getTime("thoiGianBatDau").toLocalTime();
			LocalTime thoiGianKetThuc = resultSet.getTime("thoiGianKetThuc").toLocalTime();
			String ghiChu = resultSet.getNString("ghiChu");

			return new CaLam(maCaLam, tenCa, thoiGianBatDau, thoiGianKetThuc, ghiChu);
		}

		resultSet.close();
		statement.close();
		return null;
	}

	public boolean add(CaLam entity) throws SQLException {
		String sql = "INSERT INTO CaLam(maCa, tenCa, thoiGianBatDau, thoiGianKetThuc, ghiChu) VALUES(?, ?, ?, ?, ?)";

		Connection con = Database.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, entity.getMaCa());
		statement.setNString(2, entity.getTenCa());
		statement.setTime(3, Time.valueOf(entity.getThoiGianBatDau()));
		statement.setTime(4, Time.valueOf(entity.getThoiGianKetThuc()));
		statement.setNString(5, entity.getGhiChu());
		int count = statement.executeUpdate();

		statement.close();
		return count == 1;
	}

	public boolean update(CaLam entity) throws SQLException {
		String sql = "UPDATE CaLam SET tenCa = ?, thoiGianBatDau = ?, thoiGianKetThuc = ?, ghiChu = ? WHERE maCa = ?";

		Connection con = Database.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setNString(1, entity.getTenCa());
		statement.setTime(2, Time.valueOf(entity.getThoiGianBatDau()));
		statement.setTime(3, Time.valueOf(entity.getThoiGianKetThuc()));
		statement.setNString(4, entity.getGhiChu());
		statement.setString(5, entity.getMaCa());
		int count = statement.executeUpdate();

		statement.close();
		return count == 1;
	}

	public boolean delete(CaLam entity) throws SQLException {
		String sql = "DELETE FROM CaLam WHERE maCa = ?";

		Connection con = Database.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, entity.getMaCa());
		int count = statement.executeUpdate();

		statement.close();
		return count == 1;
	}

}
