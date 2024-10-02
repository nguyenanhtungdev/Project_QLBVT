package controller;

import java.util.ArrayList;

import model.KhachHang;
import model.KhachHang_DAO;


public class Menu_Controller {
	private KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
	
	public ArrayList<KhachHang> getDataKhachHang(){
		return khachHang_DAO.getalltbKH();
	}
	public double getGiamGia(String maKH) {
		for (KhachHang kh : khachHang_DAO.getalltbKH()) {
			if(kh.getMaKhachHang().equals(maKH)) {
				return kh.getLoaiKH().getDiscount();
			}
		}
		return 0;
	}
}
