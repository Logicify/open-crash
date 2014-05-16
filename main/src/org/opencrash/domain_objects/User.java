package org.opencrash.domain_objects;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fong on 07.05.14.
 */
@Entity
@Table(name="application_users")
public class User extends IdentifiableEntity {
    private Device device;
    private System system;
    private String uid;
    private Set<Obtained_exception> obtained_exception = new HashSet<Obtained_exception>(0);

    @ManyToOne
    @JoinColumn(name="device_id",nullable = true)
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @ManyToOne
    @JoinColumn(name="system_id",nullable = true)
    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    @Column(name="uid",length = 11,nullable = false,unique = true)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Obtained_exception> getObtained_exception() {
        return obtained_exception;
    }

    public void setObtained_exception(Set<Obtained_exception> obtained_exceptions) {
        this.obtained_exception = obtained_exceptions;
    }
}
