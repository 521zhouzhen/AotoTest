package cn.com.pajk.workflow;

import cn.com.pajk.common.report.ShareLog;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class SequenceProcessor extends BaseProcessor {
    private List passArrayList=new ArrayList();
    private List failedArrayList=new ArrayList();
    private List skippedArrayList=new ArrayList();
    private ITestResult iTestResult= Reporter.getCurrentTestResult();
    private Log logger= LogFactory.getLog(SequenceProcessor.class);
    private Class processContextClass;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
    public void setProcessContextClass(Class processContextClass){
        this.processContextClass=processContextClass;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public boolean supports(Activity activity) {
        return activity instanceof BaseActivity;
    }

    @Override
    public void doActivities() {
        doActivities(null);
    }

    @Override
    public void doActivities(Object seedData) {

    }
    public void recordPassActionExecResult(String actionName){
        passArrayList.add(actionName);
        iTestResult.setAttribute(iTestResult.getTestClass().getName().concat("PassActions"), passArrayList);
    }
    public void recordSkippedActionExecResult(String actionName){
        skippedArrayList.add(actionName);
        iTestResult.setAttribute(iTestResult.getTestClass().getName().concat("SkippedActions"), skippedArrayList);
    }
    public void recordFailedActionExecResult(String actionName){
        failedArrayList.add(actionName);
        iTestResult.setAttribute(iTestResult.getTestClass().getName().concat("FailedActions"), failedArrayList);
    }
    private boolean processShouldStop(ProcessContext context, Activity activity){
        if (context!=null && context.stopProcess()){
            logger.info("Interrupted workflow as requested by:"+activity.getBeanName());
            return true;
        }
        return false;
    }


    @Override
    public void doActivities(ProcessContext context) {
        if (logger.isDebugEnabled()){
            logger.debug(getBeanName()+" processor is running..");
        }
        List activities=getActivities();
        int stepNo=0;
        for(Iterator it=activities.iterator();it.hasNext();){
            Activity activity=(Activity) it.next();
            stepNo++;
            if (logger.isDebugEnabled()){
                logger.debug("run activity :"+activity.getBeanName()+" using arguments: "+context);
            }
            try {
                if (stepNo>1){
                    ShareLog.reportLog=ShareLog.reportLog+"..........";
                }
                ShareLog.reportLog=ShareLog.reportLog+"["+stepNo+"] begin to run ["+activity.getBeanName()+ "] case ..."+"\r\n";
                context=activity.excute(context);
                this.recordPassActionExecResult(activity.getBeanName());
            }catch (Throwable throwable){
                ErrorHandler errorHandler=activity.getErrorHandler();
                //记录失败的用例
                this.recordFailedActionExecResult(activity.getBeanName());
                //记录未执行的action，状态为skipped
                Activity tmpActivity;
                while (it.hasNext()){
                    tmpActivity=(Activity) it.next();
                    this.recordSkippedActionExecResult(tmpActivity.getBeanName());
                }
                if (errorHandler==null){
                    logger.info("no error handler for this action, run default error"+"handle and abort processing ");
                    getDefaultErrorHandle().handleError(context,throwable);
                    break;
                }else {
                    logger.info("run error handler and continue");
                    errorHandler.handleError(context,throwable);
                }
            }
            if (processShouldStop(context,activity)){
                break;
            }
        }
        logger.debug(getBeanName()+" process is done.");
    }
}
