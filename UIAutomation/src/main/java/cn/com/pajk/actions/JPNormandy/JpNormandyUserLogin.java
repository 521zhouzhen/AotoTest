package cn.com.pajk.actions.JPNormandy;


import cn.com.pajk.pages.JPNormandy.JPNormandyLoginPage;
import cn.com.pajk.utils.ConfigProperty;
import cn.com.pajk.utils.LogUtils;
import cn.com.pajk.workflow.BaseActivity;
import cn.com.pajk.workflow.ErrorHandler;
import cn.com.pajk.workflow.ProcessContext;
import cn.com.pajk.workflow.SimpleContext;
import com.alibaba.fastjson.JSONObject;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("jPNormandyUserLogin")
public class JpNormandyUserLogin extends BaseActivity {
    @Override
    public ProcessContext excute(ProcessContext processContext) throws Exception {
        System.out.println("==========开始执行步骤： "+this.getClass().getName()+"=========");
        LogUtils.info("========EXCUTE ACtion===========\n");
        SimpleContext simpleContext= (SimpleContext) processContext;
        JSONObject requestContext=JSONObject.parseObject((String) simpleContext.getWorkflowData().get("requestContext"));
        Map<String, WebDriver> webDriverFactoy = (Map<String, WebDriver>) simpleContext.getWorkflowData().get("webDriverMap");
        /*String reportId = (String)simpleContext.getWorkflowData().get("reportId");
        String caseClassName = (String) simpleContext.getWorkflowData().get("caseClassName");
        String actionClass=this.getClass().getName();*/
        //操作步骤
        WebDriver webDriver=webDriverFactoy.get("JPNormandyBench");
        webDriver.get(ConfigProperty.get("JPNormandyUrl"));
        Thread.sleep(5000);
        JPNormandyLoginPage jpNormandyLoginPage=new JPNormandyLoginPage(webDriver);
        sendKey(jpNormandyLoginPage.userName,requestContext.getString("userName"));
        sendKey(jpNormandyLoginPage.password,requestContext.getString("passWord"));
        click(jpNormandyLoginPage.loginBtn);
        return null;
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return null;
    }

    @Override
    public Map registerDbCheckTables() {
        return null;
    }
}
