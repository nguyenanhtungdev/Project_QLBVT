package model;

import java.time.LocalDateTime;

public class StatisticData {

	private LocalDateTime ngayThongKe;
	private double doanhThu;
	private int soLuongHoaDon;
	private int soLuongVeBanRa;
	private int soLuongVeDaHuy;
	private int soLuongKhuyenMaiDaDung;

	public StatisticData(LocalDateTime ngayThongKe, double doanhThu, int soLuongHoaDon, int soLuongVeBanRa,
			int soLuongVeDaHuy, int soLuongKhuyenMaiDaDung) {
		this.ngayThongKe = ngayThongKe;
		this.doanhThu = doanhThu;
		this.soLuongHoaDon = soLuongHoaDon;
		this.soLuongVeBanRa = soLuongVeBanRa;
		this.soLuongVeDaHuy = soLuongVeDaHuy;
		this.soLuongKhuyenMaiDaDung = soLuongKhuyenMaiDaDung;
	}

	public LocalDateTime getNgayThongKe() {
		return ngayThongKe;
	}

	public double getDoanhThu() {
		return doanhThu;
	}

	public int getSoLuongHoaDon() {
		return soLuongHoaDon;
	}

	public int getSoLuongVeBanRa() {
		return soLuongVeBanRa;
	}

	public int getSoLuongVeDaHuy() {
		return soLuongVeDaHuy;
	}

	public int getSoLuongKhuyenMaiDaDung() {
		return soLuongKhuyenMaiDaDung;
	}
}
