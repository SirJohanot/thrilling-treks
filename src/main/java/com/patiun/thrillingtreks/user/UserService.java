package com.patiun.thrillingtreks.user;

import com.patiun.thrillingtreks.exception.ValidationException;
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

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signUp(UserRegistrationDto userRegistrationDto) throws ValidationException {
        String userDtoName = userRegistrationDto.getName();
        if (userRepository.findByName(userDtoName) != null) {
            throw new ValidationException("A user with such name already exists");
        }

        String userDtoEmail = userRegistrationDto.getEmail();

        String userDtoPassword = userRegistrationDto.getPassword();
        String userDtoConfirmedPassword = userRegistrationDto.getConfirmedPassword();
        if (!userDtoPassword.equals(userDtoConfirmedPassword)) {
            throw new ValidationException("The passwords don't match");
        }
        String encodedPassword = passwordEncoder.encode(userDtoPassword);

        userRepository.save(new User(userDtoName, userDtoEmail, encodedPassword));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
