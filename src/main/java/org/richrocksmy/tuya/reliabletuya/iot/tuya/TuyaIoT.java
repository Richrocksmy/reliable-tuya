package org.richrocksmy.tuya.reliabletuya.iot.tuya;

import org.richrocksmy.tuya.reliabletuya.iot.IoTController;
import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaCommand.TuyaCommandEntry.LIGHT_OFF;
import static org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaCommand.TuyaCommandEntry.LIGHT_ON;

@Component
public class TuyaIoT implements IoTController {

    private final long HOME_ID;
    private final TuyaApi tuyaApi;

    public TuyaIoT(@Value("${tuya.home-id}") final long homeId,
                   final TuyaApi tuyaApi) {
        this.HOME_ID = homeId;
        this.tuyaApi = tuyaApi;
    }

    @Override
    public List<Device> getAllDevices() {
        return tuyaApi.getAllDevices(HOME_ID).stream().filter(it -> it.id() != null).map(Device::new).toList();
    }
    @Override
    public boolean turnDeviceOn(final String deviceId) {
        return tuyaApi.sendCommands(deviceId, new TuyaCommand.Builder()
                .addCommand(LIGHT_ON)
                .build().asInterfaceType());
    }

    @Override
    public boolean turnDeviceOff(final String deviceId) {
        return tuyaApi.sendCommands(deviceId, new TuyaCommand.Builder()
                .addCommand(LIGHT_OFF)
                .build().asInterfaceType());
    }

    @Override
    public Device.State queryState(final String deviceId) {
        return null;
    }
}
