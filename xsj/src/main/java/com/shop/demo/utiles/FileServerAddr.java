package com.shop.demo.utiles;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import java.io.IOException;
import java.util.Properties;

public class FileServerAddr {
    private static  String serverAddr;

    static {
        Properties properties=new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("fileServer.properties"));
            serverAddr=properties.getProperty("fileserver");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String getFileServer(){
        return serverAddr;
    }
    public static void setFileServer(String serverAdder){
        FileServerAddr.serverAddr=serverAdder;
    }
}
