package com.httpserveremanuel.httpserver;
//**
//
// Driver Class for the HTTP Server
//

import com.httpserveremanuel.httpserver.config.Configuration;
import com.httpserveremanuel.httpserver.config.ConfigurationManager;

//TODO Read Configuration Files
//TODO Open a socket to Listen at a Port
//TODO Read Request Messages
//TODO Open and read files from the Filesystem
//TODO Write Response Mensages
public class HTTP_Server {
    public static void main(String[] args){


        System.out.println("Server starting....");

        //Starting teh Configuration Manager we just called
        ConfigurationManager.getInstance().localConfiguration("src/main/resources/http.json");
        Configuration conf =  ConfigurationManager.getInstance().getCurrentConfiguration();


        System.out.println("Using Port ." + conf.getPort());
        System.out.println("Using Webroot ." + conf.getWebroot());

    }

}
