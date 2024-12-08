package model;

import java.time.LocalDateTime;

public class TaiKhoan {

	private String maTaiKhoan;
	private String tenDangNhap;
	private String matKhau;
	private boolean trangThai;
	private LocalDateTime ngayTaoTaiKhoan;

	private NhanVien nhanVien;

	public TaiKhoan() {
	}

	public TaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public TaiKhoan(String maTaiKhoan, String tenDangNhap, String matKhau, boolean trangThai,
			LocalDateTime ngayTaoTaiKhoan, NhanVien nhanVien) {
		this.setMaTaiKhoan(maTaiKhoan);
		this.setTenDangNhap(tenDangNhap);
		this.setMatKhau(matKhau);
		this.setTrangThai(trangThai);
		this.setNgayTaoTaiKhoan(ngayTaoTaiKhoan);
		this.nhanVien = nhanVien;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		// Ràng buộc: phải là duy nhất, không được rỗng, và không chứa ký tự đặc biệt
		// trừ "_" hoặc "."
		if (tenDangNhap != null && !tenDangNhap.trim().isEmpty() && tenDangNhap.matches("^[a-zA-Z0-9._]+$")) {
			this.tenDangNhap = tenDangNhap;
		} else {
			throw new IllegalArgumentException("Tên đăng nhập không hợp lệ!");
		}
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		// Ràng buộc: tối thiểu 8 ký tự, bao gồm ít nhất 1 chữ hoa, 1 chữ thường, 1 số
		// và 1 ký tự đặc biệt
		matKhau = matKhau.trim();
		if (matKhau != null && matKhau.length() >= 8
				&& matKhau.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$")) {
			this.matKhau = matKhau;
		} else {
			throw new IllegalArgumentException("Mật khẩu không hợp lệ!");
		}
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public LocalDateTime getNgayTaoTaiKhoan() {
		return ngayTaoTaiKhoan;
	}

	public void setNgayTaoTaiKhoan(LocalDateTime ngayTaoTaiKhoan) {
		// Ràng buộc: phải là ngày hiện tại hoặc trước đó, không được ở tương lai
		if (ngayTaoTaiKhoan.isBefore(LocalDateTime.now()) || ngayTaoTaiKhoan.isEqual(LocalDateTime.now())) {
			this.ngayTaoTaiKhoan = ngayTaoTaiKhoan;
		} else {
			throw new IllegalArgumentException("Ngày tạo tài khoản không hợp lệ!");
		}
	}

	public NhanVien getNhanVien() {
		return nhanVien.getHoTenNV() == null ? nhanVien = NhanVien_DAO.getInstance().getByMaNhanVien(nhanVien.getMaNV())
				: nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "TaiKhoan [maTaiKhoan=" + maTaiKhoan + ", tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau
				+ ", trangThai=" + trangThai + ", ngayTaoTaiKhoan=" + ngayTaoTaiKhoan + ", nhanVien=" + nhanVien + "]";
	}

}
