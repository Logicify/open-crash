package org.opencrash;


import org.json.JSONObject;

/**
 * Created by Fong on 07.05.14.
 */
public class ExceptionParser {
    private String json;
    private JSONObject exception;
    private JSONObject application_environment;
    private JSONObject client;


    public ExceptionParser(String json) {
        this.json = json;
    }

    public void parse(){

        JSONObject o = new JSONObject(json);
        JSONObject request = (JSONObject) o.get("request");
        exception = (JSONObject)  request.get("exception");
        application_environment = (JSONObject) request.get("application_environment");
        client = (JSONObject) o.get("client");
    }


    public JSONObject getException() {
        return exception;
    }

    public JSONObject getApplication_environment() {
        return application_environment;
    }

    public boolean checkExceptionBody(){
        String body = exception.get("body").toString();
        if(body.isEmpty())
            return false;
        else
            return true;
    }

    public String getExceptionClass(){
        return exception.get("class").toString();
    }

    public String getExceptionMassage(){
        return exception.get("message").toString();
    }

    public String getExceptionBacktrace(){
        return exception.get("backtrace").toString();
    }

    public String getUserID(){
        return  application_environment.get("uid").toString();
    }

    public String getApplication_environment_parameter(String param) {
        return application_environment.get(param).toString();
    }

    public String getApplicationName(){
        return client.get("name").toString();
    }

    public String getAppVersion(){
        return client.get("version").toString();
    }
}
