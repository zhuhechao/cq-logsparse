package io.dfjinxin.modules.logs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjinxin.modules.logs.entity.SepUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录记录以日志文件的方式记录，数据库中只存最后次登录的时间。
 *
 * @author zhc
 *
 * @date 2020-06-02 16:22:29
 */
@Mapper
public interface SepUserMapper extends BaseMapper<SepUserEntity> {

}
