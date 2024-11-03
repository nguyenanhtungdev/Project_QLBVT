package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import connectDB.ConnectDB;

public class ChiTiet_HoaDon_DAO implements DataAccessObject<ChiTiet_HoaDon> {

	private static ChiTiet_HoaDon_DAO instance;

	public static ChiTiet_HoaDon_DAO getInstance() {
		if (instance == null)
			instance = new ChiTiet_HoaDon_DAO();
		return instance;
	}

	public List<ChiTiet_HoaDon> findAll() throws SQLException {
		String sql = "SELECT * FROM ChiTiet_HoaDon";

		Connection con = ConnectDB.getInstance().getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<ChiTiet_HoaDon> list = new ArrayList<>();
		while (resultSet.next()) {
			int soLuong = resultSet.getInt(1);
			HoaDon hoaDon = new HoaDon(resultSet.getString(2));
			KhuyenMai khuyenMai = new KhuyenMai(resultSet.getString(3));
			VeTau veTau = new VeTau(resultSet.getString(4));

			list.add(new ChiTiet_HoaDon(soLuong, hoaDon, khuyenMai, veTau));
		}

		return list;
	}

	public ChiTiet_HoaDon findById(String id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

	public boolean save(ChiTiet_HoaDon entity) throws SQLException {
		String sql = "INSERT INTO ChiTiet_HoaDon(soLuong, maHoaDon, maKhuyenMai, maVeTau) VALUES(?, ?, ?, ?)";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(0, entity.getSoLuong());
		statement.setString(1, entity.getHoaDon().getMaHoaDon());
		statement.setString(2, entity.getKhuyenMai().getMaKhuyenMai());
		statement.setString(3, entity.getVeTau().getMaVeTau());
		int count = statement.executeUpdate();

		return count > 0;
	}

	@Override
	public boolean update(ChiTiet_HoaDon entity) throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

	@Override
	public boolean delete(ChiTiet_HoaDon entity) throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

}
