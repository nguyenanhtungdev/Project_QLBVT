package model;

public class StatisticData {

	private Object target;
	private double doanhThu;
	private int soLuongHoaDon;
	private int soLuongVeBan;
	private int soLuongVeHuy;
	private int soLuongKhuyenMai;

	public StatisticData(Object target, double doanhThu, int soLuongHoaDon, int soLuongVeBan, int soLuongVeHuy,
			int soLuongKhuyenMai) {
		this.target = target;
		this.doanhThu = doanhThu;
		this.soLuongHoaDon = soLuongHoaDon;
		this.soLuongVeBan = soLuongVeBan;
		this.soLuongVeHuy = soLuongVeHuy;
		this.soLuongKhuyenMai = soLuongKhuyenMai;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public double getDoanhThu() {
		return doanhThu;
	}

	public void setDoanhThu(double doanhThu) {
		this.doanhThu = doanhThu;
	}

	public int getSoLuongHoaDon() {
		return soLuongHoaDon;
	}

	public void setSoLuongHoaDon(int soLuongHoaDon) {
		this.soLuongHoaDon = soLuongHoaDon;
	}

	public int getSoLuongVeBan() {
		return soLuongVeBan;
	}

	public void setSoLuongVeBan(int soLuongVeBan) {
		this.soLuongVeBan = soLuongVeBan;
	}

	public int getSoLuongVeHuy() {
		return soLuongVeHuy;
	}

	public void setSoLuongVeHuy(int soLuongVeHuy) {
		this.soLuongVeHuy = soLuongVeHuy;
	}

	public int getSoLuongKhuyenMai() {
		return soLuongKhuyenMai;
	}

	public void setSoLuongKhuyenMai(int soLuongKhuyenMai) {
		this.soLuongKhuyenMai = soLuongKhuyenMai;
	}

}
