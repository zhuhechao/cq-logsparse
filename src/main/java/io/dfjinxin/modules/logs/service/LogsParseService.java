package io.dfjinxin.modules.logs.service;

import io.dfjinxin.modules.logs.entity.ResourceInvokeLogsEntity;

/**
 * @Desc:
 * @Author: z.h.c
 * @Date: 2020/6/2 16:12
 * @Version: 1.0
 */
public interface LogsParseService {

    void insertLog(String ip, String date, String serviceCode);

    ResourceInvokeLogsEntity queryDataByParams(String ip, String date, String serviceCode);
}
