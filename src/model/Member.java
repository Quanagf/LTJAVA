package model;

public class Member extends NguoiDung {
    private String tinhTrangSucKhoe;

    public Member(String maSo, String hoTen, String nhomMau, String cccd, String soDienThoai, String tinhTrangSucKhoe) {
        super(maSo, hoTen, nhomMau, cccd, soDienThoai);
        this.tinhTrangSucKhoe = tinhTrangSucKhoe;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("== MEMBER ==");
        System.out.println("Ma so: " + maSo);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Nhom mau: " + nhomMau);
        System.out.println("CCCD: " + cccd);
        System.out.println("So dien thoai: " + soDienThoai);
        System.out.println("Suc khoe: " + tinhTrangSucKhoe);
    }
}
