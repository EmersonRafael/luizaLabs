package com.test.luizalabs.service;

import com.test.luizalabs.exception.NormalizedException;
import com.test.luizalabs.service.impl.NormalizedServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class NormalizedServiceTests {

    @InjectMocks
    NormalizedServiceImpl normalizedService;

    @Test
    void normalized_success(){
        List<String> lines = new ArrayList<>();
        lines.add("0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308");
        assertTrue(!normalizedService.normalizedAllLine(lines).isEmpty());
    }

    @Test
    void normalized_error(){
        List<String> lines = new ArrayList<>();
        lines.add("0000000070                              Palmer Prosacco00000007ghgf530000000003     1836.7420210308");

        try {
            normalizedService.normalizedAllLine(lines);
        }catch (NormalizedException e){
            assertTrue(true);
        }

    }

}
