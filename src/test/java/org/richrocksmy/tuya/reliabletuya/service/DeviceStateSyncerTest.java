package org.richrocksmy.tuya.reliabletuya.service;

import org.junit.jupiter.api.Test;
import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.repository.DeviceRepository;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeviceStateSyncerTest {

    @Test
    public void shouldSyncDeviceStateIfStateKnown() {
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = mock(DeviceService.class);
        DeviceStateSyncer deviceStateSyncer = new DeviceStateSyncer(deviceService, deviceRepository);

        Device device1 = Device.builder().id(1L).deviceId("123457L").state(Device.State.ON).build();
        Device device2 = Device.builder().id(1L).deviceId("123457L").state(Device.State.OFF).build();
        when(deviceRepository.findDevicesByStateIn(List.of(Device.State.ON, Device.State.OFF))).thenReturn(List.of(device1, device2));

        deviceStateSyncer.sync();

        verify(deviceRepository).findDevicesByStateIn(List.of(Device.State.ON, Device.State.OFF));
        verify(deviceService).turnDeviceOn(device1.getDeviceId());
        verify(deviceService).turnDeviceOff(device2.getDeviceId());
    }

}