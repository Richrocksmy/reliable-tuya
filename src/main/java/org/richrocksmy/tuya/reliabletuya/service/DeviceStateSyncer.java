package org.richrocksmy.tuya.reliabletuya.service;

import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.repository.DeviceRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.richrocksmy.tuya.reliabletuya.model.Device.State.OFF;
import static org.richrocksmy.tuya.reliabletuya.model.Device.State.ON;

public class DeviceStateSyncer {

    private final DeviceService deviceService;

    private final DeviceRepository deviceRepository;

    public DeviceStateSyncer(final DeviceService deviceService, final DeviceRepository deviceRepository) {
        this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
    }

    public void sync() {
        Map<Boolean, List<Device>> devices = deviceRepository.findDevicesByStateIn(List.of(ON, OFF)).stream().collect(Collectors.partitioningBy(it -> Device.State.ON == it.getState()));
        devices.get(true).forEach(it -> deviceService.turnDeviceOn(it.getDeviceId()));
        devices.get(false).forEach(it -> deviceService.turnDeviceOff(it.getDeviceId()));
    }
}
