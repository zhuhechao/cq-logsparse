package io.dfjinxin.modules.logs.service.impl;

import io.dfjinxin.modules.hive.service.HiveService;
import io.dfjinxin.modules.logs.entity.DiDataitemEntity;
import io.dfjinxin.modules.logs.entity.SepOrgEntity;
import io.dfjinxin.modules.logs.entity.SepUserEntity;
import io.dfjinxin.modules.logs.entity.SepUserFixipHistoryEntity;
import io.dfjinxin.modules.logs.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desc:
 * @Author: z.h.c
 * @Date: 2020/6/2 16:13
 * @Version: 1.0
 */
@Service
@Slf4j
public class LogsParseServiceImpl implements LogsParseService {


    @Autowired
    private SepOrgService sepOrgService;
    @Autowired
    private DiDataitemService diDataitemService;
    @Autowired
    private SepUserFixipHistoryService sepUserFixipHistoryService;
    @Autowired
    private SepUserService sepUserService;

    @Autowired
    private HiveService hiveService;

    @Override
    public void insertHive(String ip, String date, String serviceCode) {
        log.info("ip:{},date:{},serviceCode:{}", ip, date, serviceCode);
        SepUserFixipHistoryEntity entityByFixIp = sepUserFixipHistoryService.getEntityByFixIp(ip);
        SepUserEntity entityUser = sepUserService.getEntityByUserId(entityByFixIp.getUserId());
        log.info("entityByFixIp:{}", entityByFixIp);
        SepOrgEntity userOrgEntity = sepOrgService.getEntity(entityUser.getOrgId());
        log.info("userOrgEntity:{}", userOrgEntity);

        DiDataitemEntity diDataitemEntity = diDataitemService.getEntityByDataitemCode(serviceCode);
        SepOrgEntity sepOrgEntity = sepOrgService.getEntity(diDataitemEntity.getOrgId());
//        hiveService.insert("");
    }
}
