package org.richrocksmy.tuya.reliabletuya.repository;

import org.richrocksmy.tuya.reliabletuya.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    Device findDeviceByDeviceId(final String deviceId);
}
