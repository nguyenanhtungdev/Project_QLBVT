package model;

public class ChiTiet_HoaDon {

	private int soLuong;
	private HoaDon hoaDon;
	private KhuyenMai khuyenMai;
	private VeTau veTau;

	public ChiTiet_HoaDon() {

	}
	
	public ChiTiet_HoaDon(int soLuong, HoaDon hoaDon, VeTau veTau) {
		this.soLuong = soLuong;
		this.hoaDon = hoaDon;
		this.veTau = veTau;
	}

	public ChiTiet_HoaDon(int soLuong, HoaDon hoaDon, KhuyenMai khuyenMai, VeTau veTau) {
		setSoLuong(soLuong);
		this.hoaDon = hoaDon;
		this.khuyenMai = khuyenMai;
		this.veTau = veTau;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		if (soLuong > 0) {
			this.soLuong = soLuong;
		} else {
			throw new IllegalArgumentException("Số lượng phải là số nguyên dương lớn hơn 0.");
		}
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public VeTau getVeTau() {
		return veTau;
	}

	public void setVeTau(VeTau veTau) {
		this.veTau = veTau;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public double tinhThanhTien(Double giaVeHienTai, Double tiLeGiamGia) {
		return giaVeHienTai - (giaVeHienTai * tiLeGiamGia);
	}
	
	@Override
	public String toString() {
		return "ChiTiet_HoaDon {soLuong: " + soLuong + ", hoaDon: " + hoaDon + ", veTau: " + veTau + ", khuyenMai: "
				+ khuyenMai + "}";
	}

}
