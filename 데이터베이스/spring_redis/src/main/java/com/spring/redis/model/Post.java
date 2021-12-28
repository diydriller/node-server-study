package com.spring.redis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.redis.config.ModelMapperUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "post")
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="users_id")
    private User user;

    public void setUser(User user) {
        this.user=user;
        user.getPostList().add(this);
    }
    public static Post toPost(PostDto postDto){
        Post post= ModelMapperUtil.getModelMapper().map(postDto,Post.class);
        post.setUser(User.toUser(postDto.getUser()));
        return post;
    }
}
