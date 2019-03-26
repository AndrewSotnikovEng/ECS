package db;

import java.io.File;
import java.io.IOException;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.IndexBuilder;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;

import entity.VarSheetRow;


/*
 * 	MsAccessConnector - provides a connector for working with MsAccess
 *  db file. It is able to create table if note exists, and write rows
 *  from Excel files. 
 *  
 */

public class MsAccessConnector {

	private Database db = null;
	private Table table;

	public MsAccessConnector() {
		try {
			File dbFile = new File("Dtp_output.accdb");

			if (dbFile.exists() && !dbFile.isDirectory()) {
				this.db = DatabaseBuilder.open(dbFile);
			} else {
				this.db = DatabaseBuilder.create(Database.FileFormat.V2007, dbFile);
			}
		} catch (IOException e) {
			System.out.println("Something go wrong");
		}
	}

	public Database getDb() {
		return this.db;
	}

	/* 
	 * Creating VarSheets table if not exist
	 * 
	 */
	public void createTable() {

		try {
			if (this.db.getTable("VarSheets") == null) {
				this.table = new TableBuilder("VarSheets")
						.addColumn(new ColumnBuilder("id", DataType.LONG).setAutoNumber(true))
						.addColumn(new ColumnBuilder("Component", DataType.TEXT))
						.addColumn(new ColumnBuilder("Description", DataType.TEXT))
						.addColumn(new ColumnBuilder("Requirements", DataType.TEXT))
						.addIndex(new IndexBuilder(IndexBuilder.PRIMARY_KEY_NAME).addColumns("id").setPrimaryKey())
						.toTable(db);
			}

		} catch (Exception e) {
		}

	}

	/* 
	 * Adding row to Ms Access db table
	 * 
	 */
	public void addRow(VarSheetRow varRow) throws IOException {

		this.table = this.db.getTable("VarSheets");
		this.table.addRow(Column.AUTO_NUMBER, varRow.getComponent(), varRow.getDescription(), varRow.getRequirements());

	}

	public void closeDb() {

		try {
			this.db.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
