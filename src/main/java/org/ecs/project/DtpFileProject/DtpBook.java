package org.ecs.project.DtpFileProject;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DtpBook {

	public DtpBook(String fileName) throws BiffException, IOException {
		
		 //Read the given XL sheet 
	    Workbook workbook = Workbook.getWorkbook(new File(fileName));
	    System.out.println("Number of sheets in this workbook : " + workbook.getNumberOfSheets());
		
	    String [] sheetNames = workbook.getSheetNames();
	    
	    for (int i = 0 ; i < sheetNames.length ; i ++ ) {
	      System.out.println("Sheet Name[" + i + "] = " + sheetNames[i]);
	    }
	    
	    //Close and free allocated memory 
	    workbook.close(); 
	    
	}
	
	
}
