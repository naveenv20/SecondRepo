package com.maax.businessmanager.util;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	
	
	@DataProvider(name="SuiteCommontestdata",parallel=true)
	public static Object[][] getdata(Method m){
		System.out.println(m.getName());
		String suitename="Common";
		return util.getdata(m.getName(), suitename);
			
	}
	
	
}
