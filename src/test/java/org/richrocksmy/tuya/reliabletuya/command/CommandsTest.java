package org.richrocksmy.tuya.reliabletuya.command;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.output.Output;
import org.richrocksmy.tuya.reliabletuya.service.DeviceService;
import org.richrocksmy.tuya.reliabletuya.service.ScheduleService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CommandsTest {

    @Test
    @SuppressWarnings("unchecked")
    public void shouldListDevices() {
        DeviceService deviceService = mock(DeviceService.class);
        ScheduleService scheduleService = mock(ScheduleService.class);
        Output output = mock(Output.class);

        Commands commands = new Commands(deviceService, scheduleService, output);
        Device device = Device.builder().deviceId("123456").id(12345L).ip("127.0.0.1").localKey("localKey").name("Kitchen light").build();
        when(deviceService.listDevices()).thenReturn(List.of(device));

        commands.listDevices();

        ArgumentCaptor<List<Device>> outputArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(output).write(outputArgumentCaptor.capture(), any());

        List<Device> receivedDevices = outputArgumentCaptor.getValue();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receivedDevices).isNotEmpty();
        softly.assertThat(receivedDevices).contains(device);
        softly.assertAll();
    }

    @Test
    public void shouldTurnDeviceOn() {
        DeviceService deviceService = mock(DeviceService.class);
        ScheduleService scheduleService = mock(ScheduleService.class);
        Output output = mock(Output.class);

        Commands commands = new Commands(deviceService, scheduleService, output);
        String deviceId = "123456A";

        commands.deviceOn(deviceId);

        verify(deviceService).turnDeviceOn(deviceId);
    }

    @Test
    public void shouldTurnDeviceOff() {
        DeviceService deviceService = mock(DeviceService.class);
        ScheduleService scheduleService = mock(ScheduleService.class);
        Output output = mock(Output.class);

        Commands commands = new Commands(deviceService, scheduleService, output);
        String deviceId = "123456A";

        commands.deviceOff(deviceId);

        verify(deviceService).turnDeviceOff(deviceId);
    }

    @Test
    public void shouldConfigureDeviceForSchedule() {
        DeviceService deviceService = mock(DeviceService.class);
        ScheduleService scheduleService = mock(ScheduleService.class);
        Output output = mock(Output.class);

        Commands commands = new Commands(deviceService, scheduleService, output);
        String deviceId = "123456A";

        commands.scheduleDevice(deviceId, "sunset");

        verify(scheduleService).scheduleDevice(deviceId, "sunset");
    }

    @Test
    public void shouldFailWhenUnsupportedScheduleRequested() {
        DeviceService deviceService = mock(DeviceService.class);
        ScheduleService scheduleService = mock(ScheduleService.class);
        Output output = mock(Output.class);

        Commands commands = new Commands(deviceService, scheduleService, output);
        String deviceId = "123456A";

        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> commands.scheduleDevice(deviceId, "0800 - 1000"))
                .withMessage("Currently the application only supports sunset scheduling");
    }
}