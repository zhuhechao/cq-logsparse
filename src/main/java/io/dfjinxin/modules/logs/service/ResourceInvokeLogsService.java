package io.dfjinxin.modules.logs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjinxin.modules.logs.entity.ResourceInvokeLogsEntity;

import java.util.List;

/**
 * 共享资源调用日志
 *
 * @author zhc
 * @date 2020-06-05 09:55:05
 */
public interface ResourceInvokeLogsService extends IService<ResourceInvokeLogsEntity> {
    void insertBatchByList(List<ResourceInvokeLogsEntity> logsEntityList);
}

