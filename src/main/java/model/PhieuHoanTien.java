package model;

import java.time.LocalDateTime;

public class PhieuHoanTien {
    // Khai báo thuộc tính
    private String maPhieuHoanTien;
    private LocalDateTime ngayHoanTien;
    private String lyDoHoanTra;
    private String ghiChu;
    private float tiLeHoanTien;

    // Constructor không tham số
    public PhieuHoanTien() {
    }

    // Constructor với tham số maPhieuHoanTien
    public PhieuHoanTien(String maPhieuHoanTien) {
        this.maPhieuHoanTien = maPhieuHoanTien;
    }

    // Constructor với đầy đủ tham số
    public PhieuHoanTien(String maPhieuHoanTien, LocalDateTime ngayHoanTien, float tiLeHoanTien, String lyDoHoanTra, String ghiChu) {
        this.maPhieuHoanTien = maPhieuHoanTien;
        setNgayHoanTien(ngayHoanTien);
        setTiLeHoanTien(tiLeHoanTien);
        setLyDoHoanTra(lyDoHoanTra);
        this.ghiChu = ghiChu;
    }

    // Getter và Setter
    public String getMaPhieuHoanTien() {
        return maPhieuHoanTien;
    }

    public void setMaPhieuHoanTien(String maPhieuHoanTien) {
        this.maPhieuHoanTien = maPhieuHoanTien;
    }

    public LocalDateTime getNgayHoanTien() {
        return ngayHoanTien;
    }

    public void setNgayHoanTien(LocalDateTime ngayHoanTien) {
        // Ràng buộc: phải là ngày hiện tại hoặc ngày trong quá khứ, không được ở tương lai
        if (ngayHoanTien.isBefore(LocalDateTime.now()) || ngayHoanTien.isEqual(LocalDateTime.now())) {
            this.ngayHoanTien = ngayHoanTien;
        } else {
            throw new IllegalArgumentException("Ngày hoàn tiền không hợp lệ!");
        }
    }

    public String getLyDoHoanTra() {
        return lyDoHoanTra;
    }

    public void setLyDoHoanTra(String lyDoHoanTra) {
        if (lyDoHoanTra != null && !lyDoHoanTra.trim().isEmpty() && lyDoHoanTra.length() <= 255) {
            this.lyDoHoanTra = lyDoHoanTra;
        } else {
            throw new IllegalArgumentException("Lý do hoàn trả không hợp lệ!");
        }
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        if (ghiChu == null || ghiChu.length() <= 255) {
            this.ghiChu = ghiChu;
        } else {
            throw new IllegalArgumentException("Ghi chú quá dài!");
        }
    }

    public float getTiLeHoanTien() {
        return tiLeHoanTien;
    }

    public void setTiLeHoanTien(float tiLeHoanTien) {
        // Ràng buộc: tỉ lệ hoàn tiền phải >= 0 và <= 100
        if (tiLeHoanTien >= 0 && tiLeHoanTien <= 100) {
            this.tiLeHoanTien = tiLeHoanTien;
        } else {
            throw new IllegalArgumentException("Tỉ lệ hoàn tiền không hợp lệ!");
        }
    }

    // Phương thức tính số tiền hoàn trả
    public double tinhSoTienHoanTra(double tongTienThanhToan) {
        return tongTienThanhToan * (tiLeHoanTien / 100);
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "PhieuHoanTien{" +
                "maPhieuHoanTien='" + maPhieuHoanTien + '\'' +
                ", ngayHoanTien=" + ngayHoanTien +
                ", lyDoHoanTra='" + lyDoHoanTra + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                ", tiLeHoanTien=" + tiLeHoanTien +
                '}';
    }
}

