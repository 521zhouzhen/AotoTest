package cn.com.pajk.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Driver {
    static String browserType="Chrome";
    static String chromeVersion="114";
    public static WebDriver driver;
    static long timeout=20l;
    public static WebDriver initWebDriver(){
        String os=System.getProperty("os.name").toLowerCase();
        if (os.indexOf("linux")>=0){
            ChromeOptions chromeOptions=new ChromeOptions();
            chromeOptions.setHeadless(true);//设置为无头模式
            chromeOptions.addArguments("--whitelisted--ips=\"\"");
            chromeOptions.setExperimentalOption("w3c",false);
            driver=new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            driver.manage().window().setSize(new Dimension(2048,1536));
            driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        }else if ("Chrome".equals(browserType)){
            //driver位置   D:\coding\ideaProjects\UIAutomation\src\main\resources\drivers
            System.setProperty("webdriver.chrome.driver", MyFileUtils.fileSeparatorAdapt(System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\chromedriver_"+chromeVersion+".exe"));
            ChromeOptions chromeOptions=new ChromeOptions();
           // chromeOptions.setHeadless(true);//设置为无头模式
            chromeOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            driver=new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            driver.manage().window().setSize(new Dimension(2048,1536));
            driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);//页面加载时间
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);//元素出现时间
        }else {
            System.out.println("The browser type doesn't support in this framework");
            System.exit(0);
        }
        return driver;
    }
}
