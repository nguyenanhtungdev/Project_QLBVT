package model;

import java.time.LocalTime;
import java.util.Objects;

public class CaLam {

	private String maCa;
	private String tenCa;
	private LocalTime thoiGianBatDau;
	private LocalTime thoiGianKetThuc;
	private String ghiChu;

	public String getMaCa() {
		return maCa;
	}

	private void setMaCa(String maCa) throws Exception {
		if (!maCa.matches("^CA\\d{2}$")) {
			throw new Exception("Mã ca phải bắt đầu bằng CA và theo sau là hai ký số ngẫu nhiên (CAXX)");
		}

		this.maCa = maCa;
	}

	public String getTenCa() {
		return tenCa;
	}

	public void setTenCa(String tenCa) throws Exception {
		if (!tenCa.matches("^(?:SA|TR|TO|KH).+$")) {
			throw new Exception(
					"Tên ca phải bắt đầu bằng một trong các tiền tố “SA”, “TR”, “TO”, “KH”, theo sau đó là các ký tự chữ hoặc số bất kỳ");
		}

		this.tenCa = tenCa;
	}

	public LocalTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalTime thoiGianBatDau) throws Exception {
		if (thoiGianBatDau.isAfter(thoiGianKetThuc)) {
			throw new Exception("Thời gian bắt đầu phải trước thời gian kết thúc");
		}

		this.thoiGianBatDau = thoiGianBatDau;
	}

	public LocalTime getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(LocalTime thoiGianKetThuc) throws Exception {
		if (thoiGianKetThuc.isBefore(thoiGianBatDau)) {
			throw new Exception("Thời gian kết thúc phải trước thời gian bắt đầu");
		}

		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public CaLam() {

	}

	public CaLam(String maCa) throws Exception {
		setMaCa(maCa);
	}

	public CaLam(String maCa, String tenCa, LocalTime thoiGianBatDau, LocalTime thoiGianKetThuc, String ghiChu) throws Exception {
		setMaCa(maCa);
		setTenCa(tenCa);
		this.thoiGianBatDau = thoiGianBatDau;
		setThoiGianKetThuc(thoiGianKetThuc);
		setGhiChu(ghiChu);
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
