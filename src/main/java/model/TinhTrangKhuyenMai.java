package model;

public enum TinhTrangKhuyenMai {
	CON(0), HET_SO_LUONG(1), HET_HAN_SU_DUNG(2);

	private int tinhTrang;

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	private TinhTrangKhuyenMai(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

}
