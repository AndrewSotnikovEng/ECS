package org.ecs.project.DtpFileProject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FileSystemFunc {

	
	private static ArrayList<String> getAllFiles() {
	
	File folder = new File(".");
	ArrayList<File> listOfFiles = new ArrayList<File>(Arrays.asList(folder.listFiles()));
	ArrayList<String> listOfFileName = new ArrayList<String>();
	
	
	//regex pattern - ".*xlsx?$"
		
	for (File file : listOfFiles) {
		//lookin for a file with Excel extenstion
	    if (file.isFile() && Pattern.matches(".*xlsx?$", file.getName())) {
	    	
	        listOfFileName.add(file.getName());
	        
	    }
	}
	
	return listOfFileName;
}

	public static ArrayList<String> getExcelFiles() {
		
		ArrayList<String> absPaths = new ArrayList<String>();
		
		final String dir = System.getProperty("user.dir");
        
        
		for (String fName : getAllFiles()) {
			absPaths.add(dir + "/" + fName);
		}
		
		return absPaths;
        
        
        
		
		
	}

	
	
}
