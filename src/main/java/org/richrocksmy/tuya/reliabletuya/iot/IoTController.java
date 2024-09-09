package org.richrocksmy.tuya.reliabletuya.iot;

import org.richrocksmy.tuya.reliabletuya.model.Device;

import java.util.List;

public interface IoTController {

    List<Device> getAllDevices();
    void turnDeviceOn(String deviceId);

    void turnDeviceOff(String deviceId);

    Device.State queryState(String deviceId);
}
