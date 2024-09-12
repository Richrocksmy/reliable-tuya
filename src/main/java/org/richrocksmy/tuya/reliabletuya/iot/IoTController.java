package org.richrocksmy.tuya.reliabletuya.iot;

import org.richrocksmy.tuya.reliabletuya.model.Device;

import java.util.List;

public interface IoTController {

    List<Device> getAllDevices();

    Device getDevice(final String deviceId);
    boolean turnDeviceOn(final String deviceId);

    boolean turnDeviceOff(final String deviceId);

    Device.State queryState(final String deviceId);
}
