package cn.com.pajk.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import cn.com.pajk.utils.Driver;
import cn.com.pajk.utils.FuncationBase;


public class BasePage extends Driver {
    // 输入文字
    public void sendKeys(WebElement element,String keys){
        FuncationBase.waitElementDisplay(driver,10,element);
        sendKeys(element,keys);
    }
    //点击按钮
    public void clickBtn(WebElement element) throws Exception{
        Thread.sleep(500);
        FuncationBase.waitElementEnableClick(driver,10,element);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);
        Thread.sleep(1000);
    }
    //上传文件
    public void uploadFile(String path,WebElement element){

    }
}
