package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;

public class NhanVien_CaLam_DAO {

	public static NhanVien_CaLam_DAO instance;

	public static NhanVien_CaLam_DAO getInstance() {
		return instance == null ? instance = new NhanVien_CaLam_DAO() : instance;
	}

	public List<NhanVien_CaLam> getAll() {
		String sql = "Select * FROM NhanVien_CaLam";

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			List<NhanVien_CaLam> list = new ArrayList<>();
			while (resultSet.next()) {
				LocalDateTime thoiGianNhanCa = resultSet.getTimestamp("thoiGianNhanCa").toLocalDateTime();
				LocalDateTime thoiGianKetThucCa = resultSet.getTimestamp("thoiGianKetThucCa").toLocalDateTime();
				String maNhanVien = resultSet.getString("maNV");
				String maCaLam = resultSet.getString("maCa");

				NhanVien nhanVien = new NhanVien(maNhanVien);
				CaLam caLam = new CaLam(maCaLam);

				list.add(new NhanVien_CaLam(thoiGianNhanCa, thoiGianKetThucCa, nhanVien, caLam));
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

	public boolean saveOrUpdate(NhanVien_CaLam nhanVienCaLam) {
		String checkSql = "SELECT COUNT(*) FROM NhanVien_CaLam WHERE maNV = ? AND maCa = ? AND thoiGianNhanCa = ? AND thoiGianKetThucCa = ?";
		String updateSql = "UPDATE NhanVien_CaLam SET thoiGianNhanCa = ?, thoiGianKetThucCa = ?, maCa = ? WHERE maNV = ? AND maCa = ? AND thoiGianNhanCa = ? AND thoiGianKetThucCa = ?";
		String insertSql = "INSERT INTO NhanVien_CaLam (maNV, maCa, thoiGianNhanCa, thoiGianKetThucCa) VALUES (?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement checkStmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = Database.getInstance().getConnection();
			checkStmt = con.prepareStatement(checkSql);
			checkStmt.setString(1, nhanVienCaLam.getNhanVien().getMaNV());
			checkStmt.setString(2, nhanVienCaLam.getCaLam().getMaCa());
			checkStmt.setTimestamp(3, Timestamp.valueOf(nhanVienCaLam.getThoiGianNhanCa()));
			checkStmt.setTimestamp(4, Timestamp.valueOf(nhanVienCaLam.getThoiGianKetThucCa()));
			rs = checkStmt.executeQuery();

			boolean exists = false;
			if (rs.next()) {
				exists = rs.getInt(1) > 0;
			}

			if (exists) {
				stmt = con.prepareStatement(updateSql);
				stmt.setTimestamp(1, Timestamp.valueOf(nhanVienCaLam.getThoiGianNhanCa()));
				stmt.setTimestamp(2, Timestamp.valueOf(nhanVienCaLam.getThoiGianKetThucCa()));
				stmt.setString(3, nhanVienCaLam.getCaLam().getMaCa());
				stmt.setString(4, nhanVienCaLam.getNhanVien().getMaNV());
				stmt.setString(5, nhanVienCaLam.getCaLam().getMaCa());
				stmt.setTimestamp(6, Timestamp.valueOf(nhanVienCaLam.getThoiGianNhanCa()));
				stmt.setTimestamp(7, Timestamp.valueOf(nhanVienCaLam.getThoiGianKetThucCa()));
			} else {
				stmt = con.prepareStatement(insertSql);
				stmt.setString(1, nhanVienCaLam.getNhanVien().getMaNV());
				stmt.setString(2, nhanVienCaLam.getCaLam().getMaCa());
				stmt.setTimestamp(3, Timestamp.valueOf(nhanVienCaLam.getThoiGianNhanCa()));
				stmt.setTimestamp(4, Timestamp.valueOf(nhanVienCaLam.getThoiGianKetThucCa()));
			}
			int affectedRows = stmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (checkStmt != null)
					checkStmt.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public List<NhanVien_CaLam> getByNhanVienAndNgay(String maNV, LocalDate ngay) {
		String sql = "SELECT * FROM NhanVien_CaLam WHERE maNV = ? AND CAST(thoiGianNhanCa AS DATE) = ?";
		List<NhanVien_CaLam> resultList = new ArrayList<>();
		try (Connection con = Database.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maNV);
			stmt.setDate(2, java.sql.Date.valueOf(ngay));
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					NhanVien nhanVien = new NhanVien(rs.getString("maNV"));
					CaLam caLam = new CaLam(rs.getString("maCa"));
					LocalDateTime thoiGianNhanCa = rs.getTimestamp("thoiGianNhanCa").toLocalDateTime();
					LocalDateTime thoiGianKetThucCa = rs.getTimestamp("thoiGianKetThucCa").toLocalDateTime();
					resultList.add(new NhanVien_CaLam(thoiGianNhanCa, thoiGianKetThucCa, nhanVien, caLam));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public boolean delete(NhanVien_CaLam nhanVienCaLam) {
		String sql = "DELETE FROM NhanVien_CaLam WHERE maNV = ? AND maCa = ? AND thoiGianNhanCa = ? AND thoiGianKetThucCa = ?";
		try (Connection con = Database.getInstance().getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, nhanVienCaLam.getNhanVien().getMaNV());
			stmt.setString(2, nhanVienCaLam.getCaLam().getMaCa());
			stmt.setTimestamp(3, Timestamp.valueOf(nhanVienCaLam.getThoiGianNhanCa()));
			stmt.setTimestamp(4, Timestamp.valueOf(nhanVienCaLam.getThoiGianKetThucCa()));
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
