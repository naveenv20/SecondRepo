package com.maax.businessmanager.common;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.maax.businessmanager.util.ErrorUtil;
import com.maax.businessmanager.util.TestBase;
import com.maax.businessmanager.util.TestDataProvider;
import com.maax.businessmanager.util.util;

public class sampleTest extends TestBase{
	
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="SuiteCommontestdata")
	public void basicnavigation(Hashtable<String, String>data){

		 WebDriver d1=null;
		 //RemoteWebDriver d1=null;
	d1=init("basicnavigation", "Common",data,false);
	//d1=init("basicnavigation", "Common",data,true);
		checkrunmodes("Common", "basicnavigation", data.get("Runmode"));
		APPLICATION_LOG.debug((data.get("Browser")));
		
		//openbrowsergrid(data.get("Browser"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		navigate(d1);
		dologin(d1);
		click("BusinessManagerlink",d1);
		waitforelementpresent("Agreementslink",d1);
		click("Agreementslink",d1);
		waitforelementpresent("commonagreementlink",d1);
		click("commonagreementlink",d1);
		waitforelementpresent("enrollmenteligibilitylink",d1);
		click("enrollmenteligibilitylink",d1);
		waitforelementpresent("Beneeligibilitylink",d1);
		click("Beneeligibilitylink",d1);
		waitforelementpresent("agreementsearchcodefield",d1);
		type("agreementsearchcodefield", "*",d1);
		click("beneeleigibilityagreementsearch",d1);
		dynamicwait(d1);
		click("beneeleigibiltyaddbutton",d1);
		dynamicwait(d1);
		select("hostedcarrierdp", data.get("HC"),d1);
		dynamicwait(d1);
		select("bookofbusinessdp", data.get("BOB"),d1);
	//	type("agreementaddcodefield", data.get("Code"),d1);
		
		type("agreementaddcodefield",String.valueOf(Integer.parseInt(queryit())),d1);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		type("agreementadddescription", data.get("Description"),d1);
		select("beneligibilitystatusdp", data.get("Status"),d1);
		type("waitingperiodtext", data.get("Waitingperiod"),d1);
		select("waitingperioddp", data.get("WaitingperiodDP"),d1);
		select("startdatecalculation", data.get("StartDatecal"),d1);
		type("terminationage", data.get("Term.Age"),d1);
		select("Terminationdatecal", data.get("TermDatecal"),d1);
		click("depaddbutton",d1);
		waitforelementpresent("categorydp",d1);
		select("categorydp", data.get("Category"),d1);
		dynamicwait(d1);
		select("subcategorydp", data.get("SubCate"),d1);
		dynamicwait(d1);
		//type("qualificationprdtxt", data.get("Qual Period"));
		//select("qualificationprddp", data.get("qual period dp"));
		type("depenterminationagetxt", data.get("DepTerm age"),d1);
		select("depterminatindatecal", data.get("DepTermcalDP"),d1);
		click("addtolistbutton",d1);
		dynamicwait(d1);
		click("beneligiagreesave",d1);		
		dynamicwait(d1);
		teardown(d1);
		/*try{
		Assert.assertEquals("ABC", "ABCD");
		}catch(Throwable e){			
			ErrorUtil.addVerificationFailure(e);
			
		}*/																											
		
	
	}
	

		

}
