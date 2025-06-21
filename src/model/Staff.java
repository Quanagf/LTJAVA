package model;

public class Staff extends NguoiDung {
    private String maNhanVien;

    public Staff(String maSo, String hoTen, String nhomMau, String cccd, String soDienThoai, String maNhanVien) {
        super(maSo, hoTen, nhomMau, cccd, soDienThoai);
        this.maNhanVien = maNhanVien;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("== STAFF ==");
        System.out.println("Ma so: " + maSo);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Nhom mau: " + nhomMau);
        System.out.println("CCCD: " + cccd);
        System.out.println("So dien thoai: " + soDienThoai);
        System.out.println("Ma nhan vien: " + maNhanVien);
    }
}
