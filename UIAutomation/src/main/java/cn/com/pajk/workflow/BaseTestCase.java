package cn.com.pajk.workflow;

import cn.com.pajk.common.report.RunningCaseInfo;
import cn.com.pajk.utils.Driver;
import cn.com.pajk.utils.MyFileUtils;
import cn.com.pajk.workflow.entity.Action;
import cn.com.pajk.workflow.entity.Actions;
import cn.com.pajk.workflow.entity.ProcessSet;
import cn.com.pajk.workflow.entity.Testworkflow;
import com.alibaba.fastjson.JSONObject;
import com.csvreader.CsvReader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public class BaseTestCase extends AbstractTestNGSpringContextTests {
    public Map<String, WebDriver> webDriverFactory;
    protected Logger logger= LoggerFactory.getLogger(this.getClass().getName());
    private  static  ThreadLocal<SimpleContext> simpleContextThreadLocal=new ThreadLocal<>();
    @DataProvider(name = "DriverDataProvider")
    public Iterator<Object[]> prepareDataProvider(){
        List<Object[]> testCases=new ArrayList<>();
        String fullClassName=this.getClass().getName();
        String fileName=fullClassName.replace(".","/").concat(".csv");
        try {
            String path= MyFileUtils.fileSeparatorAdapt(System.getProperty("user.dir")+"\\src\\main\\resources\\testdata\\"+fileName+"");
            FileReader fileReader=new FileReader(new File(path));
            CsvReader csvReader = new CsvReader(fileReader);
            csvReader.readHeaders();
            while (csvReader.readRecord()){
                Object[] objects=new Object[6];
                objects[0]=Integer.parseInt(csvReader.get(0));
                objects[1]=csvReader.get(1);
                objects[2]=csvReader.get(2);
                objects[3]=csvReader.get(3);
                objects[4]=Integer.parseInt(csvReader.get(4));
                objects[5]=Boolean.parseBoolean(csvReader.get(5));
                testCases.add(objects);
            }
            csvReader.close();
        } catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        return testCases.iterator();
}
    public <T extends BaseProcessor> BaseProcessor render(String claseeName, String actionString, String desc, String requestContext, int dbCkColum){
        RunningCaseInfo.description=desc;
        String[] actionsArray = actionString.split(",");
        Testworkflow testworkflow = new Testworkflow();
       testworkflow.id = claseeName.split("\\.")[claseeName.split("\\.").length - 1];
        Actions actions = new Actions();
        Action actionTemp;
        actions.action=new ArrayList<>();
        for (int i = 0; i <actionsArray.length ; i++) {
            actionTemp=new Action();
            actionTemp.bean=actionsArray[i];
            actions.action.add(actionTemp);
        }
        ProcessSet processSet=new ProcessSet();
        processSet.actions=actions;
        testworkflow.processSet=processSet;
        List<Activity> activities=new ArrayList<>();
        for (Action action:testworkflow.processSet.actions.action) {
            activities.add((Activity) applicationContext.getBean(action.bean));
        }
        SimpleContext simpleContext=new SimpleContext();
        // 传入action中用到的参数
        simpleContext.getWorkflowData().put("requestContext",requestContext);
        String[] platforms = JSONObject.parseObject(requestContext).getString("platform").split(",");
        Map<String,WebDriver> webDriverMap=new HashMap<>();
        if (platforms.length>0){
            for (int i = 0; i < platforms.length; i++) {
                if ("GrabHealth".equals(platforms[i])){
                  //  webDriverMap.put("GrabHealth"), Driver.initWebDriverForMobile();
                }else if (platforms[i].contains("mobile")){
                  //  webDriverMap.put(platforms[i], Driver.initWebDriverForMobile();
                }else {
                    webDriverMap.put(platforms[i],  Driver.initWebDriver());
                }
            }
        }
        else {
            System.out.println("用例参数中，未指定需要创建的webDriver");
        }
        simpleContext.getWorkflowData().put("webDriverMap",webDriverMap);
        if (simpleContext.getWorkflowData().get("reportId")==null){
            simpleContext.getWorkflowData().put("reportId", getReportId());
        }
        simpleContext.getWorkflowData().put("caseClassName",this.getClass().getName());
        this.webDriverFactory=webDriverMap;
        simpleContextThreadLocal.set(simpleContext);
        SequenceProcessor sequenceProcessor=new SequenceProcessor();
        sequenceProcessor.setActivities(activities);
        sequenceProcessor.setProcessContextClass(simpleContext.getClass());
        sequenceProcessor.setDefaultErrorHandle(new SimpleErrorHandler());
        return sequenceProcessor;
    }
    public void run(BaseProcessor baseProcessor){
        baseProcessor.doActivities(simpleContextThreadLocal.get());
    }
    private int getReportId() {
        return 1;
    }
      /*  int id=0;
        Connection conn=null;
        Statement statement=null;
        String DB_URL="";
        String USER="autotest";
        String PWD="autotest";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(DB_URL,USER,PWD);
            statement=conn.createStatement();
            String sql="select * from webui_result_summary where test_name='"+Thread.currentThread().getName()+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                id=resultSet.getInt("id");
            }
        }
        catch (Exception e){

        }
        finally {
            try {
                if (statement!=null)statement.close();
            }catch (Exception e){

            }
            try {
                if (conn!=null)conn.close();
            }catch (Exception e){

            }
        }
        return id;
    }*/
}
