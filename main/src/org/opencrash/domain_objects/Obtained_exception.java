package org.opencrash.domain_objects;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Fong on 12.05.14.
 */
@Entity
@Table(name="obtained_exceptions")
public class Obtained_exception extends IdentifiableEntity{
    private User user;
    private Exception_class exception_class;
    private String backtrace;
    private String message;
    private Application application;
    private Date create_at;

    @ManyToOne
    @Cascade({CascadeType.MERGE, CascadeType.SAVE_UPDATE})
    @JoinColumn(name="aplication_users_id")
    public User getUser() {
        return user;
    }

    public void setUser(User applicationuser) {
        this.user = applicationuser;
    }
    @ManyToOne
    @JoinColumn(name="exception_class_id")
    public Exception_class getException_class() {
        return exception_class;
    }

    public void setException_class(Exception_class exception_class) {
        this.exception_class = exception_class;
    }

    @Column(name="backtrace",columnDefinition = "text")
    public String getBacktrace() {
        return backtrace;
    }

    public void setBacktrace(String backtrace) {
        this.backtrace = backtrace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ManyToOne
    @JoinColumn(name="application_id")
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}
