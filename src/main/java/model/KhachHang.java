package model;

import java.time.LocalDate;
import java.time.Period;

public class KhachHang {
	private String maKhachHang;
	private String hoTen;
	private String soDienThoai;
	private String email;
	private boolean gioiTinh;
	private String CCCD;
	private LocalDate ngaySinh;
	public LoaiKhachHang loaiKH;
	
    public enum LoaiKhachHang {
        TRE_EM(1.0),          
        SINH_VIEN(0.05),    
        HOC_SINH(0.1),      
        NGUOI_GIA(0.15),    
        NGUOI_KHUYET_TAT(0.2); 
        
        private final double discount;

        // Constructor cho enum
        LoaiKhachHang(double discount) {
            this.discount = discount;
        }

        public double getDiscount() {
            return discount;
        }
    }
	
	public KhachHang(String maKhachHang, String hoTen, String soDienThoai, String email, boolean gioiTinh, String CCCD,
			LocalDate ngaySinh, LoaiKhachHang loaiKH) {
		this.setMaKhachHang(maKhachHang);
		this.setHoTen(hoTen); 
		this.setSoDienThoai(soDienThoai);
		this.setEmail(email);
		this.setGioiTinh(gioiTinh);
		this.setCCCD(CCCD);
		this.setNgaySinh(ngaySinh);
		this.setLoaiKH(loaiKH);
	}

	public KhachHang() {
	}

	public KhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		if (maKhachHang != null && maKhachHang.matches("^KH\\d{7}$")) {
            this.maKhachHang = maKhachHang;
        } else {
            throw new IllegalArgumentException("Mã khách hàng phải có dạng 'NVxxxxxxx', với 'xxxxxxx' là các chữ số.");
        }
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		if (hoTen != null && hoTen.matches("^[a-zA-Z\\p{L} ]+$")) {
            this.hoTen = hoTen;
        } else {
            throw new IllegalArgumentException("Họ tên khách hàng chỉ được chứa các chữ cái và khoảng trắng.");
        }
  
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		if (soDienThoai != null && soDienThoai.matches("^\\d{10}$")) {
            this.soDienThoai = soDienThoai;
        } else {
            throw new IllegalArgumentException("Độ dài chuỗi phải tuân thủ là 10 ký tự. ");
        }
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null && email.matches("^[a-zA-Z0-9._-]+@gmail.com$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Định dạng email không hợp lệ! ");
        }
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getCCCD() {
		return CCCD;
	}

	public void setCCCD(String CCCD) {
		if (CCCD != null && CCCD.matches("^\\d{12}$")) {
            this.CCCD = CCCD;
        } else {
            throw new IllegalArgumentException("Định dạng CCCD không hợp lệ! ");
        }
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		if (ngaySinh.isBefore(LocalDate.now())) {
            this.ngaySinh = ngaySinh;
        } else {
            throw new IllegalArgumentException("Ngày sinh không hợp lệ. ");
        }
	}
	

	public LoaiKhachHang getLoaiKH() {
		return loaiKH;
	}

	public void setLoaiKH(LoaiKhachHang loaiKH) {
		this.loaiKH = loaiKH;
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai
				+ ", email=" + email + ", gioiTinh=" + gioiTinh + ", CCCD=" + CCCD + ", ngaySinh=" + ngaySinh
				+ ", loaiKH=" + loaiKH + "]";
	}
}
