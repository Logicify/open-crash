package org.opencrash.domain_objects;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fong on 07.05.14.
 */
@Entity
@Table(name="systems")
public class MobileSystem extends IdentifiableEntity {
    private String name;
    private Set<Application> applications = new HashSet<Application>(0);

    @Column(name="name",length = 80,nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "mobileSystem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}
