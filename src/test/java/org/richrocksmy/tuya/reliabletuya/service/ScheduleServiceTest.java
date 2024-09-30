package org.richrocksmy.tuya.reliabletuya.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.model.Schedule;
import org.richrocksmy.tuya.reliabletuya.repository.DeviceScheduleRepository;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ScheduleServiceTest {

    @Test
    @SuppressWarnings("unchecked")
    public void shouldScheduleForSunset() {
        DaylightTimeProvider daylightTimeProvider = mock(DaylightTimeProvider.class);
        DeviceScheduleRepository deviceScheduleRepository = mock(DeviceScheduleRepository.class);
        ScheduleService scheduleService = new ScheduleService(deviceScheduleRepository, daylightTimeProvider);
        String deviceId = "12345Id";
        when(deviceScheduleRepository.findScheduleByDeviceIdOrderByActivationTimeAsc(deviceId)).thenReturn(Collections.emptyList());

        Instant nextSunset = Instant.now().plusSeconds(30);

        when(daylightTimeProvider.getNextSunset()).thenReturn(nextSunset);
        scheduleService.scheduleDevice(deviceId, "sunset");

        ArgumentCaptor<List<Schedule>> scheduleArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(deviceScheduleRepository).saveAll(scheduleArgumentCaptor.capture());

        List<Schedule> schedule = scheduleArgumentCaptor.getValue();
        assertThat(schedule).containsExactlyInAnyOrder(
                Schedule.builder()
                        .deviceId(deviceId)
                        .activationTime(nextSunset.atZone(ZoneOffset.UTC))
                        .desiredState(Device.State.ON)
                        .scheduleType(Schedule.ScheduleType.RECURRING)
                        .build(),
                Schedule.builder()
                        .deviceId(deviceId)
                        .activationTime(nextSunset.plus(5, ChronoUnit.HOURS).atZone(ZoneOffset.UTC))
                        .desiredState(Device.State.OFF)
                        .scheduleType(Schedule.ScheduleType.RECURRING)
                        .build());

    }
}