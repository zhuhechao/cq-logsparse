package io.dfjinxin.modules.logs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjinxin.modules.logs.entity.SepUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 登录记录以日志文件的方式记录，数据库中只存最后次登录的时间。
 *
 * @author zhc
 * @date 2020-06-02 16:22:29
 */
@Mapper
@Repository
public interface SepUserMapper extends BaseMapper<SepUserEntity> {

    @Select("SELECT *\n" +
            "FROM sep_user\n" +
            "WHERE org_id !='' and fix_ip !='' and create_time is not null and find_in_set(#{fixIp}, fix_ip) order by create_time limit 1;")
    SepUserEntity getEntityByFixIp(@Param("fixIp") String fixIp);
}
