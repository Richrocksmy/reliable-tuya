package org.richrocksmy.tuya.reliabletuya.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

class DeviceTest {

    @Test
    void shouldGetColumnProvider() {
        var columnProvider = Device.getColumnProvider();
        List<String> headers = columnProvider.getFirst();

        Device device = new Device(1L, "127.0.0.1", "12345", "localKey", "name");
        List<String> values = columnProvider.getSecond().apply(device);

        assertThat(headers).containsExactly("Id", "IP", "Device Id", "Local Key", "Name");
        assertThat(values).containsExactly("1", "127.0.0.1", "12345", "localKey", "name");
    }
}