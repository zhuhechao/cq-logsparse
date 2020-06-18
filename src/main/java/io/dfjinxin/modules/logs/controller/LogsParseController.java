package io.dfjinxin.modules.logs.controller;

import io.dfjinxin.common.utils.DateUtils;
import io.dfjinxin.common.utils.R;
import io.dfjinxin.modules.logs.entity.ResourceInvokeLogsEntity;
import io.dfjinxin.modules.logs.service.LogsParseService;
import io.dfjinxin.modules.logs.service.ResourceInvokeLogsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 数据共享日志分析-日志文件处理
 * @Author: z.h.c
 * @Date: 2020/5/29 16:39
 * @Version: 1.0
 */

@RestController
@RequestMapping("parse/logs/")
@Api(tags = "数据共享日志分析-Api")
@Slf4j
public class LogsParseController {

    //日志文件路径
    @Value("${file.path}")
    private String path;

    @Autowired
    private LogsParseService logsParseService;

    @Autowired
    private
    ResourceInvokeLogsService resourceInvokeLogsService;

    /**
     * @Desc: 根据文件目录path、文件名称name（access-系统当前日期.log）解析文件，并落入hive库
     * @Param: [dateStr]
     * @Return: io.dfjinxin.common.utils.R
     * @Author: z.h.c
     * @Date: 2020/5/29 16:48
     */
    @PostMapping("doLogsFileParse/")
    @ApiOperation(value = "数据共享日志分析", notes = "根据文件目录&文件名称解析文件，并落入hive库")
    public R doLogsFileParse(@RequestBody(required = false) Map<String, String> reqMap) {

        String dateStr = "";
        if (reqMap != null && reqMap.containsKey("dateStr")) {
            dateStr = reqMap.getOrDefault("dateStr", DateUtils.getCurrentDayStr());
        } else {
            dateStr = DateUtils.getCurrentDayStr();
        }
        String fileName = "access_" + dateStr + ".log";
        log.info("数据共享日志文件分析,params- filepath:{},filename:{}", path, fileName);
        String fileLine = "";
        List<ResourceInvokeLogsEntity> list = new ArrayList<>();
        long startT = System.currentTimeMillis();
        try {
            FileInputStream inputStream = new FileInputStream(path + fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((fileLine = bufferedReader.readLine()) != null) {
                try {
                    ResourceInvokeLogsEntity logsEntity = this.subFileLineStrParse(fileLine);
                    if (logsEntity != null) list.add(logsEntity);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    continue;
                }
            }
            inputStream.close();
            bufferedReader.close();
        } catch (IOException e) {
            String errMsg = path + fileName + "文件不存在!";
            log.error(errMsg);
            return R.ok(errMsg);
        }
        long endT = System.currentTimeMillis();
        log.info("从文件:{},共解析:{}条记录,用时:{}s!", fileName, list.size(), (endT - startT) / 1000);
        this.resourceInvokeLogsService.insertBatchByList(list);
        return R.ok();
    }

    /**
     * @Desc: 解析读取到的文件行内容
     * @Param: [subFileLineStr]
     * @Return: void
     * @Author: z.h.c
     * @Date: 2020/5/29 17:45
     */

    /**
     * 23.52.0.9 - - [24/Apr/2020:18:40:28 +0800] "GET /RES_SFZGGWOH/jngl/56poInJfKmiNX2no1JRdW4w2TibZB5FrYyV5QlbShqU/getDataJson?pageNo=1&pageSize=20&search= HTTP/1.1" 404 203 "-" "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36" "-"
     * 23.52.0.9 - - [24/Apr/2020:18:40:37 +0800] "GET /services/RES_SFZGGWOH/jngl/56poInJfKmiNX2no1JRdW4w2TibZB5FrYyV5QlbShqU/getDataJson?pageNo=1&pageSize=20&search= HTTP/1.1" 200 1391 "-" "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36" "-"
     * 23.52.0.9 - - [28/Apr/2020:15:28:10 +0800] "\x03" 400 158 "-" "-" "-"
     * 23.52.0.10 - - [25/May/2020:14:55:16 +0800] "GET /services/RES_SFZGGWOH/jngl/56poInJfKmhR_LaZyB0jAe-KbMad4o6Z6NxM4KwvlUA/getDataJson?pageNo=1&pageSize=2&search= HTTP/1.1" 200 1390 "-" "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36" "-"
     * 23.52.0.10 - - [25/May/2020:14:55:16 +0800] "GET /favicon.ico HTTP/1.1" 404 203 "http://23.212.180.252:8070/services/RES_SFZGGWOH/jngl/56poInJfKmhR_LaZyB0jAe-KbMad4o6Z6NxM4KwvlUA/getDataJson?pageNo=1&pageSize=2&search=" "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36" "-"
     * 23.52.0.10 - - [27/May/2020:13:39:41 +0800] "GET / HTTP/1.1" 403 147 "-" "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko" "-"
     * 23.52.0.10 - - [27/May/2020:13:40:04 +0800] "GET / HTTP/1.1" 403 202 "-" "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/18.17763" "-"
     * 23.52.0.10 - - [27/May/2020:14:06:21 +0800] "GET / HTTP/1.1" 403 147 "-" "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko" "-"
     * 23.52.0.10 - - [27/May/2020:14:19:37 +0800] "GET / HTTP/1.1" 403 202 "-" "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/18.17763" "-"
     * 23.52.0.10 - - [27/May/2020:14:19:37 +0800] "GET /favicon.ico HTTP/1.1" 404 203 "-" "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/18.17763" "-"
     * 23.52.0.10 - - [27/May/2020:14:37:02 +0800] "GET / HTTP/1.1" 403 147 "-" "Mozilla/5.0 (Windows NT 10.0; Win64; x64; Trident/7.0; rv:11.0) like Gecko" "-"
     *
     * @param fileLineStr
     */
    private ResourceInvokeLogsEntity subFileLineStrParse(String fileLineStr) {
//        subFileLineStr = "23.52.0.9 - - [24/Apr/2020:18:40:37 +0800] \"GET /services/RES_SFZGGWOH/jngl/56poInJfKmiNX2no1JRdW4w2TibZB5FrYyV5QlbShqU/getDataJson?pageNo=1&pageSize=20&search= HTTP/1.1\" 200 1391 \"-\" \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36\" \"-\"";
//        if (fileLineStr.contains("/services/") && fileLineStr.contains("HTTP/1.1\" 200")) {
        if (fileLineStr.contains("/services/") && fileLineStr.contains("HTTP/1.1\"")) {
            String[] subFileLineStrArr = fileLineStr.split("\\?");
            String subFileLineStr = subFileLineStrArr[0];

            String ip = subFileLineStr.substring(0, subFileLineStr.indexOf("-")).trim();
            String date = subFileLineStr.substring(subFileLineStr.indexOf("[") + 1, subFileLineStr.indexOf("]")).trim();
            String[] serverUrlArr = subFileLineStr.split("]");
            String serverUrlStr = serverUrlArr[1];
            String[] serverPathArr = serverUrlStr.split("/");
            String serviceCode = serverPathArr[2];
            String requestMethod = serverPathArr[0].replace("\"", "").trim();
            String routeUrl = "";
            String routeStr = fileLineStr.substring(fileLineStr.lastIndexOf("\"-\"")).trim();
            if (routeStr.length() > 10) {
                routeUrl = routeStr.substring(routeStr.lastIndexOf("\"-\"") + 4).trim();
            }
            int indexS = fileLineStr.indexOf("HTTP/1.1");
            String tempStr = fileLineStr.substring(indexS, indexS + 13);
            String responseStatus = tempStr.replace("HTTP/1.1\" ", "");
            log.info("ip = " + ip);
            log.info("requestMethod = " + requestMethod);
            log.info("date = " + date);
            log.info("serviceCode = " + serviceCode);
            log.info("routeStr = " + routeUrl);
            log.info("responseStatus = " + responseStatus);
            return logsParseService.queryDataByParams(ip, date, serviceCode, routeUrl, requestMethod, responseStatus);
        }
        return null;
    }

}
