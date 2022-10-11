package com.patiun.thrillingtreks.user;

import com.patiun.thrillingtreks.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private UserRegistrationDto firstValidUserDto;
    private UserRegistrationDto notMatchingPasswordsUserDto;

    @BeforeEach
    public void setUp() {
        firstValidUserDto = new UserRegistrationDto("username", "user@mail.com", "pass", "pass");
        notMatchingPasswordsUserDto = new UserRegistrationDto("username", "user@mail.com", "pass", "word");
    }

    @Test
    public void testSignUpShouldSaveUserToDatabaseWhenUserDtoIsValid() throws ValidationException {
        //given
        String newUserName = firstValidUserDto.getName();
        given(userRepository.findByName(newUserName)).willReturn(null);

        String newUserEmail = firstValidUserDto.getEmail();

        String newUserPassword = firstValidUserDto.getPassword();
        String encodedPassword = newUserPassword + "1234";
        given(passwordEncoder.encode(newUserPassword)).willReturn(encodedPassword);

        User expectedUserToBeSaved = new User(newUserName, newUserEmail, encodedPassword);
        //when
        userService.signUp(firstValidUserDto);
        //then
        verify(userRepository, times(1)).save(expectedUserToBeSaved);
    }

    @Test
    public void testSignUpShouldThrowValidationExceptionWhenPasswordsDoNotMatch() throws ValidationException {
        //given
        String newUserName = notMatchingPasswordsUserDto.getName();
        given(userRepository.findByName(newUserName)).willReturn(null);
        //when
        assertThrows(ValidationException.class, () -> userService.signUp(notMatchingPasswordsUserDto));
        //then
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testSignUpShouldThrowValidationExceptionWhenUsernameAlreadyExists() throws ValidationException {
        //given
        String newUserName = firstValidUserDto.getName();
        given(userRepository.findByName(newUserName)).willReturn(new User());
        //when
        assertThrows(ValidationException.class, () -> userService.signUp(firstValidUserDto));
        //then
        verify(userRepository, never()).save(any(User.class));
    }
}
