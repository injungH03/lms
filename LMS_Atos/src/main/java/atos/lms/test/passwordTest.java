package atos.lms.test;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;

public class passwordTest {
	
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // 콘솔에서 아이디와 비밀번호 입력 받기
            System.out.print("아이디 입력: ");
            String id = scanner.nextLine();
            
            System.out.print("비밀번호 입력: ");
            String password = scanner.nextLine();

            // 암호화된 비밀번호 출력
            String encryptedPassword = encryptPassword(password, id);
            System.out.println("암호화된 비밀번호: " + encryptedPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptPassword(String password, String id) throws Exception {
        if (password == null || id == null) return "";

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.reset();
        md.update(id.getBytes());  // 아이디로 해시 업데이트
        byte[] hashValue = md.digest(password.getBytes());  // 비밀번호 해싱

        return Base64.getEncoder().encodeToString(hashValue);  // Base64 인코딩 후 반환
    }

}
