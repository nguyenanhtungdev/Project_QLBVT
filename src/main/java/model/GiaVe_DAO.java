package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class GiaVe_DAO {

	private static GiaVe_DAO instance;

	public static GiaVe_DAO getInstance() {
		if (instance == null)
			instance = new GiaVe_DAO();
		return instance;
	}

	public ArrayList<GiaVe> getAllGiaVe() {
		ArrayList<GiaVe> dsGiaVe = new ArrayList<>();
		String sql = "SELECT * FROM GiaVe";
		Connection con;
		try {
			con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				String maGiaVe = resultSet.getString(1);
				double giaVe = resultSet.getDouble(2);
				float tiLeTangGia = resultSet.getFloat(3);
				LocalDateTime ngayCapNhap = resultSet.getTimestamp(4).toLocalDateTime();
				String ghiChu = resultSet.getString(5);

				GiaVe giave = new GiaVe(maGiaVe, giaVe, tiLeTangGia, ngayCapNhap, ghiChu);
				dsGiaVe.add(giave);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsGiaVe;
	}

	public boolean themGiaVe(GiaVe giaVe) {
		String sql = "INSERT INTO GiaVe (maGiaVe, giaVe, tiLeTangGia, ngayCapNhap, ghiChu) VALUES (?, ?, ?, ?, ?)";
		Connection con;
		try {
			con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, giaVe.getMaGiaVe());
			stmt.setDouble(2, giaVe.getGiaVe());
			stmt.setFloat(3, giaVe.getTiLeTangGia());
			stmt.setTimestamp(4, Timestamp.valueOf(giaVe.getNgayCapNhap()));
			stmt.setString(5, giaVe.getGhiChu());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean capNhapGiaVe(GiaVe giaVe) {
		String sql = "UPDATE GiaVe SET giaVe = ?, tiLeTangGia = ?, ngayCapNhap = ?, ghiChu = ? WHERE maGiaVe = ?";
		Connection con;
		try {
			con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setDouble(1, giaVe.getGiaVe());
			stmt.setFloat(2, giaVe.getTiLeTangGia());
			stmt.setTimestamp(3, Timestamp.valueOf(giaVe.getNgayCapNhap()));
			stmt.setString(4, giaVe.getGhiChu());
			stmt.setString(5, giaVe.getMaGiaVe());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public GiaVe getGiaVeTheoChuyenTau(String maGheTau, String maChuyenTau) {
		GiaVe gv = null;
		String sql = "SELECT GV.maGiaVe, GV.giaVe, GV.tiLeTangGia, GV.ngayCapNhat, GV.ghiChu " + "FROM dbo.GiaVe GV "
				+ "JOIN dbo.ChuyenTau CT ON GV.maGiaVe = CT.maGiaVe " + "JOIN dbo.Tau T ON T.maTau = CT.maTau "
				+ "JOIN dbo.ToaTau TT ON TT.maTau = T.maTau " + "JOIN dbo.GheTau GT ON GT.maToaTau = TT.maToaTau "
				+ "WHERE GT.maGheTau = ? AND CT.maChuyenTau = ?";
		Connection con;
		try {
			con = ConnectDB.getInstance().getConnection();
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
