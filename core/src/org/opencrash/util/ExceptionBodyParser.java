package org.opencrash.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fong on 28.05.14.
 */
public class ExceptionBodyParser {
    private String body;
    private String exceptionClass;
    private String message;
    private String backtrace;

    public ExceptionBodyParser(String body) {
        this.body = body;
    }

    public String getExceptionClass() throws ApiExceptions{
        Pattern datePatt = Pattern.compile("([\\w\\.]*[^:])(:) ");
        Matcher m = datePatt.matcher(this.body);
        if (m.find()) {
            exceptionClass=m.group(1);
        }else
            throw new ApiExceptions("Cannot get `backtrace` from this data",8);
        return exceptionClass;
    }

    public String getMessage()throws ApiExceptions{
        Pattern datePatt = Pattern.compile("(:) ([\\/\\w\\.\\s]*)(. at)");
        Matcher m = datePatt.matcher(this.body);
        if (m.find()) {
            message=m.group(2);
        }
        else
            throw new ApiExceptions("Cannot get `backtrace` from this data",8);
        return message;
    }

    public String getBacktrace() throws ApiExceptions{
        Pattern datePatt = Pattern.compile("(.) (at .*)");
        Matcher m = datePatt.matcher(this.body);
        if (m.find()) {
            backtrace = m.group(2);
        }else
            throw new ApiExceptions("Cannot get `backtrace` from this data",8);
        return backtrace;
    }

    public void parse() {
        Pattern datePatt = Pattern.compile("([\\w\\.]*[^:]): ([\\/\\w\\.\\s]*) at (.*)");
        Matcher m = datePatt.matcher(this.body);
        if (m.matches()) {
            this.exceptionClass = m.group(1);
            this.message = m.group(1)+": "+m.group(2);
            this.backtrace ="at "+ m.group(3);
        }
    }
}
