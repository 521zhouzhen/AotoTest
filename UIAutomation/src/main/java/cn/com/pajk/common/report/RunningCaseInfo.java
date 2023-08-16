package cn.com.pajk.common.report;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;

public class RunningCaseInfo {
    public static String caseName;
    public static String caseInstanceName;
    public static String description;
    public static String caseStatusResult;
    public static String caseErrorLog;
    public static String caseOwner;
    protected static Logger logger=Logger.getLogger(RunningCaseInfo.class);
    private static JsonArray caseJsonInfo;
    public static String getCaseNamePrefix(){
        if ((caseName!=null)&& (caseInstanceName!=null)){
            return String.format("%s,%s",caseInstanceName,caseName);
        }else {
            return null;
        }
    }
    public static String getCaseOwnerBy(String casePkg,String caseName){
        String owner="";
        try {
            if (caseJsonInfo==null){
                InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream("caseinfo.json");
                String jsonStr= IOUtils.toString(inputStream, Charset.defaultCharset());
                JsonParser parser=new JsonParser();
                JsonElement jsonElement = parser.parse(jsonStr);
                caseJsonInfo=new JsonArray();
                caseJsonInfo=jsonElement.getAsJsonArray();
            }
            Iterator iterator=caseJsonInfo.iterator();
            while (iterator.hasNext()){
                JsonObject jsonPackage=(JsonObject) iterator.next();
                String pkg=jsonPackage.get("package").getAsString();
                if (pkg.equals(casePkg)){
                    if (jsonPackage.equals(casePkg)){
                        if (jsonPackage.get("owner")!=null){
                            owner=jsonPackage.get("owner").getAsString();
                        }
                        JsonArray caselist=jsonPackage.getAsJsonArray("cases");
                        Iterator it=caselist.iterator();
                        while (it.hasNext()){
                            JsonObject jsonCase=(JsonObject)it.next();
                            String name=jsonCase.get("case").getAsString();
                            if (name.equals(caseName)){
                                if (jsonCase.get("owner")!=null){
                                    owner=jsonCase.get("owner").getAsString();
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
logger.error("An exception is thrown when trying to read case info from json file - caseinfo.json");
        }
        return owner;
    }
}
