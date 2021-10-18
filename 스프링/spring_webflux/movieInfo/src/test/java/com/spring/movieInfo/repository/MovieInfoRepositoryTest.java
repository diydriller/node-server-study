package com.spring.movieInfo.repository;

import com.spring.movieInfo.domain.MovieInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;


// spring context 사용 안하고 repository 만 사용하기 때문에 빠르다.
// 내장 몽고디비 사용
@DataMongoTest
// 내장 몽고디비 설정 프로필
@ActiveProfiles("dev")
class MovieInfoRepositoryTest {

    @Autowired
    MovieInfoRepository movieInfoRepository;

    @BeforeEach
    void setUp(){
        var movieinfos = List.of(new MovieInfo(null, "Batman Begins",
                        2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")),
                new MovieInfo(null, "The Dark Knight",
                        2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18")),
                new MovieInfo("abc", "Dark Knight Rises",
                        2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-20")));

        movieInfoRepository.saveAll(movieinfos)
                .blockLast();
    }


    @Test
    void findAll(){
        var moviesFlux=movieInfoRepository.findAll().log();

        StepVerifier.create(moviesFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void findById(){
        var moviesMono=movieInfoRepository.findById("abc").log();


        StepVerifier.create(moviesMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    @AfterEach
    void tearDown(){
        movieInfoRepository.deleteAll().block();
    }
}