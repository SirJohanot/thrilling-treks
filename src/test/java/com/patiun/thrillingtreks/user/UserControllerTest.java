package com.patiun.thrillingtreks.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:applicationIntegrationTest.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenGetSignUpPage_thenReturnViewWithUserModelAttribute() throws Exception {
        mockMvc.perform(get("/sign-up-page"))
                .andExpect(status().isOk())
                .andExpect(view().name("signUp"))
                .andExpect(model().attributeExists("user"));
    }
}
