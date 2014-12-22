package com.maax.businessmanager.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.internal.Utils;







public class CustomListener extends TestListenerAdapter implements IInvokedMethodListener,ISuiteListener{

	
	public static Hashtable<String,String> resulttable=null;
	public static String Foldername;
	public static String resultfilepath;
	public static ArrayList<String> keys;
	
	
	//coming from TestListenerAdapter
	public void onTestFailure(ITestResult tr){
		//simple one..with out Error util after invocation methods
		//report(tr.getName(),tr.getThrowable().getMessage());
		
		List<Throwable> verificationFailures = ErrorUtil.getVerificationFailures();
		String errMsg="";
		for(int i=0;i<verificationFailures.size();i++){
			errMsg=errMsg+"["+verificationFailures.get(i).getMessage()+"]-";
		}
		report(tr.getName(), errMsg);
		
	}
	
	//coming from TestListenerAdapter
	public void onTestSkipped(ITestResult tr) {
		report(tr.getName(), tr.getThrowable().getMessage()); 
	}
	
	
	//coming from TestListenerAdapter
	public void onTestSuccess(ITestResult tr){
		report(tr.getName(), "PASS");
		// report , logs
	}
	
	//overridden the function written in IInvokedMethodListener
public void afterInvocation(IInvokedMethod method, ITestResult result) {
		
		Reporter.setCurrentTestResult(result);

		if (method.isTestMethod()) {
			List<Throwable> verificationFailures = ErrorUtil.getVerificationFailures();
			//if there are verification failures...
			if (verificationFailures.size() != 0) {
				//set the test to failed
				result.setStatus(ITestResult.FAILURE);
				
				//if there is an assertion failure add it to verificationFailures
				if (result.getThrowable() != null) {
					verificationFailures.add(result.getThrowable());
				}
 
				int size = verificationFailures.size();
				//if there's only one failure just set that
				if (size == 1) {
					result.setThrowable(verificationFailures.get(0));
				} else {
					//create a failure message with all failures and stack traces (except last failure)
					StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):nn");
					for (int i = 0; i < size-1; i++) {
						failureMessage.append("Failure ").append(i+1).append(" of ").append(size).append(":n");
						Throwable t = verificationFailures.get(i);
						String fullStackTrace = Utils.stackTrace(t, false)[1];
						failureMessage.append(fullStackTrace).append("nn");
					}
 
					//final failure
					Throwable last = verificationFailures.get(size-1);
					failureMessage.append("Failure ").append(size).append(" of ").append(size).append(":n");
					failureMessage.append(last.toString());
 
					//set merged throwable
					Throwable merged = new Throwable(failureMessage.toString());
					merged.setStackTrace(last.getStackTrace());
 
					result.setThrowable(merged);
					
				}
			}
		
		}
		
		
	}
 

//overridden the function written in IInvokedMethodListener

	public void beforeInvocation(IInvokedMethod arg0, ITestResult test) {
		
	}

	@Override
	//coming from ISuiteListener
	public void onStart(ISuite suite) {
		
		
		
	System.out.println("Starting suite"+suite.getName());
	System.out.println("Starting suite"+suite.getName());
	
	
	
	if(resulttable==null){
		keys=new ArrayList<String>();
 resulttable=new Hashtable<String,String>();
	}
		if(Foldername==null){
			Date d =new Date();
			Foldername=d.toString().replace(":", "_");
			File f= new File(System.getProperty("user.dir")+"\\target\\simplereports\\"+Foldername);
			f.mkdir();
			File src=new File(System.getProperty("user.dir")+"\\target\\simplereports\\Results_Sheet.xlsx");
			resultfilepath=System.getProperty("user.dir")+"\\target\\simplereports\\"+Foldername+"\\Results_Sheet.xlsx";
			File Dest=new File(resultfilepath);
			try {
				FileUtils.copyFile(src, Dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
	//coming from ISuiteListener

	public void onFinish(ISuite suite) {
		if(resulttable!=null){
		System.out.println("Finishing suite"+suite.getName());
		System.out.println(resulttable);
		
		
		//write the results into the xlsx
		Xls_Reader res=new Xls_Reader(resultfilepath);
		
		res.addSheet(suite.getName());
		
		
		//add the results 
		res.setCellData(suite.getName(), 0, 1, "Test Case");
		res.setCellData(suite.getName(), 1, 1, "Result");
		
		for(int i=0;i<keys.size();i++){
			String key=keys.get(i);
			String tableval=resulttable.get(key);
			res.setCellData(suite.getName(), "Test Case", i+2,key );
			res.setCellData(suite.getName(), "Result", i+2,tableval );
		}
		}
		
		resulttable=null;
		keys=null;
		
	}
			

	public void report(String nameoftest,String result){
	
		int iteration=1;
		while(resulttable.containsKey(nameoftest+" Iteration "+iteration)){
			iteration++;
		}
		
		keys.add(nameoftest+" Iteration "+iteration);
		resulttable.put(nameoftest+" Iteration "+iteration, result);
				
	}

}
