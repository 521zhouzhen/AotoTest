package cn.com.pajk.workflow;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

public class SimpleContext implements ProcessContext {
    private boolean stopProcess;
    private Log log=LogFactory.getLog(SimpleContext.class);
    private Map<String,Object> workflowData;

    public SimpleContext() {
      log.info("Instantiating SimpleContext");
      workflowData=new HashMap<>();
    }

    @Override
    public boolean stopProcess() {
        return stopProcess;
    }

    @Override
    public void setSeedDate() {

    }

    public boolean isStopProcess() {
        return stopProcess;
    }

    public void setStopProcess(boolean stopProcess) {
        this.stopProcess = stopProcess;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Map<String, Object> getWorkflowData() {
        return workflowData;
    }

    public void setWorkflowData(Map<String, Object> workflowData) {
        this.workflowData = workflowData;
    }
}
