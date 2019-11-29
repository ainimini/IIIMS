package com.fc.test.service;

import com.fc.test.common.base.BaseService;
import com.fc.test.common.support.Convert;
import com.fc.test.mapper.auto.TSysConsumerMapper;
import com.fc.test.mapper.auto.TSysUserVaccinationInfoMapper;
import com.fc.test.mapper.auto.TsysUserMapper;
import com.fc.test.mapper.custom.TsysUserDao;
import com.fc.test.model.auto.*;
import com.github.pagehelper.PageInfo;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/11/21
 * @Version 1.0
 **/
@Service
public class UserVaccinationService implements BaseService<TSysUserVaccinationInfo, TSysUserVaccinationInfoExample> {

    //生成的疫苗dao
    @Autowired
    private TSysUserVaccinationInfoMapper tSysUserVaccinationInfoMapper;

    @Autowired
    private TsysUserMapper tsysUserMapper;

    @Autowired
    private TSysConsumerMapper tSysConsumerMapper;

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<TSysUserVaccinationInfo> list(Tablepar tablepar, String vaccinationUserIdNumber) {
        //模糊查询
        TSysUserVaccinationInfoExample testExample = new TSysUserVaccinationInfoExample();
        testExample.setOrderByClause("id+0 DESC");
        if (vaccinationUserIdNumber != null && !"".equals(vaccinationUserIdNumber)) {
            testExample.createCriteria().andVaccinationUserIdNumberLike("%" + vaccinationUserIdNumber + "%");
        }

        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        List<TSysUserVaccinationInfo> list = tSysUserVaccinationInfoMapper.selectByExample(testExample);
        PageInfo<TSysUserVaccinationInfo> pageInfo = new PageInfo<TSysUserVaccinationInfo>(list);
        return pageInfo;
    }


    @Override
    public int deleteByPrimaryKey(String ids) {
        List<String> lista = Convert.toListStrArray(ids);
        TSysUserVaccinationInfoExample example = new TSysUserVaccinationInfoExample();
        example.createCriteria().andIdIn(lista);
        return tSysUserVaccinationInfoMapper.deleteByExample(example);
    }

    /**
     * 添加疫苗
     */
    @Override
    public int insertSelective(TSysUserVaccinationInfo record) {
        String accineId = SnowflakeIdWorker.getUUID();
        record.setId(accineId);
        return tSysUserVaccinationInfoMapper.insertSelective(record);
    }

    @Override
    public TSysUserVaccinationInfo selectByPrimaryKey(String id) {

        return tSysUserVaccinationInfoMapper.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(TSysUserVaccinationInfo record) {
        return tSysUserVaccinationInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByExampleSelective(TSysUserVaccinationInfo record, TSysUserVaccinationInfoExample example) {
        return tSysUserVaccinationInfoMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(TSysUserVaccinationInfo record, TSysUserVaccinationInfoExample example) {

        return tSysUserVaccinationInfoMapper.updateByExample(record, example);
    }

    @Override
    public List<TSysUserVaccinationInfo> selectByExample(TSysUserVaccinationInfoExample example) {

        return tSysUserVaccinationInfoMapper.selectByExample(example);
    }


    @Override
    public long countByExample(TSysUserVaccinationInfoExample example) {

        return tSysUserVaccinationInfoMapper.countByExample(example);
    }


    @Override
    public int deleteByExample(TSysUserVaccinationInfoExample example) {

        return tSysUserVaccinationInfoMapper.deleteByExample(example);
    }

    /**
     * @title: updateVaccineInfo
     * @description: 修改疫苗信息
     * @author: X
     * @updateTime: 2019/11/17 21:31
     */
    @Transactional
    public int updateUserVaccinationInfo(TSysUserVaccinationInfo record) {
        //更新用户信息
        tSysUserVaccinationInfoMapper.updateByPrimaryKeySelective(record);
        //修改用户信息
        return 1;
    }

    /**
     * 通过接种疫苗用户身份证号检查用户
     *
     * @param tSysUserVaccinationInfo
     * @return
     */
    public  PageInfo<TSysConsumer> checkVaccinationUserIdNumber(String vaccinationUserIdNumber) {
        TSysConsumerExample testExample = new TSysConsumerExample();
        testExample.setOrderByClause("id+0 DESC");
        if (vaccinationUserIdNumber != null && !"".equals(vaccinationUserIdNumber)) {
            testExample.createCriteria().andIdNumberLike("%" + vaccinationUserIdNumber + "%");
        }
        List<TSysConsumer> list = tSysConsumerMapper.selectByExample(testExample);
        PageInfo<TSysConsumer> pageInfo = new PageInfo<TSysConsumer>(list);
        return pageInfo;
    }
}
