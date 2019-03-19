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
    	FileSystemFunc.getFiles();
    	DtpBook dtp = new DtpBook("/media/Maindata/Дело/das_code/java/DtpFileProject/dtpFile1.xls");
    	
    }
}
