package util;

import model.TaiKhoan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanUtil {
    private static final String FILE_NAME = "users.txt";

    public static void luuTaiKhoan(TaiKhoan tk) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(tk.toFileString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<TaiKhoan> docTatCaTaiKhoan() {
        List<TaiKhoan> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(TaiKhoan.fromFileString(line));
            }
        } catch (IOException e) {
            // Nếu file chưa có thì coi như danh sách rỗng
        }
        return list;
    }

public static boolean kiemTraDangNhap(String username, String password) {
    List<TaiKhoan> danhSach = docTatCaTaiKhoan();
    for (TaiKhoan tk : danhSach) {
        if (tk.getUsername().equals(username) && tk.getPassword().equals(password)) {
            return true;
        }
    }
    return false;
}
}