package org.opencrash.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.opencrash.domain_objects.FilterObject;
import org.opencrash.domain_objects.ObtainedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Fong on 20.06.14.
 */
public class FiltersParser {
    Logger logger = Logger.getLogger(FiltersParser.class.getName());
    private String json;
    private FilterObject filterObject;

    public FiltersParser(String json) {
        this.json = json;
    }
    public void Parse()throws ApiExceptions{
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(this.json);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray filters= (JSONArray) jsonObject.get("filters");
            JSONArray group = (JSONArray) jsonObject.get("group");
            FilterObject filterObj = new FilterObject();
            for(int i=0;i<filters.size();i++){
                JSONObject f = (JSONObject) filters.get(i);
                String filter_type =  f.get("filter").toString();
               if(filter_type.equals("exceptionClass")){
                    filterObj.setClassFilter(true);
                    JSONArray classes =(JSONArray) f.get("value");
                    List<Integer> list = new ArrayList<Integer>();
                    for (int j=0; j<classes.size(); j++) {
                       list.add( Integer.parseInt(classes.get(j).toString()));
                    }
                    filterObj.setClassesId(list);
               }else if(filter_type.equals("date")){
                    filterObj.setDateFilter(true);
                    HashMap<String,String> params = new HashMap<String, String>();
                    String operation = f.get("operation").toString();
                    params.put("operation",operation);
                    if(operation.equals("from_to")){
                        params.put("from",f.get("from").toString());
                        params.put("to",f.get("to").toString());
                    }else{
                        params.put("date",f.get("date").toString());
                    }
                   filterObj.setDateParameters(params);
               }else if(filter_type.equals("user")){
                    filterObj.setUserFilter(true);
                   JSONArray users =(JSONArray) f.get("user");
                   List<Integer> list = new ArrayList<Integer>();
                   for (int j=0; j<users.size(); j++) {
                       list.add( Integer.parseInt(users.get(j).toString()));
                   }
                    filterObj.setUsersId(list);
               }else if(filter_type.equals("application")){
                   filterObj.setApplicationFilter(true);
                   JSONArray applications =(JSONArray) f.get("application");
                   List<Integer> list = new ArrayList<Integer>();
                   for (int j=0; j<applications.size(); j++) {
                       list.add( Integer.parseInt(applications.get(j).toString()));
                   }
                   filterObj.setApplicationsId(list);
               }
            }
            if(group.size() != 0){
                filterObj.setGrouping(true);
                List<String> list = new ArrayList<String>();
                for (int j=0; j<group.size(); j++) {
                    list.add(group.get(j).toString());
                }
                filterObj.setGroup_by(list);
            }
            this.filterObject = filterObj;
        }catch (Exception e){

        }
    }
    public FilterObject getFilters(){
        return  this.filterObject;
    }

    public String getResult(List<ObtainedException> list,Integer total,boolean grouping){
        JSONArray jsonArray = new JSONArray();
        JSONObject total_page= new JSONObject();
        total_page.put("total",total);
        jsonArray.add(total);
        if(grouping){
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Object[] row = (Object[])iterator.next();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",row[2].toString());
                jsonObject.put("exceptionClass",row[3].toString());
                jsonObject.put("count",row[0].toString());
                jsonObject.put("date",row[1].toString());
                jsonObject.put("appId",row[4].toString());
                jsonObject.put("appName",row[5].toString());
                jsonArray.add(jsonObject);
            }
        }else
        for(int i=0;i<list.size();i++){
            ObtainedException obj = list.get(i);
            JSONObject jsonObject = new JSONObject();
            Integer id = obj.getId();
            jsonObject.put("id",id.toString());
            jsonObject.put("exceptionClass",obj.getExceptionClass().getException_class());
            jsonObject.put("application",obj.getApplication().getName());
            jsonObject.put("application_id",obj.getApplication().getId());
            jsonObject.put("date",obj.getCreate_at().toString());
            jsonObject.put("message",obj.getMessage());
            jsonArray.add(jsonObject);
        }
        json = jsonArray.toJSONString();
        return json;
    }
    public String getSortField(String filed){
        String column=null;
        if(filed.equals("date"))
            column = "create_at";
        if(filed.equals("class"))
            column = "exc.id";
        if(filed.equals("message"))
            column = "message";
        if(filed.equals("application"))
            column = "app.id";
        return column;
    }
}
