package com.patiun.thrillingtreks.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(userController)
                .build();
    }

    @Test
    public void testGetSignUpPageShouldReturnSignUpViewWithUserAttribute() throws Exception {
        mockMvc.perform(get("/sign-up-page"))
                .andExpect(status().isOk())
                .andExpect(view().name("signUp"))
                .andExpect(model().attributeExists("user"));
    }
}
