package com.spring.transaction.service;

import com.spring.transaction.entity.Test;
import com.spring.transaction.repository.TestRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@AllArgsConstructor
public class Scheduler {

    private final TestService testService;

    //@Transactional(rollbackFor = Exception.class)
    @Scheduled(cron="10 * * * * *")
    public void cron() throws Exception {
        testService.cronTransaction();
    }

}
