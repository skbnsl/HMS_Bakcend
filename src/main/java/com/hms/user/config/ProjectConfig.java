package com.hms.user.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public org.modelmapper.ModelMapper ModelMapper(){
        return new org.modelmapper.ModelMapper();
    }

/* @Autowired
    private ModelMapper mapper;
updatedCart=entity
*   //return mapper.map(updatedCart,CartDto.class);
this will return entity to dto
* */
}