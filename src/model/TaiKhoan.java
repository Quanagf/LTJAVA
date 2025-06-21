package model;

public class TaiKhoan {
    private String loai; // member, staff, admin
    private String username;
    private String password;

    public TaiKhoan(String loai, String username, String password) {
        this.loai = loai;
        this.username = username;
        this.password = password;
    }

    public String getLoai() {
        return loai;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Dòng ghi vào file
    public String toFileString() {
        return loai + "," + username + "," + password;
    }

    public static TaiKhoan fromFileString(String line) {
        String[] parts = line.split(",");
        return new TaiKhoan(parts[0], parts[1], parts[2]);
    }
}
