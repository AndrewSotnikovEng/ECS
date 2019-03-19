package org.ecs.project.DtpFileProject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

class FileSystemFunc {


	public static void getFiles() {
	
	File folder = new File(".");
	ArrayList<File> listOfFiles = new ArrayList<File>(Arrays.asList(folder.listFiles()));
	
	for (File file : listOfFiles) {
	    if (file.isFile()) {
	        System.out.println(file.getName());
	    }
	}
}


	
	
}
