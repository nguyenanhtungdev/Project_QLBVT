package model;

import java.time.LocalDateTime;

public class NhanVien_CaLam {

	private LocalDateTime thoiGianNhanCa;
	private LocalDateTime thoiGianKetThucCa;

	private NhanVien nhanVien;
	private CaLam caLam;

	public NhanVien_CaLam() {
	}

	public NhanVien_CaLam(LocalDateTime thoiGianNhanCa, LocalDateTime thoiGianKetThucCa, NhanVien nhanVien,
			CaLam caLam) {
		this.thoiGianNhanCa = thoiGianNhanCa;
		this.thoiGianKetThucCa = thoiGianKetThucCa;
		this.nhanVien = nhanVien;
		this.caLam = caLam;
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

	public NhanVien getNhanVien() {
		return nhanVien.getHoTenNV() == null ? nhanVien = NhanVien_DAO.getInstance().getByMaNhanVien(nhanVien.getMaNV())
				: nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public CaLam getCaLam() {
		return caLam.getTenCa() == null ? caLam = CaLam_DAO.getInstance().getByMaCa(caLam.getMaCa()) : caLam;
	}

	public void setCaLam(CaLam caLam) {
		this.caLam = caLam;
	}

	public float soGioLam() {
		if (thoiGianNhanCa != null && thoiGianKetThucCa != null) {
			return (float) java.time.Duration.between(thoiGianNhanCa, thoiGianKetThucCa).toHours();
		}
		return 0;
	}

	@Override
	public String toString() {
		return "NhanVien_CaLam {thoiGianNhanCa: " + thoiGianNhanCa + ", thoiGianKetThucCa: " + thoiGianKetThucCa
				+ ", nhanVien: " + nhanVien + ", caLam: " + caLam + "}";
	}

}
