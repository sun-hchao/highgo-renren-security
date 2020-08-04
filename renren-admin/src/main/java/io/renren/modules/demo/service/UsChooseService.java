package io.renren.modules.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.demo.entity.UsChooseEntity;

import java.util.List;
import java.util.Map;

/**
 * 人员信息
 *
 * @author shc
 * @email sunlightcs@gmail.com
 * @date 2019-12-02 13:19:54
 */
public interface UsChooseService extends IService<UsChooseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<UsChooseEntity> search();
}

