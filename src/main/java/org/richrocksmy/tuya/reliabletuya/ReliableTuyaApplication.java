package org.richrocksmy.tuya.reliabletuya;

import com.tuya.connector.spring.annotations.ConnectorScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ConnectorScan(basePackages = "org.richrocksmy.tuya.reliabletuya.iot")
public class ReliableTuyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReliableTuyaApplication.class, args);
	}

}
