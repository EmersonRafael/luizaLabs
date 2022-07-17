package com.test.luizalabs.service;


import com.test.luizalabs.dto.UserDTO;
import com.test.luizalabs.service.impl.ReadFileServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;


import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class ReadFileServiceTests {

    @InjectMocks
    ReadFileServiceImpl readFileService;

    @Mock
    NormalizedService normalizedService;

    @Test
    void read_file_success() throws IOException {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data_teste.txt");
        final MockMultipartFile file = new MockMultipartFile("file", "data_teste.txt", "text/plain", inputStream);
        List<UserDTO> user = readFileService.normalizedReturn(file);
        assertTrue(Objects.nonNull(user));
    }

    @Test
    void temp_write_file_success() throws IOException{
        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data_teste.txt");
        final MockMultipartFile file = new MockMultipartFile("file", "data_teste.txt", "text/plain", inputStream);
        Path path = readFileService.tempWriteFile(file.getBytes(), file.getOriginalFilename());
        assertTrue(Objects.nonNull(path));
    }


}
