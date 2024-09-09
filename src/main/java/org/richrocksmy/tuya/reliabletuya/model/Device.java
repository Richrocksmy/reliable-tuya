package org.richrocksmy.tuya.reliabletuya.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Device {

    @Id
    private Long id;

}
