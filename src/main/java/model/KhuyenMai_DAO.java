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

import connectDB.Database;
import model.KhuyenMai.TinhTrangKhuyenMai;

public class KhuyenMai_DAO {

	private static KhuyenMai_DAO instance;

	public static KhuyenMai_DAO getInstance() {
		return instance == null ? instance = new KhuyenMai_DAO() : instance;
	}

	public List<KhuyenMai> getAll() {
		String sql = "SELECT * FROM KhuyenMai";

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			List<KhuyenMai> list = new ArrayList<>();
			while (resultSet.next()) {
				String maKhuyenMai = resultSet.getString("maKhuyenMai");
				String tenKhuyenMai = resultSet.getNString("tenKhuyenMai");
				String noiDungKhuyenMai = resultSet.getNString("noiDungKhuyenMai");
				int giamGia = resultSet.getInt("giamGia");
				int soLuongToiDa = resultSet.getInt("soLuongToiDa");
				LocalDateTime thoiGianBatDau = resultSet.getTimestamp("thoiGianBatDau").toLocalDateTime();
				LocalDateTime hanSuDungKhuyenMai = resultSet.getTimestamp("hanSuDungKhuyenMai").toLocalDateTime();
				TinhTrangKhuyenMai tinhTrangKhuyenMai = TinhTrangKhuyenMai
						.fromValue(resultSet.getInt("tinhTrangKhuyenMai"));

				list.add(new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, giamGia, soLuongToiDa,
						thoiGianBatDau, hanSuDungKhuyenMai, tinhTrangKhuyenMai));
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

	public KhuyenMai getByMaKhuyenMai(String maKhuyenMai) {
		String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maKhuyenMai);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String tenKhuyenMai = resultSet.getNString("tenKhuyenMai");
				String noiDungKhuyenMai = resultSet.getNString("noiDungKhuyenMai");
				int giamGia = resultSet.getInt("giamGia");
				int soLuongToiDa = resultSet.getInt("soLuongToiDa");
				LocalDateTime thoiGianBatDau = resultSet.getTimestamp("thoiGianBatDau").toLocalDateTime();
				LocalDateTime hanSuDungKhuyenMai = resultSet.getTimestamp("hanSuDungKhuyenMai").toLocalDateTime();
				TinhTrangKhuyenMai tinhTrangKhuyenMai = TinhTrangKhuyenMai
						.fromValue(resultSet.getInt("tinhTrangKhuyenMai"));

				return new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, giamGia, soLuongToiDa, thoiGianBatDau,
						hanSuDungKhuyenMai, tinhTrangKhuyenMai);
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

	public boolean add(KhuyenMai entity) {
		String sql = "INSERT INTO KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, giamGia, soLuongToiDa, thoiGianBatDau, hanSuDungKhuyenMai, tinhTrangKhuyenMai) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, entity.getMaKhuyenMai());
			statement.setNString(2, entity.getTenKhuyenMai());
			statement.setNString(3, entity.getNoiDungKhuyenMai());
			statement.setDouble(4, entity.getGiamGia());
			statement.setInt(5, entity.getSoLuongToiDa());
			statement.setTimestamp(6, Timestamp.valueOf(entity.getThoiGianBatDau()));
			statement.setTimestamp(7, Timestamp.valueOf(entity.getHanSuDungKhuyenMai()));
			statement.setInt(8, entity.getTinhTrangKhuyenMai().getValue());
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

	public boolean update(KhuyenMai entity) {
		String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, noiDungKhuyenMai = ?, giamGia = ?, soLuongToiDa = ?, thoiGianBatDau = ?, hanSuDungKhuyenMai = ?, tinhTrangKhuyenMai = ? WHERE maKhuyenMai = ?";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setNString(1, entity.getTenKhuyenMai());
			statement.setNString(2, entity.getNoiDungKhuyenMai());
			statement.setInt(3, entity.getGiamGia());
			statement.setInt(4, entity.getSoLuongToiDa());
			statement.setTimestamp(5, Timestamp.valueOf(entity.getThoiGianBatDau()));
			statement.setTimestamp(6, Timestamp.valueOf(entity.getHanSuDungKhuyenMai()));
			statement.setInt(7, entity.getTinhTrangKhuyenMai().getValue());
			statement.setString(8, entity.getMaKhuyenMai());
			int count = statement.executeUpdate();

			return count > 0;
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

	public List<KhuyenMai> getKhuyenMaiTheoTrangThai(TinhTrangKhuyenMai tinhTrang) {
		String sql = "SELECT * FROM KhuyenMai WHERE tinhTrangKhuyenMai = ?";

		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setInt(1, tinhTrang.getValue());
			ResultSet resultSet = statement.executeQuery();

			List<KhuyenMai> list = new ArrayList<>();
			while (resultSet.next()) {
				String maKhuyenMai = resultSet.getString("maKhuyenMai");
				String tenKhuyenMai = resultSet.getNString("tenKhuyenMai");
				String noiDungKhuyenMai = resultSet.getNString("noiDungKhuyenMai");
				int giamGia = resultSet.getInt("giamGia");
				int soLuongToiDa = resultSet.getInt("soLuongToiDa");
				LocalDateTime thoiGianBatDau = resultSet.getTimestamp("thoiGianBatDau").toLocalDateTime();
				LocalDateTime hanSuDungKhuyenMai = resultSet.getTimestamp("hanSuDungKhuyenMai").toLocalDateTime();
				TinhTrangKhuyenMai tinhTrangKhuyenMai = TinhTrangKhuyenMai
						.fromValue(resultSet.getInt("tinhTrangKhuyenMai"));

				list.add(new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, giamGia, soLuongToiDa,
						thoiGianBatDau, hanSuDungKhuyenMai, tinhTrangKhuyenMai));
			}

			return list;
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

		return null;
	}

	public String getMaxMaKhuyenMai() {
		String sql = "SELECT MAX(maKhuyenMai) AS maxMaKhuyenMai FROM KhuyenMai";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString("maxMaKhuyenMai");
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

	public List<KhuyenMai> getKhuyenMaiHetHanTrong7Ngay() {
		String sql = "SELECT * FROM KhuyenMai WHERE DATEDIFF(DAY, GETDATE(), hanSuDungKhuyenMai) <= 7 AND DATEDIFF(DAY, GETDATE(), hanSuDungKhuyenMai) >= 0";

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			List<KhuyenMai> list = new ArrayList<>();
			while (resultSet.next()) {
				String maKhuyenMai = resultSet.getString("maKhuyenMai");
				String tenKhuyenMai = resultSet.getNString("tenKhuyenMai");
				String noiDungKhuyenMai = resultSet.getNString("noiDungKhuyenMai");
				int giamGia = resultSet.getInt("giamGia");
				int soLuongToiDa = resultSet.getInt("soLuongToiDa");
				LocalDateTime thoiGianBatDau = resultSet.getTimestamp("thoiGianBatDau").toLocalDateTime();
				LocalDateTime hanSuDungKhuyenMai = resultSet.getTimestamp("hanSuDungKhuyenMai").toLocalDateTime();
				TinhTrangKhuyenMai tinhTrangKhuyenMai = TinhTrangKhuyenMai
						.fromValue(resultSet.getInt("tinhTrangKhuyenMai"));

				list.add(new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, giamGia, soLuongToiDa,
						thoiGianBatDau, hanSuDungKhuyenMai, tinhTrangKhuyenMai));
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
