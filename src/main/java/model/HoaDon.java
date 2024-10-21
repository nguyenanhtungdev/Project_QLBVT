package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HoaDon {
	private String maHoaDon;
	private LocalDateTime ngayLapHoaDon;
	private String ghiChu;
	private float thueVAT;
	private String phuongThucThanhToan;
	private String loaiHoaDon;
	private KhachHang khachHang;
	private ThongTinTram thongTinTram;
	private NhanVien nhanVien;
	private ThongTinGiuCho thongTinGiuCho;
	
	public HoaDon(String maHoaDon, LocalDateTime ngayLapHoaDon, String ghiChu, float thueVAT,
			String phuongThucThanhToan, String loaiHoaDon, KhachHang khachHang, ThongTinTram thongTinTram,
			NhanVien nhanVien, ThongTinGiuCho thongTinGiuCho) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.ghiChu = ghiChu;
		this.thueVAT = thueVAT;
		this.phuongThucThanhToan = phuongThucThanhToan;
		this.loaiHoaDon = loaiHoaDon;
		this.khachHang = khachHang;
		this.thongTinTram = thongTinTram;
		this.nhanVien = nhanVien;
		this.thongTinGiuCho = thongTinGiuCho;
	}

	public HoaDon() {
	}

	public HoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		if (maHoaDon != null && maHoaDon.matches("^HD\\d{4}\\d{5}$")) {
            this.maHoaDon= maHoaDon;
        } else {
            throw new IllegalArgumentException("Mã hóa đơn phải có dạng 'HDyyyyxxxxx'");
        }
	}

	public LocalDateTime getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(LocalDateTime ngayLapHoaDon) {
		if (ngayLapHoaDon.isBefore(LocalDateTime.now())) {
            this.ngayLapHoaDon= ngayLapHoaDon;
        } else {
            throw new IllegalArgumentException("Ngày lập hóa đơn không hợp lệ. ");
        }
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public float getThueVAT() {
		return thueVAT;
	}

	public void setThueVAT(float thueVAT) {
		if (thueVAT > 0) {
            this.thueVAT= thueVAT;
        } else {
            throw new IllegalArgumentException("Thuế VAT phải > 0");
        }
	}

	public String getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}

	public void setPhuongThucThanhToan(String phuongThucThanhToan) {
		//Chưa ràng buộc 
		this.phuongThucThanhToan = phuongThucThanhToan;
	}

	public String getLoaiHoaDon() {
		return loaiHoaDon;
	}

	public void setLoaiHoaDon(String loaiHoaDon) {
		//Chưa ràng buộc
		this.loaiHoaDon = loaiHoaDon;
	}
	
	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public ThongTinTram getThongTinTram() {
		return thongTinTram;
	}

	public void setThongTinTram(ThongTinTram thongTinTram) {
		this.thongTinTram = thongTinTram;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public ThongTinGiuCho getThongTinGiuCho() {
		return thongTinGiuCho;
	}

	public void setThongTinGiuCho(ThongTinGiuCho thongTinGiuCho) {
		this.thongTinGiuCho = thongTinGiuCho;
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLapHoaDon=" + ngayLapHoaDon + ", ghiChu=" + ghiChu + ", thueVAT="
				+ thueVAT + ", phuongThucThanhToan=" + phuongThucThanhToan + ", loaiHoaDon=" + loaiHoaDon + "]";
	}
}

