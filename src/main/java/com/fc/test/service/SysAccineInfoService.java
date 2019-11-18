package com.fc.test.service;

import com.fc.test.common.base.BaseService;
import com.fc.test.common.support.Convert;
import com.fc.test.mapper.auto.TSysVaccineInfoMapper;
import com.fc.test.model.auto.*;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统用户
 *
 * @author fuce
 * @ClassName: SysUserService
 * @date 2018年8月26日
 */
@Service
public class SysAccineInfoService implements BaseService<TSysVaccineInfo, TSysVaccineInfoExample> {
    //生成的疫苗dao
    @Autowired
    private TSysVaccineInfoMapper tSysVaccineInfoMapper;

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<TSysVaccineInfo> list(Tablepar tablepar, String vaccineName) {
        TSysVaccineInfoExample testExample = new TSysVaccineInfoExample();
        testExample.setOrderByClause("id+0 DESC");
        if (vaccineName != null && !"".equals(vaccineName)) {
            testExample.createCriteria().andVaccineNameLike("%" + vaccineName + "%");
        }

        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        List<TSysVaccineInfo> list = tSysVaccineInfoMapper.selectByExample(testExample);
        PageInfo<TSysVaccineInfo> pageInfo = new PageInfo<TSysVaccineInfo>(list);
        return pageInfo;
    }


    @Override
    public int deleteByPrimaryKey(String ids) {
        List<String> lista = Convert.toListStrArray(ids);
        TSysVaccineInfoExample example = new TSysVaccineInfoExample();
        example.createCriteria().andIdIn(lista);
        return tSysVaccineInfoMapper.deleteByExample(example);
    }

    /**
     * 添加疫苗
     */
    @Override
    public int insertSelective(TSysVaccineInfo record) {
        String accineId=SnowflakeIdWorker.getUUID();
        record.setId(accineId);
        return tSysVaccineInfoMapper.insertSelective(record);
    }

    @Override
    public TSysVaccineInfo selectByPrimaryKey(String id) {

        return tSysVaccineInfoMapper.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(TSysVaccineInfo record) {
        return tSysVaccineInfoMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int updateByExampleSelective(TSysVaccineInfo record, TSysVaccineInfoExample example) {

        return tSysVaccineInfoMapper.updateByExampleSelective(record, example);
    }


    @Override
    public int updateByExample(TSysVaccineInfo record, TSysVaccineInfoExample example) {

        return tSysVaccineInfoMapper.updateByExample(record, example);
    }

    @Override
    public List<TSysVaccineInfo> selectByExample(TSysVaccineInfoExample example) {

        return tSysVaccineInfoMapper.selectByExample(example);
    }


    @Override
    public long countByExample(TSysVaccineInfoExample example) {

        return tSysVaccineInfoMapper.countByExample(example);
    }


    @Override
    public int deleteByExample(TSysVaccineInfoExample example) {

        return tSysVaccineInfoMapper.deleteByExample(example);
    }

    /**
     * @title: updateVaccineInfo
     * @description: 修改疫苗信息
     * @author: X
     * @updateTime: 2019/11/17 21:31
     */
    @Transactional
    public int updateVaccineInfo(TSysVaccineInfo record) {
        //更新用户信息
        tSysVaccineInfoMapper.updateByPrimaryKeySelective(record);
        //修改用户信息
        return 1;
    }


}
