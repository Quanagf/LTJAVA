package service;

import strategy.DangNhapStrategy;

public class DangNhapService {
    private DangNhapStrategy strategy;

    public void setStrategy(DangNhapStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean dangNhap(String taiKhoan, String matKhau) {
        return strategy.dangNhap(taiKhoan, matKhau);
    }
}

