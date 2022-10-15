package com.patiun.thrillingtreks.user;

import com.patiun.thrillingtreks.exception.ServiceException;
import com.patiun.thrillingtreks.exception.ValidationException;
import com.patiun.thrillingtreks.user.validation.UserRegistrationDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRegistrationDtoValidator userRegistrationDtoValidator;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRegistrationDtoValidator userRegistrationDtoValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRegistrationDtoValidator = userRegistrationDtoValidator;
    }

    public void signUp(UserRegistrationDto userRegistrationDto) throws ServiceException {
        String userDtoName = userRegistrationDto.getName();
        if (userRepository.findByName(userDtoName) != null) {
            throw new ServiceException("A user with such name already exists");
        }

        String userDtoEmail = userRegistrationDto.getEmail();

        try {
            userRegistrationDtoValidator.validate(userRegistrationDto);
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }
        String userDtoPassword = userRegistrationDto.getPassword();
        String encodedPassword = passwordEncoder.encode(userDtoPassword);

        userRepository.save(new User(userDtoName, userDtoEmail, encodedPassword));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
