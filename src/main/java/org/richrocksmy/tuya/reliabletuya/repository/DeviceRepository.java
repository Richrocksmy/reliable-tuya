package org.richrocksmy.tuya.reliabletuya.repository;

import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    Optional<Device> findDeviceByDeviceId(final String deviceId);

    List<Device> findDevicesByStateIn(List<Device.State> states);
}
