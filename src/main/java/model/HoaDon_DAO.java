package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import model.KhachHang.LoaiKhachHang;

public class HoaDon_DAO {

	private static HoaDon_DAO instance;

	public static HoaDon_DAO getInstance() {
		if (instance == null)
			instance = new HoaDon_DAO();
		return instance;
	}

	// Lấy hóa đơn theo số điện thoại hoặc CCCD
	public ArrayList<HoaDon> getHoaDonBySoDienThoaiOrCCCD(String soDienThoaiOrCCCD) {
		ArrayList<HoaDon> hoaDons = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT hd.*, kh.hoTen, kh.soDienThoai " + "FROM HoaDon hd "
					+ "JOIN KhachHang kh ON hd.maKH = kh.maKH " + "WHERE kh.soDienThoai = ? OR kh.CCCD = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, soDienThoaiOrCCCD);
			ps.setString(2, soDienThoaiOrCCCD);
			rs = ps.executeQuery();

			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				String hoTen = rs.getString("hoTen");
				float thueVAT = rs.getFloat("thueVAT");

				// Định dạng ngày lập hóa đơn
				Timestamp timestamp = rs.getTimestamp("ngayLapHoaDon");
				LocalDateTime ngayLapHoaDon = timestamp.toLocalDateTime();

				// Tạo đối tượng HoaDon
				HoaDon hoaDon = new HoaDon();
				hoaDon.setMaHoaDon(maHoaDon);
				hoaDon.setNgayLapHoaDon(ngayLapHoaDon);
				hoaDon.setThueVAT(thueVAT);

				// Thêm thông tin khách hàng
				KhachHang khachHang = new KhachHang();
				khachHang.setHoTen(hoTen);
				hoaDon.setKhachHang(khachHang);

				hoaDons.add(hoaDon);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hoaDons;
	}

	public ArrayList<HoaDon> getalltbHD() {
		ArrayList<HoaDon> hoaDons = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			ConnectDB.getInstance();
			con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM HoaDon";
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				String maHoaDon = resultSet.getString(1);
				Timestamp timestamp = resultSet.getTimestamp(2);
				LocalDateTime ngayLapHoaDon = timestamp.toLocalDateTime();
				String ghiChu = resultSet.getString(3);
				float thueVAT = resultSet.getFloat(4);
				String phuongThucThanhToan = resultSet.getString(5);
				String loaiHoaDon = resultSet.getString(6);
				String maKH = resultSet.getString(7);
				String maNhaGa = resultSet.getString(8);
				String maNV = resultSet.getString(9);
				String maThongTinGiuCho = resultSet.getString(10);

				KhachHang khachHang = new KhachHang(maKH);
				ThongTinTram thongTinTram = new ThongTinTram(maNhaGa);
				NhanVien nhanVien = new NhanVien(maNV);
				ThongTinGiuCho thongTinGiuCho = new ThongTinGiuCho(maThongTinGiuCho);

				HoaDon hoaDon = new HoaDon(maHoaDon, ngayLapHoaDon, ghiChu, thueVAT, phuongThucThanhToan, loaiHoaDon,
						khachHang, thongTinTram, nhanVien, thongTinGiuCho);
				hoaDons.add(hoaDon);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hoaDons;
	}

	public ArrayList<HoaDon> getalltbHDKH() {
		ArrayList<HoaDon> hoaDons = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			ConnectDB.getInstance();
			con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT hd.*, kh.hoTen, kh.soDienThoai " + "FROM HoaDon hd "
					+ "JOIN KhachHang kh ON hd.maKH = kh.maKH";
			statement = con.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				String maHoaDon = resultSet.getString("maHoaDon");
				Timestamp timestamp = resultSet.getTimestamp("ngayLapHoaDon");
				LocalDateTime ngayLapHoaDon = timestamp.toLocalDateTime();
				String ghiChu = resultSet.getString("ghiChu");
				float thueVAT = resultSet.getFloat("thueVAT");
				String phuongThucThanhToan = resultSet.getString("phuongThucThanhToan");
				String loaiHoaDon = resultSet.getString("loaiHoaDon");
				String maKH = resultSet.getString("maKH");
				String maNhaGa = resultSet.getString("maNhaGa");
				String maNV = resultSet.getString("maNV");
				String maThongTinGiuCho = resultSet.getString("maThongTinGiuCho");
				String tenKhachHang = resultSet.getString("hoTen");
				String soDienThoai = resultSet.getString("soDienThoai");
				KhachHang khachHang = new KhachHang(maKH, tenKhachHang, soDienThoai);

				ThongTinTram thongTinTram = new ThongTinTram(maNhaGa);
				NhanVien nhanVien = new NhanVien(maNV);
				ThongTinGiuCho thongTinGiuCho = new ThongTinGiuCho(maThongTinGiuCho);

				HoaDon hoaDon = new HoaDon(maHoaDon, ngayLapHoaDon, ghiChu, thueVAT, phuongThucThanhToan, loaiHoaDon,
						khachHang, thongTinTram, nhanVien, thongTinGiuCho);
				hoaDons.add(hoaDon);
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
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hoaDons;
	}

	public HoaDon layTTHoaDonTheoMa(String maHoaDon) {
		HoaDon hoaDon = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			ConnectDB.getInstance();
			con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT hd.*, kh.hoTen, kh.soDienThoai " + "FROM HoaDon hd "
					+ "JOIN KhachHang kh ON hd.maKH = kh.maKH " + "WHERE hd.maHoaDon = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maHoaDon);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String maKH = resultSet.getString("maKH");
				String tenKhachHang = resultSet.getString("hoTen");
				String soDienThoai = resultSet.getString("soDienThoai");

				Timestamp timestamp = resultSet.getTimestamp("ngayLapHoaDon");
				LocalDateTime ngayLapHoaDon = timestamp.toLocalDateTime();
				String ghiChu = resultSet.getString("ghiChu");
				float thueVAT = resultSet.getFloat("thueVAT");
				String phuongThucThanhToan = resultSet.getString("phuongThucThanhToan");
				String loaiHoaDon = resultSet.getString("loaiHoaDon");
				String maNhaGa = resultSet.getString("maNhaGa");
				String maNV = resultSet.getString("maNV");
				String maThongTinGiuCho = resultSet.getString("maThongTinGiuCho");

				KhachHang khachHang = new KhachHang(maKH, tenKhachHang, soDienThoai);
				ThongTinTram thongTinTram = new ThongTinTram(maNhaGa);
				NhanVien nhanVien = new NhanVien(maNV);
				ThongTinGiuCho thongTinGiuCho = new ThongTinGiuCho(maThongTinGiuCho);

				hoaDon = new HoaDon(maHoaDon, ngayLapHoaDon, ghiChu, thueVAT, phuongThucThanhToan, loaiHoaDon,
						khachHang, thongTinTram, nhanVien, thongTinGiuCho);
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
		return hoaDon;
	}

	public List<HoaDon> layTTHoaDonTheoSDT(String soDienThoai) {
		List<HoaDon> hoaDons = new ArrayList<>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			ConnectDB.getInstance();
			con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT hd.*, kh.hoTen, kh.soDienThoai " + "FROM HoaDon hd "
					+ "JOIN KhachHang kh ON hd.maKH = kh.maKH " + "WHERE kh.soDienThoai = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, soDienThoai);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String maHoaDon = resultSet.getString("maHoaDon");
				Timestamp timestamp = resultSet.getTimestamp("ngayLapHoaDon");
				LocalDateTime ngayLapHoaDon = timestamp.toLocalDateTime();
				String ghiChu = resultSet.getString("ghiChu");
				float thueVAT = resultSet.getFloat("thueVAT");
				String phuongThucThanhToan = resultSet.getString("phuongThucThanhToan");
				String loaiHoaDon = resultSet.getString("loaiHoaDon");
				String maKH = resultSet.getString("maKH");
				String maNhaGa = resultSet.getString("maNhaGa");
				String maNV = resultSet.getString("maNV");
				String maThongTinGiuCho = resultSet.getString("maThongTinGiuCho");
				String tenKhachHang = resultSet.getString("hoTen");
				KhachHang khachHang = new KhachHang(maKH, tenKhachHang, soDienThoai);

				ThongTinTram thongTinTram = new ThongTinTram(maNhaGa);
				NhanVien nhanVien = new NhanVien(maNV);
				ThongTinGiuCho thongTinGiuCho = new ThongTinGiuCho(maThongTinGiuCho);

				HoaDon hoaDon = new HoaDon(maHoaDon, ngayLapHoaDon, ghiChu, thueVAT, phuongThucThanhToan, loaiHoaDon,
						khachHang, thongTinTram, nhanVien, thongTinGiuCho);
				hoaDons.add(hoaDon);
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
		return hoaDons;
	}

	public List<HoaDon> layTTHoaDonTheoDate(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
		List<HoaDon> hoaDons = new ArrayList<>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			ConnectDB.getInstance();
			con = ConnectDB.getInstance().getConnection();

			// Chuyển đổi LocalDateTime sang Timestamp
			Timestamp sqlStartDate = Timestamp.valueOf(startDate);
			Timestamp sqlEndDate = Timestamp.valueOf(endDate);

			// Truy vấn với thông tin khách hàng
			String sql = "SELECT hd.*, kh.hoTen, kh.soDienThoai " + "FROM HoaDon hd "
					+ "JOIN KhachHang kh ON hd.maKH = kh.maKH " + "WHERE hd.ngayLapHoaDon BETWEEN ? AND ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setTimestamp(1, sqlStartDate);
			preparedStatement.setTimestamp(2, sqlEndDate);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				// Phân tích kết quả
				String maHoaDon = resultSet.getString("maHoaDon");
				Timestamp timestamp = resultSet.getTimestamp("ngayLapHoaDon");
				LocalDateTime ngayLapHoaDon = timestamp.toLocalDateTime();
				String ghiChu = resultSet.getString("ghiChu");
				float thueVAT = resultSet.getFloat("thueVAT");
				String phuongThucThanhToan = resultSet.getString("phuongThucThanhToan");
				String loaiHoaDon = resultSet.getString("loaiHoaDon");
				String maKH = resultSet.getString("maKH");
				String maNhaGa = resultSet.getString("maNhaGa");
				String maNV = resultSet.getString("maNV");
				String maThongTinGiuCho = resultSet.getString("maThongTinGiuCho");

				// Lấy thông tin khách hàng
				String tenKhachHang = resultSet.getString("hoTen");
				String soDienThoai = resultSet.getString("soDienThoai");
				KhachHang khachHang = new KhachHang(maKH, tenKhachHang, soDienThoai);

				// Tạo đối tượng HoaDon
				HoaDon hoaDon = new HoaDon(maHoaDon, ngayLapHoaDon, ghiChu, thueVAT, phuongThucThanhToan, loaiHoaDon,
						khachHang, new ThongTinTram(maNhaGa), new NhanVien(maNV), new ThongTinGiuCho(maThongTinGiuCho));
				hoaDons.add(hoaDon);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			// Đóng kết nối và giải phóng tài nguyên
			if (resultSet != null)
				resultSet.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (con != null)
				con.close();
		}
		return hoaDons;
	}

	public ThongTinTram layThongTinTramTheoMa(String maNhaGa) {
		ThongTinTram thongTinTram = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			ConnectDB.getInstance();
			con = ConnectDB.getInstance().getConnection();

			// Truy vấn SQL để lấy thông tin trạm
			String sql = "SELECT * FROM ThongTinTram WHERE maNhaGa = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maNhaGa);
			resultSet = preparedStatement.executeQuery();

			// Nếu tìm thấy kết quả, tạo đối tượng ThongTinTram
			if (resultSet.next()) {
				String tenNhaGa = resultSet.getString("tenNhaGa");
				String diaChi = resultSet.getString("diaChi");
				String dienThoai = resultSet.getString("dienThoai");
				String email = resultSet.getString("email");
				String tenNganHang = resultSet.getString("tenNganHang");
				String soTaiKhoan = resultSet.getString("soTaiKhoan");
				String maSoThue = resultSet.getString("maSoThue");

				// Tạo đối tượng ThongTinTram
				thongTinTram = new ThongTinTram(maNhaGa, tenNhaGa, diaChi, dienThoai, email, tenNganHang, soTaiKhoan,
						maSoThue);
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
		return thongTinTram;
	}

	public KhachHang layKhachHangTheoMa(String maKH) {
		KhachHang khachHang = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			ConnectDB.getInstance();
			con = ConnectDB.getInstance().getConnection();

			String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maKH);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String hoTen = resultSet.getString("hoTen");
				String soDienThoai = resultSet.getString("soDienThoai");
				String email = resultSet.getString("email");
				boolean gioiTinh = resultSet.getBoolean("gioiTinh");
				String CCCD = resultSet.getString("CCCD");
				LocalDate ngaySinh = resultSet.getDate("ngaySinh").toLocalDate();
				LoaiKhachHang loaiKH = LoaiKhachHang.valueOf(resultSet.getString("loaiKH").toUpperCase());
				khachHang = new KhachHang(maKH, hoTen, soDienThoai, email, gioiTinh, CCCD, ngaySinh, loaiKH);
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
		return khachHang;
	}

}
