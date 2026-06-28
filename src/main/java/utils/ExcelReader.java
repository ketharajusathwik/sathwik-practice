package utils;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelReader {

    public static Object[][] getLoginData() {
        Object[][] data = null;
        try {
            FileInputStream fis =
                    new FileInputStream(
                            "src/test/resources/testdata/LoginData.xlsx");
            Workbook workbook =
                    new XSSFWorkbook(fis);
            Sheet sheet =
                    workbook.getSheet("Login");
            int rows =
                    sheet.getPhysicalNumberOfRows();
            int cols =
                    sheet.getRow(0)
                         .getPhysicalNumberOfCells();
            data =  new Object[rows - 1][cols];
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i - 1][j] =
                            sheet.getRow(i)
                                 .getCell(j)
                                 .toString();
                }
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}