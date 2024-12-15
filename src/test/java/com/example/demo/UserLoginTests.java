package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; 

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserLoginTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSuccessfulLogin() throws Exception {
        String loginJson = """
                {
                    "email": "testtest12326@example.com",
                    "password": "password123"
                }
                """;

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testInvalidLogin() throws Exception {
        String loginJson = """
                {
                    "email": "wrong@example.com",
                    "password": "wrongpassword"
                }
                """;

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andExpect(status().isUnauthorized());
    }
}
