package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import connectDB.Database;

public class ChiTiet_HoaDon_DAO {

	private static ChiTiet_HoaDon_DAO instance;

	public static ChiTiet_HoaDon_DAO getInstance() {
		return instance == null ? instance = new ChiTiet_HoaDon_DAO() : instance;
	}

	public List<ChiTiet_HoaDon> getAll() {
		String sql = "SELECT * FROM ChiTiet_HoaDon";

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			List<ChiTiet_HoaDon> list = new ArrayList<>();
			while (resultSet.next()) {
				int soLuong = resultSet.getInt("soLuong");

				HoaDon hoaDon = new HoaDon(resultSet.getString(2));
				String maKhuyenMai = resultSet.getString(3);
				KhuyenMai khuyenMai = maKhuyenMai != null ? new KhuyenMai(maKhuyenMai) : null;
				VeTau veTau = new VeTau(resultSet.getString(4));

				list.add(new ChiTiet_HoaDon(soLuong, hoaDon, khuyenMai, veTau));
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

	public boolean add(ChiTiet_HoaDon ct) {
		String sql = "INSERT INTO ChiTiet_HoaDon(soLuong, maHoaDon, maKhuyenMai, maVeTau) VALUES(?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setInt(1, ct.getSoLuong());
			statement.setString(2, ct.getHoaDon().getMaHoaDon());

			if (ct.getKhuyenMai() != null && ct.getKhuyenMai().getMaKhuyenMai() != null) {
				statement.setString(3, ct.getKhuyenMai().getMaKhuyenMai());
			} else {
				statement.setNull(3, java.sql.Types.VARCHAR);
			}

			statement.setString(4, ct.getVeTau().getMaVeTau());

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

	public List<ChiTiet_HoaDon> getByMaHoaDon(String maHoaDon) {
		String sql = "SELECT * FROM ChiTiet_HoaDon WHERE maHoaDon = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maHoaDon);
			resultSet = statement.executeQuery();

			List<ChiTiet_HoaDon> list = new ArrayList<>();
			while (resultSet.next()) {
				int soLuong = resultSet.getInt("soLuong");

				String maKhuyenMai = resultSet.getString("maKhuyenMai");
				String maVeTau = resultSet.getString("maVeTau");

				HoaDon hoaDon = new HoaDon(maHoaDon);
				KhuyenMai khuyenMai = maKhuyenMai == null ? null : new KhuyenMai(maKhuyenMai);
				VeTau veTau = new VeTau(maVeTau);

				list.add(new ChiTiet_HoaDon(soLuong, hoaDon, khuyenMai, veTau));
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

	public List<ChiTiet_HoaDon> getByMaHoaDon(List<String> maHoaDons) {
		String sql = "SELECT * FROM ChiTiet_HoaDon WHERE maHoaDon IN ("
				+ String.join(",", Collections.nCopies(maHoaDons.size(), "?")) + ")";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			for (int i = 0; i < maHoaDons.size(); i++) {
				statement.setString(i + 1, maHoaDons.get(i));
			}
			resultSet = statement.executeQuery();

			List<ChiTiet_HoaDon> list = new ArrayList<>();
			while (resultSet.next()) {
				int soLuong = resultSet.getInt("soLuong");

				String maHoaDon = resultSet.getString("maHoaDon");
				String maKhuyenMai = resultSet.getString("maKhuyenMai");
				String maVeTau = resultSet.getString("maVeTau");

				HoaDon hoaDon = new HoaDon(maHoaDon);
				KhuyenMai khuyenMai = maKhuyenMai == null ? null : new KhuyenMai(maKhuyenMai);
				VeTau veTau = new VeTau(maVeTau);

				list.add(new ChiTiet_HoaDon(soLuong, hoaDon, khuyenMai, veTau));
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
