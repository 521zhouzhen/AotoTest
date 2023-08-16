package cn.com.pajk.utils;




import org.testng.Assert;

import java.io.*;
import java.util.*;

public class ConfigProperty {
    public static Map<String,String> propMap=new HashMap<>();
    public static String get(String key){return  propMap.get(key);}
    static {
        //初始化config.properties 文件
        InputStreamReader inputStreamReader=null;
        try {
            inputStreamReader=new InputStreamReader(ConfigProperty.class.getClassLoader().getResourceAsStream("config.properties"),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        Properties prop=new Properties();
        if (inputStreamReader!=null){
            try {
                prop.load(inputStreamReader);
                Set<Object> keySet = prop.keySet();
                for (Object object :keySet) {
                    String propKey=object.toString().trim();
                    String propValue=prop.getProperty(propKey).toString().trim();
                    propMap.put(propKey,propValue);
                }

            }catch (Exception e){
                try {
                    inputStreamReader.close();
                }catch (IOException ioException){

                }
            }
        }
     /*   if (!StringUtils.isNullOrEmpty("Config.runmode")){
            propMap.put("runmode",System.getProperty("Config.runmode"));
        }*/
        if (propMap.containsKey("runmode")){
            InputStreamReader input=null;
            try {
                String configFilePath="config/config.properties."+propMap.get("runmode").toLowerCase();
                System.out.println("=============="+configFilePath);
                input=new InputStreamReader(ConfigProperty.class.getClassLoader().getResourceAsStream(configFilePath),"UTF-8");
            }catch (IOException ioException){

            }
            Properties properties=new Properties();
            if (input!=null){
                try {
                    System.out.println("1111==================="+propMap);
                    properties.load(input);
                    Set<Object> keySet = properties.keySet();
                    for (Object object :keySet) {
                        String propKey=object.toString().trim();
                        String propValue=properties.getProperty(propKey).toString().trim();
                        propMap.put(propKey,propValue);
                        System.out.println(propKey+"============="+propValue);
                    }

                }catch (Exception e){
                    System.out.println(e.toString());
                    try {
                        input.close();
                    }catch (IOException ioException){

                    }
                }
            }
            if (!StringUtils.isNullOrEmpty("Config.serverIP")){
                propMap.put("serverIP",System.getProperty("config.serverIP"));
            }

        }
    }
    public static void setConfig(String path,String key,String value) throws IOException{
        File file=new File(path);
        //read
        StringBuffer sb=new StringBuffer();
        String line=null;
        FileReader fileReader=new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        while ((line=bufferedReader.readLine())!=null){
            if (!line.trim().startsWith("#")&& line.contains("=")&& line.contains(key) && line.split("=")[0].trim().equals(key)){
                String oldValue=line.split("=")[1].trim();
                line=line.replace(oldValue,value);
            }
            sb.append(line+"\n");
        }
        fileReader.close();
        bufferedReader.close();
        //write
        FileWriter fileWriter=new FileWriter(path);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
       bufferedWriter.write(sb.toString());
       bufferedWriter.flush();
       fileWriter.close();
       bufferedReader.close();
       System.out.println("[ "+key+" ]"+" 's value update to ["+value+"] Success!");
    }
}

