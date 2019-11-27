package com.fc.test.mapper.auto;

import com.fc.test.model.auto.TSysUserVaccinationInfo;
import com.fc.test.model.auto.TSysUserVaccinationInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysUserVaccinationInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_vaccination_info
     *
     * @mbg.generated
     */
    long countByExample(TSysUserVaccinationInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_vaccination_info
     *
     * @mbg.generated
     */
    int deleteByExample(TSysUserVaccinationInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_vaccination_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_vaccination_info
     *
     * @mbg.generated
     */
    int insert(TSysUserVaccinationInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_vaccination_info
     *
     * @mbg.generated
     */
    int insertSelective(TSysUserVaccinationInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_vaccination_info
     *
     * @mbg.generated
     */
    List<TSysUserVaccinationInfo> selectByExample(TSysUserVaccinationInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_vaccination_info
     *
     * @mbg.generated
     */
    TSysUserVaccinationInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_vaccination_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TSysUserVaccinationInfo record, @Param("example") TSysUserVaccinationInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_vaccination_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TSysUserVaccinationInfo record, @Param("example") TSysUserVaccinationInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_vaccination_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TSysUserVaccinationInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_vaccination_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TSysUserVaccinationInfo record);
}