package cn.com.pajk.workflow.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "action")
public class Action {
    @XmlAttribute(name = "bean")
    public String bean;
    @XmlAttribute(name = "name")
    public String name;
    @XmlAttribute(name = "linkto")
    public String linkto;
    @Override
    public String toString(){
        return "Action [bean= "+bean+" ,name= "+name+" , linkto="+linkto+" ]";
    }
}
