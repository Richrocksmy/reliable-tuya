package org.richrocksmy.tuya.reliabletuya;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class ReliableTuyaApplicationTests {

	@Container
	@ServiceConnection
	// Ancient version of MySQL because my Mac is ancient and doesn't appear to support newer versions!
	private final static MySQLContainer<?> MY_SQL_CONTAINER
			= new MySQLContainer<>(DockerImageName.parse("mysql:5.7.34"));

	@Test
	void contextLoads() {
	}

}
