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

import javax.naming.OperationNotSupportedException;

import connectDB.ConnectDB;

public class ChuyenTau_DAO implements DataAccessObject<ChuyenTau> {

	private static ChuyenTau_DAO instance;

	public static ChuyenTau_DAO getInstance() {
		if (instance == null)
			instance = new ChuyenTau_DAO();
		return instance;
	}

	@Override
	public List<ChuyenTau> findAll() throws SQLException {
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

	@Override
	public ChuyenTau findById(String id) throws SQLException {
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

	@Override
	public boolean save(ChuyenTau entity) throws SQLException {
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

	@Override
	public boolean update(ChuyenTau entity) throws SQLException {
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

	@Override
	public boolean delete(ChuyenTau entity) throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

}
