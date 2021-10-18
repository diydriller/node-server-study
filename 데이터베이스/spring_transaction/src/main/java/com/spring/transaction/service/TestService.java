package com.spring.transaction.service;

import com.spring.transaction.entity.Test;
import com.spring.transaction.repository.TestRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
@Service
@AllArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    @Transactional(rollbackFor = Exception.class)
    public void cronTransaction() throws Exception {

        Test t1 = new Test();
        t1.setContent("first data");
        testRepository.saveAndFlush(t1);

        if(t1!=null){
            throw new Exception("error");
        }

        Test t2 = new Test();
        t2.setContent("second data");
        testRepository.saveAndFlush(t2);


    }
}
