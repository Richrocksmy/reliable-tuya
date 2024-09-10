package org.richrocksmy.tuya.reliabletuya.iot.tuya;

import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;

import java.util.List;

public interface TuyaApi {

    @GET("/v1.0/homes/{home_id}/devices")
    List<TuyaDevice> getAllDevices(@Path("home_id") final long homeId);

    @GET("/v1.0/devices/{device_id}")
    TuyaDevice getById(@Path("device_id") final String deviceId);

    @POST("/v1.0/iot-03/devices/{device_id}/commands")
    Boolean sendCommands(final String deviceId, final TuyaCommand tuyaCommand);
}
