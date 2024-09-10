package org.richrocksmy.tuya.reliabletuya.iot;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaApi;
import org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaCommand;
import org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaDevice;
import org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaIoT;
import org.richrocksmy.tuya.reliabletuya.model.Device;

import java.util.List;
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
    void shouldTurnDeviceOn() {
        TuyaApi tuyaApi = mock(TuyaApi.class);
        long homeId = 1234567;
        TuyaIoT tuyaIoT = new TuyaIoT(homeId, tuyaApi);

        String deviceId = "1234Id";

        tuyaIoT.turnDeviceOn(deviceId);

        ArgumentCaptor<TuyaCommand> tuyaCommandArgumentCaptor = ArgumentCaptor.forClass(TuyaCommand.class);
        verify(tuyaApi).sendCommands(eq(deviceId), tuyaCommandArgumentCaptor.capture());

        TuyaCommand tuyaCommand = tuyaCommandArgumentCaptor.getValue();
        assertThat(tuyaCommand.getCommands()).satisfiesExactly(device -> {
            assertThat(tuyaCommand.getCommands()).containsExactly(TuyaCommand.TuyaCommandEntry.LIGHT_ON);
        });
    }

    @Test
    void shouldTurnDeviceOff() {
        TuyaApi tuyaApi = mock(TuyaApi.class);
        long homeId = 1234567;
        TuyaIoT tuyaIoT = new TuyaIoT(homeId, tuyaApi);

        String deviceId = "1234Id";

        tuyaIoT.turnDeviceOff(deviceId);

        ArgumentCaptor<TuyaCommand> tuyaCommandArgumentCaptor = ArgumentCaptor.forClass(TuyaCommand.class);
        verify(tuyaApi).sendCommands(eq(deviceId), tuyaCommandArgumentCaptor.capture());

        TuyaCommand tuyaCommand = tuyaCommandArgumentCaptor.getValue();
        assertThat(tuyaCommand.getCommands()).satisfiesExactly(device -> {
            assertThat(tuyaCommand.getCommands()).containsExactly(TuyaCommand.TuyaCommandEntry.LIGHT_OFF);
        });
    }

    @Test
    void ShouldQueryState() {
    }
}