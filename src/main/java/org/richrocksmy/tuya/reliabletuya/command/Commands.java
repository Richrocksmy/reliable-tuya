package org.richrocksmy.tuya.reliabletuya.command;

import org.richrocksmy.tuya.reliabletuya.output.Output;
import org.richrocksmy.tuya.reliabletuya.service.DeviceService;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

@Command
public class Commands {

    private final DeviceService deviceService;

    private final Output output;

    public Commands(final DeviceService deviceService, final Output output) {
        this.deviceService = deviceService;
        this.output = output;
    }

    @Command(command="list-devices", description="List all available devices")
    public void listDevices() {
        output.write(deviceService.listDevices().toString());
    }

    @Command(command="on", description="Turns a device on")
    public void deviceOn(@Option(required = true, description = "The device id") String deviceId) {
        deviceService.turnDeviceOn(deviceId);
    }

    @Command(command="off", description="Turns a device off")
    public void deviceOff(@Option(required = true, description = "The device id") String deviceId) {
        deviceService.turnDeviceOff(deviceId);
    }
}
