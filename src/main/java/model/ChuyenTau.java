package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChuyenTau {

	private String maChuyenTau;
	private String gaKhoiHanh;
	private String gaDen;
	private LocalDateTime thoiGianKhoiHanh;
	private LocalDateTime thoiGianDuKien;
	private String ghiChu;

	public String getMaChuyenTau() {
		return maChuyenTau;
	}

	private void setMaChuyenTau(String maChuyenTau) throws Exception {
		if (!maChuyenTau.matches("^CT\\d{4}$")) {
			throw new Exception("Mã chuyến tàu bắt đầu bằng CT và theo sau là bốn ký số ngẫu nhiên (CTXXXX)");
		}

		this.maChuyenTau = maChuyenTau;
	}

	public String getGaKhoiHanh() {
		return gaKhoiHanh;
	}

	public void setGaKhoiHanh(String gaKhoiHanh) throws Exception {
		if (gaKhoiHanh.isBlank()) {
			throw new Exception("Ga khởi hành không được để trống");
		}

		this.gaKhoiHanh = gaKhoiHanh;
	}

	public String getGaDen() {
		return gaDen;
	}

	public void setGaDen(String gaDen) throws Exception {
		if (gaDen.isBlank()) {
			throw new Exception("Ga đến không được để trống");
		}

		this.gaDen = gaDen;
	}

	public LocalDateTime getThoiGianKhoiHanh() {
		return thoiGianKhoiHanh;
	}

	public void setThoiGianKhoiHanh(LocalDateTime thoiGianKhoiHanh) throws Exception {
		if (thoiGianKhoiHanh.isAfter(thoiGianDuKien)) {
			throw new Exception("Thời gian khởi hành phải trước thời gian dự kiến");
		}

		this.thoiGianKhoiHanh = thoiGianKhoiHanh;
	}

	public LocalDateTime getThoiGianDuKien() {
		return thoiGianDuKien;
	}

	public void setThoiGianDuKien(LocalDateTime thoiGianDuKien) throws Exception {
		if (thoiGianDuKien.isAfter(thoiGianKhoiHanh)) {
			throw new Exception("Thời gian dự kiến phải sau thời gian khởi hành");
		}

		this.thoiGianDuKien = thoiGianDuKien;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public ChuyenTau() {

	}

	public ChuyenTau(String maChuyenTau) throws Exception {
		setMaChuyenTau(maChuyenTau);
	}

	public ChuyenTau(String maChuyenTau, String gaKhoiHanh, String gaDen, LocalDateTime thoiGianKhoiHanh,
			LocalDateTime thoiGianDuKien, String ghiChu) throws Exception {
		setMaChuyenTau(maChuyenTau);
		setGaKhoiHanh(gaKhoiHanh);
		setGaDen(gaDen);
		this.thoiGianKhoiHanh = thoiGianKhoiHanh;
		setThoiGianDuKien(thoiGianDuKien);
		setGhiChu(ghiChu);
	}

	@Override
	public int hashCode() {
		return Objects.hash(maChuyenTau);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ChuyenTau))
			return false;
		ChuyenTau other = (ChuyenTau) obj;
		return Objects.equals(maChuyenTau, other.maChuyenTau);
	}

	@Override
	public String toString() {
		return "ChuyenTau {maChuyenTau: " + maChuyenTau + ", gaKhoiHanh: " + gaKhoiHanh + ", gaDen: " + gaDen
				+ ", thoiGianKhoiHanh: " + thoiGianKhoiHanh + ", thoiGianDuKien: " + thoiGianDuKien + ", ghiChu: "
				+ ghiChu + "}";
	}

}
