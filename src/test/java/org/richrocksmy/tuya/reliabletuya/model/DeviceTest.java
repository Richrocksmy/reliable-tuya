package org.richrocksmy.tuya.reliabletuya.model;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaDevice;

import java.util.List;
import java.util.UUID;

class DeviceTest {

    @Test
    void shouldGetColumnProvider() {
        var columnProvider = Device.getColumnProvider();
        List<String> headers = columnProvider.getFirst();

        Device device = new Device(1L, "127.0.0.1", "12345", "localKey", "name", Device.State.ON);
        List<String> values = columnProvider.getSecond().apply(device);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(headers).containsExactly("Id", "IP", "Device Id", "Local Key", "Name", "State");
        softly.assertThat(values).containsExactly("1", "127.0.0.1", "12345", "localKey", "name", "ON");
        softly.assertAll();
    }

    @Test
    void shouldInitialiseStateWhenNotProvided() {
        var columnProvider = Device.getColumnProvider();
        List<String> headers = columnProvider.getFirst();

        TuyaDevice tuyaDevice = new TuyaDevice(
                12345L,
                0,
                "light",
                System.currentTimeMillis(),
                "lamp",
                "123456",
                "127.0.0.1",
                "localKey",
                "Model",
                "House Light",
                true,
                "12345OwnerId",
                "12345ProductId",
                "Generic Lamp",
                null,
                true,
                "UTC",
                UUID.randomUUID().toString(),
                System.currentTimeMillis(),
                UUID.randomUUID().toString());

        Device device = new Device(tuyaDevice);
        List<String> values = columnProvider.getSecond().apply(device);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(headers).containsExactly("Id", "IP", "Device Id", "Local Key", "Name", "State");
        softly.assertThat(values).containsExactly("null", "127.0.0.1", "123456", "localKey", "House Light", "UNKNOWN");
        softly.assertAll();
    }
}