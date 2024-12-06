package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.Database;

public class TaiKhoan_DAO {

	public static TaiKhoan_DAO instance;

	public static TaiKhoan_DAO getInstance() {
		return instance == null ? instance = new TaiKhoan_DAO() : instance;
	}

	public ArrayList<TaiKhoan> getalltbTK() {

		ArrayList<TaiKhoan> taiKhoans = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Database.getInstance();
			con = Database.getInstance().getConnection();
			String sql = "SELECT * FROM TaiKhoan";
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				String maTaiKhoan = resultSet.getString(1);
				String tenDangNhap = resultSet.getString(2);
				String matKhau = resultSet.getString(3);
				boolean trangThai = resultSet.getBoolean(4);
				Timestamp timestamp = resultSet.getTimestamp(5);
				LocalDateTime ngayTaoTaiKhoan = timestamp.toLocalDateTime();
				String maNV = resultSet.getString(6);

				NhanVien nhanVien = new NhanVien(maNV);
				TaiKhoan taiKhoan = new TaiKhoan(maTaiKhoan, tenDangNhap, matKhau, trangThai, ngayTaoTaiKhoan,
						nhanVien);
				taiKhoans.add(taiKhoan);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (con != null)
					con.close(); // Đóng kết nối sau khi sử dụng
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return taiKhoans;
	}
}
