package io.dfjinxin.modules.logs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.dfjinxin.modules.logs.dto.ServiceUsageTemplateDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 服务使用说明模板
 *
 * @author z.h.c
 * @email z.h.c@126.com
 * @date 2020-04-27 21:18:53
 */
@Data
@Accessors(chain = true)
@TableName("service_usage_template")
public class ServiceUsageTemplateEntity implements Serializable {
    private static final long serialVersionUID = 1887662L;

    /**
     *
     */
    @TableId(type = IdType.INPUT)
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

    public static Optional<ServiceUsageTemplateDto> entity2Dto(ServiceUsageTemplateEntity entry) {
        ServiceUsageTemplateDto result = new ServiceUsageTemplateDto();
        if (entry != null) {
            BeanUtils.copyProperties(entry, result);
        }
        return Optional.of(result);
    }

}
