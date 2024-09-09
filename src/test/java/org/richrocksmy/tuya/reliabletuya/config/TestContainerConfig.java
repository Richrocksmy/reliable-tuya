package org.richrocksmy.tuya.reliabletuya.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainerConfig {

    @Bean
    @ServiceConnection
    MySQLContainer<?> mySqljContainer() {
        return new MySQLContainer<>("mysql:5.7.34");
    }
}
