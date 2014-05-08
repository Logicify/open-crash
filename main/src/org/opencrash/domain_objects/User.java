package org.opencrash.domain_objects;

import javax.persistence.*;

/**
 * Created by Fong on 07.05.14.
 */
@Entity
@Table(name="users")
public class User extends IdentifiableEntity {
    private Integer device_id;
    private Integer system_id;

    @Column(name="device_id",length = 11,nullable = false)
    public Integer getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Integer device_id) {
        this.device_id = device_id;
    }

    @Column(name="system_id",length = 11,nullable = false)
    public Integer getSystem_id() {
        return system_id;
    }

    public void setSystem_id(Integer system_id) {
        this.system_id = system_id;
    }
}
