package com.fc.test.mapper.custom;

import com.fc.test.model.auto.SysProvince;
import com.fc.test.model.auto.SysStreet;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/11/20-15:13
 * @Version 1.0
 **/
public interface StreetDao {

    public SysStreet queryStreetByCode (String streetCode);

}
