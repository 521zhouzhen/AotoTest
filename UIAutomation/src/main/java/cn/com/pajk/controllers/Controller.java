package cn.com.pajk.controllers;

import cn.com.pajk.service.impl.CaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/webuiautomation")
public class Controller {
    @Autowired
    CaseServiceImpl caseServiceImpl;


}
