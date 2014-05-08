package org.opencrash;


import org.json.JSONObject;

/**
 * Created by Fong on 07.05.14.
 */
public class ExceptionParser {
    private String json;
    private JSONObject exception;
    private JSONObject application_environment;


    public ExceptionParser(String json) {
        this.json = json;
    }

    public void parse(){

        JSONObject o = new JSONObject(json);
        JSONObject request = (JSONObject) o.get("request");
        exception = (JSONObject)  request.get("exception");
        application_environment = (JSONObject) request.get("application_environment");

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

}
