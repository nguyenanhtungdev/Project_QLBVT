package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class KhuyenMai {

	private String maKhuyenMai;
	private String tenKhuyenMai;
	private String noiDungKhuyenMai;
	private int soLuongToiDa;
	private LocalDateTime hanSuDungKhuyenMai;
	private TinhTrangKhuyenMai tinhTrangKhuyenMai;

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	private void setMaKhuyenMai(String maKhuyenMai) throws Exception {
		if (!maKhuyenMai.matches("^KM\\d{4}$")) {
			throw new Exception("Mã khuyến mãi phải bắt đầu bằng KM và theo sau là bốn ký số ngẫu nhiên (KMXXXX)");
		}

		this.maKhuyenMai = maKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) throws Exception {
		if (tenKhuyenMai.isBlank()) {
			throw new Exception("Tên khuyến mãi không được để trống");
		}

		this.tenKhuyenMai = tenKhuyenMai;
	}

	public String getNoiDungKhuyenMai() {
		return noiDungKhuyenMai;
	}

	public void setNoiDungKhuyenMai(String noiDungKhuyenMai) throws Exception {
		if (noiDungKhuyenMai.isBlank()) {
			throw new Exception("Nội dung khuyến mãi không được để trống");
		}

		this.noiDungKhuyenMai = noiDungKhuyenMai;
	}

	public int getSoLuongToiDa() {
		return soLuongToiDa;
	}

	public void setSoLuongToiDa(int soLuongToiDa) throws Exception {
		if (soLuongToiDa < 0) {
			throw new Exception("Số lượng tối đa không được là số âm");
		}

		this.soLuongToiDa = soLuongToiDa;
	}

	public LocalDateTime getHanSuDungKhuyenMai() {
		return hanSuDungKhuyenMai;
	}

	public void setHanSuDungKhuyenMai(LocalDateTime hanSuDungKhuyenMai) throws Exception {
		if (!hanSuDungKhuyenMai.isAfter(LocalDateTime.now())) {
			throw new Exception("Hạn sử dụng khuyến mãi phải sau thời gian hiện tại");
		}

		this.hanSuDungKhuyenMai = hanSuDungKhuyenMai;
	}

	public TinhTrangKhuyenMai getTinhTrangKhuyenMai() {
		return tinhTrangKhuyenMai;
	}

	public void setTinhTrangKhuyenMai(TinhTrangKhuyenMai tinhTrangKhuyenMai) {
		this.tinhTrangKhuyenMai = tinhTrangKhuyenMai;
	}

	public KhuyenMai() {

	}

	public KhuyenMai(String maKhuyenMai) throws Exception {
		setMaKhuyenMai(maKhuyenMai);
	}

	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, String noiDungKhuyenMai, int soLuongToiDa,
			LocalDateTime hanSuDungKhuyenMai, TinhTrangKhuyenMai tinhTrangKhuyenMai) throws Exception {
		setMaKhuyenMai(maKhuyenMai);
		setTenKhuyenMai(tenKhuyenMai);
		setNoiDungKhuyenMai(noiDungKhuyenMai);
		setSoLuongToiDa(soLuongToiDa);
		setHanSuDungKhuyenMai(hanSuDungKhuyenMai);
		setTinhTrangKhuyenMai(tinhTrangKhuyenMai);
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKhuyenMai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof KhuyenMai))
			return false;
		KhuyenMai other = (KhuyenMai) obj;
		return Objects.equals(maKhuyenMai, other.maKhuyenMai);
	}

	@Override
	public String toString() {
		return "KhuyenMai {maKhuyenMai: " + maKhuyenMai + ", tenKhuyenMai: " + tenKhuyenMai + ", noiDungKhuyenMai: "
				+ noiDungKhuyenMai + ", soLuongToiDa: " + soLuongToiDa + ", hanSuDungKhuyenMai: " + hanSuDungKhuyenMai
				+ ", tinhTrangKhuyenMai: " + tinhTrangKhuyenMai + "}";
	}

}
