package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import model.TaiKhoan;
import model.TaiKhoan_DAO;
import view.ChoDangNhap;
import view.DangNhap;

public class DangNhap_Controller implements ActionListener, KeyListener {
    private TaiKhoan_DAO taiKhoan_DAO;
    private DangNhap dangNhap;
    private ChoDangNhap choDangNhap;

    public DangNhap_Controller() {
        taiKhoan_DAO = new TaiKhoan_DAO();
        dangNhap = new DangNhap();
        choDangNhap = new ChoDangNhap();
        
        dangNhap.setVisible(true);

        // Đăng ký ActionListener cho nút đăng nhập
        dangNhap.getBtn_DangNhap().addActionListener(this);
        // Đăng ký KeyListener cho trường mật khẩu
        dangNhap.getTxt_passWord().addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        
        if (obj == dangNhap.getBtn_DangNhap()) {
            performLogin();
        }
    }

    // Hàm xử lý đăng nhập
    public boolean xuLyDangNhap() {
        for (TaiKhoan tk : taiKhoan_DAO.getalltbTK()) {
            String password = new String(dangNhap.getTxt_passWord().getPassword()).trim();
            if (dangNhap.getTxt_userName().getText().trim().equals(tk.getTenDangNhap()) && password.equals(tk.getMatKhau())) {
                return true;
            }
        }
        return false;
    }

    private void performLogin() {
        if (xuLyDangNhap()) {
            dangNhap.dispose();
            choDangNhap.setVisible(true); 
            choDangNhap.startProgress();
        } else {
            JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng!", "Đăng nhập", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Không cần xử lý
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Kiểm tra nếu phím nhấn là Enter
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            performLogin(); // Gọi hàm đăng nhập
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Không cần xử lý
    }
}
