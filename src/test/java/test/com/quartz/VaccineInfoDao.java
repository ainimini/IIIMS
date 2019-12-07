package test.com.quartz;

import com.fc.test.model.auto.TSysVaccineInfo;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/12/6
 * @Version 1.0
 **/
public interface VaccineInfoDao {

    /**
     * 查询数据库中疫苗
     * @param
     * @return
     */
    public TSysVaccineInfo queryVaccineInfo();

    /**
     * @title:
     * @description: 更新疫苗信息表
     * @author: X
     * @updateTime: 2019/12/6 23:30
     * @return:
     * @param:
     * @throws:
     */
    public TSysVaccineInfo deleteVaccineInfo();
}
