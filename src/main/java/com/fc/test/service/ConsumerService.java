package com.fc.test.service;

import java.util.ArrayList;
import java.util.List;

import com.fc.test.mapper.auto.TSysConsumerMapper;
import com.fc.test.mapper.custom.*;
import com.fc.test.model.auto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fc.test.common.base.BaseService;
import com.fc.test.common.support.Convert;
import com.fc.test.mapper.auto.TSysRoleUserMapper;
import com.fc.test.mapper.auto.TsysRoleMapper;
import com.fc.test.model.custom.RoleVo;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.util.MD5Util;
import com.fc.test.util.SnowflakeIdWorker;
import com.fc.test.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/11/29
 * @Version 1.0
 **/
@Service
public class ConsumerService implements BaseService<TSysConsumer, TSysConsumerExample> {
    //生成的用户dao
    @Autowired
    private TSysConsumerMapper tSysConsumerMapper;

    //生成的角色用户dao
    @Autowired
    private TSysRoleUserMapper tSysRoleUserMapper;

    //自定义角色dao
    @Autowired
    private RoleDao roleDao;

    //自动生成的角色dao
    @Autowired
    private TsysRoleMapper tsysRoleMapper;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private StreetDao streetDao;

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<TSysConsumer> list(Tablepar tablepar, String idNumber) {
        //模糊查询
        TSysConsumerExample testExample = new TSysConsumerExample();
        testExample.setOrderByClause("id+0 DESC");
        if (idNumber != null && !"".equals(idNumber)) {
            testExample.createCriteria().andIdNumberLike("%" + idNumber + "%");
        }

        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        List<TSysConsumer> list = tSysConsumerMapper.selectByExample(testExample);
        PageInfo<TSysConsumer> pageInfo = new PageInfo<TSysConsumer>(list);
        return pageInfo;
    }


    @Override
    public int deleteByPrimaryKey(String ids) {
        List<String> lista = Convert.toListStrArray(ids);
        TSysConsumerExample example = new TSysConsumerExample();
        example.createCriteria().andIdIn(lista);
        return tSysConsumerMapper.deleteByExample(example);
    }

    /**
     * 添加用户
     */
    @Override
    @Transactional
    public int insertSelective(TSysConsumer record) {
        String accineId = SnowflakeIdWorker.getUUID();
        record.setId(accineId);
        //省市区一般以数字的形式传入进来的，判断是否为数字
        if (isNumeric(record.getProvinceCode())) {
            //通过对应的省份的数字代号查出对应点的省份的名称
            SysProvince sysProvince = provinceDao.queryProvinceByCode(record.getProvinceCode());
            if (sysProvince != null) {
                record.setProvinceCode(sysProvince.getProvinceName());
            }
        }
        //省市区一般以数字的形式传入进来的，判断是否为数字
        if (isNumeric(record.getCityCode())) {
            //通过对应的城市的数字代号查出对应点的城市的名称
            SysCity sysCity = cityDao.queryCityByCode(record.getCityCode());
            if (sysCity != null) {
                record.setCityCode(sysCity.getCityName());
            }
        }
        //省市区一般以数字的形式传入进来的，判断是否为数字
        if (isNumeric(record.getAreaCode())) {
            //通过对应的地区的数字代号查出对应点的地区的名称
            SysArea sysArea = areaDao.queryAreaByCode(record.getAreaCode());
            if (sysArea != null) {
                record.setAreaCode(sysArea.getAreaName());
            }
        }
        //省市区一般以数字的形式传入进来的，判断是否为数字
        if (isNumeric(record.getStreetCode())) {
            //通过对应的街道的数字代号查出对应点的街道的名称
            SysStreet sysStreet = streetDao.queryStreetByCode(record.getStreetCode());
            if (sysStreet != null) {
                record.setStreetCode(sysStreet.getStreetName());
            }
        }
        //获取通过code查询出的地名
        String recordProvince = record.getProvinceCode();
        String recordCity = record.getCityCode();
        String recordArea = record.getAreaCode();
        String recordStreet = record.getStreetCode();
        //获取详细地址
        String detailedAddress = record.getDetailedAddress();
        record.setHomeAddress(recordProvince + recordCity + recordArea + recordStreet + detailedAddress);
        //密码加密
        record.setPassword(MD5Util.encode(record.getPassword()));
        return tSysConsumerMapper.insertSelective(record);
    }

    @Override
    public TSysConsumer selectByPrimaryKey(String id) {

        return tSysConsumerMapper.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(TSysConsumer record) {
        record.setPassword(MD5Util.encode(record.getPassword()));
        return tSysConsumerMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int updateByExampleSelective(TSysConsumer record, TSysConsumerExample example) {

        return tSysConsumerMapper.updateByExampleSelective(record, example);
    }


    @Override
    public int updateByExample(TSysConsumer record, TSysConsumerExample example) {

        return tSysConsumerMapper.updateByExample(record, example);
    }

    @Override
    public List<TSysConsumer> selectByExample(TSysConsumerExample example) {

        return tSysConsumerMapper.selectByExample(example);
    }


    @Override
    public long countByExample(TSysConsumerExample example) {

        return tSysConsumerMapper.countByExample(example);
    }


    @Override
    public int deleteByExample(TSysConsumerExample example) {

        return tSysConsumerMapper.deleteByExample(example);
    }

    /**
     * 检查用户登录名称
     *
     * @param TSysConsumer
     * @return
     */
    public int checkLoginNameUnique(TSysConsumer TSysConsumer) {
        TSysConsumerExample example = new TSysConsumerExample();
        example.createCriteria().andUsernameEqualTo(TSysConsumer.getUsername());
        List<TSysConsumer> list = tSysConsumerMapper.selectByExample(example);

        return list.size();
    }

    /**
     * 修改用户密码
     *
     * @param record
     * @return
     */
    public int updateUserPassword(TSysConsumer record) {
        record.setPassword(MD5Util.encode(record.getPassword()));
        //修改用户信息
        return tSysConsumerMapper.updateByPrimaryKeySelective(record);
    }


    /**
     * 修改用户信息以及角色信息
     *
     * @param record
     * @param roles
     * @return
     */
    @Transactional
    public int updateConsumer(TSysConsumer record) {
        //更新用户信息
        tSysConsumerMapper.updateByPrimaryKeySelective(record);
        //修改用户信息
        return 1;
    }


}
