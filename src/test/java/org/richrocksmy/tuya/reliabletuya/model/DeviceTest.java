package org.richrocksmy.tuya.reliabletuya.model;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DeviceTest {

    @Test
    void shouldGetColumnProvider() {
        var columnProvider = Device.getColumnProvider();
        List<String> headers = columnProvider.getFirst();

        Device device = new Device(1L, "127.0.0.1", "12345", "localKey", "name", Device.State.ON.toString());
        List<String> values = columnProvider.getSecond().apply(device);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(headers).containsExactly("Id", "IP", "Device Id", "Local Key", "Name");
        softly.assertThat(values).containsExactly("1", "127.0.0.1", "12345", "localKey", "name");
        softly.assertAll();
    }
}