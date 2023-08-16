package cn.com.pajk.workflow;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class SimpleErrorHandler implements ErrorHandler {
    private Log log= LogFactory.getLog(SimpleErrorHandler.class);
    private String beanName;
    @Override
    public void handleError(ProcessContext context, Throwable th) {
    log.error("Handing Error: ",th);
    if (th instanceof AssertionError){
        throw new AssertionError(th);
    }else {
        throw new RuntimeException(th);
    }
    }

    @Override
    public void setBeanName(String s) {
        this.beanName=beanName;
    }
}
