import junit.framework.TestCase;
import org.junit.Test;
import org.opencrash.ExceptionParser;
import org.opencrash.domain_objects.ParserObject;
import org.opencrash.util.ApiExceptions;

/**
 * Created by Shichirin on 11.06.2014.
 */
public class ExceptionParserTest extends TestCase {

    @Test
    public void testParseFail(){
        ExceptionParser parser = new ExceptionParser("");
        ParserObject object = null;
        boolean throw_check = false;
        try {
            object = parser.parse();
        }catch (ApiExceptions e){
            throw_check = true;
        }
        assertTrue(throw_check);
    }
    @Test
    public void testParse(){
        ExceptionParser parser = new ExceptionParser("{\"client\": {\"name\": \"target-app-name\",\"version\": \"0.1\"}, \"request\": {\"custom_data\": {\"key1\": \"value1\",\"key2\": \"value2\"},\"exception\": {\"body\": \"org.postgresql.util.PSQLException: Connection refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections. at org.postgresql.core.v3.ConnectionFactoryImpl.openConnectionImpl(ConnectionFactoryImpl.java:207) at org.postgresql.core.ConnectionFactory.openConnection(ConnectionFactory.java:64) at org.postgresql.jdbc2.AbstractJdbc2Connection.<init>(AbstractJdbc2Connection.java:136) at org.postgresql.jdbc3.AbstractJdbc3Connection.<init>(AbstractJdbc3Connection.java:29) at org.postgresql.jdbc3g.AbstractJdbc3gConnection.<init>(AbstractJdbc3gConnection.java:21) at org.postgresql.jdbc4.AbstractJdbc4Connection.<init>(AbstractJdbc4Connection.java:31) at org.postgresql.jdbc4.Jdbc4Connection.<init>(Jdbc4Connection.java:24) at org.postgresql.Driver.makeConnection(Driver.java:410) at org.postgresql.Driver.connect(Driver.java:280) at com.mchange.v2.c3p0.DriverManagerDataSource.getConnection(DriverManagerDataSource.java:146) at com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:195) at com.mchange.v2.c3p0.WrapperConnectionPoolDataSource.getPooledConnection(WrapperConnectionPoolDataSource.java:184) at com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:200) at com.mchange.v2.resourcepool.BasicResourcePool.doAcquire(BasicResourcePool.java:1086) at com.mchange.v2.resourcepool.BasicResourcePool.doAcquireAndDecrementPendingAcquiresWithinLockOnSuccess(BasicResourcePool.java:1073) at com.mchange.v2.resourcepool.BasicResourcePool.access$800(BasicResourcePool.java:44) at com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1810)at com.mchange.v2.async.ThreadPoolAsynchronousRunner$PoolThread.run(ThreadPoolAsynchronousRunner.java:648) Caused by: java.net.ConnectException: Connection refused at java.net.PlainSocketImpl.socketConnect(Native Method) at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:327) at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:193) at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:180) at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:385) at java.net.Socket.connect(Socket.java:546) at java.net.Socket.connect(Socket.java:495)at org.postgresql.core.PGStream.<init>(PGStream.java:60) at org.postgresql.core.v3.ConnectionFactoryImpl.openConnectionImpl(ConnectionFactoryImpl.java:101) ... 17 more\",\"message\": \"java.lang.RuntimeException: some exception message\",\"where\": \"SomeClass.java:58\",\"class\": \"java.lang.RuntimeException\",\"backtrace\": \"BACKTRACE\"},\"application_environment\": {\"phone\": \"phone name + model\",\"appver\": \"1.2\",\"appname\": \"com.someapp\",\"osver\": \"4.1\",\"wifi_on\": \"true\",\"mobile_net_on\": \"true\",\"gps_on\": \"true\",\"screen_dpi(x:y)\": \"120.0:120.0\",\"screen:width\": \"240\",\"screen:height\": \"400\",\"screen:orientation\": \"normal\",\"uid\": \"11z22c5\"},\"feedback\": {\"email\": \"test@bugsense.com\",\"description\": \"I just had a crash :(\"}}}");
        ParserObject object = null;
        boolean throw_check = false;
        try {
            object = parser.parse();
        }catch (ApiExceptions e){
            throw_check = true;
        }
        assertFalse(throw_check);
    }
}
