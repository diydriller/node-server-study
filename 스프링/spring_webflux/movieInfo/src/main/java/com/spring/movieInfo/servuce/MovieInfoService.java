package com.spring.movieInfo.servuce;

import com.spring.movieInfo.domain.MovieInfo;
import com.spring.movieInfo.repository.MovieInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MovieInfoService {

    private MovieInfoRepository movieInfoRepository;


    public Mono<MovieInfo> addMovieInfo(MovieInfo moviceInfo) {

        return movieInfoRepository.save(moviceInfo);
    }
}
