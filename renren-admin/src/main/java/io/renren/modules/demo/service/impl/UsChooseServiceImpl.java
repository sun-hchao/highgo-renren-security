package io.renren.modules.demo.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.demo.dao.UsChooseDao;
import io.renren.modules.demo.entity.UsChooseEntity;
import io.renren.modules.demo.service.UsChooseService;


@Service("usChooseService")
public class UsChooseServiceImpl extends ServiceImpl<UsChooseDao, UsChooseEntity> implements UsChooseService {

    @Autowired
    UsChooseDao usChooseDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String username = (String)params.get("username");

        IPage<UsChooseEntity> page = this.page(
                new Query<UsChooseEntity>().getPage(params),
                new QueryWrapper<UsChooseEntity>()
                        .like(StringUtils.isNotBlank(username),"name", username)
        );

        return new PageUtils(page);
    }

    @Override
    public List<UsChooseEntity> search() {
        return usChooseDao.search();
    }

}
