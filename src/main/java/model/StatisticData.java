package model;

public class StatisticData {

	private Object target;
	private double doanhThu;
	private long soLuongHoaDon;
	private long soLuongVeBan;
	private long soLuongVeHuy;
	private long soLuongKhuyenMai;

	public StatisticData(Object target, double doanhThu, long soLuongHoaDon, long soLuongVeBan, long soLuongVeHuy,
			long soLuongKhuyenMai) {
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

	public long getSoLuongHoaDon() {
		return soLuongHoaDon;
	}

	public void setSoLuongHoaDon(long soLuongHoaDon) {
		this.soLuongHoaDon = soLuongHoaDon;
	}

	public long getSoLuongVeBan() {
		return soLuongVeBan;
	}

	public void setSoLuongVeBan(long soLuongVeBan) {
		this.soLuongVeBan = soLuongVeBan;
	}

	public long getSoLuongVeHuy() {
		return soLuongVeHuy;
	}

	public void setSoLuongVeHuy(long soLuongVeHuy) {
		this.soLuongVeHuy = soLuongVeHuy;
	}

	public long getSoLuongKhuyenMai() {
		return soLuongKhuyenMai;
	}

	public void setSoLuongKhuyenMai(long soLuongKhuyenMai) {
		this.soLuongKhuyenMai = soLuongKhuyenMai;
	}

}
