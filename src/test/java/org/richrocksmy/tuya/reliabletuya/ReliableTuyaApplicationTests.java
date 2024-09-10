package org.richrocksmy.tuya.reliabletuya;

import org.junit.jupiter.api.Test;
import org.richrocksmy.tuya.reliabletuya.config.TestContainerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
//@TestPropertySource(properties = "tuya.home-id=123456")
class ReliableTuyaApplicationTests {

	@Container
	@ServiceConnection
	// Ancient version of MySQL because my Mac is ancient and doesn't appear to support newer versions!
	private final static MySQLContainer<?> MY_SQL_CONTAINER
			= new MySQLContainer<>(DockerImageName.parse("mysql:5.7.34"));

	public static void main(String[] args) {
		SpringApplication.from(ReliableTuyaApplication::main)
				.with(TestContainerConfig.class)
				.run(args);
	}

	@Test
	void contextLoads() {
	}

}
