package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.Database;

public class ThongTinTram_DAO {

	private static ThongTinTram_DAO instance;

	public static ThongTinTram_DAO getInstance() {
		return instance == null ? instance = new ThongTinTram_DAO() : instance;
	}

	public ThongTinTram getByMaNhaGa(String maNhaGa) {
		String sql = "SELECT* FROM ThongTinTram WHERE maNhaGa = ?";

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = Database.getInstance().getConnection();
			statement = con.prepareStatement(sql);
			statement.setString(1, maNhaGa);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String tenNhaGa = resultSet.getString("tenNhaGa");
				String diaChi = resultSet.getString("diaChi");
				String dienThoai = resultSet.getString("dienThoai");
				String email = resultSet.getString("email");
				String tenNganHang = resultSet.getString("tenNganHang");
				String soTaiKhoan = resultSet.getString("soTaiKhoan");
				String maSoThue = resultSet.getString("maSoThue");

				return new ThongTinTram(maNhaGa, tenNhaGa, diaChi, dienThoai, email, tenNganHang, soTaiKhoan, maSoThue);
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
}
