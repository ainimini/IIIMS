package com.fc.test.controller.admin;

import com.fc.test.model.auto.*;
import com.fc.test.service.SysProvinceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.common.log.Log;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2019/11/29
 * @Version 1.0
 **/
@Controller
@RequestMapping("/ConsumerController")
public class ConsumerController extends BaseController {

    @Autowired
    private SysProvinceService sysProvinceService;

    private String prefix = "admin/consumer";

    /**
     * 展示跳转页面
     * @param model
     * @return
     * @author fuce
     * @Date 2019年11月11日 下午4:14:34
     */
    @GetMapping("/view")
    @RequiresPermissions("system:consumer:view")
    public String view(ModelMap model)
    {
        String str="用户";
        setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/list";
    }


    /**
     * list集合
     * @param tablepar
     * @param searchTxt
     * @return
     * @author fuce
     * @Date 2019年11月11日 下午4:14:40
     */
    @PostMapping("/list")
    @RequiresPermissions("system:consumer:list")
    @ResponseBody
    public Object list(Tablepar tablepar, String searchTxt, String searchText){
        PageInfo<TSysConsumer> page=consumerService.list(tablepar, searchTxt, searchText);
        TableSplitResult<TSysConsumer> result=new TableSplitResult<TSysConsumer>(page.getPageNum(), page.getTotal(), page.getList());
        return  result;
    }
    /**
     * 新增跳转
     * @param modelMap
     * @return
     * @author fuce
     * @Date 2019年11月11日 下午4:14:51
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        //添加角色列表
       // List<TsysRole> tsysRoleList=sysRoleService.queryList();
        //地区三级联动
        modelMap.addAttribute("provinceList",sysProvinceService.selectByExample(new SysProvinceExample()));
       // modelMap.put("tsysRoleList",tsysRoleList);
        return prefix + "/add";
    }
    /**
     * 新增保存
     * @param user
     * @param model
     * @param roles
     * @return
     * @author fuce
     * @Date 2019年11月11日 下午4:14:57
     */
    @Log(title = "用户新增", action = "111")
    @PostMapping("/add")
    @RequiresPermissions("system:consumer:add")
    @ResponseBody
    public AjaxResult add(TSysConsumer tSysConsumer, Model model) {
        int b = consumerService.insertSelective(tSysConsumer);
        if (b > 0) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @PostMapping("/remove")
    @RequiresPermissions("system:user:remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        int b=consumerService.deleteByPrimaryKey(ids);
        if(b>0){
            return success();
        }else{
            return error();
        }
    }

    /**
     * 修改用户
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        //通过id查询所有接种疫苗用户信息
        mmap.put("TSysConsumer", consumerService.selectByPrimaryKey(id));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:consumer:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TSysConsumer tSysConsumer)
    {
        return toAjax(consumerService.updateConsumer(tSysConsumer));
    }

    /**
     * 修改用户密码
     * @param id
     * @param mmap
     * @return
     */
    @GetMapping("/editPwd/{id}")
    public String editPwd(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("TSysConsumer", consumerService.selectByPrimaryKey(id));
        return prefix + "/editPwd";
    }
    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:consumer:editPwd")
    @PostMapping("/editPwd")
    @ResponseBody
    public AjaxResult editPwdSave(TSysConsumer tSysConsumer)
    {
        return toAjax(consumerService.updateUserPassword(tSysConsumer));
    }

    /**
     * 检查用户登录名称
     * @param tSysConsumer
     * @return
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public int checkLoginNameUnique(TSysConsumer tSysConsumer){
        int b=consumerService.checkLoginNameUnique(tSysConsumer);
        if(b>0){
            return 1;
        }else{
            return 0;
        }
    }
}
