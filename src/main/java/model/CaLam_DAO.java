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

import javax.naming.OperationNotSupportedException;

import connectDB.ConnectDB;

public class CaLam_DAO implements DataAccessObject<CaLam> {

	private static CaLam_DAO instance;

	public static CaLam_DAO getInstance() {
		if (instance == null)
			instance = new CaLam_DAO();
		return instance;
	}

	public List<CaLam> findAll() throws SQLException {
		String sql = "SELECT * FROM CaLam";

		Connection con = ConnectDB.getInstance().getConnection();
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

		return list;
	}

	public CaLam findById(String id) throws SQLException {
		String sql = "SELECT * FROM CaLam WHERE maCa = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String tenCa = resultSet.getNString("tenCa");
			LocalTime thoiGianBatDau = resultSet.getTime("thoiGianBatDau").toLocalTime();
			LocalTime thoiGianKetThuc = resultSet.getTime("thoiGianKetThuc").toLocalTime();
			String ghiChu = resultSet.getNString("ghiChu");

			return new CaLam(id, tenCa, thoiGianBatDau, thoiGianKetThuc, ghiChu);
		}

		return null;
	}

	public boolean save(CaLam entity) throws SQLException {
		String sql = "INSERT INTO CaLam(maCa, tenCa, thoiGianBatDau, thoiGianKetThuc, ghiChu) VALUES(?, ?, ?, ?, ?)";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, entity.getMaCa());
		statement.setNString(2, entity.getTenCa());
		statement.setTime(3, Time.valueOf(entity.getThoiGianBatDau()));
		statement.setTime(4, Time.valueOf(entity.getThoiGianKetThuc()));
		statement.setNString(5, entity.getGhiChu());
		int count = statement.executeUpdate();

		return count > 0;
	}

	@Override
	public boolean update(CaLam entity) throws SQLException {
		String sql = "UPDATE CaLam SET tenCa = ?, thoiGianBatDau = ?, thoiGianKetThuc = ?, ghiChu = ? WHERE maCa = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setNString(1, entity.getTenCa());
		statement.setTime(2, Time.valueOf(entity.getThoiGianBatDau()));
		statement.setTime(3, Time.valueOf(entity.getThoiGianKetThuc()));
		statement.setNString(4, entity.getGhiChu());
		statement.setString(5, entity.getMaCa());
		int count = statement.executeUpdate();

		return count == 0;
	}

	@Override
	public boolean delete(CaLam entity) throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

}
