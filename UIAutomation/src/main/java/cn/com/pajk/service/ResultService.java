package cn.com.pajk.service;

public interface ResultService {
    String getResultSummary();
    String getCaseResultBySummaryId(int summaryId);
    String getActionResultByCaseResultId(int caseResultId);
    byte[] getSnapShoots(int reportId,String caseName,String actionName,String picName);
}
