package org.richrocksmy.tuya.reliabletuya.service;

import org.richrocksmy.tuya.reliabletuya.model.Schedule;
import org.richrocksmy.tuya.reliabletuya.repository.DeviceScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.richrocksmy.tuya.reliabletuya.model.Device.State.OFF;
import static org.richrocksmy.tuya.reliabletuya.model.Device.State.ON;
import static org.richrocksmy.tuya.reliabletuya.model.Schedule.ScheduleType.RECURRING;

@Service
public class ScheduleService {

    private DeviceScheduleRepository deviceScheduleRepository;

    private DaylightTimeProvider daylightTimeProvider;

    public ScheduleService(final DeviceScheduleRepository deviceScheduleRepository,
                           final DaylightTimeProvider daylightTimeProvider) {
        this.deviceScheduleRepository = deviceScheduleRepository;
        this.daylightTimeProvider = daylightTimeProvider;
    }

    public void scheduleDevice(final String deviceId, final String schedule) {
        Instant sunsetTime = daylightTimeProvider.getNextSunset();

        Schedule onSchedule = Schedule.builder().deviceId(deviceId)
                .activationTime(sunsetTime.atZone(ZoneOffset.UTC))
                .desiredState(ON)
                .scheduleType(RECURRING).build();
        Schedule offSchedule = Schedule.builder().deviceId(deviceId)
                .activationTime(sunsetTime.plus(5, ChronoUnit.HOURS).atZone(ZoneOffset.UTC))
                .desiredState(OFF)
                .scheduleType(RECURRING).build();

        deviceScheduleRepository.saveAll(List.of(onSchedule, offSchedule));
    }
}
