package com.fc.test.common.quartz;

import com.fc.test.service.SysVaccineInfoService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @ClassName dell
 * @Description Job 任务类
 * @Author X
 * @Data 2019/12/6
 * @Version 1.0
 **/
public class QuartzJob implements Job {

    @Autowired
    private SysVaccineInfoService sysVaccineInfoService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        /**
         * @title: 疫苗定时任务
         * @description: 疫苗定时任务 server层的vaccineDateQuartz方法
         * @author: X
         * @updateTime: 2019/12/8 1:10
         * @return: void
         * @param: [jobExecutionContext]
         * @throws:
         */
        sysVaccineInfoService.vaccineDateQuartz();
    }
}
