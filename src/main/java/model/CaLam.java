package model;

import java.time.LocalTime;
import java.util.Objects;

public class CaLam {

	private String maCa;
	private String tenCa;
	private LocalTime thoiGianBatDau;
	private LocalTime thoiGianKetThuc;
	private String ghiChu;

	public CaLam() {
	}

	public CaLam(String maCa) {
		setMaCa(maCa);
	}

	public CaLam(String maCa, String tenCa, LocalTime thoiGianBatDau, LocalTime thoiGianKetThuc, String ghiChu) {
		setMaCa(maCa);
		setTenCa(tenCa);
		this.thoiGianBatDau = thoiGianBatDau;
		setThoiGianKetThuc(thoiGianKetThuc);
		setGhiChu(ghiChu);
	}

	public String getMaCa() {
		return maCa;
	}

	private void setMaCa(String maCa) {
		if (!maCa.matches("^CA\\d{2}$")) {
			throw new IllegalArgumentException("Mã ca làm phải bắt đầu bằng CA và theo sau là hai ký số");
		}

		this.maCa = maCa;
	}

	public String getTenCa() {
		return tenCa;
	}

	public void setTenCa(String tenCa) {
		if (!tenCa.matches("^(SA|TR|TO|KH)$")) {
			throw new IllegalArgumentException("Tên ca làm phải là 1 trong các giá trị “SA”, “TR”, “TO” hoặc “KH”");
		}

		this.tenCa = tenCa;
	}

	public LocalTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalTime thoiGianBatDau) {
		if (thoiGianBatDau.isAfter(thoiGianKetThuc)) {
			throw new IllegalArgumentException("Thời gian bắt đầu ca làm phải trước thời gian kết thúc ca làm");
		}

		this.thoiGianBatDau = thoiGianBatDau;
	}

	public LocalTime getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(LocalTime thoiGianKetThuc) {
		if (thoiGianKetThuc.isBefore(thoiGianBatDau)) {
			throw new IllegalArgumentException("Thời gian kết thúc ca làm phải trước thời gian bắt đầu ca làm");
		}

		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CaLam))
			return false;
		CaLam other = (CaLam) obj;
		return Objects.equals(maCa, other.maCa);
	}

	@Override
	public String toString() {
		return "CaLam {maCa: " + maCa + ", tenCa: " + tenCa + ", thoiGianBatDau: " + thoiGianBatDau
				+ ", thoiGianKetThuc: " + thoiGianKetThuc + ", ghiChu: " + ghiChu + "}";
	}

}
