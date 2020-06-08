package io.dfjinxin.modules.logs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjinxin.modules.logs.dao.ResourceInvokeLogsMapper;
import io.dfjinxin.modules.logs.entity.ResourceInvokeLogsEntity;
import io.dfjinxin.modules.logs.service.ResourceInvokeLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ResourceInvokeLogsServiceImpl extends ServiceImpl<ResourceInvokeLogsMapper, ResourceInvokeLogsEntity> implements ResourceInvokeLogsService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatchByList(List<ResourceInvokeLogsEntity> logsEntityList) {

        long starT = System.currentTimeMillis();
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
        long endT = System.currentTimeMillis();
        log.info("批量(insertBatchByList)入库用时:{}s", (endT - starT) / 1000);
    }
}