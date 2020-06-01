package io.dfjinxin.modules.logs.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Desc:
 * @Author: z.h.c
 * @Date: 2020/4/27 21:12
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class ServiceUsageTemplateDto {

    /**
     *
     */
    private Long id;
    /**
     * 父级id(没有父级则为0)
     */
    private Long parentId;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板类型 1:标准服务
     */
    private String templateType;
    /**
     * 模板内容
     */
    private String templateText;
    /**
     * 创建者
     */
    private Long creator;
    /**
     * 修改者
     */
    private Long modifier;
    /**
     * 修改时间
     */
    private LocalDateTime modifyTm;
    /**
     * 创建时间
     */
    private LocalDateTime createTm;
    /**
     * 是否能够修改
     */
    private Boolean isCanModify;
    /**
     * 是否能够删除
     */
    private Boolean isCanDelete;
    /**
     * 是否删除
     */
    private Boolean delStatus;
}
