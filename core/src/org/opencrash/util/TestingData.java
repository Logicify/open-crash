package org.opencrash.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Fong on 18.06.14.
 */
public class TestingData {
    Logger logger = Logger.getLogger(TestingData.class.getName());

    public String getJsonData(){
        String data = null;
        try{
            data = readFile("test.json");
        }catch (IOException e){
            logger.log(Level.SEVERE,"File error:",e);
        }
        return data;
    }

    private String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
