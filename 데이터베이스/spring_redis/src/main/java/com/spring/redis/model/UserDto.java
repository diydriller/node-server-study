package com.spring.redis.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.redis.config.ModelMapperUtil;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String email;
    @JsonManagedReference
    public List<PostDto> postList=new ArrayList<>();

    public static UserDto toUserDto(User user){
        UserDto userDto= ModelMapperUtil.getModelMapper().map(user,UserDto.class);
        userDto.setPostList(user.getPostList()
                .stream()
                .map(p->ModelMapperUtil.getModelMapper().map(p,PostDto.class))
                .collect(Collectors.toList()));
        return userDto;
    }
}
