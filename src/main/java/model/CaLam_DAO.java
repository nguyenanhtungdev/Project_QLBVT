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

import connectDB.ConnectDB;

public class CaLam_DAO {

	public List<CaLam> getAllCaLam() throws SQLException {
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

	public CaLam getCaLam(String maCa) throws SQLException {
		String sql = "SELECT * FROM CaLam WHERE maCa = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(0, maCa);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String tenCa = resultSet.getNString("tenCa");
			LocalTime thoiGianBatDau = resultSet.getTime("thoiGianBatDau").toLocalTime();
			LocalTime thoiGianKetThuc = resultSet.getTime("thoiGianKetThuc").toLocalTime();
			String ghiChu = resultSet.getNString("ghiChu");

			return new CaLam(maCa, tenCa, thoiGianBatDau, thoiGianKetThuc, ghiChu);
		}

		return null;
	}

	public boolean themCaLam(CaLam caLam) throws SQLException {
		String sql = "INSERT INTO CaLam(maCa, tenCa, thoiGianBatDau, thoiGianKetThuc, ghiChu) VALUES(?, ?, ?, ?, ?)";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(0, caLam.getMaCa());
		statement.setNString(1, caLam.getTenCa());
		statement.setTime(2, Time.valueOf(caLam.getThoiGianBatDau()));
		statement.setTime(3, Time.valueOf(caLam.getThoiGianKetThuc()));
		statement.setNString(4, caLam.getGhiChu());
		int count = statement.executeUpdate();

		return count > 0;
	}

	public boolean capNhatCaLam(String maCa, CaLam caLam) throws SQLException {
		String sql = "UPDATE CaLam SET tenCa = ?, thoiGianBatDau = ?, thoiGianKetThuc = ?, ghiChu = ? WHERE maCa = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setNString(0, caLam.getTenCa());
		statement.setTime(1, Time.valueOf(caLam.getThoiGianBatDau()));
		statement.setTime(2, Time.valueOf(caLam.getThoiGianKetThuc()));
		statement.setNString(3, caLam.getGhiChu());
		statement.setString(4, maCa);
		int count = statement.executeUpdate();

		return count == 0;
	}

}
