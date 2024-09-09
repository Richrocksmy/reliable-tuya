package org.richrocksmy.tuya.reliabletuya.service;

import org.junit.jupiter.api.Test;
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

        List<Device> devices = List.of(new Device());
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

        String deviceId = "12345A";
        Device device = new Device();
        deviceService.turnDeviceOn(deviceId);

        verify(deviceRepository).save(device);
        verify(ioTController).turnDeviceOn(deviceId);
    }

    @Test
    void turnDeviceOff() {
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        IoTController ioTController = mock(IoTController.class);
        DeviceService deviceService = new DeviceService(ioTController, deviceRepository);

        String deviceId = "12345A";
        Device device = new Device();
        deviceService.turnDeviceOn(deviceId);

        verify(deviceRepository).save(device);
        verify(ioTController).turnDeviceOn(deviceId);
    }
}