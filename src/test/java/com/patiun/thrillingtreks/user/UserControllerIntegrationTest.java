package com.patiun.thrillingtreks.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:applicationIntegrationTest.properties")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testGetSignUpPageShouldReturnSignUpViewWithUserAttribute() throws Exception {
        mockMvc.perform(get("/sign-up-page"))
                .andExpect(status().isOk())
                .andExpect(view().name("signUp"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testPostSignUpPageShouldSaveUserToTheDatabaseAndReturnRedirectToHomePageWhenUserInfoIsValid() throws Exception {
        //given
        String name = "user";
        String email = "user@mail.com";
        String password = "pass";

        UserRegistrationDto registrationDto = new UserRegistrationDto(name, email, password, password);
        User expectedUserToBeSaved = new User(name, email, password);
        //when
        mockMvc.perform(post("/sign-up")
                        .flashAttr("user", registrationDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        //then
        assertThat(userRepository.findByName(name), is(notNullValue(User.class)));
    }
}
