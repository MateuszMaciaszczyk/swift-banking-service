package org.example.swiftbankingservice;

import org.example.swiftbankingservice.models.SwiftCodeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest

class SwiftBankingServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private SwiftCodeDTO testSwiftCode;

    @BeforeEach
    void setup() {
        testSwiftCode = new SwiftCodeDTO(
                "1 Wall St, NY",
                "Bank of America",
                "US",
                "United States",
                true,
                "REMITLYXXXX",
                null
        );
    }

    @Test
    void testAddSwiftCode() throws Exception {
        mockMvc.perform(post("/v1/swift-codes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSwiftCode)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("SWIFT code added successfully!"));
    }

    @Test
    void testGetSwiftCode() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/REMITLYXXXX"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.swiftCode").value("REMITLYXXXX"))
                .andExpect(jsonPath("$.isHeadquarter").value(true));
    }

    @Test
    void testGetSwiftCodesByCountry() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/country/US"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryISO2").value("US"))
                .andExpect(jsonPath("$.swiftCodes[0].swiftCode").value("REMITLYXXXX"));
    }

    @Test
    void testDeleteSwiftCode() throws Exception {
        mockMvc.perform(delete("/v1/swift-codes/REMITLYXXXX"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("SWIFT code deleted successfully!"));

        mockMvc.perform(get("/v1/swift-codes/REMITLYXXXX"))
                .andExpect(status().isNotFound());
    }
}
