package com.Library;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.pageFactory.OIPA_PF;

import xls.ShineXlsReader;

public class OIPA_Library extends OIPA_PF {

	public static WebDriver driver1;
	public static String Browser;
	public XSSFWorkbook wb;
	public XSSFSheet sheet1;
	Properties p;
	public Connection con;
	String connectionString;
	public static ShineXlsReader xls = new ShineXlsReader(".\\src\\com\\Utilities\\TestData.xlsx");
	String dbType = xls.getCellData("Config", "DbType", 2);
	String Host = xls.getCellData("Config", "Host", 2);
	String Port = xls.getCellData("Config", "Port", 2);
	String Schema = xls.getCellData("Config", "Schema", 2);
	String DbUserName = xls.getCellData("Config", "Db_UserName", 2);
	String DbPassword = xls.getCellData("Config", "Db_Password", 2);

	public void openBrowser() throws Throwable {

		System.out.println("Launching browser..");
		Browser = "chrome";
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
		options.addArguments("--silent");
		driver1 = new ChromeDriver(options);
		driver1.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver1.manage().deleteAllCookies();
		driver1.manage().window().maximize();

	}

	public WebDriver login(WebDriver driver) throws IOException {

		driver1.get(xls.getCellData("Config", "URL", 2));
		wait(5);
		driver1.findElement(oipa_username).sendKeys(xls.getCellData("Config", "UserName", 2));
		wait(5);
		driver1.findElement(oipa_password).sendKeys(xls.getCellData("Config", "Password", 2));
		wait(5);
		driver1.findElement(oipa_login).click();
		wait(5);
		return driver1;

	}

	public void wait(int seconds) {
		int ms = seconds * 1000;
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void closeBrowser(WebDriver driver) {
		driver1.quit();
	}

	public void selectItemInDropdown(WebDriver driver, String item) {

		String xpath = "//li[contains(.,'" + item + "')]";
		driver1.findElement(By.xpath(xpath)).click();
	}

	public Properties LoadPropertiesFile() throws IOException {

		FileReader reader = new FileReader(".\\src\\com\\Utilities\\config.properties");
		Properties p = new Properties();
		p.load(reader);
		return p;
	}

	public void takeScreenShot(WebDriver driver, String screenshotName) throws IOException {

		TakesScreenshot srcshot = ((TakesScreenshot) driver1);

		File srcFile = srcshot.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(srcFile, new File(".\\src\\com\\Utilities\\Screenshots\\" + screenshotName + ".png"));

	}

	// Checks if element passed in function is exists in screen

	public boolean exists(By by) {

		try {
			driver1.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Highlights the element passed in function

	public static WebDriver HighlightElement(WebDriver driver, By by) {
		WebElement Celement = driver.findElement(by);
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3pxsolid red'", Celement);
		}
		return driver;
	}

	// Unhighlights the element passed in function

	public static WebDriver UnhighlightElement(WebDriver driver, By by) {
		WebElement Celement = driver.findElement(by);
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", Celement);
		}
		return driver;
	}

	// Navigates to the element passed in function in screen

	public void NavigateToElement(WebDriver driver, By by) {
		WebElement Celement = driver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (driver instanceof JavascriptExecutor) {
			js.executeScript("arguments[0].scrollIntoView();", Celement);
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(Celement);
		actions.perform();
	}

	// This will return the row data
	public ArrayList<String> RunQuery(String query) throws SQLException {
		ArrayList<String> Value = new ArrayList<String>();

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		ResultSetMetaData md = rs.getMetaData();
		int colNumber = md.getColumnCount();
		while (rs.next()) {
			for (int i = 0; i < colNumber; i++) {
				Value.add(i, rs.getString(i + 1));
			}
		}
		return Value;
	}

	// This method will establish a connection to DB
	public void connectToDb() {

		try {
			if (dbType.equalsIgnoreCase("Oracle")) {
				connectionString = "jdbc:oracle:thin:@" + Host + ":" + Port + ":" + Schema; // When
																							// SID
																							// is
																							// used
				// String
				// connectionString="jdbc:oracle:thin:@//"+dbServer+":"+port+"/"+serviceName;
				// //When service name is used
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(connectionString, DbUserName, DbPassword);
				System.out.println("Oracle DB Connection Established");
			} else if (dbType.equalsIgnoreCase("SQL")) {
				connectionString = "jdbc:sqlserver://" + Host + ":" + Port + ";databaseName=" + Schema + ";user="
						+ DbUserName + ";password=" + DbPassword;
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				con = DriverManager.getConnection(connectionString);
				System.out.println("SQL DB Connection Established");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// This will update clob data for your specific businessrule
	public void db_updateClob(String fileName, String query) {

		try {
			String contents = new String(Files.readAllBytes(Paths.get(fileName)));
			StringReader reader = new StringReader(contents);
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setCharacterStream(1, reader, contents.length());
			pStmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// This will update the DB whenever you perform any change to DB rules
	public void db_refresh() {

		try {
			if (dbType.equalsIgnoreCase("Oracle")) {
				Statement statement = con.createStatement();
				statement.executeQuery(
						"update assequence set sequencedate = sequencedate + 1 Where Sequencename = 'ConfigurationUpdatedGMT'");
				wait(3);
				statement.executeQuery("commit");
				wait(3);
			} else {
				db_runUpdate(
						"update assequence set sequencedate = sequencedate + 1 Where Sequencename = 'ConfigurationUpdatedGMT'");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This will help just to update any query
	public String db_runUpdate(String query) {
		String result = "";

		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return result;
	}

	// This will help in closing the DB Connection
	public void closeDbConnection() throws SQLException {
		con.close();
	}

	

}
