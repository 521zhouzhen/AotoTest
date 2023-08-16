package cn.com.pajk.workflow.entity;

import cn.com.pajk.workflow.entity.ProcessSet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testworkflow")
public class Testworkflow {
    @XmlAttribute(name = "id")
    public String id;
    @XmlElement(name = "processSet")
    public ProcessSet processSet;

    @Override
    public String toString() {
        return "Testworkflow{" +
                "id='" + id + '\'' +
                ", processSet=" + processSet +
                '}';
    }
}
