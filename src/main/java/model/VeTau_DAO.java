package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;

public class VeTau_DAO {

	public ArrayList<VeTau> getalltbVT() {
		ArrayList<VeTau> veTaus = new ArrayList<>();
		String sql = "Select * FROM VeTau";
		Connection con;

		try {
			con = ConnectDB.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String maVeTau = resultSet.getString(1);
				String maVach = resultSet.getString(2);
				boolean loaiVe = resultSet.getBoolean(3);
				TrangThaiVeTau trangThai = TrangThaiVeTau.values()[resultSet.getInt(4)];
				ChuyenTau chuyenTau = new ChuyenTau(resultSet.getString(5));

				VeTau vetau = new VeTau(maVeTau, maVach, loaiVe, trangThai, chuyenTau);
				veTaus.add(vetau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return veTaus;
	}
}
