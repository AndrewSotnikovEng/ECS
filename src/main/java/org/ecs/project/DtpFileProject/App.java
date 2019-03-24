package org.ecs.project.DtpFileProject;

import java.io.IOException;

import jxl.read.biff.BiffException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws BiffException, IOException
    {
    	
    	for (String fName : FileSystemFunc.getExcelFiles()) {
        	DtpBook dtp = new DtpBook(fName);
        	dtp.generateVarSheets();

        	dtp.close();
		}
    	

    	
    }
}
