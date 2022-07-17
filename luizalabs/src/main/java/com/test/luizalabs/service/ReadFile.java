package com.test.luizalabs.service;

import com.test.luizalabs.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface ReadFile {

    List<UserDTO> normalizedReturn(MultipartFile file);
    Path tempWriteFile(byte[] file, String fileName) throws IOException;

}
