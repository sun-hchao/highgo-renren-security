package io.renren.modules.demo.dao;

import io.renren.modules.demo.entity.EmployeeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${comments}
 * 
 * @author shc
 * @email sunlightcs@gmail.com
 * @date 2019-12-02 17:36:33
 */
@Mapper
public interface EmployeeDao extends BaseMapper<EmployeeEntity> {
	
}
