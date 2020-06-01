package io.dfjinxin.modules.logs.controller;

import io.dfjinxin.modules.logs.dto.ServiceUsageTemplateDto;
import io.dfjinxin.modules.logs.service.ServiceUsageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Desc:
 * @Author: z.h.c
 * @Date: 2020/4/27 21:24
 * @Version: 1.0
 */

@RestController
public class ServiceUsageTemplateController {

    @Autowired
    private ServiceUsageTemplateService serviceUsageTemplateService;

    @PostMapping("/usage/template/delete/{id}")
    Boolean delete(@PathVariable("id") Long id) {
        return this.serviceUsageTemplateService.delete(id);

    }

    @GetMapping("/usage/template/getList")
    List<ServiceUsageTemplateDto> getList() {
        return this.serviceUsageTemplateService.getList();
    }

}
