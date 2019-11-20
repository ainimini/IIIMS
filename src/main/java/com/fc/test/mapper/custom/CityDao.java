package com.fc.test.mapper.custom;

import com.fc.test.model.auto.SysCity;
import com.fc.test.model.auto.SysProvince;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/11/20-15:11
 * @Version 1.0
 **/
public interface CityDao {

    public SysCity queryCityByCode (String cityCode);
}
