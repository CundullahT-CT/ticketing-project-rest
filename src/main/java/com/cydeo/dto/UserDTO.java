package com.cydeo.dto;

import com.cydeo.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passWord;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassWord;
    private boolean enabled;
    private String phone;
    private RoleDTO role;
    private Gender gender;
}


