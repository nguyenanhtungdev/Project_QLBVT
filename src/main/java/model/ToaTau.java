package model;

public class ToaTau {
	private String maToaTau ; 
	private String tenToaTau ;
	private int soThuTuToa ;
	private String loaiToa ; 
	private int soLuongGhe ; 
	private boolean trangThai = true ;
	
	public ToaTau() {
		super();
	}
	
	public ToaTau(String maToaTau, String tenToaTau, int soThuTuToa, String loaiToa, int soLuongGhe,
			boolean trangThai) {
		super();
		this.maToaTau = maToaTau;
		this.tenToaTau = tenToaTau;
		this.soThuTuToa = soThuTuToa;
		this.loaiToa = loaiToa;
		this.soLuongGhe = soLuongGhe;
		this.trangThai = trangThai;
	}
	
	public ToaTau(String maToaTau) {
		super();
		this.maToaTau = maToaTau;
	}
	
	public String getMaToaTau() {
		return maToaTau;
	}
	
	public void setMaToaTau(String maToaTau) {
		if(maToaTau != null && maToaTau.matches("^TT\\d{6}")) {
			this.maToaTau = maToaTau;
		} else {
			throw new IllegalArgumentException("Mã toa tàu phải có dạng 'VTXXXXYY' với XXXXYY là các chữ số"); 
		}
	}
	
	public String getTenToaTau() {
		return tenToaTau;
	}
	
	public void setTenToaTau(String tenToaTau) {
		 if (tenToaTau != null && tenToaTau.matches("^[TV]\\d{2}$")) {
		        this.tenToaTau = tenToaTau;
		    } else {
		        throw new IllegalArgumentException("Tên toa tàu không được để trống và phải có dạng 'LXX' với L là loại toa , XX là các chữ số");
		    }
	}
	
	public int getSoThuTuToa() {
		return soThuTuToa;
	}
	
	public void setSoThuTuToa(int soThuTuToa) {
		if(soThuTuToa > 0){
			this.soThuTuToa = soThuTuToa;
		}else {
			throw new IllegalArgumentException("STT toa tàu không hợp lệ ") ;
		}
	}
	
	public String getLoaiToa() {
		return loaiToa;
	}
	
	public void setLoaiToa(String loaiToa) {
		if (loaiToa != null && (loaiToa.equals("T") || loaiToa.equals("V"))) {
	        this.loaiToa = loaiToa;
	    } else {
	        throw new IllegalArgumentException("Loại toa không hợp lệ ");
	    }
	}
	
	public int getSoLuongGhe() {
		return soLuongGhe;
	}
	
	public void setSoLuongGhe(int soLuongGhe) {
		if(soLuongGhe > 0){
			this.soLuongGhe = soLuongGhe;
		}else {
			throw new IllegalArgumentException("Số lượng ghế không hợp lệ ")
		}
	}
	
	public boolean isTrangThai() {
		return trangThai;
	}
	
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
	@Override
	public String toString() {
		return "ToaTau [maToaTau=" + maToaTau + ", tenToaTau=" + tenToaTau + ", soThuTuToa=" + soThuTuToa + ", loaiToa="
				+ loaiToa + ", soLuongGhe=" + soLuongGhe + ", trangThai=" + trangThai + "]";
	} 
}

