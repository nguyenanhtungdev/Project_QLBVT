package model;

import java.time.LocalDateTime;

public class NhanVien_CaLam {
	private LocalDateTime thoiGianNhanCa ; 
	private LocalDateTime thoiGianKetThucCa ;
	
	public NhanVien_CaLam() {
		super();
	}
	
	public NhanVien_CaLam(LocalDateTime thoiGianNhanCa, LocalDateTime thoiGianKetThucCa) {
		super();
		this.thoiGianNhanCa = thoiGianNhanCa;
		this.thoiGianKetThucCa = thoiGianKetThucCa;
	}
	
	public LocalDateTime getThoiGianNhanCa() {
		return thoiGianNhanCa;
	}
	
	public void setThoiGianNhanCa(LocalDateTime thoiGianNhanCa) {
		if (thoiGianNhanCa.isBefore(LocalDateTime.now())) {
	        throw new IllegalArgumentException("Thời gian nhận ca phải lớn hơn hoặc bằng thời gian hiện tại.");
	    }
	    this.thoiGianNhanCa = thoiGianNhanCa;
	}
	
	public LocalDateTime getThoiGianKetThucCa() {
		return thoiGianKetThucCa;
	}
	
	public void setThoiGianKetThucCa(LocalDateTime thoiGianKetThucCa) {
		if (thoiGianKetThucCa.isBefore(thoiGianNhanCa)) {
	        throw new IllegalArgumentException("Thời gian kết thúc ca phải sau thời gian nhận ca.");
	    }
	    this.thoiGianKetThucCa = thoiGianKetThucCa;
	}
	
	@Override
	public String toString() {
		return "NhanVien_CaLam [thoiGianNhanCa=" + thoiGianNhanCa + ", thoiGianKetThucCa=" + thoiGianKetThucCa + "]";
	}
	
	public float soGioLam() {
		if (thoiGianNhanCa != null && thoiGianKetThucCa != null) {
	        return (float) java.time.Duration.between(thoiGianNhanCa, thoiGianKetThucCa).toHours();
	    }
	    return 0;
	}
}
