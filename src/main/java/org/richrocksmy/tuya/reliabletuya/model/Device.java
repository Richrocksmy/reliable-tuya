package org.richrocksmy.tuya.reliabletuya.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.richrocksmy.tuya.reliabletuya.iot.tuya.TuyaDevice;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.function.Function;

@Data
@Entity
@Builder
@Table(name = "Devices")
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    public enum State {
        ON,
        OFF
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ip;

    private String deviceId;

    private String localKey;

    private String name;

    private State state;

    public Device(final TuyaDevice tuyaDevice) {
        this.ip = tuyaDevice.ip();
        this.deviceId = tuyaDevice.id();
        this.localKey = tuyaDevice.local_key();
        this.name = tuyaDevice.name();
    }

    public static Pair<List<String>, Function<Device, List<String>>> getColumnProvider() {
        return Pair.of(List.of("Id", "IP", "Device Id", "Local Key", "Name"),
                (device) -> List.of(String.valueOf(device.id), device.ip, device.deviceId, device.localKey, device.name));
    }
}
