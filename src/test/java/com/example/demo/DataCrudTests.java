package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; 

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DataCrudTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSuccessfulCreate() throws Exception {
        String createJson = """
                {
                    "companyName": "Example12",
                    "location": "Krakow",
                    "technology": "Java",
                    "seniority": "Mid",
                    "avgSalary": 5000.0
                }
                """;

        mockMvc.perform(post("/api/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson))
                .andExpect(status().is(201));
    }

    @Test
    public void testInvalidCreate() throws Exception {
        String createJson = """
                {
                    "location": "Krakow",
                    "technology": "Java",
                    "seniority": "Mid",
                    "avgSalary": "test"
                }
                """;

        mockMvc.perform(post("/api/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson))
                .andExpect(status().is(400));
    }

    @Test
    public void testSuccessfulFind() throws Exception {
        mockMvc.perform(get("/api/data/675ef41d4e776745c533f9a1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void testInvalidFind() throws Exception {
        mockMvc.perform(get("/api/data/675d85d30d738a581ca0e0c4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(500));
    }

    @Test
    public void testSuccessfulUpdate() throws Exception {
        String updateJson = """
                {
                    "companyName": "Updated Company",
                    "location": "Warsaw",
                    "technology": "Python",
                    "seniority": "Senior",
                    "avgSalary": 7000.0
                }
                """;

        mockMvc.perform(put("/api/data/675ef41d4e776745c533f9a1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(status().is(200));
    }

    @Test
    public void testInvalidUpdate() throws Exception {
        String updateJson = """
                {
                    "companyName": "Updated Company",
                    "location": "Warsaw",
                    "technology": "Python",
                    "seniority": "Senior",
                    "avgSalary": 7000.0
                }
                """;

        mockMvc.perform(put("/api/data/675ef41d4e776745c533f9a")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(status().is(404));
    }

    @Test
    public void testSuccessfulDelete() throws Exception {
        mockMvc.perform(delete("/api/data/675ef41d4e776745c533f9a1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(204));
    }

    @Test
    public void testInvalidDelete() throws Exception {
        mockMvc.perform(post("/api/data/675ef5f14e776745c533f")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(405));
    }
}
