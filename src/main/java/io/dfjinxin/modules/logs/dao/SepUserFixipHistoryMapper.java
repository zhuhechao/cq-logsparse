package io.dfjinxin.modules.logs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjinxin.modules.logs.entity.SepUserFixipHistoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 用户备案ip历史记录
 *
 * @author zhc
 *
 * @date 2020-06-02 16:01:00
 */
@Repository
@Mapper
public interface SepUserFixipHistoryMapper extends BaseMapper<SepUserFixipHistoryEntity> {


    @Select("SELECT *\n" +
            "FROM sep_user_fixip_history\n" +
            "WHERE find_in_set(#{fixIp}, fix_ip);")
    SepUserFixipHistoryEntity getEntityByFixIp(@Param("fixIp") String fixIp);
}
