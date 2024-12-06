package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class KhuyenMai {

	public enum TinhTrangKhuyenMai {
		CON(0), HET_SO_LUONG(1), HET_HAN_SU_DUNG(2);

		private int value;

		public int getValue() {
			return value;
		}

		private TinhTrangKhuyenMai(int value) {
			this.value = value;
		}

		public static TinhTrangKhuyenMai fromValue(int value) {
			for (TinhTrangKhuyenMai status : TinhTrangKhuyenMai.values()) {
				if (status.getValue() == value) {
					return status;
				}
			}

			throw new IllegalArgumentException("Sai giá trị enum TinhTrangKhuyenMai: " + value);
		}

	}

	private String maKhuyenMai;
	private String tenKhuyenMai;
	private String noiDungKhuyenMai;
	private int giamGia;
	private int soLuongToiDa;
	private LocalDateTime thoiGianBatDau;
	private LocalDateTime hanSuDungKhuyenMai;
	private TinhTrangKhuyenMai tinhTrangKhuyenMai;

	public KhuyenMai() {
	}

	public KhuyenMai(String maKhuyenMai) {
		setMaKhuyenMai(maKhuyenMai);
	}

	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, String noiDungKhuyenMai, int giamGia, int soLuongToiDa,
			LocalDateTime thoiGianBatDau, LocalDateTime hanSuDungKhuyenMai, TinhTrangKhuyenMai tinhTrangKhuyenMai) {
		setMaKhuyenMai(maKhuyenMai);
		setTenKhuyenMai(tenKhuyenMai);
		setNoiDungKhuyenMai(noiDungKhuyenMai);
		setGiamGia(giamGia);
		setSoLuongToiDa(soLuongToiDa);
		this.thoiGianBatDau = thoiGianBatDau;
		setHanSuDungKhuyenMai(hanSuDungKhuyenMai);
		setTinhTrangKhuyenMai(tinhTrangKhuyenMai);
	}

	public LocalDateTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
		if (thoiGianBatDau.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Thời gian bắt đầu khuyến mãi phải sau hoặc bằng thời gian hiện tại");
		}
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public int getGiamGia() {
		return giamGia;
	}

	public void setGiamGia(int giamGia) {
		this.giamGia = giamGia;
	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	private void setMaKhuyenMai(String maKhuyenMai) {
		if (!maKhuyenMai.matches("^KM\\d{4}$")) {
			throw new IllegalArgumentException(
					"Mã khuyến mãi phải bắt đầu bằng KM và theo sau là bốn ký số ngẫu nhiên (KMXXXX)");
		}

		this.maKhuyenMai = maKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		if (tenKhuyenMai.isBlank()) {
			throw new IllegalArgumentException("Tên khuyến mãi không được để trống");
		}

		this.tenKhuyenMai = tenKhuyenMai;
	}

	public String getNoiDungKhuyenMai() {
		return noiDungKhuyenMai;
	}

	public void setNoiDungKhuyenMai(String noiDungKhuyenMai) {
		if (noiDungKhuyenMai.isBlank()) {
			throw new IllegalArgumentException("Nội dung khuyến mãi không được để trống");
		}

		this.noiDungKhuyenMai = noiDungKhuyenMai;
	}

	public int getSoLuongToiDa() {
		return soLuongToiDa;
	}

	public void setSoLuongToiDa(int soLuongToiDa) {
		if (soLuongToiDa < 0) {
			throw new IllegalArgumentException("Số lượng tối đa không được là số âm");
		}

		this.soLuongToiDa = soLuongToiDa;
	}

	public LocalDateTime getHanSuDungKhuyenMai() {
		return hanSuDungKhuyenMai;
	}

	public void setHanSuDungKhuyenMai(LocalDateTime hanSuDungKhuyenMai) {
		if (hanSuDungKhuyenMai.isBefore(thoiGianBatDau)) {
			throw new IllegalArgumentException("Hạn sử dụng khuyến mãi phải sau thời gian bắt đầu khuyến mãi");
		}

		this.hanSuDungKhuyenMai = hanSuDungKhuyenMai;
	}

	public TinhTrangKhuyenMai getTinhTrangKhuyenMai() {
		return tinhTrangKhuyenMai;
	}

	public void setTinhTrangKhuyenMai(TinhTrangKhuyenMai tinhTrangKhuyenMai) {
		this.tinhTrangKhuyenMai = tinhTrangKhuyenMai;
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
