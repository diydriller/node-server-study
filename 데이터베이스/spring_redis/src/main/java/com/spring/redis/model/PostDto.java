package com.spring.redis.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.redis.config.ModelMapperUtil;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Type;

@Data
public class PostDto implements Serializable {
    private static final long serialVersionUID = 2L;

    private Long id;

    private String title;
    private String content;
    @JsonBackReference
    private UserDto user;

    public static PostDto toPostDto(Post post){
        PostDto postDto=ModelMapperUtil.getModelMapper().map(post,PostDto.class);
        postDto.setUser(UserDto.toUserDto(post.getUser()));
        return postDto;
    }
}
