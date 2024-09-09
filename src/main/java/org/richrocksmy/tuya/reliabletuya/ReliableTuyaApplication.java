package org.richrocksmy.tuya.reliabletuya;

import org.richrocksmy.tuya.reliabletuya.config.TestContainerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReliableTuyaApplication {

	public static void main(String[] args) {
		SpringApplication.from(ReliableTuyaApplication::main)
				.with(TestContainerConfig.class)
				.run(args);
	}

}
