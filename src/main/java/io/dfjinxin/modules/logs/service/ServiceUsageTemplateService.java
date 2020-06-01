package io.dfjinxin.modules.logs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjinxin.modules.logs.dto.ServiceUsageTemplateDto;
import io.dfjinxin.modules.logs.entity.ServiceUsageTemplateEntity;

import java.util.List;

/**
 * @Desc:
 * @Author: z.h.c
 * @Date: 2020/4/27 21:26
 * @Version: 1.0
 */
public interface ServiceUsageTemplateService extends IService<ServiceUsageTemplateEntity> {

//    Boolean saveAndUpdate(ServiceUsageTemplateDto reqDto);

    List<ServiceUsageTemplateDto> getList();

    Boolean delete(Long id);

//    ServiceUsageDescDto buildServiceUsageDesc(ServiceQueryDto serviceQueryDto);
}
