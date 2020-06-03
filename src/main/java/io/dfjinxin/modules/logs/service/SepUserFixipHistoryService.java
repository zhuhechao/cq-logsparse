package io.dfjinxin.modules.logs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjinxin.modules.logs.entity.SepUserFixipHistoryEntity;

import java.util.Map;

/**
 * 用户备案ip历史记录
 *
 * @author zhc
 *
 * @date 2020-06-02 16:01:00
 */
public interface SepUserFixipHistoryService extends IService<SepUserFixipHistoryEntity> {
    SepUserFixipHistoryEntity getEntityByFixIp(String fixIp);
}

