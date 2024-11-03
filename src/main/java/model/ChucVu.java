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
		if (maChucVu.matches("CV\\d{4}")) {
			this.maChucVu = maChucVu;
		} else {
			throw new IllegalArgumentException("Mã chức vụ phải theo định dạng CVXXXX, trong đó XXXX là dãy 4 chữ số.");
		}
	}

	public String getTenChucVu() {
		return tenChucVu;
	}

	public void setTenChucVu(String tenChucVu) {
		if (tenChucVu.equals("NVBV") || tenChucVu.equals("NVQL")) {
			this.tenChucVu = tenChucVu;
		} else {
			throw new IllegalArgumentException("Tên chức vụ chỉ được phép là NVBV hoặc NVQL.");
		}
	}

	public LocalDate getNgayNhanChuc() {
		return ngayNhanChuc;
	}

	public void setNgayNhanChuc(LocalDate ngayNhanChuc) {
		if (ngayNhanChuc.isBefore(LocalDate.now())) {
			this.ngayNhanChuc = ngayNhanChuc;
		} else {
			throw new IllegalArgumentException("Ngày nhận chức phải trước ngày hiện tại.");
		}
	}

	@Override
	public String toString() {
		return "ChucVu [maChucVu=" + maChucVu + ", tenChucVu=" + tenChucVu + ", ngayNhanChuc=" + ngayNhanChuc + "]";
	}

}
