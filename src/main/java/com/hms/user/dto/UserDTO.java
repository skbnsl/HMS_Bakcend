package com.hms.user.dto;

import com.hms.user.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "email is mandatory")
    @Email(message = "email should be valid")
    // @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,15}$",
    //  message = "password should contans 1 upperCase, 1 lowerCase and 1 special character "
    //             + "and min 8 Character and max 15")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
    private Roles role;

    public User toEntity(){
        return new User(this.id,this.name,this.email,this.password,this.role);
    }

}
