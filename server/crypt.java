import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class crypt {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("root"));
    }
}
