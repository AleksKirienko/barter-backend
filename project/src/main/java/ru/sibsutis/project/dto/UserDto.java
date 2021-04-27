package ru.sibsutis.project.dto;

public class UserDto {
    private final String name;
    private final String number;
    private final String email;
    private final String password;
    private final boolean isInteractionPost;
    private final String address;

    public UserDto(String name, String number, String email, String password, boolean isInteractionPost, String address) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.password = password;
        this.isInteractionPost = isInteractionPost;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public boolean isInteractionPost() {
        return isInteractionPost;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }
}
