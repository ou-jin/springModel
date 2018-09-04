package sys.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConfig {
	  @Bean
	    public DataSource getDataSource() {
		  	DataSource dataSource = new DataSource();
		  	dataSource.setUrl("jdbc:mysql://120.27.213.3:3306/storeManage");
	        dataSource.setUsername("root");
	        dataSource.setPassword("123456a?");
	        return dataSource;
	  }
}
