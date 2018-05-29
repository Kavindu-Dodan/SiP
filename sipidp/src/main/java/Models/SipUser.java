package Models;

public class SipUser {
    private final String username;
    private final String issuer;

    private String email;
    private int age;

    public SipUser(final String username, final String issuer) {
        this.username = username;
        this.issuer = issuer;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIssuer() {
        return issuer;
    }
}
