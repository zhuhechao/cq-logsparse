package io.dfjinxin.modules.logs.service.impl;

import io.dfjinxin.common.utils.DateUtils;
import io.dfjinxin.modules.hive.service.HiveService;
import io.dfjinxin.modules.logs.entity.*;
import io.dfjinxin.modules.logs.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;

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
    public void insertLog(String ip, String date, String serviceCode) {

        /*log.info("ip:{},date:{},serviceCode:{}", ip, date, serviceCode);

        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(date) || StringUtils.isEmpty(serviceCode)) {
            return;
        }

        SepUserFixipHistoryEntity entityByFixIp = sepUserFixipHistoryService.getEntityByFixIp(ip);
        Assert.notNull(entityByFixIp, "*** sep_user_fixip_history不存在:" + ip);

        SepUserEntity entityUser = sepUserService.getEntityByUserId(entityByFixIp.getUserId());
        Assert.notNull(entityUser, "*** sep_user不存在:" + entityByFixIp.getUserId());

        SepOrgEntity userOrgEntity = sepOrgService.getEntity(entityUser.getOrgId());
        Assert.notNull(userOrgEntity, "***sep_org不存在调用方用户机构:" + entityUser.getOrgId());

        DiDataitemEntity diDataitemEntity = diDataitemService.getEntityByDataitemCode(serviceCode);
        Assert.notNull(diDataitemEntity, "*** di_dataitem不存在:" + serviceCode);

        SepOrgEntity sepOrgEntity = sepOrgService.getEntity(diDataitemEntity.getOrgId());
        Assert.notNull(sepOrgEntity, "*** sep_org不存在资源提供方机构:" + diDataitemEntity.getOrgId());*/

        /*StringBuilder sb = new StringBuilder("insert into resource_invoke_logs " +
                "(log_id,invoker_ip,invoker_user_id,invoker_org_name,invoker_user_name," +
                "invoke_date," +
                "resourcer_org_name,resource_name,dataitem_id,create_time) values (");


        final String flag = "',";
        final String flag2 = "'";
        sb.append(System.currentTimeMillis()).append(",");
        sb.append(flag2).append(ip).append(flag);
        sb.append(entityUser.getUserId()).append(",");
        sb.append(flag2).append(userOrgEntity.getName()).append(flag);
        sb.append(flag2).append(entityUser.getName()).append(flag);
        sb.append(flag2).append(date).append(flag);
        sb.append(flag2).append(sepOrgEntity.getName()).append(flag);
        sb.append(flag2).append(diDataitemEntity.getName()).append(flag);
        sb.append(diDataitemEntity.getDataitemId()).append(",");
        sb.append(flag2).append(DateUtils.getCurrentYMDHMSStr()).append("'");
        sb.append(" );");
        log.info("hive sql:{}", sb.toString());
        hiveService.insert(sb.toString());
        */
//        List list = Lists.newArrayList();
//        resourceInvokeLogsService.saveBatch(list);
//        long endTime = System.currentTimeMillis();
//        log.info("入Hive用时:{}s", (endTime - startTime) / 1000);
    }

    @Override
    public ResourceInvokeLogsEntity queryDataByParams(String ip, String date, String serviceCode, String routeUrl, String reqMethod) {
        log.info("ip:{},date:{},serviceCode:{},routeUrl:{},reqMethod:{}", ip, date, serviceCode, routeUrl, reqMethod);

        if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(date) || StringUtils.isEmpty(serviceCode)) {
            return null;
        }

//        SepUserFixipHistoryEntity entityByFixIp = sepUserFixipHistoryService.getEntityByFixIp(ip);
//        Assert.notNull(entityByFixIp, "*** sep_user_fixip_history不存在:" + ip);

        SepUserEntity entityUser = sepUserService.getEntityByFixIp(ip);
        Assert.notNull(entityUser, "*** sep_user不存在:" + ip);

        SepOrgEntity userOrgEntity = sepOrgService.getEntity(entityUser.getOrgId());
        Assert.notNull(userOrgEntity, "***sep_org不存在调用方用户机构:" + entityUser.getOrgId());

        DiDataitemEntity diDataitemEntity = diDataitemService.getEntityByDataitemCode(serviceCode);
        Assert.notNull(diDataitemEntity, "*** di_dataitem不存在:" + serviceCode);

        SepOrgEntity sepOrgEntity = sepOrgService.getEntity(diDataitemEntity.getOrgId());
        Assert.notNull(sepOrgEntity, "*** sep_org不存在资源提供方机构:" + diDataitemEntity.getOrgId());

        ResourceInvokeLogsEntity entity = new ResourceInvokeLogsEntity();
        entity.setInvokerIp(ip);
        entity.setInvokeDate(DateUtils.conver8GMT2Date(date));
        entity.setInvokerOrgName(userOrgEntity.getName());
        entity.setInvokerUserName(entityUser.getName());
        entity.setInvokerUserId(entityUser.getUserId());
        entity.setResourceName(diDataitemEntity.getName());
        entity.setResourcerOrgName(sepOrgEntity.getName());
        entity.setDataitemId(diDataitemEntity.getDataitemId());
        entity.setServiceCode(serviceCode);
        entity.setRouteUrl(routeUrl);
        entity.setRequestMethod(reqMethod);
        entity.setCreateTime(new Date());
        return entity;
    }
}
