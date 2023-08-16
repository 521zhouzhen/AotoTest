package cn.com.pajk.workflow;

import java.util.List;

public interface Processor {
    boolean supports(Activity activity);
    void doActivities();
    void doActivities(Object seedData);
    void doActivities(ProcessContext context);
    void setActivities(List activities);
    void setDefaultErrorHandle(ErrorHandler defaultErrorHandle);
}
