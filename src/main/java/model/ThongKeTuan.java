package model;

import java.time.LocalDate;

public class ThongKeTuan {

	private LocalDate ngayTaoThongKe;
	private double[] doanhThu;
	private int[] soLuongHoaDon, soLuongVeBanRa, soLuongVeDaHuy, soLuongKhuyenMaiDaDung;

	public LocalDate getNgayTaoThongKe() {
		return ngayTaoThongKe;
	}

	public double[] getDoanhThu() {
		return doanhThu;
	}

	public int[] getSoLuongHoaDon() {
		return soLuongHoaDon;
	}

	public int[] getSoLuongVeBanRa() {
		return soLuongVeBanRa;
	}

	public int[] getSoLuongVeDaHuy() {
		return soLuongVeDaHuy;
	}

	public int[] getSoLuongKhuyenMaiDaDung() {
		return soLuongKhuyenMaiDaDung;
	}

	public ThongKeTuan(LocalDate ngayTaoThongKe, double[] doanhThu, int[] soLuongHoaDon, int[] soLuongVeBanRa,
			int[] soLuongVeDaHuy, int[] soLuongKhuyenMaiDaDung) {
		this.ngayTaoThongKe = ngayTaoThongKe;
		this.doanhThu = doanhThu;
		this.soLuongHoaDon = soLuongHoaDon;
		this.soLuongVeBanRa = soLuongVeBanRa;
		this.soLuongVeDaHuy = soLuongVeDaHuy;
		this.soLuongKhuyenMaiDaDung = soLuongKhuyenMaiDaDung;
	}

}
