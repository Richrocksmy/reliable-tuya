package org.richrocksmy.tuya.reliabletuya.iot;

import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.model.State;

import java.util.List;

public interface IoTController {

    List<Device> getAllDevices();
    void turnDeviceOn(String deviceId);

    void turnDeviceOff(String deviceId);

    State queryState(String deviceId);
}
