package com.patiun.thrillingtreks.user.validation;

import com.patiun.thrillingtreks.exception.ValidationException;
import com.patiun.thrillingtreks.user.UserRegistrationDto;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationDtoValidator {

    public UserRegistrationDtoValidator() {
    }

    public void validate(UserRegistrationDto userRegistrationDto) throws ValidationException {
        String userDtoPassword = userRegistrationDto.getPassword();
        String userDtoConfirmedPassword = userRegistrationDto.getConfirmedPassword();
        if (!userDtoPassword.equals(userDtoConfirmedPassword)) {
            throw new ValidationException("The passwords don't match");
        }
    }
}
