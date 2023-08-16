package cn.com.pajk.workflow;

import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class BaseProcessor implements InitializingBean, BeanNameAware, BeanFactoryAware, Processor {
    private BeanFactory beanFactory;
    private String beanName;
    private List activities;
    private ErrorHandler defaultErrorHandle;

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public List getActivities() {
        return activities;
    }

    @Override
    public void setActivities(List activities) {
        this.activities = activities;
    }

    public ErrorHandler getDefaultErrorHandle() {
        return defaultErrorHandle;
    }

    @Override
    public void setDefaultErrorHandle(ErrorHandler defaultErrorHandle) {
        this.defaultErrorHandle = defaultErrorHandle;
    }
}
