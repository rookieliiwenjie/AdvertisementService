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
/**
 * Spring Boot如何解决项目启动时初始化资源，在我们实际工作中，总会遇到这样需求，
 * 在项目启动的时候需要做一些初始化的操作，比如初始化线程池，提前加载好加密证书等。
 *
 * 为了达到这个目的，我们需要使用CommandLineRunner或ApplicationRunner
 * 接口创建bean，spring boot会自动监测到它们。这两个接口都有一个run()方法，在实现接口时需要覆盖该方法，并使用@Component注解使其成为bean。
 *
 * CommandLineRunner和ApplicationRunner的作用是相同的。不同之处在于CommandLineRunner接口的run()方法接收String数组作为参数
 * ，即是最原始的参数，没有做任何处理；而ApplicationRunner接口的run()方法接收ApplicationArguments对象作为参数，是对原始参数做了进一步的封装。
 *
 * 当程序启动时，我们传给main()方法的参数可以被实现CommandLineRunner和ApplicationRunner接口的类的run()方法访问，
 * 即可接收启动服务时传过来的参数。我们可以创建多个实现CommandLineRunner和ApplicationRunner接口的类。为了使他们按一定顺序执行，可以使用@Order注解或实现Ordered接口
 */
public class Runnner02 implements ApplicationRunner
{
  
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("args = " + args);
    }
}
