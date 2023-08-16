package cn.com.pajk.workflow;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import cn.com.pajk.pages.BasePage;
import cn.com.pajk.utils.FuncationBase;
import cn.com.pajk.utils.LogUtils;
import cn.com.pajk.workflow.Activity;
import cn.com.pajk.workflow.ErrorHandler;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public abstract class BaseActivity extends BasePage implements Activity {
    private ErrorHandler errorHandler;
    private String beanName;
    protected Map<String, List<String>> registerDbCheckMap=new ConcurrentHashMap<>();
    @Test
    public void setUp(){
    }
    //@Override
    public ErrorHandler getErrorHandle(){
        return  errorHandler;
    }
    public void setErrorHandle(ErrorHandler errorHandler){
        this.errorHandler=errorHandler;
    }
    @Override
    public void setBeanName(String beanName){
        this.beanName=beanName;
    }
    public String getBeanName(){
       return beanName;
    }
    public void loadPage(WebDriver webDriver,String url){
        if (webDriver!=null && !url.isEmpty()){
            webDriver.get(url);
            webDriver.manage().window().maximize();
        }else {
            LogUtils.info(BaseActivity.class,"parameters cannot null!");
        }
    }
    public void click(WebElement webElement) throws InterruptedException{
        Thread.sleep(1000);
        webElement.click();
    }
    public void doubleClick(WebElement webElement,WebDriver driver){
        Actions actions = new Actions(driver);
        actions.doubleClick(webElement).build().perform();
    }
    //下拉框
    public void select(WebElement webElement,String value){
        Select select=new Select(webElement);
        select.selectByValue(value);
    }
    //清除
    public void clear(WebElement webElement){
        webElement.clear();
    }
    // 如果有滚动条，滚动到指定元素再页面上可见，并点击
    public void scrollToAndClick(WebDriver webDriver,WebElement webElement) throws InterruptedException{
        JavascriptExecutor js=(JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);",webElement);
        click(webElement);
    }
    //上传文件(无头模式通用）
    public void uploadFile(WebDriver webDriver,WebElement webElement,String filePath) throws InterruptedException{
        JavascriptExecutor js=(JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style.display='block';",webElement);
        Thread.sleep(3000);
        webElement.sendKeys(filePath);
    }
    // 输入文字
    public void sendKey(WebElement webElement,String keys){
        FuncationBase.waitElementDisplay(driver,10,webElement);
        webElement.sendKeys(keys);
    }
    // 点击按钮
    public void clickBtn(WebElement webElement) throws Exception{
        Thread.sleep(500);
        FuncationBase.waitElementDisplay(driver,10,webElement);
        FuncationBase.waitElementEnableClick(driver,10,webElement);
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",webElement);
        Thread.sleep(500);
    }
    //点击下拉框选选项
    public void clickSelector(WebElement webElement) throws Exception{
        FuncationBase.waitElementEnableClick(driver,10,webElement);
        Thread.sleep(500);
        webElement.click();
        Thread.sleep(500);
    }
    //
    public void uploadBtn(WebElement webElement,String filePath){
        webElement.sendKeys(filePath);
    }
    public void input(WebElement webElement,String keysToSend){
        webElement.sendKeys(keysToSend);
    }
    // 切换至弹出的Alert
    public Alert switchToAlert() throws Exception{
        Alert alert;
        for (int i=0;i++<10;){
            try {
                alert=driver.switchTo().alert();
                return alert;
            }catch (NoAlertPresentException e){
                Thread.sleep(500);
                continue;
            }
        }
        throw new NoAlertPresentException("找不到alert弹框");
    }
    //点击下拉框选选项
    public void clickSelect(WebElement webElement) throws Exception{
        FuncationBase.waitElementDisplay(driver,10,webElement);
        FuncationBase.waitElementEnableClick(driver,10,webElement);
        Thread.sleep(500);
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",webElement);
        Thread.sleep(500);
    }
    public void submitBtn(WebElement webElement){
        webElement.submit();
    }
    public void selecttext(WebElement webElement,int index){
        Select select=new Select(webElement);
        select.selectByIndex(index);
        select.getAllSelectedOptions();
    }
    public void commonWait(WebDriver webDriver,int time){
        webDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(time,TimeUnit.SECONDS);
    }
    public void clickSearchBtn(WebElement webElement) throws Exception{
        Thread.sleep(200);
        webElement.click();
        Thread.sleep(200);
    }
    public void openNewTab(WebDriver webDriver,String url){
        String finalUrl="\""+url+"\"";
        String s = String.format("window.open(%s)", finalUrl);
        ( (JavascriptExecutor)webDriver).executeScript(s);
    }
    //上传文案及你（图片/文件）
    public void uploadAble(String path,WebElement webElement) throws Exception{
        webElement.click();
        StringSelection stringSelection=new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_V);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
    }
}
