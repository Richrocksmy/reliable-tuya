package org.richrocksmy.tuya.reliabletuya.iot;

import org.richrocksmy.tuya.reliabletuya.model.Device;

import java.util.List;

public interface IoTController {

    List<Device> getAllDevices();
    boolean turnDeviceOn(String deviceId);

    boolean turnDeviceOff(String deviceId);

    Device.State queryState(String deviceId);
}
