package com.fc.test.mapper.custom;

import com.fc.test.model.auto.SysArea;
import com.fc.test.model.auto.SysProvince;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/11/20-15:12
 * @Version 1.0
 **/
public interface AreaDao {

    public SysArea queryAreaByCode (String areaCode);
}
