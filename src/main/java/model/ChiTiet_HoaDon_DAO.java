package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;

public class ChiTiet_HoaDon_DAO {

	private static ChiTiet_HoaDon_DAO instance;

	public static ChiTiet_HoaDon_DAO getInstance() {
		return instance == null ? instance = new ChiTiet_HoaDon_DAO() : instance;
	}

	public List<ChiTiet_HoaDon> getAll() throws SQLException {
		String sql = "SELECT * FROM ChiTiet_HoaDon";

		Connection con = Database.getInstance().getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<ChiTiet_HoaDon> list = new ArrayList<>();
		while (resultSet.next()) {
			int soLuong = resultSet.getInt("soLuong");

			HoaDon hoaDon = new HoaDon(resultSet.getString(2));
			KhuyenMai khuyenMai = new KhuyenMai(resultSet.getString(3));
			VeTau veTau = new VeTau(resultSet.getString(4));

			list.add(new ChiTiet_HoaDon(soLuong, hoaDon, khuyenMai, veTau));
		}

		resultSet.close();
		statement.close();
		return list;
	}

	public boolean add(ChiTiet_HoaDon ct) throws SQLException {
		String sql = "INSERT INTO ChiTiet_HoaDon(soLuong, maHoaDon, maKhuyenMai, maVeTau) VALUES(?, ?, ?, ?)";

		Connection con = Database.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(0, ct.getSoLuong());
		statement.setString(1, ct.getHoaDon().getMaHoaDon());
		statement.setString(2, ct.getKhuyenMai().getMaKhuyenMai());
		statement.setString(3, ct.getVeTau().getMaVeTau());
		int count = statement.executeUpdate();

		statement.close();
		return count == 1;
	}

	public List<ChiTiet_HoaDon> getByMaHoaDon(String maHoaDon) throws SQLException {
		String sql = "SELECT * FROM ChiTiet_HoaDon WHERE maHoaDon = ?";

		Connection con = Database.getInstance().getConnection();

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, maHoaDon);
		ResultSet resultSet = statement.executeQuery();

		List<ChiTiet_HoaDon> list = new ArrayList<>();
		while (resultSet.next()) {
			int soLuong = resultSet.getInt("soLuong");

			String maKhuyenMai = resultSet.getString("maKhuyenMai");
			String maVeTau = resultSet.getString("maVeTau");

			HoaDon hoaDon = new HoaDon(maHoaDon);
			KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai);
			VeTau veTau = new VeTau(maVeTau);

			list.add(new ChiTiet_HoaDon(soLuong, hoaDon, khuyenMai, veTau));
		}

		resultSet.close();
		statement.close();
		return list;
	}

}
