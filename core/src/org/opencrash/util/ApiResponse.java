package org.opencrash.util;


import com.google.gson.Gson;
import org.opencrash.domain_objects.ApiResponseObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fong on 28.05.14.
 */
public class ApiResponse {
    private ApiResponseObject obj;
    private String responseJson;
    private Set<ApiExceptions> errors = new HashSet<ApiExceptions>();

    public ApiResponse(ApiResponseObject obj) {
        this.obj = obj;
    }

    public void setResponse(){
        Gson gson = new Gson();
        Date date= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy h:mm:ss a");
        obj.setTimestamp(sdf.format(date));
        responseJson =  gson.toJson(obj);
    }

    public String getResponseJson() {
        return responseJson;
    }
}
