package org.richrocksmy.tuya.reliabletuya.iot.tuya;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;

import java.util.List;
import java.util.Map;

public interface TuyaApi {

    @GET("/v1.0/homes/{home_id}/devices")
    List<TuyaDevice> getAllDevices(@Path("home_id") final long homeId);

    @GET("/v1.0/devices/{device_id}")
    TuyaDevice getDevice(@Path("device_id") final String deviceId);

    @POST("/v1.0/devices/{device_id}/commands")
    Boolean sendCommands(@Path("device_id") final String deviceId, @Body final Map<String, Object> tuyaCommand);
}
