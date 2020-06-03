package io.dfjinxin.modules.logs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjinxin.modules.logs.entity.SepOrgEntity;

/**
 * 机构信息
 *
 * @author zhc
 * @date 2020-06-02 15:54:12
 */
public interface SepOrgService extends IService<SepOrgEntity> {
    SepOrgEntity getEntity(Long orgid);
}

