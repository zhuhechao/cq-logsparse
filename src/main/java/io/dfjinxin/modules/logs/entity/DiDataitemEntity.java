package io.dfjinxin.modules.logs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 资源目录
 *
 * @author zhc
 * @date 2020-06-02 15:48:21
 */
@Data
@TableName("di_dataitem")
public class DiDataitemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据项ID
	 */
	@TableId
	private Long dataitemId;
	/**
	 * 信息资源代码，不能为空
	 */
	private String code;
	/**
	 * 目录编码，全局不能重复
	 */
	private String dataitemCode;
	/**
	 * 信息资源名称，不能为空
	 */
	private String name;
	/**
	 * 信息资源提供方，不能为空
	 */
	private Long orgId;
	/**
	 * 提供方内部部门
	 */
	private String innerOrg;
	/**
	 * 绑定角色
	 */
	private String roleCode;
	/**
	 * 信息资源格式分类，不能为空 通过字典表【BASECODE（CQNORM_DATAITEM_KIND）+CODENUM】进行查询
	 */
	private Long dataKind;
	/**
	 * 信息资源格式类型，不能为空 通过字典表【BASECODE（CQNORM_DATAITEM_TYPE）+CODENUM】进行查询
	 */
	private Long dataType;
	/**
	 * 其他类型资源格式描述
	 */
	private String dataKindDes;
	/**
	 * 数据来源
	 */
	private String dataSource;
	/**
	 * 资源提供方代码
	 */
	private String dataSourceCode;
	/**
	 * 更新周期 通过字典表【BASECODE（DATAITEM_HIT_FREQUENCY）+CODENUM】进行查询
	 */
	private Long hitFrequency;
	/**
	 * 信息资源摘要
	 */
	private String description;
	/**
	 * 共享方式类型，默认是数据库 通过字典表【BASECODE（CQNORM_SHARE_TYPE）+CODENUM】进行查询
	 */
	private Long shareWayType;
	/**
	 * 共享方式分类，默认是共享平台方式 通过字典表【BASECODE（CQNORM_SHARE_KIND）+CODENUM】进行查询
	 */
	private Long shareWay;
	/**
	 * 关联及类目名称
	 */
	private String categoryName;
	/**
	 * 访问量
	 */
	private Long visitCount;
	/**
	 * 状态 通过字典表【BASECODE（DATAITEM_STATUS）+CODENUM】进行查询
	 */
	private Long status;
	/**
	 * 是否有更新，默认：是 通过字典表【BASECODE（NORMAL_BOOLEAN）+CODENUM】进行查询
	 */
	private Long hasUpdate;
	/**
	 * 发布日期
	 */
	private Date publishDate;
	/**
	 * 最后修改时间
	 */
	private Date lastModytime;
	/**
	 * 是否公有，默认是否 通过字典表【BASECODE（NORMAL_BOOLEAN）+CODENUM】进行查询
	 */
	private Long openness;
	/**
	 * 接入方式 通过字典表【BASECODE（CQNORM_SHARE_TYPE）+CODENUM】进行查询
	 */
	private Long joinWay;
	/**
	 * 关键字，多个关键字间用英文分号连接
	 */
	private String keyWords;
	/**
	 * 是否落地，默认否 通过字典表【BASECODE（NORMAL_BOOLEAN）+CODENUM】进行查询
	 */
	private Long locally;
	/**
	 * 信息资源代码（标准录入）
	 */
	private String innerCode;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 目录来源 通过字典表【BASECODE（SEP_DATAITEM_SOURCE）+CODENUM】进行查询
	 */
	private Long dataitemSource;
	/**
	 * 拼音
	 */
	private String pinyin;
	/**
	 * 所属目录
	 */
	private Long catalogId;
	/**
	 * 是否虚目录，默认否 通过字典表【BASECODE（NORMAL_BOOLEAN）+CODENUM】进行查询
	 */
	private Long fake;
	/**
	 * 是否允许数据下载，默认不允许 通过字典表【BASECODE（NORMAL_BOOLEAN）+CODENUM】进行查询
	 */
	private Long allowDown;
	/**
	 * 标准制定日期，标准文档上的“发布日期”
	 */
	private String stanCompDate;
	/**
	 *
	 */
	private Long pyLocally;
	/**
	 *
	 */
	private String totalRecordsDescription;
	/**
	 * 考核状态，默认考核 通过字典表【BASECODE（SEP_ASSESS_STATUS）+CODENUM】进行查询
	 */
	private Long byAssess;

}
