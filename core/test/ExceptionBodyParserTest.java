import junit.framework.TestCase;
import org.junit.Test;
import org.opencrash.util.ApiExceptions;
import org.opencrash.util.ExceptionBodyParser;

/**
 * Created by Fong on 03.06.14.
 */

public class ExceptionBodyParserTest extends TestCase {

    @Test
    public void testParse(){
        ExceptionBodyParser exceptionBodyParser = new ExceptionBodyParser("Some text");
        exceptionBodyParser.parse();
    }
    @Test
    public void testGetExceptionClass() throws ApiExceptions{
        ExceptionBodyParser exceptionBodyParser = new ExceptionBodyParser("Some text");
        boolean throw_check = false;
        try {
            exceptionBodyParser.getExceptionClass();
        }catch (ApiExceptions e){
            throw_check = true;
        }
        assertTrue(throw_check);
        ExceptionBodyParser exceptionBodyParser1 = new ExceptionBodyParser("class: message. at ");
        assertEquals("class",exceptionBodyParser1.getExceptionClass());
    }

    @Test(expected =ApiExceptions.class)
    public void testGetMessage() throws ApiExceptions{
        ExceptionBodyParser exceptionBodyParser = new ExceptionBodyParser("Some text");
        boolean throw_check = false;
        try {
            exceptionBodyParser.getMessage();
        }catch (ApiExceptions e){
            throw_check = true;
        }
        assertTrue(throw_check);
        ExceptionBodyParser exceptionBodyParser_1 = new ExceptionBodyParser("class: message. at ");
        assertEquals("message",exceptionBodyParser_1.getMessage());
    }

    @Test(expected =ApiExceptions.class)
    public void testGetBacktrace() throws ApiExceptions{
        ExceptionBodyParser exceptionBodyParser = new ExceptionBodyParser("Some text");
        boolean throw_check = false;
        try {
            exceptionBodyParser.getBacktrace();
        }catch (ApiExceptions e){
            throw_check = true;
        }
        assertTrue(throw_check);
        ExceptionBodyParser exceptionBodyParser_1 = new ExceptionBodyParser("class: message. at backtrace");
        assertEquals("at backtrace",exceptionBodyParser_1.getBacktrace());
    }
}
