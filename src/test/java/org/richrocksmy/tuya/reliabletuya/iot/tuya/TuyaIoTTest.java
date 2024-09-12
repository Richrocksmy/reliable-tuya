package org.richrocksmy.tuya.reliabletuya.iot.tuya;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.richrocksmy.tuya.reliabletuya.model.Device;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TuyaIoTTest {

    @Test
    void shouldGetAllDevices() {
        TuyaApi tuyaApi = mock(TuyaApi.class);
        long homeId = 1234567;
        TuyaIoT tuyaIoT = new TuyaIoT(homeId, tuyaApi);

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

        when(tuyaApi.getAllDevices(homeId)).thenReturn(List.of(tuyaDevice));

        List<Device> devices = tuyaIoT.getAllDevices();

        assertThat(devices).satisfiesExactly(device -> {
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(device.getDeviceId()).as("Device id").isEqualTo("123456");
            softly.assertThat(device.getIp()).as("IP").isEqualTo("127.0.0.1");
            softly.assertThat(device.getLocalKey()).as("Local Key").isEqualTo("localKey");
            softly.assertThat(device.getName()).as("Name").isEqualTo("House Light");
            softly.assertAll();
        });
    }

    @Test
    void shouldGetAllDevicesAndFilterEmptyObjects() {
        TuyaApi tuyaApi = mock(TuyaApi.class);
        long homeId = 1234567;
        TuyaIoT tuyaIoT = new TuyaIoT(homeId, tuyaApi);

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

        when(tuyaApi.getAllDevices(homeId)).thenReturn(List.of(tuyaDevice, nullTuyaDevice));

        List<Device> devices = tuyaIoT.getAllDevices();

        assertThat(devices).satisfiesExactly(device -> {
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(device.getDeviceId()).as("Device id").isEqualTo("123456");
            softly.assertThat(device.getIp()).as("IP").isEqualTo("127.0.0.1");
            softly.assertThat(device.getLocalKey()).as("Local Key").isEqualTo("localKey");
            softly.assertThat(device.getName()).as("Name").isEqualTo("House Light");
            softly.assertAll();
        });
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldTurnDeviceOn() {
        TuyaApi tuyaApi = mock(TuyaApi.class);
        when(tuyaApi.sendCommands(any(), any())).thenReturn(true);

        long homeId = 1234567;
        TuyaIoT tuyaIoT = new TuyaIoT(homeId, tuyaApi);

        String deviceId = "1234Id";

        boolean result = tuyaIoT.turnDeviceOn(deviceId);
        assertThat(result).isTrue();

        ArgumentCaptor<Map<String, Object>> tuyaCommandArgumentCaptor = ArgumentCaptor.forClass(Map.class);
        verify(tuyaApi).sendCommands(eq(deviceId), tuyaCommandArgumentCaptor.capture());

        List<Map<String, Object>> tuyaCommands = (List<Map<String, Object>>)((Map<String, Object>) tuyaCommandArgumentCaptor.getValue()).get("commands");

        assertThat(tuyaCommands).satisfiesExactly(it -> {
            assertThat(it).containsEntry("code", "switch_led").containsEntry("value", true);
        });
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldTurnDeviceOff() {
        TuyaApi tuyaApi = mock(TuyaApi.class);
        when(tuyaApi.sendCommands(any(), any())).thenReturn(true);

        long homeId = 1234567;
        TuyaIoT tuyaIoT = new TuyaIoT(homeId, tuyaApi);

        String deviceId = "1234Id";

        boolean result = tuyaIoT.turnDeviceOff(deviceId);

        assertThat(result).isTrue();
        ArgumentCaptor<Map<String, Object>> tuyaCommandArgumentCaptor = ArgumentCaptor.forClass(Map.class);
        verify(tuyaApi).sendCommands(eq(deviceId), tuyaCommandArgumentCaptor.capture());

        List<Map<String, Object>> tuyaCommands = (List<Map<String, Object>>)((Map<String, Object>) tuyaCommandArgumentCaptor.getValue()).get("commands");

        assertThat(tuyaCommands).satisfiesExactly(it -> {
            assertThat(it).containsEntry("code", "switch_led").containsEntry("value", false);
        });
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldTurnDeviceOffReturnsFailureWhenCallFails() {
        TuyaApi tuyaApi = mock(TuyaApi.class);
        when(tuyaApi.sendCommands(any(), any())).thenReturn(false);
        long homeId = 1234567;
        TuyaIoT tuyaIoT = new TuyaIoT(homeId, tuyaApi);

        String deviceId = "1234Id";

        boolean result = tuyaIoT.turnDeviceOff(deviceId);

        assertThat(result).isFalse();
        ArgumentCaptor<Map<String, Object>> tuyaCommandArgumentCaptor = ArgumentCaptor.forClass(Map.class);
        verify(tuyaApi).sendCommands(eq(deviceId), tuyaCommandArgumentCaptor.capture());

        List<Map<String, Object>> tuyaCommands = (List<Map<String, Object>>)((Map<String, Object>) tuyaCommandArgumentCaptor.getValue()).get("commands");

        assertThat(tuyaCommands).satisfiesExactly(it -> {
            assertThat(it).containsEntry("code", "switch_led").containsEntry("value", false);
        });
    }

    @Test
    void shouldReturnSingleDevice() {
        TuyaApi tuyaApi = mock(TuyaApi.class);
        String deviceId = "123456Id";

        TuyaDevice tuyaDevice = new TuyaDevice(
                12345L,
                0,
                "light",
                System.currentTimeMillis(),
                "lamp",
                deviceId,
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

        when(tuyaApi.getDevice(deviceId)).thenReturn(tuyaDevice);
        long homeId = 1234567;
        TuyaIoT tuyaIoT = new TuyaIoT(homeId, tuyaApi);

        Device device = tuyaIoT.getDevice(deviceId);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(device.getDeviceId()).isEqualTo(deviceId);
        softly.assertThat(device.getIp()).isEqualTo("127.0.0.1");
        softly.assertThat(device.getLocalKey()).isEqualTo("localKey");
        softly.assertThat(device.getName()).isEqualTo("House Light");
        softly.assertAll();
    }

    @Test
    void ShouldQueryState() {

    }
}