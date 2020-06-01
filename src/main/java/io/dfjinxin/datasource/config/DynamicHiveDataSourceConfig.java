/**
 * 2019 东方金信
 */

package io.dfjinxin.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.dfjinxin.datasource.properties.DataSourceProperties;
import io.dfjinxin.datasource.properties.DynamicDataSourceProperties;
import io.dfjinxin.datasource.properties.HiveSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DynamicHiveDataSourceConfig {

    @Autowired
    private DynamicDataSourceProperties properties;
    @Autowired
    private HiveSourceProperties hiveSourceProperties;

//    @Bean
//    public DynamicDataSource dynamicDataSource() {
//        DynamicDataSource dynamicDataSource = new DynamicDataSource();
//        dynamicDataSource.setTargetDataSources(getHiveDataSource());
//
//        //默认数据源
////        DruidDataSource defaultDataSource = DynamicDataSourceFactory.buildDruidDataSource(dataSourceProperties);
////        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
//
//        return dynamicDataSource;
//    }

    public Map<Object, Object> getHiveDataSource() {
        Map<String, DataSourceProperties> dataSourcePropertiesMap = properties.getDatasource();
        Map<Object, Object> targetDataSources = new HashMap<>(dataSourcePropertiesMap.size());
        dataSourcePropertiesMap.forEach((k, v) -> {
            DruidDataSource druidDataSource = DynamicDataSourceFactory.buildDruidDataSource(v);
            targetDataSources.put(k, druidDataSource);
        });

        targetDataSources.put("HiveDataSource", DynamicHiveDataSourceFactory.buildDruidDataSource(hiveSourceProperties));

        return targetDataSources;
    }

}
