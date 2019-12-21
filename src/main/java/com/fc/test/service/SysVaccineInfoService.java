package com.fc.test.service;

import com.fc.test.common.base.BaseService;
import com.fc.test.common.support.Convert;
import com.fc.test.mapper.auto.TSysVaccineInfoMapper;
import com.fc.test.mapper.custom.VaccineInfoDao;
import com.fc.test.model.auto.TSysVaccineInfo;
import com.fc.test.model.auto.TSysVaccineInfoExample;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author fuce
 * @ClassName: SysUserService
 * @date 2018年8月26日
 */
@Service
public class SysVaccineInfoService implements BaseService<TSysVaccineInfo, TSysVaccineInfoExample> {
    //生成的疫苗dao
    @Autowired
    private TSysVaccineInfoMapper tSysVaccineInfoMapper;
    //手动书写疫苗dao
    @Autowired
    private VaccineInfoDao vaccineInfoDao;

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<TSysVaccineInfo> list(Tablepar tablepar,String searchTxt, String searchText, Integer searchSelect) {
        TSysVaccineInfoExample testExample = new TSysVaccineInfoExample();
        testExample.setOrderByClause("id+0 DESC");
        if (searchTxt != null && !"".equals(searchTxt)) {
            testExample.createCriteria().andVaccineNameLike("%" + searchTxt + "%");
        }
        if (searchText != null && !"".equals(searchText)) {
            testExample.createCriteria().andVaccineNameLike("%" + searchText + "%");
        }
        if (searchSelect != null && !"".equals(searchSelect)) {
            testExample.createCriteria().andIsOverdueEqualTo(searchSelect);
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
        String accineId = SnowflakeIdWorker.getUUID();
        record.setId(accineId);
        record.setIsOverdue(1);
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

    /**
     * @title: 定时任务
     * @description: 定时查询数据库疫苗有效时间 当前时间大于等于有效期 将状态更改为2
     * 状态码 1为没有过期 2为已过期
     * @author: X
     * @updateTime: 2019/12/9 19:35
     * @return:
     * @param:
     * @throws:
     */
    public void vaccineDateQuartz() {
        //格式换时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        Date dateTime = new Date();
        // 输出已经格式化的现在时间（24小时制）
        String date = dateFormat.format(dateTime);
        //查询vaccineInfoList
        List<TSysVaccineInfo> vaccineInfoList = vaccineInfoDao.queryVaccineInfo();
        //得到vaccine的有效日期
        for (int i = 0; i < vaccineInfoList.size(); i++) {
            //查得有效时间
            String effectiveDate = vaccineInfoList.get(i).getEffectiveDate();
            try {
                if (dateFormat.parse(date).getTime() >= dateFormat.parse(effectiveDate).getTime()) {
                    //查得id
                    String vaccineId = vaccineInfoList.get(i).getId();
                    TSysVaccineInfoExample tSysVaccineInfoExample = new TSysVaccineInfoExample();
                    tSysVaccineInfoExample.createCriteria().andIdEqualTo(vaccineId);
                    List<TSysVaccineInfo> vaccineInfos = tSysVaccineInfoMapper.selectByExample(tSysVaccineInfoExample);
                    for (TSysVaccineInfo tSysVaccineInfo : vaccineInfos) {
                        //更新状态 1为没有过期 2为已过期
                        tSysVaccineInfo.setIsOverdue(2);
                        tSysVaccineInfoMapper.updateByPrimaryKey(tSysVaccineInfo);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @description: 疫苗有效期类别
     * @author: X
     * @updateTime: 2019/12/16 14:28
     */
   /* public PageInfo<TSysVaccineInfo> listType(Integer overdueType) {
       // PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        List<TSysVaccineInfo> listType = vaccineInfoDao.queryVaccineType(overdueType);
        PageInfo<TSysVaccineInfo> pageInfo = new PageInfo<TSysVaccineInfo>(listType);
        return pageInfo;
    }*/
}
