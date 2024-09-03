package com.app.community.storage.db.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.app.community.storage.db.command")
@EnableJpaRepositories(basePackages = "com.app.community.storage.db.command")
class JpaConfig {

}
