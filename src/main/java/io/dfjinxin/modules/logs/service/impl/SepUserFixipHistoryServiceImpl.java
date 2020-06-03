package io.dfjinxin.modules.logs.service.impl;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjinxin.modules.logs.dao.SepUserFixipHistoryMapper;
import io.dfjinxin.modules.logs.entity.SepUserFixipHistoryEntity;
import io.dfjinxin.modules.logs.service.SepUserFixipHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SepUserFixipHistoryServiceImpl extends ServiceImpl<SepUserFixipHistoryMapper, SepUserFixipHistoryEntity> implements SepUserFixipHistoryService {

    @Autowired
    private SepUserFixipHistoryMapper sepUserFixipHistoryMapper;

    @Override
    public SepUserFixipHistoryEntity getEntityByFixIp(String fixIp) {
        return this.sepUserFixipHistoryMapper.getEntityByFixIp(fixIp);
    }
}
