public class PremiumUser extends User {

    public PremiumUser(String name, String email, String hashedPassword) {
        super(name, email, hashedPassword);
    }

    @Override
    public String getUserType() {
        return "PREMIUM";
    }
}