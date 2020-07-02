package io.dfjinxin.modules.logs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjinxin.common.utils.DateUtils;
import io.dfjinxin.modules.logs.dao.ResourceInvokeLogsMapper;
import io.dfjinxin.modules.logs.entity.ResourceInvokeLogsEntity;
import io.dfjinxin.modules.logs.service.ResourceInvokeLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ResourceInvokeLogsServiceImpl extends ServiceImpl<ResourceInvokeLogsMapper, ResourceInvokeLogsEntity> implements ResourceInvokeLogsService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatchByList(List<ResourceInvokeLogsEntity> logsEntityList) {

        log.info("数据入库################start######################");
        Date start = new Date();
        if (null != logsEntityList && logsEntityList.size() > 0) {
            int pointsDataLimit = 1000;//限制条数
            Integer size = logsEntityList.size();
            //判断是否有必要分批
            if (pointsDataLimit < size) {
                int part = size / pointsDataLimit;//分批数
                log.info("共有:" + size + "条数据待入库." + "分为:" + part + "批");
                for (int i = 0; i < part; i++) {
                    //80条
                    List<ResourceInvokeLogsEntity> logsEntityListPage = logsEntityList.subList(0, pointsDataLimit);
                    super.saveBatch(logsEntityListPage);
                    //剔除前指定条数的数据
                    logsEntityList.subList(0, pointsDataLimit).clear();
                }
                if (!logsEntityList.isEmpty()) {
                    super.saveBatch(logsEntityList);//表示最后剩下的数据
                }
            } else {
                super.saveBatch(logsEntityList);
            }
        }
        Date end = new Date();
        log.info("数据入库开始时间:{},结束时间{}", DateUtils.dateToStrYMDHMS(start), DateUtils.dateToStrYMDHMS(end));
        log.info("批量(insertBatchByList)入库用时:{}mins", TimeUnit.MILLISECONDS.toMinutes(end.getTime() - start.getTime()));
        log.info("数据入库################end######################");
    }
}
