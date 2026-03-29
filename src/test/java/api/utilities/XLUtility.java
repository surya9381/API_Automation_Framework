package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XLUtility {

	FileInputStream fis;
	FileOutputStream fos;
	Workbook book;
	Sheet sh;
	Row row;
	Cell cell;
	
	
	String path;
	public XLUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		sh=book.getSheet(sheetname);
		int rowCount=sh.getLastRowNum();
		book.close();
		fis.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetname,int rownum) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		sh=book.getSheet(sheetname);
		row=sh.getRow(rownum);
		int cellCount=row.getLastCellNum();
		book.close();
		fis.close();
		return cellCount;
	}
	
	public String getCellData(String sheetname,int rownum,int cellnum) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		sh=book.getSheet(sheetname);
		row=sh.getRow(rownum);
		cell=row.getCell(cellnum);
	
		DataFormatter format=new DataFormatter();
		String data=format.formatCellValue(cell);
		book.close();
		fis.close();
		return data;
	}
	
	public String setCellData(String sheetname,int rownum,int cellnum,String data) throws EncryptedDocumentException, IOException
	{
		fis=new FileInputStream(path);
		book=WorkbookFactory.create(fis);
		sh=book.getSheet(sheetname);
		row=sh.getRow(rownum);
		cell=row.getCell(cellnum);
		cell.setCellValue(data);
		book.close();
		fis.close();
		return data;
	}
}
