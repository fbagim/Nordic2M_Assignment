package com.test.nordic2m.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.test.nordic2m.dto.CipherData;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import com.test.nordic2m.service.CipherService;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.test.nordic2m.controller.CipherController;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CipherController.class)
@ContextConfiguration
@Import(SecurityConfig.class)
public class CipherControllerTest {
    private final Logger logger = LoggerFactory.getLogger(CipherControllerTest.class);
    @MockBean
    CipherService cipherService;
    @Autowired
    MockMvc mockMvc;
    CipherController cipherController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        cipherController = new CipherController();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "root")
    @DisplayName("Test Encrypt Data For Valid JSON Payload")
    public void testEncryptForValidJSONPayload() throws Exception {

        StringBuffer stringBuffer = new StringBuffer("CCXXXGGGcufuuhufhuhuh");
        Mockito.when(cipherService.encrypt(any(String.class), any(Integer.class))).thenReturn(stringBuffer);
        mockMvc.perform(MockMvcRequestBuilders.post("/encrypt")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(" {\n" +
                                "        \"requestData\": \"AAVVVEEEasdssfsdfsfsf\",\n" +
                                "        \"responseData\": \"\",\n" +
                                "        \"noOfRotations\": 2\n" +
                                "    }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.requestData").value("AAVVVEEEasdssfsdfsfsf"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseData").value("CCXXXGGGcufuuhufhuhuh"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.noOfRotations").value(2));

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "root")
    @DisplayName("Test Decrypt Data For Valid JSON Payload")
    public void testDecryptForValidJSONPayload() throws Exception {

        StringBuffer stringBuffer = new StringBuffer("CCXXXGGGcufuuhufhuhuh");
        Mockito.when(cipherService.encrypt(any(String.class), any(Integer.class))).thenReturn(stringBuffer);
        mockMvc.perform(MockMvcRequestBuilders.post("/decrypt")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(" {\n" +
                                "        \"requestData\": \"AAVVVEEEasdssfsdfsfsf\",\n" +
                                "        \"responseData\": \"\",\n" +
                                "        \"noOfRotations\": 2\n" +
                                "    }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.requestData").value("AAVVVEEEasdssfsdfsfsf"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseData").value("CCXXXGGGcufuuhufhuhuh"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.noOfRotations").value(2));

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "root")
    @DisplayName("Test Decrypt Data For InValid Rotations")
    public void testDecryptForInValidRotations() throws Exception {

        StringBuffer stringBuffer = new StringBuffer("CCXXXGGGcufuuhufhuhuh");
        Mockito.when(cipherService.encrypt(any(String.class), any(Integer.class))).thenReturn(stringBuffer);
        mockMvc.perform(MockMvcRequestBuilders.post("/decrypt")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(" {\n" +
                                "        \"requestData\": \"AAVVVEEEasdssfsdfsfsf\",\n" +
                                "        \"responseData\": \"\",\n" +
                                "        \"noOfRotations\": 30\n" +
                                "    }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "root")
    @DisplayName("Test Encrypt Data For InValid Rotations")
    public void testEncryptForInValidRotations() throws Exception {

        StringBuffer stringBuffer = new StringBuffer("CCXXXGGGcufuuhufhuhuh");
        Mockito.when(cipherService.encrypt(any(String.class), any(Integer.class))).thenReturn(stringBuffer);
        mockMvc.perform(MockMvcRequestBuilders.post("/encrypt")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(" {\n" +
                                "        \"requestData\": \"AAVVVEEEasdssfsdfsfsf\",\n" +
                                "        \"responseData\": \"\",\n" +
                                "        \"noOfRotations\": 30\n" +
                                "    }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "root")
    @DisplayName("Test Get All History Data")
    public void testGetAllHistory() throws Exception {
        List historyData = new ArrayList();
        CipherData cipherData = new CipherData();
        historyData.add(cipherData);
        Mockito.when(cipherService.getHistory()).thenReturn(historyData);

        mockMvc.perform(MockMvcRequestBuilders.get("/historyData")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "root")
    @DisplayName("Test POST Data For Invalid URL")
    public void testPOSTDataForInvalidURL() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wrrwerwerw/")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(" {\n" +
                                "        \"requestData\": \"YYYZZZZ\",\n" +
                                "        \"responseData\": \"ZZZAAAA\",\n" +
                                "        \"noOfRotations\": 1\n" +
                                "    }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test POST Data For Encrypt With InValid Auth")
    public void testPOSTDataForEncryptWithInValidAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/encrypt")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content("{rrewrwrr}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "root")
    @DisplayName("Test POST data for Decrypt with InValid JSON Payload")
    public void testPostDataForDecryptWithInValidJSONPayload() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/decrypt")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content("{rrewrwrr}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "root")
    @DisplayName("Test Post Data For Encrypt With Null Payload")
    public void testPostDataForEncryptWithNullPayload() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/encrypt")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(" {\n" +
                                "        \"requestData\": null,\n" +
                                "        \"responseData\": null,\n" +
                                "        \"noOfRotations\": 1\n" +
                                "    }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "root")
    @DisplayName("Test Post Data For Decrypt With Null Payload")
    public void testPostDataForDecryptWithNullPayload() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/decrypt")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(" {\n" +
                                "        \"requestData\": null,\n" +
                                "        \"responseData\": null,\n" +
                                "        \"noOfRotations\": 1\n" +
                                "    }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

}
