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

public class ChiTiet_HoaDon_DAO {

	private static ChiTiet_HoaDon_DAO instance;

	public static ChiTiet_HoaDon_DAO getInstance() {
		if (instance == null)
			instance = new ChiTiet_HoaDon_DAO();
		return instance;
	}

	public List<ChiTiet_HoaDon> getAll() throws SQLException {
		String sql = "SELECT * FROM ChiTiet_HoaDon";

		Connection con = ConnectDB.getInstance().getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		List<ChiTiet_HoaDon> list = new ArrayList<>();
		while (resultSet.next()) {
			int soLuong = resultSet.getInt(1);
			HoaDon hoaDon = new HoaDon(resultSet.getString(2));
			KhuyenMai khuyenMai = new KhuyenMai(resultSet.getString(3));
			VeTau veTau = new VeTau(resultSet.getString(4));

			list.add(new ChiTiet_HoaDon(soLuong, hoaDon, khuyenMai, veTau));
		}

		return list;
	}

	public boolean them(ChiTiet_HoaDon entity) throws SQLException {
		String sql = "INSERT INTO ChiTiet_HoaDon(soLuong, maHoaDon, maKhuyenMai, maVeTau) VALUES(?, ?, ?, ?)";

		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(0, entity.getSoLuong());
		statement.setString(1, entity.getHoaDon().getMaHoaDon());
		statement.setString(2, entity.getKhuyenMai().getMaKhuyenMai());
		statement.setString(3, entity.getVeTau().getMaVeTau());
		int count = statement.executeUpdate();

		return count > 0;
	}

	public ArrayList<ChiTiet_HoaDon> layChiTietHDTheoMaHD(String maHD) {
		ArrayList<ChiTiet_HoaDon> cthoaDons = new ArrayList<>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			ConnectDB.getInstance();
			con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM ChiTiet_HoaDon WHERE maHoaDon = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maHD);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int soLuong = resultSet.getInt("soLuong"); // Giả sử bạn đã định nghĩa cột này trong CSDL
				String maKhuyenMai = resultSet.getString("maKhuyenMai");
				String maVeTau = resultSet.getString("maVeTau");

				// Tạo các đối tượng cần thiết
				HoaDon hd = new HoaDon(maHD);
				KhuyenMai km = new KhuyenMai(maKhuyenMai);
				VeTau vt = new VeTau(maVeTau);

				// Tạo đối tượng ChiTiet_HoaDon và thêm vào danh sách
				ChiTiet_HoaDon cthd = new ChiTiet_HoaDon(soLuong, hd, km, vt);
				cthoaDons.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cthoaDons;
	}
}
