package io.renren.modules.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.demo.entity.EmployeeEntity;

import java.util.Map;

/**
 * ${comments}
 *
 * @author shc
 * @email sunlightcs@gmail.com
 * @date 2019-12-02 17:36:33
 */
public interface EmployeeService extends IService<EmployeeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

