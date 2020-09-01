package com.tc.groupcustomer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Library.OIPA_Library;

public class GC09_GroupBilling1 extends GC03_Enrollment {

	// Tester : Poornima
	// Functionality : GC_GroupBill_1

	public static WebDriver driver;
	Random rand;
	Statement stmt = null;
	public ArrayList<String> billDetailList = new ArrayList<String>();
	public ArrayList<String> billList = new ArrayList<String>();
	public ArrayList<String> billDetailReconciliationList = new ArrayList<String>();
	public ArrayList<String> BillDetailFieldList = new ArrayList<String>();
	public String policyname;
	public String suspenseNumber;
	public String CustomerName;

	/* Method to retrieve buttons from the table */
	public WebElement tableButtonRetreive_bill(WebDriver driver, int rowno, int columnno, String buttonicon) {
		ResourceBundle.clearCache();
		String first_part = "//table/tbody/tr[";
		String second_part = "]/td[";
		String third_part = "]/button[@title='";
		String fourth_part = "']";
		String final_xpath = first_part + rowno + second_part + columnno + third_part + buttonicon + fourth_part;
		WebElement buttonDisplay = driver.findElement(By.xpath(final_xpath));
		return buttonDisplay;
	}

	/* Method to retrieve data from the table */
	public String commonTableTextRetreive_bill(WebDriver driver, int rowno, int columnno) {
		ResourceBundle.clearCache();
		String first_part = "//table/tbody/tr[";
		String second_part = "]/td[";
		String third_part = "]/span";
		String final_xpath = first_part + rowno + second_part + columnno + third_part;
		String tableData = driver.findElement(By.xpath(final_xpath)).getText();
		return tableData;
	}

	/* Method to retrieve buttons from the table for Plan */
	public WebElement tableExpandRetreive_bill(WebDriver driver, int rowno, int columnno) {
		String first_part = "//table/tbody/tr[";
		String second_part = "]/td[";
		String third_part = "]/div";
		String final_xpath = first_part + rowno + second_part + columnno + third_part;
		WebElement buttonDisplay = driver.findElement(By.xpath(final_xpath));
		return buttonDisplay;
	}

	/* Method to create runtime for screenshot */
	public String runDateTime(WebDriver driver) {

		Date currentDate = new Date();
		String dateToStr = DateFormat.getInstance().format(currentDate);
		dateToStr = dateToStr.replace("/", "-").replace(" ", "_").replace(":", ".");
		return dateToStr;
	}

	/* Method returns the data from a table based on the number of columns provided */
	public ArrayList<String> MultipleRunQuery(String query, int noOfColumns) throws SQLException {

		ArrayList<String> Value = new ArrayList<String>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			for (int i = 1; i <= noOfColumns; i++) {
				Value.add(rs.getString(i));
			}
		}
		con.close();
		return Value;
	}

	/* Method to create Suspense */
	public String createSuspense(WebDriver driver) {

		System.out.println("Info! --------- Creating Suspense ------------------");

		// Select Suspense from Create Menu
		driver.findElement(oipa_createDD).click();
		wait(5);
		selectItemInDropdown(driver, "Suspense");
		wait(5);
		driver.findElement(oipa_CreateButton).click();
		wait(3);
		int randomnumber = rand.nextInt(100);
		// Steps to create Suspense
		driver.findElement(oipa_suspense_policyNumber).sendKeys(xls.getCellData("Suspense", "suspense_policyNumber", 2) + randomnumber);
		wait(5);
		driver.findElement(oipa_suspense_amount).clear();
		wait(5);
		driver.findElement(oipa_suspense_amount).sendKeys(xls.getCellData("Suspense", "suspense_amount", 2));
		wait(5);
		driver.findElement(oipa_suspense_amount).sendKeys(Keys.TAB);
		wait(2);
		driver.findElement(oipa_suspense_firstName).sendKeys(xls.getCellData("Suspense", "suspense_firstName", 2) + randomnumber);
		wait(5);
		driver.findElement(oipa_suspense_firstName).sendKeys(Keys.TAB);
		wait(5);
		driver.findElement(oipa_suspense_lastName).sendKeys(xls.getCellData("Suspense", "suspense_lastName", 2) + randomnumber);
		wait(5);
		driver.findElement(oipa_suspense_lastName).sendKeys(Keys.TAB);
		wait(5);
		driver.findElement(oipa_suspense_accountNumber).sendKeys(xls.getCellData("Suspense", "suspense_accountNumber", 2) + randomnumber);
		wait(5);
		driver.findElement(oipa_suspense_accountNumber).sendKeys(Keys.TAB);
		wait(5);
		driver.findElement(oipa_suspense_checkNumber).sendKeys(xls.getCellData("Suspense", "suspense_checkNumber", 2) + randomnumber);
		wait(5);
		driver.findElement(oipa_suspense_checkNumber).sendKeys(Keys.TAB);
		wait(5);
		driver.findElement(oipa_suspense_bankName).sendKeys(xls.getCellData("Suspense", "suspense_bankName", 2) + randomnumber);
		wait(5);
		driver.findElement(oipa_suspense_bankName).sendKeys(Keys.TAB);
		wait(5);
		driver.findElement(oipa_suspense_bankNumber).sendKeys(xls.getCellData("Suspense", "suspense_bankNumber", 2) + randomnumber);
		wait(5);
		driver.findElement(oipa_suspense_bankNumber).sendKeys(Keys.TAB);
		wait(5);
		driver.findElement(oipa_suspense_saveButton).click();
		wait(5);
		suspenseNumber = driver.findElement(oipa_suspense_suspenseCreated).getText();
		String[] splited = suspenseNumber.split("#");
		System.out.println("Info!---------Suspense created sucessfully with" + "  " + splited[1]);
		suspenseNumber = splited[1];
		wait(6);
		return suspenseNumber;

	}

	@BeforeClass
	public void Prerequisites() throws Throwable {
		
		System.out.println("***************GC09 GroupBilling1******************************");


		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GroupBill_Prerequisites_TestBegins------------------");

		// Connecting to Database and Deleting the previous Bill details if any
		connectToDb();
		try {
			// Executing update query
			System.out.println("Creating statement...");
			stmt = con.createStatement();
			String sql = xls.getCellData("GroupBill_Configuration", "New XMLData", 7);
			stmt.executeUpdate(sql);
			System.out.println("Success!------Deleted Data from AsBillXXXX related tables");
		} catch (SQLException excep) {
			System.out.println("Failed!------ Unable to delete Data from AsBillXXXX related tables");
			excep.printStackTrace();
		}

		// Setting the GenerateBillDetail and MaintainBillDetail transactions to the original values - running Update query in DB

		connectToDb();
		try {
			// Executing update query
			System.out.println("Creating update statement...");
			stmt = con.createStatement();
			String sql = xls.getCellData("GroupBill_Configuration", "Old XMLData", 2);
			stmt.executeUpdate(sql);
			sql = xls.getCellData("GroupBill_Configuration", "Old XMLData", 3);
			stmt.executeUpdate(sql);
			sql = xls.getCellData("GroupBill_Configuration", "Old XMLData", 4);
			stmt.executeUpdate(sql);
			sql = xls.getCellData("GroupBill_Configuration", "Old XMLData", 5);
			stmt.executeUpdate(sql);
			sql = xls.getCellData("GroupBill_Configuration", "Old XMLData", 6);
			stmt.executeUpdate(sql);
			System.out.println("Success!------Updated GenerateBillDetail and MaintainBillDetail transactions for single BillDetail generation");
		} catch (SQLException excep) {
			System.out.println("Failed!------Unable to set the GenerateBillDetail and MaintainBillDetail transactions for single BillDetail");
			excep.printStackTrace();
		}

		// Below methods are the steps for enrolling the policy to a customer
		try {
			CustomerName = gc_customerCreation();
			gc_RelationshipCreation();
			gc_ParentMasterAgreementCreation();
			gc_ProductCreation();
			gc_PlanCreationAndProcess();
			gc_AddClassGroup();
			gc_wholeClassGroupEntities();
			gc_ActiveRelationship();
			policyname = gc_ClientActivities();
			closeBrowser(driver);
			System.out.println("Success!------Prerequisite for Group Customer enrollement Completed");
		} catch (SQLException excep) {
			System.out.println("Failed!-----------Prerequisite Group Customer enrollement");
			excep.printStackTrace();
		}
		// Opening the browser for these tests
		openBrowser();
		driver = login(driver);
		rand = new Random();
		wait(5);

		suspenseNumber = createSuspense(driver);

	}

	/************************************************** Group Billing - Create Bill *************************************************************/

	/** Hierarchy : Oracle Insurance Policy Administration » Functional » Group Bill » Part 1.1 - Group Billing - Create Bill **/

	/* Test 7888: Verify GenerateBillDetail for Policy entity ,BillGroup for Class and GenerateBill for Class */
	@Test
	public void GroupBill01_SingleBillCreation() throws Throwable {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GroupBill01_SingleBillCreation_TestBegins------------------");

		System.out.println("Info!-------Creating GenerateBillDetail at Policy and GenerateBill at Customer Level");

		System.out.println("Info!-------Navigating to Policy Screen for adding GenerateBillDetail activity");

		// Navigating to Policy Screen -> Activities -> Add and Processing 'GenerateBillDetail' activity

		driver.findElement(oipa_searchDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "oipa_searchDD", 2));
		wait(4);
		driver.findElement(oipa_searchInput).sendKeys(policyname);
		wait(4);
		driver.findElement(oipa_searchButton).click();
		wait(9);

		// Navigating to Policy Segments -> Adding BaseCoverage Basic
		driver.findElement(oipa_policy_segments).click();
		wait(5);
		driver.findElement(oipa_segment_segmentDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 3));
		wait(3);
		driver.findElement(oipa_segment_addSegmentButton).click();
		wait(3);
		driver.findElement(oipa_segment_saveBtn).click();
		wait(3);
		Assert.assertEquals(driver.findElement(oipa_addMessage).getText(), "Segment added successfully");
		wait(4);

		// Processing spawned 'Activate' activity
		driver.findElement(oipa_policy_activities).click();
		wait(5);

		// To Handle the Confirmation PopUp
		/*
		 * try { driver.switchTo().activeElement(); wait(3); driver.findElement(oipa_popUpLeaveBtn).isDisplayed(); wait(5); driver.findElement(oipa_popUpLeaveBtn).click(); wait(6);
		 * System.out.println("'Confirmation' pop up is displayed and clicked on 'Leave Page' button"); } catch (Exception e) { System.out.println("'Confirmation' pop up is NOT displayed"); }
		 */

		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		// Adding and processing 'GenerateBillDetail' activity
		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "activitydropdown", 4));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 4));
		wait(8);
		driver.findElement(oipa_policy_activityBillDueDate).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 4));
		wait(8);
		driver.findElement(oipa_policy_activityBillDueAmount).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4));
		wait(8);
		driver.findElement(oipa_policy_activityBillGroupTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillGroupTypeDD", 4));
		wait(8);
		driver.findElement(oipa_policy_activityBillEntityTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillEntityTypeDD", 4));
		wait(8);
		driver.findElement(oipa_activityOKButton).click();
		wait(9);
		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		tableExpandRetreive_bill(driver, 1, 2).click();
		wait(5);
		driver.findElement(oipa_GC_activityBillingTab).click();
		wait(6);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "Single_GenerateBillDetail_PENDING " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// After Processing the GenerateBillDetail, Verifying the generation of Bill Details in Database
		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLGROUPGUID FROM Asbilldetail where AMOUNT = '"
				+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "'", 7);

		if (billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))) {
			System.out.println("After Processing 'GenerateBillDetail' activity, the bill details from AsBillDetail:" + " " + billDetailList);
			System.out.println("Success!---------BillDetail generated Successfully with status as" + " " + billDetailList.get(4));
		}
		else {
			System.out.println("Fail!---------BillDetail NOT generated Successfully");
			Assert.assertTrue(false);
		}

		// Navigating to Customer Screen -> Activities -> To Add and Process 'GenerateBill' activity
		driver.findElement(oipa_searchDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "oipa_searchDD", 3));
		wait(4);
		driver.findElement(oipa_searchInput).sendKeys(CustomerName);
		wait(4);
		driver.findElement(oipa_searchButton).click();
		wait(9);
		driver.findElement(oipa_GC_activities).click();
		wait(8);

		// Adding and processing 'GenerateBill' activity

		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "activitydropdown", 7));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 7));
		wait(8);
		driver.findElement(oipa_GC_activityBillingStartDate).sendKeys(xls.getCellData("GroupBill_1", "GC_activityBillingStartDate", 7));
		wait(8);
		driver.findElement(oipa_GC_activityBillingEndDate).sendKeys(xls.getCellData("GroupBill_1", "GC_activityBillingEndDate", 7));
		wait(8);
		driver.findElement(oipa_GC_activityBillOwnerTypeDD).click();
		wait(5);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "GC_activityBillOwnerTypeDD", 7));
		wait(8);
		driver.findElement(oipa_GC_activityThreshholdMinimumAmount).clear();
		wait(8);
		driver.findElement(oipa_GC_activityThreshholdMinimumAmount).sendKeys(xls.getCellData("GroupBill_1", "GC_activityThreshholdMinimumAmount", 7));
		wait(8);
		driver.findElement(oipa_GC_activityThreshholdMaximumAmount).clear();
		wait(8);
		driver.findElement(oipa_GC_activityThreshholdMaximumAmount).sendKeys(xls.getCellData("GroupBill_1", "GC_activityThreshholdMaximumAmount", 7));
		wait(8);
		driver.findElement(oipa_activityOKButton).click();
		wait(9);
		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + " is processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + " is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		tableExpandRetreive_bill(driver, 1, 2).click();
		wait(5);
		driver.findElement(oipa_GC_activityBillingTab).click();
		wait(6);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "Single_GenerateBill_BILLED " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// After Processing the 'GenerateBill' activity, Verifying the Bill Detail being updated to 'BILLED status in Database
		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLGROUPGUID FROM Asbilldetail where AMOUNT = '"
				+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "'", 7);

		if (billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 2))) {
			System.out.println("After Processing 'GenerateBill' activity, the bill details from AsBillDetail:" + " " + billDetailList);
			System.out.println("Success!---------BillDetail updated Successfully with status as" + " " + billDetailList.get(4));
		}
		else {
			System.out.println("Fail!---------Generated BillDetail status NOT updated");
			Assert.assertTrue(false);
		}

		// Connecting to database to verify the BillReferenceID generation in AsBill table
		connectToDb();
		billList = MultipleRunQuery("SELECT BILLOWNERGUID, BILLREFERENCEID FROM AsBill where BILLOWNERGUID = '" + billDetailList.get(6) + "'", 2);

		if (billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 2))) {
			System.out.println("Group Bill referenceID from AsBill is" + " " + billList.get(1));
			System.out.println("Success!---------Bill generated Successfully");
		}
		else {
			System.out.println("Fail!---------Bill NOT generated Successfully");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill01_SingleBillCreation_TestEnds------------------");

	}

	/************************************************** Group_List Bill Details / Adjust / Link Suspense screens **********************************************/

	/** Hierarchy : Oracle Insurance Policy Administration » Functional » Group Bill » Group/List Bill Details / Adjust / Link Suspense screens - JET Upgrade **/

	/* TC:01 - Verify the UI of Group Bill/ List Bill screen */
	@Test
	public void GroupBill02_ListBillScreenUI() throws Throwable {

		System.out.println("/**************************************************************************************************************/");
		System.out.println("Info! ---------GroupBill02_ListBillScreenUI_TestBegins------------------");

		System.out.println("Info! --------- Verifying the UI of GroupBill_ListBillScreen------------------");
		// Connecting to database to get Group Reference Number from AsBill
		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLGROUPGUID FROM Asbilldetail where AMOUNT = '"
				+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "'", 7);
		System.out.println("Bill details from AsBillDetail:" + " " + billDetailList);

		connectToDb();
		billList = MultipleRunQuery("SELECT BILLOWNERGUID, BILLREFERENCEID FROM AsBill where BILLOWNERGUID = '" + billDetailList.get(6) + "'", 2);
		System.out.println("Group Bill referenceID from AsBill:" + " " + billList.get(1));

		// Searching the above created Bill from Group/List Bill Search
		driver.findElement(oipa_searchDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "oipa_searchDD", 4));
		wait(4);
		driver.findElement(oipa_searchInput).sendKeys(billList.get(1));
		wait(4);
		driver.findElement(oipa_searchButton).click();
		wait(9);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "GroupListBill_ReconciliationScreenUI " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Group/List Bill Reconciliation Screen UI Verification
		if (driver.findElement(oipa_bill_adjustLink).isDisplayed() && driver.findElement(oipa_bill_linkSuspenseLink).isDisplayed()
				&& driver.findElement(oipa_bill_TotalAmountDue).isDisplayed() && driver.findElement(oipa_bill_CurrentReconciledAmount).isDisplayed()) {
			System.out.println(
					"Success!---------Adjust link, LinkSuspense link, Total Amount Due field, Current Reconciled Amount fields are displayed in Group/List Bill Reconciliation Screen");
		}
		else {
			System.out.println(
					"Fail!---------Adjust link, LinkSuspense link, Total Amount Due field, Current Reconciled Amount fields are NOT displayed in Group/List Bill Reconciliation Screen");
			Assert.assertTrue(false);
		}

		// Summary pane information verification
		if (driver.findElement(oipa_bill_GroupBillReferenceID).isDisplayed() && driver.findElement(oipa_bill_GroupBillDate).isDisplayed()
				&& driver.findElement(oipa_bill_GroupCustomerNumber).isDisplayed()) {
			System.out.println(
					"Success!---------Group Bill Reference, Group Bill Date ID, Group Customer Number data are displayed in Summary pane of Group/List Bill Reconciliation Screen");
		}
		else {
			System.out.println(
					"Fail!---------Group Bill Reference, Group Bill Date ID, Group Customer Number data are NOT displayed in Summary pane of Group/List Bill Reconciliation Screen");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill02_ListBillScreenUI_TestEnds------------------");

	}

	/* TC:04 - Verify the UI of Link Suspense screen */
	@Test
	public void GroupBill03_LinkSuspenseScreenUI() throws Throwable {

		System.out.println("/**************************************************************************************************************/");
		System.out.println("Info! ---------GroupBill03_LinkSuspenseScreenUI_TestBegins------------------");

		System.out.println("Info! --------- Verifying the UI of GroupBill_LinkSuspenseScreen------------------");
		// Connecting to database to get Group Reference Number from AsBill
		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLGROUPGUID FROM Asbilldetail where AMOUNT = '"
				+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "'", 7);

		connectToDb();
		billList = MultipleRunQuery("SELECT BILLOWNERGUID, BILLREFERENCEID FROM AsBill where BILLOWNERGUID = '" + billDetailList.get(6) + "'", 2);
		System.out.println("Group Bill referenceID from AsBill:" + " " + billList.get(1));

		// Searching the above created Bill from Group/List Bill Search
		driver.findElement(oipa_searchDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "oipa_searchDD", 4));
		wait(4);
		driver.findElement(oipa_searchInput).sendKeys(billList.get(1));
		wait(4);
		driver.findElement(oipa_searchButton).click();
		wait(9);

		if (driver.findElement(oipa_bill_linkSuspenseLink).isEnabled()) {
			driver.findElement(oipa_bill_linkSuspenseLink).click();
			wait(9);
			System.out.println("Info!--------Link Suspense link is enabled and clicked");

			// Link Suspense Search screen verification
			if (driver.findElement(oipa_bill_typeofSuspense).isDisplayed() && driver.findElement(oipa_bill_typeofSuspenseText).isDisplayed()
					&& driver.findElement(oipa_bill_reconciliationEffectiveDateText).isDisplayed()
					&& driver.findElement(oipa_bill_reconciliationEffectiveDatefield).isDisplayed() && driver.findElement(oipa_bill_linkSuspenseOkBtn).isDisplayed()
					&& driver.findElement(oipa_bill_linkSuspenseCancelBtn).isDisplayed()) {

				// Capturing the Screenshot
				try {
					takeScreenShot(driver, "GroupBill_LinkSuspenseScreenUI " + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}

				driver.findElement(oipa_bill_linkSuspenseCancelBtn).click();
				wait(5);
				System.out.println("Success!---------Type of suspense - Drop Down, Type of suspense - Text, Reconciliation Effective Date - Text, "
						+ "Reconciliation Effective Date - Date Field, LinkSuspense 'Ok' and 'Cancel' buttons are displayed in Link Suspense Search Screen");
			}
			else {
				System.out.println("Fail!---------Type of suspense - Drop Down, Type of suspense - Text, Reconciliation Effective Date - Text, "
						+ "Reconciliation Effective Date - Date Field, LinkSuspense 'Ok' and 'Cancel' buttons are NOT displayed in Link Suspense Search Screen");
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Fail!---------Link Suspense link is NOT enabled");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill03_LinkSuspenseScreenUI_TestEnds------------------");

	}

	// TC:05 - Select a record from suspense search results
	// TC:19 - Link Suspense Action:Verify the Actions section.
	// TC:20 - Link Suspense Action:Verify the Save button (If Amount to Reconcile <=Unattached Amount)
	// TC:18 - Group List Bill Reconciliation Screen: Configure BillReconciliationScreen BR and verify the reconciliation and reversal transactions for Policy level
	@Test
	public void GroupBill04_LinkSuspenseActions() throws Throwable {

		System.out.println("/**************************************************************************************************************/");
		System.out.println("Info! ---------GroupBill04_LinkSuspenseActions_TestBegins------------------");

		System.out.println("Info! --------- Verifying GroupBill_LinkSuspenseActions------------------");

		// Connecting to database to get Group Reference Number from AsBill
		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLGROUPGUID FROM Asbilldetail where AMOUNT = '"
				+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "'", 7);

		connectToDb();
		billList = MultipleRunQuery("SELECT BILLOWNERGUID, BILLREFERENCEID FROM AsBill where BILLOWNERGUID = '" + billDetailList.get(6) + "'", 2);
		System.out.println("Group Bill referenceID from AsBill:" + " " + billList.get(1));

		// Searching the above created Bill from Group/List Bill Search
		driver.findElement(oipa_searchDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "oipa_searchDD", 4));
		wait(4);
		driver.findElement(oipa_searchInput).sendKeys(billList.get(1));
		wait(4);
		driver.findElement(oipa_searchButton).click();
		wait(9);

		if (driver.findElement(oipa_bill_linkSuspenseLink).isEnabled()) {
			driver.findElement(oipa_bill_linkSuspenseLink).click();
			wait(5);

			// Checking for Reconciliation Effective Date as future date and the error message display

			driver.findElement(oipa_bill_typeofSuspenseDD).click();
			wait(5);
			selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "bill_typeofSuspenseDD", 2));
			wait(5);
			driver.findElement(oipa_bill_reconciliationEffectiveDatefield).clear();
			wait(4);
			driver.findElement(oipa_bill_reconciliationEffectiveDatefield).sendKeys(xls.getCellData("GroupBill_1", "bill_reconciliationEffectiveDatefield", 2));
			wait(8);
			driver.findElement(oipa_bill_linkSuspense_suspenseNumber).sendKeys(suspenseNumber);
			wait(5);
			driver.findElement(oipa_bill_linkSuspense_searchButton).click();
			wait(5);
			driver.findElement(oipa_bill_linkSuspense_selectrow).click();
			wait(5);
			driver.findElement(oipa_bill_linkSuspenseOkBtn).click();
			wait(8);

			// Creating the JavascriptExecutor interface object
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Find element by Xpath and storing in variable "Element"
			WebElement Element = driver.findElement(oipa_bill_reconciliationEffectiveDateErrorMsg);
			wait(4);

			// This will scroll the page till the element is found
			js.executeScript("arguments[0].scrollIntoView();", Element);

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "InvalidDate_ErrorMessage " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Assert.assertTrue(driver.findElement(oipa_bill_reconciliationEffectiveDateErrorMsg).isDisplayed(), "Validation error saying 'Invalid Date' is NOT displayed");

			// Reentering the valid date in Reconciliation EffectiveDate field
			driver.findElement(oipa_bill_reconciliationEffectiveDatefield).clear();
			wait(4);
			driver.findElement(oipa_bill_reconciliationEffectiveDatefield).sendKeys(xls.getCellData("GroupBill_1", "bill_reconciliationEffectiveDatefield", 3));
			wait(8);
			driver.findElement(oipa_bill_linkSuspenseOkBtn).click();
			wait(8);

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "GroupBill_LinkSuspenseActions " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Suspense Details section verification
			if (driver.findElement(oipa_bill_reconciliationEffectiveDate).isDisplayed() && driver.findElement(oipa_bill_suspenseNumber).isDisplayed()
					&& driver.findElement(oipa_bill_unattachedAmount).isDisplayed() && driver.findElement(oipa_bill_totalofAmounttoReconcile).isDisplayed()
					&& driver.findElement(oipa_bill_reconcileallcheckBox).isDisplayed()) {
				System.out.println("Success!---------Reconciliation Effective Date, Suspense Number, Unattached Amount, Total of Amount to Reconcile,"
						+ " reconcile-all Check box are displayed in Bill Suspense Details section");
			}
			else {
				System.out.println("Fail!---------Reconciliation Effective Date, Suspense Number, Unattached Amount, Total of Amount to Reconcile,"
						+ " reconcile-all Check box are NOT displayed in Bill Suspense Details section");
				Assert.assertTrue(false);
			}

			// Bill Detail Search Criteria section verification
			if (driver.findElement(oipa_bill_searchCriteria_BillGroupType).isDisplayed() && driver.findElement(oipa_bill_searchCriteria_Amount).isDisplayed()
					&& driver.findElement(oipa_bill_searchCriteria_PolicyNumber).isDisplayed() && driver.findElement(oipa_bill_searchCriteria_FindBtn).isDisplayed()) {
				System.out.println("Success!---------Bill Group Type, Amount, Policy Number, Find button are displayed in Bill Detail Search Criteria section");
			}
			else {
				System.out.println("Fail!---------Bill Group Type, Amount, Policy Number, Find button are NOT displayed in Bill Detail Search Criteria section");
				Assert.assertTrue(false);
			}

			// Bill Detail Search Results section verification
			if (driver.findElement(oipa_bill_searchResult_Amount).isDisplayed() && driver.findElement(oipa_bill_searchResult_PolicyNumber).isDisplayed()
					&& driver.findElement(oipa_bill_searchResult_AmountForReconciliation).isDisplayed() && driver.findElement(oipa_bill_searchResult_Action).isDisplayed()) {
				System.out.println(
						"Success!---------Amount, Policy Number, Amount for reconciliation, Action are displayed in Bill Detail Search Results section as tabular header");
			}
			else {
				System.out.println(
						"Fail!---------Amount, Policy Number, Amount for reconciliation, Action are NOT displayed in Bill Detail Search Results section as tabular header");
				Assert.assertTrue(false);
			}

			// Verifying adjust and LinkSuspense links are disabled
			if (driver.findElement(oipa_bill_adjustLink).getAttribute("aria-disabled").equalsIgnoreCase(xls.getCellData("GroupBill_1", "aria-disabled", 2))
					&& driver.findElement(oipa_bill_linkSuspenseLink).getAttribute("aria-disabled").equalsIgnoreCase(xls.getCellData("GroupBill_1", "aria-disabled", 2))) {
				System.out.println("Adjust link and LinkSuspense link are disabled in Group/List Bill Reconciliation Screen");

			}
			else {
				System.out.println("Adjust link and LinkSuspense link are enabled in Group/List Bill Reconciliation Screen");
				Assert.assertTrue(false);
			}

		}
		else {
			System.out.println("Fail!---------Link Suspense link is NOT enabled");
			Assert.assertTrue(false);
		}

		// Verifying the error message if the Amount to Reconcile field value Greater than Amount Due and trying to process/Save.

		driver.findElement(oipa_bill_searchResult_editIcon).click();
		wait(8);
		driver.findElement(oipa_bill_searchResult_pendingReconciliationAmount).clear();
		wait(8);
		driver.findElement(oipa_bill_searchResult_pendingReconciliationAmount).sendKeys(xls.getCellData("GroupBill_1", "bill_searchResult_pendingReconciliationAmount", 2));
		wait(8);
		driver.findElement(oipa_bill_searchResult_actionIcon).click();
		wait(8);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "AmountToReconcile_Greaterthan_AmountDue_ErrorMessage " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (driver.findElement(oipa_bill_errorMessage).isDisplayed()) {
			System.out.println("Success!---------Error Message is displayed as: " + " " + driver.findElement(oipa_bill_errorMessage).getText());
		}
		else {
			System.out.println("Fail!---------Error Message is NOT displayed when the Amount to Reconcile on is greater than the Amount Due on the Bill Detail");
			Assert.assertTrue(false);
		}

		// Re-entering the new value in Reconciliation amount Less than Amount Due field to rectify the error message and proceed for Reconciliation
		driver.findElement(oipa_bill_searchResult_pendingReconciliationAmount).clear();
		wait(8);
		driver.findElement(oipa_bill_searchResult_pendingReconciliationAmount).sendKeys(xls.getCellData("GroupBill_1", "bill_searchResult_pendingReconciliationAmount", 3));
		wait(8);
		driver.findElement(oipa_bill_searchResult_actionIcon).click();
		wait(8);
		driver.findElement(oipa_bill_searchResult_saveBtn).click();
		wait(8);
		driver.switchTo().activeElement();
		driver.findElement(oipa_bill_processOkBtn).click();
		wait(8);
		driver.findElement(oipa_bill_processOkConfirmBtn).click();
		wait(8);

		// Creating the JavascriptExecutor interface object
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// This will scroll the web page till end.
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "AmountToReconcile_Lessthan_AmountDue " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Navigating to Policy Screen -> Activities -> Add and Processing the Spawned 'Reconciliation' activity

		driver.findElement(oipa_searchDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "oipa_searchDD", 2));
		wait(4);
		driver.findElement(oipa_searchInput).sendKeys(policyname);
		wait(4);
		driver.findElement(oipa_searchButton).click();
		wait(9);

		driver.findElement(oipa_policy_activities).click();
		wait(5);

		// To Handle the Confirmation PopUp

		/*
		 * try { driver.switchTo().activeElement(); wait(3); driver.findElement(oipa_popUpLeaveBtn).isDisplayed(); wait(5); driver.findElement(oipa_popUpLeaveBtn).click(); wait(6);
		 * System.out.println("'Confirmation' pop up is displayed and clicked on 'Leave Page' button"); } catch (Exception e) { System.out.println("'Confirmation' pop up is NOT displayed"); }
		 */

		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))
				&& commonTableTextRetreive_bill(driver, 1, 4).equals(xls.getCellData("GroupBill_1", "bill_reconciliationEffectiveDatefield", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its Effective Data and status are:"
					+ "  " + commonTableTextRetreive_bill(driver, 1, 4) + " " + commonTableTextRetreive_bill(driver, 1, 6));
		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its Effective Data and status are:"
					+ "  " + commonTableTextRetreive_bill(driver, 1, 4) + " " + commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		// Verifying the billdetail status updated to 'UNDERRECON' in AsBillDetail table
		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLGROUPGUID FROM Asbilldetail where AMOUNT = '"
				+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "'", 7);

		if (billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 4))) {

			System.out.println("Info!---------After Processing 'Reconciliation' activity the bill detail status from AsBillDetail:" + " " + billDetailList);

			System.out.println(
					"Success!---------Reconciliation Completed Successfully with  Reconciliation amount Less than Amount Due and the status is" + " " + billDetailList.get(4));
		}
		else {
			System.out.println("Fail!---------Reconciliation Not Completed and the bill status is" + " " + billDetailList.get(4));
			Assert.assertTrue(false);
		}

		// Verifying the amount and status in AsBillDetailReconciliation
		connectToDb();
		billDetailReconciliationList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, STATUSCODE FROM AsBillDetailReconciliation where AMOUNT = '"
				+ xls.getCellData("GroupBill_1", "bill_searchResult_pendingReconciliationAmount", 3) + "'", 3);

		System.out.println("Info!---------After Processing 'Reconciliation' activity the bill detail status from AsBillDetail:" + " " + billDetailReconciliationList);

		System.out.println("Info! ---------GroupBill04_LinkSuspenseActions_TestEnds------------------");

	}

	/* TC:02 - Verify the UI of Adjust screen */
	@Test
	public void GroupBill05_AdjustScreenUI() throws Throwable {

		System.out.println("/**************************************************************************************************************/");
		System.out.println("Info! ---------GroupBill05_AdjustScreenUI_TestBegins------------------");

		System.out.println("Info! --------- Verifying the UI of GroupBill_AdjustScreen------------------");

		// Connecting to database to get Group Reference Number from AsBill
		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLGROUPGUID FROM Asbilldetail where AMOUNT = '"
				+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "'", 7);

		connectToDb();
		billList = MultipleRunQuery("SELECT BILLOWNERGUID, BILLREFERENCEID FROM AsBill where BILLOWNERGUID = '" + billDetailList.get(6) + "'", 2);
		System.out.println("Group Bill referenceID from AsBill:" + " " + billList.get(1));

		// Searching the above created Bill from Group/List Bill Search
		driver.findElement(oipa_searchDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "oipa_searchDD", 4));
		wait(4);
		driver.findElement(oipa_searchInput).sendKeys(billList.get(1));
		wait(4);
		driver.findElement(oipa_searchButton).click();
		wait(9);
		if (driver.findElement(oipa_bill_adjustLink).isEnabled()) {
			driver.findElement(oipa_bill_adjustLink).click();
			System.out.println("Adjust link is enabled and clicked");

			if (driver.findElement(oipa_bill_adjust_billExpandCollapseBtn).getAttribute("aria-expanded")
					.equalsIgnoreCase(xls.getCellData("GroupBill_1", "aria-expanded", 2))) {
				System.out.println("'Bill Detail Reconciliation Search criteria' section expand/collapse button is expanded");

				// 'Bill Detail Reconciliation Search criteria' section
				if (driver.findElement(oipa_bill_adjust_billDetailReferenceNumber).isDisplayed()
						&& driver.findElement(oipa_bill_adjust_billDetailReconciliationAmount).isDisplayed()
						&& driver.findElement(oipa_bill_adjust_billStatusDD).isDisplayed()) {
					System.out.println(
							"Info!---------Bill Detail Reference Number Field, Bill Detail Reconciliation Amount, Bill Status DD is displayed in Bill Detail Reconciliation Search Criteria");

					driver.findElement(oipa_bill_adjust_billDetailReferenceNumber).sendKeys(xls.getCellData("GroupBill_1", "bill_adjust_billDetailReferenceNumber", 2));
					wait(8);
					driver.findElement(oipa_bill_adjust_billFindBtn).click();
					wait(8);

					// Bill Detail Search Results section verification
					if (driver.findElement(oipa_bill_searchResult_PolicyNumber).isDisplayed()
							&& driver.findElement(oipa_bill_searchResult_AmountForReconciliation).isDisplayed()
							&& driver.findElement(oipa_bill_searchResult_Action).isDisplayed() && driver.findElement(oipa_bill_adjust_editIcon).isDisplayed()) {
						System.out.println(
								"Success!---------Policy Number, Amount for reconciliation,Action and Pencil (Edit) link is displayed in Bill Detail Search Results section in tabular section");
					}
					else {
						System.out.println(
								"Fail!---------Policy Number, Amount for reconciliation,Action and Pencil (Edit) link is NOT displayed in Bill Detail Search Results section in tabular section");
						Assert.assertTrue(false);
					}

					driver.findElement(oipa_bill_adjust_editIcon).click();
					wait(8);
					driver.findElement(oipa_bill_adjust_searchResult_reconciliationAmount).clear();
					wait(5);
					driver.findElement(oipa_bill_adjust_searchResult_reconciliationAmount).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4));
					wait(8);
					driver.findElement(oipa_bill_adjust_searchResult_actionIcon).click();
					wait(8);

					if (driver.findElement(oipa_bill_adjust_saveBtn).isDisplayed() && driver.findElement(oipa_bill_adjust_cancelBtn).isDisplayed()) {
						System.out.println("Success!---------Save and Cancel buttons are displayed in Adjust Reconciliation Screen");
					}
					else {
						System.out.println("Fail!---------Save and Cancel buttons are NOT displayed in Adjust Reconciliation Screen");
						Assert.assertTrue(false);
					}

				}
				else {
					System.out.println(
							"Fail!---------Bill Detail Reference Number Field, Bill Detail Reconciliation Amount, Bill Status DD is NOT displayed in Bill Detail Reconciliation Search Criteria");
					Assert.assertTrue(false);
				}

			}
			else {
				System.out.println("Fail!---------'Bill Detail Reconciliation Search criteria' section expand/collapse button is NOT expanded");
				Assert.assertTrue(false);
			}

			driver.findElement(oipa_bill_adjust_billExpandCollapseBtn).click();
			wait(8);
			if (driver.findElement(oipa_bill_adjust_billExpandCollapseBtn).getAttribute("aria-expanded")
					.equalsIgnoreCase(xls.getCellData("GroupBill_1", "aria-expanded", 3))) {
				System.out.println("Info!---------'Bill Detail Reconciliation Search criteria' section expand/collapse button is collapsed");
			}
			else {
				System.out.println("'Bill Detail Reconciliation Search criteria' section expand/collapse button is NOT collapsed");
				Assert.assertTrue(false);
			}

		}
		else {
			System.out.println("Fail!---------Adjust link is Disabled");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill05_AdjustScreenUI_TestEnds------------------");

	}

	// TC:03 - Submit a bill for reconciliation
	// TC:18 - Group List Bill Reconciliation Screen: Configure BillReconciliationScreen BR and verify the reconciliation and reversal transactions for Policy level
	@Test
	public void GroupBill06_SubmitBillReconciliation() throws Throwable {

		System.out.println("/**************************************************************************************************************/");
		System.out.println("Info! ---------GroupBill06_SubmitBillReconciliation_TestBegins------------------");

		System.out.println("Info! --------- Submitting Bill Reconciliation------------------");

		// Connecting to database to get Group Reference Number from AsBill
		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLGROUPGUID FROM Asbilldetail where AMOUNT = '"
				+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "'", 7);

		connectToDb();
		billList = MultipleRunQuery("SELECT BILLOWNERGUID, BILLREFERENCEID FROM AsBill where BILLOWNERGUID = '" + billDetailList.get(6) + "'", 2);
		System.out.println("Group Bill referenceID from AsBill:" + " " + billList.get(1));

		// Searching the above created Bill from Group/List Bill Search
		driver.findElement(oipa_searchDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "oipa_searchDD", 4));
		wait(4);
		driver.findElement(oipa_searchInput).sendKeys(billList.get(1));
		wait(4);
		driver.findElement(oipa_searchButton).click();
		wait(9);
		if (driver.findElement(oipa_bill_adjustLink).isEnabled()) {

			// Verifying the Cancel' button functionality
			driver.findElement(oipa_bill_adjustLink).click();
			wait(8);
			driver.findElement(oipa_bill_adjust_billDetailReferenceNumber).sendKeys(xls.getCellData("GroupBill_1", "bill_adjust_billDetailReferenceNumber", 2));
			wait(8);
			driver.findElement(oipa_bill_adjust_billFindBtn).click();
			wait(8);
			driver.findElement(oipa_bill_adjust_editIcon).click();
			wait(8);

			driver.findElement(oipa_bill_adjust_searchResult_reconciliationAmount).clear();
			wait(5);
			driver.findElement(oipa_bill_adjust_searchResult_reconciliationAmount).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4));
			wait(8);
			driver.findElement(oipa_bill_adjust_searchResult_actionIcon).click();
			wait(8);
			driver.findElement(oipa_bill_adjust_cancelBtn).click();
			wait(8);

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "NoSearchCriteria_AdjustCancelBtn " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			// To find the elements not present when cancel button is clicked
			List<WebElement> listOfElements = driver.findElements(oipa_bill_adjust_billExpandCollapseBtn);
			if (listOfElements.size() == 0) {
				System.out.println("Info!---------'Bill Detail Reconciliation Search criteria' section should not displayed");
			}
			else {
				System.out.println("'Bill Detail Reconciliation Search criteria' section is displayed");
				Assert.assertTrue(false);
			}

			/*
			 * try { driver.findElement(oipa_bill_adjust_billExpandCollapseBtn); wait(8); System.out. println("'Bill Detail Reconciliation Search criteria' section is displayed" ); } catch (Exception
			 * e) { System.out. println("'Bill Detail Reconciliation Search criteria' section should not displayed" ); }
			 */

			// Verifying the 'Save' button functionality and Submit the bill Reconciliation

			driver.findElement(oipa_bill_adjustLink).click();
			wait(8);
			driver.findElement(oipa_bill_adjust_billDetailReferenceNumber).sendKeys(xls.getCellData("GroupBill_1", "bill_adjust_billDetailReferenceNumber", 2));
			wait(8);
			driver.findElement(oipa_bill_adjust_billFindBtn).click();
			wait(8);
			driver.findElement(oipa_bill_adjust_editIcon).click();
			wait(8);
			driver.findElement(oipa_bill_adjust_searchResult_reconciliationAmount).clear();
			wait(5);
			driver.findElement(oipa_bill_adjust_searchResult_reconciliationAmount).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4));
			wait(8);
			driver.findElement(oipa_bill_adjust_searchResult_actionIcon).click();
			wait(8);
			driver.findElement(oipa_bill_adjust_saveBtn).click();
			wait(8);
			driver.switchTo().activeElement();
			driver.findElement(oipa_bill_adjust_processOkBtn).click();
			wait(8);
			driver.findElement(oipa_bill_adjust_processOkConfirmBtn).click();
			wait(8);

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "SubmitSingle_BillReconciliation " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Navigating to Policy Screen -> Activities -> Add and Processing the Spawned 'ReconciliationReversal' activity

			driver.findElement(oipa_searchDD).click();
			wait(5);
			selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "oipa_searchDD", 2));
			wait(4);
			driver.findElement(oipa_searchInput).sendKeys(policyname);
			wait(4);
			driver.findElement(oipa_searchButton).click();
			wait(9);

			driver.findElement(oipa_policy_activities).click();
			wait(5);

			// To Handle the Confirmation PopUp
			/*
			 * try { driver.switchTo().activeElement(); wait(3); driver.findElement(oipa_popUpLeaveBtn).isDisplayed(); wait(5); driver.findElement(oipa_popUpLeaveBtn).click(); wait(6);
			 * System.out.println("'Confirmation' pop up is displayed and clicked on 'Leave Page' button"); } catch (Exception e) { System.out.println("'Confirmation' pop up is NOT displayed"); }
			 */

			// To trace the Spawned 'ReconciliationReversal' activity in the activity table and Process the same
			List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));

			for (int rowno = 1; rowno <= rows.size(); rowno++) {

				if (commonTableTextRetreive_bill(driver, rowno, 3).equals(xls.getCellData("GroupBill_1", "activitydropdown", 8))) {
					tableButtonRetreive_bill(driver, rowno, 8, "Process").click();
					wait(8);

					if (commonTableTextRetreive_bill(driver, rowno - 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
						System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, rowno - 1, 3) + "' " + " is processed successfully and its status is:"
								+ "  " + commonTableTextRetreive_bill(driver, rowno - 1, 6));
					}
					else {
						System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, rowno - 1, 3) + "' " + " is NOT processed successfully and its status is:"
								+ "  " + commonTableTextRetreive_bill(driver, rowno - 1, 6));
						Assert.assertTrue(false);
					}
					break;
				}
			}

			// Verifying the bill details status from AsBillDetail
			connectToDb();
			billDetailList = MultipleRunQuery(
					"SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLGROUPGUID FROM Asbilldetail where AMOUNT = '"
							+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "'",
					7);

			System.out.println("Info!---------After Processing 'ReconciliationReversal' activity the bill detail status from AsBillDetail:" + " " + billDetailList.get(4));

			// Connecting to database to get the data from AsBillDetailReconciliation
			connectToDb();
			billDetailReconciliationList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, STATUSCODE FROM AsBillDetailReconciliation where AMOUNT = '"
					+ xls.getCellData("GroupBill_1", "bill_searchResult_pendingReconciliationAmount", 3) + "'", 3);

			System.out.println("Info!---------After Processing 'ReconciliationReversal' activity the bill detail Reconciliation status from AsBillDetailReconciliation:" + " "
					+ billDetailReconciliationList.get(2));

			// Again Processing the spawned activity i.e., 'Reconciliation' which is spawned by processing 'ReconciliationReversal' activity
			tableButtonRetreive_bill(driver, 1, 8, "Process").click();
			wait(8);

			if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
				System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its status is:" + "  "
						+ commonTableTextRetreive_bill(driver, 1, 6));

				// Capturing the screenshot for Reconciliation for GenerateBillDetail activity
				try {
					if (commonTableTextRetreive_bill(driver, 3, 3).equals(xls.getCellData("GroupBill_1", "activitydropdown", 4))) {
						tableExpandRetreive_bill(driver, 3, 2).click();
						wait(5);
						driver.findElement(oipa_GC_activityBillingTab).click();
						wait(6);
						// Creating the JavascriptExecutor interface object
						JavascriptExecutor js = (JavascriptExecutor) driver;

						// This will scroll the web page till end.
						js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

						// Capturing the Screenshot
						try {
							takeScreenShot(driver, "Single_GenerateBill_RECONCILED " + runDateTime(driver));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				} catch (Exception ex) {
					System.out.println("Unable to locate the Activity: " + commonTableTextRetreive_bill(driver, 3, 3) + "and Cannot capture the Screenshot:" + ex);
				}

			}
			else {

				System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its status is:" + "  "
						+ commonTableTextRetreive_bill(driver, 1, 6));
				Assert.assertTrue(false);
			}

			// Verifying the bill details status updated to 'RECONCILED' in AsBillDetail
			connectToDb();
			billDetailList = MultipleRunQuery(
					"SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLGROUPGUID FROM Asbilldetail where AMOUNT = '"
							+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "'",
					7);

			if (billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 5))) {
				System.out.println("Info!---------After Processing 'Reconciliation' activity the bill detail status from AsBillDetail:" + " " + billDetailList);

				System.out.println("Success!---------Reconciliation Completed Successfully with status as" + " " + billDetailList.get(4));
			}
			else {
				System.out.println("Fail!---------Reconciliation Not Completed and the bill status is" + " " + billDetailList.get(4));
				Assert.assertTrue(false);
			}

			// Connecting to database to verify the status of the amounts entered in AsBillDetailReconciliation
			connectToDb();
			billDetailReconciliationList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, STATUSCODE FROM AsBillDetailReconciliation where AMOUNT = '"
					+ xls.getCellData("GroupBill_1", "bill_searchResult_pendingReconciliationAmount", 3) + "'", 3);

			System.out.println("Info!---------After Processing 'Reconciliation' activity the bill detail Reconciliation status for amount" + " '"
					+ xls.getCellData("GroupBill_1", "bill_searchResult_pendingReconciliationAmount", 3) + "' " + "from AsBillDetailReconciliation:" + " "
					+ billDetailReconciliationList.get(2));

			connectToDb();
			billDetailReconciliationList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, STATUSCODE FROM AsBillDetailReconciliation where AMOUNT = '"
					+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "'", 3);

			System.out.println("Info!---------After Processing 'Reconciliation' activity the bill detail Reconciliation status for amount" + " '"
					+ xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 4) + "' " + "from AsBillDetailReconciliation:" + " "
					+ billDetailReconciliationList.get(2));

		}
		else {
			System.out.println("Fail!---------Adjust link is Disabled and Reconciliation could NOT happen");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill06_SubmitBillReconciliation_TestEnds------------------");

	}

	/************************************************** GenerateBillDetail_Single/MultipleBillDetails **********************************************/

	/**
	 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group Bill » Bug - 26121826/26032445 - Group Billing (Create / Maintain Bill Detail) » Enh 26121826 - GENERATION OF MULTIPLE
	 * ASBILLDETAIL RECORDS FROM A SINGLE ACTIVITY
	 **/

	// TC 41060 Modify/Maintain BillDetail for a bill with single billdetail
	@Test
	public void GroupBill07_MaintainBillDetail_SingleBillDetail() throws Throwable {

		System.out.println("/**************************************************************************************************************/");
		System.out.println("Info! ---------GroupBill07_MaintainBillDetail_SingleBillDetail_TestBegins------------------");

		System.out.println("Info! --------- Verifying the Maintain Bill Detail functionality for Single Bill Detail------------------");
		// Adding and processing 'GenerateBillDetail' activity

		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "activitydropdown", 5));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 5));
		wait(8);
		driver.findElement(oipa_policy_activityBillDueDate).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 5));
		wait(8);
		driver.findElement(oipa_policy_activityBillDueAmount).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 5));
		wait(8);
		driver.findElement(oipa_policy_activityBillGroupTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillGroupTypeDD", 4));
		wait(8);
		driver.findElement(oipa_policy_activityBillEntityTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillEntityTypeDD", 4));
		wait(8);
		driver.findElement(oipa_activityOKButton).click();
		wait(9);
		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));

			// Capturing the screenshot for GenerateBillDetail activity - For Amount Field
			try {
				if (commonTableTextRetreive_bill(driver, 1, 3).equals(xls.getCellData("GroupBill_1", "activitydropdown", 5))) {
					tableExpandRetreive_bill(driver, 1, 2).click();
					wait(5);
					driver.findElement(oipa_GC_activityBillingTab).click();
					wait(6);

					// Capturing the Screenshot
					try {
						takeScreenShot(driver, "Single_GenerateBillDetail_Pending " + runDateTime(driver));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (Exception ex) {
				System.out.println("Unable to locate the Activity: " + commonTableTextRetreive_bill(driver, 1, 3) + "and Cannot capture the Screenshot:" + ex);
			}
		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		// After Processing the GenerateBillDetail, Verifying the 'PENDING' bill detail in DB
		connectToDb();
		billDetailList = MultipleRunQuery(
				"SELECT BILLDETAILGUID, AMOUNT, STATUS FROM Asbilldetail where AMOUNT = '" + xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 5) + "'", 3);

		String BillDetailguid = billDetailList.get(0);

		if (billDetailList.get(1).equals(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 5))
				&& billDetailList.get(2).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))) {
			System.out.println("Info!---------After Processing 'GenerateBillDetail' activity the bill details from AsBillDetail:" + " " + billDetailList);
			System.out.println("Success!---------BillDetail generated Successfully with status:" + " " + billDetailList.get(2));
		}
		else {
			System.out.println("Fail!---------BillDetail NOT generated Successfully");
			Assert.assertTrue(false);
		}

		// Adding and processing 'MaintainBillDetail' activity

		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "activitydropdown", 11));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 11));
		wait(8);
		driver.findElement(oipa_policy_activityBillDetailDD).click();
		wait(5);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 11));
		wait(6);
		driver.findElement(oipa_policy_activityNewAmount).clear();
		wait(3);
		driver.findElement(oipa_policy_activityNewAmount).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount1", 11));
		wait(5);
		driver.findElement(oipa_activityOKButton).click();
		wait(12);
		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));

			// Capturing the screenshot for GenerateBillDetail activity - For Amount Field
			try {
				if (commonTableTextRetreive_bill(driver, 2, 3).equals(xls.getCellData("GroupBill_1", "activitydropdown", 5))) {
					tableExpandRetreive_bill(driver, 2, 2).click();
					wait(5);
					driver.findElement(oipa_GC_activityBillingTab).click();
					wait(6);

					// Capturing the Screenshot
					try {
						takeScreenShot(driver, "Single_GenerateBillDetail_AmountUpdated " + runDateTime(driver));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (Exception ex) {
				System.out.println("Unable to locate the Activity: " + commonTableTextRetreive_bill(driver, 2, 3) + "and Cannot capture the Screenshot:" + ex);
			}

		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		// After Processing the MaintainBillDetail, Verifying the Data in DB with the BillDetailGUID which generated on processing GenerateBillDetail activity

		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, STATUS FROM Asbilldetail where BILLDETAILGUID = '" + BillDetailguid + "'", 3);

		if (billDetailList.get(1).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 11))) {

			System.out.println(
					"Info!---------After Processing 'MaintainBillDetail' activity, the amount is updated and the bill detail from AsBillDetail:" + " " + billDetailList);
		}
		else {
			System.out.println("Fail!---------BillDetail Amount NOT updated correctly");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill07_MaintainBillDetail_SingleBillDetail_TestEnds------------------");

	}

	// TC 41058 Generate a bill with multiple generatebilldetail.
	// TC 43269 Verify that dynamic field as well as fixed fields are getting populated in DB after processing GenerateBillDetail activity
	@Test
	public void GroupBill08_MultipleGenerateBillDetails() throws Throwable {

		System.out.println("/**************************************************************************************************************/");
		System.out.println("Info! ---------GroupBill08_MultipleGenerateBillDetails_TestBegins------------------");

		System.out.println("Info! --------- Generating Multiple Bill details using GenerateBillDetail Array------------------");

		// Updating the GenerateBillDetail transaction to generate Multiple bill details from running Update query in DB
		connectToDb();
		try {
			// Executing update query
			System.out.println("Creating update statement...");
			stmt = con.createStatement();
			String sql = xls.getCellData("GroupBill_Configuration", "New XMLData", 2);
			stmt.executeUpdate(sql);
			sql = xls.getCellData("GroupBill_Configuration", "New XMLData", 3);
			stmt.executeUpdate(sql);
			sql = xls.getCellData("GroupBill_Configuration", "New XMLData", 4);
			stmt.executeUpdate(sql);			
			System.out.println("Success!------Updated GenerateBillDetail transaction for Multiple BillDetails generation");
		} catch (SQLException excep) {
			System.out.println("Fail!------Unable to Update GenerateBillDetail transaction for Multiple BillDetails generation");
			excep.printStackTrace();
		}

		// Adding and processing 'GenerateBillDetail' activity to generate Multiple Bill Details
		driver.findElement(oipa_policy_activityRefreshBtn).click();
		wait(10);
		driver.findElement(oipa_policy_segments).click();
		wait(10);
		driver.findElement(oipa_policy_activities).click();
		wait(10);

		// To Handle the Confirmation PopUp
		/*
		 * try { driver.switchTo().activeElement(); wait(3); driver.findElement(oipa_popUpLeaveBtn).isDisplayed(); wait(5); driver.findElement(oipa_popUpLeaveBtn).click(); wait(6);
		 * System.out.println("'Confirmation' pop up is displayed and clicked on 'Leave Page' button"); } catch (Exception e) { System.out.println("'Confirmation' pop up is NOT displayed"); }
		 */

		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "activitydropdown", 9));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 9));
		wait(8);
		driver.findElement(oipa_policy_activityBillDueDate).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 9));
		wait(5);
		driver.findElement(oipa_policy_activityBillDueAmount).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 9));
		wait(6);
		driver.findElement(oipa_policy_activityAmount1).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount1", 9));
		wait(6);
		driver.findElement(oipa_policy_activityAmount2).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount2", 9));
		wait(6);
		driver.findElement(oipa_policy_activityBillGroupTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillGroupTypeDD", 9));
		wait(8);
		driver.findElement(oipa_policy_activityBillEntityTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillEntityTypeDD", 9));
		wait(8);
		driver.findElement(oipa_activityOKButton).click();
		wait(9);
		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));

			// Capturing the screenshot for GenerateBillDetail activity - MultipleGenerateBillDetails
			try {
				if (commonTableTextRetreive_bill(driver, 1, 3).equals(xls.getCellData("GroupBill_1", "activitydropdown", 9))) {
					tableExpandRetreive_bill(driver, 1, 2).click();
					wait(5);
					driver.findElement(oipa_GC_activityBillingTab).click();
					wait(6);

					// Capturing the Screenshot
					try {
						takeScreenShot(driver, "Multiple_GenerateBillDetails_Pending" + runDateTime(driver));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (Exception ex) {
				System.out.println("Unable to locate the Activity: " + commonTableTextRetreive_bill(driver, 1, 3) + "and Cannot capture the Screenshot:" + ex);
			}
		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		// After Processing the GenerateBillDetail for creating multiple Bills, Verifying the Data in DB

		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLDETAILGUID FROM Asbilldetail where AMOUNT in"
				+ "(" + xls.getCellData("GroupBill_1", "policy_activityAmount1", 9) + "," + xls.getCellData("GroupBill_1", "policy_activityAmount2", 9)
				+ ") Order By Amount Desc", 7);

		if (billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 9))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))
				&& billDetailList.get(10).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 9))
				&& billDetailList.get(11).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))) {

			System.out.println("Info!---------After Processing 'GenerateBillDetail' activity the multiple bill details from AsBillDetail:" + " " + billDetailList);

			System.out.println("Info!---------Multiple generated Bill Details from AsBillDetail:" + " " + billDetailList.get(0) + " ," + billDetailList.get(2) + ","
					+ billDetailList.get(3) + "," + billDetailList.get(4));
		}
		else {
			System.out.println("Fail!---------Multiple BillDetails are NOT generated");
			Assert.assertTrue(false);
		}

		// Verifying the Dynamic data in 'AsBillDetailField' table from Database

		connectToDb();
		BillDetailFieldList = MultipleRunQuery("SELECT BILLDETAILGUID,FIELDNAME, DATEVALUE, FLOATVALUE  FROM AsbilldetailField where BILLDETAILGUID in" + "('"
				+ billDetailList.get(6) + "','" + billDetailList.get(13) + "')", 4);

		System.out
				.println("Dynamic Fields from AsBillDetailField for " + BillDetailFieldList.get(0) + " are: " + BillDetailFieldList.get(1) + "-" + BillDetailFieldList.get(2));
		System.out
				.println("Dynamic Fields from AsBillDetailField for " + BillDetailFieldList.get(0) + " are: " + BillDetailFieldList.get(5) + "-" + BillDetailFieldList.get(6));
		System.out.println(
				"Dynamic Fields from AsBillDetailField for " + BillDetailFieldList.get(0) + " are: " + BillDetailFieldList.get(9) + "-" + BillDetailFieldList.get(11));
		System.out.println(
				"Dynamic Fields from AsBillDetailField for " + BillDetailFieldList.get(0) + " are: " + BillDetailFieldList.get(13) + "-" + BillDetailFieldList.get(14));
		System.out.println(
				"Dynamic Fields from AsBillDetailField for " + BillDetailFieldList.get(16) + " are: " + BillDetailFieldList.get(17) + "-" + BillDetailFieldList.get(18));
		System.out.println(
				"Dynamic Fields from AsBillDetailField for " + BillDetailFieldList.get(16) + " are: " + BillDetailFieldList.get(21) + "-" + BillDetailFieldList.get(22));
		System.out.println(
				"Dynamic Fields from AsBillDetailField for " + BillDetailFieldList.get(16) + " are: " + BillDetailFieldList.get(25) + "-" + BillDetailFieldList.get(27));
		System.out.println(
				"Dynamic Fields from AsBillDetailField for " + BillDetailFieldList.get(16) + " are: " + BillDetailFieldList.get(29) + "-" + BillDetailFieldList.get(30));

		if (BillDetailFieldList.get(2).contains(xls.getCellData("GroupBill_1", "activityEffectiveDate", 9))
				&& BillDetailFieldList.get(6).contains(xls.getCellData("GroupBill_1", "activityEffectiveDate", 9))
				&& BillDetailFieldList.get(14).contains(xls.getCellData("GroupBill_1", "activityEffectiveDate", 9))
				&& BillDetailFieldList.get(11).equals(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 9))
				&& BillDetailFieldList.get(18).contains(xls.getCellData("GroupBill_1", "activityEffectiveDate", 9))
				&& BillDetailFieldList.get(22).contains(xls.getCellData("GroupBill_1", "activityEffectiveDate", 9))
				&& BillDetailFieldList.get(30).contains(xls.getCellData("GroupBill_1", "activityEffectiveDate", 9))
				&& BillDetailFieldList.get(27).equals(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 9))) {
			System.out.println("Success!---------Dynamic Fields of 1st bill " + BillDetailFieldList.get(0) + " from AsbillDetailField table are as expected");

			System.out.println("Success!---------Dynamic Fields of 2nd bill " + BillDetailFieldList.get(16) + " from AsbillDetailField table are as expected");
		}
		else {
			System.out.println("Fail!---------Dynamic Fields of multiple bills from AsbillDetailField table are NOT as expected");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill08_MultipleGenerateBillDetails_TestEnds------------------");

	}

	// TC 41117 Verify the DB values on recycling the activity
	@Test
	public void GroupBill09_Recycling_MultipleGenerateBillDetail() throws Throwable {

		System.out.println("/**************************************************************************************************************/");
		System.out.println("Info! ---------GroupBill09_Recycling_MultipleGenerateBillDetail_TestBegins------------------");

		System.out.println("Info! --------- Recycling Multiple Bill details using GenerateBillDetail Array------------------");

		// 'GenerateBillDetail' Processed from previous test for creating multiple Bills, Verifying the Bill Detail status in DB
		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLDETAILGUID FROM Asbilldetail where AMOUNT in"
				+ "(" + xls.getCellData("GroupBill_1", "policy_activityAmount1", 9) + "," + xls.getCellData("GroupBill_1", "policy_activityAmount2", 9)
				+ ") Order By Amount Desc", 7);

		if (billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 9))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))
				&& billDetailList.get(10).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 9))
				&& billDetailList.get(11).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))) {

			System.out.println("Info!---------Before recycling 'GenerateBillDetail' activity the multiple bill details from AsBillDetail:" + " " + billDetailList);

			System.out.println(
					"Info!---------Bill Details from AsBillDetail for 1st Bill with:" + " Amount='" + billDetailList.get(3) + "' is of status: " + billDetailList.get(4));

			System.out.println(
					"Info!---------Bill Details from AsBillDetail for 2nd Bill with:" + " Amount='" + billDetailList.get(10) + "' is of status: " + billDetailList.get(11));
		}
		else {

			System.out.println("Fail!---------Multiple BillDetails are NOT generated");
			Assert.assertTrue(false);
		}

		// Recycling GenerateBillDetail (Multiple Bill Details) with new input data

		tableButtonRetreive_bill(driver, 1, 8, "Recycle").click();
		wait(8);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 10));
		wait(8);
		driver.findElement(oipa_policy_activityBillDueDate).clear();
		wait(4);
		driver.findElement(oipa_policy_activityBillDueDate).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 10));
		wait(5);
		driver.findElement(oipa_policy_activityBillDueAmount).clear();
		wait(4);
		driver.findElement(oipa_policy_activityBillDueAmount).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 10));
		wait(6);
		driver.findElement(oipa_policy_activityAmount1).clear();
		wait(4);
		driver.findElement(oipa_policy_activityAmount1).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount1", 10));
		wait(6);
		driver.findElement(oipa_policy_activityAmount2).clear();
		wait(4);
		driver.findElement(oipa_policy_activityAmount2).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount2", 10));
		wait(6);
		driver.findElement(oipa_policy_activityBillGroupTypeDD).click();
		wait(5);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillGroupTypeDD", 10));
		wait(8);
		driver.findElement(oipa_policy_activityBillEntityTypeDD).click();
		wait(5);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillEntityTypeDD", 4));
		wait(8);
		driver.findElement(oipa_policy_activityBillEntityTypeDD).click();
		wait(5);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillEntityTypeDD", 10));
		wait(8);
		driver.findElement(oipa_activityOKButton).click();
		wait(9);
		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "of multiple bill details is recycled successfully and its status is:"
					+ "  " + commonTableTextRetreive_bill(driver, 1, 6));
		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' "
					+ " of multiple bill details is NOT recycled successfully and its status is:" + "  " + commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		// After recycling the 'GenerateBillDetail' of Multiple Bill details, Verifying the OLD Bill Detail status in DB

		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLDETAILGUID FROM Asbilldetail where AMOUNT in"
				+ "(" + xls.getCellData("GroupBill_1", "policy_activityAmount1", 9) + "," + xls.getCellData("GroupBill_1", "policy_activityAmount2", 9)
				+ ") Order By Amount Desc", 7);

		if (billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 9))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 6))
				&& billDetailList.get(10).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 9))
				&& billDetailList.get(11).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 6))) {

			System.out.println("Info!---------After recycling 'GenerateBillDetail' activity the multiple bill details from AsBillDetail:" + " " + billDetailList);

			System.out.println("Success!---------Bill Details from AsBillDetail for 1st OLD Bill detail with:" + " Amount='" + billDetailList.get(3) + "' is of status: "
					+ billDetailList.get(4));

			System.out.println("Success!---------Bill Details from AsBillDetail for 2nd OLD Bill detail with:" + " Amount='" + billDetailList.get(10) + "' is of status: "
					+ billDetailList.get(11));
		}
		else {
			System.out.println("Fail!---------Multiple BillDetails are NOT Shadowed");
			Assert.assertTrue(false);
		}

		// After recycling the 'GenerateBillDetail' of Multiple Bill details, Verifying the NEW Bill Detail status in DB

		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLDETAILGUID FROM Asbilldetail where AMOUNT in"
				+ "(" + xls.getCellData("GroupBill_1", "policy_activityAmount1", 10) + "," + xls.getCellData("GroupBill_1", "policy_activityAmount2", 10)
				+ ") Order By Amount Desc", 7);

		if (billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 10))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))
				&& billDetailList.get(10).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 10))
				&& billDetailList.get(11).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))) {

			System.out.println("Info!---------After recycling 'GenerateBillDetail' activity, NEW multiple bill details from AsBillDetail:" + " " + billDetailList);

			System.out.println("Success!---------Bill Details from AsBillDetail for 1st NEW Bill detail with:" + " Amount='" + billDetailList.get(3) + "' is of status: "
					+ billDetailList.get(4));

			System.out.println("Success!---------Bill Details from AsBillDetail for 2nd NEW Bill detail with:" + " Amount='" + billDetailList.get(10) + "' is of status: "
					+ billDetailList.get(11));
		}
		else {
			System.out.println("Fail!---------Multiple BillDetails are NOT Pending");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill09_Recycling_MultipleGenerateBillDetail_TestEnds------------------");

	}

	// TC 43282 Verify the DB values on reversing the activity
	@Test
	public void GroupBill10_Reversing_MultipleGenerateBillDetail() throws Throwable {

		System.out.println("/**************************************************************************************************************/");
		System.out.println("Info! ---------GroupBill10_Reversing_MultipleGenerateBillDetail_TestBegins------------------");

		System.out.println("Info! --------- Reversing Multiple Bill details using GenerateBillDetail Array------------------");

		// 'GenerateBillDetail' Processed from previous test for creating multiple Bills, Verifying the Bill Detail status in DB
		// Pending multiple Bill details from AsBillDetail table in DB, before reversing the GenerateBillDetail activity
		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLDETAILGUID FROM Asbilldetail where AMOUNT in"
				+ "(" + xls.getCellData("GroupBill_1", "policy_activityAmount1", 10) + "," + xls.getCellData("GroupBill_1", "policy_activityAmount2", 10)
				+ ") Order By Amount Desc", 7);

		if (billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 10))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))
				&& billDetailList.get(10).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 10))
				&& billDetailList.get(11).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))) {

			System.out.println("Info!---------Before reversing 'GenerateBillDetail' activity, multiple bill details from AsBillDetail:" + " " + billDetailList);

			System.out.println("Info!---------Bill Details from AsBillDetail before reversing the activity for 1st Bill detail with:" + " Amount='" + billDetailList.get(3)
					+ "' is of status: " + billDetailList.get(4));

			System.out.println("Info!---------Bill Details from AsBillDetail before reversing the activity for 2nd Bill detail with:" + " Amount='" + billDetailList.get(10)
					+ "' is of status: " + billDetailList.get(11));
		}
		else {
			System.out.println("Fail!---------Multiple BillDetails are NOT Pending");
			Assert.assertTrue(false);
		}

		// Clicking on Reverse icon of 'GenerateBillDetail' activity
		tableButtonRetreive_bill(driver, 1, 8, "Reverse").click();
		wait(8);

		// To trace the 'Pending [Reversal]' status of 'GenerateBillDetail' activity in the activity table and Process the same

		List<WebElement> beforeRows = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		int beforeRowCount = beforeRows.size();
		for (int rowno = 1; rowno <= beforeRowCount; rowno++) {
			if (commonTableTextRetreive_bill(driver, rowno, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 4))) {
				tableButtonRetreive_bill(driver, rowno, 8, "Process").click();
				wait(8);
				List<WebElement> afterRows = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
				int afterRowCount = afterRows.size();

				if (afterRowCount < beforeRowCount) {
					System.out.println("Info!---------Activity is reversed successfully");
				}
				else {
					System.out.println("Fail!---------Activity is NOT reversed successfully");
					Assert.assertTrue(false);
				}
				break;
			}

		}

		// After reversing the GenerateBillDetail activity, the status of multiple Bill details from AsBillDetail table in DB

		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLENTITYTYPE, BILLGROUPTYPE, RECEIVABLEDUETYPE, AMOUNT, STATUS, DUEDATE, BILLDETAILGUID FROM Asbilldetail where AMOUNT in"
				+ "(" + xls.getCellData("GroupBill_1", "policy_activityAmount1", 10) + "," + xls.getCellData("GroupBill_1", "policy_activityAmount2", 10)
				+ ") Order By Amount Desc", 7);

		if (billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 10))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 6))
				&& billDetailList.get(10).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 10))
				&& billDetailList.get(11).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 6))) {

			System.out.println("Info!---------After reversing 'GenerateBillDetail' activity, multiple bill details from AsBillDetail:" + " " + billDetailList);

			System.out.println("Success!---------Bill Details from AsBillDetail after reversing the activity for 1st Bill detail with:" + " Amount='" + billDetailList.get(3)
					+ "' is of status: " + billDetailList.get(4));

			System.out.println("Success!---------Bill Details from AsBillDetail after reversing the activity for 2nd Bill detail with:" + " Amount='" + billDetailList.get(10)
					+ "' is of status: " + billDetailList.get(11));
		}
		else {
			System.out.println("Fail!---------Multiple BillDetails are NOT Shadowed");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill10_Reversing_MultipleGenerateBillDetail_TestEnds------------------");

	}

	/******************************************** MaintainBillDetail_MultipleBillDetails/Shadow/Reconcile **********************************************/

	/**
	 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group Bill » Bug - 26121826/26032445 - Group Billing (Create / Maintain Bill Detail) » Enh 26032445 - MAINTAINBILLDETAIL
	 * OPERATION ON MULTIPLE ASBILLDETAIL RECORDS FRM THE SAME ACTVY
	 **/

	// TC 41106 Modify/Maintain BillDetail for a bill with multiple billdetails
	// TC 41137 Modify/Maintain date for multiple billdetails
	// TC 41138 Modify/Maintain payment type for multiple billdetails
	@Test
	public void GroupBill11_MaintainBillDetail_MultipleBillDetails() throws Throwable {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GroupBill11_MaintainBillDetail_MultipleBillDetails_TestBegins------------------");

		System.out.println("Info! --------- Verifying MaintainBillDetail updates the values of Multiple Bill details generated by GenerateBillDetail Array------------------");
		// Updating the 'MaintainBillDetail' transaction to update Multiple bill details from running Update query in DB
		connectToDb();
		try {
			// Executing update query
			System.out.println("Creating update statement...");
			stmt = con.createStatement();
			String sql = xls.getCellData("GroupBill_Configuration", "New XMLData", 5);
			stmt.executeUpdate(sql);
			sql = xls.getCellData("GroupBill_Configuration", "New XMLData", 6);
			stmt.executeUpdate(sql);
			System.out.println("Success!------Updated MaintainBillDetail transaction for Updating Multiple BillDetails");
		} catch (SQLException excep) {
			System.out.println("Fail!------Unable to update MaintainBillDetail transaction for Updating Multiple BillDetails");
			excep.printStackTrace();
		}

		// Adding and processing 'GenerateBillDetail' activity for generating Multiple Bill Details

		driver.findElement(oipa_policy_activityRefreshBtn).click();
		wait(10);
		driver.findElement(oipa_policy_segments).click();
		wait(10);
		driver.findElement(oipa_policy_activities).click();
		wait(10);

		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "activitydropdown", 12));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 12));
		wait(8);
		driver.findElement(oipa_policy_activityBillDueDate).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 12));
		wait(5);
		driver.findElement(oipa_policy_activityBillDueAmount).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 12));
		wait(6);
		driver.findElement(oipa_policy_activityAmount1).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount1", 12));
		wait(6);
		driver.findElement(oipa_policy_activityAmount2).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount2", 12));
		wait(6);
		driver.findElement(oipa_policy_activityBillGroupTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillGroupTypeDD", 12));
		wait(8);
		driver.findElement(oipa_policy_activityBillEntityTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillEntityTypeDD", 12));
		wait(8);
		driver.findElement(oipa_activityOKButton).click();
		wait(9);
		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));

			// Capturing the screenshot for GenerateBillDetail activity - MultipleGenerateBillDetails
			try {
				if (commonTableTextRetreive_bill(driver, 1, 3).equals(xls.getCellData("GroupBill_1", "activitydropdown", 12))) {
					tableExpandRetreive_bill(driver, 1, 2).click();
					wait(5);
					driver.findElement(oipa_GC_activityBillingTab).click();
					wait(6);

					// Capturing the Screenshot
					try {
						takeScreenShot(driver, "MultipleBillDetails_BeforeUpdation" + runDateTime(driver));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (Exception ex) {
				System.out.println("Unable to locate the Activity: " + commonTableTextRetreive_bill(driver, 1, 3) + "and Cannot capture the Screenshot:" + ex);
			}

		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		// After Processing the GenerateBillDetail for creating multiple Bills, Verifying the Data in DB

		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, DUEDATE, RECEIVABLEDUETYPE FROM Asbilldetail where AMOUNT in" + "("
				+ xls.getCellData("GroupBill_1", "policy_activityAmount1", 12) + "," + xls.getCellData("GroupBill_1", "policy_activityAmount2", 12) + ") Order By Amount Desc",
				4);

		ArrayList<String> billDetailList_before = new ArrayList<String>();
		billDetailList_before = billDetailList;
		String BillDetailguid1 = billDetailList.get(0);
		String BillDetailguid2 = billDetailList.get(4);

		if (billDetailList.get(1).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 12))
				&& billDetailList.get(2).contains(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 12))
				&& billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 2))
				&& billDetailList.get(5).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 12))
				&& billDetailList.get(6).contains(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 12))
				&& billDetailList.get(7).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 2))) {

			System.out.println("Success!---------After Processing 'GenerateBillDetail' activity, the bill details of 1st Bill detail are:" + " " + billDetailList.get(0) + " ,"
					+ billDetailList.get(1) + "," + billDetailList.get(2) + "," + billDetailList.get(3));

			System.out.println("Success!---------After Processing 'GenerateBillDetail' activity, the bill details of 2nd Bill detail are:" + " " + billDetailList.get(4) + " ,"
					+ billDetailList.get(5) + "," + billDetailList.get(6) + "," + billDetailList.get(7));
		}
		else {
			System.out.println("Fail!---------Multiple BillDetails are NOT generated");
			Assert.assertTrue(false);
		}

		// Adding and processing 'MaintainBillDetail' activity

		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "activitydropdown", 13));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 13));
		wait(8);
		driver.findElement(oipa_policy_activityBillDetail1DD).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityAmount1", 12));
		wait(6);
		driver.findElement(oipa_policy_activityNewAmount1).clear();
		wait(4);
		driver.findElement(oipa_policy_activityNewAmount1).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount1", 13));
		wait(5);
		driver.findElement(oipa_policy_activityBillDetail2DD).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityAmount2", 12));
		wait(6);
		driver.findElement(oipa_policy_activityNewAmount2).clear();
		wait(4);
		driver.findElement(oipa_policy_activityNewAmount2).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount2", 13));
		wait(5);
		driver.findElement(oipa_activityOKButton).click();
		wait(12);
		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));

			// Capturing the screenshot for GenerateBillDetail activity after processing MaintainBillDetail
			try {
				if (commonTableTextRetreive_bill(driver, 2, 3).equals(xls.getCellData("GroupBill_1", "activitydropdown", 12))) {
					tableExpandRetreive_bill(driver, 2, 2).click();
					wait(5);
					driver.findElement(oipa_GC_activityBillingTab).click();
					wait(6);

					// Capturing the Screenshot
					try {
						takeScreenShot(driver, "MultipleBillDetailsUpdated_AfterProcessingMaintainBillDetail" + runDateTime(driver));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (Exception ex) {
				System.out.println("Unable to locate the Activity: " + commonTableTextRetreive_bill(driver, 2, 3) + "and Cannot capture the Screenshot:" + ex);
			}

		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		// After Processing the MaintainBillDetail activity, the fields Amount, DueDate and ReceivableDueType are updated and Verifying these columns data
		// in DB with the BillDetailGUID which is generated on processing GenerateBillDetail activity

		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, DUEDATE, RECEIVABLEDUETYPE FROM Asbilldetail where BILLDETAILGUID in" + "('" + BillDetailguid1
				+ "','" + BillDetailguid2 + "') Order By Amount Desc", 4);

		if (billDetailList.get(1).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 13))
				&& billDetailList.get(2).contains(xls.getCellData("GroupBill_1", "activityEffectiveDate", 13))
				&& billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 3))
				&& billDetailList.get(5).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 13))
				&& billDetailList.get(6).contains(xls.getCellData("GroupBill_1", "activityEffectiveDate", 13))
				&& billDetailList.get(7).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 3))) {

			System.out.println("Success!---------After Processing 'MaintainBillDetail' activity, the bill details of 1st Bill detail are updated as:" + " "
					+ billDetailList.get(0) + " ," + billDetailList.get(1) + "," + billDetailList.get(2) + "," + billDetailList.get(3));

			System.out.println("Success!---------After Processing 'MaintainBillDetail' activity, the bill details of 2nd Bill detail are updated as:" + " "
					+ billDetailList.get(4) + " ," + billDetailList.get(5) + "," + billDetailList.get(6) + "," + billDetailList.get(7));
		}
		else {
			System.out.println("Fail!---------BillDetail Amounts, DueDate and ReceivableDueType columns are NOT updated correctly");
			Assert.assertTrue(false);
		}

		// Clicking on Reverse icon of 'MaintainBillDetail' activity
		tableButtonRetreive_bill(driver, 1, 8, "Reverse").click();
		wait(8);

		// To trace the 'Pending [Reversal]' status of 'MaintainBillDetail' activity in the activity table and Process the same

		List<WebElement> beforeRows = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		int beforeRowCount = beforeRows.size();
		for (int rowno = 1; rowno <= beforeRowCount; rowno++) {
			if (commonTableTextRetreive_bill(driver, rowno, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 4))) {
				tableButtonRetreive_bill(driver, rowno, 8, "Process").click();
				wait(8);
				List<WebElement> afterRows = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
				int afterRowCount = afterRows.size();

				if (afterRowCount < beforeRowCount) {
					System.out.println("Activity is reversed successfully");

					// Capturing the screenshot for GenerateBillDetail activity after reversing MaintainBillDetail
					try {
						if (commonTableTextRetreive_bill(driver, 1, 3).equals(xls.getCellData("GroupBill_1", "activitydropdown", 12))) {
							tableExpandRetreive_bill(driver, 1, 2).click();
							wait(5);
							driver.findElement(oipa_GC_activityBillingTab).click();
							wait(6);

							// Capturing the Screenshot
							try {
								takeScreenShot(driver, "MultipleBillDetails_RevertingToOriginalValues_AfterReversingMaintainBillDetail" + runDateTime(driver));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

					} catch (Exception ex) {
						System.out.println("Unable to locate the Activity: " + commonTableTextRetreive_bill(driver, 1, 3) + "and Cannot capture the Screenshot:" + ex);
					}

				}
				else {
					System.out.println("Activity is NOT reversed successfully");
					Assert.assertTrue(false);
				}
				break;
			}
		}

		// After Reversing the MaintainBillDetail, Verifying the fields Amount, DueDate and ReceivableDueType are reverted to their original values
		// in DB with the BillDetailGUID which is generated on processing GenerateBillDetail activity

		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, DUEDATE, RECEIVABLEDUETYPE FROM Asbilldetail where BILLDETAILGUID in" + "('" + BillDetailguid1
				+ "','" + BillDetailguid2 + "') Order By Amount Desc", 4);

		ArrayList<String> billDetailList_after = new ArrayList<String>();
		billDetailList_after = billDetailList;

		// Comparing Bill details before Processing and after reversal of 'MaintainBillDetail' activity [Values should be same]
		if (billDetailList_after.containsAll(billDetailList_before)) {
			System.out.println("Info!---------BillDetail Amounts, DueDate and ReceivableDueType columns are reverted to the original values");

			System.out.println("Success!---------After reversing 'MaintainBillDetail' activity, the bill details of 1st Bill detail are updated as:" + " "
					+ billDetailList.get(0) + " ," + billDetailList.get(1) + "," + billDetailList.get(2) + "," + billDetailList.get(3));

			System.out.println("Success!---------After reversing 'MaintainBillDetail' activity, the bill details of 2nd Bill detail are updated as:" + " "
					+ billDetailList.get(4) + " ," + billDetailList.get(5) + "," + billDetailList.get(6) + "," + billDetailList.get(7));
		}
		else {
			System.out.println("Fail!---------BillDetail Amounts, DueDate and ReceivableDueType columns are NOT reverted correctly");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill11_MaintainBillDetail_MultipleBillDetails_TestEnds------------------");

	}

	// TC 41133 Verify <ShadowBillDetailCollection> functionality
	@Test
	public void GroupBill12_MaintainBillDetail_ShadowBillDetailCollectionCheck() throws Throwable {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GroupBill12_MaintainBillDetail_ShadowBillDetailCollectionCheck_TestBegins------------------");

		System.out.println(
				"Info! --------- Verifying MaintainBillDetail Shadows the Multiple Bill details generated by GenerateBillDetail Array where ShadowBillDetailCollection is Checked ------------------");

		// Multiple Bill details already generated as per above test cases MaintainBillDetail_MultipleBillDetails()
		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, DUEDATE, RECEIVABLEDUETYPE, STATUS FROM Asbilldetail where AMOUNT in" + "("
				+ xls.getCellData("GroupBill_1", "policy_activityAmount1", 12) + "," + xls.getCellData("GroupBill_1", "policy_activityAmount2", 12) + ") Order By Amount Desc",
				5);

		String BillDetailguid1 = billDetailList.get(0);
		String BillDetailguid2 = billDetailList.get(5);

		if (billDetailList.get(1).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 12))
				&& billDetailList.get(2).contains(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 12))
				&& billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 2))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))
				&& billDetailList.get(6).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 12))
				&& billDetailList.get(7).contains(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 12))
				&& billDetailList.get(8).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 2))
				&& billDetailList.get(9).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))) {

			System.out.println("Info!---------Multiple BillDetails before processing MaintainBillDetail with ShadowBillDetailCollectionCheck");

			System.out.println("Info!---------The bill details of 1st Bill detail are:" + " " + billDetailList.get(0) + " , " + billDetailList.get(1) + " , "
					+ billDetailList.get(2) + " , " + billDetailList.get(3) + " , " + billDetailList.get(4));

			System.out.println("Info!---------The bill details of 2nd Bill detail are:" + " " + billDetailList.get(5) + " , " + billDetailList.get(6) + " , "
					+ billDetailList.get(7) + " , " + billDetailList.get(8) + " , " + billDetailList.get(9));
		}
		else {
			System.out.println("Fail!---------Multiple BillDetails does NOT exist");
			Assert.assertTrue(false);
		}

		// Adding and processing 'MaintainBillDetail' activity with ShadowBillDetail check box being checked

		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "activitydropdown", 14));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 14));
		wait(8);
		driver.findElement(oipa_policy_activityShadowBillDetailChkBox).click();
		wait(13);
		if (driver.findElement(oipa_policy_activityShadowBillDetailChkBox).isSelected()) {
			driver.findElement(oipa_policy_activityBillDetail1DD).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityAmount1", 14));
			wait(6);
			driver.findElement(oipa_policy_activityBillDetail2DD).click();
			wait(4);

			selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityAmount2", 14));
			wait(6);
		}
		else {
			System.out.println("ShadowBillDetail checkbox is not CHECKED");
			Assert.assertTrue(false);
		}

		driver.findElement(oipa_activityOKButton).click();
		wait(12);
		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		// After Processing the MaintainBillDetail with ShadowBillDetail check box being checked,
		// Verifying the status of the BillDetail is 'SHADOWED' in DB with the BillDetailGUID which generated on processing GenerateBillDetail activity

		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, DUEDATE, RECEIVABLEDUETYPE, STATUS FROM Asbilldetail where BILLDETAILGUID in" + "('"
				+ BillDetailguid1 + "','" + BillDetailguid2 + "') Order By Amount Desc", 5);

		if (billDetailList.get(1).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 12))
				&& billDetailList.get(2).contains(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 12))
				&& billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 2))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 6))
				&& billDetailList.get(6).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 12))
				&& billDetailList.get(7).contains(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 12))
				&& billDetailList.get(8).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 2))
				&& billDetailList.get(9).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 6))) {

			System.out.println("Info!---------Multiple BillDetails after processing MaintainBillDetail with ShadowBillDetailCollectionCheck");

			System.out.println("Success!---------After Processing 'MaintainBillDetail' activity, the bill details of 1st Bill detail are:" + " " + billDetailList.get(0)
					+ " , " + billDetailList.get(1) + " , " + billDetailList.get(2) + " , " + billDetailList.get(3) + " , " + billDetailList.get(4));

			System.out.println("Success!---------After Processing 'MaintainBillDetail' activity, the bill details of 2nd Bill detail are:" + " " + billDetailList.get(5)
					+ " , " + billDetailList.get(6) + " , " + billDetailList.get(7) + " , " + billDetailList.get(8) + " , " + billDetailList.get(9));
		}

		else {
			System.out.println("Fail!---------Bill Detail status are NOT Shadowed");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill12_MaintainBillDetail_ShadowBillDetailCollectionCheck_TestEnds------------------");

	}

	// TC 41134 Verify <ReconcileBillDetailCollection> functionality
	@Test
	public void GroupBill13_MaintainBillDetail_ReconcileBillDetailCheck() throws Throwable {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GroupBill13_MaintainBillDetail_ReconcileBillDetailCheck_TestBegins------------------");
		System.out.println(
				"Info! --------- Verifying MaintainBillDetail Reconciles the  Multiple Bill details generated by GenerateBillDetail Array where ReconcileBillDetailCollection is Checked ------------------");
		// Adding and processing 'GenerateBillDetail' activity for generating Multiple Bill Details

		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "activitydropdown", 15));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 15));
		wait(8);
		driver.findElement(oipa_policy_activityBillDueDate).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 15));
		wait(5);
		driver.findElement(oipa_policy_activityBillDueAmount).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 15));
		wait(6);
		driver.findElement(oipa_policy_activityAmount1).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount1", 15));
		wait(6);
		driver.findElement(oipa_policy_activityAmount2).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount2", 15));
		wait(6);
		driver.findElement(oipa_policy_activityBillGroupTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillGroupTypeDD", 15));
		wait(8);
		driver.findElement(oipa_policy_activityBillEntityTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillEntityTypeDD", 15));
		wait(8);
		driver.findElement(oipa_activityOKButton).click();
		wait(9);
		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));

			// Capturing the screenshot for GenerateBillDetail activity after processing GenerateBillDetail
			try {
				if (commonTableTextRetreive_bill(driver, 1, 3).equals(xls.getCellData("GroupBill_1", "activitydropdown", 15))) {
					tableExpandRetreive_bill(driver, 1, 2).click();
					wait(5);
					driver.findElement(oipa_GC_activityBillingTab).click();
					wait(6);

					// Capturing the Screenshot
					try {
						takeScreenShot(driver, "MultipleBillDetails_Pending" + runDateTime(driver));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (Exception ex) {
				System.out.println("Unable to locate the Activity: " + commonTableTextRetreive_bill(driver, 1, 3) + "and Cannot capture the Screenshot:" + ex);
			}

		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		// After Processing the GenerateBillDetail for creating multiple Bills, Verifying the Data in DB

		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, DUEDATE, RECEIVABLEDUETYPE, STATUS FROM Asbilldetail where AMOUNT in" + "("
				+ xls.getCellData("GroupBill_1", "policy_activityAmount1", 15) + "," + xls.getCellData("GroupBill_1", "policy_activityAmount2", 15) + ") Order By Amount Desc",
				5);

		String BillDetailguid1 = billDetailList.get(0);
		String BillDetailguid2 = billDetailList.get(5);

		if (billDetailList.get(1).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 15))
				&& billDetailList.get(2).contains(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 15))
				&& billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 2))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))
				&& billDetailList.get(6).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 15))
				&& billDetailList.get(7).contains(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 15))
				&& billDetailList.get(8).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 2))
				&& billDetailList.get(9).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 3))) {
			
			System.out.println("Info!---------Multiple BillDetails before processing MaintainBillDetail with ReconcileBillDetailCheck");
			
			System.out.println("Before!---------The bill details of 1st Bill detail are:" + " " + billDetailList.get(0) + " , " + billDetailList.get(1) + " , " + billDetailList.get(2) + " , "
					+ billDetailList.get(3) + " , " + billDetailList.get(4));

			System.out.println("Before!---------The bill details of 2nd Bill detail are:" + " " + billDetailList.get(5) + " , " + billDetailList.get(6) + " , " + billDetailList.get(7) + " , "
					+ billDetailList.get(8) + " , " + billDetailList.get(9));
		}
		else {
			System.out.println("Fail!--------Multiple BillDetails are NOT generated");
			Assert.assertTrue(false);
		}

		// Adding and processing 'MaintainBillDetail' activity with ReconcileBillDetail check box being checked

		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "activitydropdown", 16));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 16));
		wait(8);
		driver.findElement(oipa_policy_activityReconcileBillDetailChkBox).click();
		wait(13);
		if (driver.findElement(oipa_policy_activityReconcileBillDetailChkBox).isSelected()) {
			driver.findElement(oipa_policy_activityBillDetail1DD).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityAmount1", 16));
			wait(6);
			driver.findElement(oipa_policy_activityBillDetail2DD).click();
			wait(4);

			selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityAmount2", 16));
			wait(6);
		}
		else {
			System.out.println("ReconcileBillDetail checkbox is not CHECKED");
			Assert.assertTrue(false);
		}

		driver.findElement(oipa_activityOKButton).click();
		wait(12);
		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive_bill(driver, 1, 6).equals(xls.getCellData("GroupBill_1", "activityStatus", 3))) {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));

			// Capturing the screenshot for GenerateBillDetail activity after processing MaintainBillDetail
			try {
				if (commonTableTextRetreive_bill(driver, 2, 3).equals(xls.getCellData("GroupBill_1", "activitydropdown", 15))) {
					tableExpandRetreive_bill(driver, 2, 2).click();
					wait(5);
					driver.findElement(oipa_GC_activityBillingTab).click();
					wait(6);

					// Capturing the Screenshot
					try {
						takeScreenShot(driver, "MultipleBillDetails_RECONCILED " + runDateTime(driver));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (Exception ex) {
				System.out.println("Unable to locate the Activity: " + commonTableTextRetreive_bill(driver, 2, 3) + "and Cannot capture the Screenshot:" + ex);
			}
		}
		else {
			System.out.println("Activity" + " '" + commonTableTextRetreive_bill(driver, 1, 3) + "' " + "is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive_bill(driver, 1, 6));
			Assert.assertTrue(false);
		}

		// After Processing the MaintainBillDetail with ReconcileBillDetail check box being checked,
		// Verifying the status of the BillDetail is 'RECONCILED' in DB with the BillDetailGUID which generated on processing GenerateBillDetail activity

		connectToDb();
		billDetailList = MultipleRunQuery("SELECT BILLDETAILGUID, AMOUNT, DUEDATE, RECEIVABLEDUETYPE, STATUS FROM Asbilldetail where BILLDETAILGUID in" + "('"
				+ BillDetailguid1 + "','" + BillDetailguid2 + "') Order By Amount Desc", 5);

		if (billDetailList.get(1).equals(xls.getCellData("GroupBill_1", "policy_activityAmount1", 15))
				&& billDetailList.get(2).contains(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 15))
				&& billDetailList.get(3).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 2))
				&& billDetailList.get(4).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 5))
				&& billDetailList.get(6).equals(xls.getCellData("GroupBill_1", "policy_activityAmount2", 15))
				&& billDetailList.get(7).contains(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 15))
				&& billDetailList.get(8).equals(xls.getCellData("GroupBill_1", "ReceivableDueType", 2))
				&& billDetailList.get(9).equals(xls.getCellData("GroupBill_1", "BillDetailStatus", 5))) {
			
			System.out.println("Info!---------Multiple BillDetails After processing MaintainBillDetail with ReconcileBillDetailCheck");
			
			System.out.println("Success!--------After Processing 'MaintainBillDetail' activity, the bill details of 1st Bill detail are:" + " " + billDetailList.get(0) + " , "
					+ billDetailList.get(1) + " , " + billDetailList.get(2) + " , " + billDetailList.get(3) + " , " + billDetailList.get(4));

			System.out.println("Success!--------After Processing 'MaintainBillDetail' activity, the bill details of 2nd Bill detail are:" + " " + billDetailList.get(5) + " , "
					+ billDetailList.get(6) + " , " + billDetailList.get(7) + " , " + billDetailList.get(8) + " , " + billDetailList.get(9));
		}
		else {
			System.out.println("Fail!--------Bill Detail status is NOT Reconciled");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GroupBill13_MaintainBillDetail_ReconcileBillDetailCheck_TestEnds------------------");

	}

	/************************************************** GenerateBillDetail_StackTraceCheck **********************************************/

	/**
	 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group Bill » Bug - 26121826/26032445 - Group Billing (Create / Maintain Bill Detail) » Enh 26121826 - GENERATION OF MULTIPLE
	 * ASBILLDETAIL RECORDS FROM A SINGLE ACTIVITY
	 **/

	// TC 43283 Validate the error when mandatory fields values are not passed.
	@Test
	public void GroupBill14_ErrorCheck_GenerateBillDetail_Multiple() throws Throwable {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GroupBill14_ErrorCheck_GenerateBillDetail_TestBegins------------------");

		System.out.println("Info! --------- Verifying the stack trace error when Mandatory fields are not passed to GenerateBillDetail Array ------------------");

		// Updating the GenerateBillDetail transaction to display error on processing from running Update query in DB
		connectToDb();
		try {
			// Executing update query
			System.out.println("Creating update statement...");
			stmt = con.createStatement();
			String sql = xls.getCellData("GroupBill_Configuration", "Error Scenario XMLData", 3);
			stmt.executeUpdate(sql);
			System.out.println("Success!------Updated GenerateBillDetail transaction for receiving stack trace");
		} catch (SQLException excep) {
			System.out.println("Fail!------Unable to update GenerateBillDetail transaction for receiving stack trace");
			excep.printStackTrace();
		}

		// Adding 'GenerateBillDetail' activity and checking the stack trace error after processing
		driver.findElement(oipa_policy_activityRefreshBtn).click();
		wait(10);
		driver.findElement(oipa_policy_segments).click();
		wait(10);
		driver.findElement(oipa_policy_activities).click();
		wait(10);
		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "activitydropdown", 17));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate).sendKeys(xls.getCellData("GroupBill_1", "activityEffectiveDate", 17));
		wait(8);
		driver.findElement(oipa_policy_activityBillDueDate).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueDate", 17));
		wait(5);
		driver.findElement(oipa_policy_activityBillDueAmount).sendKeys(xls.getCellData("GroupBill_1", "policy_activityBillDueAmount", 17));
		wait(6);
		driver.findElement(oipa_policy_activityAmount1).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount1", 17));
		wait(6);
		driver.findElement(oipa_policy_activityAmount2).sendKeys(xls.getCellData("GroupBill_1", "policy_activityAmount2", 17));
		wait(6);
		driver.findElement(oipa_policy_activityBillGroupTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillGroupTypeDD", 17));
		wait(8);
		driver.findElement(oipa_policy_activityBillEntityTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupBill_1", "policy_activityBillEntityTypeDD", 17));
		wait(8);
		driver.findElement(oipa_activityOKButton).click();
		wait(9);

		tableButtonRetreive_bill(driver, 1, 8, "Process").click();
		wait(8);

		driver.findElement(oipa_policy_activityShowErrorBtn).click();
		wait(5);

		if (driver.findElement(oipa_policy_activityStackTraceBill).isDisplayed()) {
			System.out.println("Success!--------Error message is displayed");

			// Creating the JavascriptExecutor interface object
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// This will scroll the web page till end.
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "BillDetailArrayerror " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		else{
			
			System.out.println("Fail!--------Error message is NOT displayed");			
			Assert.assertTrue(false);
		}

		// Reverting the configurations of GenerateBillDetail and MaintainBillDetail transactions to the original values - running Update query in DB

		connectToDb();
		try {
			// Executing update query
			System.out.println("Creating update statement...");
			stmt = con.createStatement();
			String sql = xls.getCellData("GroupBill_Configuration", "Old XMLData", 2);
			stmt.executeUpdate(sql);
			sql = xls.getCellData("GroupBill_Configuration", "Old XMLData", 3);
			stmt.executeUpdate(sql);
			sql = xls.getCellData("GroupBill_Configuration", "Old XMLData", 4);
			stmt.executeUpdate(sql);
			sql = xls.getCellData("GroupBill_Configuration", "Old XMLData", 5);
			stmt.executeUpdate(sql);
			sql = xls.getCellData("GroupBill_Configuration", "Old XMLData", 6);
			stmt.executeUpdate(sql);
			System.out.println("Success!------Reverted the transactions to the original");
		} catch (SQLException excep) {
			System.out.println("Fail!------Unable to revert the transactions to the original");
			excep.printStackTrace();
		}

		System.out.println("Info! ---------GroupBill14_ErrorCheck_GenerateBillDetail_TestEnds------------------");
	}

	@AfterClass
	public void aClass() {
		closeBrowser(driver);
	}

}
