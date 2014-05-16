package org.opencrash.domain_objects;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fong on 14.05.14.
 */
@Entity
@Table(name ="users")
public class Register_user extends IdentifiableEntity {
    private String username;
    private String email;
    private String password;
    private boolean activate;
    private Set<Application> applications = new HashSet<Application>(0);

    @Column(name = "username",length = 25,unique = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name="email",length = 60,unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name="password",length = 120,unique = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "activate")
    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    @OneToMany(mappedBy = "register_user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}
