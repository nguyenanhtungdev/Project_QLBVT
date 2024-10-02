package model;

public enum TinhTrangKhuyenMai {
	CON(0), HET_SO_LUONG(1), HET_HAN_SU_DUNG(2);

	private int value;

	public int getValue() {
		return value;
	}

	private TinhTrangKhuyenMai(int value) {
		this.value = value;
	}

	public static TinhTrangKhuyenMai fromValue(int value) {
		for (TinhTrangKhuyenMai status : TinhTrangKhuyenMai.values()) {
			if (status.getValue() == value) {
				return status;
			}
		}

		throw new IllegalArgumentException("Sai giá trị enum TinhTrangKhuyenMai: " + value);
	}

}
