package io.dfjinxin.modules.logs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjinxin.modules.logs.entity.SepUserEntity;

/**
 * 登录记录以日志文件的方式记录，数据库中只存最后次登录的时间。
 *
 * @author zhc
 *
 * @date 2020-06-02 16:22:29
 */
public interface SepUserService extends IService<SepUserEntity> {

    SepUserEntity getEntityByUserId(Long userId);
    SepUserEntity getEntityByFixIp(String fixIp);
}

