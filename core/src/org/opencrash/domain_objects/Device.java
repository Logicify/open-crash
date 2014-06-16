package org.opencrash.domain_objects;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fong on 07.05.14.
 */
@Entity
@Table(name="Device")
public class Device extends IdentifiableEntity {

    private String name;
    private Set<ObtainedException> obtainedExceptions = new HashSet<ObtainedException>(0);

    @Column(name="name",length =50,nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<ObtainedException> getObtainedExceptions() {
        return obtainedExceptions;
    }
    public void setObtainedExceptions(Set<ObtainedException> obtainedExceptions) {
        this.obtainedExceptions = obtainedExceptions;
    }
}
