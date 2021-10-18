package com.spring.security.model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String role;

    protected AuthUser() {
    }

    public AuthUser(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public List<String> getRoleList(){
        if(this.role.length()>0){
            return Arrays.asList(role.split(","));
        }
        return new ArrayList<>();
    }

}
