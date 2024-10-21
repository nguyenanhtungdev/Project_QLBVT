package model;

import java.time.LocalDateTime;

public class ThongTinGiuCho {
	private String maThongTinGiuCho;
	private LocalDateTime ngayDatGiuCho;
	private LocalDateTime ngayHetHanGiuCho;
	private String ghiChu;
	
	public ThongTinGiuCho(String maThongTinGiuCho, LocalDateTime ngayDatGiuCho, LocalDateTime ngayHetHanGiuCho,
			String ghiChu) {
		super();
		this.maThongTinGiuCho = maThongTinGiuCho;
		this.ngayDatGiuCho = ngayDatGiuCho;
		this.ngayHetHanGiuCho = ngayHetHanGiuCho;
		this.ghiChu = ghiChu;
	}
	
	public ThongTinGiuCho(String maThongTinGiuCho) {
		super();
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

	@Override
	public String toString() {
		return "ThongTinGiuCho [maThongTinGiuCho=" + maThongTinGiuCho + ", ngayDatGiuCho=" + ngayDatGiuCho
				+ ", ngayHetHanGiuCho=" + ngayHetHanGiuCho + ", ghiChu=" + ghiChu + "]";
	}
}
