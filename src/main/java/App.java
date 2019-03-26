
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import db.MsAccessConnector;
import entity.DtpBook;
import utils.FsUtils;

/**
 * 
 * 	Parser fetch a necessary data from Excel DTP data files and put it into MS Access database.
 * 
 * 	For more information, please see :
 * 				https://github.com/AndrewSotnikovEng/ECS
 *  
 * 
 * 			Author:
 * 
 * 	Andrii Sotnikov
 * 	andrew.sotnikov.eng@gmail.com
 *  Kyiv, 2019
 *
 */
public class App {
	public static void main(String[] args) throws IOException, EncryptedDocumentException, InvalidFormatException {
		
		MsAccessConnector db = new MsAccessConnector();
		db.createTable();

		for (String fName : FsUtils.getAbsExcelFilesPath()) {
			DtpBook dtp = new DtpBook(fName);
			dtp.generateVarSheets();
			dtp.flushRowsToDb(db);
			dtp.close();
		}

		db.closeDb();

	}
}
