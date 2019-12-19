package com.fc.test.mapper.custom;

import com.fc.test.model.auto.TSysVaccineInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName dell
 * @Description 手动书写疫苗信息mapper接口
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
    public List<TSysVaccineInfo> queryVaccineInfo();

    /**
     * @description: 查询疫苗是否过期 返回list列表
     * @author: X
     * @updateTime: 2019/12/16 0:05
     * @param overdueType
     */
    public List<TSysVaccineInfo> queryVaccineType(Integer overdueType);
}
