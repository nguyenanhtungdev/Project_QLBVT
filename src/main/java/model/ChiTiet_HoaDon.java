package model;

import java.util.ArrayList;

public class ChiTiet_HoaDon {
    // Khai báo thuộc tính
    private int soLuong;
    private ArrayList<ChiTiet_HoaDon> dsChiTietHD;

    // Constructor không tham số
    public ChiTiet_HoaDon() {
        dsChiTietHD = new ArrayList<>();
    }

    // Constructor với tham số soLuong
    public ChiTiet_HoaDon(int soLuong) {
        setSoLuong(soLuong);
        dsChiTietHD = new ArrayList<>();
    }

    // Getter và Setter
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        // Ràng buộc: phải là số nguyên dương lớn hơn 0
        if (soLuong > 0) {
            this.soLuong = soLuong;
        } else {
            throw new IllegalArgumentException("Số lượng phải là số nguyên dương lớn hơn 0.");
        }
    }

    public ArrayList<ChiTiet_HoaDon> getDsChiTietHD() {
        return dsChiTietHD;
    }

    // Phương thức tính thành tiền
    public float tinhThanhTien(float giaVeHienTai, float tiLeGiamGia) {
        return giaVeHienTai - giaVeHienTai * (tiLeGiamGia / 100);
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "ChiTiet_HoaDon{" +
                "soLuong=" + soLuong +
                ", dsChiTietHD=" + dsChiTietHD +
                '}';
    }
}

