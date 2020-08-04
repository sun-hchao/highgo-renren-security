package io.renren.modules.demo.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

import io.renren.modules.demo.entity.UsChooseEntity;
import io.renren.modules.demo.service.UsChooseService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 人员信息
 *
 * @author shc
 * @email sunlightcs@gmail.com
 * @date 2019-12-02 13:19:54
 */
@RestController
@RequestMapping("demo/uschoose")
public class UsChooseController {
    @Autowired
    private UsChooseService usChooseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("demo:uschoose:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = usChooseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("demo:uschoose:info")
    public R info(@PathVariable("id") Long id){
        UsChooseEntity usChoose = usChooseService.getById(id);

        return R.ok().put("usChoose", usChoose);
    }

    /**
     * 保存
     */
    @SysLog("保存人员")
    @RequestMapping("/save")
    @RequiresPermissions("demo:uschoose:save")
    public R save(@RequestBody UsChooseEntity usChoose){

        usChoose.setCrtime(new Date());
        usChooseService.save(usChoose);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改人员")
    @RequestMapping("/update")
    @RequiresPermissions("demo:uschoose:update")
    public R update(@RequestBody UsChooseEntity usChoose){
        ValidatorUtils.validateEntity(usChoose);
        usChoose.setUptime(new Date());
        usChooseService.updateById(usChoose);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除人员")
    @RequestMapping("/delete")
    @RequiresPermissions("demo:uschoose:delete")
    public R delete(@RequestBody Long[] ids){
        usChooseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    /*自定义查询*/
    @RequestMapping("/search")
    public List<UsChooseEntity> search(){
        return usChooseService.search();
    }

}
