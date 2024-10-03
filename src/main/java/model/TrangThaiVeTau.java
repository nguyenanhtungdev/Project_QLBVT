package model;

public enum TrangThaiVeTau {
    CON_CHO(0),
    DA_BAN(1),
    HET_HAN(2),
    DA_HUY(3),
    DA_CHINH_SUA(4);
	
    private final int ttvt;
    
    private TrangThaiVeTau(int ttvt) {
        this.ttvt = ttvt;
    }
    
    public int getValue() {
        return ttvt;
    }
}

