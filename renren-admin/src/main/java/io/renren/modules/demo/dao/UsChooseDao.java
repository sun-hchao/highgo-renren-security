package io.renren.modules.demo.dao;

import io.renren.modules.demo.entity.UsChooseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 人员信息
 * 
 * @author shc
 * @email sunlightcs@gmail.com
 * @date 2019-12-02 13:19:54
 */
@Mapper
public interface UsChooseDao extends BaseMapper<UsChooseEntity> {
   List<UsChooseEntity> search();
}
