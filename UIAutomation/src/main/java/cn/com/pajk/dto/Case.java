package cn.com.pajk.dto;

import org.springframework.stereotype.Component;

@Component
public class Case {
    int id;
    String CaseName;
    String caseClass;
    String caseParameters;
    String caseDescription;
    int caseType;
    int countryId;
    int businessId;
    int isValid;

    public Case() {
    }
}
