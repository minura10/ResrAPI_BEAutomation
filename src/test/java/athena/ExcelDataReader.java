package athena;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataReader {

    public static final Iterator<Object[]> excelDataReader(Integer sheetNumber, String filepath) throws IOException {

        ArrayList<Object[]> apiData = new ArrayList<>();

        String projectPath = System.getProperty("user.dir");
        Workbook workbook = new XSSFWorkbook(projectPath +"\\Excel\\"+filepath);
        Sheet firstSheet = workbook.getSheetAt(sheetNumber);


        for (Row row : firstSheet) {
            if (row.getRowNum() == 0)
                continue;

            Cell SchemaCell =row.getCell(2);
            Cell StatusCodeCell =row.getCell(1);
            Cell ValidationMessageCell =row.getCell(3);

            if(sheetNumber == 0) {
                Object ob[] = {(int)StatusCodeCell.getNumericCellValue(), SchemaCell.getStringCellValue(),ValidationMessageCell.getStringCellValue(),};
                apiData.add(ob);
            }
            else{
                Object ob[] = {(int)StatusCodeCell.getNumericCellValue(), SchemaCell.getStringCellValue(),ValidationMessageCell.getStringCellValue(),};
                apiData.add(ob);

            }

        }
        return apiData.iterator();
    }
}
