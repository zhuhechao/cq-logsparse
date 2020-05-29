package io.dfjinxin.common.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Desc:
 * @Author: z.h.c
 * @Date: 2019/9/17 13:52
 * @Version: 1.0
 */
@Data
public class CountAndProvinceDto {

    private Integer statAreaId;

    private String statAreaName;

    private List<Map<String,Object>> indexNameList;
}
