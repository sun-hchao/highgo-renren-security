package io.renren.modules.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 人员信息
 * 
 * @author shc
 * @email sunlightcs@gmail.com
 * @date 2019-12-02 13:19:54
 */
@Data
@TableName("us_choose")
public class UsChooseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别：1男、0女
	 */
	private Integer sex;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 创建日期
	 */
	private Date crtime;
	/**
	 * 修改日期
	 */
	private Date uptime;
	/**
	 * 手机
	 */
	private String phone;

}
