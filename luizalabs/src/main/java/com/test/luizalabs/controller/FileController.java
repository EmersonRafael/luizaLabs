package com.test.luizalabs.controller;

import com.test.luizalabs.dto.UserDTO;
import com.test.luizalabs.service.ReadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@RestController
@RequestMapping("luizalabs")
@RequiredArgsConstructor
public class FileController {
    private final ReadFileService readFileService;

    @PostMapping("/upload")
    public ResponseEntity<List<UserDTO>> fileUpload(@RequestParam("file") MultipartFile file) {
        return  !file.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(readFileService.normalizedReturn(file)) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}
