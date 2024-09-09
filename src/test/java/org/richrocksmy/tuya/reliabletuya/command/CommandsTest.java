package org.richrocksmy.tuya.reliabletuya.command;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.output.Output;
import org.richrocksmy.tuya.reliabletuya.service.DeviceService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class  CommandsTest {

    @Test
    public void shouldListDevices() {
        DeviceService deviceService = mock(DeviceService.class);
        Output output = mock(Output.class);

        Commands commands = new Commands(deviceService, output);
        Device device = Device.builder().deviceId("123456").id(12345L).ip("127.0.0.1").localKey("localKey").name("Kitchen light").build();
        when(deviceService.listDevices()).thenReturn(List.of(device));

        commands.listDevices();

        ArgumentCaptor<List<Device>> outputArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(output).write(outputArgumentCaptor.capture(), any());

        List<Device> receivedDevices = outputArgumentCaptor.getValue();
        assertThat(receivedDevices).isNotEmpty();
        assertThat(receivedDevices).contains(device);
    }

    @Test
    public void shouldTurnDeviceOn() {
        DeviceService deviceService = mock(DeviceService.class);
        Output output = mock(Output.class);

        Commands commands = new Commands(deviceService, output);
        String deviceId = "123456A";

        commands.deviceOn(deviceId);

        verify(deviceService).turnDeviceOn(deviceId);
    }

    @Test
    public void shouldTurnDeviceOff() {
        DeviceService deviceService = mock(DeviceService.class);
        Output output = mock(Output.class);

        Commands commands = new Commands(deviceService, output);
        String deviceId = "123456A";

        commands.deviceOff(deviceId);

        verify(deviceService).turnDeviceOff(deviceId);
    }
}