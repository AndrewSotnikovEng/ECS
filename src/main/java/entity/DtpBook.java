package entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import db.MsAccessConnector;


public class DtpBook {

	private Workbook wb;
	// sheet names which contains vars
	private ArrayList<String> sheets = new ArrayList<String>();
	// rows with data from vars sheets
	private ArrayList<VarSheetRow> rows = new ArrayList<VarSheetRow>();;

	public DtpBook(String fName) throws IOException, 
	EncryptedDocumentException, InvalidFormatException {

		InputStream inp = new FileInputStream(fName);
		this.wb = WorkbookFactory.create(inp);
		this.printBookName(fName);

	}

	public ArrayList<VarSheetRow> getRows() {

		return rows;

	}

	/*
	 * Get a sheet names where encounter vars. Results put in "sheets"
	 * 
	 */
	private void putVarSheets() {

		String regex = ".*vars.*";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		

		for (int i = 0; i < wb.getNumberOfSheets(); i++) {

			Matcher matcher = pattern.matcher(wb.getSheetName(i));
			if (matcher.find()) {

				this.sheets.add(wb.getSheetName(i));
			}

		}

	}

	public void generateVarSheets() {

		this.putVarSheets();

		for (String sheet : sheets) {
			this.putVarSheetRow(sheet);
		}

	}

	public void putVarSheetRow(String name) {

		String comp;
		String desc;
		String req;

	 	Sheet sheet = this.wb.getSheet(name);
		// getting limitation cells
		int limRowIndex = sheet.getPhysicalNumberOfRows();
		System.out.println("\n" + name + "\n");
		// get vars sheet row
		for (int i = 1; i < limRowIndex; i++) {

			comp = sheet.getRow(i).getCell(1).getRichStringCellValue().toString();
			desc = sheet.getRow(i).getCell(2).getRichStringCellValue().toString();
			req = sheet.getRow(i).getCell(3).getRichStringCellValue().toString();

			VarSheetRow row = new VarSheetRow(comp, desc, req);
			System.out.println(row);
			rows.add(row);

		}

	}

	/*
	 * 	Print a human readable name of current working wile.
	 */
	private void printBookName(String fName) {

		// cut after last slash
		int index = fName.lastIndexOf('/') + 1;
		fName = fName.substring(index, fName.length());
		System.out.println();
		System.out.println(StringUtils.repeat("*", 60));
		System.out.println(StringUtils.center("  " + fName + "  ", 60, "*"));
		System.out.println(StringUtils.repeat("*", 60));
	}

	
	/*
	 * 	Upload previously retrieved rows to Ms Access db file.
	 */
	public void flushRowsToDb(MsAccessConnector db) {

		for (VarSheetRow row : this.rows) {
			// remove this
			try {
				db.addRow(row);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void close() {

		// Close and free allocated memory
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
