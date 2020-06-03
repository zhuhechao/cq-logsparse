package io.dfjinxin.modules.logs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjinxin.modules.logs.dao.SepOrgMapper;
import io.dfjinxin.modules.logs.entity.SepOrgEntity;
import io.dfjinxin.modules.logs.service.SepOrgService;
import org.springframework.stereotype.Service;


@Service
public class SepOrgServiceImpl extends ServiceImpl<SepOrgMapper, SepOrgEntity> implements SepOrgService {

    @Override
    public SepOrgEntity getEntity(Long orgid) {
        return this.baseMapper.selectById(orgid);
    }
}
