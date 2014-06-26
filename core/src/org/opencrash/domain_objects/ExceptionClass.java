package org.opencrash.domain_objects;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fong on 12.05.14.
 */
@Entity
@Table(name = "exception_classes")

public class ExceptionClass extends IdentifiableEntity {
    private String exception_class;
    private Set<ObtainedException> obtained_exception = new HashSet<ObtainedException>(0);

    @Column(name = "exception_class",length = 40,nullable = false,unique = true)
    public String getException_class() {
        return exception_class;
    }

    public void setException_class(String exception_class) {
        this.exception_class = exception_class;
    }
    @OneToMany(mappedBy = "exceptionClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<ObtainedException> getObtained_exception() {
        return obtained_exception;
    }

    public void setObtained_exception(Set<ObtainedException> obtained_exceptions) {
        this.obtained_exception = obtained_exceptions;
    }
}
