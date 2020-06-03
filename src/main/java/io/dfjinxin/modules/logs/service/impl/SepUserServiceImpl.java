package io.dfjinxin.modules.logs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjinxin.modules.logs.dao.SepUserMapper;
import io.dfjinxin.modules.logs.entity.SepUserEntity;
import io.dfjinxin.modules.logs.service.SepUserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SepUserServiceImpl extends ServiceImpl<SepUserMapper, SepUserEntity> implements SepUserService {

    @Override
    public SepUserEntity getEntityByUserId(Long userId) {
        List<SepUserEntity> list = this.lambdaQuery().eq(SepUserEntity::getUserId, userId).list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
