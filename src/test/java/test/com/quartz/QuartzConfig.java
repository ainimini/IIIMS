package test.com.quartz;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @ClassName dell
 * @Description Quartz配置类
 * @Author X
 * @Data 2019/12/6
 * @Version 1.0
 **/
@Configuration
public class QuartzConfig {

    //创建job对象
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        //关联自己的job类
        factory.setJobClass(QuartzJob.class);
        return factory;
    }

    //创建Trigger对象
    /*@Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
        SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
        //关联JobDetail对象
        factory.setJobDetail(jobDetailFactoryBean.getObject());
        //执行时间（毫秒）
        factory.setRepeatInterval(2000);
        //执行次数
        factory.setRepeatCount(5);
        return factory;
    }*/

    //创建Cron Trigger
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        //关联JobDetail对象
        factory.setJobDetail(jobDetailFactoryBean.getObject());
        //执行时间（毫秒）
        factory.setCronExpression("0/2 * * * * ?");
        return factory;
    }

    //创建Scheduler对象
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean, MyAdaptableJobFactory myAdaptableJobFactory) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //关联trigger
        factory.setTriggers(cronTriggerFactoryBean.getObject());
        factory.setJobFactory(myAdaptableJobFactory);
        return factory;
    }
}
