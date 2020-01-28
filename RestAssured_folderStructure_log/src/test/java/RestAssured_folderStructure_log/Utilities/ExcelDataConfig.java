package RestAssured_folderStructure_log.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.groovy.ast.expr.CastExpression;

public class ExcelDataConfig {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow rowNum;
	public static XSSFCell cell;
	public static XSSFCellStyle style;
	
	public ExcelDataConfig (String excelPath) {
		
		try {
			
			File scr = new File(excelPath);
			
			FileInputStream fileStream = new FileInputStream(scr);
			
			wb = new XSSFWorkbook(fileStream);
			
			
			} 
		
			catch (Exception e) {
			
			System.out.println(e.getMessage());
			
				
			}
		
	}

	public String getData (int sheetNumber, int row, int column)
	{
		
		String data;
		ws = wb.getSheet("Sheet1");  //  wb.getSheet(sheetNumber);
		rowNum = ws.getRow(row);
		cell = rowNum.getCell(column);
		
		XSSFCell cell = ExcelDataConfig.ws.getRow(row).getCell(column); 
		
		data = cell.toString();

        switch (cell.getCellType()) {

        case STRING:
            System.out.println(cell.getRichStringCellValue().getString());
            break;

        case NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {  
                System.out.println(cell.getDateCellValue());
            } else {
                System.out.println((int)cell.getNumericCellValue());   //System.out.println((int)cell.getNumericCellValue()); 
            }
            break;

        case BOOLEAN:
            System.out.println(cell.getBooleanCellValue());
            break;

        case FORMULA:
            System.out.println(cell.getCellFormula());
            break;

        default:
            System.out.println();
    }
		
		return data;
		
			
	}
	
	public int getRowCount (int sheetIndex)
	{
		int row = wb.getSheetAt(sheetIndex).getLastRowNum();
		row = row +1;
		return row;
	}
	
	 public int getColumnCount(String sheetName)
	   {
	       ws = wb.getSheet(sheetName);
	       rowNum = ws.getRow(0);
	       int colCount = rowNum.getLastCellNum();
	       return colCount;
	   }
	 
}
