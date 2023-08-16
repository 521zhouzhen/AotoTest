package cn.com.pajk.pages.JPNormandy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import cn.com.pajk.pages.BasePage;

public class JPNormandyLoginPage extends BasePage {
    WebDriver webDriver;
    public JPNormandyLoginPage(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }
    @FindBy(xpath = "//*[@id=\"loginName\"]")
    public WebElement userName;
    @FindBy(xpath = "//*[@id=\"password\"]")
    public WebElement password;
    @FindBy(xpath = "//*[@id=\"login\"]/form/div[3]/div/div/button")
    public WebElement loginBtn;
    @FindBy(xpath = "//*[@id=\"password\"]/input")
    public WebElement captcha;
    @FindBy(xpath = "//*[@id=\"login\"]/form/div[2]/div/div/button")
    public WebElement submitCaptcha;
    @FindBy(xpath = "")
    public WebElement columnName;
}
