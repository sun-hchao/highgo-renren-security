package io.renren.modules.demo.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.annotation.SysLog;
import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.demo.entity.EmployeeEntity;
import io.renren.modules.demo.service.EmployeeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * ${comments}
 *
 * @author shc
 * @email sunlightcs@gmail.com
 * @date 2019-12-02 17:36:33
 */
@RestController
@RequestMapping("demo/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("demo:employee:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = employeeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("demo:employee:info")
    public R info(@PathVariable("id") Integer id){
        EmployeeEntity employee = employeeService.getById(id);

        return R.ok().put("employee", employee);
    }

    /**
     * 保存
     */
    @SysLog("employee测试保存")
    @RequestMapping("/save")
    @RequiresPermissions("demo:employee:save")
    public R save(@RequestBody EmployeeEntity employee){
        employeeService.save(employee);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("employee测试修改")
    @RequestMapping("/update")
    @RequiresPermissions("demo:employee:update")
    public R update(@RequestBody EmployeeEntity employee){
        ValidatorUtils.validateEntity(employee);
        employeeService.updateById(employee);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("employee测试删除")
    @RequestMapping("/delete")
    @RequiresPermissions("demo:employee:delete")
    public R delete(@RequestBody Integer[] ids){
        employeeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
