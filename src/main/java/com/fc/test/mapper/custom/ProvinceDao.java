package com.fc.test.mapper.custom;

import com.fc.test.model.auto.SysProvince;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/11/20-14:26
 * @Version 1.0
 **/
public interface ProvinceDao {

    public SysProvince queryProvinceByCode (String provinceCode);
}
