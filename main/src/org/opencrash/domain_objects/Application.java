package org.opencrash.domain_objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Fong on 07.05.14.
 */
@Entity
@Table(name="applications")
public class Application extends IdentifiableEntity {
    private String name;
    private String version;

    @Column(name="name",length = 80,nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name="version",length = 15,nullable = false)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
