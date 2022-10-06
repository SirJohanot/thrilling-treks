package com.patiun.thrillingtreks.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRegistrationDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String password;
    private String confirmedPassword;

    protected UserRegistrationDto() {
    }

    public UserRegistrationDto(String name, String password, String confirmedPassword) {
        this.name = name;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRegistrationDto userRegistrationDto = (UserRegistrationDto) o;

        if (name != null ? !name.equals(userRegistrationDto.name) : userRegistrationDto.name != null) {
            return false;
        }
        if (password != null ? !password.equals(userRegistrationDto.password) : userRegistrationDto.password != null) {
            return false;
        }
        return confirmedPassword != null ? confirmedPassword.equals(userRegistrationDto.confirmedPassword) : userRegistrationDto.confirmedPassword == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (confirmedPassword != null ? confirmedPassword.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", confirmedPassword='" + confirmedPassword + '\'' +
                '}';
    }
}
