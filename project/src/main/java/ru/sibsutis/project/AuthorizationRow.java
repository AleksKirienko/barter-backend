package ru.sibsutis.project;

public class AuthorizationRow {

    private final String email;
    private final String password;

    public AuthorizationRow(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
