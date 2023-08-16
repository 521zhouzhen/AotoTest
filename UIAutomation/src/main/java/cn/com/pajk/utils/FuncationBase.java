package cn.com.pajk.utils;

import cn.com.pajk.enums.ElementFindMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;

public class FuncationBase {
    //等待元素可见
    public static void waitElementVisibilityByCss(WebDriver webDriver,int times,String cssPath) {
        WebDriverWait webDriverWait=new WebDriverWait(webDriver,times);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssPath)));
    }
    public static void waitElementVisibilityByXpath(WebDriver webDriver,int times,String xpath) {
        WebDriverWait webDriverWait=new WebDriverWait(webDriver,times);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    public static void waitElementVisibilityById(WebDriver webDriver,int times,String id) {
        WebDriverWait webDriverWait=new WebDriverWait(webDriver,times);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }
    //等待元素出现
    public static void waitElementDisplay(WebDriver webDriver, int times, final WebElement element){
        WebDriverWait driverWait=new WebDriverWait(webDriver,times);
         driverWait.until((ExpectedCondition<Boolean>) d -> element.isDisplayed());
    }
    public static void waitElementDisplayByCss(WebDriver webDriver, int times, final String  cssPath){
        WebDriverWait driverWait=new WebDriverWait(webDriver,times);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssPath)));
    }
    public static void waitElementDisplayByXpath(WebDriver webDriver, int times, final String  path){
        WebDriverWait driverWait=new WebDriverWait(webDriver,times);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
    }
    //等待元素可点击
    public static void waitElementEnableClick(WebDriver webDriver,int times, final WebElement element){
        WebDriverWait driverWait=new WebDriverWait(webDriver,times);
        driverWait.until(ExpectedConditions.elementToBeClickable(element));
    }
    //等待元素可点击
    public static void waitPageTitle(WebDriver webDriver,int times, String contains){
        WebDriverWait driverWait=new WebDriverWait(webDriver,times);
        driverWait.until(ExpectedConditions.titleContains(contains));
    }
  // 判断元素是否存在
    public static Boolean isExistByCss(WebDriver driver,String cssPath){
        if (driver.findElements(By.cssSelector(cssPath)).size()>0){
            return true;
        }
        return false;
    }
    public static Boolean isExistByXpath(WebDriver driver,String xpath){
        if (driver.findElements(By.xpath(xpath)).size()>0){
            return true;
        }
        return false;}
    //点击
    public static boolean click(WebDriver webDriver, ElementFindMethod elementFindBy,String elementPath){
        if ("CSS".equals(elementFindBy)){
            try {
                webDriver.findElement(By.cssSelector(elementPath)).click();
                return true;
            }catch (Exception e){
                return false;
            }
        }
        if ("ID".equals(elementFindBy)){
            try {
                webDriver.findElement(By.id(elementPath)).click();
                return true;
            }catch (Exception e){
                return false;
            }
        }
        if ("XPATH".equals(elementFindBy)){
            try {
                webDriver.findElement(By.xpath(elementPath)).click();
                return true;
            }catch (Exception e){
                return false;
            }
        }
        if ("CLASSNAME".equals(elementFindBy)){
            try {
                webDriver.findElement(By.className(elementPath)).click();
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }
    //写日志
    public static boolean writeLogFile(String content){
        String logPath= MyFileUtils.fileSeparatorAdapt(System.getProperty("user.dir")+"/src/main/resources/data/TemplateLog.txt");
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(logPath)));
            while (true){
                String line= br.readLine();
                if (line==null){
                    OutputStream outputStream=new FileOutputStream(logPath);
                    outputStream.write((content+"\n").getBytes());
                    break;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
