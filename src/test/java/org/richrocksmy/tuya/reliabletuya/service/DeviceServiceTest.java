package org.richrocksmy.tuya.reliabletuya.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.richrocksmy.tuya.reliabletuya.iot.IoTController;
import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.repository.DeviceRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class DeviceServiceTest {

    @Test
    void shouldListDevices() {
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        IoTController ioTController = mock(IoTController.class);
        DeviceService deviceService = new DeviceService(ioTController, deviceRepository);

        List<Device> devices = List.of(Device.builder().deviceId("123456Id").name("Kitchen Light").build());
        when(ioTController.getAllDevices()).thenReturn(devices);
        List<Device> returnedDevices = deviceService.listDevices();

        verifyNoInteractions(deviceRepository);
        verify(ioTController).getAllDevices();
        assertThat(returnedDevices).containsAll(devices);
    }

    @Test
    void turnDeviceOn() {
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        IoTController ioTController = mock(IoTController.class);
        DeviceService deviceService = new DeviceService(ioTController, deviceRepository);

        String deviceId = "123456id";
        Device device = Device.builder().id(1L).deviceId(deviceId).state(Device.State.OFF.toString()).build();
        when(deviceRepository.findDeviceByDeviceId(deviceId)).thenReturn(device);

        deviceService.turnDeviceOn(deviceId);

        ArgumentCaptor<Device> deviceArgumentCaptor = ArgumentCaptor.forClass(Device.class);
        verify(deviceRepository).save(deviceArgumentCaptor.capture());

        Device updatedDevice = deviceArgumentCaptor.getValue();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(updatedDevice.getDeviceId()).isEqualTo(deviceId);
        softly.assertThat(updatedDevice.getState()).isEqualTo(Device.State.ON);
        softly.assertAll();

        verify(ioTController).turnDeviceOn(deviceId);
    }

    @Test
    void turnDeviceOff() {
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        IoTController ioTController = mock(IoTController.class);
        DeviceService deviceService = new DeviceService(ioTController, deviceRepository);

        String deviceId = "123456id";
        Device device = Device.builder().id(1L).deviceId(deviceId).state(Device.State.ON.toString()).build();
        when(deviceRepository.findDeviceByDeviceId(deviceId)).thenReturn(device);

        deviceService.turnDeviceOff(deviceId);

        ArgumentCaptor<Device> deviceArgumentCaptor = ArgumentCaptor.forClass(Device.class);
        verify(deviceRepository).save(deviceArgumentCaptor.capture());

        Device updatedDevice = deviceArgumentCaptor.getValue();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(updatedDevice.getDeviceId()).isEqualTo(deviceId);
        softly.assertThat(updatedDevice.getState()).isEqualTo(Device.State.OFF);
        softly.assertAll();

        verify(ioTController).turnDeviceOff(deviceId);
    }
}