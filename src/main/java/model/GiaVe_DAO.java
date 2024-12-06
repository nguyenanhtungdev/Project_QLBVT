package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;

public class GiaVe_DAO {

	private static GiaVe_DAO instance;

	public static GiaVe_DAO getInstance() {
		return instance == null ? instance = new GiaVe_DAO() : instance;
	}

	public List<GiaVe> getAll() throws SQLException {
		String sql = "SELECT * FROM GiaVe";

		Connection con = Database.getInstance().getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<GiaVe> list = new ArrayList<>();
		while (resultSet.next()) {
			String maGiaVe = resultSet.getString(1);
			double giaVe = resultSet.getDouble(2);
			float tiLeTangGia = resultSet.getFloat(3);
			LocalDateTime ngayCapNhap = resultSet.getTimestamp(4).toLocalDateTime();
			String ghiChu = resultSet.getString(5);

			list.add(new GiaVe(maGiaVe, giaVe, tiLeTangGia, ngayCapNhap, ghiChu));
		}

		return list;
	}

	public GiaVe getByMaGiaVe(String maGiaVe) throws SQLException {
		String sql = "SELECT * FROM GiaVe WHERE maGiaVe = ?";

		Connection con = Database.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, maGiaVe);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			String maGiaVeResult = resultSet.getString(1);
			double giaVeResult = resultSet.getDouble(2);
			float tiLeTangGia = resultSet.getFloat(3);
			LocalDateTime ngayCapNhap = resultSet.getTimestamp(4).toLocalDateTime();
			String ghiChu = resultSet.getString(5);

			return new GiaVe(maGiaVeResult, giaVeResult, tiLeTangGia, ngayCapNhap, ghiChu);
		}

		return null;
	}

	public GiaVe getGiaVeTheoChuyenTau(String maGheTau, String maChuyenTau) {
		GiaVe gv = null;
		String sql = "SELECT GV.maGiaVe, GV.giaVe, GV.tiLeTangGia, GV.ngayCapNhat, GV.ghiChu " + "FROM dbo.GiaVe GV "
				+ "JOIN dbo.ChuyenTau CT ON GV.maGiaVe = CT.maGiaVe " + "JOIN dbo.Tau T ON T.maTau = CT.maTau "
				+ "JOIN dbo.ToaTau TT ON TT.maTau = T.maTau " + "JOIN dbo.GheTau GT ON GT.maToaTau = TT.maToaTau "
				+ "WHERE GT.maGheTau = ? AND CT.maChuyenTau = ?";
		Connection con;
		try {
			con = Database.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			// Thiết lập các tham số cho câu truy vấn
			stmt.setString(1, maGheTau);
			stmt.setString(2, maChuyenTau);

			ResultSet resultSet = stmt.executeQuery();

			// Lấy kết quả
			if (resultSet.next()) {
				String maGiaVe = resultSet.getString("maGiaVe");
				double giaVe = resultSet.getDouble("giaVe");
				float tiLeTangGia = resultSet.getFloat("tiLeTangGia");
				LocalDateTime ngayCapNhat = resultSet.getTimestamp("ngayCapNhat").toLocalDateTime();
				String ghiChu = resultSet.getString("ghiChu");

				gv = new GiaVe(maGiaVe, giaVe, tiLeTangGia, ngayCapNhat, ghiChu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gv;
	}

}
