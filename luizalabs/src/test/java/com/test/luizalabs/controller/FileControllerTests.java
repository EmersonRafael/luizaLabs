package com.test.luizalabs.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.io.InputStream;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FileControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void file_upload_success() throws Exception {

        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data_teste.txt");
        final MockMultipartFile file = new MockMultipartFile("file", "data_teste.txt", "text/plain", inputStream);

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/luizalabs/upload").file(file))
                .andExpect(status().isOk());


    }

    @Test
     void file_upload_error() throws Exception {

        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data_teste.txt");
        final MockMultipartFile file = new MockMultipartFile("file", "data_teste.txt", "text/plain", (byte[]) null);

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/luizalabs/upload").file(file))
                .andExpect(status().isBadRequest());


    }

}
