package com.maax.businessmanager.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;



public class TestBase {
	
	public static Logger APPLICATION_LOG;
	public Hashtable<String,String> objdata;
//	 public static  WebDriver d1; 

	public WebDriver init(String testcasename,String Suite,Hashtable<String,String> data,Boolean grid){
		WebDriver d2; 
		  APPLICATION_LOG=Logger.getLogger("devpinoyLogger");
		objdata=util.getobjectdata(testcasename, Suite);
		if(grid)
			d2=openbrowsergrid(data.get("Browser"));
		else
		d2=openbrowser(data.get("Browser"));
		
		
		return d2;
		
	}
	
	
	

	public static void checkrunmodes(String Suitename,String testcasename,String currentrunmode){
		
		boolean suiterunmode=util.istestsuiterunnable(Suitename);
		boolean testcaserunmode=util.istestcaserunnable(testcasename, Suitename);
		boolean currrunmode=false;
		if(currentrunmode.equalsIgnoreCase("Y"))
			currrunmode=true;
		if(!(suiterunmode&&testcaserunmode&&currrunmode)){
			APPLICATION_LOG.debug("Test case is configured as No"+"     Suitename ::"+Suitename+"  testcasename::"+ testcasename+"  currentrunmode::"+ currentrunmode);
			throw new SkipException("Test case is configured as No");
		}
		
		else
			APPLICATION_LOG.debug("Running the test case"+"     Suitename ::"+Suitename+"  testcasename::"+ testcasename+"  currentrunmode::"+ currentrunmode);	
		
		
	}
	
	
	public void click(String identifier, WebDriver d1){
		
		d1.findElement(By.xpath(objdata.get(identifier))).click();
		
	}
	
	public WebDriver openbrowser(String browsername){

		WebDriver d1=null;
		if(browsername.equalsIgnoreCase("Firefox")){	
			 
			
		  d1=new FirefoxDriver();	
			String scriptpath="C:\\autoit_myscripts\\auth.exe";
			//executeScript(scriptpath);
			
			
	
		}
		if(browsername.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\browsers\\chromedriver.exe");
				d1=new ChromeDriver();
			String scriptpath="C:\\autoit_myscripts\\auth_chrome.exe";
			//executeScript(scriptpath);
			
		}
		if(browsername.equalsIgnoreCase("IE")){
				 d1=new InternetExplorerDriver();
		}
		//Implicit wait time out
	//	d1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		//Browser maximising
		d1.manage().window().maximize();
		return d1;
	}
	
	public RemoteWebDriver openbrowsergrid(String browsername){
		  DesiredCapabilities cap=null;
		RemoteWebDriver d1=null;
		if(browsername.equalsIgnoreCase("Firefox")){	
			 cap=DesiredCapabilities.firefox();	
			 cap.setBrowserName("firefox");
			cap.setPlatform(Platform.ANY);
			
			String scriptpath="C:\\autoit_myscripts\\pasesolution_IE.exe";
			//executeScript(scriptpath);
			}
		if(browsername.equalsIgnoreCase("Chrome")){
			cap=DesiredCapabilities.chrome();	
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.ANY);
			
			String scriptpath="C:\\autoit_myscripts\\auth_chrome.exe";
			//executeScript(scriptpath);
			
		}
		if(browsername.equalsIgnoreCase("IE")){
			
			cap=DesiredCapabilities.internetExplorer();	
			cap.setBrowserName("iexplore");
			cap.setPlatform(Platform.WINDOWS);
		
		}
	
		try {
				d1=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		d1.manage().window().maximize();
		return d1;
			
	}
	
	public void navigate(WebDriver d1){
		d1.get(objdata.get("URL"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void type(String identifier, String datatotype,WebDriver d1){
		d1.findElement(By.xpath(objdata.get(identifier))).clear();
		d1.findElement(By.xpath(objdata.get(identifier))).sendKeys(datatotype);
	}
	
	public void select(String identifier, String valuetoselect,WebDriver d1){
	WebElement 	e=d1.findElement(By.xpath(objdata.get(identifier)));
	Select element= new Select(e);
	element.selectByVisibleText(valuetoselect);
	}
	
	public void selectradio(String identifier, String valuetoselect,WebDriver d1){
		//not implemented fully , check this *****
		d1.findElement(By.xpath(objdata.get(identifier))).click();
	}
	
	public void executeScript(String scriptPath) 
	{
	//scriptPath is the path of the executable
	try {
		Runtime.getRuntime().exec(scriptPath);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	public void teardown(WebDriver d2){
		if(d2!=null){
			d2.quit();
		}
	}
	
	
	//***********************************************
	//***********PROJECT SPECIFIC FUNCTIONS**********
	//************************************************
	
	public void dologin(WebDriver d1){
	
		waitforelementpresent("loginid",d1);
		d1.findElement(By.xpath(objdata.get("loginid"))).sendKeys("BCCHASE");
		d1.findElement(By.xpath(objdata.get("password"))).sendKeys("Welcome1");
		d1.findElement(By.xpath(objdata.get("loginbtn"))).click();
	}
	
	public static void dynamicwait(WebDriver d1){
		
		/*int maaxtimeout=90;int counter=0;
		WebElement bubbles=d1.findElement(By.xpath("//div[contains(@id,'connection-working') and contains(@class,'iceOutConStatActv') and contains(@style,'visibility: visible')]"));
		List<WebElement> bubexistence=d1.findElements(By.xpath("//div[contains(@id,'connection-working') and contains(@class,'iceOutConStatActv') and contains(@style,'visibility: visible')]"));
		while(bubexistence.size()!=0&&counter<maaxtimeout){
			 bubexistence=d1.findElements(By.xpath("//div[contains(@id,'connection-working') and contains(@class,'iceOutConStatActv') and contains(@style,'visibility: visible')]"));
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 counter++;
		}
		System.out.println("********:::"+counter);
		bubbles=null;*/
		
		WebDriverWait obj=new WebDriverWait(d1, 300);
		WebElement Element=d1.findElement(By.xpath("//div[contains(@id,'connection-working') and contains(@class,'iceOutConStatActv') and contains(@style,'visibility: visible')]"));
		obj.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'connection-working') and contains(@class,'iceOutConStatActv') and contains(@style,'visibility: visible')]")));
		
	}
	
	
	 public  void waitforelementpresent(String Element_xpath,WebDriver d1)  
	 {
	 	int sec=0;

	 while (sec<60)
	 {
	 	int count=d1.findElements(By.xpath(objdata.get(Element_xpath))).size();
	 	List<WebElement> element_array=d1.findElements(By.xpath(objdata.get(Element_xpath)));
	 	if (count==0){
	 		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}sec++;}
	 	else
	 	{
	 	APPLICATION_LOG.debug("Object Found::::"+Element_xpath);	
	 	break;}
	 	
	 	
	 }

	 }
}
