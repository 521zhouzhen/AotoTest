package cn.com.pajk.workflow;

import org.springframework.beans.factory.BeanNameAware;

import java.util.Map;


public interface Activity extends BeanNameAware {
    public ProcessContext excute(ProcessContext processContext) throws Exception;
    public ErrorHandler getErrorHandler();
    public String getBeanName();
    public Map registerDbCheckTables();
}

