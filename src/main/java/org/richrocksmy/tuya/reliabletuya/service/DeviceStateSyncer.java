package org.richrocksmy.tuya.reliabletuya.service;

import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.richrocksmy.tuya.reliabletuya.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.richrocksmy.tuya.reliabletuya.model.Device.State.OFF;
import static org.richrocksmy.tuya.reliabletuya.model.Device.State.ON;

@Service
public class DeviceStateSyncer {

    private static final int EXECUTOR_POOL_SIZE = 1;

    private final boolean DEVICE_SYNC_ENABLED;

    private final long INITIAL_DELAY;

    private final long PERIOD;

    private final DeviceService deviceService;

    private final DeviceRepository deviceRepository;

    private ScheduledExecutorService scheduledExecutorService;

    public DeviceStateSyncer(@Value("${reliable-tuya.device-sync.enabled}") final boolean deviceSyncEnabled,
                             @Value("${reliable-tuya.device-sync.initial-delay}") final long initialDelay,
                             @Value("${reliable-tuya.device-sync.period}") final long period,
                             final DeviceService deviceService,
                             final DeviceRepository deviceRepository) {
        this.DEVICE_SYNC_ENABLED = deviceSyncEnabled;
        this.INITIAL_DELAY = initialDelay;
        this.PERIOD = period;
        this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
    }

    @PostConstruct
    private void schedule() {
        if(DEVICE_SYNC_ENABLED) {
            scheduledExecutorService = Executors.newScheduledThreadPool(EXECUTOR_POOL_SIZE);
            ScheduledFuture<?> scheduledFuture = scheduledExecutorService
                    .scheduleAtFixedRate(this::sync, INITIAL_DELAY, PERIOD, TimeUnit.SECONDS);
        }
    }

    public void sync() {
        Map<Boolean, List<Device>> devices = deviceRepository.findDevicesByStateIn(List.of(ON, OFF)).stream()
                .collect(Collectors.partitioningBy(it -> Device.State.ON == it.getState()));
        devices.get(true).forEach(it -> deviceService.turnDeviceOn(it.getDeviceId()));
        devices.get(false).forEach(it -> deviceService.turnDeviceOff(it.getDeviceId()));
    }
}
