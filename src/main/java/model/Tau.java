package model;

import java.time.LocalDate;

public class Tau {

	private String maTau;
	private String tenTau;
	private int soToa;
	private LocalDate namSanXuat;
	private String nhaSanXuat;
	private float tocDoTB;
	private float tocDoToiDa;
	private String ghiChu;
	private TrangThaiTau trangThai;

	private ChuyenTau chuyenTau;

	public enum TrangThaiTau {
		HOAT_DONG(0), BAO_TRI(1), DUNG_HOAT_DONG(2);

		private final int trangThai;

		TrangThaiTau(int trangThai) {
			this.trangThai = trangThai;
		}

		public int getTrangThai() {
			return trangThai;
		}

		public static TrangThaiTau fromInt(int i) {
			for (TrangThaiTau tt : values()) {
				if (tt.getTrangThai() == i) {
					return tt;
				}
			}
			throw new IllegalArgumentException("No enum constant for value: " + i);
		}

		public static TrangThaiTau toEnum(String value) {
			for (TrangThaiTau tt : values()) {
				if (tt.toString().equals(value)) {
					return tt;
				}
			}
			throw new IllegalArgumentException("No enum constant for value: " + value);
		}

		@Override
		public String toString() {
			switch (this) {
			case HOAT_DONG:
				return "Hoạt động";
			case BAO_TRI:
				return "Bảo trì";
			case DUNG_HOAT_DONG:
				return "Dừng hoạt động";
			default:
				return "";
			}
		}
	}

	public Tau() {
	}

	public Tau(String maTau) {
		this.maTau = maTau;
	}

	public Tau(String maTau, String tenTau, int soToa, LocalDate namSanXuat, String nhaSanXuat, float tocDoTB,
			float tocDoToiDa, TrangThaiTau trangThai, String ghiChu, ChuyenTau chuyenTau) {
		this.maTau = maTau;
		this.tenTau = tenTau;
		this.soToa = soToa;
		this.namSanXuat = namSanXuat;
		this.nhaSanXuat = nhaSanXuat;
		this.tocDoTB = tocDoTB;
		this.tocDoToiDa = tocDoToiDa;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
		this.chuyenTau = chuyenTau;
	}

	public String getMaTau() {
		return maTau;
	}

	public void setMaTau(String maTau) {
		if (!maTau.matches("[A-Z]{3}\\d{4}")) {
			throw new IllegalArgumentException("Mã tàu phải theo định dạng AAAXXXX, với AAA là chữ và XXXX là dãy số.");
		}
		this.maTau = maTau;
	}

	public String getTenTau() {
		return tenTau;
	}

	public void setTenTau(String tenTau) {
		if (tenTau == null || tenTau.isEmpty()) {
			throw new IllegalArgumentException("Tên tàu không được để trống.");
		}
		this.tenTau = tenTau;
	}

	public int getSoToa() {
		return soToa;
	}

	public void setSoToa(int soToa) {
		if (soToa <= 0) {
			throw new IllegalArgumentException("Số toa phải là một số dương lớn hơn 0.");
		}
		this.soToa = soToa;
	}

	public LocalDate getNamSanXuat() {
		return namSanXuat;
	}

	public void setNamSanXuat(LocalDate namSanXuat) {
		if (namSanXuat == null || namSanXuat.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException(
					"Năm sản xuất phải nhỏ hơn hoặc bằng năm hiện tại và không được để trống.");
		}
		this.namSanXuat = namSanXuat;
	}

	public String getNhaSanXuat() {
		return nhaSanXuat;
	}

	public void setNhaSanXuat(String nhaSanXuat) {
		if (nhaSanXuat == null || nhaSanXuat.isEmpty()) {
			throw new IllegalArgumentException("Nhà sản xuất không được để trống.");
		}
		this.nhaSanXuat = nhaSanXuat;
	}

	public float getTocDoTB() {
		return tocDoTB;
	}

	public void setTocDoTB(float tocDoTB) {
		if (tocDoTB <= 0) {
			throw new IllegalArgumentException("Tốc độ trung bình phải là số dương lớn hơn 0.");
		}
		this.tocDoTB = tocDoTB;
	}

	public float getTocDoToiDa() {
		return tocDoToiDa;
	}

	public void setTocDoToiDa(float tocDoToiDa) {
		if (tocDoToiDa <= 0) {
			throw new IllegalArgumentException("Tốc độ tối đa phải là số dương lớn hơn 0.");
		}
		this.tocDoToiDa = tocDoToiDa;
	}

	public TrangThaiTau getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiTau trangThai) {
		if (trangThai == null) {
			throw new IllegalArgumentException("Trạng thái không được để trống.");
		}
		this.trangThai = trangThai;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public ChuyenTau getChuyenTau() {
		return chuyenTau.getGaKhoiHanh() == null
				? chuyenTau = ChuyenTau_DAO.getInstance().getByMaChuyenTau(chuyenTau.getMaChuyenTau())
				: chuyenTau;
	}

	public void setChuyenTau(ChuyenTau chuyenTau) {
		this.chuyenTau = chuyenTau;
	}

	@Override
	public String toString() {
		return "Tau {maTau: " + maTau + ", tenTau: " + tenTau + ", soToa: " + soToa + ", namSanXuat: " + namSanXuat
				+ ", nhaSanXuat: " + nhaSanXuat + ", tocDoTB: " + tocDoTB + ", tocDoToiDa: " + tocDoToiDa + ", ghiChu: "
				+ ghiChu + ", trangThai: " + trangThai + "}";
	}

}
