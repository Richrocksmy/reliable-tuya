package org.richrocksmy.tuya.reliabletuya.repository;

import org.richrocksmy.tuya.reliabletuya.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceScheduleRepository extends CrudRepository<Schedule, Long> {

    List<Schedule> findScheduleByDeviceIdOrderByActivationTimeAsc(final String deviceId);

}
