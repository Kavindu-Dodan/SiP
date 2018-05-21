package Models;

public class User {

    private final String username;
    private final String password;

    private String email;
    private int age;

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
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

    public boolean authenticate(final String password) {
        return this.password.equals(password);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
