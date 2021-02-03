package com.immooc.ag.DemoService;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by lwj32 on 2021/2/3.
 */
@Component
@Order(1)
public class Runnner02 implements ApplicationRunner
{
  
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("args = " + args);
    }
}
