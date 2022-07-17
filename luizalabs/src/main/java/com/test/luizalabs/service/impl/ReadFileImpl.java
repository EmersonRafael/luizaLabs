package com.test.luizalabs.service.impl;

import com.test.luizalabs.dto.UserDTO;
import com.test.luizalabs.service.Normalized;
import com.test.luizalabs.service.ReadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Log
public class ReadFileImpl implements ReadFile {

    private final Normalized normalized;

    public List<UserDTO> normalizedReturn(MultipartFile file){

       try{
           Path path = this.tempWriteFile(file.getBytes(), file.getOriginalFilename());
           List<String> lines = this.readAllLines(path);
           return normalized.normalizedAllLine(lines);
       }catch (IOException e){
           log.severe("Erro ao tentar ler arquivo "+ e.getMessage());
       }catch (RuntimeException e){
           log.severe("Erro ao tentar normalizar dados "+ e.getMessage());
       }

        return new ArrayList<>();
    }


    public Path tempWriteFile(byte[] file, String fileName) throws IOException {
        String tmpdir = Files.createTempDirectory("tmpDir-").toFile().getAbsolutePath();
        byte[] bytes = file;
        Path path = Paths.get(tmpdir + fileName);
        Files.write(path, bytes);
        return path;
    }

    private List<String> readAllLines(Path path) throws IOException {
        return Files.readAllLines(path);
    }

}
