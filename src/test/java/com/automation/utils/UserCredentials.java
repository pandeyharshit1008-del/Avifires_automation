package com.automation.utils;

/**
 * Simple value object for signup test data.
 */
public class UserCredentials {
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String mobile;
    private final String password;

    public UserCredentials(String email, String firstName, String lastName, String mobile, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

}
