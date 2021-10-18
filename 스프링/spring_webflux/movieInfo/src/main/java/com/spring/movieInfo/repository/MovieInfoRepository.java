package com.spring.movieInfo.repository;

import com.spring.movieInfo.domain.MovieInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface MovieInfoRepository extends ReactiveMongoRepository<MovieInfo,String> {

}
