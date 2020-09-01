package com.tc.groupcustomer;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Library.OIPA_Library;

public class GC05_GroupCustomer2 extends OIPA_Library {
	// Tester : Sushma
		// Functionality : GC_GoupCustomer-2
		public static WebDriver driver;
		Random rand;
		String agreementName;
		String Firstname;
		String ClientFirstname;
		String CustomerNumber;
		String CustomerNumberCapture;
		String GCEnroll_customerName;
		String GCEnroll_ClientFirstName;
		String GCEnroll_policyname;

		@BeforeClass
		public void prerequisite() throws Throwable {
			
			System.out.println("***************GC05 GroupCustomer2******************************");

			openBrowser();
			driver = login(driver);
			rand = new Random();
			wait(5);
			ClientFirstname = NewClientCreation();
			CustomerNumberCapture = customerCreation();
			customerCreation();
		}

		/*****************************
		 * All Methods Implementaion
		 ************************************************/

		// Method to create runtime for screenshot //
			public String runDateTime(WebDriver driver) {
				Date currentDate = new Date();
				String dateToStr = DateFormat.getInstance().format(currentDate);
				dateToStr = dateToStr.replace("/", "-").replace(" ", "_").replace(":", ".");
				return dateToStr;
			}
			// Method to retrieve data from the table which has span section
		public String commonTableTextRetreive(WebDriver driver, int rowno, int columnno) {
			ResourceBundle.clearCache();
			String first_part = "//table//tbody//tr[";
			String second_part = "]//td[";
			String third_part = "]/span";
			String final_xpath = first_part + rowno + second_part + columnno + third_part;
			String tableData = driver.findElement(By.xpath(final_xpath)).getText();
			return tableData;
		}

		/* Method to retrieve buttons from the table for DataIntake */
		public WebElement tableButtonRetreive_DataIntake(WebDriver driver, int rowno, int columnno, String buttonicon) {
			String first_part = "//table/tbody/tr[";
			String second_part = "]/td[";
			String third_part = "]/div/button[@title='";
			String fourth_part = "']";
			String final_xpath = first_part + rowno + second_part + columnno + third_part + buttonicon + fourth_part;
			WebElement buttonDisplay = driver.findElement(By.xpath(final_xpath));
			return buttonDisplay;
		}

		/* Method to retrieve buttons from the table for Plan */
		public WebElement tableExpandRetreive_Plan(WebDriver driver, int rowno, int columnno) {
			String first_part = "//table/tbody/tr[";
			String second_part = "]/td[";
			String third_part = "]/div";
			String final_xpath = first_part + rowno + second_part + columnno + third_part;
			WebElement buttonDisplay = driver.findElement(By.xpath(final_xpath));
			return buttonDisplay;
		}

		// Method to Create Client
		public String NewClientCreation() throws IOException {
			System.out.println("Info !------------------------Creating Client---------------------------");
			driver.findElement(oipa_createDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateDDInput", 2));
			wait(3);
			driver.findElement(oipa_CreateButton).click();
			wait(3);
			driver.findElement(oipa_client_typeDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_Type", 2));
			wait(3);
			driver.findElement(oipa_client_legalResidenceCountry).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_Legal ResidenceCountry", 2));
			wait(3);
			driver.findElement(oipa_client_prefix).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_Prefix", 2));
			wait(3);
			driver.findElement(oipa_client_FirstName)
					.sendKeys(xls.getCellData("GroupCustomer_2", "CreateClient_FirstName", 2) + rand.nextInt(1000));
			wait(4);
			String Firstname = driver.findElement(oipa_client_FirstName).getAttribute("value");
			wait(3);
			driver.findElement(oipa_client_MiddleName)
					.sendKeys(xls.getCellData("GroupCustomer_2", "CreateClient_MiddleName", 2));
			wait(3);
			driver.findElement(oipa_client_LastName)
					.sendKeys(xls.getCellData("GroupCustomer_2", "CreateClient_LastName", 2));
			wait(3);
			driver.findElement(oipa_client_Gender).click();
			wait(3);
			driver.findElement(oipa_client_MartialDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_MaritalStatus", 2));
			wait(3);
			driver.findElement(oipa_client_DOB).sendKeys(xls.getCellData("GroupCustomer_2", "CreateClient_DOB", 2));
			wait(3);
			driver.findElement(oipa_client_TaxID).sendKeys(String.valueOf(rand.nextInt(1000000000)));
			wait(3);
			driver.findElement(oipa_client_BirthCountry).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_Birthcountry", 2));
			wait(4);
			driver.findElement(oipa_client_citizenShipCountry).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_CitizenshipCountry", 2));
			wait(3);
			driver.findElement(oipa_client_TextField).clear();
			driver.findElement(oipa_client_TextField).sendKeys(String.valueOf(rand.nextInt(100000)));
			wait(3);
			driver.findElement(oipa_client_savebutton).click();
			wait(6);
			try {
				takeScreenShot(driver, "Client Creation" + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Firstname;
		}

		// Method to Create Group Customer
		public String customerCreation() throws IOException {
			System.out.println("!------------------******Creating Group Customer******--------------------!");
			driver.findElement(oipa_createDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "oipa_createDD", 2));
			wait(3);
			driver.findElement(oipa_CreateButton).click();
			wait(5);
			driver.findElement(oipa_GC_customerType).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_customerType", 2));
			wait(4);
			String customerName = xls.getCellData("GroupCustomer_1", "GC_customerName", 2) + rand.nextInt(1000);
			driver.findElement(oipa_GC_customerName).sendKeys(customerName);
			wait(3);
			if (driver.findElement(oipa_GC_customerNumber).isEnabled()) {
				driver.findElement(oipa_GC_customerNumber).sendKeys(customerName);
				driver.findElement(oipa_GC_customerNumber).sendKeys(Keys.TAB);
				wait(5);
			}
			driver.findElement(oipa_GC_TaxID).sendKeys(String.valueOf(rand.nextInt(1000000000)));
			driver.findElement(oipa_GC_customerNumber).sendKeys(Keys.TAB);
			wait(6);
			driver.findElement(oipa_GC_primaryEnrollmentRelationshipDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_primaryEnrollmentRelationshipDD", 2));
			wait(3);
			driver.findElement(oipa_GC_enrollmentClassGroupDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_enrollmentClassGroupDD", 2));
			wait(3);
			driver.findElement(oipa_GC_hierarchyRelationshipDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_hierarchyRelationshipDD", 2));
			wait(3);
			driver.findElement(oipa_GC_originalEffectiveDate).clear();
			wait(3);
			driver.findElement(oipa_GC_originalEffectiveDate)
					.sendKeys(xls.getCellData("GroupCustomer_1", "GC_originalEffectiveDate", 2));
			wait(3);
			driver.findElement(oipa_GC_customerLegalName).sendKeys(customerName);
			wait(3);
			driver.findElement(oipa_GC_customerStatusDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_customerStatusDD", 2));
			wait(3);
			driver.findElement(oipa_GC_savebutton).click();
			wait(3);
			String customerNameEntered = driver.findElement(oipa_GC_customerNameEntered).getText();
			System.out.println("SUCCESS !----------Group Customer is Created: " + customerNameEntered + "");
			wait(6);
			takeScreenShot(driver, "Customer Creation" + rand.nextInt(1000));
			try {
				takeScreenShot(driver, "Customer Creation" + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			CustomerNumber = driver.findElement(oipa_GC_Customer_CustomerNumber).getAttribute("value");
			System.out.println("Customer number is : " + CustomerNumber);
			return CustomerNumber;

		}

		// Method to Add Phone Number
		public void AddingPhoneNumber() throws IOException {
			// Adding a new Phone Number of Type 'Home'
			driver.findElement(oipa_GC_CustomerPhoneLink).click();
			wait(3);
			driver.findElement(oipa_GC_CustomerPhone_AddPhoneButton).click();
			wait(3);
			driver.findElement(oipa_GC_CustomerPhone_PhoneTypeDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Phone_PhoneType", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerPhone_CountryDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Phone_Country", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerPhone_Number)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Phone_Number", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerPhone_SaveButton).click();
			wait(6);

		}
		// Method to Create Agreement ClassGroup

		public String AddClassGroupDetails() {
			driver.findElement(oipa_GC_Agreement_ClassGroups_AddDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_AddDD", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_ClassGroups_AddButton).click();
			wait(4);
			driver.switchTo().activeElement();
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_TypeDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Type", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_StatusDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Status", 2));
			wait(3);
			String ClassGrpName = xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Name", 2)
					+ rand.nextInt(100);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupName).sendKeys(ClassGrpName);
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupDescription)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Description", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_EffectiveFrom)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_EffectiveFrom", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_ExpirationDate).click();
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_ExpirationDate)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_ExpirationDate", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupSubType)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_ClassGroupSubType", 2)
							+ rand.nextInt(100));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupCriteria)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_ClassGroupCriteria", 2)
							+ rand.nextInt(100));
			wait(3);
			// Adding Class
			driver.findElement(oipa_GC_Agreement_AddClassGroups_AddClassLink).click();
			wait(4);
			driver.findElement(oipa_GC_Agreement_AddClass_ClassTypeDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ClassType", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_ClassName)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ClassName", 2) + rand.nextInt(100));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_ClassDescription).sendKeys(
					xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ClassDescription", 2) + rand.nextInt(100));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_UndefinedSectionExpander).click();
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_RelatedClass).sendKeys(
					xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_RelatedClass", 2) + rand.nextInt(100));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_ProductType)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ProductType", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_ProductSubType)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ProductSubType", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_Reporting1)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_Reporting1", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_Reporting2)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_Reporting2", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_Billing1)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_Billing1", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_Billing2)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_Billing2", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_CustomerNumber).sendKeys(
					xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_CustomerNumber", 2) + rand.nextInt(100));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_ExperienceNumber).sendKeys(
					xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ExperienceNumber", 2) + rand.nextInt(100));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_ReportNumber).sendKeys(
					xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ReportNumber", 2) + rand.nextInt(100));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_SubCode)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_SubCode", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_Branch)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_Branch", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_KeyNum)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_KeyNum", 2) + rand.nextInt(100));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClass_RateGroup)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_RateGroup", 2) + rand.nextInt(100));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_SaveButton).click();
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_SaveStatusDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClassGroup_SaveStatus", 2));
			wait(3);
			return ClassGrpName;
		}

		/*
		 * public void customerSearch() { //Search for customer System.out.
		 * println("After Customer creation, I am searching for a Customer");
		 * driver.findElement(oipa_SearchDD).click(); wait(3);
		 * selectItemInDropdown(driver,"Customer"); wait(3);
		 * driver.findElement(oipa_SearchData).sendKeys("GC_Cust_101"); wait(3);
		 * driver.findElement(oipa_SearchButton).click(); wait(4);
		 * System.out.println("Search is successful"); wait(3); }
		 */

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Address » Group Customer Address - JET Upgrade »
		 * TC:02 - Verify the functionality of adding a new address to group
		 * customer.
		 */

		@Test
		public void gc01_AddingNewAddress_TC27055() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("!-----------------------****** Adding Billing Address ******-----------------------!");
			// Adding Billing Address for Group Customer
			driver.findElement(oipa_GC_CustomerAddressLink).click();
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_AddAddressButton).click();
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_AddressTypeDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Addr_AddressType", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_CountryDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Addr-Country", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_AddressLine1)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText1", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_AddressLine2)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText2", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_AddressLine3)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText3", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_AddressLine4)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText4", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_City)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_City", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_StateDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Addr_State", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_Zip).sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_Zip", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_Zip).sendKeys(Keys.TAB);
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_Email)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_Email", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_Fax)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_Fax Number", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_EffectiveDate).clear();
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_EffectiveDate)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_ EffectiveDate", 2));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_Savebutton).click();
			wait(8);
			// Variable to get AddressType of a table after adding Address
			String FinalAddressType = driver
					.findElement(By.xpath("//table//tbody//tr[1]//td[contains(@data-bind,'addressRole')]")).getText();
			// Variable to get to verify FinalAddress from Excel
			String FinalAddress = xls.getCellData("GroupCustomer_2", "GC_AddressText1", 2) + ", "
					+ xls.getCellData("GroupCustomer_2", "GC_AddressText2", 2) + ", "
					+ xls.getCellData("GroupCustomer_2", "GC_AddressText3", 2) + ", "
					+ xls.getCellData("GroupCustomer_2", "GC_AddressText4", 2) + ", "
					+ xls.getCellData("GroupCustomer_2", "GC_Addr_City", 2) + ", "
					+ xls.getCellData("GroupCustomer_2", "GC_Addr_State", 2) + ", "
					+ xls.getCellData("GroupCustomer_2", "GC_Addr_Zip", 2);
			// Variable to get Address of a table after adding Address
			String FinalAddressfromTable = driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]")).getText();
			if (FinalAddressType.equals(xls.getCellData("GroupCustomer_2", "GC_Addr_AddressType", 2))
					&& FinalAddress.equals(FinalAddressfromTable)) {
				System.out.println("SUCCESS ! --------------Billing Address added successfully for Group Customer");
				try {
					takeScreenShot(driver, "Group Customer Billing Address Created" + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("FAIL ! --------------Billing Address is not added successfully for Group Customer");
				Assert.assertTrue(false);

			}
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Address » Group Customer Address - JET Upgrade »
		 * TC:03 - Verify the funcationality of making an addres as default.
		 */

		@Test
		public void gc02_MakeDefaultAddress_TC27056() throws IOException {
			wait(5);
			System.out.println("************************************************************************************");
			System.out.println(
					"!-------------******Adding Mailing address and making an address as default******----------------!");
			// Adding other address i.e. Mailing
			driver.findElement(oipa_GC_CustomerAddress_AddAddressButton).click();
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_AddressTypeDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Addr_AddressType", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_CountryDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Addr-Country", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_AddressLine1)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText1", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_AddressLine2)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText2", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_AddressLine3)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText3", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_AddressLine4)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText4", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_City)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_City", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_StateDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Addr_State", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_Zip).sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_Zip", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_Email)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_Email", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_Fax)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_Fax Number", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_EffectiveDate).clear();
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_EffectiveDate)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_ EffectiveDate", 3));
			wait(3);
			driver.findElement(oipa_GC_CustomerAddress_Savebutton).click();
			wait(4);
			System.out.println("SUCCESS ! --------------Mailing Address added successfully for Group Customer");
			try {
				takeScreenShot(driver, "Default and Delete Icon Address Verified" + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Verify Default icon and delete icon on Address Screen

			List<WebElement> rows = driver.findElements(By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr"));

			for (int i = 1; i <= rows.size(); i++) {
				if (driver.findElement(By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr[" + i + "]//td[3]"))
						.isEnabled()) {
					if (driver.findElement(By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr[" + i
							+ "]//td[7]//span[@class='oi-icon-delete']")).isDisplayed()) {
						System.out.println("Row " + i + " Default Icon is : "
								+ driver.findElement(
										By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr[" + i + "]//td[3]"))
										.isSelected());
						System.out
								.println("Row " + i
										+ " Delete Icon is : " + driver
												.findElement(
														By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr["
																+ i + "]//td[7]//span[@class='oi-icon-delete']"))
												.isDisplayed());

					}

					else {
						System.out.println("Row " + i + " Default Icon is : "
								+ driver.findElement(
										By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr[" + i + "]//td[3]"))
										.isEnabled());
						System.out
								.println("Row " + i
										+ " Delete Icon is : " + driver
												.findElement(
														By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr["
																+ i + "]//td[7]//span[@class='oi-icon-delete']"))
												.isDisplayed());

					}

				} else {
					if (driver.findElement(By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr[" + i
							+ "]//td[7]//span[@class='oi-icon-delete']")).isDisplayed()) {
						System.out.println("Row " + i + " Default Icon is : "
								+ driver.findElement(
										By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr[" + i + "]//td[3]"))
										.isSelected());
						System.out
								.println("Row " + i
										+ " Delete Icon is : " + driver
												.findElement(
														By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr["
																+ i + "]//td[7]//span[@class='oi-icon-delete']"))
												.isDisplayed());

					}

					else {
						System.out.println("Row " + i + " Default Icon is : "
								+ driver.findElement(
										By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr[" + i + "]//td[3]"))
										.isSelected());
						System.out
								.println("Row " + i
										+ " Delete Icon is : " + driver
												.findElement(
														By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr["
																+ i + "]//td[7]//span[@class='oi-icon-delete']"))
												.isDisplayed());

					}
				}
			}
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Address » Group Customer Address - JET Upgrade »
		 * TC:04 - Verify the functionality of deleting an adrress.
		 */
		@Test
		public void gc03_DeleteAddress_TC27059() throws IOException {
			// Delete functionality of a customer address
			System.out.println("************************************************************************************");
			System.out.println("!-------------******Deleting one address******----------------!");
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr"));
			for (int i = 1; i <= rows.size(); i++) {
				boolean DeleteIconPresence = driver
						.findElement(By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr[" + i
								+ "]//td[7]//span[@class='oi-icon-delete']"))
						.isDisplayed();
				if (DeleteIconPresence == true) {
					System.out.println("INFO !--------------Delete Icon is present for Row " + i);
					driver.findElement(By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr[" + i
							+ "]//td[7]//span[@class='oi-icon-delete']")).click();
					wait(3);
					driver.switchTo().activeElement();
					wait(3);
					driver.findElement(oipa_GC_CustomerAddress_DeleteOKButton).click();
					wait(3);
					System.out
							.println("----------------------------------------------------------------------------------");
					System.out.println("SUCCESS ! -----------Address Deleted(Expired) successfully for row " + i);
					String status = driver.findElement(By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr[" + i
							+ "]//td[5]//span[contains(text(),'Expired')]")).getText();
					System.out.println("Status of deleted address is : " + status);
					boolean DeleteIconAfterDeletion = driver
							.findElement(By.xpath("//table[@id=('groupcustomerAddressTable')]//tbody//tr[" + i
									+ "]//td[7]//span[@class='oi-icon-delete']"))
							.isDisplayed();
					if (DeleteIconAfterDeletion == true) {
						System.out.println("INFO ! -----------Delete Icon is present for deleted row");
						Assert.assertTrue(false);
					} else {
						System.out.println("INFO ! -----------Delete Icon is not present for deleted row");
						try {
							takeScreenShot(driver, "Adddress Deleted" + runDateTime(driver));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				} else {
					System.out.println("INFO !-----------Delete Icon is not present for Row " + i);
				}
			}
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Phone Screen » Bug-24436349-IMPLEMENT GROUP
		 * CUSTOMER PHONE SCREEN »TC-06 To Verify Adding of a 'new' Phone Number on
		 * Group Customer Phone Screen
		 */
		@Test
		public void gc04_AddPhone_TC28991() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("!---------------------******Adding Phone Number******------------------------!");
			wait(3);
			AddingPhoneNumber();
			try {
				takeScreenShot(driver, "PhoneNumber Added" + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}		Assert.assertEquals(commonTableTextRetreive(driver, 1, 3),
					xls.getCellData("GroupCustomer_2", "GC_Phone_PhoneType", 2));
			System.out.println("SUCCESS !------ Phone number added Successfully.");
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Phone Screen » Bug-24436349-IMPLEMENT GROUP
		 * CUSTOMER PHONE SCREEN »TC-11 To Verify Editing of an existing Phone
		 * Number
		 */

		@Test
		public void gc05_EditPhone_TC29003() throws IOException {
			// Phone number Edit functionality
			System.out.println("************************************************************************************");
			System.out.println("!---------------------******Editing Phone Number******------------------------!");
			driver.findElement(oipa_GC_CustomerPhoneLink).click();
			wait(3);
			driver.findElement(oipa_GC_PhoneEdit_ExpandIcon).click();
			wait(3);
			driver.findElement(oipa_GC_PhoneEdit_PhoneTypeDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Phone_PhoneType", 3));
			wait(4);
			boolean ExtenionPresense = driver.findElement(oipa_GC_PhoneEdit_ExtensionTxt).isDisplayed();
			boolean ExtenionEnabled = driver.findElement(oipa_GC_PhoneEdit_ExtensionTxt).isEnabled();
			String ExtenionTxtInside = driver.findElement(oipa_GC_PhoneEdit_ExtensionTxt).getAttribute("Value");
			if (ExtenionPresense == true && ExtenionEnabled == true && ExtenionTxtInside == null) {
				System.out.println("INFO !-- Extension Text box is displayed,enabled and Empty");
				try {
					takeScreenShot(driver, "Extension Text Box Displayed" + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}		} else {
				System.out.println("INFO !-- Extension Text box is not displayed,enabled and not empty");
				Assert.assertTrue(false);

			}
			driver.findElement(oipa_GC_PhoneEdit_ExtensionTxt).sendKeys(xls.getCellData("GroupCustomer_2", "Extension", 2));
			wait(3);
			driver.findElement(oipa_GC_PhoneEdit_SaveButton).click();
			wait(6);
			try {
				takeScreenShot(driver, "PhoneNumber Edited" + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Assert.assertEquals(commonTableTextRetreive(driver, 1, 3),
					xls.getCellData("GroupCustomer_2", "GC_Phone_PhoneType", 3));
			System.out.println("SUCCESS !------ Phone number edited successfully");
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Phone Screen » Bug-24436349-IMPLEMENT GROUP
		 * CUSTOMER PHONE SCREEN »TC-12 To Verify Deleting of an existing Phone
		 * Number
		 */

		@Test
		public void gc06_DeletePhone_TC29004() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("!---------------------******Deleting Phone Number******------------------------!");
			AddingPhoneNumber();
			wait(3);
			// Delete functionality for Group Customer phone number
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id=('phoneTable')]//tbody//tr"));
			for (int i = rows.size(); i >= rows.size(); i--) {
				String path = "//table[@id=('phoneTable')]//tbody//tr[";
				boolean IconCheck = driver
						.findElement(By.xpath(path + i + "]//td[7]//button[contains(@data-bind,'processDelete')]"))
						.isDisplayed();
				if (IconCheck == true) {
					driver.findElement(By.xpath(path + i + "]//td[7]//button[contains(@data-bind,'processDelete')]"))
							.click();
					wait(3);
					driver.switchTo().activeElement();
					wait(3);
					driver.findElement(OIPA_GC_Phone_DeleteCancelButton).click();
					wait(3);
					System.out.println("INFO !------Clicked on cancel button, Phone number is not deleted");
					wait(3);
					driver.findElement(By.xpath(path + i + "]//td[7]//button[contains(@data-bind,'processDelete')]"))
							.click();
					wait(3);
					driver.switchTo().activeElement();
					wait(3);
					driver.findElement(OIPA_GC_Phone_DeleteOKButton).click();
					wait(3);
					try {
						takeScreenShot(driver, "PhoneNumber Deleted" + runDateTime(driver));
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("SUCCESS !-------- Clicked on OK button, Phone number is deleted Successfully");
				} else {
					System.out.println("FAIL!-------Delete Button is not present in row" + i);

				}
			}
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Activity » Bug 23597352 - Group Customer
		 * Activities » TC 3:Verify the user is able to add the activity by clicking
		 * on Add Activity link in the Group Customer Screen
		 */
		@Test
		public void gc07_AddActivities_TC27666() throws IOException {
			// Adding an Group Customer Activity
			System.out.println("************************************************************************************");
			System.out.println("-------------------****** Adding Actiivties******---------------------");
			System.out.println("-------------------****** Verifying 'OK' button functionality******---------------------");
			driver.findElement(oipa_GC_AddActivity_ActivitiesLink).click();
			wait(3);
			driver.findElement(oipa_GC_AddActivity_ActivitySelectionDD).click();
			wait(3);
			if (driver.findElement(oipa_GC_AddActivity_AddActivityCancelButton).isDisplayed()) {
				System.out.println("INFO!-- Cancel buttons is present on Add Activity Screen");

			} else {
				System.out.println("INFO!-- Cancel buttons is not present on Add Activity Screen");
				Assert.assertTrue(false);

			}
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Activity_ActivityName", 2));
			wait(3);
			if (driver.findElement(oipa_GC_AddActivity_ActivityOKButton).isDisplayed() == true
					&& driver.findElement(oipa_GC_AddActivity_ActivityCancelButton).isDisplayed() == true
					&& driver.findElement(oipa_GC_AddActivity_ActivityOkNavigateButton).isDisplayed() == true) {
				System.out.println("INFO!-- OK,OK Navigate and cancel buttons are present after selecting Activity");
			} else {
				System.out.println("INFO!-- OK,OK Navigate and cancel buttons are not present after selecting Activity");
				Assert.assertTrue(false);
			}
			driver.findElement(oipa_GC_AddActivity_ActivityOKButton).click();
			wait(5);
			driver.findElement(oipa_GC_Activities_Link).click();
			wait(4);
			if (commonTableTextRetreive(driver, 1, 3)
					.equals(xls.getCellData("GroupCustomer_2", "GC_Activity_ActivityName", 2))
					&& commonTableTextRetreive(driver, 1, 6)
							.equals(xls.getCellData("GroupCustomer_2", "GC_Activity_ActivityStatus", 2))) {
				System.out.println("SUCCESS!-------Activity is added successfully and present in Activities Screen with "
						+ commonTableTextRetreive(driver, 1, 6) + " Status");
				try {
					takeScreenShot(driver, "Activity Added" + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("FAIL!-------Activity is not added successfully and not present in Activities Screen");
				Assert.assertTrue(false);

			}
			if (driver.findElement(oipa_GC_Activities_RowExpandIcon).isDisplayed()) {
				driver.findElement(oipa_GC_Activities_RowExpandIcon).click();
				wait(3);
				System.out.println("INFO !-------Activity Details Section is expandanded");
				driver.findElement(oipa_GC_Activities_RowExpandIcon).click();
				wait(3);
				System.out.println("INFO !-------Activity Details Section is Collapsed");
			}

			System.out.println(
					"-------------------****** Verifying 'Cancel' button functionality******---------------------");
			driver.findElement(oipa_GC_AddActivity_ActivitiesLink).click();
			wait(3);
			driver.findElement(oipa_GC_AddActivity_ActivitySelectionDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Activity_ActivityName", 2));
			wait(3);
			driver.findElement(oipa_GC_AddActivity_ActivityCancelButton).click();
			System.out.println(" INFO !------Clicked on 'Cancel' Button so Activity is not added");
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Activity » Bug 23597352 - Group Customer
		 * Activities » TC 4:Verify the functionality of Process button.
		 */
		@Test
		public void gc08_ActivitiesProcess_TC27669() throws IOException {
			// Processing the added activity
			System.out.println("************************************************************************************");
			System.out.println("-------------------****** Processing Activity******---------------------");
			wait(3);
			driver.findElement(oipa_GC_Activities_Link).click();
			wait(3);
			if (driver.findElement(oipa_GC_Activities_ActivityProcessButton).isDisplayed()) {
				System.out.println(
						"INFO!-------Process Icon is present for " + commonTableTextRetreive(driver, 1, 6) + " Activity");
			} else {
				System.out.println("INFO!-------Process Icon is not present for " + commonTableTextRetreive(driver, 1, 6)
						+ " Activity");
			}
			driver.findElement(oipa_GC_Activities_ActivityProcessButton).click();
			wait(6);
			Assert.assertEquals(commonTableTextRetreive(driver, 1, 6),
					xls.getCellData("GroupCustomer_2", "GC_Activity_ActivityStatus", 3));
			System.out.println("SUCCESS!------------------ Activity processed successfully");
			try {
				takeScreenShot(driver, "Activity Processed" + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			wait(8);
		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Activity » Bug 23597352 - Group Customer
		 * Activities » TC 5:Verify the recycling of activities is happening
		 * correctly.
		 */
		@Test
		public void gc09_RecycleActivities_TC27670() throws IOException {
			// Recycle of an active Activity
			System.out.println("************************************************************************************");
			System.out.println("-------------------****** Recycling Activity******---------------------");
			if (driver.findElement(oipa_GC_Activities_ActivityRecycleButton).isDisplayed() == true) {
				System.out.println(" INFO !---------Recycle Button is present for " + commonTableTextRetreive(driver, 1, 6)
						+ " Activity");
			} else {
				System.out.println(" INFO !---------Recycle Button is not present for "
						+ commonTableTextRetreive(driver, 1, 6) + " Activity");
				Assert.assertTrue(false);

			}
			driver.findElement(oipa_GC_Activities_ActivityRecycleButton).click();
			wait(3);
			driver.findElement(oipa_GC_Activities_ActivityRecycleOKButton).click();
			wait(6);

			List<WebElement> rows = driver.findElements(oipa_GC_Activities_Rows);
			System.out.println(rows.size() + " Activities are present after recycling");
			for (int i = 1; i <= rows.size(); i++) {
				String Status = driver
						.findElement(By
								.xpath("//table[@id=('groupCustomerActivitiesTable')]//tbody//tr[" + i + "]//td[6]//span"))
						.getText();
				System.out.println("Row " + i + " Activity status is : " + Status);
			}
			driver.findElement(oipa_GC_Actvities_PendingReversal_ProcessButton).click();
			wait(6);
			try {
				takeScreenShot(driver, "Activity Recycled" + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<WebElement> rowsAfterRecycle = driver.findElements(oipa_GC_Activities_Rows);
			if (rowsAfterRecycle.size() == 1 && commonTableTextRetreive(driver, 1, 6)
					.equals(xls.getCellData("GroupCustomer_2", "GC_Activity_ActivityStatus", 2))) {
				System.out.println("INFO !------- " + rowsAfterRecycle.size() + " Activity is present with "
						+ commonTableTextRetreive(driver, 1, 6) + " Status");
			} else {
				System.out.println("FAIL! ----Pending[Revresal] Activity is not processed");
				Assert.assertTrue(false);

			}
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Comments » FDD -19483163 - Ability to add
		 * comments at Group Customer Level » Prototype Configuration Testing »
		 * Verify that user is able to add comments at Group Customer level
		 */
		@Test
		public void gc10_AddComments_TC16851() throws IOException {
			// Creating comments for a customer
			System.out.println("************************************************************************************");
			System.out.println("!-------------******Adding Group Customer Comments******-------------------!");
			driver.findElement(oipa_GC_Comments_AddCommentsLink).click();
			wait(3);
			driver.switchTo().activeElement();
			wait(3);
			driver.findElement(oipa_GC_AddComments_NewButton).click();
			wait(3);
			driver.findElement(oipa_GC_AddComments_FunctionalDepartmentDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Comments_FuncDept", 2));
			wait(3);
			driver.findElement(oipa_GC_AddComments_Categroy).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Comments_Category", 2));
			wait(3);
			driver.findElement(oipa_GC_AddComments_ClientCommentsField).clear();
			wait(3);
			driver.findElement(oipa_GC_AddComments_ClientCommentsField)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Comments_CommentsField", 2));
			wait(3);
			driver.findElement(oipa_GC_AddComments_CommentName).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Comments_commentName", 2));
			wait(3);
			driver.findElement(oipa_GC_AddComments_CommentTextArea).clear();
			wait(3);
			driver.findElement(oipa_GC_AddComments_CommentTextArea)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Comments_CommentsText", 2));
			wait(3);
			driver.findElement(oipa_GC_AddComments_SaveButton).click();
			wait(6);
			if (commonTableTextRetreive(driver, 1, 6)
					.equals(xls.getCellData("GroupCustomer_2", "GC_Comments_CommentsText", 2))) {
				System.out.println("SUCCESS !------ Group Customer Comments are added successfully");
				try {
					takeScreenShot(driver, "Comments Added" + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("FAIL !------ Group Customer Comments are not added successfully");
				Assert.assertTrue(false);

			}
			driver.findElement(oipa_GC_AddComments_CloseWindowButton).click();
			wait(8);
		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Comments » FDD -19483163 - Ability to add
		 * comments at Group Customer Level » Prototype Configuration Testing »
		 * Verify that system allows to upload the attachment in the comments screen
		 * and available in commentsSearch screen.
		 */
		@Test
		public void gc11_UploadComments_TC18704() throws IOException {
			// Uploading Comments for Customer
			System.out.println("************************************************************************************");
			System.out.println(
					"!--------------------****** Uploading and Adding Group Customer Comments******---------------");
			driver.findElement(oipa_GC_Comments_AddCommentsLink).click();
			wait(3);
			driver.switchTo().activeElement();
			wait(3);
			driver.findElement(oipa_GC_AddComments_NewButton).click();
			wait(3);
			driver.findElement(oipa_GC_AddComments_FunctionalDepartmentDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Comments_FuncDept", 3));
			wait(3);
			driver.findElement(oipa_GC_AddComments_Categroy).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Comments_Category", 3));
			wait(3);
			driver.findElement(oipa_GC_AddComments_ClientCommentsField).clear();
			wait(3);
			driver.findElement(oipa_GC_AddComments_ClientCommentsField)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Comments_CommentsField", 3));
			wait(3);
			driver.findElement(oipa_GC_AddComments_CommentName).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Comments_commentName", 3));
			wait(3);
			driver.findElement(oipa_GC_Comments_UploadButton).click();
			wait(3);
			System.out.println("INFO !-- Till File Save, did the coding but pending with upload from desktop coding.");

			// Unable to upload the file as file should be uploaded by accessing
			// windows(From our desktop)
			/*
			 * driver.findElement(oipa_GC_Comments_ChooseFileButton).click();
			 * wait(3); WebElement
			 * Upload1=driver.findElement(oipa_GC_Comments_ChooseFileButton);
			 * Upload1.
			 * sendKeys("D:\\OIPA Automation-WorkSpace\\OIPAAutomation\\src\\com\\Utilities\\CommentsUpload.txt"
			 * ); wait(3);
			 * driver.findElement(oipa_GC_AddComments_SaveButton).click(); wait(3);
			 * System.out.
			 * println("Success!-- Group Customer Comments data uploaded successfully"
			 * );
			 */
			driver.findElement(oipa_GC_AddComments_CloseWindowButton).click();
			wait(8);
		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » GroupCustomer Comments » FDD -19483163 - Ability to add
		 * comments at Group Customer Level » Prototype Configuration Testing »
		 * Verify that user is able to Update User comments and all comments for
		 * GroupCustomer Comments as per the security provided.
		 */
		@Test
		public void gc12_UpdateComments_TC18706() throws IOException {
			// Updating Group Customer Comments
			System.out.println("************************************************************************************");
			System.out.println("!-------------******Updating Group Customer Comments******-------------------!");
			driver.findElement(oipa_GC_Comments_CommentsLink).click();
			wait(3);
			driver.findElement(oipa_GC_Comments_DisplayBoxDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Comments_Display", 2));
			wait(3);
			driver.findElement(oipa_GC_Comments_RefreshButton).click();
			wait(3);
			driver.findElement(oipa_GC_Comments_RowExpandIcon).click();
			wait(3);
			driver.findElement(oipa_GC_EditComments_FunctionalDepartmentDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Comments_FuncDept", 4));
			wait(3);
			driver.findElement(oipa_GC_EditComments_Categroy).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Comments_Category", 4));
			wait(3);
			driver.findElement(oipa_GC_EditComments_ClientCommentsField).clear();
			wait(3);
			driver.findElement(oipa_GC_EditComments_ClientCommentsField)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Comments_CommentsField", 4));
			wait(3);
			driver.findElement(oipa_GC_EditComments_CommentName).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Comments_commentName", 4));
			wait(3);
			driver.findElement(oipa_GC_EditComments_CommentTextArea).clear();
			wait(3);
			driver.findElement(oipa_GC_EditComments_CommentTextArea)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Comments_CommentsText", 3));
			wait(3);
			driver.findElement(oipa_GC_EditComments_SaveButton).click();
			wait(6);
			if (commonTableTextRetreive(driver, 1, 6).equals(xls.getCellData("GroupCustomer_2", "GC_Comments_FuncDept", 4))
					&& commonTableTextRetreive(driver, 1, 7)
							.equals(xls.getCellData("GroupCustomer_2", "GC_Comments_CommentsText", 3))) {
				System.out.println("SUCCESS !------ Group Customer Comments updated successfully");
				try {
					takeScreenShot(driver, "Comments Updated" + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("FAIL !------ Group Customer Comments are not updated successfully");
				Assert.assertTrue(false);

			}
			wait(8);

		}

		@Test
		public void gc13_ParentMasterAgreementCreation() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("-------- ******Creating 'Master Agreement Insured'  Contract Agreement****** ----------");
			driver.findElement(oipa_GC_agreement).click();
			wait(4);

			// Instantiate Action Class
			Actions actions = new Actions(driver);

			// Retrieve WebElement 'Master Agreement' to perform mouse hover
			WebElement hamburgerMenuOption = driver.findElement(oipa_GC_agreementContractTreetext);

			// Mouse hover on hamburgerMenuOption displays (+) Plus icon
			actions.moveToElement(hamburgerMenuOption).perform();
			driver.findElement(oipa_GC_agreementContractPlusIcon).click();
			wait(4);

			if (driver.findElement(oipa_GC_agreementContractText).isDisplayed()
					&& driver.findElement(oipa_GC_agreementDetailsText).isDisplayed()) {
				System.out.println("'Details' section is displayed under the header:'Contract'");
			} else {
				System.out.println("'Details' section is NOT displayed under the header:'Contract'");
				Assert.assertTrue(false);
			}

			driver.findElement(oipa_GC_agreementTypeDD).click();
			wait(3);
			if (driver.findElement(By.xpath("//div[@id='subsectionDynamicFields']")).isDisplayed()) {
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_agreementContractTypeDD", 2));
				wait(4);

				driver.findElement(oipa_GC_agreementName).clear();
				wait(4);
				agreementName = xls.getCellData("GroupCustomer_1", "GC_agreementNameMA_Parent", 2);
				driver.findElement(oipa_GC_agreementName).sendKeys(agreementName);
				driver.findElement(oipa_GC_agreementName).sendKeys(Keys.TAB);
				wait(4);

				driver.findElement(oipa_GC_agreementStatusDD).click();
				wait(6);
				String agreementStatus = xls.getCellData("GroupCustomer_1", "GC_agreementStatusDD", 2);
				selectItemInDropdown(driver, agreementStatus);
				wait(3);

				/*
				 * driver.findElement(oipa_GC_agreementUndefinedExpandIcon).click();
				 * wait(3); driver.findElement(oipa_GC_agreementNumber)
				 * .sendKeys(xls.getCellData("GroupCustomer_1",
				 * "GC_agreementNumber", 2));
				 * driver.findElement(oipa_GC_agreementName).sendKeys(Keys.TAB);
				 * wait(6);
				 * driver.findElement(oipa_GC_agreementUndefinedExpandIcon).click();
				 * wait(3); driver.findElement(oipa_GC_agreementExperienceNumber)
				 * .sendKeys(xls.getCellData("GroupCustomer_1",
				 * "GC_agreementExperienceNumber", 2)); wait(4);
				 */

				driver.findElement(oipa_GC_agreementSaveButton).click();
				wait(3);
				Assert.assertEquals(driver.findElement(oipa_addMessage).getText(), "Agreement added successfully");
				try {
					takeScreenShot(driver, "Agreement Creation" + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}
				wait(3);
				String first_part = "//button[@aria-label='";
				String second_part = "']";
				String final_xpath = first_part + agreementName + second_part;
				if (driver.findElement(By.xpath(final_xpath)).getText()
						.equals(agreementName + " " + "(" + agreementStatus + ")")) {
					System.out.println(
							"Newly added Agreement and status: " + driver.findElement(By.xpath(final_xpath)).getText()
									+ "is displayed under agreements type selection");
				} else {
					System.out.println("Newly added Agreement and status is NOT displayed under agreements type selection");
					Assert.assertTrue(false);
				}

				if (driver.findElement(oipa_GC_agreementAddAgmtName).getText()
						.contains(agreementName + " " + "(" + agreementStatus + ")")) {
					System.out.println("Updated Header is displayed as:  "
							+ driver.findElement(oipa_GC_agreementAddAgmtName).getText());

				} else {
					System.out.println("Header is not updated as expected");
					Assert.assertTrue(false);
				}

				if (driver.findElement(oipa_GC_agreementContractCount).getText()
						.equals(xls.getCellData("GroupCustomer_1", "GC_agreementCount", 2))) {
					System.out.println("The Count of agreements added are : "
							+ driver.findElement(oipa_GC_agreementContractCount).getText());
				} else {
					System.out.println("The count of agreements is NOT as expected");
					Assert.assertTrue(false);
				}
			}
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » Group Customer - Agreements » Bug Agreements -Roles » TC-09
		 * Verify that user is able to add roles through New client tab
		 */
		@Test
		public void gc14_AddingAgreementRoles_TC28409() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("--------****** Adding 'Roles'for an Agreement using New Client Button******----------");
			driver.findElement(oipa_GC_AgreementRoles_RolesLink).click();
			wait(4);
			if (driver.findElement(oipa_GC_AgreementRoles_FindClientButton).isDisplayed()
					&& driver.findElement(oipa_GC_AgreementRoles_NewClientButton).isDisplayed()
					&& driver.findElement(oipa_GC_AgreementRoles_FindCustomerButton).isDisplayed()) {
				System.out.println("INFO !-- 'Find Client','New Client' and 'Find Customer' tabs are displayed in Roles");
				try {
					takeScreenShot(driver, "Agreement Roles Added" + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out
						.println("INFO !-- 'Find Client','New Client' and 'Find Customer' tabs are not displayed in Roles");
				Assert.assertTrue(false);

			}
			if (driver.findElement(oipa_GC_AgreementRoles_NewClientButton).isDisplayed()) {
				driver.findElement(oipa_GC_AgreementRoles_NewClientButton).click();
				driver.switchTo().activeElement();
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_NewClient_TypeDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_Client_Type", 2));
				wait(3);
				if (xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_Client_Type", 2)
						.equals(driver.findElement(oipa_GC_AgreementRoles_NewClient_Type).getText())) {
					System.out.println(
							"SUCCESS !------ " + xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_Client_Type", 2)
									+ " is selected in Type Dropdown");

				} else {
					System.out
							.println("FAIL !-----" + xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_Client_Type", 2)
									+ " is not selected in Type Dropdown");
					Assert.assertTrue(false);

				}
				driver.findElement(oipa_GC_AgreementRoles_NewClient_ClientTypeDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_Type", 2));
				wait(3);
				driver.findElement(oipa_client_legalResidenceCountry).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_Legal ResidenceCountry", 2));
				wait(3);
				driver.findElement(oipa_client_prefix).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_Prefix", 2));
				wait(3);
				driver.findElement(oipa_client_FirstName)
						.sendKeys(xls.getCellData("GroupCustomer_2", "CreateClient_FirstName", 2) + rand.nextInt(1000));
				wait(3);
				driver.findElement(oipa_client_MiddleName)
						.sendKeys(xls.getCellData("GroupCustomer_2", "CreateClient_MiddleName", 2));
				wait(3);
				driver.findElement(oipa_client_LastName)
						.sendKeys(xls.getCellData("GroupCustomer_2", "CreateClient_LastName", 2));
				wait(3);
				driver.findElement(oipa_client_Gender).click();
				wait(3);
				driver.findElement(oipa_client_MartialDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_MaritalStatus", 2));
				wait(3);
				driver.findElement(oipa_client_DOB).sendKeys(xls.getCellData("GroupCustomer_2", "CreateClient_DOB", 2));
				wait(3);
				driver.findElement(oipa_client_TaxID).sendKeys(String.valueOf(rand.nextInt(1000000000)));
				wait(3);
				driver.findElement(oipa_client_BirthCountry).click();
				wait(4);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_Birthcountry", 2));
				wait(4);
				driver.findElement(oipa_client_citizenShipCountry).click();
				wait(4);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "CreateClient_CitizenshipCountry", 2));
				wait(3);
				driver.findElement(oipa_client_TextField).clear();
				driver.findElement(oipa_client_TextField).sendKeys(String.valueOf(rand.nextInt(100000)));
				wait(3);
				wait(3);
				driver.findElement(oipa_GC_CustomerAddress_AddressTypeDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Addr_AddressType", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_NewClient_CountryDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Addr-Country", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_NewClient_AddressLine1)
						.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText1", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_NewClient_AddressLine2)
						.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText2", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_NewClient_AddressLine3)
						.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText3", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_NewClient_AddressLine4)
						.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AddressText4", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_NewClient_City)
						.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_City", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_NewClient_StateDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Addr_State", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_NewClient_Zip)
						.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_Zip", 2));
				wait(4);
				driver.findElement(oipa_GC_AgreementRoles_NewClient_Email)
						.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_Email", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_NewClient_Fax)
						.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Addr_Fax Number", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_NewClientSaveButton).click();
				wait(3);
				System.out.println("New Client is added successfully");
				wait(6);
				// driver.findElement(oipa_GC_AgreementRoles_RowExpandIcon).click();
				// wait(3);
				// driver.findElement(oipa_GC_AgreementRoles_RowExpandIcon).click();
				// wait(3);
				if (driver.findElement(oipa_GC_AgreementRoleDetail_AgreementDetailSection).isDisplayed()) {
					System.out.println(
							"INFO ! ------- Detail Section is present under Agreement Roles section when expanded");
				} else {
					System.out.println(
							"INFO ! ---------Detail Section is not present under Agreement Roles section when expanded");
					Assert.assertTrue(false);

				}
			}
			if (driver.findElement(oipa_GC_AgreementRoleDetail_IdentifierNumber).isDisplayed()
					&& !driver.findElement(oipa_GC_AgreementRoleDetail_IdentifierNumber).isEnabled()) {
				System.out.println(" INFO ! ------ Identifier number is present and disabled");
			} else {
				System.out.println(" INFO ! ------ Identifier number is not present and not disabled");
				Assert.assertTrue(false);

			}
			if (driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleType).isDisplayed()
					&& driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleType).isEnabled()) {
				System.out.println(" INFO !--------  Agreement role Type is present and Enabled");

			} else {
				System.out.println(" INFO !--------  Agreement role Type is not present and not Enabled");
				Assert.assertTrue(false);

			}
			wait(3);
			driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleStatusDD).click();
			wait(3);
			String DDValues[] = { "Active", "Deleted", "Inactive" };
			if (driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleStatusActive).getText().equals(DDValues[0])
					&& driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleStatusDeleted).getText()
							.equals(DDValues[1])
					&& driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleStatusInactive).getText()
							.equals(DDValues[2])) {
				System.out.println("First Value in Agreement Role Status Dropdown is :" + DDValues[0]);
				System.out.println("Second Value in Agreement Role Status Dropdown is :" + DDValues[1]);
				System.out.println("Third Value in Agreement Role Status Dropdown is :" + DDValues[2]);

			}

			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_RoleStatus", 2));
			wait(3);
			driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleType)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_RoleType", 2));
			wait(3);
			driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleSaveButton).click();
			wait(5);
			if (driver.findElement(oipa_GC_AgreementRoles_RolesTable_AgreementType).getText()
					.equals(xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_Client_Type", 2))) {
				System.out.println(" SUCCESS !--- Agreemt Role is added using New Client button with type "
						+ driver.findElement(oipa_GC_AgreementRoles_RolesTable_AgreementType).getText());
				try {
					takeScreenShot(driver, "AgreementRole Added-NewClient" + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			else {
				System.out.println(" FAIL !--- Agreemt Role is not added using New Client button with type "
						+ driver.findElement(oipa_GC_AgreementRoles_RolesTable_AgreementType).getText());
				Assert.assertTrue(false);

			}
			driver.findElement(oipa_GC_AgreementRoles_RowExpandIcon).click();
			System.out.println("INFO ! ---- Agreement Roles section is expanded");
			wait(3);
			driver.findElement(oipa_GC_AgreementRoles_RowExpandIcon).click();
			System.out.println("INFO ! ---- Agreement Roles section is Collapsed");
			wait(3);
			driver.findElement(oipa_GC_AgreementRoles_RowExpandIcon).click();
			System.out.println("INFO ! ---- Agreement Roles section is expanded");
			wait(4);
			String IDNumber = driver.findElement(oipa_GC_AgreementRoleDetail_IdentifierNumber).getAttribute("value");
			if (IDNumber == null) {
				System.out.println("FAIL ! ---------IdentifierNumber is not auto-genereated and it is null");
				Assert.assertTrue(false);
			} else {
				System.out.println("SUCCESS ! --------IdentifierNumber auto-generated is " + IDNumber);
				try {
					takeScreenShot(driver, "IdentifierNumber autogenerated " + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » Group Customer - Agreements » Bug Agreements -Roles » TC-13
		 * Verify "Edit Role Details" in hamburgr menu when a client role is added.
		 */
		@Test
		public void gc15_EditAgreementRoles_TC28414() throws IOException {
			// Group Customer Agreement Roles Edit functionality
			System.out.println("************************************************************************************");
			System.out.println("--------****** Editing 'Roles' for an Agreement******----------");
			Actions actions = new Actions(driver);
			WebElement HamburgerMenuIcon = driver.findElement(oipa_GC_AgreementRole_EditHamburgerMenu);
			actions.moveToElement(HamburgerMenuIcon).perform();
			driver.findElement(oipa_GC_AgreementRole_EditRoleDetail).click();
			wait(3);
			driver.findElement(oipa_GC_AgreementRole_EditAgreementRoleType)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_RoleType", 2));
			wait(3);
			driver.findElement(oipa_GC_AgreementRole_EditRole_AgreementStatus).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_RoleStatus", 3));
			wait(3);
			driver.findElement(oipa_GC_AgreementRole_EditRole_Save).click();
			wait(4);
			driver.findElement(oipa_GC_AgreementRoles_RowExpandIcon).click();
			wait(3);
			if (driver.findElement(oipa_GC_AgreementRole_AgreementRoleStatus).getText()
					.equals(xls.getCellData("GroupCustomer_2", "GC_Agreement_RoleStatus", 3))) {
				System.out.println("SUCCESS !-------Agreement Role is updated Successfully");
				try {
					takeScreenShot(driver, "Agreement Role Updated" + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("FAIL !-------Agreement Role is not updated Successfully");
				Assert.assertTrue(false);

			}
			wait(8);

		}
		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » Group Customer - Agreements » Bug Agreements -Roles » TC-12
		 * Verify "Delete Role" in hamburgr menu when a client role is added.
		 */

		@Test
		public void gc16_DeleteAgreementRoles_TC28413() throws IOException {

			// Group Customer Agreement Roles Delete Functionality
			System.out.println("************************************************************************************");
			System.out.println("--------****** Deleting 'Roles' for an Agreement******----------");
			Actions actions = new Actions(driver);
			WebElement HamburgerMenuIcon = driver.findElement(oipa_GC_AgreementRole_EditHamburgerMenu);
			actions.moveToElement(HamburgerMenuIcon).perform();
			driver.findElement(oipa_GC_AgreementRole_DeleteRole).click();
			wait(5);
			if (driver.findElement(oipa_GC_AgreementRole_DeleteRole_AgreementRoleStatusinTable).getText()
					.equals(xls.getCellData("GroupCustomer_2", "GC_Agreement_RoleStatus", 4))) {

				System.out.println("SUCCESS !-------Agreement Role is Deleted Successfully");
				try {
					takeScreenShot(driver, "Agreement Role Deleted " + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				System.out.println("FAIL !-------Agreement Role is not Deleted Successfully");
				Assert.assertTrue(false);
			}
			if (driver.findElement(oipa_GC_AgreementRoleDetail_IdentifierNumber).isEnabled()
					&& driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleType).isEnabled()
					&& driver.findElement(oipa_GC_AgreementRole_DeleteRole_AgremmentStatus).isEnabled()) {
				System.out.println(
						"FAIL !-------IdentifierNumber,AgreementRoleType and AgreementRoleStatus are enabled after role deleted");
				Assert.assertTrue(false);
			} else {
				System.out.println(
						"SUCCESS !-------IdentifierNumber,AgreementRoleType and AgreementRoleStatus are Disabled after role deleted");
				Assert.assertTrue(true);
			}
			wait(3);
			// To close the open section of Agreement Role
			driver.findElement(oipa_GC_AgreementRoles_RowExpandIcon).click();
			wait(8);

		}
		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » Group Customer - Agreements » Bug Agreements -Roles » TC-08
		 * Verify that user is able to add roles through Find client tab
		 */

		@Test
		public void gc17_AddAgreementRoles_FindClient_TC28395() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("--------****** Adding 'Roles'for an Agreement using Find Client Button******----------");
			wait(3);
			if (driver.findElement(oipa_GC_AgreementRoles_FindClientButton).isDisplayed()) {

				driver.findElement(oipa_GC_AgreementRoles_FindClientButton).click();
				wait(4);
				driver.findElement(Oipa_GC_AgreementRoles_FindClient_TypeDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_Client_Type", 2));
				wait(4);
				System.out.println("INFO !----First Name we are searching for is : " + ClientFirstname);
				driver.findElement(oipa_GC_AgreementRoles_FindClient_FirstName).sendKeys(ClientFirstname);
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_FindClient_SearchButton).click();
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_SearchResult).click();
				wait(3);
				driver.findElement(oipa_GC_AgreementRoles_FindClientSaveButton).click();
				wait(3);
				driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleType)
						.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_RoleType", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleStatusDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_RoleStatus", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleSaveButton).click();
				wait(6);
				if (driver.findElement(oipa_GC_AgreementRoles_RolesTable_AgreementType).getText()
						.equals(xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_Client_Type", 2))) {
					System.out.println(" SUCCESS !--- Agreemt Role is added using Find Client button with type "
							+ driver.findElement(oipa_GC_AgreementRoles_RolesTable_AgreementType).getText());
					try {
						takeScreenShot(driver, "Agreement Role Added-FindClient" + runDateTime(driver));
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				else {
					System.out.println(" FAIL !--- Agreemt Role is not added using Find Client button with type "
							+ driver.findElement(oipa_GC_AgreementRoles_RolesTable_AgreementType).getText());
					Assert.assertTrue(false);

				}
				driver.findElement(oipa_GC_AgreementRoles_RowExpandIcon).click();
				System.out.println("INFO ! ---- Agreement Roles section is expanded");
				wait(4);
				String IDNumber = driver.findElement(oipa_GC_AgreementRoleDetail_IdentifierNumber).getAttribute("value");
				if (IDNumber == null) {
					System.out.println("FAIL ! ---------IdentifierNumber is not auto-genereated and it is null");
					Assert.assertTrue(false);
				} else {
					System.out.println("SUCCESS ! --------IdentifierNumber auto-generated is " + IDNumber);

				}
				wait(3);
				// To close the open section of Agreement Role
				driver.findElement(oipa_GC_AgreementRoles_RowExpandIcon).click();
			}
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » Group Customer - Agreements » Bug Agreements -Roles » TC-10
		 * Verify that user is able to add roles through Find Customer" and Go To
		 * client works for it.
		 */
		@Test
		public void gc18_AddingAgreementRoles_FindCustomer_TC28410() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("--------****** Adding 'Roles'for an Agreement using Find Customer Button******----------");
			wait(3);
			if (driver.findElement(oipa_GC_AgreementRoles_FindCustomerButton).isDisplayed()) {
				driver.findElement(oipa_GC_AgreementRoles_FindCustomerButton).click();
				wait(4);
				driver.findElement(oipa_GC_AgreementRole_FindCustomer_SearchExpandIcon).click();
				wait(4);
				System.out.println("Customer Number we are searching is : " + CustomerNumberCapture);
				driver.findElement(oipa_GC_AgreementRole_FindCustomer_CustomerNumber).sendKeys(CustomerNumberCapture);
				wait(3);
				driver.findElement(oipa_GC_AgreementRole_FindCustomer_Search).click();
				wait(3);
				driver.findElement(oipa_GC_AgreementRole_Findustomer_SearchResult).click();
				wait(3);
				driver.findElement(oipa_GC_AgreementRole_FindCustomer_SearchOKButton).click();
				wait(3);
				driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleStatusDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_RoleStatus", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleType)
						.sendKeys(xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_RoleType", 2));
				wait(3);
				driver.findElement(oipa_GC_AgreementRoleDetail_AgreementRoleSaveButton).click();
				wait(5);
				if (driver.findElement(oipa_GC_AgreementRoles_RolesTable_AgreementType).getText()
						.equals(xls.getCellData("GroupCustomer_2", "GC_AgreementRoles_Client_Type", 2))) {
					System.out.println(" SUCCESS !--- Agreemt Role is added using Find Customer button with type "
							+ driver.findElement(oipa_GC_AgreementRoles_RolesTable_AgreementType).getText());
					try {
						takeScreenShot(driver, "greement Role Added-FindCustomer " + runDateTime(driver));
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				else {
					System.out.println(" FAIL !--- Agreemt Role is not added using Find Customer button with type "
							+ driver.findElement(oipa_GC_AgreementRoles_RolesTable_AgreementType).getText());
					Assert.assertTrue(false);

				}
				driver.findElement(oipa_GC_AgreementRoles_RowExpandIcon).click();
				System.out.println("INFO ! ---- Agreement Roles section is expanded");
				wait(4);
				String IDNumber = driver.findElement(oipa_GC_AgreementRoleDetail_IdentifierNumber).getAttribute("value");
				if (IDNumber == null) {
					System.out.println("FAIL ! ---------IdentifierNumber is not auto-genereated and it is null");
					Assert.assertTrue(false);
				} else {
					System.out.println("SUCCESS ! --------IdentifierNumber auto-generated is " + IDNumber);
				}
				wait(3);
				// To close the open section of Agreement Role
				driver.findElement(oipa_GC_AgreementRoles_RowExpandIcon).click();
			}
			wait(8);

		}

		// Group Customer Agreement Product Creation
		@Test
		public void gc19_AgreementProductCreation() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("---------****** Creating the Group Prototype Product****** ------------");
			driver.findElement(oipa_GC_productLink).click();
			wait(4);
			driver.findElement(oipa_GC_productAddProductButton).click();
			wait(4);
			driver.switchTo().activeElement();
			driver.findElement(oipa_GC_productcompanyDD).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_productcompanyDD", 2));
			wait(4);
			driver.findElement(oipa_GC_productGPPlink).click();
			wait(4);
			driver.findElement(oipa_GC_productAddProductOkButton).click();
			wait(4);
			driver.findElement(oipa_GC_productStatusDD).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_productStatusDD", 2));
			wait(4);
			driver.findElement(oipa_GC_productEffectivefromDate)
					.sendKeys(xls.getCellData("GroupCustomer_1", "GC_productEffectivefromDate", 2));
			wait(4);
			driver.findElement(oipa_GC_productExpirationDate).click();
			wait(3);
			driver.findElement(oipa_GC_productExpirationDate)
					.sendKeys(xls.getCellData("GroupCustomer_1", "GC_productExpirationDate", 2));
			wait(4);
			driver.findElement(oipa_GC_productSaveButton).click();
			wait(8);
			try {
				takeScreenShot(driver, "Product 'GPP' Creation" + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (commonTableTextRetreive(driver, 1, 3).equals(xls.getCellData("GroupCustomer_1", "GC_productName", 2))
					&& commonTableTextRetreive(driver, 1, 4)
							.equals(xls.getCellData("GroupCustomer_1", "GC_productStatusDD", 2))) {
				System.out.println("Product in the table is displayed as:" + " " + commonTableTextRetreive(driver, 1, 3)
						+ " " + commonTableTextRetreive(driver, 1, 4) + "  " + commonTableTextRetreive(driver, 1, 5) + " "
						+ commonTableTextRetreive(driver, 1, 6));
				System.out.println("SUCCESS !----------Group Prototype Product is added successfully");
			} else {
				System.out.println("FAIL !----------Group Prototype Product is Not Created and No data in table exists");
				Assert.assertTrue(false);
			}
			wait(8);

		}

		// Group Customer Agreement Plan Creation
		@Test
		public void gc20_AgreementPlanCreation() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("-----------******Creating Plan under Agreements section ******---------");
			driver.findElement(oipa_GC_agreementplanLink).click();
			wait(4);
			driver.findElement(oipa_GC_planAddNewPlanDD).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planAddNewPlanDD", 2));
			wait(6);
			driver.findElement(oipa_GC_planAddNewPlanButton).click();
			wait(4);
			driver.switchTo().activeElement();
			driver.findElement(oipa_GC_plancompanyDD).click();
			wait(5);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_plancompanyDD", 2));
			wait(6);
			driver.findElement(oipa_GC_planproductDD).click();
			wait(5);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planproductDD", 2));
			wait(6);
			String planName = xls.getCellData("GroupCustomer_1", "GC_planName", 2) + rand.nextInt(1000);
			driver.findElement(oipa_GC_planName).sendKeys(planName);
			driver.findElement(oipa_GC_planName).sendKeys(Keys.TAB);
			wait(4);
			driver.findElement(oipa_GC_planStatusDD).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planStatusDD", 2));
			wait(6);
			driver.findElement(oipa_GC_planEffectiveFrom).clear();
			driver.findElement(oipa_GC_planEffectiveFrom)
					.sendKeys(xls.getCellData("GroupCustomer_1", "GC_planEffectiveFrom", 2));
			driver.findElement(oipa_GC_planEffectiveFrom).sendKeys(Keys.TAB);
			wait(6);
			driver.findElement(oipa_GC_planCurrencyDD).click();
			wait(6);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planCurrencyDD", 2));
			wait(6);
			driver.findElement(oipa_GC_planExpirationDatePopUp).click();
			wait(3);
			driver.findElement(oipa_GC_planExpirationDatePopUp)
					.sendKeys(xls.getCellData("GroupCustomer_1", "GC_planExpirationDate", 2));
			driver.findElement(oipa_GC_planExpirationDatePopUp).sendKeys(Keys.TAB);
			wait(6);
			driver.findElement(oipa_GC_planAlocationMethodDD).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planAlocationMethodDD", 2));
			wait(6);
			driver.findElement(oipa_GC_planMarketMakerDD).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planMarketMakerDD", 2));
			wait(6);
			driver.findElement(oipa_GC_planAddSegmentPlusIcon).click();
			wait(4);
			driver.switchTo().activeElement();
			System.out.println("INFO !--------- Adding 'Base Coverage Plus' segment to the Plan ----------");
			driver.findElement(oipa_GC_planSegmentNameDD).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentNameDD", 2));
			wait(6);
			driver.findElement(oipa_GC_planSegmentName)
					.sendKeys(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 2));
			wait(4);
			driver.findElement(oipa_GC_planSegmentName).sendKeys(Keys.TAB);
			wait(3);
			driver.findElement(oipa_GC_planSegmentTypeDD).click();
			wait(8);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentTypeDD", 2));
			wait(6);
			driver.findElement(oipa_GC_planProcessAddSegment).click();
			wait(4);
			driver.findElement(oipa_GC_planAddSegmentPlusIcon).click();
			wait(4);
			driver.findElement(oipa_GC_planAddSegmentPlusIcon).sendKeys(Keys.TAB);
			driver.switchTo().activeElement();
			System.out.println("INFO !--------- Adding 'Base Coverage Basic' segment to the Plan ----------");
			driver.findElement(oipa_GC_planSegmentNameDD).click();
			wait(4);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentNameDD", 3));
			wait(6);
			driver.findElement(oipa_GC_planSegmentName)
					.sendKeys(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 3));
			wait(4);
			driver.findElement(oipa_GC_planSegmentName).sendKeys(Keys.TAB);
			wait(3);
			driver.findElement(oipa_GC_planSegmentTypeDD).click();
			wait(6);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentTypeDD", 3));
			wait(6);
			driver.findElement(oipa_GC_planProcessAddSegment).click();
			wait(4);
			if (commonTableTextRetreive(driver, 1, 3).equals(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 2))
					&& commonTableTextRetreive(driver, 1, 4)
							.equals(xls.getCellData("GroupCustomer_1", "GC_planSegmentNameDD", 2))
					&& commonTableTextRetreive(driver, 2, 3)
							.equals(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 3))
					&& commonTableTextRetreive(driver, 2, 4)
							.equals(xls.getCellData("GroupCustomer_1", "GC_planSegmentNameDD", 3))) {
				System.out.println("INFO !---- Segment Details are saved in tabular format successfully");
				System.out.println("INFO !---- Segments details in the table for first row are displayed as:" + " "
						+ commonTableTextRetreive(driver, 1, 3) + " " + commonTableTextRetreive(driver, 1, 4));
				System.out.println("INFO !---- Segments details in the table for second row are displayed as:" + " "
						+ commonTableTextRetreive(driver, 2, 3) + " " + commonTableTextRetreive(driver, 2, 4));
				wait(3);
				driver.findElement(oipa_GC_planProcessSaveAddPlan).click();
				wait(4);
				driver.findElement(oipa_GC_planBusinessStatusDD).click();
				wait(4);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planBusinessStatusDD", 2));
				wait(6);
				driver.findElement(oipa_GC_planSaveButton).click();
				wait(3);
				Assert.assertEquals(driver.findElement(oipa_addMessage).getText(), "Plan added successfully");
				driver.findElement(By.xpath("//button[@id='refreshPlansButton']")).click();
				wait(8);
				try {
					takeScreenShot(driver, "Agreement Plan Added " + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}			if (commonTableTextRetreive(driver, 1, 3).equals(planName) && commonTableTextRetreive(driver, 1, 4)
						.equals(xls.getCellData("GroupCustomer_1", "GC_planBusinessStatusDD", 2))) {
					System.out.println("Plan details in the table are displayed as:" + " "
							+ commonTableTextRetreive(driver, 1, 3) + " " + commonTableTextRetreive(driver, 1, 4) + "  "
							+ commonTableTextRetreive(driver, 1, 5) + " " + commonTableTextRetreive(driver, 1, 6));
					System.out.println("SUCCESS !----------Plan is added successfully with plan segments");

				} else {
					System.out.println("FAIL !----------Plan is Not Created and No data in table exists");
					Assert.assertTrue(false);
				}
			}
			wait(8);
		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » Group Customer - Agreements » Bug 24451285-Agreements Class
		 * Groups-Phase IV-Jet Upgrade. » TC-03 Verify that user is able to add a
		 * class group to the agreement
		 */

		@Test
		public void gc21_AddClassGroupToAgreement_TC29274() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("--------****** Adding Class Group for an Agreement'******----------");
			System.out.println("-------Verifying 'Save' button functionality----------");
			wait(3);
			driver.findElement(oipa_GC_Agreement_ClassGroupsLink).click();
			wait(3);
			String ClassGrpName1 = AddClassGroupDetails();
			driver.findElement(oipa_GC_Agreement_AddClassGroups_agreementClassgroupSaveButton).click();
			wait(3);
			Assert.assertEquals(driver.findElement(oipa_addMessage).getText(), "Class group saved successfully.");
			wait(6);
			try {
				takeScreenShot(driver, "Agreement Class Group Added " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (commonTableTextRetreive(driver, 1, 3).equals(ClassGrpName1) && commonTableTextRetreive(driver, 1, 4)
					.equals(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClassGroup_SaveStatus", 2))) {
				System.out.println("SUCCESS ! ------------ Clicked on 'Save' button and Class Group is added successfully");
				
			} else {
				System.out.println("FAIL ! ------------ Class Group is not added successfully");
				Assert.assertTrue(false);
			}
			wait(4);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_HamburgerMenuIcon).click();
			wait(3);
			System.out.println("Hamburger Menu Items are : 1. "
					+ driver.findElement(oipa_GC_Agreement_AddClassGroups_Hamburger_Edit).getText() + " 2. "
					+ driver.findElement(oipa_GC_Agreement_AddClassGroups_Hamburger_GoToClass).getText() + " 3. "
					+ driver.findElement(oipa_GC_Agreement_AddClassGroups_Hamburger_Delete).getText());
			wait(3);
			System.out.println("-------Verifying 'Cancel' button functionality----------");
			AddClassGroupDetails();
			driver.findElement(oipa_GC_Agreement_AddClassGroups_agreementClassGroupCancelButton).click();
			wait(3);
			System.out.println("SUCCESS ! ------------ Clicked on 'Cancel' Button, Class Group is not added successfully");
			wait(8);
		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » Group Customer - Agreements » Bug 24451285-Agreements Class
		 * Groups-Phase IV-Jet Upgrade. » TC-05 Verify that user is able to edit a
		 * class group through hamburger menu item "Edit" in Class Group section
		 */
		@Test
		public void gc22_EditClassGroupToAgreement_TC29276() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("--------****** Editing Class Group  through hamburger menu item 'Edit'******----------");
			wait(3);
			driver.findElement(oipa_GC_Agreement_ClassGroups_StatusFilter).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_ClassGroup_StatusFilter", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_ClassGroups_RefreshButton).click();
			wait(3);
			Actions actions = new Actions(driver);
			WebElement HamburgerMenuIcon = driver.findElement(oipa_GC_Agreement_AddClassGroups_HamburgerMenuIcon);
			actions.moveToElement(HamburgerMenuIcon).perform();
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_Hamburger_Edit).click();
			wait(3);
			if (driver.findElement(oipa_GC_Agreement_EditClassGroups_Status).isEnabled()
					&& driver.findElement(oipa_GC_Agreement_EditClassGroups_EffectiveDate).isEnabled()
					&& driver.findElement(oipa_GC_Agreement_EditClassGroups_ExpirationDate).isEnabled()) {
				System.out.println("INFO !------- Status,EffectiveDate and Expiration date fields are Enabled");
			} else {
				System.out.println("INFO !------- Status,EffectiveDate and Expiration date fields are not Enabled");

			}
			driver.findElement(oipa_GC_Agreement_EditClassGroups_StatusDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClassGroup_SaveStatus", 3));
			wait(3);
			driver.findElement(oipa_GC_Agreement_EditClassGroups_EffectiveDate).clear();
			wait(3);
			driver.findElement(oipa_GC_Agreement_EditClassGroups_EffectiveDate)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_EffectiveFrom", 3));
			wait(3);
			driver.findElement(oipa_GC_Agreement_EditClassGroups_ExpirationDate).clear();
			wait(3);
			driver.findElement(oipa_GC_Agreement_EditClassGroups_ExpirationDate)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_ExpirationDate", 3));
			wait(3);
			driver.findElement(oipa_GC_Agreement_EditClassGroups_SaveButton).click();
			wait(3);
			// Assert.assertEquals(driver.findElement(oipa_addMessage).getText(),
			// "Class Group modified successfully");
			wait(3);
			driver.findElement(oipa_GC_Agreement_ClassGroups_StatusFilter).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_ClassGroup_StatusFilter", 3));
			wait(3);
			driver.findElement(oipa_GC_Agreement_ClassGroups_RefreshButton).click();
			wait(6);
			try {
				takeScreenShot(driver, "Agreement Class Group Modified " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (commonTableTextRetreive(driver, 1, 4)
					.equals(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClassGroup_SaveStatus", 3))) {
				System.out.println(
						"SUCCESS ! ------------ Clicked on 'Save' button and Class Group is modified successfully with the status : "
								+ xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClassGroup_SaveStatus", 3));
				
			} else {
				System.out.println("FAIL ! ------------ Class Group is not modified successfully");
				Assert.assertTrue(false);
			}
			// Verify if fields are disabled after Edit
			driver.findElement(oipa_GC_Agreement_EditClassGroups_RowExpandIcon).click();
			wait(5);
			try {
				takeScreenShot(driver, "Fields Disabled " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (driver.findElement(oipa_GC_Agreement_EditClassGroups_ClassGrpName).isEnabled()
					&& driver.findElement(oipa_GC_Agreement_EditClassGroups_Status).isEnabled()
					&& driver.findElement(oipa_GC_Agreement_EditClassGroups_EffectiveDate).isEnabled()
					&& driver.findElement(oipa_GC_Agreement_EditClassGroups_ExpirationDate).isEnabled()) {
				System.out.println("INFO ! ------------ All Fields are not disbaled after we edit the class group");
				Assert.assertTrue(false);

			} else {
				System.out.println("INFO ! ------------ All Fields are disbaled after we edit the class group");

			}
			wait(8);

		}

		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » Group Customer - Agreements » Bug 24451285-Agreements Class
		 * Groups-Phase IV-Jet Upgrade. » TC-07 Verify that user is able to delete a
		 * class group through hamburger menu item "Delete" in Class Group section
		 */
		@Test
		public void gc23_DeleteAgreementClassGroup_TC29278() {
			System.out.println("************************************************************************************");
			System.out.println("--------****** Deleting Class Group  through hamburger menu item 'Delete'******----------");
			wait(3);
			try {
				Actions actions = new Actions(driver);
				WebElement HamburgerMenuIcon = driver.findElement(oipa_GC_Agreement_AddClassGroups_HamburgerMenuIcon);
				actions.moveToElement(HamburgerMenuIcon).perform();
				driver.findElement(oipa_GC_Agreement_AddClassGroups_Hamburger_Delete).click();
				wait(3);
				driver.switchTo().activeElement();
				wait(3);
				driver.findElement(oipa_GC_Agreement_DeleteClassGroup_OKButton).click();
				wait(3);
				System.out.println("SUCCESS ! --------- Class Group is deleted successfully");
				try {
					takeScreenShot(driver, "Class Group Deleted " + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}		} catch (Exception e) {
				System.out.println("FAIL ! --------- Class Group is not deleted successfully");
			}
			wait(8);

		}
		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » Group Customer - Agreements » Bug 24451285-Agreements Class
		 * Groups-Phase IV-Jet Upgrade. » TC-08 Verify that user is able to submit
		 * and process a Class Group through Class group screen
		 */

		@Test
		public void gc24_SubmitAgreementClassGroup_TC29286() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("--------****** Add and Submit Class Group ******----------");
			wait(3);
			String ClassGrpName1 = AddClassGroupDetails();
			driver.findElement(oipa_GC_Agreement_AddClassGroups_agreementClassgroupSaveButton).click();
			wait(3);
			Assert.assertEquals(driver.findElement(oipa_addMessage).getText(), "Class group saved successfully.");
			wait(3);
			System.out.println(ClassGrpName1 + " is added successfully");
			wait(3);
			driver.findElement(oipa_GC_ClassGroups_LeftLink).click();
			wait(3);
			// driver.findElement(oipa_GC_ClassGroups_AsOfDate).clear();
			// wait(3);
			driver.findElement(oipa_GC_ClassGroups_RowExpandIcon).click();
			wait(3);
			/*
			 * if(driver.findElement(
			 * oipa_GC_ClassGroups_RowExpand_ReturnApplicationButton).isDisplayed())
			 * { driver.findElement(
			 * oipa_GC_ClassGroups_RowExpand_ReturnApplicationButton).click();
			 * wait(4);
			 * driver.findElement(oipa_GC_ClassGroups_RowExpandIcon).click();
			 * wait(3); }
			 */
			Assert.assertEquals(commonTableTextRetreive(driver, 1, 7),
					xls.getCellData("GroupCustomer_2", "GC_ClassGroups_Status", 2));
			System.out.println(" Class Group Status before submitting is : "
					+ xls.getCellData("GroupCustomer_2", "GC_ClassGroups_Status", 2));
			wait(3);
			driver.findElement(oipa_GC_ClassGroups_SubmitButton).click();
			wait(6);
			try {
				takeScreenShot(driver, "ClassGroup Submitted" + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (commonTableTextRetreive(driver, 1, 7)
					.equals(xls.getCellData("GroupCustomer_2", "GC_ClassGroups_Status", 3))) {
				System.out.println("Class Group is submitted and status is : "
						+ xls.getCellData("GroupCustomer_2", "GC_ClassGroups_Status", 3));
				takeScreenShot(driver, "Agreement Class Group Submitted");
			} else {
				System.out.println("INFO ! -----------Class Group is not submitted");
				Assert.assertTrue(false);
			}
			wait(3);
			driver.findElement(oipa_GC_ClassGroups_RowExpandIcon).click();
			wait(8);

		}

		@Test
		public void gc25_CopyAgreementClassGroup_TC29275() {
			System.out.println("************************************************************************************");
			System.out.println("--------****** Copy of Agreement Class Group ******----------");
			wait(3);
			driver.findElement(oipa_GC_agreement).click();
			wait(4);
			driver.findElement(oipa_GC_CopyClassGroups_ContractExpandIcon).click();
			wait(3);
			driver.findElement(oipa_GC_Agreement_ClassGroupsLink).click();
			wait(3);
			/*
			 * Actions actions = new Actions(driver); WebElement HamburgerMenuIcon =
			 * driver.findElement(oipa_GC_ClassGroups_HamburgerMenuIcon);
			 * actions.moveToElement(HamburgerMenuIcon).perform(); wait(3);
			 * driver.findElement(oipa_GC_ClassGroups_Hamburger_GoToAgreement).click
			 * (); wait(5);
			 */
			driver.findElement(oipa_GC_Agreement_ClassGroups_AddDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_AddDD", 3));
			wait(3);
			driver.findElement(oipa_GC_Agreement_ClassGroups_AddButton).click();
			wait(4);
			driver.switchTo().activeElement();
			wait(4);
			// driver.findElement(oipa_GC_CopyClassGroups_AsofDateTxtBox).clear();
			// wait(3);
			// driver.findElement(oipa_GC_CopyClassGroups_RefreshButton).click();
			// wait(3);
			driver.findElement(oipa_GC_CopyClassGroups_ClassGroupCollapseIcon).click();
			wait(3);
			driver.findElement(oipa_GC_CopyClassGroups_ClassGroupSelectCheckbox).click();
			wait(3);
			driver.findElement(oipa_GC_CopyClassGroups_ClassGroupExpandIcon).click();
			wait(3);
			driver.findElement(oipa_GC_CopyClassGroups_NextButton).click();
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_TypeDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Type", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_StatusDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Status", 2));
			wait(3);
			String CopyClassGrpName = xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Name", 4)
					+ rand.nextInt(100);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupName).sendKeys(CopyClassGrpName);
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupDescription)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Description", 4));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_EffectiveFrom).click();
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_EffectiveFrom)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_EffectiveFrom", 4));
			wait(3);

			driver.findElement(oipa_GC_Agreement_AddClassGroups_ExpirationDate).click();
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_ExpirationDate)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_ExpirationDate", 4));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupSubType)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_ClassGroupSubType", 4)
							+ rand.nextInt(100));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupCriteria)
					.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_ClassGroupCriteria", 4)
							+ rand.nextInt(100));
			wait(3);
			driver.findElement(oipa_GC_CopyClassGroups_SaveButton).click();
			wait(4);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_SaveStatusDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClassGroup_SaveStatus", 2));
			wait(3);
			driver.findElement(oipa_GC_Agreement_AddClassGroups_agreementClassgroupSaveButton).click();
			wait(6);
			try {
				takeScreenShot(driver, "ClassGroup Copied " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (commonTableTextRetreive(driver, 2, 3).equals(CopyClassGrpName) && commonTableTextRetreive(driver, 2, 4)
					.equals(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClassGroup_SaveStatus", 2))) {
				System.out.println("Agreement Class Group is copied and saved as :" + " "
						+ commonTableTextRetreive(driver, 2, 3) + " " + commonTableTextRetreive(driver, 2, 4) + "  "
						+ commonTableTextRetreive(driver, 2, 5) + " " + commonTableTextRetreive(driver, 2, 6));
			} else {
				System.out.println("FAIL !-----Agreement Class Group is not copied Successfully");
				Assert.assertTrue(false);

			}
			wait(8);

		}

		@Test
		public void gc26_addplansegment_PlanSegmentTab() throws IOException {
			System.out.println("************************************************************************************");
			System.out.println("-----------******Creating Plan Segments under Plans Tab ******---------");
			wait(3);
			driver.findElement(oipa_GC_Plans_PlansLeftsideLink).click();
			wait(3);
			driver.findElement(oipa_GC_Plans_PlanRowExpandIcon).click();
			wait(3);
			driver.findElement(oipa_GC_plans_PlanSliceRowExpandIcon).click();
			wait(3);
			if (driver.findElement(oipa_GC_Plans_PlanSegmentsLink).isDisplayed()) {
				System.out.println("INFO !--------Plan Segments tab is visible after plans tab");
				wait(3);
				driver.findElement(oipa_GC_Plans_PlanSegmentsLink).click();
				wait(3);
				driver.findElement(oipa_GC_Plans_PlanSegments_AddSegmentButton).click();
				wait(3);
				driver.findElement(oipa_GC_Plans_PlanSegments_SegmentNameDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Plans_SegmentName", 2));
				wait(3);
				driver.findElement(oipa_GC_Plans_PlanSegments_PlanSegmentName)
						.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Plans_PlanSegmentName", 2));
				wait(3);
				driver.findElement(oipa_GC_Plans_PlanSegments_PlanSegmentTypeDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Plans_PlanSegmentType", 2));
				wait(3);
				driver.findElement(oipa_GC_Plans_PlanSegments_SaveButton).click();
				wait(6);
				try {
					takeScreenShot(driver, "Agreement PlanSegments Added " + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (driver.findElement(oipa_GC_Plans_PlanSegments_SegmentTextVerify).getText()
						.equals(xls.getCellData("GroupCustomer_2", "GC_Plans_PlanSegmentName", 2))) {
					System.out.println(" SUCCESS !------ Plan Segment is added successfully with plan segment name : "
							+ driver.findElement(oipa_GC_Plans_PlanSegments_SegmentTextVerify).getText());

				} else {
					System.out.println(" FAIL !------ Plan Segment is not added");
					Assert.assertTrue(false);

				}
			} else {
				System.out.println("INFO !--------Plan Segments tab is not visible after plans tab");
				Assert.assertTrue(false);
			}
			wait(8);

		}
		/*
		 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group
		 * Customer » Group Customer - Plans & Products » Bug -24619576- GC Plans
		 * and Plan Segments-II » TC-06 Verify user is able to delete the plan
		 * segment for a plan in Plan Segments tab of Plan Screen
		 */

		@Test
		public void gc27_deleteplansegment_PlanSegmentTab() {
			System.out.println("************************************************************************************");
			System.out.println("--------****** Deleting Plan Segment'******----------");
			wait(3);
			try {
				driver.findElement(oipa_GC_Plans_PlanSegments_HamburgerMenuIcon).click();
				wait(3);
				driver.findElement(oipa_GC_Plans_PlanSegments_DeleteButton).click();
				wait(3);
				System.out.println("SUCCESS ! --------- Plan Segment is deleted Successfully");
				try {
					takeScreenShot(driver, "Agreement Plan Segments Deleted" + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				System.out.println("FAIL ! --------- Plan Segment is not deleted Successfully");
			}
			wait(8);

		}

		@AfterClass
		public void closeBrowser() {
			closeBrowser(driver);
		}

}
