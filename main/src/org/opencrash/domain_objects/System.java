package org.opencrash.domain_objects;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fong on 07.05.14.
 */
@Entity
@Table(name="systems")
public class System extends IdentifiableEntity {
    private String name;
    private String version;
    private Set<User> applicationuser = new HashSet<User>(0);

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

    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<User> getApplicationuser() {
        return applicationuser;
    }

    public void setApplicationuser(Set<User> applicationusers) {
        this.applicationuser = applicationusers;
    }
}
