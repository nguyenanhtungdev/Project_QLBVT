package model;

import java.time.LocalDateTime;

public class ThongTinGiuCho {

	private String maThongTinGiuCho;
	private LocalDateTime ngayDatGiuCho;
	private LocalDateTime ngayHetHanGiuCho;
	private String ghiChu;

	private KhachHang khachHang;

	public ThongTinGiuCho(String maThongTinGiuCho, LocalDateTime ngayDatGiuCho, LocalDateTime ngayHetHanGiuCho,
			String ghiChu, KhachHang khachHang) {
		this.maThongTinGiuCho = maThongTinGiuCho;
		this.ngayDatGiuCho = ngayDatGiuCho;
		this.ngayHetHanGiuCho = ngayHetHanGiuCho;
		this.ghiChu = ghiChu;
		this.khachHang = khachHang;
	}

	public ThongTinGiuCho(String maThongTinGiuCho) {
		this.maThongTinGiuCho = maThongTinGiuCho;
	}

	public String getMaThongTinGiuCho() {
		return maThongTinGiuCho;
	}

	public void setMaThongTinGiuCho(String maThongTinGiuCho) {
		this.maThongTinGiuCho = maThongTinGiuCho;
	}

	public LocalDateTime getNgayDatGiuCho() {
		return ngayDatGiuCho;
	}

	public void setNgayDatGiuCho(LocalDateTime ngayDatGiuCho) {
		this.ngayDatGiuCho = ngayDatGiuCho;
	}

	public LocalDateTime getNgayHetHanGiuCho() {
		return ngayHetHanGiuCho;
	}

	public void setNgayHetHanGiuCho(LocalDateTime ngayHetHanGiuCho) {
		this.ngayHetHanGiuCho = ngayHetHanGiuCho;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public KhachHang getKhachHang() {
		return khachHang.getSoDienThoai() == null
				? KhachHang_DAO.getInstance().getByMaKhachHang(khachHang.getMaKhachHang())
				: khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	@Override
	public String toString() {
		return "ThongTinGiuCho {maThongTinGiuCho: " + maThongTinGiuCho + ", ngayDatGiuCho: " + ngayDatGiuCho
				+ ", ngayHetHanGiuCho: " + ngayHetHanGiuCho + ", ghiChu: " + ghiChu + ", khachHang: " + khachHang + "}";
	}

}
