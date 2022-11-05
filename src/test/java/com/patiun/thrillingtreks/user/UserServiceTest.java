package com.patiun.thrillingtreks.user;

import com.patiun.thrillingtreks.exception.ServiceException;
import com.patiun.thrillingtreks.exception.ValidationException;
import com.patiun.thrillingtreks.user.validation.UserRegistrationDtoValidator;
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

    @Mock
    private UserRegistrationDtoValidator userRegistrationDtoValidator;

    @InjectMocks
    private UserService userService;

    private UserRegistrationDto firstValidUserDto;
    private UserRegistrationDto notMatchingPasswordsUserDto;

    @BeforeEach
    public void setUp() {
        reset(userRepository, passwordEncoder, userRegistrationDtoValidator);
        firstValidUserDto = new UserRegistrationDto("username", "user@mail.com", "pass", "pass");
        notMatchingPasswordsUserDto = new UserRegistrationDto("username", "user@mail.com", "pass", "word");
    }

    @Test
    public void testSignUpShouldSaveUserToDatabaseWhenUserDtoIsValid() throws ServiceException, ValidationException {
        //given
        String newUserName = firstValidUserDto.getName();
        given(userRepository.findByName(newUserName)).willReturn(null);

        doNothing().when(userRegistrationDtoValidator).validate(firstValidUserDto);

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
    public void testSignUpShouldThrowServiceExceptionWhenPasswordsDoNotMatch() throws ValidationException {
        //given
        String newUserName = notMatchingPasswordsUserDto.getName();
        doThrow(ValidationException.class).when(userRegistrationDtoValidator).validate(notMatchingPasswordsUserDto);
        given(userRepository.findByName(newUserName)).willReturn(null);
        //when
        assertThrows(ServiceException.class, () -> userService.signUp(notMatchingPasswordsUserDto));
        //then
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testSignUpShouldThrowServiceExceptionWhenUsernameAlreadyExists() throws ValidationException {
        //given
        String newUserName = firstValidUserDto.getName();
        given(userRepository.findByName(newUserName)).willReturn(new User());
        //when
        assertThrows(ServiceException.class, () -> userService.signUp(firstValidUserDto));
        //then
        verify(userRepository, never()).save(any(User.class));
    }
}
