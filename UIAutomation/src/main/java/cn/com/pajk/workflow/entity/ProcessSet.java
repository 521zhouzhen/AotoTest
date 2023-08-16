package cn.com.pajk.workflow.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "processSet")
public class ProcessSet {
    @XmlElement(name = "actions")
    public Actions actions;
    @Override
    public String toString(){
        return "ProcessSet actions= "+actions+" ]";
    }
}
