package test.com.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName dell
 * @Description Job 任务类
 * @Author X
 * @Data 2019/12/6
 * @Version 1.0
 **/
public class QuartzJob implements Job {

    @Autowired
    private VaccineDateQuartzService vaccineDateQuartzService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       /* Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        System.out.println(format);*/
        //vaccineDateQuartzService.addUsers();
        try {
            vaccineDateQuartzService.vaccineDateQuartz();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
