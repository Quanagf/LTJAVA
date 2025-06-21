package model;

public abstract class NguoiDung {
    protected String maSo;
    protected String hoTen;
    protected String nhomMau;
    protected String cccd;
    protected String soDienThoai;

    public NguoiDung(String maSo, String hoTen, String nhomMau, String cccd, String soDienThoai) {
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.nhomMau = nhomMau;
        this.cccd = cccd;
        this.soDienThoai = soDienThoai;
    }

    public abstract void hienThiThongTin();

    public String getCccd() {
        return cccd;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }
}
