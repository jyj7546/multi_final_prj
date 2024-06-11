package config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:application.yml")
public class DatasourceConfig {
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")   // application.yml 파일 내 db정보
    /**
     * springboot 2 부터 지정된 default db connection pool
     * @return
     */
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
    	// hikariConfig 기반으로 datasource 생성
        DataSource dataSource = new HikariDataSource(hikariConfig());
        
        return dataSource;
    }

    @Bean
    /**
     * 데이터베이스와 웹 어플리케이션을 연동해주는 객체
     * @param dataSource
     * @return
     * @throws Exception
     */
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperLocations));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    /**
     * Mybatis 쿼리문을 실행해주는 역할
     * @param sqlSessionFactory
     * @return
     */
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}