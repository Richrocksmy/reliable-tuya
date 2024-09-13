package org.richrocksmy.tuya.reliabletuya.command;

import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.output.Output;
import org.richrocksmy.tuya.reliabletuya.service.DeviceService;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class Commands {

    private final DeviceService deviceService;

    private final Output output;
    public Commands(final DeviceService deviceService, final Output output) {
        this.deviceService = deviceService;
        this.output = output;
    }

    @ShellMethod(key="list-devices")
    public void listDevices() {
        output.write(deviceService.listDevices(), Device.getColumnProvider());
    }

    @ShellMethod(key="on")
    public void deviceOn(@Option(required = true, description = "The device id") String deviceId) {
        deviceService.turnDeviceOn(deviceId);
    }

    @ShellMethod(key="off")
    public void deviceOff(@Option(required = true, description = "The device id") String deviceId) {
        deviceService.turnDeviceOff(deviceId);
    }
}
