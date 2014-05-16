package org.opencrash.domain_objects;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fong on 12.05.14.
 */
@Entity
@Table(name = "exception_classes")
public class Exception_class extends IdentifiableEntity {
    private String exception_class;
    private Set<Obtained_exception>obtained_exception = new HashSet<Obtained_exception>(0);

    @Column(name = "exception_class",length = 40,nullable = false,unique = true)
    public String getException_class() {
        return exception_class;
    }

    public void setException_class(String exception_class) {
        this.exception_class = exception_class;
    }
    @OneToMany(mappedBy = "exception_class", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Obtained_exception> getObtained_exception() {
        return obtained_exception;
    }

    public void setObtained_exception(Set<Obtained_exception> obtained_exceptions) {
        this.obtained_exception = obtained_exceptions;
    }
}
