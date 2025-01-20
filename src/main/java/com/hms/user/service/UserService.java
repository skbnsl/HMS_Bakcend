package com.hms.user.service;

import com.hms.user.dto.UserDTO;
import com.hms.user.exception.HmsException;

public interface UserService {

    public void registerUser(UserDTO userDTO) throws HmsException;
    public UserDTO loginUser(UserDTO userDTO);
    public UserDTO getUserById(Long id);
    public void updateUser(UserDTO userDTO);

}