package io.dfjinxin.modules.logs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 机构信息
 *
 * @author zhc
 * @date 2020-06-02 15:54:12
 */
@Data
@TableName("sep_org")
public class SepOrgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ORG_ID
	 */
	@TableId
	private Long orgId;
	/**
	 * 机构代码，全表唯一
	 */
	private String code;
	/**
	 * 上级机构
	 */
	private Long superOrgid;
	/**
	 * 组织类别 通过字典表【BASECODE（ORG_TYPE）+CODENUM】进行查询
	 */
	private Long type;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 联系人
	 */
	private String linkman;
	/**
	 * 联系电话
	 */
	private String telNo;
	/**
	 * 法人
	 */
	private String legalPerson;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 注册登记号
	 */
	private String checkNum;
	/**
	 * 地图范围
	 */
	private String mapScope;
	/**
	 * 描述
	 */
//	private String describe;
	/**
	 * 最后修改时间
	 */
	private Date lastModytime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 部门排序，升序
	 */
	private Long orderIndex;

}
