package com.yart.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.yart.app.dao.SpringSecurityAuditorAware;
import com.yart.app.domain.User;

@Configuration
@EntityScan({ "com.yart.app.domain" })
@EnableJpaRepositories({ "com.yart.app.dao" })
@EnableJpaAuditing()
public class JpaDataConfig
{

    @Bean
    public AuditorAware<User> auditAware()
    {
        return new SpringSecurityAuditorAware();
    }

}
