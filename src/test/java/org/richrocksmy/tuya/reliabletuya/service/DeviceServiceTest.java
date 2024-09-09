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

        List<Device> devices = List.of(Device.builder().deviceId("123456Id").name("Kitchen Light").build());
        when(ioTController.getAllDevices()).thenReturn(devices);
        List<Device> returnedDevices = deviceService.listDevices();

        verifyNoInteractions(deviceRepository);
        verify(ioTController).getAllDevices();
        assertThat(returnedDevices).containsAll(devices);
    }

    @Test
    void turnDeviceOn() {
    }

    @Test
    void turnDeviceOff() {
    }
}