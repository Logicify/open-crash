package org.opencrash;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.opencrash.domain_objects.ParserObject;
import org.opencrash.util.ApiExceptions;
import org.opencrash.util.ExceptionBodyParser;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fong on 07.05.14.
 */
public class ExceptionParser {
    private String json;
    Logger logger = Logger.getLogger(ExceptionParser.class.getName());

    public ExceptionParser(String json) {
        this.json = json;
    }


    public ParserObject parse()throws ApiExceptions{
        JSONParser parser = new JSONParser();
        ParserObject parserObject = new ParserObject();
        try {
            Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject request = getObjElement(jsonObject,"request");
            JSONObject exception = getObjElement(request, "exception");
            JSONObject application_environment = getObjElement(request, "application_environment");
            parserObject.setException(exception);
            parserObject.setApplicationEnvironment(application_environment);
            parserObject.setClient((JSONObject) jsonObject.get("client"));

            if(getStringElement(exception,"body").isEmpty()){
                parserObject.setBacktrace(getBacktrace(exception));
                parserObject.setMessage(getMessage(exception));
                parserObject.setExceptionClass(getExceptionClass(exception));
            }else{
                ExceptionBodyParser exceptionBodyParser = new ExceptionBodyParser(getStringElement(exception, "body"));
                //exceptionBodyParser.parse();
                parserObject.setBacktrace(exceptionBodyParser.getBacktrace());
                parserObject.setMessage(exceptionBodyParser.getMessage());
                parserObject.setExceptionClass(exceptionBodyParser.getExceptionClass());
            }

            parserObject.setUid(getUid(application_environment));
            parserObject.setDevice(getPhoneModel(application_environment.get("phone").toString()));
            if(application_environment.containsKey("wifi_on")){
                parserObject.setWifiStatus(getWifiStatus(application_environment));
            }
            if(application_environment.containsKey("gps_on")){
                parserObject.setGpsStatus(getGpsStatus(application_environment));
            }
            if(application_environment.containsKey("screen:orientation")){
                parserObject.setScreenOrientation(getScreenOrientation(application_environment));
            }
            if(application_environment.containsKey("screen:width")){
                parserObject.setScreenWidth(getScreenParam(application_environment, "width"));
            }
            if(application_environment.containsKey("screen:height")){
                parserObject.setScreenHeight(getScreenParam(application_environment, "height"));
            }
            if(application_environment.containsKey("osver")){
                parserObject.setOsVersion(getStringElement(application_environment,"osver"));
            }
        }catch (ParseException e){
            logger.log(Level.SEVERE,"Parser error:",e);
            throw new ApiExceptions("Data error."+e.getMessage(),4);
        }
        return parserObject;
    }

    private Integer getScreenParam(JSONObject application_environment, String param) {
        return Integer.parseInt(application_environment.get("screen:" + param).toString());
    }

    private String getBacktrace(JSONObject exception) throws ApiExceptions{
        return getStringElement(exception,"backtrace");
    }

    private String getMessage(JSONObject exception) throws  ApiExceptions{
        return getStringElement(exception,"message");
    }

    private String getUid(JSONObject request) throws ApiExceptions{
        return getStringElement(request,"uid");
    }
    private String getExceptionClass(JSONObject exception) throws ApiExceptions{
        return getStringElement(exception,"class");
    }
    private String getWifiStatus(JSONObject request) throws ApiExceptions{
        return  getStringElement(request,"wifi_on");
    }
    private String getGpsStatus(JSONObject application_environment) throws ApiExceptions{
        return getStringElement(application_environment,"gps_on");
    }
    private String getScreenOrientation(JSONObject application_environment) throws ApiExceptions{
        return  getStringElement(application_environment,"screen:orientation");
    }

    private String getStringElement(JSONObject request,String value) throws ApiExceptions{
        String data =null;
        if(request.containsKey(value))
            data = request.get(value).toString();
        else
            throw new ApiExceptions("Data error:Field "+value+" not found",5);
        return data;
    }
    private JSONObject getObjElement(JSONObject request,String value) throws  ApiExceptions{
        JSONObject object = null;
        if(request.containsKey(value))
            object = (JSONObject) request.get(value);
        else
            throw new ApiExceptions("Data error:Field `"+value+"` not found",5);
        return object;
    }

    private String getPhoneModel(String data){
        String model = null;
        Pattern datePatt = Pattern.compile("(.+)\\|(.+)");
        Matcher m = datePatt.matcher(data);
        if (m.find()) {
            model=m.group(2);
        }else
            logger.log(Level.SEVERE,"Cant get Phone Model");
        return model;
    }
}
