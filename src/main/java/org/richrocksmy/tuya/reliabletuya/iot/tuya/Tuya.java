package org.richrocksmy.tuya.reliabletuya.iot.tuya;

import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.Path;

import java.util.List;

public interface Tuya {

    @GET("/v1.0/homes/{home_id}/devices")
    List<TuyaDevice> getAll(@Path("home_id") final long homeId);

    @GET("/v1.0/devices/{device_id}")
    Object getById(@Path("device_id") String deviceId);

}
