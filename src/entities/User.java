package entities;

public class User {
    private String email;
    private String password;
    private String name;
    private double balance;
    private double special_check;

    public User(String email, String name, String password, double balance, double special_check) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.balance = balance;
        this.special_check = special_check;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String newName) {
        this.password = newName;
    }

    public String getName() {
        return this.name;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getSpecialCheck() {
        return this.special_check;
    }

    public void setSpecialCheck(double newSpecialCheck) {
        this.special_check = newSpecialCheck;
    }
}
