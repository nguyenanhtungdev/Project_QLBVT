package model;

import java.time.LocalDateTime;

import model.KhachHang.LoaiKhachHang;
import model.Tau.TrangThaiTau;

public class ThongKeFilters {

	private KhachHang[] khachHang;
	private LoaiKhachHang[] khachHangCategory;
	private NhanVien[] nhanVien;
	private CaLam[] caLam;
	private KhuyenMai[] khuyenMai;
	private ChuyenTau[] chuyenTau;
	private Tau[] tau;
	private TrangThaiTau[] tauStatus;
	private ToaTau[] toaTau;
	private Boolean[] toaTauStatus;
	private GheTau[] gheTau;
	private String[] gheTauStatus;
	private Boolean[] loaiVe;
	private Boolean[] trangThaiVe;
	private LocalDateTime tuLuc;
	private LocalDateTime denLuc;

	public KhachHang[] getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang[] khachHang) {
		this.khachHang = khachHang;
	}

	public LoaiKhachHang[] getKhachHangCategory() {
		return khachHangCategory;
	}

	public void setKhachHangCategory(LoaiKhachHang[] khachHangCategory) {
		this.khachHangCategory = khachHangCategory;
	}

	public NhanVien[] getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien[] nhanVien) {
		this.nhanVien = nhanVien;
	}

	public CaLam[] getCaLam() {
		return caLam;
	}

	public void setCaLam(CaLam[] caLam) {
		this.caLam = caLam;
	}

	public KhuyenMai[] getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai[] khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public ChuyenTau[] getChuyenTau() {
		return chuyenTau;
	}

	public void setChuyenTau(ChuyenTau[] chuyenTau) {
		this.chuyenTau = chuyenTau;
	}

	public Tau[] getTau() {
		return tau;
	}

	public void setTau(Tau[] tau) {
		this.tau = tau;
	}

	public TrangThaiTau[] getTauStatus() {
		return tauStatus;
	}

	public void setTauStatus(TrangThaiTau[] tauStatus) {
		this.tauStatus = tauStatus;
	}

	public ToaTau[] getToaTau() {
		return toaTau;
	}

	public void setToaTau(ToaTau[] toaTau) {
		this.toaTau = toaTau;
	}

	public Boolean[] getToaTauStatus() {
		return toaTauStatus;
	}

	public void setToaTauStatus(Boolean[] toaTauStatus) {
		this.toaTauStatus = toaTauStatus;
	}

	public GheTau[] getGheTau() {
		return gheTau;
	}

	public void setGheTau(GheTau[] gheTau) {
		this.gheTau = gheTau;
	}

	public String[] getGheTauStatus() {
		return gheTauStatus;
	}

	public void setGheTauStatus(String[] gheTauStatus) {
		this.gheTauStatus = gheTauStatus;
	}

	public Boolean[] getLoaiVe() {
		return loaiVe;
	}

	public void setLoaiVe(Boolean[] loaiVe) {
		this.loaiVe = loaiVe;
	}

	public Boolean[] getTrangThaiVe() {
		return trangThaiVe;
	}

	public void setTrangThaiVe(Boolean[] trangThaiVe) {
		this.trangThaiVe = trangThaiVe;
	}

	public LocalDateTime getTuLuc() {
		return tuLuc;
	}

	public void setTuLuc(LocalDateTime tuLuc) {
		this.tuLuc = tuLuc;
	}

	public LocalDateTime getDenLuc() {
		return denLuc;
	}

	public void setDenLuc(LocalDateTime denLuc) {
		this.denLuc = denLuc;
	}

}
