package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.common.log.Log;
import com.fc.test.model.auto.TSysConsumer;
import com.fc.test.model.auto.TSysUserVaccinationInfo;
import com.fc.test.model.auto.TSysVaccineInfo;
import com.fc.test.model.auto.TSysVaccineInfoExample;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import com.fc.test.service.SysVaccineInfoService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/11/21
 * @Version 1.0
 **/
@Controller
@RequestMapping("/UserVaccinationController")
public class UserVaccinationController extends BaseController {

    @Autowired
    private SysVaccineInfoService sysVaccineInfoService;

    private String prefix = "admin/vaccination";


    @GetMapping("/view")
    @RequiresPermissions("system:userVaccination:view")
    public String view(ModelMap model) {
        String str = "用户接种疫苗信息";
        setTitle(model, new TitleVo("列表", str + "管理", true, "欢迎进入" + str + "页面", true, false));
        return prefix + "/list";
    }


    @PostMapping("/list")
    @RequiresPermissions("system:userVaccination:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt) {
        PageInfo<TSysUserVaccinationInfo> page = userVaccinationService.list(tablepar, searchTxt);
        TableSplitResult<TSysUserVaccinationInfo> result = new TableSplitResult<TSysUserVaccinationInfo>(page.getPageNum(), page.getTotal(), page.getList());
        return result;
    }

    /**
     * 新增疫苗信息
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        //查询疫苗数据库疫苗名称 返回给用户接种疫苗管理页面得到疫苗名称
       /* List<TSysVaccineInfo> tSysVaccineInfos = sysVaccineInfoService.selectByExample(new TSysVaccineInfoExample());
        for (int i = 0; i < tSysVaccineInfos.size(); i++){
            Integer isOverdue = tSysVaccineInfos.get(i).getIsOverdue();
            if (isOverdue == 1){
                String vaccineName = tSysVaccineInfos.get(i).getVaccineName();
            }
            return null;
        }*/
        modelMap.addAttribute("vaccineList",sysVaccineInfoService.selectByExample(new TSysVaccineInfoExample()) );
        //添加角色列表
        return prefix + "/add";
    }

    @Log(title = "疫苗新增")
    @PostMapping("/add")
    @RequiresPermissions("system:userVaccination:add")
    @ResponseBody
    public AjaxResult add(TSysUserVaccinationInfo userVaccineInfo, Model model) {
        int b = userVaccinationService.insertSelective(userVaccineInfo);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 删除疫苗信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/remove")
    @RequiresPermissions("system:userVaccination:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int b = userVaccinationService.deleteByPrimaryKey(ids);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 修改疫苗信息
     *
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        //通过id查询所有接种疫苗用户信息
        mmap.put("TSysUserVaccinationInfo", userVaccinationService.selectByPrimaryKey(id));
        //查询疫苗数据库疫苗名称 返回给用户接种疫苗管理页面得到疫苗名称
        mmap.addAttribute("vaccineList", sysVaccineInfoService.selectByExample(new TSysVaccineInfoExample()));

        return prefix + "/edit";
    }

    /**
     * 修改保存疫苗信息
     */
    @RequiresPermissions("system:userVaccination:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TSysUserVaccinationInfo tSysUserVaccinationInfo) {
        return toAjax(userVaccinationService.updateUserVaccinationInfo(tSysUserVaccinationInfo));
    }

    /**
     * 通过身份证号检查用户是否存在
     *
     * @param TsysUser
     * @return
     */
    @PostMapping("/checkVaccinationUserIdNumber")
    @ResponseBody
    public Object checkVaccinationUserIdNumber(@RequestParam(value = "vaccinationUserIdNumber",required = false) String vaccinationUserIdNumber) {
        if (vaccinationUserIdNumber != ""){
            PageInfo<TSysConsumer> result= userVaccinationService.checkVaccinationUserIdNumber(vaccinationUserIdNumber);
            if(result.getSize() > 0){
                return result;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }
}
