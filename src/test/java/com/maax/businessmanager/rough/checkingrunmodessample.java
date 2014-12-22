package com.maax.businessmanager.rough;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.maax.businessmanager.util.TestBase;
import com.maax.businessmanager.util.util;

public class checkingrunmodessample extends TestBase {

	@Test
	public void check1() {
		// TODO Auto-generated method stub
		checkrunmodes("common","basicnavigation","Y");
		util.getdata("basicnavigation", "Common");
		util.getobjectdata("basicnavigation", "Common");
		
	}

}


