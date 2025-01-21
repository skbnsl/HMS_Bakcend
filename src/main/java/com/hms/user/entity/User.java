package com.hms.user.entity;


/*
* @Data will provide below four properties of lombok
 @Getter
 @Setter
* */

import com.hms.user.dto.Roles;
import com.hms.user.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    //@Min(7 , message="")
    private String password;
    private Roles role;

    public UserDTO toDTO(){
        return new UserDTO(this.id,this.name,this.email,this.password,this.role);
    }

}
