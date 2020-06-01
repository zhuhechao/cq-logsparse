/**
 * 2019 东方金信
 */

package io.dfjinxin.datasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        initDataSource();
        return DynamicContextHolder.peek();
    }

    @Autowired
    private DynamicMysql1DataSourceConfig dynamicMysql1DataSourceConfig;
    @Autowired
    private DynamicMysql2DataSourceConfig dynamicMysql2DataSourceConfig;
    @Autowired
    private DynamicHiveDataSourceConfig dynamicHiveDataSourceConfig;

//    @Bean
    public DynamicDataSource initDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dynamicMysql1DataSourceConfig.getMysqlRspDataSource());
//        dynamicDataSource.setTargetDataSources(dynamicMysql2DataSourceConfig.getMysqlAuthDataSource());
//        dynamicDataSource.setTargetDataSources(dynamicHiveDataSourceConfig.getHiveDataSource());

//        //默认数据源
//        DruidDataSource defaultDataSource = DynamicDataSourceFactory.buildDruidDataSource(dataSourceProperties);
//        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);

        return dynamicDataSource;
    }

}
