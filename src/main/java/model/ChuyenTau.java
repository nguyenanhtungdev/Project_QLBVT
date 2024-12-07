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

	private GiaVe giaVe;

	public ChuyenTau() {
	}

	public ChuyenTau(String maChuyenTau) {
		setMaChuyenTau(maChuyenTau);
	}

	public ChuyenTau(String maChuyenTau, String gaKhoiHanh, String gaDen, LocalDateTime thoiGianKhoiHanh,
			LocalDateTime thoiGianDuKien, String ghiChu, GiaVe giaVe) {
		setMaChuyenTau(maChuyenTau);
		setGaKhoiHanh(gaKhoiHanh);
		setGaDen(gaDen);
		this.thoiGianKhoiHanh = thoiGianKhoiHanh;
		setThoiGianDuKien(thoiGianDuKien);
		setGhiChu(ghiChu);
		this.giaVe = giaVe;
	}

	public String getMaChuyenTau() {
		return maChuyenTau;
	}

	private void setMaChuyenTau(String maChuyenTau) {
		if (!maChuyenTau.matches("^CT\\d{4}$")) {
			throw new IllegalArgumentException("Mã chuyến tàu phải bắt đầu bằng CT và theo sau là bốn ký số");
		}

		this.maChuyenTau = maChuyenTau;
	}

	public String getGaKhoiHanh() {
		return gaKhoiHanh;
	}

	public void setGaKhoiHanh(String gaKhoiHanh) {
		if (gaKhoiHanh.isBlank()) {
			throw new IllegalArgumentException("Ga khởi hành không được để trống");
		}

		this.gaKhoiHanh = gaKhoiHanh;
	}

	public String getGaDen() {
		return gaDen;
	}

	public void setGaDen(String gaDen) {
		if (gaDen.isBlank()) {
			throw new IllegalArgumentException("Ga đến không được để trống");
		}

		this.gaDen = gaDen;
	}

	public LocalDateTime getThoiGianKhoiHanh() {
		return thoiGianKhoiHanh;
	}

	public void setThoiGianKhoiHanh(LocalDateTime thoiGianKhoiHanh) {
		if (thoiGianKhoiHanh.isAfter(thoiGianDuKien)) {
			throw new IllegalArgumentException("Thời gian khởi hành phải trước thời gian đến dự kiến");
		}

		this.thoiGianKhoiHanh = thoiGianKhoiHanh;
	}

	public LocalDateTime getThoiGianDuKien() {
		return thoiGianDuKien;
	}

	public void setThoiGianDuKien(LocalDateTime thoiGianDuKien) {
		if (thoiGianDuKien.isBefore(thoiGianKhoiHanh)) {
			throw new IllegalArgumentException("Thời gian đến dự kiến phải sau thời gian khởi hành");
		}

		this.thoiGianDuKien = thoiGianDuKien;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public GiaVe getGiaVe() {
		return giaVe.getGiaVe() == 0 ? giaVe = GiaVe_DAO.getInstance().getByMaGiaVe(giaVe.getMaGiaVe()) : giaVe;
	}

	public void setGiaVe(GiaVe giaVe) {
		this.giaVe = giaVe;
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
				+ ghiChu + ", giaVe: " + giaVe + "}";
	}

}
