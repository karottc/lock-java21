package demo.test.dao.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by cba/kit-backend on 2022-05-23 11:16:38
 */
@Configuration
public class MybatisGeneralConfig {

    public static final String MBTS_CFG_NAME = "mybatisGeneralCfg";

    @Bean(MBTS_CFG_NAME)
    @ConfigurationProperties("mybatis.configuration")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public org.apache.ibatis.session.Configuration configuration() {
        return new org.apache.ibatis.session.Configuration();
    }
}
