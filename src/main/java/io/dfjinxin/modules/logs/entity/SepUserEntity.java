package io.dfjinxin.modules.logs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 登录记录以日志文件的方式记录，数据库中只存最后次登录的时间。
 *
 * @author zhc
 *
 * @date 2020-06-02 16:22:29
 */
@Data
@TableName("sep_user")
public class SepUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * USER_ID
	 */
	@TableId
	private Long userId;
	/**
	 * 登录名，全表唯一
	 */
	private String userName;
	/**
	 * 加密后的密文密码
	 */
	private String password;
	/**
	 * 所属机构，每个用户必须都属于某个机构（除了超级管理员）
	 */
	private Long orgId;
	/**
	 * 数字越大级别越大 通过字典表【BASECODE（USER_LEVEL）+CODENUM】进行查询
	 */
	private Long userLevel;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String mobileNo;
	/**
	 * 办公电话
	 */
	private String officeNo;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 出生日期
	 */
	private Date birthDate;
	/**
	 * 性别 通过字典表【BASECODE（GENDER）+CODENUM】进行查询
	 */
	private Long gender;
	/**
	 * 家庭地址
	 */
	private String address;
	/**
	 * 身份证号
	 */
	private String cardNo;
	/**
	 * 最后修改时间
	 */
	private Date lastModytime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态 通过字典表【BASECODE（USER_STATUS）+CODENUM】进行查询
	 */
	private Long status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 固定IP，多个IP使用英文逗号连接
	 */
	private String fixIp;
	/**
	 * 相关附件
	 */
	private String affixKey;
	/**
	 * 是否已备案，默认否 通过字典表【BASECODE（NORMAL_BOOLEAN）+CODENUM】进行查询
	 */
	private Long hasFile;

}
