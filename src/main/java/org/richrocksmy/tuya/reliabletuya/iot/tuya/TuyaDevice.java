package org.richrocksmy.tuya.reliabletuya.iot.tuya;

import java.util.List;
import java.util.Map;

public record TuyaDevice(Long active_time,
                         int biz_type,
                         String category,
                         Long create_time,
                         String icon,
                         String id,
                         String ip,
                         String local_key,
                         String model,
                         String name,
                         Boolean online,
                         String owner_id,
                         String product_id,
                         String product_name,
                         List<Map<String, Object>> status,
                         Boolean sub,
                         String time_zone,
                         String uid,
                         Long update_time,
                         String uuid) {

}
