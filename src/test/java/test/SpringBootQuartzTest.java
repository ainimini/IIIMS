package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/12/6
 * @Version 1.0
 **/
@SpringBootApplication
@EnableScheduling
public class SpringBootQuartzTest {

    public static void main(String[] args){
        SpringApplication.run(SpringBootQuartzTest.class,args);
    }
}
