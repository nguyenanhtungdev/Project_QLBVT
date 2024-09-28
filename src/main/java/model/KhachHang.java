package model;

import java.time.LocalDate;

public class KhachHang {
	private String maKhachHang;
	private String hoTen;
	private String soDienThoai;
	private String email;
	private boolean gioiTinh;
	private String CCCD;
	private LocalDate ngaySinh;
	private String loaiKH;
	
	public KhachHang(String maKhachHang, String hoTen, String soDienThoai, String email, boolean gioiTinh, String CCCD,
			LocalDate ngaySinh, String loaiKH) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.gioiTinh = gioiTinh;
		this.CCCD = CCCD;
		this.ngaySinh = ngaySinh;
		this.loaiKH = loaiKH;
	}

	public KhachHang() {
	}

	public KhachHang(String maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getCCCD() {
		return CCCD;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getLoaiKH() {
		return loaiKH;
	}

	public void setLoaiKH(String loaiKH) {
		this.loaiKH = loaiKH;
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai
				+ ", email=" + email + ", gioiTinh=" + gioiTinh + ", CCCD=" + CCCD + ", ngaySinh=" + ngaySinh
				+ ", loaiKH=" + loaiKH + "]";
	}
}
