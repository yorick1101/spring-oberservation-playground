package yorick.poc.spring.observation.config;

import io.micrometer.tracing.Tracer;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yorick.poc.spring.observation.repository.interceptor.MyBatisTracingInterceptor;

import javax.sql.DataSource;

@Configuration
@MapperScan("yorick.poc.spring.observation.repository")
public class MybatisConfiguration {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private Tracer tracer;
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        //factoryBean.setPlugins(new MyBatisTracingInterceptor(tracer));
        return factoryBean.getObject();
    }

}
