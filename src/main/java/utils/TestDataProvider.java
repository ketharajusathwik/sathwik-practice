package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginData")

    public Object[][] loginData() {

        return ExcelReader.getLoginData();
    }
}