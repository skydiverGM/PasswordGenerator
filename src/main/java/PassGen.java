import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.Deque;

public class PassGen {
    private final char[] uppercase;
    private final char[] lowercase;
    private final char[] digits;
    private final char[] specialCharacters;
    private final char[] allCharacters;
    SecureRandom random;

    public PassGen() {
        this.uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        this.lowercase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        this.digits = "0123456789".toCharArray();
        this.specialCharacters = "!@#$%&*()_+-=[]?".toCharArray();
        this.allCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*()_+-=[]?".toCharArray();
        this.random = new SecureRandom();
    }

    public String generatePassword(int length) {
        if (length < 8) {
            length = 8;
            System.out.println("Password can't be less then 8 characters.");
        }
        Deque<Character> deque = new ArrayDeque<>();
        deque.add(uppercase[random.nextInt(uppercase.length)]);
        deque.add(lowercase[random.nextInt(lowercase.length)]);
        deque.add(digits[random.nextInt(digits.length)]);
        deque.add(specialCharacters[random.nextInt(specialCharacters.length)]);

        for (int i = 4; i < length; i++) {
            deque.add(allCharacters[random.nextInt(allCharacters.length)]);
        }
        StringBuilder pass = new StringBuilder();

        while (!deque.isEmpty()) {
            pass.append(deque.pollFirst());
            if (!deque.isEmpty()) {
                pass.append(deque.pollLast());
            }
        }

        return pass.toString();
    }
}
