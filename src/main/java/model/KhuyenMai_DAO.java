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

public class KhuyenMai_DAO {

	public List<KhuyenMai> getAllKhuyenMai() throws SQLException {
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
			LocalDateTime hanSuDungKhuyenMai = resultSet.getTimestamp("hanSuDungKhuyenMai").toLocalDateTime();
			TinhTrangKhuyenMai tinhTrangKhuyenMai = TinhTrangKhuyenMai
					.fromValue(resultSet.getInt("tinhTrangKhuyenMai"));

			list.add(new KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, soLuongToiDa, hanSuDungKhuyenMai,
					tinhTrangKhuyenMai));
		}

		return list;
	}

	public KhuyenMai getKhuyenMai(String maKhuyenMai) throws SQLException {
		String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(0, maKhuyenMai);
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

	public boolean themKhuyenMai(KhuyenMai khuyenMai) throws SQLException {
		String sql = "INSERT INTO KhuyenMai(maKhuyenMai, tenKhuyenMai, noiDungKhuyenMai, soLuongToiDa, hanSuDungKhuyenMai, tinhTrangKhuyenMai) VALUES(?, ?, ?, ?, ?, ?)";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(0, khuyenMai.getMaKhuyenMai());
		statement.setNString(1, khuyenMai.getTenKhuyenMai());
		statement.setNString(2, khuyenMai.getNoiDungKhuyenMai());
		statement.setInt(3, khuyenMai.getSoLuongToiDa());
		statement.setTimestamp(4, Timestamp.valueOf(khuyenMai.getHanSuDungKhuyenMai()));
		statement.setInt(5, khuyenMai.getTinhTrangKhuyenMai().getValue());
		int count = statement.executeUpdate();

		return count > 0;
	}

	public boolean capNhatKhuyenMai(String maKhuyenMai, KhuyenMai khuyenMai) throws SQLException {
		String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, noiDungKhuyenMai = ?, soLuongToiDa = ?, hanSuDungKhuyenMai = ?, tinhTrangKhuyenMai = ? WHERE maCa = ?";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setNString(0, khuyenMai.getTenKhuyenMai());
		statement.setNString(1, khuyenMai.getNoiDungKhuyenMai());
		statement.setInt(2, khuyenMai.getSoLuongToiDa());
		statement.setTimestamp(3, Timestamp.valueOf(khuyenMai.getHanSuDungKhuyenMai()));
		statement.setInt(4, khuyenMai.getTinhTrangKhuyenMai().getValue());
		statement.setString(5, maKhuyenMai);
		int count = statement.executeUpdate();

		return count == 0;
	}

}
