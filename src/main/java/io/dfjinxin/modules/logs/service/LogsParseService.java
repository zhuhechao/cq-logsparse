package io.dfjinxin.modules.logs.service;

/**
 * @Desc:
 * @Author: z.h.c
 * @Date: 2020/6/2 16:12
 * @Version: 1.0
 */
public interface LogsParseService {

    void insertHive(String ip, String date, String serviceCode);
}
