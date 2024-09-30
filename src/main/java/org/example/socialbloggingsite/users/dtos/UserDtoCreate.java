package org.example.socialbloggingsite.users.dtos;

public class LoginUserDto {
    private String fullName;
    private String password;
    private String email;

    public String getFullName() {
        return fullName;
    }

    public LoginUserDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginUserDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
