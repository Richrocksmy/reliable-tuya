package org.richrocksmy.tuya.reliabletuya.iot;

import org.junit.jupiter.api.Test;
import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class TuyaIoTTest {

    private MockRestServiceServer mockRestServiceServer;

    @Test
    void shouldGetAllDevices() {
        String accessKey = "accessKey";
        String accessId = "accessId";
        String region = "EU";

        RestClient.Builder restClientBuilder = RestClient.builder();
        mockRestServiceServer = MockRestServiceServer.bindTo(restClientBuilder).build();
        mockRestServiceServer.expect(requestTo("https://openapi.tuyaeu.com/v1.0/iot-03/devices"))
                .andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));

        TuyaIoT tuyaIoT = new TuyaIoT(accessKey, accessId, region, restClientBuilder.build());

        List<Device> devices = tuyaIoT.getAllDevices();

        Device device = new Device();
        assertThat(devices).isEmpty();
//        assertThat(devices).containsExactly(device);
    }

    @Test
    void shouldTurnDeviceOn() {
    }

    @Test
    void shouldTurnDeviceOff() {
    }

    @Test
    void ShouldQueryState() {
    }
}