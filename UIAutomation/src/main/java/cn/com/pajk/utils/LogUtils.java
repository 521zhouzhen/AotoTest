package cn.com.pajk.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
    public static void info(Class clss,String msg){
        Logger logger = LoggerFactory.getLogger(clss.getName());
        if (logger.isInfoEnabled()){
            logger.info(msg);
        }
    }
    public static void info(Object object,String msg){
       info(object.getClass(),msg);
    }
    public static void info(String msg){
        Logger logger = LoggerFactory.getLogger(LogUtils.class);
        logger.info(msg);
    }
    public static void debug(Class clss,String msg){
        Logger logger = LoggerFactory.getLogger(clss.getName());
        if (logger.isInfoEnabled()){
            logger.debug(msg);
        }
    }
    public static void debug(Object object,String msg){
        debug(object.getClass(),msg);
    }
    public static void debug(String msg){
        Logger logger = LoggerFactory.getLogger(LogUtils.class);
        logger.debug(msg);
    }
    public static void warn(Class clss,String msg){
        Logger logger = LoggerFactory.getLogger(clss.getName());
        if (logger.isInfoEnabled()){
            logger.warn(msg);
        }
    }
    public static void warn(Object object,String msg){
        warn(object.getClass(),msg);
    }
    public static void warn(String msg){
        Logger logger = LoggerFactory.getLogger(LogUtils.class);
        logger.warn(msg);
    }
    public static void error(Class clss,String msg){
        Logger logger = LoggerFactory.getLogger(clss.getName());
        if (logger.isInfoEnabled()){
            logger.error(msg);
        }
    }
    public static void error(Object object,String msg){
        info(object.getClass(),msg);
    }
    public static void error(String msg){
        Logger logger = LoggerFactory.getLogger(LogUtils.class);
        logger.error(msg);
    }
}

