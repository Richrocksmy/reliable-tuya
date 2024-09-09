package org.richrocksmy.tuya.reliabletuya.command;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.richrocksmy.tuya.reliabletuya.output.Output;
import org.richrocksmy.tuya.reliabletuya.service.DeviceService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CommandsTest {

    @Test
    public void shouldListDevices() {
        DeviceService deviceService = mock(DeviceService.class);
        Output output = mock(Output.class);

        Commands commands = new Commands(deviceService, output);

        commands.listDevices();

        ArgumentCaptor<String> outputArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(output).write(outputArgumentCaptor.capture());
        assertThat(outputArgumentCaptor.getValue()).isNotEmpty();
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