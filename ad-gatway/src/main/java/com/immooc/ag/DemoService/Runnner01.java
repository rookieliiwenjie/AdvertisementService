package com.immooc.ag.DemoService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by lwj32 on 2021/2/3.
 */
@Component
public class Runnner01 implements CommandLineRunner
{
    @Override
    public void run(String... args) throws Exception {
        System.out.println("runner01" + args);
    }
}
