package demo.test.dao.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by cba/kit-backend on 2022-05-23 11:16:38
 */
@Configuration
@MapperScan(basePackages = BookDataSourceConfig.BASE_PACKAGE,
        sqlSessionFactoryRef = BookDataSourceConfig.SSF_NAME,
        sqlSessionTemplateRef = BookDataSourceConfig.SST_NAME)
public class BookDataSourceConfig {

    public static final String DS_NAME = "bookDataSource";

    public static final String TM_NAME = "bookTransactionManager";

    public static final String SSF_NAME = "bookSqlSessionFactory";

    public static final String SST_NAME = "bookSqlSessionTemplate";

    public static final String DS_CFG_PREFIX = "datasource.book";

    public static final String BASE_PACKAGE = "demo.test.dao.mapper";

    @Bean(name = DS_NAME)
    @ConfigurationProperties(DS_CFG_PREFIX)
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = TM_NAME)
    public DataSourceTransactionManager transactionManager(@Qualifier(DS_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name = SSF_NAME)
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DS_NAME) DataSource dataSource, @Qualifier(MybatisGeneralConfig.MBTS_CFG_NAME) org.apache.ibatis.session.Configuration configuration) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setConfiguration(configuration);
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean(name = SST_NAME)
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(SSF_NAME) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
