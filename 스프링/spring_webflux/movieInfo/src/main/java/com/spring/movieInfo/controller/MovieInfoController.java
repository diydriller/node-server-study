package com.spring.movieInfo.controller;

import com.spring.movieInfo.domain.MovieInfo;
import com.spring.movieInfo.servuce.MovieInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class MovieInfoController {
    private MovieInfoService movieInfoService;

    @PostMapping("/movieinfos")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovieInfo> addMovieInfo(@RequestBody MovieInfo moviceInfo){
        return movieInfoService.addMovieInfo(moviceInfo).log();
    }
}
