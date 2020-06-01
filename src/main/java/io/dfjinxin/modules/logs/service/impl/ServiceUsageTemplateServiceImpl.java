package io.dfjinxin.modules.logs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjinxin.datasource.annotation.DataSource;
import io.dfjinxin.modules.logs.dao.ServiceUsageTemplateMapper;
import io.dfjinxin.modules.logs.dto.ServiceUsageTemplateDto;
import io.dfjinxin.modules.logs.entity.ServiceUsageTemplateEntity;
import io.dfjinxin.modules.logs.service.ServiceUsageTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: z.h.c
 * @Date: 2020/4/28 9:34
 * @Version: 1.0
 */
@Service
@Slf4j
@DataSource("RspDataSource")
public class ServiceUsageTemplateServiceImpl extends ServiceImpl<ServiceUsageTemplateMapper, ServiceUsageTemplateEntity>
        implements ServiceUsageTemplateService {



    @Override
    public List<ServiceUsageTemplateDto> getList() {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper
        return this.lambdaQuery()
                .eq(ServiceUsageTemplateEntity::getDelStatus, Boolean.FALSE)
                .list().stream().map(ServiceUsageTemplateEntity::entity2Dto)
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        return this.lambdaUpdate()
                .eq(ServiceUsageTemplateEntity::getId, id)
                .eq(ServiceUsageTemplateEntity::getDelStatus, Boolean.FALSE)
                .set(ServiceUsageTemplateEntity::getDelStatus, Boolean.TRUE)
                .set(ServiceUsageTemplateEntity::getModifyTm, LocalDateTime.now())
                .update();
    }

}
