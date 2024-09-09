package org.richrocksmy.tuya.reliabletuya.service;

import org.richrocksmy.tuya.reliabletuya.iot.IoTController;
import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final IoTController ioTController;

    private final DeviceRepository deviceRepository;

    public DeviceService(final IoTController ioTController, final DeviceRepository deviceRepository) {
        this.ioTController = ioTController;
        this.deviceRepository = deviceRepository;
    }
    public List<Device> listDevices() {
        return ioTController.getAllDevices();
    }

    public void turnDeviceOn(final String deviceId) {

    }

    public void turnDeviceOff(final String deviceId) {

    }
}

