package com.httpserveremanuel.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.httpserveremanuel.httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {


    public static ConfigurationManager myConfigurationManager;
    private static  Configuration myCurrentconfiguration;
    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance(){
        if(myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;

    }
    //
    //USed to load a configuration file by the path provided
    //
    public void localConfiguration(String filePath) {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HTTPConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();

        // NOw we going to read everything in the file so we need a while or for loop
        int i;
        try {
            while ( (  i = fileReader.read()) != -1){
                sb.append((char)i);
            }
        }catch (IOException e){
            throw new HTTPConfigurationException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HTTPConfigurationException("Error parsing the COnfiguration file",e);
        }
        //We use that note from our Json file
        try {
            myCurrentconfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HTTPConfigurationException("Error parsing the confiuration file,internal",e);
        }

    }
    //
    //Returns the Current loaded Configuration
    //
    public  Configuration getCurrentConfiguration(){

        if( myCurrentconfiguration == null){
            throw new HTTPConfigurationException("NO Curretn Configuration Set.");
        }
        return myCurrentconfiguration;

    }
}
