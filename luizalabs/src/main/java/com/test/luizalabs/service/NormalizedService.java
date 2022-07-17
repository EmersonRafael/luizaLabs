package com.test.luizalabs.service;

import com.test.luizalabs.dto.UserDTO;
import com.test.luizalabs.exception.NormalizedException;
import java.util.List;

public interface NormalizedService {

    List<UserDTO> normalizedAllLine(List<String> lines) throws NormalizedException;

}
