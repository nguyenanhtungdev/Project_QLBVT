package model;

import java.time.LocalDateTime;

public class GiaVe {
	private String maGiaVe;
	private double giaVe;
	private float tiLeTangGia;
	private LocalDateTime ngayCapNhap;
	private String ghiChu;

	public GiaVe() {
		// TODO Auto-generated constructor stub
	}

	public GiaVe(String maGiaVe) {
		super();
		this.maGiaVe = maGiaVe;
	}

	public GiaVe(String maGiaVe, double giaVe, float tiLeTangGia, LocalDateTime ngayCapNhap, String ghiChu) {
		super();
		this.maGiaVe = maGiaVe;
		this.giaVe = giaVe;
		this.tiLeTangGia = tiLeTangGia;
		this.ngayCapNhap = ngayCapNhap;
		this.ghiChu = ghiChu;
	}

	public String getMaGiaVe() {
		return maGiaVe;
	}

	public void setMaGiaVe(String maGiaVe) {
		if (!maGiaVe.matches("^GV\\d{4}$")) {
			throw new IllegalArgumentException("Mã GiaVe phải có định dạng GVXXXX, trong đó XXXX là 4 chữ số.");
		}
		this.maGiaVe = maGiaVe;
	}

	public double getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(double giaVe) {
		if (giaVe <= 0) {
			throw new IllegalArgumentException("GiaVe phải lớn hơn 0.");
		}
		this.giaVe = giaVe;
	}

	public float getTiLeTangGia() {
		return tiLeTangGia;
	}

	public void setTiLeTangGia(float tiLeTangGia) {
		if (tiLeTangGia < 0 || tiLeTangGia > 100) {
			throw new IllegalArgumentException("TiLeTangGia phải nằm trong khoảng từ 0 đến 100.");
		}
		this.tiLeTangGia = tiLeTangGia;
	}

	public LocalDateTime getNgayCapNhap() {
		return ngayCapNhap;
	}

	public void setNgayCapNhat(LocalDateTime ngayCapNhap) {
		if (ngayCapNhap.isAfter(LocalDateTime.now())) {
			throw new IllegalArgumentException("NgayCapNhat phải trước ngày hiện tại.");
		}
		this.ngayCapNhap = ngayCapNhap;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "GiaVe [maGiaVe=" + maGiaVe + ", giaVe=" + giaVe + ", tiLeTangGia=" + tiLeTangGia + ", ngayCapNhap="
				+ ngayCapNhap + ", ghiChu=" + ghiChu + "]";
	}

	public double tinhGiaVeHienTai() {
		return giaVe * (1 + (tiLeTangGia / 100));
	}
}
