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

public class KhuyenMai_DAO {

	private static KhuyenMai_DAO instance;

	public static KhuyenMai_DAO getInstance() {
		if (instance == null)
			instance = new KhuyenMai_DAO();
		return instance;
	}

//	public List<KhuyenMai> getAll() throws SQLException {
//		String sql = "SELECT * FROM KhuyenMai";
//
//		Connection con = ConnectDB.getInstance().getConnection();
//		Statement statement = con.createStatement();
//		ResultSet resultSet = statement.executeQuery(sql);
//
//		List<KhuyenMai> list = new ArrayList<>();
//		while (resultSet.next()) {
//			String maKhuyenMai = resultSet.getString("maKhuyenMai");
//			String tenKhuyenMai = resultSet.getNString("tenKhuyenMai");
//			String noiDungKhuyenMai = resultSet.getNString("noiDungKhuyenMai").isBlank() ? "NULL"
//					: resultSet.getNString("noiDungKhuyenMai");
//			int soLuongToiDa = resultSet.getInt("soLuongToiDa");
//			LocalDateTime hanSuDungKhuyenMai = resultSet.getTimestamp("hanSuDungKhuyenMai") == null
//					? LocalDateTime.of(2100, 12, 31, 23, 59)
//					: resultSet.getTimestamp("hanSuDungKhuyenMai").toLocalDateTime();
//			TinhTrangKhuyenMai tinhTrangKhuyenMai = TinhTrangKhuyenMai
//					.fromValue(resultSet.getInt("tinhTrangKhuyenMai"));
//
//			list.add(new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, soLuongToiDa, hanSuDungKhuyenMai,
//					tinhTrangKhuyenMai));
//		}
//
//		return list;
//	}

	public KhuyenMai getById(String maKhuyenMai) throws SQLException {
		String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, maKhuyenMai);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String tenKhuyenMai = resultSet.getNString("tenKhuyenMai");
			String noiDungKhuyenMai = resultSet.getNString("noiDungKhuyenMai");
			int soLuongToiDa = resultSet.getInt("soLuongToiDa");
			LocalDateTime hanSuDungKhuyenMai = resultSet.getTimestamp("hanSuDungKhuyenMai").toLocalDateTime();
			TinhTrangKhuyenMai tinhTrangKhuyenMai = TinhTrangKhuyenMai
					.fromValue(resultSet.getInt("tinhTrangKhuyenMai"));

			return new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, soLuongToiDa, hanSuDungKhuyenMai,
					tinhTrangKhuyenMai);
		}

		return null;
	}

	public KhuyenMai getById1(String id) throws SQLException {
		String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String tenKhuyenMai = resultSet.getNString("tenKhuyenMai");
			String noiDungKhuyenMai = resultSet.getNString("noiDungKhuyenMai");
			int soLuongToiDa = resultSet.getInt("soLuongToiDa");

			Timestamp timestamp = resultSet.getTimestamp("hanSuDungKhuyenMai");
			LocalDateTime hanSuDungKhuyenMai = null;

			if (timestamp != null) {
				hanSuDungKhuyenMai = timestamp.toLocalDateTime();
			} else {
				hanSuDungKhuyenMai = LocalDateTime.now();
			}
			TinhTrangKhuyenMai tinhTrangKhuyenMai = TinhTrangKhuyenMai
					.fromValue(resultSet.getInt("tinhTrangKhuyenMai"));
			double giamGia = resultSet.getDouble("giamGia");

			return new KhuyenMai(id, tenKhuyenMai, noiDungKhuyenMai, soLuongToiDa, hanSuDungKhuyenMai, giamGia,
					tinhTrangKhuyenMai);
		}

		return null;
	}

	public List<KhuyenMai> getAll1() throws SQLException {
		String sql = "SELECT * FROM KhuyenMai";

		Connection con = ConnectDB.getInstance().getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<KhuyenMai> list = new ArrayList<>();
		while (resultSet.next()) {
			String maKhuyenMai = resultSet.getString("maKhuyenMai");
			String tenKhuyenMai = resultSet.getNString("tenKhuyenMai");
			String noiDungKhuyenMai = resultSet.getNString("noiDungKhuyenMai");
			int soLuongToiDa = resultSet.getInt("soLuongToiDa");
			LocalDateTime hanSuDungKhuyenMai = resultSet.getTimestamp("hanSuDungKhuyenMai") != null
					? resultSet.getTimestamp("hanSuDungKhuyenMai").toLocalDateTime()
					: null;

			if (hanSuDungKhuyenMai != null && hanSuDungKhuyenMai.isAfter(LocalDateTime.now())) {
				TinhTrangKhuyenMai tinhTrangKhuyenMai = TinhTrangKhuyenMai
						.fromValue(resultSet.getInt("tinhTrangKhuyenMai"));
				double giamGia = resultSet.getDouble("giamGia");
				list.add(new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, soLuongToiDa, hanSuDungKhuyenMai,
						giamGia, tinhTrangKhuyenMai));
			}
		}

		return list;
	}

	public boolean them(KhuyenMai entity) throws SQLException {
		String sql = "INSERT INTO KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, soLuongToiDa, hanSuDungKhuyenMai, tinhTrangKhuyenMai, giamGia) VALUES(?, ?, ?, ?, ?, ?, ?)";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, entity.getMaKhuyenMai());
		statement.setNString(2, entity.getTenKhuyenMai());
		statement.setNString(3, entity.getNoiDungKhuyenMai());
		statement.setInt(4, entity.getSoLuongToiDa());
		statement.setTimestamp(5, Timestamp.valueOf(entity.getHanSuDungKhuyenMai()));
		statement.setInt(6, entity.getTinhTrangKhuyenMai().getValue());
		int count = statement.executeUpdate();
		statement.setDouble(7, entity.getGiamGia());

		return count > 0;
	}

	public boolean capNhat(KhuyenMai entity) throws SQLException {
		String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, noiDungKhuyenMai = ?, soLuongToiDa = ?, hanSuDungKhuyenMai = ?, tinhTrangKhuyenMai = ? WHERE maCa = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setNString(1, entity.getTenKhuyenMai());
		statement.setNString(2, entity.getNoiDungKhuyenMai());
		statement.setInt(3, entity.getSoLuongToiDa());
		statement.setTimestamp(4, Timestamp.valueOf(entity.getHanSuDungKhuyenMai()));
		statement.setInt(5, entity.getTinhTrangKhuyenMai().getValue());
		statement.setString(6, entity.getMaKhuyenMai());
		int count = statement.executeUpdate();

		return count == 0;
	}

	public List<KhuyenMai> getAll() throws SQLException {
		String sql = "SELECT * FROM KhuyenMai";

		Connection con = ConnectDB.getInstance().getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<KhuyenMai> list = new ArrayList<>();
		while (resultSet.next()) {
			String maKhuyenMai = resultSet.getString("maKhuyenMai");
			String tenKhuyenMai = resultSet.getNString("tenKhuyenMai");
			String noiDungKhuyenMai = resultSet.getNString("noiDungKhuyenMai");
			int soLuongToiDa = resultSet.getInt("soLuongToiDa");
			LocalDateTime hanSuDungKhuyenMai = resultSet.getTimestamp("hanSuDungKhuyenMai") != null
					? resultSet.getTimestamp("hanSuDungKhuyenMai").toLocalDateTime()
					: null;

			// Kiểm tra hạn sử dụng khuyến mãi
			if (hanSuDungKhuyenMai != null && hanSuDungKhuyenMai.isAfter(LocalDateTime.now())) {
				TinhTrangKhuyenMai tinhTrangKhuyenMai = TinhTrangKhuyenMai
						.fromValue(resultSet.getInt("tinhTrangKhuyenMai"));

				list.add(new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, soLuongToiDa, hanSuDungKhuyenMai,
						tinhTrangKhuyenMai));
			}
		}

		return list;
	}

	public List<KhuyenMai> getKhuyenMaiTheoTrangThai(TinhTrangKhuyenMai tinhTrang) throws SQLException {
		String sql = "SELECT * FROM KhuyenMai WHERE tinhTrangKhuyenMai = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, tinhTrang.getValue()); // Sử dụng phương thức lấy giá trị int của trạng thái nếu có

		ResultSet resultSet = statement.executeQuery();

		List<KhuyenMai> list = new ArrayList<>();
		while (resultSet.next()) {
			String maKhuyenMai = resultSet.getString("maKhuyenMai");
			String tenKhuyenMai = resultSet.getNString("tenKhuyenMai");
			String noiDungKhuyenMai = resultSet.getNString("noiDungKhuyenMai");
			int soLuongToiDa = resultSet.getInt("soLuongToiDa");
			LocalDateTime hanSuDungKhuyenMai = resultSet.getTimestamp("hanSuDungKhuyenMai") != null
					? resultSet.getTimestamp("hanSuDungKhuyenMai").toLocalDateTime()
					: null;

			// Kiểm tra hạn sử dụng khuyến mãi
			if (hanSuDungKhuyenMai != null && hanSuDungKhuyenMai.isAfter(LocalDateTime.now())) {
				TinhTrangKhuyenMai tinhTrangKhuyenMai = TinhTrangKhuyenMai
						.fromValue(resultSet.getInt("tinhTrangKhuyenMai"));

				list.add(new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, soLuongToiDa, hanSuDungKhuyenMai,
						tinhTrangKhuyenMai));
			}
		}

		return list;
	}

	public String getMaxMaKhuyenMai() throws SQLException {
		String sql = "SELECT MAX(maKhuyenMai) AS maxMaKhuyenMai FROM KhuyenMai";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			return resultSet.getString("maxMaKhuyenMai");
		}

		return null;
	}

}
