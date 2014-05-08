package org.opencrash.domain_objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Fong on 07.05.14.
 */
@Entity
@Table(name="Exceptions")
public class Exception extends IdentifiableEntity {
    private String ExceptionClass;
    private String ExceptionText;
    private String ExceptionMassage;

    @Column(name="exceptionClass",length = 80,nullable = false)
    public String getExceptionClass() {
        return ExceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        ExceptionClass = exceptionClass;
    }
    @Column(name="ExceptionText",length = 80,nullable = false)
    public String getExceptionText() {
        return ExceptionText;
    }

    public void setExceptionText(String exceptionText) {
        ExceptionText = exceptionText;
    }
    @Column(name="ExceptionMassge",length = 150,nullable = false)
    public String getExceptionMassage() {
        return ExceptionMassage;
    }

    public void setExceptionMassage(String exceptionMassage) {
        ExceptionMassage = exceptionMassage;
    }
}
