package org.ecs.project.DtpFileProject;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DtpBook {

	private Workbook wb;
	//sheet names which contains vars
	private ArrayList<String> sheets = new ArrayList<String>();
	//rows with data from vars sheets
	private ArrayList<VarSheetRow> rows = new ArrayList<VarSheetRow>();;

	public DtpBook(String fName) throws BiffException, IOException {

		this.wb = Workbook.getWorkbook(new File(fName));
		this.printBookName(fName);

	}

	public ArrayList<VarSheetRow> getRows() {
		
		return rows;
		
	}

	/*	Get a sheet names where enounter vars.
	 * 	Results put in "sheets"
	 * 
	 */
	private void putVarSheets() {

		String regex = ".*vars.*";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		String[] sheetNames = this.wb.getSheetNames();

		for (int i = 0; i < sheetNames.length; i++) {

			Matcher matcher = pattern.matcher(sheetNames[i]);
			if (matcher.find()) {

				this.sheets.add(sheetNames[i]);
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

		// getting limitation cells
		int limRowIndex = this.wb.getSheet(name).getRows();
		System.out.println("\n" + name + "\n");
		// get vars sheet row
		for (int i = 1; i < limRowIndex; i++) {

			comp = this.wb.getSheet(name).getCell(1, i).getContents();
			desc = this.wb.getSheet(name).getCell(2, i).getContents();
			req = this.wb.getSheet(name).getCell(3, i).getContents();

			VarSheetRow row = new VarSheetRow(comp, desc, req);
			System.out.println(row);
			rows.add(row);

		}

	}
	
	private void printBookName(String fName) {

		//cut after last slash
	    int index=fName.lastIndexOf('/') + 1;
	    fName = fName.substring(index, fName.length());
	    System.out.println();
		System.out.println(StringUtils.repeat("*", 60));
		System.out.println(StringUtils.center("  " + fName + "  ", 60, "*"));
		System.out.println(StringUtils.repeat("*", 60));
	}

	public void close() {

		// Close and free allocated memory
		wb.close();

	}
	
	

}
