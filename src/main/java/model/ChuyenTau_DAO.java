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

import connectDB.ConnectDB;

public class ChuyenTau_DAO {

	private static ChuyenTau_DAO instance;

	public static ChuyenTau_DAO getInstance() {
		if (instance == null)
			instance = new ChuyenTau_DAO();
		return instance;
	}

	public List<ChuyenTau> getAll() throws SQLException {
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
			GiaVe giaVe = new GiaVe(resultSet.getString("maGiaVe"));
			Tau tau = new Tau(resultSet.getString("maTau"));

			list.add(new ChuyenTau(maChuyenTau, gaKhoiHanh, gaDen, thoiGianKhoiHanh, thoiGianDuKien, ghiChu, giaVe,
					tau));
		}

		return list;
	}

	public ChuyenTau getById(String id) throws SQLException {
		String sql = "SELECT * FROM ChuyenTau WHERE maChuyenTau = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String gaKhoiHanh = resultSet.getNString("gaKhoiHanh");
			String gaDen = resultSet.getNString("gaDen");
			LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp("thoiGianKhoiHanh").toLocalDateTime();
			LocalDateTime thoiGianDuKien = resultSet.getTimestamp("thoiGianDuKien").toLocalDateTime();
			String ghiChu = resultSet.getNString("ghiChu");
			GiaVe giaVe = new GiaVe(resultSet.getString("maGiaVe"));
			Tau tau = new Tau(resultSet.getString("maTau"));

			return new ChuyenTau(id, gaKhoiHanh, gaDen, thoiGianKhoiHanh, thoiGianDuKien, ghiChu, giaVe, tau);
		}

		return null;
	}

	public boolean them(ChuyenTau entity) throws SQLException {
		String sql = "INSERT INTO ChuyenTau(maChuyenTau, gaKhoiHanh, gaDen, thoiGianKhoiHanh, thoiGianDuKien, ghiChu, maGiaVe, maTau) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, entity.getMaChuyenTau());
		statement.setNString(2, entity.getGaKhoiHanh());
		statement.setNString(3, entity.getGaDen());
		statement.setTimestamp(4, Timestamp.valueOf(entity.getThoiGianKhoiHanh()));
		statement.setTimestamp(5, Timestamp.valueOf(entity.getThoiGianDuKien()));
		statement.setNString(6, entity.getGhiChu());
		statement.setString(7, entity.getGiaVe().getMaGiaVe());
		statement.setString(8, entity.getTau().getMaTau());
		int count = statement.executeUpdate();

		return count > 0;
	}

	public boolean capNhat(ChuyenTau entity) throws SQLException {
		String sql = "UPDATE ChuyenTau SET gaKhoiHanh = ?, gaDen = ?, thoiGianKhoiHanh = ?, thoiGianDuKien = ?, ghiChu = ?, maGiaVe = ?, maTau = ? WHERE maChuyenTau = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setNString(1, entity.getGaKhoiHanh());
		statement.setNString(2, entity.getGaDen());
		statement.setTimestamp(3, Timestamp.valueOf(entity.getThoiGianKhoiHanh()));
		statement.setTimestamp(4, Timestamp.valueOf(entity.getThoiGianDuKien()));
		statement.setNString(5, entity.getGhiChu());
		statement.setNString(6, entity.getGiaVe().getMaGiaVe());
		statement.setNString(7, entity.getTau().getMaTau());
		statement.setString(8, entity.getMaChuyenTau());
		int count = statement.executeUpdate();

		return count == 0;
	}

	public ArrayList<ChuyenTau> timKiemChuyenTau(String gaDi, String gaDen, String ngayDi) throws SQLException {
		String sql = "SELECT * FROM ChuyenTau WHERE gaKhoiHanh = ? AND gaDen = ? AND CONVERT(date, thoiGianKhoiHanh) = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, gaDi);
		preparedStatement.setString(2, gaDen);
		preparedStatement.setString(3, ngayDi);

		ResultSet resultSet = preparedStatement.executeQuery();

		ArrayList<ChuyenTau> list = new ArrayList<>();
		while (resultSet.next()) {
			String maChuyenTau = resultSet.getString("maChuyenTau");
			String gaKhoiHanh = resultSet.getNString("gaKhoiHanh");
			String gaDenResult = resultSet.getNString("gaDen");
			LocalDateTime thoiGianKhoiHanh = resultSet.getTimestamp("thoiGianKhoiHanh").toLocalDateTime();
			LocalDateTime thoiGianDuKien = resultSet.getTimestamp("thoiGianDuKien").toLocalDateTime();
			String ghiChu = resultSet.getNString("ghiChu");
			String maTau = resultSet.getString("maTau");
			String maGiaVe = resultSet.getString("maGiaVe");
			Tau tau = new Tau(maTau);
			GiaVe giaVe = new GiaVe(maGiaVe);

			list.add(new ChuyenTau(maChuyenTau, gaKhoiHanh, gaDenResult, thoiGianKhoiHanh, thoiGianDuKien, ghiChu,
					giaVe, tau));
		}
		return list;
	}

	public Map<String, Integer> layThongTinGhe(String maChuyenTau) {
		String sql = """
				SELECT
				    COUNT(ghe.maGheTau) AS tongSoGhe,
				    SUM(CASE WHEN ghe.trangThai = 'TRONG' THEN 1 ELSE 0 END) AS soGheConLai
				FROM
				    ChuyenTau chuyen
				JOIN
				    Tau tau ON chuyen.maTau = tau.maTau
				JOIN
				    ToaTau toa ON tau.maTau = toa.maTau
				JOIN
				    GheTau ghe ON toa.maToaTau = ghe.maToaTau
				WHERE
				    chuyen.maChuyenTau = ?
				""";

		try {
			Map<String, Integer> result = new HashMap<>();
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maChuyenTau);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result.put("tongSoGhe", rs.getInt("tongSoGhe"));
				result.put("soGheConLai", rs.getInt("soGheConLai"));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}
}
