package com.maax.businessmanager.rough;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.maax.businessmanager.util.ErrorUtil;



public class testa {
@Test
public void testing(){
try{
	Assert.assertEquals("ABC", "DEF");
}catch (Throwable e){
	ErrorUtil.addVerificationFailure(e);
}

System.out.println("After the error ");

}
}
