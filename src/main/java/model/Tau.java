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
	private TrangThaiChuyenTau trangThai;
	private String ghiChu;
	public enum TrangThaiChuyenTau {
	    HOAT_DONG(0),
	    BAO_TRI(1),
	    DUNG_HOAT_DONG(2);

	    private int trangThai;

	    TrangThaiChuyenTau(int i) {
	        this.trangThai = i;
	    }

	    public int getTrangThai() {
	        return trangThai;
	    }
	}
	
	public Tau() {
		// TODO Auto-generated constructor stub
	}

	public Tau(String maTau, String tenTau, int soToa, LocalDate namSanXuat, String nhaSanXuat, float tocDoTB,
			float tocDoToiDa, TrangThaiChuyenTau trangThai, String ghiChu) {
		super();
		this.maTau = maTau;
		this.tenTau = tenTau;
		this.soToa = soToa;
		this.namSanXuat = namSanXuat;
		this.nhaSanXuat = nhaSanXuat;
		this.tocDoTB = tocDoTB;
		this.tocDoToiDa = tocDoToiDa;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
	}

	public Tau(String maTau) {
		super();
		this.maTau = maTau;
	}

	public String getMaTau() {
		return maTau;
	}

	public void setMaTau(String maTau) {
		this.maTau = maTau;
	}

	public String getTenTau() {
		return tenTau;
	}

	public void setTenTau(String tenTau) {
		this.tenTau = tenTau;
	}

	public int getSoToa() {
		return soToa;
	}

	public void setSoToa(int soToa) {
		this.soToa = soToa;
	}

	public LocalDate getNamSanXuat() {
		return namSanXuat;
	}

	public void setNamSanXuat(LocalDate namSanXuat) {
		this.namSanXuat = namSanXuat;
	}

	public String getNhaSanXuat() {
		return nhaSanXuat;
	}

	public void setNhaSanXuat(String nhaSanXuat) {
		this.nhaSanXuat = nhaSanXuat;
	}

	public float getTocDoTB() {
		return tocDoTB;
	}

	public void setTocDoTB(float tocDoTB) {
		this.tocDoTB = tocDoTB;
	}

	public float getTocDoToiDa() {
		return tocDoToiDa;
	}

	public void setTocDoToiDa(float tocDoToiDa) {
		this.tocDoToiDa = tocDoToiDa;
	}

	public TrangThaiChuyenTau getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiChuyenTau trangThai) {
		this.trangThai = trangThai;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "Tau [maTau=" + maTau + ", tenTau=" + tenTau + ", soToa=" + soToa + ", namSanXuat=" + namSanXuat
				+ ", nhaSanXuat=" + nhaSanXuat + ", tocDoTB=" + tocDoTB + ", tocDoToiDa=" + tocDoToiDa + ", trangThai="
				+ trangThai + ", ghiChu=" + ghiChu + "]";
	}
	
	
	
}
