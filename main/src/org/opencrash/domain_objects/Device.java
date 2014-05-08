package org.opencrash.domain_objects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Fong on 07.05.14.
 */
@Entity
@Table(name="Device")
public class Device extends IdentifiableEntity {

    private String name;

    @Column(name="name",length =50,nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
