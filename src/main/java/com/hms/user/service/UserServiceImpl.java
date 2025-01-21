package com.hms.user.service;

import com.hms.user.dto.UserDTO;
import com.hms.user.entity.User;
import com.hms.user.exception.HmsException;
import com.hms.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDTO userDTO) throws HmsException {
        Optional<User> opt = userRepository.findByEmail(userDTO.getEmail());
        if(opt.isPresent()){
            throw new HmsException("USER_ALREADY_EXIST");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userDTO.toEntity());
    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) throws HmsException {
        User user= userRepository.findByEmail(userDTO.getEmail()).orElseThrow(()->new HmsException("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(user.getPassword(), user.getPassword())){
            throw new HmsException("INVALID_CREDENTIALS");
        }
        //bcz password would not go to the frontend
        user.setPassword(null);
        return user.toDTO();
    }

    @Override
    public UserDTO getUserById(Long id) throws HmsException {
        return userRepository.findById(id).orElseThrow(()->new HmsException("User Not Found")).toDTO();
    }

    @Override
    public void updateUser(UserDTO userDTO)  {
    }
}
