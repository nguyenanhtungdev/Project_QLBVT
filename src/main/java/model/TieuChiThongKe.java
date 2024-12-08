package model;

import java.time.LocalDateTime;

import model.KhachHang.LoaiKhachHang;

public class TieuChiThongKe {

	public enum StatisticType {
		TAT_CA, DOANH_THU, SO_LUONG_HOA_DON, SO_LUONG_VE, SO_LUONG_VE_HUY, SO_LUONG_KHUYEN_MAI
	}

	private StatisticType loaiThongKe;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private KhachHang.LoaiKhachHang loaiKhachHang;
	private boolean loaiVe;
	private KhuyenMai khuyenMai;
	private Tau tau;
	private ChuyenTau chuyenTau;
	private CaLam caLam;
	private LocalDateTime tuLuc;
	private LocalDateTime denLuc;

	public TieuChiThongKe(StatisticType loaiThongKe, NhanVien nhanVien, KhachHang khachHang,
			LoaiKhachHang loaiKhachHang, boolean loaiVe, KhuyenMai khuyenMai, Tau tau, ChuyenTau chuyenTau, CaLam caLam,
			LocalDateTime tuLuc, LocalDateTime denLuc) {
		this.loaiThongKe = loaiThongKe;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.loaiKhachHang = loaiKhachHang;
		this.loaiVe = loaiVe;
		this.khuyenMai = khuyenMai;
		this.tau = tau;
		this.chuyenTau = chuyenTau;
		this.caLam = caLam;
		this.tuLuc = tuLuc;
		this.denLuc = denLuc;
	}

	public StatisticType getLoaiThongKe() {
		return loaiThongKe;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public KhachHang.LoaiKhachHang getLoaiKhachHang() {
		return loaiKhachHang;
	}

	public boolean isLoaiVe() {
		return loaiVe;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public Tau getTau() {
		return tau;
	}

	public ChuyenTau getChuyenTau() {
		return chuyenTau;
	}

	public CaLam getCaLam() {
		return caLam;
	}

	public LocalDateTime getTuLuc() {
		return tuLuc;
	}

	public LocalDateTime getDenLuc() {
		return denLuc;
	}

}
