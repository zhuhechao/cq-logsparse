package io.dfjinxin.modules.logs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc:
 * @Author: z.h.c
 * @Date: 2020/6/5 10:34
 * @Version: 1.0
 */

@TableName("resource_invoke_logs")
@Data
public class ResourceInvokeLogsEntity implements Serializable {
    private static final long serialVersionUID = 1709998766L;

    /**
     * logId
     */
    @TableId(type = IdType.AUTO)
    private Long logId;
    /**
     * 调用方用户id
     */
    private Long invokerUserId;
    /**
     * 调用方ip
     */
    private String invokerIp;
    /**
     * 调用方所属部门名称
     */
    private String invokerOrgName;
    /**
     * 调用方调用服务时间
     */
    private Date invokeDate;
    /**
     * 调用方用户名称
     */
    private String invokerUserName;
    /**
     * 资源提供方所属部门名称
     */
    private String resourcerOrgName;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 路由地址
     */
    private String routeUrl;

    private String requestMethod;

    /**
     * http status code
     */
    private Integer responseCode;

    /**
     * 服务编码
     */
    private String serviceCode;
    /**
     * 资源ID(di_dataitem.dataitem_id)
     */
    private Long dataitemId;
    /**
     * 创建时间
     */
    private Date createTime;

}
