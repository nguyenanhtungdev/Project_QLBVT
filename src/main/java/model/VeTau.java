package model;

import java.time.Year;

public class VeTau {
	private String maVeTau ; 
	private String maVach ; 
	private boolean loaiVe ; 
	private TrangThaiVeTau trangThai ;
	
	public VeTau() {
	}
	
	public VeTau(String maVeTau, String maVach, boolean loaiVe, TrangThaiVeTau trangThai) {
		super();
		this.maVeTau = maVeTau;
		this.maVach = maVach;
		this.loaiVe = loaiVe;
		this.trangThai = trangThai;
	}
	
	public VeTau(String maVeTau) {
		super();
		this.maVeTau = maVeTau;
	}
	
	public String getMaVeTau() {
		return maVeTau;
	}
	
	public void setMaVeTau(String maVeTau) {
		int y = Year.now().getValue();
	    String yy = String.valueOf(y).substring(2);
	    if(maVeTau != null && maVeTau.matches("^VT" + yy + "\\d{4}")) {
	        this.maVeTau = maVeTau;
	    } else {
	        throw new IllegalArgumentException("Mã vé tàu phải có dạng 'VTYYXXXX' với YY là 2 chữ số cuối năm hiện tại và XXXX là các chữ số.");
	    }
		
	}
	
	public String getMaVach() {
		return maVach;
	}
	
	public void setMaVach(String maVach) {
		if(maVach != null) {
			this.maVach = maVach;
		} else {
			throw new IllegalArgumentException("Mã vạch không hợp lệ"); 
		}
	}
	
	public boolean isLoaiVe() {
		return loaiVe;
	}
	
	public void setLoaiVe(boolean loaiVe) {
		this.loaiVe = loaiVe;
	}
	
	public TrangThaiVeTau getTrangThai() {
		return trangThai;
	}
	
	public void setTrangThai(TrangThaiVeTau trangThai) {
		this.trangThai = trangThai;
	}
	
	@Override
	public String toString() {
		return "VeTau [maVeTau=" + maVeTau + ", maVach=" + maVach + ", loaiVe=" + loaiVe + ",trangThai=" + trangThai + "]"; 
	}
}
