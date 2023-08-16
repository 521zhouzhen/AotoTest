package cn.com.pajk.workflow.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "actions")
public class Actions {
    @XmlElement
    public List<Action> action;
    @Override
    public String toString(){
        return "Actions [action = "+ action+" ]";
    }
}
