package model;

public class GheTau {

	private String maGheTau;
	private String tenLoaiGheTau;
	private int soThuTuGhe;
	private String trangThai;

	private ToaTau toaTau;

	public GheTau() {
	}

	public GheTau(String maGheTau) {
		this.maGheTau = maGheTau;
	}

	public GheTau(String maGheTau, String tenLoaiGheTau, int soThuTuGhe, String trangThai, ToaTau toaTau) {
		this.setMaGheTau(maGheTau);
		this.setTenLoaiGheTau(tenLoaiGheTau);
		this.setsoThuTuGhe(soThuTuGhe);
		this.setTrangThai(trangThai);
		this.toaTau = toaTau;
	}

	public String getMaGheTau() {
		return maGheTau;
	}

	public void setMaGheTau(String maGheTau) {
		maGheTau = maGheTau.trim();
		if (maGheTau != null && maGheTau.matches("^GT\\d{4}$")) {
			this.maGheTau = maGheTau;
		} else {
			throw new IllegalArgumentException("Mã ghế tàu phải có dạng 'GTXXXX', với 'XXXX' là các chữ số.");
		}
	}

	public String getTenLoaiGheTau() {
		return tenLoaiGheTau;
	}

	public void setTenLoaiGheTau(String tenLoaiGheTau) {
		if (tenLoaiGheTau.equals("Ghế") || tenLoaiGheTau.equals("Giường nằm")) {
			this.tenLoaiGheTau = tenLoaiGheTau;
		} else {
			throw new IllegalArgumentException("Tên loại ghế tàu không hợp lệ!");
		}
	}

	public int getsoThuTuGhe() {
		return soThuTuGhe;
	}

	public void setsoThuTuGhe(int soThuTuGhe) {
		// Ràng buộc: phải lớn hơn 0
		if (soThuTuGhe > 0) {
			this.soThuTuGhe = soThuTuGhe;
		} else {
			throw new IllegalArgumentException("Số thứ tự toa không hợp lệ!");
		}
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		if (trangThai.equals("DA_THANH_TOAN") || trangThai.equals("TRONG") || trangThai.equals("DANG_BAO_TRI")
				|| trangThai.equals("DANG_GIU_CHO")) {
			this.trangThai = trangThai;
		} else {
			throw new IllegalArgumentException("Trạng thái không hợp lệ!");
		}
	}

	public ToaTau getToaTau() {
		return toaTau.getLoaiToa() == null ? toaTau = ToaTau_DAO.getInstance().getByMaToaTau(toaTau.getMaToaTau())
				: toaTau;
	}

	public void setToaTau(ToaTau toaTau) {
		this.toaTau = toaTau;
	}

	@Override
	public String toString() {
		return "GheTau {maGheTau: " + maGheTau + ", tenLoaiGheTau: " + tenLoaiGheTau + ", soThuTuGhe: " + soThuTuGhe
				+ ", trangThai: " + trangThai + ", toaTau: " + toaTau + "}";
	}

}
