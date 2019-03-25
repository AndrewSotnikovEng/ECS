
import java.io.IOException;

import db.MsAccessConnector;
import entity.DtpBook;
import jxl.read.biff.BiffException;
import utils.FsUtils;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws BiffException, IOException {
		MsAccessConnector db = new MsAccessConnector();
		db.createTable();

		for (String fName : FsUtils.getExcelFiles()) {
			DtpBook dtp = new DtpBook(fName);
			dtp.generateVarSheets();
			dtp.flushRowsToDb(db);
			dtp.close();
		}

		db.closeDb();

	}
}
