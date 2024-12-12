package model;

import java.time.LocalDateTime;

public class PhieuHoanTien {

	private String maPhieuHoanTien;
	private LocalDateTime ngayHoanTien;
	private String lyDoHoanTien;
	private String ghiChu;
	private float tiLeHoanTien;

	private KhachHang khachHang;

	public PhieuHoanTien() {
	}

	public PhieuHoanTien(String maPhieuHoanTien) {
		this.maPhieuHoanTien = maPhieuHoanTien;
	}

	public PhieuHoanTien(String maPhieuHoanTien, LocalDateTime ngayHoanTien, float tiLeHoanTien, String lyDoHoanTien,
			String ghiChu, KhachHang khachHang) {
		this.maPhieuHoanTien = maPhieuHoanTien;
		setNgayHoanTien(ngayHoanTien);
		setTiLeHoanTien(tiLeHoanTien);
		setLyDoHoanTien(lyDoHoanTien);
		this.ghiChu = ghiChu;
		this.khachHang = khachHang;
	}

	public String getMaPhieuHoanTien() {
		return maPhieuHoanTien;
	}

	public void setMaPhieuHoanTien(String maPhieuHoanTien) {
		this.maPhieuHoanTien = maPhieuHoanTien;
	}

	public LocalDateTime getNgayHoanTien() {
		return ngayHoanTien;
	}

	public void setNgayHoanTien(LocalDateTime ngayHoanTien) {
		// Ràng buộc: phải là ngày hiện tại hoặc ngày trong quá khứ, không được ở tương
		// lai
		if (ngayHoanTien.isBefore(LocalDateTime.now()) || ngayHoanTien.isEqual(LocalDateTime.now())) {
			this.ngayHoanTien = ngayHoanTien;
		} else {
			throw new IllegalArgumentException("Ngày hoàn tiền không hợp lệ!");
		}
	}

	public String getLyDoHoanTra() {
		return lyDoHoanTien;
	}

	public String getLyDoHoanTien() {
		return lyDoHoanTien;
	}

	public void setLyDoHoanTien(String lyDoHoanTien) {
		if (lyDoHoanTien != null && !lyDoHoanTien.trim().isEmpty() && lyDoHoanTien.length() <= 255) {
			this.lyDoHoanTien = lyDoHoanTien;
		} else {
			throw new IllegalArgumentException("Lý do hoàn trả không hợp lệ!");
		}
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		if (ghiChu == null || ghiChu.length() <= 255) {
			this.ghiChu = ghiChu;
		} else {
			throw new IllegalArgumentException("Ghi chú quá dài!");
		}
	}

	public float getTiLeHoanTien() {
		return tiLeHoanTien;
	}

	public void setTiLeHoanTien(float tiLeHoanTien) {
		// Ràng buộc: tỉ lệ hoàn tiền phải >= 0 và <= 100
		if (tiLeHoanTien >= 0 && tiLeHoanTien <= 100) {
			this.tiLeHoanTien = tiLeHoanTien;
		} else {
			throw new IllegalArgumentException("Tỉ lệ hoàn tiền không hợp lệ!");
		}
	}

	public KhachHang getKhachHang() {
		return khachHang.getSoDienThoai() == null
				? khachHang = KhachHang_DAO.getInstance().getByMaKhachHang(khachHang.getMaKhachHang())
				: khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	// Phương thức tính số tiền hoàn trả
	public double tinhSoTienHoanTra(double tongTienThanhToan) {
		return tongTienThanhToan * (tiLeHoanTien / 100);
	}

	@Override
	public String toString() {
		return "PhieuHoanTien [maPhieuHoanTien=" + maPhieuHoanTien + ", ngayHoanTien=" + ngayHoanTien
				+ ", lyDoHoanTien=" + lyDoHoanTien + ", ghiChu=" + ghiChu + ", tiLeHoanTien=" + tiLeHoanTien
				+ ", khachHang=" + khachHang + "]";
	}

}
