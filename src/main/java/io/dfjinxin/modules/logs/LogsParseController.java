package io.dfjinxin.modules.logs;

import io.dfjinxin.common.utils.DateUtils;
import io.dfjinxin.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

    /**
     * @Desc: 根据文件目录path、文件名称name（access-系统当前日期.log）解析文件，并落入hive库
     * @Param: []
     * @Return: io.dfjinxin.common.utils.R
     * @Author: z.h.c
     * @Date: 2020/5/29 16:48
     */
    @PostMapping("doLogsFileParse/")
    @ApiOperation(value = "数据共享日志分析", notes = "根据文件目录&文件名称解析文件，并落入hive库")
    public R doLogsFileParse() {

        String dateStr = DateUtils.getCurrentDayStr();
        String fileName = "access_" + dateStr + ".log";
        log.info("数据共享日志文件分析,params- filepath:{},filename:{}", path, fileName);
        String fileLine = "";
        try {
            FileInputStream inputStream = new FileInputStream("D://" + fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((fileLine = bufferedReader.readLine()) != null) {
                this.fileLineStrParse(fileLine);
            }
            inputStream.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.ok();
    }

    /**
     * @Desc: 解析读取到的文件行内容
     * @Param: [fileLineStr]
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
    private void fileLineStrParse(String fileLineStr) {
//        fileLineStr = "23.52.0.9 - - [24/Apr/2020:18:40:37 +0800] \"GET /services/RES_SFZGGWOH/jngl/56poInJfKmiNX2no1JRdW4w2TibZB5FrYyV5QlbShqU/getDataJson?pageNo=1&pageSize=20&search= HTTP/1.1\" 200 1391 \"-\" \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36\" \"-\"";
        if (fileLineStr.contains("/services/") && fileLineStr.contains("HTTP/1.1\" 200")) {
            String ip = fileLineStr.substring(0, fileLineStr.indexOf("-")).trim();
            String time = fileLineStr.substring(fileLineStr.indexOf("[") + 1, fileLineStr.indexOf("]")).trim();
            String date = time.substring(0, time.indexOf("+")).trim();
            String serverUrl = fileLineStr.substring(fileLineStr.indexOf("GET"), fileLineStr.indexOf("?")).trim();
            String serverpath = serverUrl.replace("GET /services/", "");
            String[] serverArrs = serverpath.split("/");
            String serverCode_1 = serverArrs[0];
            String serverCode_2 = serverArrs[1];
            log.info("ip = " + ip);
            log.info("date = " + date);
            log.info("serverCode_1 = " + serverCode_1);
            log.info("serverCode_2 = " + serverCode_2);
        }
    }

    public static void main(String[] args) {

        LogsParseController logsParseController = new LogsParseController();
        logsParseController.fileLineStrParse("aa");
        /*String fileLineStr = "23.52.0.9 - - [24/Apr/2020:18:40:37 +0800] \"GET /services/RES_SFZGGWOH/jngl/56poInJfKmiNX2no1JRdW4w2TibZB5FrYyV5QlbShqU/getDataJson?pageNo=1&pageSize=20&search= HTTP/1.1\" 200 1391 \"-\" \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36\" \"-\"";
        String ip = fileLineStr.substring(0, fileLineStr.indexOf("-")).trim();
        String time = fileLineStr.substring(fileLineStr.indexOf("[") + 1, fileLineStr.indexOf("]")).trim();
        String date = time.substring(0, time.indexOf("+")).trim();
        String serverUrl = fileLineStr.substring(fileLineStr.indexOf("GET"), fileLineStr.indexOf("?")).trim();
        String serverpath = serverUrl.replace("GET /services/", "");
//        String serverCode = serverpath.substring(serverpath.indexOf("/", 0) + 1, serverpath.indexOf("/", 3));

        String[] serverArrs = serverpath.split("/");
        String serverCode_1=serverArrs[0];
        String serverCode_2=serverArrs[1];
        System.out.println("ip = " + ip);
        System.out.println("date = " + date);
//        System.out.println("serverUrl = " + serverUrl);
//        System.out.println("serverpath = " + serverpath);
        System.out.println("serverCode_1 = " + serverCode_1);
        System.out.println("serverCode_2 = " + serverCode_2);
//        System.out.println("fileLineStr = " + fileLineStr);*/
    }
}
