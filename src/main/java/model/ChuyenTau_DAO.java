package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connectDB.Database;

public class ChuyenTau_DAO {

	private static ChuyenTau_DAO instance;

	public static ChuyenTau_DAO getInstance() {
		return instance == null ? instance = new ChuyenTau_DAO() : instance;
	}

	public List<ChuyenTau> getAll() {
		String sql = "SELECT * FROM ChuyenTau";

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			List<ChuyenTau> list = new ArrayList<>();
			while (resultSet.next()) {
				String maChuyenTau = resultSet.getString("maChuyenTau");
				String gaKhoiHanh = resultSet.getNString("gaKhoiHanh");
				String gaDen = resultSet.getNString("gaDen");
				LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp("thoiGianKhoiHanh").toLocalDateTime();
				LocalDateTime thoiGianDuKien = resultSet.getTimestamp("thoiGianDuKien").toLocalDateTime();
				String ghiChu = resultSet.getNString("ghiChu");

				GiaVe giaVe = new GiaVe(resultSet.getString("maGiaVe"));

				list.add(
						new ChuyenTau(maChuyenTau, gaKhoiHanh, gaDen, thoiGianKhoiHanh, thoiGianDuKien, ghiChu, giaVe));
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

	public ChuyenTau getByMaChuyenTau(String id) {
		String sql = "SELECT * FROM ChuyenTau WHERE maChuyenTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String maChuyenTau = resultSet.getString("maChuyenTau");
				String gaKhoiHanh = resultSet.getNString("gaKhoiHanh");
				String gaDen = resultSet.getNString("gaDen");
				LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp("thoiGianKhoiHanh").toLocalDateTime();
				LocalDateTime thoiGianDuKien = resultSet.getTimestamp("thoiGianDuKien").toLocalDateTime();
				String ghiChu = resultSet.getNString("ghiChu");

				GiaVe giaVe = new GiaVe(resultSet.getString("maGiaVe"));

				return new ChuyenTau(maChuyenTau, gaKhoiHanh, gaDen, thoiGianKhoiHanh, thoiGianDuKien, ghiChu, giaVe);
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

	public boolean add(ChuyenTau entity) {
		String sql = "INSERT INTO ChuyenTau(maChuyenTau, gaKhoiHanh, gaDen, thoiGianKhoiHanh, thoiGianDuKien, ghiChu, maGiaVe) VALUES(?, ?, ?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, entity.getMaChuyenTau());
			statement.setNString(2, entity.getGaKhoiHanh());
			statement.setNString(3, entity.getGaDen());
			statement.setTimestamp(4, Timestamp.valueOf(entity.getThoiGianKhoiHanh()));
			statement.setTimestamp(5, Timestamp.valueOf(entity.getThoiGianDuKien()));
			statement.setNString(6, entity.getGhiChu());
			statement.setString(7, entity.getGiaVe().getMaGiaVe());
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

	public boolean update(ChuyenTau entity) {
		String sql = "UPDATE ChuyenTau SET gaKhoiHanh = ?, gaDen = ?, thoiGianKhoiHanh = ?, thoiGianDuKien = ?, ghiChu = ?, maGiaVe = ? WHERE maChuyenTau = ?";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setNString(1, entity.getGaKhoiHanh());
			statement.setNString(2, entity.getGaDen());
			statement.setTimestamp(3, Timestamp.valueOf(entity.getThoiGianKhoiHanh()));
			statement.setTimestamp(4, Timestamp.valueOf(entity.getThoiGianDuKien()));
			statement.setNString(5, entity.getGhiChu());
			statement.setNString(6, entity.getGiaVe().getMaGiaVe());
			statement.setString(8, entity.getMaChuyenTau());
			int count = statement.executeUpdate();

			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<ChuyenTau> timKiemChuyenTau(String gaDi, String gaDen, String ngayDi) {
		String sql = "SELECT * FROM ChuyenTau WHERE gaKhoiHanh = ? AND gaDen = ? AND CONVERT(date, thoiGianKhoiHanh) = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, gaDi);
			statement.setString(2, gaDen);
			statement.setString(3, ngayDi);
			resultSet = statement.executeQuery();

			ArrayList<ChuyenTau> list = new ArrayList<>();
			while (resultSet.next()) {
				String maChuyenTau = resultSet.getString("maChuyenTau");
				String gaKhoiHanh = resultSet.getNString("gaKhoiHanh");
				String gaDenResult = resultSet.getNString("gaDen");
				LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp("thoiGianKhoiHanh").toLocalDateTime();
				LocalDateTime thoiGianDuKien = resultSet.getTimestamp("thoiGianDuKien").toLocalDateTime();
				String ghiChu = resultSet.getNString("ghiChu");
				String maGiaVe = resultSet.getString("maGiaVe");

				GiaVe giaVe = new GiaVe(maGiaVe);

				list.add(new ChuyenTau(maChuyenTau, gaKhoiHanh, gaDenResult, thoiGianKhoiHanh, thoiGianDuKien, ghiChu,
						giaVe));
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
