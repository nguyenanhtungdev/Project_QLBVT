package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;

public class NhanVien_CaLam_DAO {

	public static NhanVien_CaLam_DAO instance;

	public static NhanVien_CaLam_DAO getInstance() {
		return instance == null ? instance = new NhanVien_CaLam_DAO() : instance;
	}

	public List<NhanVien_CaLam> getAll() throws SQLException {
		String sql = "Select * FROM NhanVien_CaLam";

		Connection con = Database.getInstance().getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<NhanVien_CaLam> list = new ArrayList<>();
		while (resultSet.next()) {
			LocalDateTime thoiGianNhanCa = resultSet.getTimestamp("thoiGianNhanCa").toLocalDateTime();
			LocalDateTime thoiGianKetThucCa = resultSet.getTimestamp("thoiGianKetThucCa").toLocalDateTime();
			String maNhanVien = resultSet.getString("maNV");
			String maCaLam = resultSet.getString("maCa");

			NhanVien nhanVien = new NhanVien(maNhanVien);
			CaLam caLam = new CaLam(maCaLam);

			list.add(new NhanVien_CaLam(thoiGianNhanCa, thoiGianKetThucCa, nhanVien, caLam));
		}

		return list;
	}
}
