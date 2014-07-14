package org.opencrash.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

/**
 * Created by Fong on 14.07.14.
 */
public class Settings {
    private File config;
    private String pagination;
    public void getSettings(){
        JSONParser parser = new JSONParser();
        try {
            getConfig();
        }catch (IOException e){

        }
        try {
            Object obj = parser.parse(new FileReader(config));
            JSONObject jsonObject = (JSONObject) obj;
            pagination = jsonObject.get("pagination").toString();
        }catch (IOException e){

        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void getConfig() throws IOException{
        InputStream inputStream = null;
        Resource resource = null;
        File data = null;

        try {
            resource = new ClassPathResource("settings.json");
            data =  resource.getFile();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        config = data;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public void save() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pagination",pagination);
        try {
            FileWriter file = new FileWriter(config);
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
