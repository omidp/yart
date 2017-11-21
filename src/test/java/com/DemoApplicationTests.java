package com;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.yart.app.ContainerConfig;
import com.yart.app.JpaDataConfig;
import com.yart.app.domain.DatasourceEntity;
import com.yart.app.domain.enums.DatabaseType;
import com.yart.app.domain.enums.DatasourceType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { JpaDataConfig.class, ContainerConfig.class })
@DataJpaTest
@Transactional()
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DemoApplicationTests
{

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @Commit
    @Ignore
    public void populateDatasource()
    {
        for (int i = 0; i < 100; i++)
        {
            DatasourceEntity entity = new DatasourceEntity();
            entity.setName("ds " + i);
            entity.setDsType(DatasourceType.JDBC);
            entity.setDbType(DatabaseType.POSTGRESQL);
            entity.setDescription("ds " + i);
            em.persist(entity);

        }
        em.flush();
    }

    @TestConfiguration
    static class Config
    {

        @Bean
        public DataSource datasource()
        {
            DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:postgresql://127.0.0.1:5432/yartdb", "yart", "yart");
            return ds;
        }

    }

}
