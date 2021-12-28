package com.spring.redis.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.redis.config.ModelMapperUtil;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    private String name;

    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    public List<Post> postList=new ArrayList<>();

    public static User toUser(UserDto userDto){
        User user= ModelMapperUtil.getModelMapper().map(userDto,User.class);
        user.setPostList(userDto.getPostList()
                .stream()
                .map(pd->ModelMapperUtil.getModelMapper().map(pd,Post.class))
                .collect(Collectors.toList()));
        return user;
    }
}
