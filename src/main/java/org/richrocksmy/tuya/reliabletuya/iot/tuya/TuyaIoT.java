package org.richrocksmy.tuya.reliabletuya.iot.tuya;

import org.richrocksmy.tuya.reliabletuya.iot.IoTController;
import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TuyaIoT implements IoTController {

    private final long HOME_ID;
    private final Tuya tuya;

    public TuyaIoT(@Value("${tuya.home-id}") final long homeId,
                   final Tuya tuya) {
        this.HOME_ID = homeId;
        this.tuya = tuya;
    }

    @Override
    public List<Device> getAllDevices() {
        return tuya.getAll(HOME_ID).stream().filter(it -> it.id() != null).map(Device::new).toList();
    }
    @Override
    public void turnDeviceOn(String deviceId) {

    }

    @Override
    public void turnDeviceOff(String deviceId) {

    }

    @Override
    public Device.State queryState(String deviceId) {
        return null;
    }
}
