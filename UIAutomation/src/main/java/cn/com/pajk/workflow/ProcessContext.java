package cn.com.pajk.workflow;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public interface ProcessContext extends Serializable {
    public boolean stopProcess();
    public void setSeedDate();
    //public String getBeanName();
    //public Map registerDbCheckTables();
}
