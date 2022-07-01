import java.util.*;

public class PasswordGenerator {
    private String password = "";

    private static final int MAX = 15;
    private static final int MIN = 8;

    public static final String CHARS = "1234567890" /* nums */
                                     + "abcdefghijklmnopqrstuvwxyz" /* alpha */
                                     + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" /* caps */
                                     + "!@#$%^&*()-_=+`~[]{}\\|;:'\",.<>/?" /* special */
                                     ;

    public PasswordGenerator() {
        Random rand = new Random();
        int length = MIN + rand.nextInt(MAX - MIN) + 1;
        for (int i = 0; i < length; i++) {
            password += CHARS.charAt(rand.nextInt(CHARS.length()));
        }
    }

    public boolean testPass(String pass) {
        return password.equals(pass);
    }

    public static void main(String[] args) {
        PasswordGenerator pw = new PasswordGenerator();
        System.out.println(pw.password);
    }
}