package com.immooc.ag.DemoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by lwj32 on 2021/2/4.
 */
@Component
@Slf4j
public class SucheduDemo {
    //fixedDelay上一次执行完再执行
    @Scheduled(fixedDelay = 2000)
    public int demo() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        log.info("11111");
            return 1;

    }

}
