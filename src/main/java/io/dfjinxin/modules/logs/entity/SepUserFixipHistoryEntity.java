package io.dfjinxin.modules.logs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户备案ip历史记录
 *
 * @author zhc
 *
 * @date 2020-06-02 16:01:00
 */
@Data
@TableName("sep_user_fixip_history")
public class SepUserFixipHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 固定IP，多个IP使用英文逗号连接
	 */
	private String fixIp;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建用户
	 */
	private Long createUser;

}
