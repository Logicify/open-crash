package com.opencrash.mvc;

import org.junit.Test;
import org.opencrash.api.ExceptionClassService;
import org.opencrash.domain_objects.ExceptionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Created by Fong on 19.06.14.
 */
@ContextConfiguration("classpath:testContext.xml")
public class ApiTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    public ExceptionClassService exceptionClassService;

    @Test
    public void testCreate() {
        ExceptionClass exceptionClass = new ExceptionClass();
        exceptionClass.setException_class("1");
        exceptionClassService.AddNewClass(exceptionClass);

    }
}
