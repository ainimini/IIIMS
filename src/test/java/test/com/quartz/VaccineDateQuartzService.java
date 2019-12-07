package test.com.quartz;

import com.fc.test.model.auto.TSysVaccineInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/12/6
 * @Version 1.0
 **/
@Service
public class VaccineDateQuartzService {

    @Autowired
    private VaccineInfoDao vaccineInfoDao;

    /*public void addUsers(){
        System.out.println("Add Users .....");
    }*/

    //查询数据库中疫苗的有效日期
    public List<TSysVaccineInfo> vaccineDateQuartz() throws ParseException {
        //格式换时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        Date dateTime = new Date();
        // 输出已经格式化的现在时间（24小时制）
        String date = dateFormat.format(dateTime);

        //查询vaccineInfo
        TSysVaccineInfo tSysVaccineInfo = vaccineInfoDao.queryVaccineInfo();
        //得到vaccine的有效日期
        String effectiveDate = tSysVaccineInfo.getEffectiveDate();
        //比较
        if (dateFormat.parse(date).getTime() >= dateFormat.parse(effectiveDate).getTime()) {
            vaccineInfoDao.deleteVaccineInfo();
        }
        return null;
    }
}
