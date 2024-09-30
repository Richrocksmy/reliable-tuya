package org.richrocksmy.tuya.reliabletuya.command;

import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.output.Output;
import org.richrocksmy.tuya.reliabletuya.service.DeviceService;
import org.richrocksmy.tuya.reliabletuya.service.ScheduleService;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Locale;

@ShellComponent
public class Commands {

    private final DeviceService deviceService;

    private final ScheduleService scheduleService;

    private final Output output;
    public Commands(final DeviceService deviceService, final ScheduleService scheduleService,
                    final Output output) {
        this.deviceService = deviceService;
        this.scheduleService = scheduleService;
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

    @ShellMethod(key="schedule")
    public void scheduleDevice(@Option(required = true, description = "The device id") String deviceId,
                               @Option(required = true, description = "The schedule type") String schedule) {
        if(!"SUNSET".equals(schedule.toUpperCase(Locale.UK))) {
            throw new UnsupportedOperationException("Currently the application only supports sunset scheduling");
        }
        scheduleService.scheduleDevice(deviceId, schedule);
    }
}
