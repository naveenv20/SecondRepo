package com.maax.businessmanager.util;

import java.util.Hashtable;

public class util {

	static Xls_Reader xls_suite= new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\com\\maax\\businessmanager\\testdata\\Suite.xlsx");
	
	public static boolean istestsuiterunnable(String Suitename){
		
	for(int rnum=2;rnum<=xls_suite.getRowCount("Suite");rnum++){
		if(xls_suite.getCellData("Suite", "SuiteName", rnum).equalsIgnoreCase(Suitename)){
			
			if(xls_suite.getCellData("Suite", "Runmode", rnum).equalsIgnoreCase("Y")){
				return true;
			}
				else
					return false;
			}
		}
	return false;
		
	}
	
	
	
public static boolean istestcaserunnable(String testcasename, String suitename){
	
	Xls_Reader xls_suite_testcase=new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\com\\maax\\businessmanager\\testdata\\"+suitename+".xlsx");
	
	for(int rnum=2;rnum<=xls_suite_testcase.getRowCount("TestCases");rnum++){
		
		if(xls_suite_testcase.getCellData("TestCases", "TestCases", rnum).equals(testcasename)){
			
			if(xls_suite_testcase.getCellData("TestCases", "Runmode", rnum).equals("Y"))
				return true;
				else
					return false;
		}
		
	}
	
	
	
	
	return false;
	
}
	
	

public static Object[][] getdata(String testcasename, String suitename){
	
	
	
	Xls_Reader xls_suite_testcase=new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\com\\maax\\businessmanager\\testdata\\"+suitename+".xlsx");
	int rnum;
	for(rnum=1;rnum<=xls_suite_testcase.getRowCount("Data");rnum++){
		if(xls_suite_testcase.getCellData("Data", 0, rnum).equalsIgnoreCase(testcasename)){
			System.out.println("Test case found   :"+testcasename);
			break;
	}
		
	}	
			int testdataheaderrownum=rnum+1;
			int testdatacolumnstart=0;
			int  testdatacolumnend=0;
			int testdatastartnum=testdataheaderrownum+1;
			int testdataendnum=testdatastartnum+1;
			int rowsofdata=0;
		
			
			
			//no.of columsn calculating
			while(!(xls_suite_testcase.getCellData("Data", testdatacolumnend, testdataheaderrownum)=="")){
			testdatacolumnend++;
			}
			System.out.println("Total columns of test data are  ::"+testdatacolumnend);
			//no of. rows of test data for each test case
			while(!(xls_suite_testcase.getCellData("Data", 0,testdataendnum)=="")){
				rowsofdata++;	testdataendnum++;
			}
			System.out.println("Total rows of test data are  ::"+(rowsofdata+1));
			
			Object[][] data1=new Object[rowsofdata+1][1];
			int r=0;
	
		
		for(int rownum=testdataheaderrownum ;rownum<testdatastartnum+rowsofdata;rownum++){
			Hashtable<String,String> datarow=new Hashtable<String,String>();
			for(int colnum=testdatacolumnstart;colnum<testdatacolumnend;colnum++){
				
				//System.out.println(xls_suite_testcase.getCellData("Data", colnum, testdataheaderrownum));
			//	System.out.println(xls_suite_testcase.getCellData("Data", colnum, rownum+1));
			
			datarow.put(xls_suite_testcase.getCellData("Data", colnum, testdataheaderrownum), xls_suite_testcase.getCellData("Data", colnum, rownum+1));
		
		}
			data1[r][0]=datarow;
			r++;
		}
			
			
			
		System.out.println("Hello");	
			
	
		
		return data1;
		}	
		
	

	
	
public static Hashtable<String, String> getobjectdata(String testcasename, String suitename){
	Xls_Reader xls_suite_testcase=new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\com\\maax\\businessmanager\\testdata\\"+suitename+".xlsx");
	
	int rownum;
	for( rownum=0;rownum<xls_suite_testcase.getRowCount("ObjectData");rownum++){
		if(xls_suite_testcase.getCellData("ObjectData", 0, rownum).equalsIgnoreCase(testcasename))
			break;
		
	}
	
	int objectstartrownum=rownum+2;
	int objectendrownum=objectstartrownum;
	int objectscount=0;
	
	while(!(xls_suite_testcase.getCellData("ObjectData", 0, objectendrownum).equalsIgnoreCase(""))){
		objectscount++;objectendrownum++;
	}
	
	Hashtable<String, String> objecthash=new Hashtable<String, String>();
	for(int rnum=objectstartrownum;rnum<objectendrownum;rnum++){
		objecthash.put(xls_suite_testcase.getCellData("ObjectData", 0, rnum), xls_suite_testcase.getCellData("ObjectData", 1, rnum));
	}
	
	
System.out.println(objecthash);	
	return objecthash;
	
	
}













}
