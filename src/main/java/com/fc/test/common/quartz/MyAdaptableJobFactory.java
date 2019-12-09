package com.fc.test.common.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/12/6
 * @Version 1.0
 **/
@Component("myAdaptableJobFactory")
public class MyAdaptableJobFactory extends AdaptableJobFactory {

    //AutowireCapableBeanFactory 将对象添加到SpringIOC容器中 并且完成对象注入
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    /**
     * @title:
     * @description: 该方法需要将实例化的任务对象手动的添加到SpringIOC容器中并完成对象注入
     * @author: X
     * @updateTime: 2019/12/6 21:54
     * @return:
     * @param:
     * @throws:
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object obj = super.createJobInstance(bundle);
        //将obj对象添加到SpringIOC容器中并完成对象注入
        this.autowireCapableBeanFactory.autowireBean(obj);
        return obj;
    }
}
