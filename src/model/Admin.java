package model;

import java.time.LocalDate;

public class Admin extends NguoiDung {
    private LocalDate ngayTao;

    public Admin(String maSo, String hoTen, String nhomMau, String cccd, String soDienThoai, LocalDate ngayTao) {
        super(maSo, hoTen, nhomMau, cccd, soDienThoai);
        this.ngayTao = ngayTao;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("== ADMIN ==");
        System.out.println("Ma so: " + maSo);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Nhom mau: " + nhomMau);
        System.out.println("CCCD: " + cccd);
        System.out.println("So dien thoai: " + soDienThoai);
        System.out.println("Ngay tao tai khoan: " + ngayTao);
    }
}
