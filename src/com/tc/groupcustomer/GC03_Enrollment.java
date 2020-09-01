package com.tc.groupcustomer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Library.OIPA_Library;

public class GC03_Enrollment extends OIPA_Library {

	public static WebDriver driver;
	Random rand;
	String agreementName;
	String Firstname;
	String ClientFirstname;
	String CustomerNumber;
	String CustomerNumberCapture;
	Statement stmt = null;
	String policyname;

	@BeforeClass
	public void prerequisite() throws Throwable {
		System.out.println("***************GC03 Enrollement******************************");

		openBrowser();
		driver = login(driver);
		rand = new Random();
		wait(5);
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

	/* Customer Creation and it returns Customer Name*/
	public String gc_customerCreation() throws IOException {
		System.out.println("************************************************************************************");
		System.out.println("!------------------******Creating Group Customer******--------------------!");
		driver.findElement(oipa_createDD).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "oipa_createDD", 2));
		wait(2);
		driver.findElement(oipa_CreateButton).click();
		wait(5);
		driver.findElement(oipa_GC_customerType).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_customerType", 2));
		wait(4);
		String customerName = xls.getCellData("GroupCustomer_1", "GC_customerName", 2) + rand.nextInt(10000);
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
		driver.findElement(oipa_GC_originalEffectiveDate)
				.sendKeys(xls.getCellData("GroupCustomer_1", "GC_originalEffectiveDate", 2));
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
		System.out.println("SUCCESS !----------Group Customer is Created: " + customerNameEntered + "");
		wait(6);
		takeScreenShot(driver, "Customer Creation" + rand.nextInt(1000));
		CustomerNumber = driver.findElement(oipa_GC_Customer_CustomerNumber).getAttribute("value");
		System.out.println("Customer number is : " + CustomerNumber);
		wait(8);
		return customerName;
	}

	/* Adding Relationship and it returns ClientFirstName */
	public String gc_RelationshipCreation() throws IOException {
		System.out.println("************************************************************************************");
		System.out.println(
				"Info!------------ Creating Group Customer Employment Type Relationship using 'New Client Button'------------");
		driver.findElement(oipa_GC_relationships).click();
		wait(4);
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
		driver.findElement(oipa_GC_addRelationshipEffectiveDate)
				.sendKeys(xls.getCellData("GroupCustomer_1", "GC_addRelationshipEffectiveDate", 2));
		wait(3);
		driver.findElement(oipa_GC_addRelationshipClientSearchIcon).click();
		wait(3);
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
		driver.findElement(oipa_client_FirstName)
				.sendKeys(xls.getCellData("GroupCustomer_1", "client_FirstName", 2) + ClientNameSequence);
		String ClientFirstName = xls.getCellData("GroupCustomer_1", "client_FirstName", 2) + ClientNameSequence;
		wait(3);
		driver.findElement(oipa_client_MiddleName)
				.sendKeys(xls.getCellData("GroupCustomer_1", "client_MiddleName", 2) + ClientNameSequence);
		wait(3);
		driver.findElement(oipa_client_LastName)
				.sendKeys(xls.getCellData("GroupCustomer_1", "client_LastName", 2) + ClientNameSequence);
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
		driver.findElement(oipa_GC_addRelationshipSaveButton).click();
		wait(4);

		if (driver.findElement(oipa_GC_relationshipCreated).getText().contains(ClientFirstName)) {
			System.out.println("Client: " + ClientFirstName + " " + "is Created and added as Relationship");
			System.out.println(
					"Success!-------Group Customer Relationship is Created successfully during Save operation");
		} else {
			System.out.println("Fail!-------- Group Customer Relationship is NOT Created during Save");
			Assert.assertTrue(false);
		}
		wait(8);
		return ClientFirstName;
	}

	/* Agreement Creation*/
	public void gc_ParentMasterAgreementCreation() {
		System.out.println("************************************************************************************");
		System.out.println("-------- ******Creating 'Master Agreement Insured'  Contract Agreement****** ----------");
		driver.findElement(oipa_GC_agreement).click();
		wait(3);
		driver.findElement(oipa_GC_CopyClassGroups_ContractExpandIcon).click();
		wait(2);

		// Instantiate Action Class
		Actions actions = new Actions(driver);

		// Retrieve WebElement 'Master Agreement' to perform mouse hover
		WebElement hamburgerMenuOption = driver.findElement(oipa_GC_agreementContractTreetext);

		// Mouse hover on hamburgerMenuOption displays (+) Plus icon
		actions.moveToElement(hamburgerMenuOption).perform();
		driver.findElement(oipa_GC_agreementContractPlusIcon).click();
		wait(4);
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
			driver.findElement(oipa_GC_agreementSaveButton).click();
			wait(3);
			Assert.assertEquals(driver.findElement(oipa_addMessage).getText(), "Agreement added successfully");
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
		}
	}

	/* Product Creation under Agreement*/
	public void gc_ProductCreation() throws IOException {
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
		wait(2);
		driver.findElement(oipa_GC_productExpirationDate)
				.sendKeys(xls.getCellData("GroupCustomer_1", "GC_productExpirationDate", 2));
		wait(4);
		driver.findElement(oipa_GC_productSaveButton).click();
		wait(5);
		takeScreenShot(driver, "Product 'GPP' Creation");
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
	}

	/* Plan Creation and make plan active under Agreement*/
	public void gc_PlanCreationAndProcess() throws IOException {
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
		String planName = xls.getCellData("GroupCustomer_1", "GC_planName", 2) + rand.nextInt(10000);
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
		wait(2);
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
		wait(2);
		driver.findElement(oipa_GC_planSegmentTypeDD).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentTypeDD", 2));
		wait(4);
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
		wait(4);
		driver.findElement(oipa_GC_planSegmentName)
				.sendKeys(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 3));
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
			wait(2);
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
			wait(4);
			takeScreenShot(driver, "Plan Creation");
			if (commonTableTextRetreive(driver, 1, 3).equals(planName) && commonTableTextRetreive(driver, 1, 4)
					.equals(xls.getCellData("GroupCustomer_1", "GC_planBusinessStatusDD", 2))) {
				System.out.println("Plan details in the table are displayed as:" + " "
						+ commonTableTextRetreive(driver, 1, 3) + " " + commonTableTextRetreive(driver, 1, 4) + "  "
						+ commonTableTextRetreive(driver, 1, 5) + " " + commonTableTextRetreive(driver, 1, 6));
				System.out.println("SUCCESS !----------Plan is added successfully with plan segments");
				takeScreenShot(driver, "Agreement Plan Added");

			} else {
				System.out.println("FAIL !----------Plan is Not Created and No data in table exists");
				Assert.assertTrue(false);
			}
		}
		System.out.println("INFO!--------Activate Plan Slice");
		driver.findElement(oipa_gc_plans_sidelist).click();
		wait(5);
		driver.findElement(oipa_gc_Plans_expander).click();
		wait(5);
		driver.findElement(oipa_gc_Plans_submit).click();
		wait(2);
		driver.switchTo().activeElement();
		driver.findElement(oipa_gc_Plans_ok).click();
		wait(5);
		driver.findElement(oipa_gc_Plans_process).click();
		wait(2);
		System.out.println("SUCCESS !----Plan Slice is activated");
	}

	/* Class Group Creation under Agreement*/
	public void gc_AddClassGroup() throws IOException {
		System.out.println("************************************************************************************");
		System.out.println("--------****** Adding Class Group for an Agreement'******----------");
		wait(2);
		driver.findElement(oipa_GC_agreement).click();
		wait(4);
		driver.findElement(oipa_GC_CopyClassGroups_ContractExpandIcon).click();
		wait(2);
		driver.findElement(oipa_GC_Agreement_ClassGroupsLink).click();
		wait(3);
		// String ClassGrpName1 = AddClassGroupDetails();
		driver.findElement(oipa_GC_Agreement_ClassGroups_AddDD).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_AddDD", 2));
		wait(2);
		driver.findElement(oipa_GC_Agreement_ClassGroups_AddButton).click();
		wait(4);
		driver.switchTo().activeElement();
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_TypeDD).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Type", 3));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_StatusDD).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Status", 2));
		wait(2);
		String ClassGrpName = xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Name", 2)
				+ rand.nextInt(100);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupName).sendKeys(ClassGrpName);
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupDescription)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Description", 2));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_EffectiveFrom)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_EffectiveFrom", 2));
		wait(3);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_ExpirationDate).click();
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_ExpirationDate)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_ExpirationDate", 2));
		driver.findElement(oipa_GC_Agreement_AddClassGroups_ExpirationDate).sendKeys(Keys.TAB);
		wait(2);
		// Adding Class
		driver.findElement(oipa_GC_Agreement_AddClassGroups_AddClassLink).click();
		wait(6);
		driver.findElement(oipa_GC_Agreement_AddClass_ClassTypeDD).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ClassType", 3));
		wait(5);
		driver.findElement(oipa_GC_Agreement_AddClass_ClassName)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ClassName", 2) + rand.nextInt(100));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_ClassDescription).sendKeys(
				xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ClassDescription", 2) + rand.nextInt(100));
		wait(2);
		// driver.findElement(oipa_GC_Agreement_AddClass_UndefinedSectionExpander).click();
		// wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_RelatedClass).sendKeys(
				xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_RelatedClass", 2) + rand.nextInt(100));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_ProductType)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ProductType", 2));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_ProductSubType)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ProductSubType", 2));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_Reporting1)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_Reporting1", 2));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_Reporting2)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_Reporting2", 2));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_Billing1)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_Billing1", 2));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_Billing2)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_Billing2", 2));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_CustomerNumber).sendKeys(
				xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_CustomerNumber", 2) + rand.nextInt(100));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_ExperienceNumber).sendKeys(
				xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ExperienceNumber", 2) + rand.nextInt(100));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_ReportNumber).sendKeys(
				xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ReportNumber", 2) + rand.nextInt(100));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_SubCode)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_SubCode", 2));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_Branch)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_Branch", 2));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_KeyNum)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_KeyNum", 2) + rand.nextInt(100));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClass_RateGroup)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_RateGroup", 2) + rand.nextInt(100));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_SaveButton).click();
		wait(4);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_SaveStatusDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClassGroup_SaveStatus", 2));
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_agreementClassgroupSaveButton).click();
		wait(5);
		if (commonTableTextRetreive(driver, 1, 3).equals(ClassGrpName) && commonTableTextRetreive(driver, 1, 4)
				.equals(xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClassGroup_SaveStatus", 2))) {
			System.out.println("SUCCESS ! ------------ Clicked on 'Save' button and Class Group is added successfully");
			takeScreenShot(driver, "Agreement Class Group Added");
		} else {
			System.out.println("FAIL ! ------------ Class Group is not added successfully");
			Assert.assertTrue(false);
		}
	}

	/* Adding Classes,childClass,ClassRuleVariables,Plan coverage for parent and
	 child,Segments Association,MembershipRules Association*/
	public void gc_wholeClassGroupEntities() throws IOException {
		System.out.println("************************************************************************************");
		System.out.println("--------****** Adding Child Class  for Parent Class'******----------");
		driver.findElement(oipa_GC_Agreement_AddClassGroups_HamburgerMenuIcon).click();
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_Hamburger_GoToClass).click();
		wait(3);
		driver.findElement(oipa_GC_ClassGroups_RowExpandIcon).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroups_ClassGroupRowExpander).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroups_ClassesLink).click();
		wait(2);
		// Instantiate Action Class
		Actions actions = new Actions(driver);
		// Retrieve WebElement 'Master Agreement' to perform mouse hover
		WebElement NewIcon = driver.findElement(oipa_GC_ClassGroup_ParentClassLink);

		// Mouse hover on hamburgerMenuOption displays (+) Plus icon
		actions.moveToElement(NewIcon).perform();
		wait(2);
		driver.findElement(oipa_GC_ClassGroups_NewClass).click();
		wait(2);
		driver.switchTo().activeElement();
		wait(3);
		driver.findElement(oipa_GC_ClassGrooups_ChildClass_ClasstypeDD).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ClassType", 3));
		wait(2);
		String ClassGrpName = xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Name", 2)
				+ rand.nextInt(1000);
		driver.findElement(oipa_GC_ClassGroups_ChildClass_ClassName).sendKeys(ClassGrpName);
		wait(2);
		driver.findElement(oipa_GC_ClassGroups_ChildClass_ClassDescription).sendKeys(
				xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Description", 2) + rand.nextInt(1000));
		wait(2);
		driver.findElement(oipa_GC_ClassGroups_ChildClass_SaveButton).click();
		wait(3);
		if (driver.findElement(oipa_GC_ClassGroups_ChildClass_NameButton).getText().equals(ClassGrpName)) {
			System.out
					.println("SUCCESS ! ------ Child class group is added successfully with the class group name as : "
							+ ClassGrpName);
		} else {
			System.out.println("FAIL !----Child class group is not added successfully");
		}
		System.out.println("--------****** Adding Class Rule Variables******----------");
		driver.findElement(oipa_GC_ClassGroups_ClassRuleVariables).click();
		wait(3);
		driver.findElement(oipa_GC_ClassGroups_AddVariableButton).click();
		wait(5);
		driver.findElement(oipa_GC_ClassRuleVariables_RuleName)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_ClassRuleVariables_Name", 2));
		wait(5);
		driver.findElement(oipa_GC_ClassRuleVariables_RuleType).click();
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_ClassRuleVariables_Type", 2));
		wait(2);
		driver.findElement(oipa_GC_ClassRuleVariables_RuleDataType).click();
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "GC_ClassRuleVariables_DataType", 2));
		wait(2);
		driver.findElement(oipa_GC_ClassRuleVariables_RuleText)
				.sendKeys(xls.getCellData("GroupCustomer_2", "GC_ClassRuleVariables_Text", 2));
		wait(2);
		driver.findElement(oipa_GC_ClassRuleVariables_RuleSaveButton).click();
		wait(6);
		System.out.println("SUCCESS !-----Class group variable added successfully!");
		System.out.println(
				"--------****** Adding Plan Coverages and Membership Rules for Parent and Child******----------");
		driver.findElement(oipa_GC_ClassGroups_ClassesLink).click();
		wait(3);
		driver.findElement(oipa_GC_ClassGroup_ParentClassLink).click();
		wait(3);
		driver.findElement(oipa_GC_ClassGroup_PlanCoverageLink).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Assosiate).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Coverage_MoveButton).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Coverage_TypeDD).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "PlanCoverage_TypeCode", 2));
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Coverage_Typetext)
				.sendKeys(xls.getCellData("GroupCustomer_2", "PlanCoverage_TypeCode", 2));
		wait(4);
		driver.findElement(oipa_GC_ClassGroup_Coverage_Typetext)
				.sendKeys(xls.getCellData("GroupCustomer_2", "PlanCoverage_EffectiveDate", 2));
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Coverage_SaveButton).click();
		wait(6);
		driver.findElement(oipa_GC_ClassGroup_Classes_MembershipRulesLink).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroups_Classes_MembershipVariables).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Classes_RulesMoveButton).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Classes_SaveButton).click();
		wait(6);
		System.out.println("SUCCESS !-----Plan and Memembership Rule assosiation done for parent class!");
		// Adding Plan coverage for ChildClass
		driver.findElement(oipa_GC_ChildClassGroup_ClassLink).click();
		wait(3);
		driver.findElement(oipa_GC_ClassGroup_PlanCoverageLink).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Assosiate).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Coverage_MoveButton).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Coverage_TypeDD).click();
		wait(2);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "PlanCoverage_TypeCode", 2));
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Coverage_Typetext)
				.sendKeys(xls.getCellData("GroupCustomer_2", "PlanCoverage_TypeCode", 2));
		wait(4);
		driver.findElement(oipa_GC_ClassGroup_Coverage_Typetext)
				.sendKeys(xls.getCellData("GroupCustomer_2", "PlanCoverage_EffectiveDate", 2));
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Coverage_SaveButton).click();
		wait(6);
		driver.findElement(oipa_GC_ClassGroup_Classes_MembershipRulesLink).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroups_Classes_MembershipVariables).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Classes_RulesMoveButton).click();
		wait(2);
		driver.findElement(oipa_GC_ClassGroup_Classes_SaveButton).click();
		wait(6);
		System.out.println("SUCCESS !-----Plan and Memembership Rule assosiation done for Child class!");
		driver.findElement(oipa_GC_ClassGroups_SubmitIcon).click();
		wait(5);
		if (commonTableTextRetreive(driver, 1, 7)
				.equals(xls.getCellData("GroupCustomer_2", "GC_ClassGroups_Status", 3))) {
			System.out.println("SUCCESS !--Class group is submitted and status is : " + commonTableTextRetreive(driver, 1, 7));

		} else {
			System.out.println("FAIL !----Class group is not submitted and status is : " + commonTableTextRetreive(driver, 1, 7));

		}
	}

	/*Navigating from Relationship and making it as active*/
	public void gc_ActiveRelationship() {
		System.out.println("************************************************************************************");
		System.out.println("--------****** Activating Relationship******----------");
		driver.findElement(oipa_GC_relationships).click();
		wait(8);
		driver.findElement(oipa_GC_customerSummaryButton).click();
		wait(4);
		System.out.println("INFO!---------- Submitting the Relationship using Submit Icon ----------");
		driver.findElement(oipa_GC_addRelationshipResultExpandIcon ).click();
		wait(3);
		tableButtonRetreive(driver, 1, 8, "Submit").click();
		wait(3);
		driver.switchTo().activeElement();
		driver.findElement(oipa_GC_addRelationshipWarningOkButton).click();
		wait(3);
		System.out.println("SUCCESS !----------Relationship is Submitted successfully");
		wait(6);
	}

	/* Add and Process ' Evaluate Membership' and 'Enrollment Prototype'
	 transactions at client level and getting policy name to user further*/
	public String gc_ClientActivities() throws ClassNotFoundException, SQLException, Exception {
		System.out.println("************************************************************************************");
		System.out.println("--------****** Add and Process Client Level Activities*****----------");

		// Instantiate Action Class
		Actions actions = new Actions(driver);
		// Retrieve WebElement 'RelationshipHamburgerMenu' to perform mouse
		// hover
		WebElement hamburgerMenuOption = driver.findElement(oipa_GC_addRelationshipHamburgerMenu);
		// Mouse hover on hamburgerMenuOption displays 'Go To Client'
		actions.moveToElement(hamburgerMenuOption).perform();
		// Clicking on 'Go To Client' option to navigate to Client Screen
		driver.findElement(oipa_GC_addRelationshipGoToClient).click();
		wait(8);
		// Navigating to Client Activities Page to add activities like
		// 'EvaluateMembership' and 'Enrollment Prototype
		driver.findElement(oipa_client_activities).click();
		wait(8);
		// Adding and Processing 'EvaluateMembership' activity
		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "activitydropdown", 2));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate)
				.sendKeys(xls.getCellData("GroupCustomer_2", "activityEffectiveDate", 2));
		wait(8);
		driver.findElement(oipa_client_activityEffectiveTo)
				.sendKeys(xls.getCellData("GroupCustomer_2", "client_activityEffectiveTo", 2));
		wait(8);
		driver.findElement(oipa_activityOKButton).click();
		wait(9);
		tableButtonRetreive(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive(driver, 1, 6).equals(xls.getCellData("GroupCustomer_2", "activityStatus", 3))) {
			System.out.println("SUCCESS !-----Activity" + " '" + commonTableTextRetreive(driver, 1, 3) + "' "
					+ " is processed successfully and its status is:" + "  " + commonTableTextRetreive(driver, 1, 6));
		} else {
			System.out.println("FAIL !----Activity" + " '" + commonTableTextRetreive(driver, 1, 3) + "' "
					+ " is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive(driver, 1, 6));
			Assert.assertTrue(false);
		}
		// Adding and Processing 'EnrollmentPrototype' activity
		driver.findElement(oipa_addActivityButton).click();
		wait(8);
		driver.findElement(oipa_activitydropdown).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_2", "activitydropdown", 3));
		wait(5);
		driver.findElement(oipa_activityEffectiveDate).clear();
		wait(4);
		driver.findElement(oipa_activityEffectiveDate)
				.sendKeys(xls.getCellData("GroupCustomer_2", "activityEffectiveDate", 2));
		wait(8);
		policyname = xls.getCellData("GroupCustomer_2", "client_activityEnrollPolicyName", 3) + rand.nextInt(100);
		driver.findElement(oipa_client_activityEnrollPolicyName).sendKeys(policyname);
		wait(8);
		driver.findElement(oipa_client_activityEnrollCoverageDD).click();
		wait(8);
		selectItemInDropdown(driver, "Comprehensive");
		wait(8);
		driver.findElement(oipa_activityOKButton).click();
		wait(9);
		tableButtonRetreive(driver, 1, 8, "Process").click();
		wait(8);
		if (commonTableTextRetreive(driver, 1, 6).equals(xls.getCellData("GroupCustomer_2", "activityStatus", 3))) {
			System.out.println("SUCCESS !---Activity" + " '" + commonTableTextRetreive(driver, 1, 3) + "' "
					+ " is processed successfully and its status is:" + "  " + commonTableTextRetreive(driver, 1, 6));
		} else {
			System.out.println("FAIL !-----Activity" + " '" + commonTableTextRetreive(driver, 1, 3) + "' "
					+ " is NOT processed successfully and its status is:" + "  "
					+ commonTableTextRetreive(driver, 1, 6));
			Assert.assertTrue(false);
		}
		// Updating the PolicyNumber for recently created policy from
		// 'EnrollmentPrototype' activity
		connectToDb();
		try {
			// Executing update query
			System.out.println("Creating statement...");
			stmt = con.createStatement();
			String sql = "UPDATE AsPolicy SET PolicyNumber ='" + policyname + "' WHERE PolicyName = '" + policyname
					+ "'";
			stmt.executeUpdate(sql);
			System.out.println("INFO!----Statement executed successfully and Policy Number is updated");
		} catch (SQLException excep) {
			excep.printStackTrace();
		}
		return policyname;
	}

	@Test
	public void gc01_EnrollmentProcess() throws ClassNotFoundException, SQLException, Exception {
		gc_customerCreation();
		wait(10);
		gc_RelationshipCreation();
		wait(10);
		gc_ParentMasterAgreementCreation();
		wait(10);
		gc_ProductCreation();
		wait(10);
		gc_PlanCreationAndProcess();
		wait(10);
		gc_AddClassGroup();
		wait(10);
		gc_wholeClassGroupEntities();
		wait(10);
		gc_ActiveRelationship();
		wait(10);
		gc_ClientActivities();
	}
}
