package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/*
 * 	FsUtils - provides method for working with file system.
 *  *  
 */


public class FsUtils {

	/*
	 * Getting all files in current directory
	 */
	private static ArrayList<String> getAllFiles() {

		File folder = new File(".");
		ArrayList<File> listOfFiles = new ArrayList<File>(Arrays.asList(folder.listFiles()));
		ArrayList<String> listOfFileName = new ArrayList<String>();

		// regex pattern - ".*xlsx?$"

		for (File file : listOfFiles) {
			// looking for a file with Excel extension
			if (file.isFile() && Pattern.matches(".*xlsx?$", file.getName())) {

				listOfFileName.add(file.getName());
			}
		}

		return listOfFileName;
	}

	
	/*
	 * Generate absolute path to Excel files in current directory.
	 * 
	 */
	public static ArrayList<String> getAbsExcelFilesPath() {

		ArrayList<String> absPaths = new ArrayList<String>();

		final String dir = System.getProperty("user.dir");

		for (String fName : getAllFiles()) {
			absPaths.add(dir + "/" + fName);
		}

		return absPaths;

	}

}
