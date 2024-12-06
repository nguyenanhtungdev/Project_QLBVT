package model;

public class ThongTinTram {

	private String maNhaGa;
	private String tenNhaGa;
	private String diaChi;
	private String dienThoai;
	private String email;
	private String tenNganHang;
	private String soTaiKhoản;
	private String maSoThue;

	public ThongTinTram(String maNhaGa, String tenNhaGa, String diaChi, String dienThoai, String email,
			String tenNganHang, String soTaiKhoản, String maSoThue) {
		this.maNhaGa = maNhaGa;
		this.tenNhaGa = tenNhaGa;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.email = email;
		this.tenNganHang = tenNganHang;
		this.soTaiKhoản = soTaiKhoản;
		this.maSoThue = maSoThue;
	}

	public ThongTinTram(String maNhaGa) {
		this.maNhaGa = maNhaGa;
	}

	public ThongTinTram() {
	}

	public String getMaNhaGa() {
		return maNhaGa;
	}

	public void setMaNhaGa(String maNhaGa) {
		this.maNhaGa = maNhaGa;
	}

	public String getTenNhaGa() {
		return tenNhaGa;
	}

	public void setTenNhaGa(String tenNhaGa) {
		this.tenNhaGa = tenNhaGa;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTenNganHang() {
		return tenNganHang;
	}

	public void setTenNganHang(String tenNganHang) {
		this.tenNganHang = tenNganHang;
	}

	public String getSoTaiKhoản() {
		return soTaiKhoản;
	}

	public void setSoTaiKhoản(String soTaiKhoản) {
		this.soTaiKhoản = soTaiKhoản;
	}

	public String getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	@Override
	public String toString() {
		return "ThongTinTram [maNhaGa=" + maNhaGa + ", tenNhaGa=" + tenNhaGa + ", diaChi=" + diaChi + ", dienThoai="
				+ dienThoai + ", email=" + email + ", tenNganHang=" + tenNganHang + ", soTaiKhoản=" + soTaiKhoản
				+ ", maSoThue=" + maSoThue + "]";
	}

}
