import model.*;
import service.DangNhapService;
import strategy.*;
import util.TaiKhoanUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NguoiDung nguoiDung = null;

        System.out.println("===== HE THONG HIEN MAU =====");
        System.out.println("1. Dang ky tai khoan");
        System.out.println("2. Dang nhap");
        System.out.print("Nhap lua chon (1-2): ");
        int luaChon = scanner.nextInt();
        scanner.nextLine();

        if (luaChon == 1) {
            System.out.println("===== CHON LOAI NGUOI DUNG =====");
            System.out.println("1. Member");
            System.out.println("2. Staff");
            System.out.println("3. Admin");
            System.out.print("Nhap lua chon (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nhap username: ");
            String username = scanner.nextLine();
            System.out.print("Nhap password: ");
            String password = scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("===== Nhap thong tin MEMBER =====");
                    System.out.print("Nhap ma so: ");
                    String masoM = scanner.nextLine();
                    System.out.print("Nhap ho ten: ");
                    String hotenM = scanner.nextLine();
                    System.out.print("Nhap nhom mau: ");
                    String nhommauM = scanner.nextLine();
                    System.out.print("Nhap CCCD: ");
                    String cccdM = scanner.nextLine();
                    System.out.print("Nhap so dien thoai: ");
                    String sdtM = scanner.nextLine();
                    System.out.print("Nhap tinh trang suc khoe: ");
                    String sucKhoe = scanner.nextLine();

                    nguoiDung = new Member(masoM, hotenM, nhommauM, cccdM, sdtM, sucKhoe);
                    TaiKhoanUtil.luuTaiKhoan(new TaiKhoan("member", username, password));
                    break;

                case 2:
                    System.out.println("===== Nhap thong tin STAFF =====");
                    System.out.print("Nhap ma so: ");
                    String masoS = scanner.nextLine();
                    System.out.print("Nhap ho ten: ");
                    String hotenS = scanner.nextLine();
                    System.out.print("Nhap nhom mau: ");
                    String nhommauS = scanner.nextLine();
                    System.out.print("Nhap CCCD: ");
                    String cccdS = scanner.nextLine();
                    System.out.print("Nhap so dien thoai: ");
                    String sdtS = scanner.nextLine();
                    System.out.print("Nhap ma nhan vien: ");
                    String maNV = scanner.nextLine();

                    nguoiDung = new Staff(masoS, hotenS, nhommauS, cccdS, sdtS, maNV);
                    TaiKhoanUtil.luuTaiKhoan(new TaiKhoan("staff", username, password));
                    break;

                case 3:
                    System.out.println("===== Nhap thong tin ADMIN =====");
                    System.out.print("Nhap ma so: ");
                    String masoA = scanner.nextLine();
                    System.out.print("Nhap ho ten: ");
                    String hotenA = scanner.nextLine();
                    System.out.print("Nhap nhom mau: ");
                    String nhommauA = scanner.nextLine();
                    System.out.print("Nhap CCCD: ");
                    String cccdA = scanner.nextLine();
                    System.out.print("Nhap so dien thoai: ");
                    String sdtA = scanner.nextLine();
                    System.out.print("Nhap ngay tao tai khoan (yyyy-MM-dd): ");
                    LocalDate ngayTao = LocalDate.parse(scanner.nextLine());

                    nguoiDung = new Admin(masoA, hotenA, nhommauA, cccdA, sdtA, ngayTao);
                    TaiKhoanUtil.luuTaiKhoan(new TaiKhoan("admin", username, password));
                    break;

                default:
                    System.out.println("Lua chon khong hop le.");
                    scanner.close();
                    return;
            }

            System.out.println("\n===== THONG TIN DA DANG KY =====");
            nguoiDung.hienThiThongTin();
        }

        // ==== DANG NHAP ====
        System.out.println("\n===== DANG NHAP =====");
        System.out.print("Nhap username: ");
        String usernameLogin = scanner.nextLine();
        System.out.print("Nhap password: ");
        String passwordLogin = scanner.nextLine();

        boolean dangNhapThanhCong = TaiKhoanUtil.kiemTraDangNhap(usernameLogin, passwordLogin);
        if (dangNhapThanhCong) {
            System.out.println("Dang nhap thanh cong!");
        } else {
            System.out.println("Dang nhap that bai. Tai khoan hoac mat khau sai.");
        }

        scanner.close();
    }
}
