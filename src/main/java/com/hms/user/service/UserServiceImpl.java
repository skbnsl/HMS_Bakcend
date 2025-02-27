package com.hms.user.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.user.dto.LoginDTO;
import com.hms.user.dto.UserDTO;
import com.hms.user.entity.User;
import com.hms.user.exception.HmsException;
import com.hms.user.repository.UserRepository;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApiService apiService;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public void registerUser(UserDTO userDTO) throws HmsException {
        System.err.print(userDTO);
        logger.info("Registering user: {}", userDTO);
        String email = userDTO.getEmail().toLowerCase();
        userDTO.setEmail(email);
        Optional<User> opt = userRepository.findByEmail(userDTO.getEmail());
        if(opt.isPresent()){
            throw new HmsException("USER_ALREADY_EXIST");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Long profileId = apiService.addProfile(userDTO).block();
        System.out.println(profileId);
        userDTO.setProfileId(profileId);
        userRepository.save(userDTO.toEntity());
        logger.debug("User {} registration Successful",userDTO);
    }

    @Override
    public UserDTO loginUser(LoginDTO userDTO) throws HmsException {
        User user= userRepository.findByEmail(userDTO.getEmail().toLowerCase()).orElseThrow(()->new HmsException("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())){
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

    @Override
    public UserDTO getUser(String email) throws HmsException {
        return userRepository.findByEmail(email).orElseThrow(()-> new HmsException("USER_NOT_FOUND")).toDTO();
    }
}
