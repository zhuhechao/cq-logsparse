package io.dfjinxin.modules.logs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjinxin.modules.logs.entity.DiDataitemEntity;

/**
 * 资源目录
 *
 * @author zhc
 * @date 2020-06-02 15:48:21
 */
public interface DiDataitemService extends IService<DiDataitemEntity> {

    DiDataitemEntity getEntityByDataitemCode(String serverCode);
}

