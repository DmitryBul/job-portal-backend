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
public class UserRegistrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSuccessfulRegistration() throws Exception {
        String userJson = """
                {
                    "username": "TestUser234",
                    "email": "testtest12326@example.com",
                    "password": "password123"
                }""";

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDuplicateRegistration() throws Exception {
        String userJson = """
                {
                    "username": "TestUser121",
                    "email": "duplicate3212@example.com",
                    "password": "password123"
                }
                """;

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk());

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isInternalServerError());
    }
}
