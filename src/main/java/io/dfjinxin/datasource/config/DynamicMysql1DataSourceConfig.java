/**
 * 2019 东方金信
 */

package io.dfjinxin.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.dfjinxin.datasource.properties.DataSource1Properties;
import io.dfjinxin.datasource.properties.DataSourceProperties;
import io.dfjinxin.datasource.properties.DynamicDataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
public class DynamicMysql1DataSourceConfig {

    @Autowired
    private DynamicDataSourceProperties properties;
    @Autowired
    private DataSource1Properties dataSource1Properties;

    public Map<Object, Object> getMysqlRspDataSource() {
        Map<String, DataSourceProperties> dataSourcePropertiesMap = properties.getDatasource();
        Map<Object, Object> targetDataSources = new HashMap<>(dataSourcePropertiesMap.size());
        dataSourcePropertiesMap.forEach((k, v) -> {
            DruidDataSource druidDataSource = DynamicDataSourceFactory.buildDruidDataSource(v);
            targetDataSources.put(k, druidDataSource);
        });

        targetDataSources.put("RspDataSource", DynamicDataSourceFactory.buildDruidDataSource(dataSource1Properties));
        return targetDataSources;
    }

}
