package utilities;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Utility {
	XSSFWorkbook wb;
	XSSFSheet ws;

	public Excel_Utility(String strTestName) {
		try {
			wb= new XSSFWorkbook(System.getProperty("user.dir")+ "/src/test/resources/excel/" + strTestName + ".xlsx");
			ws = wb.getSheet("Testdata");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCellValueString(int iRow, int iCol){
		String strCellValue = ws.getRow(iRow).getCell(iCol).getStringCellValue();		
		return strCellValue ;

	}
	public double getCellValueNum(int iRow, int iCol){
		double strCellValue = ws.getRow(iRow).getCell(iCol).getNumericCellValue();		
		return strCellValue ;

	}

	public String getRowValue(Sheet sheet, String SearchText) {
		DataFormatter formatter = new DataFormatter();
		String strRownum = null;
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (SearchText.equals(formatter.formatCellValue(cell))) {
					// text matches the string cell value,
					// so find the adjacent cell
					//Cell adjacentCell = row.getCell(cell.getColumnIndex() + 1);
					strRownum =  String.valueOf(row.getRowNum());
					return strRownum;
				
			}

			// search text not found
			
		}

	}
		return strRownum;
		
	}
	
	public String getColValue(Sheet sheet,String SearchText) {
		DataFormatter formatter = new DataFormatter();
		String strColnum = null;
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (SearchText.equals(formatter.formatCellValue(cell))) {
					// text matches the string cell value,
					// so find the adjacent cell
					//Cell adjacentCell = row.getCell(cell.getColumnIndex() + 1);
					strColnum =  String.valueOf(cell.getColumnIndex());
					return strColnum;
				
			}

			// search text not found
			
		}

	}
		return strColnum;
		
		
	}
	
	public String getCellValueStringSearchtxt(String SearchText1, String SearchText2){
		int irow= Integer.parseInt(getRowValue(ws, SearchText1));
		int icol =Integer.parseInt(getColValue(ws,SearchText2));
		String SearchText = getCellValueString(irow, icol);
		return SearchText;
	
	
}
}
