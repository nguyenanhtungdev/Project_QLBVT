package model;

public class GheTau {
    // Khai báo thuộc tính
    private String maGheTau;
    private String tenLoaiGheTau;
    private int soThuTuToa;
    private String trangThai;

    // Constructor không tham số
    public GheTau() {
    }

    // Constructor với tham số maGheTau
    public GheTau(String maGheTau) {
        this.maGheTau = maGheTau;
    }

    // Constructor với đầy đủ tham số
    public GheTau(String maGheTau, String tenLoaiGheTau, int soThuTuToa, String trangThai) {
        this.maGheTau = maGheTau;
        this.tenLoaiGheTau = tenLoaiGheTau;
        this.soThuTuToa = soThuTuToa;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public String getMaGheTau() {
        return maGheTau;
    }

    public void setMaGheTau(String maGheTau) {
        this.maGheTau = maGheTau;
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

    public int getSoThuTuToa() {
        return soThuTuToa;
    }

    public void setSoThuTuToa(int soThuTuToa) {
        // Ràng buộc: phải lớn hơn 0
        if (soThuTuToa > 0) {
            this.soThuTuToa = soThuTuToa;
        } else {
            throw new IllegalArgumentException("Số thứ tự toa không hợp lệ!");
        }
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        if (trangThai.equals("Đang sử dụng") || trangThai.equals("Trống") || trangThai.equals("Bảo trì") || trangThai.equals("Không còn sử dụng")) {
            this.trangThai = trangThai;
        } else {
            throw new IllegalArgumentException("Trạng thái không hợp lệ!");
        }
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "GheTau{" +
                "maGheTau='" + maGheTau + '\'' +
                ", tenLoaiGheTau='" + tenLoaiGheTau + '\'' +
                ", soThuTuToa=" + soThuTuToa +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
