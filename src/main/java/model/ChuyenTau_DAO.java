package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;

public class ChuyenTau_DAO {

	public List<ChuyenTau> getAllChuyenTau() throws SQLException {
		String sql = "SELECT * FROM ChuyenTau";

		Connection con = ConnectDB.getInstance().getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<ChuyenTau> list = new ArrayList<>();
		while (resultSet.next()) {
			String maChuyenTau = resultSet.getString("maChuyenTau");
			String gaKhoiHanh = resultSet.getNString("gaKhoiHanh");
			String gaDen = resultSet.getNString("gaDen");
			LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp("thoiGianKhoiHanh").toLocalDateTime();
			LocalDateTime thoiGianDuKien = resultSet.getTimestamp("thoiGianDuKien").toLocalDateTime();
			String ghiChu = resultSet.getNString("ghiChu");

			list.add(new ChuyenTau(maChuyenTau, gaKhoiHanh, gaDen, thoiGianKhoiHanh, thoiGianDuKien, ghiChu));
		}

		return list;
	}

	public ChuyenTau getChuyenTau(String maChuyenTau) throws SQLException {
		String sql = "SELECT * FROM ChuyenTau WHERE maChuyenTau = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(0, maChuyenTau);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String gaKhoiHanh = resultSet.getNString("gaKhoiHanh");
			String gaDen = resultSet.getNString("gaDen");
			LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp("thoiGianKhoiHanh").toLocalDateTime();
			LocalDateTime thoiGianDuKien = resultSet.getTimestamp("thoiGianDuKien").toLocalDateTime();
			String ghiChu = resultSet.getNString("ghiChu");

			return new ChuyenTau(maChuyenTau, gaKhoiHanh, gaDen, thoiGianKhoiHanh, thoiGianDuKien, ghiChu);
		}

		return null;
	}

	public boolean themChuyenTau(ChuyenTau chuyenTau) throws SQLException {
		String sql = "INSERT INTO ChuyenTau(maChuyenTau, gaKhoiHanh, gaDen, thoiGianKhoiHanh, thoiGianDuKien, ghiChu) VALUES(?, ?, ?, ?, ?, ?)";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(0, chuyenTau.getMaChuyenTau());
		statement.setNString(1, chuyenTau.getGaKhoiHanh());
		statement.setNString(2, chuyenTau.getGaDen());
		statement.setTimestamp(3, Timestamp.valueOf(chuyenTau.getThoiGianKhoiHanh()));
		statement.setTimestamp(4, Timestamp.valueOf(chuyenTau.getThoiGianDuKien()));
		statement.setNString(5, chuyenTau.getGhiChu());
		int count = statement.executeUpdate();

		return count > 0;
	}

	public boolean capNhatChuyenTau(String maChuyenTau, ChuyenTau chuyenTau) throws SQLException {
		String sql = "UPDATE ChuyenTau SET gaKhoiHanh = ?, gaDen = ?, thoiGianKhoiHanh = ?, thoiGianDuKien = ?, ghiChu = ? WHERE maChuyenTau = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setNString(0, chuyenTau.getGaKhoiHanh());
		statement.setNString(1, chuyenTau.getGaDen());
		statement.setTimestamp(2, Timestamp.valueOf(chuyenTau.getThoiGianKhoiHanh()));
		statement.setTimestamp(3, Timestamp.valueOf(chuyenTau.getThoiGianDuKien()));
		statement.setNString(4, chuyenTau.getGhiChu());
		statement.setString(5, maChuyenTau);
		int count = statement.executeUpdate();

		return count == 0;
	}

}
