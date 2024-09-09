package org.richrocksmy.tuya.reliabletuya.iot;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.richrocksmy.tuya.reliabletuya.iot.tuya.Tuya;
import org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaDevice;
import org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaIoT;
import org.richrocksmy.tuya.reliabletuya.model.Device;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TuyaIoTTest {

    @Test
    void shouldGetAllDevices() {
        Tuya tuya = mock(Tuya.class);
        long homeId = 1234567;
        TuyaIoT tuyaIoT = new TuyaIoT(homeId, tuya);

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

        when(tuya.getAll(homeId)).thenReturn(List.of(tuyaDevice));

        List<Device> devices = tuyaIoT.getAllDevices();

        assertThat(devices).hasSize(1);
        SoftAssertions softly = new SoftAssertions();
        Device device = devices.get(0);
        softly.assertThat(device.getDeviceId()).as("Device id").isEqualTo("123456");
        softly.assertThat(device.getIp()).as("IP").isEqualTo("127.0.0.1");
        softly.assertThat(device.getLocalKey()).as("Local Key").isEqualTo("localKey");
        softly.assertThat(device.getName()).as("Name").isEqualTo("House Light");
        softly.assertAll();
    }

    @Test
    void shouldGetAllDevicesAndFilterEmptyObjects() {
        Tuya tuya = mock(Tuya.class);
        long homeId = 1234567;
        TuyaIoT tuyaIoT = new TuyaIoT(homeId, tuya);

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


        TuyaDevice nullTuyaDevice = new TuyaDevice(
                null,
                0,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                true,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        when(tuya.getAll(homeId)).thenReturn(List.of(tuyaDevice, nullTuyaDevice));

        List<Device> devices = tuyaIoT.getAllDevices();

        assertThat(devices).hasSize(1);
        SoftAssertions softly = new SoftAssertions();
        Device device = devices.get(0);
        softly.assertThat(device.getDeviceId()).as("Device id").isEqualTo("123456");
        softly.assertThat(device.getIp()).as("IP").isEqualTo("127.0.0.1");
        softly.assertThat(device.getLocalKey()).as("Local Key").isEqualTo("localKey");
        softly.assertThat(device.getName()).as("Name").isEqualTo("House Light");
        softly.assertAll();
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