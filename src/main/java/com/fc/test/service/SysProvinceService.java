package com.fc.test.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.fc.test.common.support.Convert;
import com.fc.test.mapper.auto.SysProvinceMapper;
import com.fc.test.model.auto.SysProvince;
import com.fc.test.model.auto.SysProvinceExample;
import com.fc.test.model.custom.Tablepar;

/**
 * 省份表 SysProvinceService
 *
 * @author fuce_自动生成
 * @Title: SysProvinceService.java 
 * @Package com.fc.test.service 
 * @email 115889198@qq.com
 * @date 2019-10-04 19:56:15  
 **/
@Service
public class SysProvinceService {
    @Autowired
    private SysProvinceMapper sysProvinceMapper;


    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<SysProvince> list(Tablepar tablepar, String searchTxt, String searchText) {
        SysProvinceExample testExample = new SysProvinceExample();
        testExample.setOrderByClause("id ASC");
        if (searchTxt != null && !"".equals(searchTxt)) {
            testExample.createCriteria().andProvinceNameLike("%" + searchTxt + "%");
        }
        if (searchText != null && !"".equals(searchText)) {
            testExample.createCriteria().andProvinceNameLike("%" + searchText + "%");
        }

        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
        List<SysProvince> list = sysProvinceMapper.selectByExample(testExample);
        PageInfo<SysProvince> pageInfo = new PageInfo<SysProvince>(list);
        return pageInfo;
    }


    public int deleteByPrimaryKey(String ids) {
        Integer[] integers = Convert.toIntArray(",", ids);
        List<Integer> stringB = Arrays.asList(integers);
        SysProvinceExample example = new SysProvinceExample();
        example.createCriteria().andIdIn(stringB);
        return sysProvinceMapper.deleteByExample(example);
    }


    public SysProvince selectByPrimaryKey(Integer id) {

        return sysProvinceMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(SysProvince record) {
        return sysProvinceMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 添加
     */
    public int insertSelective(SysProvince record) {
        //添加主键id
        record.setId(null);
        return sysProvinceMapper.insertSelective(record);
    }


    public int updateByExampleSelective(SysProvince record, SysProvinceExample example) {

        return sysProvinceMapper.updateByExampleSelective(record, example);
    }


    public int updateByExample(SysProvince record, SysProvinceExample example) {

        return sysProvinceMapper.updateByExample(record, example);
    }


    public List<SysProvince> selectByExample(SysProvinceExample example) {

        return sysProvinceMapper.selectByExample(example);
    }


    public long countByExample(SysProvinceExample example) {

        return sysProvinceMapper.countByExample(example);
    }


    public int deleteByExample(SysProvinceExample example) {

        return sysProvinceMapper.deleteByExample(example);
    }

    /**
     * 检查name
     *
     * @param sysProvince
     * @return
     */
    public int checkNameUnique(SysProvince sysProvince) {
        SysProvinceExample example = new SysProvinceExample();
        example.createCriteria().andProvinceNameEqualTo(sysProvince.getProvinceName());
        List<SysProvince> list = sysProvinceMapper.selectByExample(example);
        return list.size();
    }

}
