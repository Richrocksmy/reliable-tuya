package org.richrocksmy.tuya.reliabletuya.service;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class DaylightTimeProvider {

    public Instant getNextSunset() {
        return Instant.now();
    }

    public Instant getNextSunrise() {
        return Instant.now();
    }
}
