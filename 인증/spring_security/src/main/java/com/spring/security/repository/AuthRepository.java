package com.spring.security.repository;

import com.spring.security.model.AuthUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthRepository  extends CrudRepository<AuthUser,Long> {

    AuthUser findByEmail(String email);
    AuthUser save(AuthUser user);
}
