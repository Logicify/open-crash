package org.opencrash.domain_objects;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fong on 07.05.14.
 */
@Entity
@Table(name="applications")
public class Application extends IdentifiableEntity {
    private String name;
    private String version;
    private Set<Obtained_exception> obtained_exception = new HashSet<Obtained_exception>(0);
    private Register_user register_user;
    private String application_key;

    @Column(name="application_name",length = 80,nullable = false)
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

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Obtained_exception> getObtained_exception() {
        return obtained_exception;
    }

    public void setObtained_exception(Set<Obtained_exception> obtained_exceptions) {
        this.obtained_exception = obtained_exceptions;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    public Register_user getRegister_user() {
        return register_user;
    }
    public void setRegister_user(Register_user register_user) {
        this.register_user = register_user;
    }

    @Column(name="key",unique = true,nullable = false)
    public String getApplication_key() {
        return application_key;
    }

    public void setApplication_key(String application_key) {
        this.application_key = application_key;
    }
}
