package com.tc.groupcustomer;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Library.OIPA_Library;

public class GC04_GroupCustomer_1 extends OIPA_Library {

	// Tester : Poornima
	// Functionality : GC_GoupCustomer-1

	public static WebDriver driver;
	public String alternateName;
	public String agreementName;
	Random rand;

	/* Method to retrieve data from the table for Relationship */
	public String tableTextRetreive(WebDriver driver, int rowno, int columnno) {
		String first_part = "//table/tbody/tr[";
		String second_part = "]/td[";
		String third_part = "]";
		String final_xpath = first_part + rowno + second_part + columnno + third_part;
		String tableData = driver.findElement(By.xpath(final_xpath)).getText();
		return tableData;
	}

	/* Method to retrieve buttons from the table for Relationship */
	public WebElement tableButtonRetreive(WebDriver driver, int rowno, int columnno, String buttonicon) {
		String first_part = "//table/tbody/tr[";
		String second_part = "]/td[";
		String third_part = "]/button[@title='";
		String fourth_part = "']";
		String final_xpath = first_part + rowno + second_part + columnno + third_part + buttonicon + fourth_part;
		WebElement buttonDisplay = driver.findElement(By.xpath(final_xpath));
		return buttonDisplay;
	}

	/* Method to retrieve data from the table for DataIntake, Product, Plan */
	public String commonTableTextRetreive(WebDriver driver, int rowno, int columnno) {
		ResourceBundle.clearCache();
		String first_part = "//table/tbody/tr[";
		String second_part = "]/td[";
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

	/* Method to retrieve buttons from the table for Products */
	public WebElement tableButtonRetreive_Product(WebDriver driver, int rowno, int columnno, String buttonicon) {
		String first_part = "//table/tbody/tr[";
		String second_part = "]/td[";
		String third_part = "]/span[@title='";
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

	/* Method to create AlternateNames with different status */
	public void createAlternateName(WebDriver driver, String status) {

		/* Creating GC AlternateName */
		driver.findElement(oipa_GC_addAlternateNameButton).click();
		wait(3);
		driver.findElement(oipa_GC_altIdTypeCode).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_altIdTypeCode", 2));
		wait(3);
		alternateName = xls.getCellData("GroupCustomer_1", "GC_AlternateName", 2) + rand.nextInt(1000);
		System.out.println("Added: ------------ Newly created Alternate Name is:" + " " + alternateName + "------------");
		driver.findElement(oipa_GC_altName).sendKeys(alternateName);
		wait(3);
		driver.findElement(oipa_GC_altIdEffectiveFrom).clear();
		wait(2);
		driver.findElement(oipa_GC_altIdEffectiveFrom).sendKeys(xls.getCellData("GroupCustomer_1", "GC_AltIdeffectiveFrom", 2));
		wait(3);
		driver.findElement(oipa_GC_altStatusDD).click();
		wait(3);
		selectItemInDropdown(driver, status);
		wait(3);
	}

	/* Method to Filter AlternateNames based on different status */
	public void filterAlternateName(WebDriver driver, String status) {

		List<WebElement> filterElements = driver.findElements(oipa_GC_alternateNameDisplayDDRemove);
		int filterElementCount = 0;
		while (filterElementCount < filterElements.size()) {
			driver.findElement(oipa_GC_alternateNameDisplayDDRemove).click();
			wait(3);
			filterElementCount++;
		}
		driver.findElement(oipa_GC_alternateNameDisplay).click();
		wait(3);
		selectItemInDropdown(driver, status);
		wait(3);
		driver.findElement(oipa_GC_altRefreshButton).click();
		wait(3);
	}

	/* Method to create DataIntake Profile */
	public void createDataIntakeProfile(WebDriver driver, String profileName) {
		System.out.println("Info!------------ Creating Data Intake new Profile ------------");
		driver.findElement(oipa_GC_dataIntake).click();
		wait(4);
		driver.findElement(oipa_GC_DI_addIntakeProfileDD).click();
		wait(3);
		selectItemInDropdown(driver, "Enrollment Prototype");
		wait(3);
		driver.findElement(oipa_GC_DI_addProfileButton).click();
		wait(4);
		driver.findElement(oipa_GC_DI_intakeProfileName).sendKeys(profileName);
		wait(4);
		driver.findElement(oipa_GC_DI_recordMemberIdField).sendKeys(xls.getCellData("GroupCustomer_1", "GC_DI_recordMemberIdField", 2));
		wait(4);
		driver.findElement(oipa_GC_DI_recordMemberIdFieldTypeCodeDD).click();
		wait(4);
		selectItemInDropdown(driver, "Client Field");
		wait(4);
		driver.findElement(oipa_GC_DI_effectiveFromDate).sendKeys(xls.getCellData("GroupCustomer_1", "GC_DI_effectiveFromDate", 2));
		wait(4);
		driver.findElement(oipa_GC_DI_saveButton).click();
		wait(4);
		System.out.println("New Intake Profile is added Successfully:" + " " + profileName);
	}

	/* Method to create runtime for screenshot */
	public String runDateTime(WebDriver driver) {

		Date currentDate = new Date();
		String dateToStr = DateFormat.getInstance().format(currentDate);
		dateToStr = dateToStr.replace("/", "-").replace(" ", "_").replace(":", ".");
		return dateToStr;
	}

	/*************************************** Creating Group Customer *******************************************************/

	/****
	 * OTM Test cases Hierarchy : Oracle Insurance Policy Administration » Functional » Group Customer » GroupCustomer Screen » Group Customer Screen - JET upgrade
	 * 
	 * @return
	 ****/

	/* TC:02 - Create a Group Customer by providing data in all fields */
	@BeforeClass
	public void CreateCustomer() throws Throwable {
		
		System.out.println("***************GC04 GroupCustomer_1******************************");


		openBrowser();
		driver = login(driver);
		rand = new Random();
		wait(5);

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GroupCustomerCreation_Prerequisites_TestBegins------------------");

		driver.findElement(oipa_createDD).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "oipa_createDD", 2));
		wait(2);
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
		driver.findElement(oipa_GC_legalResidenceCountryCodeDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_legalResidenceCountryCodeDD", 2));
		wait(3);
		driver.findElement(oipa_GC_primaryEnrollmentRelationshipDD).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_primaryEnrollmentRelationshipDD", 2));
		wait(2);
		driver.findElement(oipa_GC_enrollmentClassGroupDD).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_enrollmentClassGroupDD", 2));
		wait(2);
		driver.findElement(oipa_GC_hierarchyRelationshipDD).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_hierarchyRelationshipDD", 2));
		wait(2);
		driver.findElement(oipa_GC_originalEffectiveDate).clear();
		wait(2);
		driver.findElement(oipa_GC_originalEffectiveDate).sendKeys(xls.getCellData("GroupCustomer_1", "GC_originalEffectiveDate", 2));
		wait(3);
		driver.findElement(oipa_GC_customerLegalName).sendKeys(customerName);
		wait(3);
		driver.findElement(oipa_GC_customerStatusDD).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_customerStatusDD", 2));
		wait(3);
		driver.findElement(oipa_GC_savebutton).click();
		wait(3);
		String customerNameEntered = driver.findElement(oipa_GC_customerNameEntered).getText();
		System.out.println("Group Customer is Created: " + customerNameEntered);
		wait(4);

		System.out.println("Info! ---------GroupCustomerCreation_Prerequisites_TestEnds------------------");

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "CustomerCreation " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.findElement(oipa_GC_customerSummaryButton).click();
		wait(5);

	}

	/*********************************************** Alternate Name Test Scripts ***********************************************/

	/****
	 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group Customer » GroupCustomer Alt Name » Bug 24288458-GroupCustomer_AlternateName screen - JET upgrade
	 ****/

	/* TC:11 - Verify the functionality of Edit and Trash Icon */
	@Test
	public void GC01_AlternateNameFunctionality() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC01_AlternateNameFunctionality_TestBegins------------------");

		/* Creating GC AlternateName */
		System.out.println("Info!------------ Creating Group Customer Alternate Name ------------");
		driver.findElement(oipa_GC_alternateName).click();
		wait(4);
		createAlternateName(driver, xls.getCellData("GroupCustomer_1", "GC_altStatusActive", 2));
		driver.findElement(oipa_GC_altSaveButton).click();
		wait(3);
		Assert.assertEquals(driver.findElement(oipa_GC_altSavedName).getText(), alternateName);
		System.out.println("Added: GroupCustomer AlternateName is created successfully with alternateName as:" + alternateName + " ");

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "AlternateNameCreation " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* Editing the Active status record to Pending status using Edit Icon */

		System.out.println("Info!------------ Editing Group Customer Alternate Name using Edit Icon ------------");
		if (driver.findElement(oipa_GC_altEditButton).isDisplayed()) {
			driver.findElement(oipa_GC_altEditButton).click();
			wait(2);
			driver.findElement(oipa_GC_altModifiedName).clear();
			alternateName = xls.getCellData("GroupCustomer_1", "GC_AlternateName", 2) + rand.nextInt(1000);
			driver.findElement(oipa_GC_altModifiedName).sendKeys(alternateName);
			wait(3);
			driver.findElement(oipa_GC_altStatusDD).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_altStatusPending", 2));
			wait(3);
			driver.findElement(oipa_GC_altSaveButton).click();
			wait(3);
			Assert.assertEquals(driver.findElement(oipa_GC_altStatusPending).getText(), xls.getCellData("GroupCustomer_1", "GC_altStatusPending", 2));
			System.out.println("Updated:------------ Newly edited value of Status is:" + " " + driver.findElement(oipa_GC_altStatusPending).getText() + "------------");

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "AlternateNameEdition " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else {
			System.out.println("Fail!----- Edit Icon is NOT displayed");
			Assert.assertTrue(false);
		}

		/* Deleting the Alternate Name record using the Trash Icon */

		System.out.println("Info!------------ Deleting Group Customer Alternate Name using Trash Icon ------------");
		createAlternateName(driver, xls.getCellData("GroupCustomer_1", "GC_altStatusActive", 2));
		driver.findElement(oipa_GC_altDefaultRadioButton).click();
		wait(2);
		if (driver.findElement(oipa_GC_altDeleteButton).isDisplayed()) {

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "Before_AlternateNameDeletion " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			driver.findElement(oipa_GC_altDeleteButton).click();
			wait(3);
			driver.findElement(oipa_GC_altWarningOkButton).click();
			wait(3);
			System.out.println("Success! ------ Deleted Alternate Name using Trash Icon successfully");

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "AlternateNameDeletion " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Fail!----- Delete Icon is NOT displayed");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GC01_AlternateNameFunctionality_TestEnds------------------");
	}

	/* TC:12 - Functionality to filter the records as per the status */
	@Test
	public void GC02_AlternateNameFilters() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC02_AlternateNameFilters_TestBegins------------------");
		/* Creating Alternates with different Status */

		System.out.println("Info!------------ Creating Alternates with different Status ------------");

		createAlternateName(driver, xls.getCellData("GroupCustomer_1", "GC_altStatusActive", 2));
		driver.findElement(oipa_GC_altSaveButton).click();
		wait(3);
		System.out.println("Info!----- Created Alternates with Status" + xls.getCellData("GroupCustomer_1", "GC_altStatusActive", 2) + "-----");

		createAlternateName(driver, xls.getCellData("GroupCustomer_1", "GC_altStatusInactive", 2));
		driver.findElement(oipa_GC_altSaveButton).click();
		wait(3);
		System.out.println("Info!----- Created Alternates with Status" + xls.getCellData("GroupCustomer_1", "GC_altStatusInactive", 2) + "-----");

		createAlternateName(driver, xls.getCellData("GroupCustomer_1", "GC_altStatusDeleted", 2));
		driver.findElement(oipa_GC_altSaveButton).click();
		wait(3);
		System.out.println("Info!----- Created Alternates with Status" + xls.getCellData("GroupCustomer_1", "GC_altStatusDeleted", 2) + "-----");

		/* Filtering the above created AlternateNames based on Display filter */

		System.out.println("Info! ------------ Filtering the created AlternateNames based on Display filter ------------");

		filterAlternateName(driver, xls.getCellData("GroupCustomer_1", "GC_altStatusActive", 2));
		Assert.assertEquals(driver.findElement(oipa_GC_altStatusActive).getText(), xls.getCellData("GroupCustomer_1", "GC_altStatusActive", 2));
		System.out.println("The results of" + " " + xls.getCellData("GroupCustomer_1", "GC_altStatusActive", 2) + " " + "status filter results are displayed");

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "Active_AlternateName_Filtering " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}
		wait(6);

		filterAlternateName(driver, xls.getCellData("GroupCustomer_1", "GC_altStatusInactive", 2));
		Assert.assertEquals(driver.findElement(oipa_GC_altStatusInactive).getText(), xls.getCellData("GroupCustomer_1", "GC_altStatusInactive", 2));
		System.out.println("The results of" + " " + xls.getCellData("GroupCustomer_1", "GC_altStatusInactive", 2) + " " + "status filter results are displayed");

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "InActive_AlternateName_Filtering " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}
		wait(6);

		filterAlternateName(driver, xls.getCellData("GroupCustomer_1", "GC_altStatusDeleted", 2));
		Assert.assertEquals(driver.findElement(oipa_GC_altStatusDeleted).getText(), xls.getCellData("GroupCustomer_1", "GC_altStatusDeleted", 2));
		System.out.println("The results of" + " " + xls.getCellData("GroupCustomer_1", "GC_altStatusDeleted", 2) + " " + "status filter results are displayed");

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "Deleted_AlternateName_Filtering " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}
		wait(6);

		filterAlternateName(driver, xls.getCellData("GroupCustomer_1", "GC_altStatusPending", 2));
		Assert.assertEquals(driver.findElement(oipa_GC_altStatusPending).getText(), xls.getCellData("GroupCustomer_1", "GC_altStatusPending", 2));
		System.out.println("The results of" + " " + xls.getCellData("GroupCustomer_1", "GC_altStatusPending", 2) + " " + "status filter results are displayed");

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "Pending_AlternateName_Filtering " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}
		wait(6);

		System.out.println("Info! ---------GC02_AlternateNameFilters_TestEnds------------------");
	}

	/************************************************ DataIntake Test Scripts ***********************************************/

	/** Hierarchy : Oracle Insurance Policy Administration » Functional » Group Customer » Group Customer Data Intake » Bug 23758178 - IMPLEMENT DATA INTAKE PHASE I - JET UPGRADE **/

	/* TC 3: Verify user is able to add a new profile */
	@Test
	public void GC03_DataIntakeProfileCreation() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC03_DataIntakeProfileCreation_TestBegins------------------");

		
		createDataIntakeProfile(driver, xls.getCellData("GroupCustomer_1", "GC_DI_intakeProfileName", 2));

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "DataIntakeProfile_Creation " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(commonTableTextRetreive(driver, 1, 3), xls.getCellData("GroupCustomer_1", "GC_DI_intakeProfileName", 2));
		Assert.assertEquals(commonTableTextRetreive(driver, 1, 8), xls.getCellData("GroupCustomer_1", "GC_DI_status", 2));
		System.out.println(
				"Intake Profile is created with Profile Name: " + " " + commonTableTextRetreive(driver, 1, 3) + " Status is:" + "  " + commonTableTextRetreive(driver, 1, 8));
		driver.findElement(oipa_GC_DI_statusActiveRadioButton).click();
		wait(4);
		if (driver.findElement(oipa_GC_DI_noDataDisplayText).isDisplayed()) {
			System.out.println("Success!----------Intake Profile table data has: " + " '" + driver.findElement(oipa_GC_DI_noDataDisplayText).getText() + "'"
					+ " when Active status is selected");
		}
		else {
			System.out.println("Fail!----------Intake Profile has No profile data");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GC03_DataIntakeProfileCreation_TestEnds------------------");
	}

	/* TC 4:Verify the newly added profile is deleted */
	@Test
	public void GC04_DataIntakeProfileDeletion() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC04_DataIntakeProfileDeletion_TestBegins------------------");

		System.out.println("Info!-------- Deleting the Data Intake Profile -----------");
		driver.findElement(oipa_GC_DI_statusAllRadioButton).click();
		wait(4);
		if (tableButtonRetreive_DataIntake(driver, 1, 9, "Delete").isDisplayed()) {
			System.out.println("Info!----------Delete button is displayed under the Action column");
			tableButtonRetreive_DataIntake(driver, 1, 9, "Delete").click();
			wait(4);
			driver.switchTo().activeElement();
			driver.findElement(oipa_GC_DI_shadowWarningOKButton).click();
			wait(4);
			if (driver.findElement(oipa_GC_DI_noDataDisplayText).isDisplayed()) {
				System.out.println("Success!----------Intake Profile table data has: " + " '" + driver.findElement(oipa_GC_DI_noDataDisplayText).getText() + "'"
						+ " and the profile is deleted successfully");

				// Capturing the Screenshot
				try {
					takeScreenShot(driver, "DataIntakeProfile_Deletion " + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Fail!----------Intake Profile is NOT deleted");
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Fail!----------Delete button is NOT displayed under the Action column");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GC04_DataIntakeProfileDeletion_TestEnds------------------");

	}

	/* TC 5: Changing the Profile status and filtering the profiles */
	@Test
	public void GC05_DIStatusChangeFiltering() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC05_DIStatusChangeFiltering_TestBegins------------------");

		System.out.println("Info!------- Creating two Intake Profiles with 'Setup' Status --------------");
		createDataIntakeProfile(driver, xls.getCellData("GroupCustomer_1", "GC_DI_intakeProfileName", 2));
		createDataIntakeProfile(driver, xls.getCellData("GroupCustomer_1", "GC_DI_intakeProfileName", 3));
		Assert.assertEquals(commonTableTextRetreive(driver, 1, 3), xls.getCellData("GroupCustomer_1", "GC_DI_intakeProfileName", 2));
		Assert.assertEquals(commonTableTextRetreive(driver, 2, 3), xls.getCellData("GroupCustomer_1", "GC_DI_intakeProfileName", 3));
		System.out.println("Info!----------First Intake Profile is created with Profile Name: " + " " + commonTableTextRetreive(driver, 1, 3) + " Status is:" + "  "
				+ commonTableTextRetreive(driver, 1, 8));
		System.out.println("Info!----------Second Intake Profile is created with Profile Name: " + " " + commonTableTextRetreive(driver, 2, 3) + " Status is:" + "  "
				+ commonTableTextRetreive(driver, 2, 8));
		System.out.println("Info!------- Changing the Intake Profiles status from 'setup' to 'Active' status --------");
		for (int rowno = 1; rowno <= 2; rowno++) {
			String first_part = "//table/tbody/tr[";
			String second_part = "]/td[";
			String third_part = "2]/div";
			String oipa_GC_DI_RowExpander = first_part + rowno + second_part + third_part;
			driver.findElement(By.xpath(oipa_GC_DI_RowExpander)).click();
			wait(4);
			driver.findElement(oipa_GC_DI_activateButton).click();
			wait(5);
		}
		Assert.assertEquals(commonTableTextRetreive(driver, 1, 8), xls.getCellData("GroupCustomer_1", "GC_DI_status", 3));
		System.out.println("Info!----------New status of First Intake Profile: " + " " + commonTableTextRetreive(driver, 1, 3) + " is changed to:" + "  "
				+ commonTableTextRetreive(driver, 1, 8));
		Assert.assertEquals(commonTableTextRetreive(driver, 2, 8), xls.getCellData("GroupCustomer_1", "GC_DI_status", 3));
		System.out.println("Info!----------New status of Second Intake Profile: " + " " + commonTableTextRetreive(driver, 2, 3) + " is changed to:" + "  "
				+ commonTableTextRetreive(driver, 2, 8));
		System.out.println("Info!-------- Filtering and checking the results after selecting the 'Active' Status filter ------------");
		driver.findElement(oipa_GC_DI_statusActiveRadioButton).click();
		wait(4);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "Active_DataIntakeProfiles_Filtering " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(commonTableTextRetreive(driver, 1, 3), xls.getCellData("GroupCustomer_1", "GC_DI_intakeProfileName", 2));
		Assert.assertEquals(commonTableTextRetreive(driver, 2, 3), xls.getCellData("GroupCustomer_1", "GC_DI_intakeProfileName", 3));
		System.out.println("Info!----------Created Profiles are displayed in the table after selecting the filter status to 'Active' ");
		System.out.println("Info!------- Changing the Intake Profile status from 'Active' to 'Deactivate' status --------");
		driver.findElement(oipa_GC_DI_RowExpander).click();
		wait(4);
		driver.findElement(oipa_GC_DI_deactivateButton).click();
		wait(6);
		driver.findElement(oipa_GC_DI_RowExpander).click();
		wait(4);
		driver.findElement(oipa_GC_DI_deactivateButton).click();
		wait(6);
		System.out.println("Info!-------- Filtering and checking the results after selecting the 'Inactive' Status filter ------------");
		driver.findElement(oipa_GC_DI_statusInactiveRadioButton).click();
		wait(5);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "Inactive_DataIntakeProfiles_Filtering " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(commonTableTextRetreive(driver, 1, 3), xls.getCellData("GroupCustomer_1", "GC_DI_intakeProfileName", 2));
		Assert.assertEquals(commonTableTextRetreive(driver, 2, 3), xls.getCellData("GroupCustomer_1", "GC_DI_intakeProfileName", 3));
		System.out.println("Success!----------Created Profiles are displayed in the table after selecting the filter status to 'Inactive' ");

		System.out.println("Info! ---------GC05_DIStatusChangeFiltering_TestEnds------------------");

	}

	/************************************************ Agreement Test Scripts ***********************************************/

	/** Hierarchy : Oracle Insurance Policy Administration » Functional » Group Customer » Group Customer - Agreements » Bug 23597316- Agreements Screen-Phase I-JET Upgrade » **/

	/* TC-07 Verify that user is able to add Master Agreement successfully and a screen level message gets displayed */
	@Test
	public void GC06_ParentMasterAgreementCreation() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC06_ParentMasterAgreementCreation_TestBegins------------------");

		System.out.println("Info!-------- Creating 'Master Agreement Insured'  Contract Agreement ----------");

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

		if (driver.findElement(oipa_GC_agreementContractText).isDisplayed() && driver.findElement(oipa_GC_agreementDetailsText).isDisplayed()) {
			System.out.println("Success!----------'Details' section is displayed under the header:'Contract'");
		}
		else {
			System.out.println("Fail!----------'Details' section is NOT displayed under the header:'Contract'");
			Assert.assertTrue(false);
		}
		driver.findElement(oipa_GC_agreementTypeDD).click();
		wait(3);
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
		 * driver.findElement(oipa_GC_agreementUndefinedExpandIcon).click(); wait(3); driver.findElement(oipa_GC_agreementNumber) .sendKeys(xls.getCellData("GroupCustomer_1", "GC_agreementNumber",
		 * 2)); driver.findElement(oipa_GC_agreementName).sendKeys(Keys.TAB); wait(6); driver.findElement(oipa_GC_agreementUndefinedExpandIcon).click(); wait(3);
		 * driver.findElement(oipa_GC_agreementExperienceNumber) .sendKeys(xls.getCellData("GroupCustomer_1", "GC_agreementExperienceNumber", 2)); wait(4);
		 */
		driver.findElement(oipa_GC_agreementSaveButton).click();
		wait(3);
		Assert.assertEquals(driver.findElement(oipa_addMessage).getText(), "Agreement added successfully");

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "AgreementCreation " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}
		wait(3);

		// Finding the xpath of the agreement name
		String first_part = "//button[@aria-label='";
		String second_part = "']";
		String final_xpath = first_part + agreementName + second_part;

		if (driver.findElement(By.xpath(final_xpath)).getText().equals(agreementName + " " + "(" + agreementStatus + ")")) {
			System.out.println("Success!----------Newly added Agreement and status: " + driver.findElement(By.xpath(final_xpath)).getText()
					+ "is displayed under agreements type selection");
		}
		else {
			System.out.println("Fail!----------Newly added Agreement and status is NOT displayed under agreements type selection");
			Assert.assertTrue(false);
		}
		if (driver.findElement(oipa_GC_agreementAddAgmtName).getText().contains(agreementName + " " + "(" + agreementStatus + ")")) {
			System.out.println("Success!----------Updated Header is displayed as:  " + driver.findElement(oipa_GC_agreementAddAgmtName).getText());
		}
		else {
			System.out.println("Fail!----------Header is not updated as expected");
			Assert.assertTrue(false);
		}
		if (driver.findElement(oipa_GC_agreementContractCount).getText().equals(xls.getCellData("GroupCustomer_1", "GC_agreementCount", 2))) {
			System.out.println("Success!----------The Count of agreements added are : " + driver.findElement(oipa_GC_agreementContractCount).getText());
		}
		else {
			System.out.println("Fail!----------The count of agreements is NOT as expected");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GC06_ParentMasterAgreementCreation_TestEnds------------------");
	}

	// TC-09 Verify that user is able to add Child entity to Master Insured Agreement successfully
	// TC-13 Verify that user is not able to delete an active agreement
	@Test
	public void GC07_ChildMasterAgreementCreation() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC07_ChildMasterAgreementCreation_TestBegins------------------");

		System.out.println("Info!-------- Creating Child entity to 'Master Agreement Insured' Contract Agreement of ACTIVE Status----------");
		// Instantiate Action Class
		Actions actions = new Actions(driver);

		// Retrieve WebElement 'Master Agreement' to perform mouse hover
		String first_part = "//div[@id='tree']//button[@aria-label='";
		String second_part = "']";
		String final_xpath = first_part + agreementName + second_part;

		// Retrieving WebElement hamburgerMenuOpion
		WebElement hamburgerMenuOption = driver.findElement(By.xpath(final_xpath));

		// Mouse hover on hamburgerMenuOption displays (+) Plus icon
		actions.moveToElement(hamburgerMenuOption).perform();

		driver.findElement(oipa_GC_agreementContractParentPlusIcon).click();
		wait(4);
		driver.findElement(oipa_GC_agreementTypeDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_agreementContractTypeDD", 3));
		wait(4);
		driver.findElement(oipa_GC_agreementName).clear();
		wait(4);
		String agreementChildName = xls.getCellData("GroupCustomer_1", "GC_agreementNameMA_Child", 2);
		driver.findElement(oipa_GC_agreementName).sendKeys(agreementChildName);
		driver.findElement(oipa_GC_agreementName).sendKeys(Keys.TAB);
		wait(4);
		driver.findElement(oipa_GC_agreementStatusDD).click();
		wait(6);
		String agreementStatus = xls.getCellData("GroupCustomer_1", "GC_agreementStatusDD", 3);
		selectItemInDropdown(driver, agreementStatus);
		wait(3);
		driver.findElement(oipa_GC_agreementSaveButton).click();
		wait(3);
		Assert.assertEquals(driver.findElement(oipa_addMessage).getText(), "Agreement added successfully");
		wait(3);
		driver.findElement(oipa_GC_agreementMAExpandIcon).click();
		wait(5);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "ChildAgreement_Creation " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Finding the xpath of child agreement
		String first_part1 = "//button[@aria-label='";
		String second_part1 = "']";
		String final_xpath1 = first_part1 + agreementChildName + second_part1;

		if (driver.findElement(By.xpath(final_xpath1)).getText().equals(agreementChildName + " " + "(" + agreementStatus + ")")) {
			System.out.println("Success!----------Newly added Child Agreement and status: " + driver.findElement(By.xpath(final_xpath1)).getText() + " "
					+ "is displayed under agreements type selection");
		}
		else {
			System.out.println("Fail!----------Newly added Child Agreement and status is NOT displayed under agreements type selection");
			Assert.assertTrue(false);
		}
		if (driver.findElement(oipa_GC_agreementContractCount).getText().equals(xls.getCellData("GroupCustomer_1", "GC_agreementCount", 3))) {
			System.out.println("Success!----------The Count of agreements added are : " + driver.findElement(oipa_GC_agreementContractCount).getText());
		}
		else {
			System.out.println("Fail!----------The count of agreements is NOT as expected");
			Assert.assertTrue(false);
		}
		System.out.println("Info!---------- Checking the Trash Icon display for Active Agreement ------------");
		if (!driver.findElement(oipa_GC_agreementChildDeleteIcon).isDisplayed()) {
			System.out.println("Success!----------Delete/Trash Icon is not available for Active Child Agreement");
		}
		else {
			System.out.println("Fail!----------Delete/Trash Icon is available for Active Child Agreement");
			Assert.assertTrue(false);
		}
		System.out.println("Info!---------- Editing the Child Agreement status from 'Active' to 'Pending' ------------");
		driver.findElement(oipa_GC_agreementStatusDD).click();
		wait(6);
		String agreementPendingStatus = xls.getCellData("GroupCustomer_1", "GC_agreementStatusDD", 2);
		selectItemInDropdown(driver, agreementPendingStatus);
		wait(3);
		driver.findElement(oipa_GC_agreementSaveButton).click();
		wait(3);
		Assert.assertEquals(driver.findElement(oipa_addMessage).getText(), "Agreement modified successfully");
		wait(3);
		driver.findElement(oipa_GC_agreementMAExpandIcon).click();
		wait(5);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "ChildAgreement_Edition " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (driver.findElement(By.xpath(final_xpath1)).getText().equals(agreementChildName + " " + "(" + agreementPendingStatus + ")")) {
			System.out.println("Success!----------Edited Child Agreement and status: " + driver.findElement(By.xpath(final_xpath1)).getText() + " "
					+ "is displayed under agreements type selection");
		}
		else {
			System.out.println("Fail!----------Edited added Child Agreement and status is NOT displayed under agreements type selection");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC07_ChildMasterAgreementCreation_TestEnds------------------");
	}

	// TC-12 Verify that user is able to delete the agreements added to Master Insured Agreement
	@Test
	public void GC08_ChildMasterAgreementDeletion() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC08_ChildMasterAgreementDeletion_TestBegins------------------");

		System.out.println("Info!------------ Deleting the child agreement ------------");
		System.out.println("Before Deleting the agreement the present count of agreements: " + driver.findElement(oipa_GC_agreementContractCount).getText());
		driver.findElement(oipa_GC_agreementChildDeleteIcon).click();
		wait(4);
		driver.switchTo().activeElement();
		driver.findElement(oipa_GC_agreementDeleteOkButton).click();
		wait(4);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "ChildAgreement_Deletion " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (driver.findElement(oipa_GC_agreementContractCount).getText().equals(xls.getCellData("GroupCustomer_1", "GC_agreementCount", 2))) {
			System.out.println("Success!----------Deleted the child agreement successfully");
			System.out.println("After Deleting the agreement the count of agreements: " + driver.findElement(oipa_GC_agreementContractCount).getText());
		}
		else {
			System.out.println("Fail!----------Unable to Delete the child agreement and the count of agreements is NOT as expected");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC08_ChildMasterAgreementDeletion_TestEnds------------------");

	}

	/*********************************************** Agreement(Product) Test Scripts ***********************************************/

	/** Hierarchy : Oracle Insurance Policy Administration » Functional » Group Customer » Group Customer - Agreements » Bug 23742442 - Agreements phase II - jet upgrade » **/

	/* TC-06 Verify that products are added successfully */
	@Test
	public void GC09_ProductCreation() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC09_ProductCreation_TestBegins------------------");

		System.out.println("Info!------- Creating the Group Prototype Product ------------");

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
		driver.findElement(oipa_GC_productEffectivefromDate).sendKeys(xls.getCellData("GroupCustomer_1", "GC_productEffectivefromDate", 2));
		wait(4);
		driver.findElement(oipa_GC_productExpirationDate).sendKeys(xls.getCellData("GroupCustomer_1", "GC_productExpirationDate", 2));
		wait(4);
		driver.findElement(oipa_GC_productSaveButton).click();
		wait(5);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "GPP_ProductCreation " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (commonTableTextRetreive(driver, 1, 3).equals(xls.getCellData("GroupCustomer_1", "GC_productName", 2))
				&& commonTableTextRetreive(driver, 1, 4).equals(xls.getCellData("GroupCustomer_1", "GC_productStatusDD", 2))
				&& commonTableTextRetreive(driver, 1, 5).equals(xls.getCellData("GroupCustomer_1", "GC_productEffectivefromDate", 2))
				&& commonTableTextRetreive(driver, 1, 6).equals(xls.getCellData("GroupCustomer_1", "GC_productExpirationDate", 2))) {
			System.out.println("Product in the table is displayed as:" + " " + commonTableTextRetreive(driver, 1, 3) + " " + commonTableTextRetreive(driver, 1, 4) + "  "
					+ commonTableTextRetreive(driver, 1, 5) + " " + commonTableTextRetreive(driver, 1, 6));
			System.out.println("Success!----------Group Prototype Product is added successfully");
		}
		else {
			System.out.println("Fail!----------Group Prototype Product is Not Created and No data in table exists");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC09_ProductCreation_TestEnds------------------");
	}

	/* TC-07 Verify that user is able to add Child product successfully */
	@Test
	public void GC10_ChildProductCreation() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC10_ChildProductCreation_TestBegins------------------");

		System.out.println("Info!------- Creating child product 'TERM Products' ------------");

		driver.findElement(oipa_GC_productAddProductButton).click();
		wait(4);
		driver.switchTo().activeElement();
		driver.findElement(oipa_GC_productTermProductlink).click();
		wait(4);
		driver.findElement(oipa_GC_productAddProductOkButton).click();
		wait(4);
		driver.findElement(oipa_GC_productStatusDD).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_productStatusDD", 2));
		wait(4);
		driver.findElement(oipa_GC_productEffectivefromDate).sendKeys(xls.getCellData("GroupCustomer_1", "GC_productEffectivefromDate", 2));
		wait(4);
		driver.findElement(oipa_GC_productExpirationDate).sendKeys(xls.getCellData("GroupCustomer_1", "GC_productExpirationDate", 2));
		wait(4);
		driver.findElement(oipa_GC_productSaveButton).click();
		wait(5);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "'TERM Products'_ChildProductCreation " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (commonTableTextRetreive(driver, 2, 3).equals(xls.getCellData("GroupCustomer_1", "GC_childProductName", 2))
				&& commonTableTextRetreive(driver, 2, 4).equals(xls.getCellData("GroupCustomer_1", "GC_productStatusDD", 2))
				&& commonTableTextRetreive(driver, 2, 5).equals(xls.getCellData("GroupCustomer_1", "GC_productEffectivefromDate", 2))
				&& commonTableTextRetreive(driver, 2, 6).equals(xls.getCellData("GroupCustomer_1", "GC_productExpirationDate", 2))) {
			System.out.println("Info!----------Child Product in the table is displayed as:" + " " + commonTableTextRetreive(driver, 2, 3) + " "
					+ commonTableTextRetreive(driver, 2, 4) + "  " + commonTableTextRetreive(driver, 2, 5) + " " + commonTableTextRetreive(driver, 2, 6));
			System.out.println("Success!----------Child Product is added successfully");
		}
		else {
			System.out.println("Fail!----------Child Product is Not Created and No data in table exists");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC10_ChildProductCreation_TestEnds------------------");

	}

	/* TC-10 Verify the user is able to edit the product details by edit icon after product addition */
	@Test
	public void GC11_ProductEdition() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC11_ProductEdition_TestBegins------------------");

		System.out.println("Info!------- Editing the Product details using Edit Icon ---------");

		System.out.println("The Expiration date of" + " " + commonTableTextRetreive(driver, 1, 3) + " before editing is " + commonTableTextRetreive(driver, 1, 6));
		if (tableButtonRetreive_Product(driver, 1, 7, "Edit").isDisplayed()) {
			System.out.println("Info!----------clicking on Edit Icon");
			tableButtonRetreive_Product(driver, 1, 7, "Edit").click();
			wait(4);
			driver.findElement(oipa_GC_productExpirationDate).clear();
			driver.findElement(oipa_GC_productExpirationDate).sendKeys(xls.getCellData("GroupCustomer_1", "GC_productExpirationDateModified", 2));
			wait(4);
			driver.findElement(oipa_GC_productSaveButton).click();
			wait(5);

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "GPP_ProductEdition " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (commonTableTextRetreive(driver, 1, 6).equals(xls.getCellData("GroupCustomer_1", "GC_productExpirationDateModified", 2))) {
				System.out.println("The Expiration date of" + " " + commonTableTextRetreive(driver, 1, 3) + " after editing is " + commonTableTextRetreive(driver, 1, 6));
			}
			else {
				System.out.println("Success!----------Expiration Data is NOT updated after clicking on Edit Icon");
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Fail!----------Edit Icon is not displayed");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC11_ProductEdition_TestEnds------------------");
	}

	/* TC-11 Verify the user is able to Delete the product details by delete icon after product addition */
	@Test
	public void GC12_ProductDeletion() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC12_ProductDeletion_TestBegins------------------");

		System.out.println("Info!----------- Deleting the Child Product -------------");

		if (tableButtonRetreive_Product(driver, 2, 7, "Delete").isDisplayed()) {
			System.out.println("Info!----------Clicking on Delete Icon of TERM Product");
			tableButtonRetreive_Product(driver, 2, 7, "Delete").click();
			wait(4);
			driver.switchTo().activeElement();
			driver.findElement(oipa_GC_productDeleteOKButton).click();
			wait(4);

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "'TERM Products'_ChildProductDeletion " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!driver.findElement(oipa_GC_prodcuttabledata).getText().contains(xls.getCellData("GroupCustomer_1", "GC_childProductName", 2))) {
				System.out.println("Success!----------Child Product - 'TERM Product' is deleted successfully");
			}
			else {
				System.out.println("Fail!----------Child Product - 'TERM Product' is NOT deleted");
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Fail!----------Delete Icon is not displayed");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GC12_ProductDeletion_TestEnds------------------");
	}

	/*********************************************** Agreement(Plan) Test Scripts ***********************************************/

	/** Hierarchy : Oracle Insurance Policy Administration » Functional » Group Customer » Group Customer - Agreements » Bug 24315707 - Agreements Phase III -Jet Upgrade » **/

	/* TC-05 Verify that user is able to save the details in dialog box and able to save a plan */
	/* TC-15 Verify that user is able to edit and delete the Plan Segment added in plansegmentdetails */
	@Test
	public void GC13_PlanCreation() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC13_PlanCreation_TestBegins------------------");

		System.out.println("Info!-----------Creating Plan under Agreements section ---------");

		System.out.println("Info!----------- Creating a Plan with edit and delete of a Plan Segment  -----------");

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
		driver.findElement(oipa_GC_planEffectiveFrom).sendKeys(xls.getCellData("GroupCustomer_1", "GC_planEffectiveFrom", 2));
		driver.findElement(oipa_GC_planEffectiveFrom).sendKeys(Keys.TAB);
		wait(6);
		driver.findElement(oipa_GC_planCurrencyDD).click();
		wait(6);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planCurrencyDD", 2));
		wait(6);
		driver.findElement(oipa_GC_planExpirationDatePopUp).sendKeys(xls.getCellData("GroupCustomer_1", "GC_planExpirationDate", 2));
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
		System.out.println("Info!--------- Adding 'Base Coverage Plus' segment to the Plan ----------");
		driver.findElement(oipa_GC_planSegmentNameDD).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentNameDD", 2));
		wait(6);
		driver.findElement(oipa_GC_planSegmentName).sendKeys(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 2));
		wait(4);
		driver.findElement(oipa_GC_planSegmentName).sendKeys(Keys.TAB);
		wait(2);
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
		System.out.println("Info!--------- Adding 'Base Coverage Basic' segment to the Plan ----------");
		driver.findElement(oipa_GC_planSegmentNameDD).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentNameDD", 3));
		wait(6);
		driver.findElement(oipa_GC_planSegmentName).sendKeys(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 3));
		wait(4);
		driver.findElement(oipa_GC_planSegmentName).sendKeys(Keys.TAB);
		wait(2);
		driver.findElement(oipa_GC_planSegmentTypeDD).click();
		wait(6);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentTypeDD", 3));
		wait(6);
		driver.findElement(oipa_GC_planProcessAddSegment).click();
		wait(4);
		if (commonTableTextRetreive(driver, 1, 3).equals(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 2))
				&& commonTableTextRetreive(driver, 1, 4).equals(xls.getCellData("GroupCustomer_1", "GC_planSegmentNameDD", 2))
				&& commonTableTextRetreive(driver, 2, 3).equals(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 3))
				&& commonTableTextRetreive(driver, 2, 4).equals(xls.getCellData("GroupCustomer_1", "GC_planSegmentNameDD", 3))) {
			System.out.println("Segment Details are saved in tabular format successfully");
			System.out.println("Segments details in the table for first row are displayed as:" + " " + commonTableTextRetreive(driver, 1, 3) + " "
					+ commonTableTextRetreive(driver, 1, 4));
			System.out.println("Segments details in the table for second row are displayed as:" + " " + commonTableTextRetreive(driver, 2, 3) + " "
					+ commonTableTextRetreive(driver, 2, 4));
			System.out.println("Info!--------- Editing 'Base Coverage Basic' segment details ----------");
			System.out.println("Before Editing, Plan Segment Name for 'BaseCoverageBasic' Segment details is" + " " + commonTableTextRetreive(driver, 2, 3));
			driver.findElement(oipa_GC_planSegmentExpand).click();
			wait(3);
			driver.findElement(oipa_GC_planSegmentName).clear();
			wait(2);
			driver.findElement(oipa_GC_planSegmentName).sendKeys(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 4));
			wait(4);
			driver.findElement(oipa_GC_planSegmentName).sendKeys(Keys.TAB);
			wait(2);
			driver.findElement(oipa_GC_planSegmentTypeDD).click();
			wait(6);
			selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentTypeDD", 2));
			wait(6);
			driver.findElement(oipa_GC_planProcessAddSegment).click();

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "PlansSegment_Creation " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			wait(4);
			if (commonTableTextRetreive(driver, 2, 3).equals(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 4))) {
				System.out.println(" After Editing, Plan Segment Name for 'BaseCoverageBasic' Segment details is updated as" + " " + commonTableTextRetreive(driver, 2, 3));
				System.out.println("Success!----------Segment details are updated successfully");
			}
			else {
				System.out.println("Fail!----------Segment details are NOT updated properly");
				Assert.assertTrue(false);
			}
			System.out.println("Info!--------- Deleting 'Base Coverage Basic' segment details ----------");
			tableExpandRetreive_Plan(driver, 2, 5).click();
			wait(4);
			driver.switchTo().activeElement();
			driver.findElement(oipa_GC_planSegmentWarningOK).click();
			wait(4);
			if (!driver.findElement(oipa_GC_planSegmentTable).getText().contains(xls.getCellData("GroupCustomer_1", "GC_planSegmentTypeDD", 2))) {
				System.out.println("Success!----------'Base Coverage Basic' segment is deleted successfully");
				System.out.println("The plan segment table data is displayed as" + " " + driver.findElement(oipa_GC_planSegmentTable).getText());
			}
			else {
				System.out.println("Fail!----------'Base Coverage Basic' segment is NOT deleted");
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Fail!----------Segment Details are NOT saved in tabular format");
			Assert.assertTrue(false);
		}
		driver.findElement(oipa_GC_planProcessSaveAddPlan).click();
		wait(4);
		driver.findElement(oipa_GC_planBusinessStatusDD).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planBusinessStatusDD", 2));
		wait(6);
		driver.findElement(oipa_GC_planSaveButton).click();
		wait(3);
		Assert.assertEquals(driver.findElement(oipa_addMessage).getText(), "Plan added successfully");
		driver.findElement(oipa_GC_plan_RefreshBtn).click();
		wait(4);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "Plan_Creation " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (commonTableTextRetreive(driver, 1, 3).equals(planName)
				&& commonTableTextRetreive(driver, 1, 4).equals(xls.getCellData("GroupCustomer_1", "GC_planBusinessStatusDD", 2))
				&& commonTableTextRetreive(driver, 1, 5).equals(xls.getCellData("GroupCustomer_1", "GC_planEffectiveFrom", 2))
				&& commonTableTextRetreive(driver, 1, 6).equals(xls.getCellData("GroupCustomer_1", "GC_planExpirationDate", 2))) {
			System.out.println("Plan details in the table are displayed as:" + " " + commonTableTextRetreive(driver, 1, 3) + " " + commonTableTextRetreive(driver, 1, 4) + "  "
					+ commonTableTextRetreive(driver, 1, 5) + " " + commonTableTextRetreive(driver, 1, 6));
			System.out.println("Success!----------Plan is added successfully after editing and deleting the plan segments");
		}
		else {
			System.out.println("Fail!----------Plan is Not Created and No data in table exists");
			Assert.assertTrue(false);
		}

		System.out.println("Info! ---------GC13_PlanCreation_TestEnds------------------");

	}

	/* TC-11 Verify the user is able to edit the plan details clicking on edit from hamburger menu corresponding to record saved. */
	@Test
	public void GC14_PlanEdition() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC14_PlanEdition_TestBegins------------------");

		System.out.println("Info!----------- Editing the Plan status and Expiration Date -----------");
		System.out.println("Plan Status and Expiration date before Editing are" + " " + commonTableTextRetreive(driver, 1, 4) + "  " + commonTableTextRetreive(driver, 1, 6));

		// Instantiate Action Class
		Actions actions = new Actions(driver);

		// Retrieve WebElement 'hamburger Menu' to perform mouse hover
		WebElement hamburgerMenuOption = (tableExpandRetreive_Plan(driver, 1, 7));

		// Mouse hover on hamburgerMenuOption displays Edit option
		actions.moveToElement(hamburgerMenuOption).perform();
		wait(5);

		driver.findElement(oipa_GC_planEdit).click();
		wait(12);
		driver.findElement(oipa_GC_planExpirationDate).clear();
		wait(3);
		driver.findElement(oipa_GC_planExpirationDate).sendKeys(xls.getCellData("GroupCustomer_1", "GC_planExpirationDateModified", 2));
		wait(4);
		driver.findElement(oipa_GC_planBusinessStatusDD).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planBusinessStatusDD", 3));
		wait(6);
		driver.findElement(oipa_GC_planSaveButton).click();
		wait(4);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "Plan_Edition " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (commonTableTextRetreive(driver, 1, 4).equals(xls.getCellData("GroupCustomer_1", "GC_planBusinessStatusDD", 3))
				&& commonTableTextRetreive(driver, 1, 6).equals(xls.getCellData("GroupCustomer_1", "GC_planExpirationDateModified", 2))) {
			System.out
					.println("Plan Status and Expiration date after Editing are" + " " + commonTableTextRetreive(driver, 1, 4) + "  " + commonTableTextRetreive(driver, 1, 6));
			System.out.println("Success!----------Plan data is edited successfully and displayed correctly in the table");
		}
		else {
			System.out.println("Fail!----------Plan data is NOT edited and NOT displayed correctly in the table");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC14_PlanEdition_TestEnds------------------");
	}

	/*********************************************** Plans Test Scripts ***********************************************/

	/** Hierarchy: Oracle Insurance Policy Administration»Functional»Group Customer» Group Customer-Plans&Products» Bug-24493637 GC Plans Phase I- JET upgrade» ***/

	/* TC-06 Verify that user is able to save the plan details from plan screen and make it active by submitting it through Blue paper icon in action. */
	@Test
	public void GC15_PlanScreen_Activation() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC15_PlanScreen_Activation_TestBegins------------------");

		System.out
				.println("Info!-------- Navigating to Plan Screen by clicking on Plans link in left navigation,Editing the Plan Name and Activating the Plan in Plans Screen");
		driver.findElement(oipa_GC_plan).click();
		wait(4);
		driver.findElement(oipa_GC_plan_expandIcon).click();
		wait(4);
		System.out.println("Plan data in Plan page table is displayed as:" + " " + " " + commonTableTextRetreive(driver, 1, 4) + "  " + commonTableTextRetreive(driver, 1, 5)
				+ " " + commonTableTextRetreive(driver, 1, 6) + "  " + commonTableTextRetreive(driver, 1, 7));

		/*
		 * if (planNameInAgreementPlan.equals(planNameInPlans)) { System.out. println("Success!------The Plan Name in Agreements Plan screen data and Plans screen are same" ); } else { System.out.
		 * println("Fail!------- The Plan Name in Agreements Plan screen data and Plans screen are NOT same" ); Assert.assertTrue(false); }
		 */
		tableExpandRetreive_Plan(driver, 1, 2).click();
		wait(4);
		driver.findElement(oipa_GC_plan_name).clear();
		wait(2);
		driver.findElement(oipa_GC_plan_name).sendKeys(xls.getCellData("GroupCustomer_1", "GC_plan_NameUpdated", 2));
		wait(5);
		driver.findElement(oipa_GC_plan_name).sendKeys(Keys.TAB);
		driver.findElement(oipa_GC_plan_save).click();
		wait(4);
		if (commonTableTextRetreive(driver, 1, 4).equals(xls.getCellData("GroupCustomer_1", "GC_plan_NameUpdated", 2))) {
			System.out.println("Success!-------The Plan name is updated successfully");
		}
		else {
			System.out.println("Fail!-------The Plan name is NOT updated uccessfully");
			Assert.assertTrue(false);
		}
		if (tableButtonRetreive(driver, 1, 8, "Submit").isDisplayed()) {
			System.out.println("Plan status before clicking on Blue Paper arrow which is displayed in Action Column:" + "  " + commonTableTextRetreive(driver, 1, 7));
			System.out.println("Info!------- Clicking on Submit Icon followed by Click on 'Cancel' button on Alert Pop up");
			tableButtonRetreive(driver, 1, 8, "Submit").click();
			wait(4);
			driver.switchTo().activeElement();
			driver.findElement(oipa_GC_plan_alertCancelbutton).click();
			wait(5);
			if (commonTableTextRetreive(driver, 1, 7).equals(xls.getCellData("GroupCustomer_1", "GC_plan_RecordStatus", 2))) {
				System.out.println("Success!------User is navigated to Plan Screen");
			}
			else {
				System.out.println("Fail!--------User is in Alert Pop up");
				Assert.assertTrue(false);
			}
			System.out.println("Info!------- Clicking on Submit Icon followed by Click on 'OK' button on Alert Pop up");

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "PlanStatus_BeforeActivation " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			tableButtonRetreive(driver, 1, 8, "Submit").click();
			wait(4);
			driver.switchTo().activeElement();
			driver.findElement(oipa_GC_plan_alertOkbutton).click();
			wait(5);
			tableExpandRetreive_Plan(driver, 1, 2).click();
			wait(4);
			if (!driver.findElement(oipa_GC_plan_name).isEnabled()) {
				System.out.println("Success!-------The Plan fields are disabled");
			}
			else {
				System.out.println("Fail!-------The Plan fields are NOT disabled");
				Assert.assertTrue(false);
			}
			Assert.assertEquals(commonTableTextRetreive(driver, 1, 7), xls.getCellData("GroupCustomer_1", "GC_plan_RecordStatus", 3));
			System.out.println("Plan status after clicking on Blue Paper arrow is:" + "  " + commonTableTextRetreive(driver, 1, 7));
			tableButtonRetreive(driver, 1, 8, "Process").click();
			wait(5);

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "PlanStatus_AfterActivation " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Assert.assertEquals(commonTableTextRetreive(driver, 1, 7), xls.getCellData("GroupCustomer_1", "GC_plan_RecordStatus", 4));
			System.out.println("Success!------Plan status after clicking on process Icon is:" + "  " + commonTableTextRetreive(driver, 1, 7));
		}
		else {
			System.out.println("Fail!------Blue Paper arrow is NOT displayed under Action Column ");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC15_PlanScreen_Activation_TestEnds------------------");

	}

	/* TC-08 Verify that Edit icon works fine */
	@Test
	public void GC16_PlanScreen_EditIconCheck() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC16_PlanScreen_EditIconCheck_TestBegins------------------");

		System.out.println("Info!---------Checking the Edit Icon functionality-------");

		System.out.println("Info!---------Clicking on Edit Icon under Action Column in Plan screen");
		tableButtonRetreive(driver, 1, 8, "Edit").click();
		wait(4);
		driver.findElement(oipa_GC_plan_maximumFaceAmountTextField).sendKeys(xls.getCellData("GroupCustomer_1", "GC_plan_maximumFaceAmountTextField", 2));
		wait(4);
		driver.findElement(oipa_GC_plan_save).click();
		wait(6);
		if (tableButtonRetreive(driver, 1, 8, "Submit").isDisplayed() && tableButtonRetreive(driver, 2, 8, "Edit").isDisplayed()) {
			System.out.println("Plan status for the newly created record is :" + "  " + commonTableTextRetreive(driver, 1, 7));
			System.out.println("Plan status of the old created record is :" + "  " + commonTableTextRetreive(driver, 2, 7));
			System.out.println("Info!------- Clicking on Submit Icon for the new record followed by Click on 'OK' button on Alert Pop up");
			tableButtonRetreive(driver, 1, 8, "Submit").click();
			wait(4);
			driver.switchTo().activeElement();
			Assert.assertEquals(driver.findElement(oipa_GC_plan_warningMessage).getText(), xls.getCellData("GroupCustomer_1", "GC_plan_warningMessage", 2));
			System.out.println(" The Warning message in the pop up is displayed as:" + " " + driver.findElement(oipa_GC_plan_warningMessage).getText());
			driver.findElement(oipa_GC_plan_alertOkbutton).click();
			wait(5);

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "IconsDisplay_NewRecord " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (tableButtonRetreive(driver, 1, 8, "Process").isDisplayed() && tableButtonRetreive(driver, 1, 8, "Revert to Draft").isDisplayed()
					&& tableButtonRetreive(driver, 1, 8, "Delete").isDisplayed()) {
				System.out.println("Success!---------'Process', 'Revert to Draft', and 'Delete' buttons are dispalyed for the new record ");
			}
			else {
				System.out.println("Fail!---------'Process', 'Revert to Draft', and 'Delete' buttons are NOT dispalyed for the new record ");
				Assert.assertTrue(false);
			}
			tableButtonRetreive(driver, 1, 8, "Process").click();
			wait(5);
			System.out.println("Info!------Plan status after clicking on process Icon of the new record is:" + "  " + commonTableTextRetreive(driver, 1, 7));
			tableExpandRetreive_Plan(driver, 1, 2).click();
			wait(4);

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "MaxFaceAmount_AfterEditing " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (driver.findElement(oipa_GC_plan_maximumFaceAmountTextField).getAttribute("aria-valuenow")
					.equals(xls.getCellData("GroupCustomer_1", "GC_plan_maximumFaceAmountTextField", 2))) {
				System.out.println("Success!---------- Maximum Face amount value is updated as:" + " "
						+ driver.findElement(oipa_GC_plan_maximumFaceAmountTextField).getAttribute("aria-valuenow"));
			}
			else {
				System.out.println("Fail!----------Maximum Face amount value is NOT updated");
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Fail!--------- 'Edit' button is NOT Displayed for the old record ");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC16_PlanScreen_EditIconCheck_TestEnds------------------");

	}

	/* TC-09 Verify that Trash icon works fine */
	@Test
	public void GC17_PlanScreen_TrashIconCheck() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC17_PlanScreen_TrashIconCheck_TestBegins------------------");

		System.out.println("Info!---------Checking the Trash Icon functionality-------");
		System.out.println("Info!---------Clicking on Edit Icon under Action Column in Plan screen");
		tableButtonRetreive(driver, 1, 8, "Edit").click();
		wait(4);
		driver.findElement(oipa_GC_plan_maximumFaceAmountTextField).clear();
		driver.findElement(oipa_GC_plan_maximumFaceAmountTextField).sendKeys(xls.getCellData("GroupCustomer_1", "GC_plan_maximumFaceAmountTextField", 3));
		wait(4);
		driver.findElement(oipa_GC_plan_save).click();
		wait(6);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "BeforeClick_TrashIcon " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		wait(6);
		if (tableButtonRetreive(driver, 1, 8, "Submit").isDisplayed() && tableButtonRetreive(driver, 1, 8, "Delete").isDisplayed()) {
			System.out.println("Plan status for the newly created record is :" + "  " + commonTableTextRetreive(driver, 1, 7));
			System.out.println("Icons displayed for the newly created record are:" + "  " + tableButtonRetreive(driver, 1, 8, "Submit").getAttribute("title") + "and"
					+ tableButtonRetreive(driver, 1, 8, "Delete").getAttribute("title"));
			System.out.println("Info!------- Clicking on Trash Icon");
			tableButtonRetreive(driver, 1, 8, "Delete").click();
			wait(4);
			driver.switchTo().activeElement();
			Assert.assertEquals(driver.findElement(oipa_GC_plan_warningMessage).getText(), xls.getCellData("GroupCustomer_1", "GC_plan_warningMessage", 3));
			System.out.println(" The Warning message in the pop up is displayed as:" + " " + driver.findElement(oipa_GC_plan_warningMessage).getText());
			System.out.println("Info!------- Clicking on 'Cancel' button on Alert Pop up");
			driver.findElement(oipa_GC_plan_alertCancelbutton).click();
			wait(5);
			if (commonTableTextRetreive(driver, 1, 7).equals(xls.getCellData("GroupCustomer_1", "GC_plan_RecordStatus", 2))) {
				System.out.println("Success!------User is navigated to Plan Screen");
			}
			else {
				System.out.println("Fail!--------User is in Alert Pop up");
				Assert.assertTrue(false);
			}
			System.out.println("Info!------- Clicking on Trash Icon followed by Click on 'OK' button on Alert Pop up");
			tableButtonRetreive(driver, 1, 8, "Delete").click();
			wait(4);
			driver.switchTo().activeElement();
			driver.findElement(oipa_GC_plan_alertOkbutton).click();
			wait(10);

			// Capturing the Screenshot
			try {
				takeScreenShot(driver, "AfterClick_TrashIcon " + runDateTime(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Info!------No.of records after clicking on Trash Icon followed by 'OK' and the Plans status of the new record ");

			// To get the total number of rows in the table

			WebElement TogetRows = driver.findElement(oipa_GC_plan_tabledata);

			List<WebElement> TotalRowsList = TogetRows.findElements(By.tagName("tr"));

			Assert.assertEquals(TotalRowsList.size(), Integer.parseInt(xls.getCellData("GroupCustomer_1", "GC_plan_rowcount", 2)));
			System.out.println("Success!-------Total number of records in the table are : " + TotalRowsList.size() + " and the status of the record is:  "
					+ commonTableTextRetreive(driver, 1, 7));
		}
		else {
			System.out.println("Fail!--------- 'Submit' and 'Delete' buttons are NOT Displayed for the new record ");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC17_PlanScreen_TrashIconCheck_TestEnds------------------");

	}

	/* TC-10 Verify that Reverse icon works fine */
	@Test
	public void GC18_PlanScreen_ReverseIconCheck() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC18_PlanScreen_ReverseIconCheck_TestBegins------------------");

		/*
		 * driver.findElement(oipa_GC_plan).click(); wait(4);
		 * 
		 * driver.findElement(oipa_GC_plan_expandIcon).click(); wait(4);
		 */
		System.out.println("Info!---------Checking the Reverse Icon functionality-------");
		System.out.println("Info!---------Clicking on Edit Icon under Action Column in Plan screen");

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "BeforeClick_ReverseIcon " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		tableButtonRetreive(driver, 1, 8, "Edit").click();
		wait(4);
		driver.findElement(oipa_GC_plan_maximumFaceAmountTextField).clear();
		driver.findElement(oipa_GC_plan_maximumFaceAmountTextField).sendKeys(xls.getCellData("GroupCustomer_1", "GC_plan_maximumFaceAmountTextField", 4));
		wait(4);
		driver.findElement(oipa_GC_plan_save).click();
		wait(6);
		if (tableButtonRetreive(driver, 1, 8, "Submit").isDisplayed() && tableButtonRetreive(driver, 1, 8, "Delete").isDisplayed()) {
			System.out.println("Plan status for the newly created record is :" + "  " + commonTableTextRetreive(driver, 1, 7));
			System.out.println("Icons displayed for the newly created record are:" + "  " + tableButtonRetreive(driver, 1, 8, "Submit").getAttribute("title") + "and"
					+ tableButtonRetreive(driver, 1, 8, "Delete").getAttribute("title"));
			System.out.println("Info!------- Clicking on Blue Paper Icon");
			tableButtonRetreive(driver, 1, 8, "Submit").click();
			wait(4);
			driver.switchTo().activeElement();
			driver.findElement(oipa_GC_plan_alertOkbutton).click();
			wait(5);
			Assert.assertEquals(commonTableTextRetreive(driver, 1, 7), xls.getCellData("GroupCustomer_1", "GC_plan_RecordStatus", 3));
			System.out.println("Plan status after clicking on Blue Paper arrow for the newly created record is:" + "  " + commonTableTextRetreive(driver, 1, 7));
			if (tableButtonRetreive(driver, 1, 8, "Process").isDisplayed() && tableButtonRetreive(driver, 1, 8, "Revert to Draft").isDisplayed()
					&& tableButtonRetreive(driver, 1, 8, "Delete").isDisplayed()) {
				System.out.println("Success!---------'Process', 'Revert to Draft', and 'Delete' buttons are dispalyed for the new record ");
				System.out.println("Info!--------- Clicking on Green Reverse Arrow i.e.,'Revert to Draft' Icon ");
				tableButtonRetreive(driver, 1, 8, "Revert to Draft").click();
				wait(4);
				driver.switchTo().activeElement();
				Assert.assertEquals(driver.findElement(oipa_GC_plan_warningMessage).getText(), xls.getCellData("GroupCustomer_1", "GC_plan_warningMessage", 4));
				System.out.println(" The Warning message in the pop up is displayed as:" + " " + driver.findElement(oipa_GC_plan_warningMessage).getText());
				driver.findElement(oipa_GC_plan_alertCancelbutton).click();
				wait(5);
				if (commonTableTextRetreive(driver, 1, 7).equals(xls.getCellData("GroupCustomer_1", "GC_plan_RecordStatus", 3))) {
					System.out.println("Success!------User is navigated to Plan Screen");
				}
				else {
					System.out.println("Fail!--------User is in Alert Pop up");
					Assert.assertTrue(false);
				}
				tableButtonRetreive(driver, 1, 8, "Revert to Draft").click();
				wait(4);
				driver.switchTo().activeElement();
				driver.findElement(oipa_GC_plan_alertOkbutton).click();
				wait(10);

				// Capturing the Screenshot
				try {
					takeScreenShot(driver, "AfterClick_ReverseIcon " + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.println("Info!------No.of records after clicking on 'Revert to Draft' Icon followed by 'OK' and the Plans status of the new record ");

				// To get the total number of rows in the table
				WebElement TogetRows = driver.findElement(oipa_GC_plan_tabledata);
				List<WebElement> TotalRowsList = TogetRows.findElements(By.tagName("tr"));
				Assert.assertEquals(TotalRowsList.size(), Integer.parseInt(xls.getCellData("GroupCustomer_1", "GC_plan_rowcount", 3)));
				Assert.assertEquals(commonTableTextRetreive(driver, 1, 7), xls.getCellData("GroupCustomer_1", "GC_plan_RecordStatus", 2));
				System.out.println("Success!-------Total number of records in the table are : " + TotalRowsList.size() + " and the status of the first record is:  "
						+ commonTableTextRetreive(driver, 1, 7));
			}
			else {
				System.out.println("Fail!---------'Process', 'Revert to Draft', and 'Delete' buttons are NOT dispalyed for the new record ");
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Fail!--------- 'Submit' and 'Delete' buttons are NOT Displayed for the new record ");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC18_PlanScreen_ReverseIconCheck_TestEnds------------------");
	}

	/************************************************ Relationship Test Scripts ***********************************************/

	/**
	 * Hierarchy : Oracle Insurance Policy Administration » Functional » Group Customer » GroupCustomer Relationships » BUG 24659715 - IMPLEMENT GROUP CUSTOMER & CLIENT RELATIONSHIPS - JET UPGRADE
	 ***/

	/* Test 29338: Verify the Add New Relationship UI and functionality */
	@Test
	public void GC19_RelationshipCreation() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC19_RelationshipCreation_TestBegins------------------");

		System.out.println("Info!------------ Creating Group Customer Employment Type Relationship ------------");

		driver.findElement(oipa_GC_relationships).click();
		wait(4);
		System.out.println("Info!------------ Checking Relationship using New Client followed by Cancel action ------------");
		driver.findElement(oipa_GC_addRelationshipDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_addRelationshipDD", 2));
		wait(3);
		driver.findElement(oipa_GC_addRelationshipPlusIcon).click();
		wait(4);
		driver.findElement(oipa_GC_addRelationshipSubTypeDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_addRelationshipSubTypeDD", 2));
		wait(3);
		driver.findElement(oipa_GC_addRelationshipEffectiveDate).sendKeys(xls.getCellData("GroupCustomer_1", "GC_addRelationshipEffectiveDate", 2));
		wait(3);
		driver.findElement(oipa_GC_addRelationshipClientSearchIcon).click();
		wait(3);
		System.out.println("Info! ------------ Adding the client using New Client option ------------");
		driver.switchTo().activeElement();
		driver.findElement(oipa_GC_addRelationshipNewClient).click();
		wait(3);
		driver.findElement(oipa_client_typeDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "client_typeDD", 2));
		wait(3);
		driver.findElement(oipa_client_legalResidenceCountry).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "client_legalResidenceCountry", 2));
		wait(3);
		driver.findElement(oipa_client_prefix).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "client_prefix", 2));
		wait(3);
		String ClientNameSequence = xls.getCellData("GroupCustomer_1", "client_Name", 2) + rand.nextInt(1000);
		driver.findElement(oipa_client_FirstName).sendKeys(xls.getCellData("GroupCustomer_1", "client_FirstName", 2) + ClientNameSequence);
		String ClientFirstName = xls.getCellData("GroupCustomer_1", "client_FirstName", 2) + ClientNameSequence;
		wait(3);
		driver.findElement(oipa_client_MiddleName).sendKeys(xls.getCellData("GroupCustomer_1", "client_MiddleName", 2) + ClientNameSequence);
		wait(3);
		driver.findElement(oipa_client_LastName).sendKeys(xls.getCellData("GroupCustomer_1", "client_LastName", 2) + ClientNameSequence);
		wait(3);
		driver.findElement(oipa_client_Gender).click();
		wait(3);
		driver.findElement(oipa_client_MartialDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "client_MartialDD", 2));
		wait(3);
		driver.findElement(oipa_client_DOB).sendKeys(xls.getCellData("GroupCustomer_1", "client_DOB", 2));
		wait(3);
		driver.findElement(oipa_client_TaxID).sendKeys(String.valueOf(rand.nextInt(1000000000)));
		wait(3);
		driver.findElement(oipa_client_BirthCountry).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "client_BirthCountry", 2));
		wait(3);
		driver.findElement(oipa_client_citizenShipCountry).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "client_citizenShipCountry", 2));
		wait(3);
		driver.findElement(oipa_client_newClientPopUpSaveButton).click();
		wait(3);
		System.out.println("Info! ------------ Relationship Cancel button functionality is Checked ------------");
		driver.findElement(oipa_GC_addRelationshipCancelButton).click();
		wait(4);
		if (!driver.findElement(oipa_GC_addRelationshipResult).isDisplayed()) {
			System.out.println("Group Customer Relationship should not be Created during Cancel operation");
		}
		else {
			System.out.println("Group Customer Relationship Created during Cancel operation");
			Assert.assertTrue(false);
		}
		System.out.println("Info!------------ Adding Relationship using Find Client Followed by Add action ------------");
		driver.findElement(oipa_GC_addRelationshipPlusIcon).click();
		wait(4);
		driver.findElement(oipa_GC_addRelationshipSubTypeDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_addRelationshipSubTypeDD", 2));
		wait(3);
		driver.findElement(oipa_GC_addRelationshipEffectiveDate).sendKeys(xls.getCellData("GroupCustomer_1", "GC_addRelationshipEffectiveDate", 2));
		wait(3);
		driver.findElement(oipa_GC_addRelationshipClientSearchIcon).click();
		wait(3);
		System.out.println("Info!------------ Searching the client using Find Client option ------------");
		driver.switchTo().activeElement();
		if (driver.findElement(oipa_GC_addRelationshipFindClient).isEnabled()) {
			System.out.println("Find Client link is Enabled");
		}
		else {
			driver.findElement(oipa_GC_addRelationshipFindClient).click();
			wait(3);
			System.out.println("Find Client link is Clicked");
		}
		driver.findElement(oipa_client_typeDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "client_typeDD", 2));
		wait(3);
		driver.findElement(oipa_GC_relationshipFirstnameSearchInput).sendKeys(ClientFirstName);
		wait(4);
		driver.findElement(oipa_GC_relationshipSearchButton).click();
		wait(3);
		driver.findElement(oipa_GC_relationshipselectClient).click();
		wait(4);
		driver.findElement(oipa_GC_addRelationshipEmploymentStatusDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_addRelationshipEmploymentStatusDD", 2));
		wait(3);
		driver.findElement(oipa_GC_addRelationshipSaveButton).click();
		wait(4);
		System.out.println("Info!------------ Relationship Save button functionality is Checked ------------");
		if (driver.findElement(oipa_GC_relationshipCreated).getText().contains(ClientFirstName)) {
			System.out.println("Client: " + ClientFirstName + " " + "is Created and added as Relationship");
			System.out.println("Success!-------Group Customer Relationship is Created successfully during Save operation");
		}
		else {
			System.out.println("Fail!-------- Group Customer Relationship is NOT Created during Save");
			Assert.assertTrue(false);
		}
		System.out.println("Info!------------ Verifying the Relationship visibility based on Display Past/ Future options ------------");
		driver.findElement(oipa_GC_addRelationshipDisplayPastBox).click();
		wait(3);
		driver.findElement(oipa_GC_addRelationshipDisplayFutureBox).click();
		wait(3);
		driver.findElement(oipa_GC_addRelationshipRefreshButton).click();
		wait(3);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "Relationship Creation " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (driver.findElement(oipa_GC_relationshipCreated).getText().contains(ClientFirstName)) {
			System.out.println("Success!------- Relationship added is displayed after checking  Display Past/ Future options");
		}
		else {
			System.out.println("Fail!--------Relationship added is NOT displayed after checking  Display Past/ Future options");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC19_RelationshipCreation_TestEnds------------------");
	}

	/* Test 29339: Verifying the UI of Added Relationship */
	@Test
	public void GC20_RelationshipUIverification() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC20_RelationshipUIverification_TestBegins------------------");

		driver.navigate().refresh();
		wait(4);

		driver.findElement(oipa_GC_customerSummaryButton).click();
		wait(5);
		System.out.println("Info!------------ Verifying the UI of Relationship screen ------------");
		if (driver.findElement(oipa_GC_addRelationshipFullTimeText).isDisplayed()) {
			System.out.println("Success!----------Relationship Name 'Full Time' Text is displayed in Relationship screen");
		}
		else {
			System.out.println("Fail!----------Relationship Name 'Full Time' Text is NOT displayed in Relationship screen");
			Assert.assertTrue(false);
		}
		if (driver.findElement(oipa_GC_addRelationshipHamburgerMenu).isDisplayed()) {
			System.out.println("Info!----------Relationship Hamburger Menu is displayed in Relationship screen");

			/* Instantiate Action Class */
			Actions actions = new Actions(driver);

			// Retrieve WebElement 'RelationshipHamburgerMenu' to perform mouse hover

			WebElement hamburgerMenuOption = driver.findElement(oipa_GC_addRelationshipHamburgerMenu);

			/* Mouse hover on hamburgerMenuOption displays 'Go To Client' */
			actions.moveToElement(hamburgerMenuOption).perform();

			System.out.println("Success!----------Mouse hover on Hamburger Menu displays 'Go To Client' in Relationship screen");
		}
		else {
			System.out.println("Fail!----------Relationship Hamburger Menu is NOT displayed in Relationship screen");
			Assert.assertTrue(false);
		}
		if (driver.findElement(oipa_GC_addRelationshipResultExpandIcon).isDisplayed()) {
			System.out.println("Info!----------Carot Icon arrow is displayed in Relationship screen");
			driver.findElement(oipa_GC_addRelationshipResultExpandIcon).click();
			wait(5);
			if (driver.findElement(oipa_GC_addRelationshipShadowCheckBox).isDisplayed() && driver.findElement(oipa_GC_addRelationshipCompareButton).isDisplayed()
					&& driver.findElement(oipa_GC_addRelationshipAddNewRecordButton).isDisplayed()) {
				System.out.println("Success!----------Shadow CheckBox, Compare button and 'Add New Record' button are displayed in Relationship screen");
			}
			else {
				System.out.println("Fail!----------Shadow CheckBox, Compare button and 'Add New Record' button are NOT displayed in Relationship screen");
				Assert.assertTrue(false);
			}
			if (driver.findElement(oipa_GC_addRelationshipSubType).isDisplayed() && driver.findElement(oipa_GC_addRelationshipTableEffectiveDate).isDisplayed()
					&& driver.findElement(oipa_GC_addRelationshipTableExpirationDate).isDisplayed() && driver.findElement(oipa_GC_addRelationshipRecordStatus).isDisplayed()
					&& driver.findElement(oipa_GC_addRelationshipAction).isDisplayed() && tableButtonRetreive(driver, 1, 8, "Submit").isDisplayed()
					&& tableButtonRetreive(driver, 1, 8, "Delete").isDisplayed() && driver.findElement(oipa_GC_addRelationshipTimeSliceTableExpander).isDisplayed()) {
				System.out
						.println("Info!----------Relationship Sub Type/Name , Effective Date, Expiration date, Record status and Action are displayed in Relationship screen");
				System.out.println("Info!----------TimeSliceTableExpander, Submit and Delete icons are displayed");

				// Capturing the Screenshot
				try {
					takeScreenShot(driver, "RelationshipUI_Icons " + runDateTime(driver));
				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.println("Info!---------- Editing the Relationship Information ----------");
				driver.findElement(oipa_GC_addRelationshipTimeSliceTableExpander).click();
				wait(3);
				driver.findElement(oipa_GC_addRelationshipEmploymentStatusDD).click();
				wait(3);
				selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_addRelationshipEmploymentStatusDD", 3));
				wait(3);
				driver.findElement(oipa_GC_addRelationshipSaveButton).click();
				wait(4);
				System.out.println("Info!----------Saved the edited Relationship Successfully");
				System.out.println("Info!---------- Submitting the Relationship using Submit Icon ----------");
				driver.findElement(oipa_GC_addRelationshipTimeSliceTableExpander).click();
				wait(3);
				tableButtonRetreive(driver, 1, 8, "Submit").click();
				wait(3);
				driver.switchTo().activeElement();
				driver.findElement(oipa_GC_addRelationshipWarningOkButton).click();
				wait(3);
				System.out.println("Info!----------Relationship is Submitted successfully");
				if (tableButtonRetreive(driver, 1, 8, "Edit").isDisplayed()) {
					System.out.println("Info!----------Edit icon is displayed under Action Column");
					System.out.println("Info!----------- Clicking on Edit icon to create a new TimeSlice ----------");
					tableButtonRetreive(driver, 1, 8, "Edit").click();
					wait(4);
					driver.findElement(oipa_GC_addRelationshipEmploymentStatusDD).click();
					wait(3);
					selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_addRelationshipEmploymentStatusDD", 2));
					wait(3);
					driver.findElement(oipa_GC_addRelationshipSaveButton).click();
					wait(4);
					System.out.println("Success!----------New TimeSlice for the Relationship is created Successfully");
				}
				else {
					System.out.println("Fail!----------Edit icon is NOT displayed under Action Column");
					Assert.assertTrue(false);
				}
			}
			else {
				System.out.println(
						"Fail!----------Relationship Sub Type/Name, Effective Date, Expiration date, Record status and Action are NOT displayed in Relationship screen");
				System.out.println("Fail!----------TimeSliceTableExpander, Submit and Delete icons are NOT displayed");
				Assert.assertTrue(false);
			}
		}
		else {
			System.out.println("Fail!----------Carot Icon arrow is NOT displayed in Relationship screen");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC20_RelationshipUIverification_TestEnds------------------");

	}

	/* Test 29340: Adding New Time Slice */
	@Test
	public void GC21_RelationshipAddNewSlice() throws IOException {

		System.out.println("/**************************************************************************************************************/");

		System.out.println("Info! ---------GC21_RelationshipAddNewSlice_TestBegins------------------");

		driver.navigate().refresh();
		wait(6);
		driver.findElement(oipa_GC_customerSummaryButton).click();
		wait(5);

		System.out.println("Info!------------ Adding New Time Slice in Relationship screen ------------");
		driver.findElement(oipa_GC_addRelationshipResultExpandIcon).click();
		wait(3);
		driver.findElement(oipa_GC_addRelationshipAddNewRecordButton).click();
		wait(3);
		driver.findElement(oipa_GC_addRelationshipSliceEffectiveDate).sendKeys(xls.getCellData("GroupCustomer_1", "GC_addRelationshipSliceEffectiveDate", 2));
		driver.findElement(oipa_GC_addRelationshipSliceEffectiveDate).sendKeys(Keys.TAB);
		wait(3);
		driver.findElement(oipa_GC_addRelationshipSliceNextButton).click();
		wait(3);
		driver.findElement(oipa_GC_addRelationshipBusinessUnitField).sendKeys(xls.getCellData("GroupCustomer_1", "GC_addRelationshipBusinessUnitField", 2));
		wait(4);
		driver.findElement(oipa_GC_addRelationshipSaveButton).click();
		wait(6);
		driver.navigate().refresh();
		wait(6);
		driver.findElement(oipa_GC_customerSummaryButton).click();
		wait(5);
		driver.findElement(oipa_GC_addRelationshipResultExpandIcon).click();
		wait(3);

		// Capturing the Screenshot
		try {
			takeScreenShot(driver, "NewTimeSlice_Relationship " + runDateTime(driver));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Info!----------Added New Time Slice in Relationship screen");
		Assert.assertEquals(tableTextRetreive(driver, 1, 5), xls.getCellData("GroupCustomer_1", "GC_addRelationshipSliceEffectiveDate", 2));
		Assert.assertEquals(tableTextRetreive(driver, 1, 7), xls.getCellData("GroupCustomer_1", "GC_addRelationshipDraftStatus", 2));
		System.out.println("Success!----------New TimeSlice is Created with Effective date and status as:" + "  " + tableTextRetreive(driver, 1, 5) + " and "
				+ tableTextRetreive(driver, 1, 7));
		if (tableButtonRetreive(driver, 1, 8, "Submit").isDisplayed() && tableButtonRetreive(driver, 1, 8, "Delete").isDisplayed()) {
			System.out.println("Success!----------New TimeSlice Action Column has Submit and Delete icons displayed");
		}
		else {
			System.out.println("Fail!----------New TimeSlice Action Column does NOT have Submit and Delete icons");
			Assert.assertTrue(false);
		}
		System.out.println("Info! ---------GC21_RelationshipAddNewSlice_TestEnds------------------");
	}

	@AfterClass
	public void aClass() {
		closeBrowser(driver);
	}
}
