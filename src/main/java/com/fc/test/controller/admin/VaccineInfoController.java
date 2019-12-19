package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.common.log.Log;
import com.fc.test.model.auto.TSysVaccineInfo;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName VaccineInfoController
 * @Description 疫苗信息管理 Controller
 * @Author X
 * @Data 2019/11/15
 * @Version 1.0
 **/
@Controller
@RequestMapping("/VaccineInfoController")
public class VaccineInfoController extends BaseController {

    private String prefix = "admin/vaccine";


    @GetMapping("/view")
    @RequiresPermissions("system:vaccineInfo:view")
    public String view(ModelMap model) {
        String str = "疫苗";
        setTitle(model, new TitleVo("列表", str + "管理", true, "欢迎进入" + str + "页面", true, false));
        return prefix + "/list";
    }


    @PostMapping("/list")
    @RequiresPermissions("system:vaccineInfo:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt, Integer overdueType) {
        PageInfo<TSysVaccineInfo> page = sysVaccineInfoService.list(tablepar, searchTxt, overdueType);
        TableSplitResult<TSysVaccineInfo> result = new TableSplitResult<TSysVaccineInfo>(page.getPageNum(), page.getTotal(), page.getList());
        return result;
    }

    /**
     * 新增疫苗信息
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        //添加角色列表
        return prefix + "/add";
    }

    @Log(title = "疫苗新增")
    @PostMapping("/add")
    @RequiresPermissions("system:vaccineInfo:add")
    @ResponseBody
    public AjaxResult add(TSysVaccineInfo vaccineInfo, Model model) {
        int b = sysVaccineInfoService.insertSelective(vaccineInfo);
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
    @RequiresPermissions("system:vaccineInfo:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int b = sysVaccineInfoService.deleteByPrimaryKey(ids);
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
        //查询所有疫苗信息
        mmap.put("TSysVaccineInfo", sysVaccineInfoService.selectByPrimaryKey(id));

        return prefix + "/edit";
    }

    /**
     * 修改保存疫苗信息
     */
    @RequiresPermissions("system:vaccineInfo:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TSysVaccineInfo tSysVaccineInfo) {
        return toAjax(sysVaccineInfoService.updateVaccineInfo(tSysVaccineInfo));
    }

    /**
     * @description: 疫苗有效期类别
     * @author: X
     * @updateTime: 2019/12/16 14:28
     */

   /* @PostMapping("overdueType")
    @ResponseBody
    public Object vaccineType(@RequestParam("overdueType") Integer overdueType) {
        PageInfo<TSysVaccineInfo> result = sysVaccineInfoService.listType(overdueType);
        //TableSplitResult<TSysVaccineInfo> result = new TableSplitResult<TSysVaccineInfo>(pageInfo.getPageNum(), pageInfo.getTotal(), pageInfo.getList());
        return result;
    }*/
}