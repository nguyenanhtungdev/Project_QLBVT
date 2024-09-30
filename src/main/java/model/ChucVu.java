package model;

import java.time.LocalDate;

public class ChucVu {
	private String maChucVu;
	private String tenChucVu;
	private LocalDate ngayNhanChuc;
	public ChucVu() {
		// TODO Auto-generated constructor stub
	}
	
	public ChucVu(String maChucVu) {
		super();
		this.maChucVu = maChucVu;
	}

	public ChucVu(String maChucVu, String tenChucVu, LocalDate ngayNhanChuc) {
		super();
		this.maChucVu = maChucVu;
		this.tenChucVu = tenChucVu;
		this.ngayNhanChuc = ngayNhanChuc;
	}
	public String getMaChucVu() {
		return maChucVu;
	}
	public void setMaChucVu(String maChucVu) {
		this.maChucVu = maChucVu;
	}
	public String getTenChucVu() {
		return tenChucVu;
	}
	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}
	public LocalDate getNgayNhanChuc() {
		return ngayNhanChuc;
	}
	public void setNgayNhanChuc(LocalDate ngayNhanChuc) {
		this.ngayNhanChuc = ngayNhanChuc;
	}

	@Override
	public String toString() {
		return "ChucVu [maChucVu=" + maChucVu + ", tenChucVu=" + tenChucVu + ", ngayNhanChuc=" + ngayNhanChuc + "]";
	}
	
}
