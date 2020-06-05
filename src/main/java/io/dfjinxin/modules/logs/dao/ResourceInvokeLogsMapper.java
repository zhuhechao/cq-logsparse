package io.dfjinxin.modules.logs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjinxin.modules.logs.entity.ResourceInvokeLogsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 共享资源调用日志
 *
 * @author zhc
 * @date 2020-06-05 09:55:05
 */
@Mapper
@Repository
public interface ResourceInvokeLogsMapper extends BaseMapper<ResourceInvokeLogsEntity> {

}
