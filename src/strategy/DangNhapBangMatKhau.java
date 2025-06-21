package strategy;

public class DangNhapBangMatKhau implements DangNhapStrategy {
    @Override
    public boolean dangNhap(String tenDangNhap, String matKhau) {
        System.out.println("Đăng nhập bằng mật khẩu cho tài khoản: " + tenDangNhap);
        return "user".equals(tenDangNhap) && "123".equals(matKhau);
    }
}


