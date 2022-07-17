package com.test.luizalabs.service;

import com.test.luizalabs.dto.UserDTO;

import java.util.List;

public interface Normalized {

    List<UserDTO> normalizedAllLine(List<String> lines) throws RuntimeException;

}
