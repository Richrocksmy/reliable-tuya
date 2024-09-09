package org.richrocksmy.tuya.reliabletuya.iot;

import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.model.State;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class TuyaIoT implements IoTController {

    private static final Map<String, String> regionHosts = Map.of(
            "CN", "https://openapi.tuyacn.com",
            "US", "https://openapi.tuyaus.com",
            "EU", "https://openapi.tuyaeu.com",
            "IN", "https://openapi.tuyain.com"
    );

    private final String ACCESS_KEY;

    private final String ACCESS_ID;
    private final String BASE_URL;
    private final RestClient restClient;

    public TuyaIoT(@Value("${tuya.access-key}") final String accessKey,
                   @Value("${tuya.access-id}") final String accessId,
                   @Value("${tuya.region}") final String region,
                   final RestClient restClient) {
        this.ACCESS_KEY = accessKey;
        this.ACCESS_ID = accessId;
        this.BASE_URL = regionHosts.get(region);
        this.restClient = restClient;
    }

    @Override
    public List<Device> getAllDevices() {
        String devices = restClient.get()
                .uri(BASE_URL + "/v1.0/iot-03/devices")
                .header("Content-Type", "application/json")
                .header("access_token", ACCESS_KEY)
                .header("client_id", ACCESS_ID)
                .retrieve()
                .body(String.class);

        System.out.println(devices);

        return Collections.emptyList();
    }

    @Override
    public void turnDeviceOn(String deviceId) {

    }

    @Override
    public void turnDeviceOff(String deviceId) {

    }

    @Override
    public State queryState(String deviceId) {
        return null;
    }
}
