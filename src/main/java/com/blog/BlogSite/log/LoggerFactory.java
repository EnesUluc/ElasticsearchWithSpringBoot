package com.blog.BlogSite.log;


public class LoggerFactory {
    private static LoggerFactory logger;

    private LoggerFactory( ){}

    public static LoggerFactory getInstance(){
        if(logger == null){
            return new LoggerFactory();
        }
        return logger;
    }
     public void writeLog(String message){
        System.out.println(message);

    }
}
