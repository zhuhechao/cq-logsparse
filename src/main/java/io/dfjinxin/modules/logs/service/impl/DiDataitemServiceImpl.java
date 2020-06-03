package io.dfjinxin.modules.logs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjinxin.modules.logs.dao.DiDataitemMapper;
import io.dfjinxin.modules.logs.entity.DiDataitemEntity;
import io.dfjinxin.modules.logs.service.DiDataitemService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DiDataitemServiceImpl extends ServiceImpl<DiDataitemMapper, DiDataitemEntity> implements DiDataitemService {


    @Override
    public DiDataitemEntity getEntityByDataitemCode(String serverCode) {
        List<DiDataitemEntity> list = this.lambdaQuery().eq(DiDataitemEntity::getDataitemCode, serverCode).list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
