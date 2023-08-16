package cn.com.pajk.cases.JPNormandy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import cn.com.pajk.workflow.BaseProcessor;
import cn.com.pajk.workflow.BaseTestCase;

@ContextConfiguration()
@ComponentScan("cn.com.pajk")
public class JPNormandyLogin extends BaseTestCase {
    @Test(dataProvider = "DriverDataProvider",description = "JPNormandy 用户登录")
    public void test(int id,String workflowId,String desc,String requestContext, int dbCkColum,boolean isNeedRun){
        if (isNeedRun){
            return;
        }
        String caseName=this.getClass().getName();
        String actions="jPNormandyUserLogin";
        BaseProcessor baseProcessor=render(caseName,actions,desc,requestContext,dbCkColum);
        run(baseProcessor);
    }
}
