package org.example.socialbloggingsite.user.dtos;

public class UpdateUserDto {
    private String email;
    private String fullName;
    private String password;

    public String getEmail() {
        return email;
    }

    public UpdateUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UpdateUserDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UpdateUserDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
