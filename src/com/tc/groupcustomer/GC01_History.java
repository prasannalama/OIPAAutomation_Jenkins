package com.tc.groupcustomer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Library.OIPA_Library;

public class GC01_History extends OIPA_Library {
	public static WebDriver driver;
	Random rnd = new Random();
	WebDriverWait wait;
	String clientName = null;
	String customerName = null;
	String fppPolicyName = null;

	@BeforeClass
	public void setup() throws Throwable {
		
		System.out.println("***************GC01 History******************************");
		
		openBrowser();
		driver = login(driver1);
		wait = new WebDriverWait(driver, 30);
	}

	public void createFppPolicy() {
		String policyName = xls.getCellData("HistoryData", "FPPName", 2) + rnd.nextInt(9999);
		wait(10);
		try {
			System.out.println("Creating a FPP Policy!");
			driver.findElement(oipa_createDD).click();
			selectItemInDropdown(driver, xls.getCellData("HistoryData", "CreatePolicy", 2));
			wait(5);
			driver.findElement(oipa_CreateButton).click();
			wait(10);
			driver.findElement(oipa_policy_companyDD).click();
			wait(1);
			selectItemInDropdown(driver, xls.getCellData("HistoryData", "PolicyCompanyType", 2));
			wait(10);
			driver.findElement(oipa_policy_productDD).click();
			wait(2);
			selectItemInDropdown(driver, xls.getCellData("HistoryData", "PolicyProductType", 2));
			wait(10);
			driver.findElement(oipa_policy_planDD).click();
			wait(1);
			selectItemInDropdown(driver, xls.getCellData("HistoryData", "PlanPolicyType", 2));
			wait(10);
			driver.findElement(oipa_policy_policyname).sendKeys(policyName);
			wait(1);
			driver.findElement(oipa_policy_policy_number).sendKeys(policyName);
			wait(1);
			driver.findElement(oipa_save).click();
			wait(10);
		} catch (Exception e) {
			System.out.println("Unable create policy");
			e.printStackTrace();
		}
		try {
			System.out.println("FPP Policy Created : " + policyName);
		} catch (Exception e) {
			System.out.println("Unable capture policy details");
			e.printStackTrace();
		}
	}

	public void addInsuredRoleByFind() {
		wait(5);
		driver.findElement(oipa_policy_role_find_client).click();
		wait(10);
		try {
			driver.findElement(oipa_policy_role_single_type).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("HistoryData", "RoleCode", 3));
		} catch (Exception e) {
			driver.findElement(oipa_policy_role_types).click();
			wait(3);
			selectItemInDropdown(driver, xls.getCellData("HistoryData", "RoleCode", 3));
		}
		wait(3);
		driver.findElement(oipa_client_typeDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ClientType", 2));
		wait(3);

		WebElement fisrtName = driver.findElement(oipa_policy_role_find_firstname);

		if (clientName == null) {
			wait(5);
			fisrtName.sendKeys("%");
			wait(5);
		} else {
			wait(5);
			fisrtName.sendKeys(clientName);
			wait(5);
		}
		driver.findElement(oipa_policy_client_search).click();
		wait(3);
		driver.findElement(oipa_policy_role_found_client).click();
		wait(3);
		driver.findElement(oipa_policy_add_client).click();
		wait(5);
	}

	public void addInsuredRole() {
		wait(5);
		driver.findElement(oipa_policy_role).click();
		wait(9);
		wait.until(ExpectedConditions.presenceOfElementLocated(oipa_policy_role_new_client));
		driver.findElement(oipa_policy_role_new_client).click();
		wait(5);
		try {
			driver.findElement(oipa_policy_role_single_type).click();
			wait(2);
			selectItemInDropdown(driver, xls.getCellData("HistoryData", "RoleCode", 3));
			wait(5);
		} catch (Exception e) {
			driver.findElement(oipa_policy_role_types).click();
			wait(2);
			selectItemInDropdown(driver, xls.getCellData("HistoryData", "RoleCode", 3));
			wait(5);
		}
		driver.findElement(oipa_client_typeDD).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ClientType", 2));
		wait(3);
		driver.findElement(oipa_client_legalResidenceCountry).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "Country", 2));
		wait(3);
		String firstName = "FirstName_" + rnd.nextInt(90000);
		driver.findElement(oipa_client_FirstName).sendKeys(firstName);
		wait(3);
		String lastName = "LastName_" + rnd.nextInt(90000);
		driver.findElement(oipa_client_LastName).sendKeys(lastName);
		wait(3);
		driver.findElement(oipa_client_DOB).sendKeys(xls.getCellData("HistoryData", "ClientDOB", 2));
		wait(3);
		driver.findElement(oipa_client_TaxID).sendKeys(xls.getCellData("HistoryData", "TaxID", 2));
		wait(5);
		wait.until(ExpectedConditions.elementToBeClickable(oipa_policy_role_client_adrress_type));
		driver.findElement(oipa_policy_role_client_adrress_type).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "AddressType", 2));
		wait.until(ExpectedConditions.elementToBeClickable(oipa_policy_role_client_adrress_country));
		driver.findElement(oipa_policy_role_client_adrress_country).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "Country", 2));

		for (int i = 0; i <= 10; i++) {
			try {
				driver.findElement(oipa_policy_role_client_adrress_line1).click();
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		wait(10);
		driver.findElement(oipa_policy_role_client_adrress_line1)
				.sendKeys(xls.getCellData("HistoryData", "AddressLine", 2) + rnd.nextInt(1));
		wait(10);
		driver.findElement(oipa_policy_role_client_adrress_line1).sendKeys(Keys.TAB);
		wait(5);
		driver.findElement(oipa_policy_role_client_adrress_city).sendKeys(xls.getCellData("HistoryData", "City", 2));
		wait(5);
		driver.findElement(oipa_policy_role_client_adrress_zip).sendKeys(xls.getCellData("HistoryData", "Zip", 2));
		driver.findElement(oipa_policy_add_client_in_role).click();
		wait(5);
	}

	public void createClient() {
		wait(5);
		String clientNameLocal = xls.getCellData("HistoryData", "ClientName", 2) + rnd.nextInt(999);
		try {
			System.out.println("Creating A Client!");
			wait(5);
			driver.findElement(oipa_createDD).click();
			selectItemInDropdown(driver, xls.getCellData("HistoryData", "CreateClient", 2));
			wait(1);
			driver.findElement(oipa_CreateButton).click();
			wait(5);
			driver.findElement(oipa_client_typeDD).click();
			wait(1);
			selectItemInDropdown(driver, xls.getCellData("HistoryData", "ClientType", 2));
			wait(1);
			driver.findElement(oipa_client_legalResidenceCountry).click();
			wait(1);
			selectItemInDropdown(driver, xls.getCellData("HistoryData", "ClientCountry", 2));
			driver.findElement(oipa_client_FirstName).sendKeys(clientNameLocal);
			wait(1);
			driver.findElement(oipa_client_LastName).sendKeys(clientNameLocal);
			wait(1);
			driver.findElement(oipa_client_DOB).sendKeys(xls.getCellData("HistoryData", "ClientDOB", 2));
			wait(1);
			driver.findElement(oipa_client_TaxID).sendKeys(xls.getCellData("HistoryData", "TaxID", 2));
			wait(5);
			driver.findElement(oipa_save).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(oipa_client_history_tab));
			if (driver.findElement(oipa_client_history_tab).isDisplayed()) {
				clientName = clientNameLocal;
				System.out.println("Client is created successfully! & Name is : " + clientName);
			}

		} catch (Exception e) {
			System.out.println("Client Not Created!");
			e.printStackTrace();
		}

	}

	public void createVdaPolicy() {
		wait(5);
		String vda_policy = "VDA_Test_Auto_" + rnd.nextInt(9999);
		driver.findElement(oipa_createDD).click();
		wait(1);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "CreatePolicy", 2));
		wait(1);
		driver.findElement(oipa_createDD).click();
		wait(2);
		driver.findElement(oipa_CreateButton).click();
		wait(5);
		driver.findElement(oipa_policy_company).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "PolicyCompanyType", 3));
		wait(5);
		driver.findElement(oipa_policy_prodcut).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "PolicyProductType", 3));
		wait(5);
		driver.findElement(oipa_policy_plan).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "PolicyPlanType", 3));
		wait(5);
		driver.findElement(oipa_policy_policy_name).sendKeys(vda_policy);
		wait(1);
		driver.findElement(oipa_policy_policy_issue_date).sendKeys(xls.getCellData("HistoryData", "IssueDate", 2));
		driver.findElement(oipa_policy_save_button).click();
		wait(10);

	}

	public void createGroupCustomer() {
		wait(5);
		System.out.println("-- Creating Group Customer");
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
		customerName = xls.getCellData("GroupCustomer_1", "GC_customerName", 2) + rnd.nextInt(9999);
		driver.findElement(oipa_GC_customerName).sendKeys(customerName);
		wait(3);
		if (driver.findElement(oipa_GC_customerNumber).isEnabled()) {
			driver.findElement(oipa_GC_customerNumber).sendKeys(customerName);
			driver.findElement(oipa_GC_customerNumber).sendKeys(Keys.TAB);
			wait(5);
		}
		driver.findElement(oipa_GC_TaxID).sendKeys(String.valueOf(rnd.nextInt(1000000000)));
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
		wait(5);
		driver.findElement(oipa_GC_hierarchyRelationshipDD).click();
		wait(5);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_hierarchyRelationshipDD", 2));
		wait(5);
		driver.findElement(oipa_GC_originalEffectiveDate).clear();
		wait(5);
		driver.findElement(oipa_GC_originalEffectiveDate)
				.sendKeys(xls.getCellData("GroupCustomer_1", "GC_originalEffectiveDate", 2));
		wait(5);
		driver.findElement(oipa_GC_customerLegalName).sendKeys(customerName);
		wait(5);
		driver.findElement(oipa_GC_customerStatusDD).click();
		wait(5);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_customerStatusDD", 2));
		wait(5);
		driver.findElement(oipa_GC_savebutton).click();
		wait(5);
		System.out.println("Group Customer is Created: " + customerName);
	}

	public void addRoleToPolicy() {
		wait(5);
		driver.findElement(oipa_policy_role).click();
		wait.until(ExpectedConditions.elementToBeClickable(oipa_policy_role_new_client));
		driver.findElement(oipa_policy_role_new_client).click();
		wait(5);
		driver.findElement(oipa_policy_role_types).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "RoleCode", 2));
		wait(5);
		driver.findElement(oipa_client_typeDD).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ClientType", 2));
		wait(3);
		driver.findElement(oipa_client_legalResidenceCountry).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "Country", 2));
		wait(3);
		String firstName = "FirstName_" + rnd.nextInt(90000);
		driver.findElement(oipa_client_FirstName).sendKeys(firstName);
		wait(3);
		String lastName = "LastName_" + rnd.nextInt(90000);
		driver.findElement(oipa_client_LastName).sendKeys(lastName);
		wait(3);
		driver.findElement(oipa_client_DOB).sendKeys(xls.getCellData("HistoryData", "ClientDOB", 2));
		wait(3);
		driver.findElement(oipa_client_TaxID).sendKeys(xls.getCellData("HistoryData", "TaxID", 2));
		wait(5);
		wait.until(ExpectedConditions.elementToBeClickable(oipa_policy_role_client_adrress_type));
		driver.findElement(oipa_policy_role_client_adrress_type).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "AddressType", 2));
		wait.until(ExpectedConditions.elementToBeClickable(oipa_policy_role_client_adrress_country));
		driver.findElement(oipa_policy_role_client_adrress_country).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "Country", 2));

		for (int i = 0; i <= 10; i++) {
			try {
				driver.findElement(oipa_policy_role_client_adrress_line1).click();
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		wait(10);
		driver.findElement(oipa_policy_role_client_adrress_line1)
				.sendKeys(xls.getCellData("HistoryData", "AddressLine", 2) + rnd.nextInt(1));
		wait(10);
		driver.findElement(oipa_policy_role_client_adrress_line1).sendKeys(Keys.TAB);
		wait(5);
		driver.findElement(oipa_policy_role_client_adrress_city).sendKeys(xls.getCellData("HistoryData", "City", 2));
		wait(5);
		driver.findElement(oipa_policy_role_client_adrress_zip).sendKeys(xls.getCellData("HistoryData", "Zip", 2));
		driver.findElement(oipa_policy_add_client_in_role).click();
		wait(5);
	}

	@Test
	public void testClientHistory() throws IOException {

		System.out.println("***** Start: Verify Client History*********");
		createClient();
		wait(5);

		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("FirstName");
		lista1.add("-");
		lista1.add(clientName);
		lista2.add("LastName");
		lista2.add("-");
		lista2.add(clientName);
		changedDetailsa.add(lista1);
		changedDetailsa.add(lista2);

		System.out.println("----Client Add------");
		driver.findElement(oipa_client_history_tab).click();
		wait(5);
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2), xls.getCellData("HistoryData", "ClientHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Client History Add Success!");
				takeScreenShot(driver, "Client History Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("Client History Details Add Fail!");
				takeScreenShot(driver, "Client History Add");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Client History Add Fail!");
			takeScreenShot(driver, "Client History Add");
			Assert.assertTrue(false);
		}

		System.out.println("----Client Update------");
		driver.findElement(oipa_client_tab).click();
		wait(5);
		String old_last_name = driver.findElement(oipa_client_last_name).getAttribute("value");
		wait(1);
		String new_last_name = old_last_name + rnd.nextInt(100);
		wait(1);
		/* Add test data details to list then add that list to main list */
		List<String> listu1 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("LastName");
		listu1.add(old_last_name);
		listu1.add(new_last_name);
		changedDetailsu.add(listu1);
		/* Add test data details to list then add that list to main list */
		// Changing LastName
		driver.findElement(oipa_client_last_name).clear();
		wait(1);
		driver.findElement(oipa_client_last_name).sendKeys(new_last_name);
		wait(1);
		// Changing Middle Initial
		driver.findElement(oipa_client_middle_initial).clear();
		wait(1);
		driver.findElement(oipa_client_save_button).click();
		wait(1);
		driver.findElement(oipa_client_history_tab).click();
		wait(3);
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "ClientHistory	", 2))) {
			driver.findElement(oipa_history_expander).click();
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Success! Client History Update");
				takeScreenShot(driver, "Client History Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("Client History Details Failed!");
				takeScreenShot(driver, "Client History Update");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Client History Failed!");
			takeScreenShot(driver, "Client History Update");
			Assert.assertTrue(false);
		}
		System.out.println("***** End: Verify Client History*********");

	}

	@Test()
	public void testClientAddressHistory() throws IOException {
		System.out.println("***** Start: Verify Client Address History*********");
		createClient();
		wait(10);
		System.out.println("---Verifying Add operation in Client Address Histoty---");
		System.out.println("Adding a new address!");
		driver.findElement(oipa_cl_address).click();
		wait(3);
		// Adding a new address
		driver.findElement(oipa_cl_add_address).click();
		wait(5);
		driver.findElement(oipa_cl_select_address_type).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "AddressType", 2));
		wait(10);
		driver.findElement(oipa_cl_select_address_country).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "Country", 2));
		wait(10);
		driver.findElement(oipa_cl_address_line1)
				.sendKeys(xls.getCellData("HistoryData", "AddressLine", 2) + rnd.nextInt(2));
		driver.findElement(oipa_cl_address_line1).sendKeys(Keys.TAB);
		driver.findElement(oipa_cl_address_line1).sendKeys(Keys.TAB);
		wait(3);
		driver.findElement(oipa_cl_address_line2)
				.sendKeys(xls.getCellData("HistoryData", "AddressLine", 2) + rnd.nextInt(9));
		wait(3);
		driver.findElement(oipa_cl_address_line3)
				.sendKeys(xls.getCellData("HistoryData", "AddressLine", 2) + rnd.nextInt(9));
		wait(3);
		driver.findElement(oipa_cl_address_line4)
				.sendKeys(xls.getCellData("HistoryData", "AddressLine", 2) + rnd.nextInt(9));
		wait(3);
		driver.findElement(oipa_cl_address_city).sendKeys(xls.getCellData("HistoryData", "City", 2));
		wait(3);
		driver.findElement(oipa_cl_address_state).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "State", 7));
		wait(3);
		driver.findElement(oipa_cl_address_zip).sendKeys(xls.getCellData("HistoryData", "Zip", 2));
		wait(3);
		driver.findElement(oipa_cl_address_email).sendKeys(xls.getCellData("HistoryData", "Email", 2));
		wait(3);
		driver.findElement(oipa_cl_address_fax).sendKeys(xls.getCellData("HistoryData", "FaxNumber", 2));
		wait(5);
		driver.findElement(oipa_cl_address_save_button).click();
		wait(5);
		driver.findElement(oipa_cl_customer_address_history).click();
		wait(5);

		/* Add test data details to list then add that list to main list */

		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<List> changedDetails = new ArrayList<List>();
		list1.add("Email");
		list1.add("-");
		list1.add(xls.getCellData("HistoryData", "Email", 2));
		list2.add("City");
		list2.add("-");
		list2.add(xls.getCellData("HistoryData", "City", 2));
		changedDetails.add(list1);
		changedDetails.add(list2);

		/* Add test data details to list then add that list to main list */

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2), xls.getCellData("HistoryData", "AddressHistory", 2))) {
			wait(5);
			driver.findElement(oipa_history_expander).click();
			wait(5);
			if (verifyHistoryDetailsTable(changedDetails)) {
				System.out.println("INFO!: Add -Operation verified successfully!");
				takeScreenShot(driver, "GC Address History Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("INFO!: Add - Operation details are wrong!");
				takeScreenShot(driver, "GC Address History Add");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("History details are wrong!");
			takeScreenShot(driver, "GC Address History Add");
			Assert.assertTrue(false);
		}

		System.out.println("-----Verifying Update operation in Client Address------");
		System.out.println("Updating recently added address");
		wait(3);
		driver.findElement(oipa_cl_customer_address_tab).click();
		wait(5);
		driver.findElement(oipa_cl_address_expander).click();
		wait(15);
		String old_adl1 = driver.findElement(oipa_cl_address_line1).getAttribute("value");
		String old_adl2 = driver.findElement(oipa_cl_address_line2).getAttribute("value");
		System.out.println("old details:" + old_adl1 + "and " + old_adl2);
		driver.findElement(oipa_cl_address_line1).clear();
		wait(1);
		String new_adl1 = old_adl1 + rnd.nextInt(100);
		driver.findElement(oipa_cl_address_line1).sendKeys(new_adl1);
		wait(1);

		driver.findElement(oipa_cl_address_line2).clear();
		wait(1);
		String new_adl2 = old_adl2 + rnd.nextInt(100);
		driver.findElement(oipa_cl_address_line2).sendKeys(new_adl2);
		wait(2);

		try {
			driver.findElement(oipa_gc_address_letter_mail_date).clear();
			wait(1);
			driver.findElement(oipa_gc_address_letter_mail_date)
					.sendKeys(xls.getCellData("HistoryData", "LetterMailDate", 7));
			wait(1);
		} catch (Exception e) {
			System.out.println("LetterMailDate is not present for this client");
		}

		driver.findElement(oipa_cl_address_save_button).click();
		wait(5);
		driver.findElement(oipa_cl_customer_address_history).click();
		wait(3);

		/* Add test data details to list then add that list to main list */

		List<String> list11 = new ArrayList<String>();
		List<String> list21 = new ArrayList<String>();
		List<List> changedDetails1 = new ArrayList<List>();
		list11.add("AddressLine1");
		list11.add(old_adl1);
		list11.add(new_adl1);
		list21.add("AddressLine2");
		list21.add(old_adl2);
		list21.add(new_adl2);
		changedDetails1.add(list11);
		changedDetails1.add(list21);

		System.out.println("Incoming: " + changedDetails1);
		/* Add test data details to list then add that list to main list */

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "AddressHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			if (verifyHistoryDetailsTable(changedDetails1)) {
				System.out.println("Client Address Update Success");
				takeScreenShot(driver, "Client Address Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("Client Address Update History Opeartion Wrong!");
				takeScreenShot(driver, "Client Address Update");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Client Address Update History Wrong!");
			takeScreenShot(driver, "Client Address Update");
			Assert.assertTrue(false);
		}

		System.out.println("***** End: Verify Client Address History*******");
	}

	@Test
	public void testProgramHistory() throws IOException {
		wait(15);
		System.out.println("***** Start: Verify Program History*********");
		wait(5);
		createFppPolicy();
		wait(5);
		wait.until(ExpectedConditions.elementToBeClickable(oipa_programs_sidelist));
		driver.findElement(oipa_programs_sidelist).click();
		wait(5);
		driver.findElement(oipa_programs_type).click();
		wait(1);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ProgramChoice", 2));
		driver.findElement(oipa_programs_select_program).click();
		wait(1);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ProgramType", 2));
		driver.findElement(oipa_programs_add_program).click();

		wait(3);
		driver.findElement(oipa_programs_start_date).click();
		wait(3);
		driver.findElement(oipa_programs_start_date).clear();
		wait(3);
		driver.findElement(oipa_programs_start_date).sendKeys(xls.getCellData("HistoryData", "StartDate", 2));

		wait(3);
		driver.findElement(oipa_programs_effective_date).click();
		wait(3);
		driver.findElement(oipa_programs_effective_date).clear();
		wait(3);
		driver.findElement(oipa_programs_effective_date).sendKeys(xls.getCellData("HistoryData", "EffectiveDate", 2));

		wait(3);
		driver.findElement(oipa_programs_end_date).click();
		wait(3);
		driver.findElement(oipa_programs_end_date).clear();
		wait(3);
		driver.findElement(oipa_programs_end_date).sendKeys(xls.getCellData("HistoryData", "EndDate", 2));

		wait(3);
		driver.findElement(oipa_programs_amount).click();
		wait(3);
		driver.findElement(oipa_programs_amount).clear();
		wait(3);
		driver.findElement(oipa_programs_amount).sendKeys(xls.getCellData("HistoryData", "Amount", 2));
		driver.findElement(oipa_programs_save).click();
		wait(10);
		driver.findElement(oipa_programs_expander).click();

		System.out.println("----------Programs Add------------");

		/* Add test data details to list then add that list to main list */

		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("StartDate");
		lista1.add("-");
		lista1.add(xls.getCellData("HistoryData", "StartDate", 2));
		lista2.add("EndDate");
		lista2.add("-");
		lista2.add(xls.getCellData("HistoryData", "EndDate", 2));
		changedDetailsa.add(lista1);
		changedDetailsa.add(lista2);
		System.out.println(changedDetailsa);

		driver.findElement(oipa_programs_history).click();
		wait(10);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2), xls.getCellData("HistoryData", "ProgramHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Programs-Add-Success");
				takeScreenShot(driver, "Programs-Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("Programs-Add-HistoryDetails-Fail");
				takeScreenShot(driver, "Programs-Add");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Programs-Add-HistoryType-Fail");
			takeScreenShot(driver, "Programs-Add");
			Assert.assertTrue(false);
		}

		driver.findElement(oipa_programs_details).click();
		wait(10);

		String old_effdate = driver.findElement(oipa_programs_effective_date).getAttribute("value");

		wait(3);
		driver.findElement(oipa_programs_effective_date).click();
		wait(3);
		driver.findElement(oipa_programs_effective_date).clear();
		wait(3);
		driver.findElement(oipa_programs_effective_date).sendKeys(xls.getCellData("HistoryData", "EffectiveDate", 3));

		String old_enddate = driver.findElement(oipa_programs_end_date).getAttribute("value");
		wait(3);
		driver.findElement(oipa_programs_end_date).click();
		wait(3);
		driver.findElement(oipa_programs_end_date).clear();
		wait(3);
		driver.findElement(oipa_programs_end_date).sendKeys(xls.getCellData("HistoryData", "EndDate", 3));
		driver.findElement(oipa_programs_save).click();
		wait(10);
		driver.findElement(oipa_programs_expander).click();
		wait(10);
		System.out.println("---------Programs Update--------------");

		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<String> listu2 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("EffectiveDate");
		listu1.add(old_effdate);
		listu1.add(xls.getCellData("HistoryData", "EffectiveDate", 3));
		listu2.add("EndDate");
		listu2.add(old_enddate);
		listu2.add(xls.getCellData("HistoryData", "EndDate", 3));
		System.out.println(changedDetailsu);

		driver.findElement(oipa_programs_history).click();
		wait(10);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "ProgramHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Programs-Update-Success");
				takeScreenShot(driver, "Programs-Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("Programs-Update-History Details-Fail");
				takeScreenShot(driver, "Programs-Update");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Programs-Update-HistoryType-Fail");
			takeScreenShot(driver, "Programs-Update");
			Assert.assertTrue(false);
		}
		System.out.println("***** End: Verify Program History*********");
	}

	@Test
	public void testPolicyCommentsHistory() throws IOException {
		wait(20);
		createFppPolicy();
		wait(5);
		System.out.println("***** Start: Verify Policy Comments History*********");
		wait.until(ExpectedConditions.elementToBeClickable(oipa_comments_sidelist));
		driver.findElement(oipa_comments_sidelist).click();
		wait(5);
		driver.findElement(oipacomments_comments_add).click();
		wait(5);
		driver.switchTo().activeElement();
		driver.findElement(oipacomments_comments_new).click();
		wait(10);
		driver.findElement(oipa_comments_functional_dept).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "CommentsFunctionalDepartment", 2));
		driver.findElement(oipa_comments_category).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "CommentsCategory", 2));
		wait(3);
		driver.findElement(oipa_comments_policy_comment_field).click();
		wait(3);
		driver.findElement(oipa_comments_policy_comment_field).clear();
		wait(3);
		driver.findElement(oipa_comments_policy_comment_field)
				.sendKeys(xls.getCellData("HistoryData", "PolicyCommentField", 2));
		wait(3);
		driver.findElement(oipa_comments_comment_name).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "CommentName", 2));
		wait(3);
		driver.findElement(oipa_comments_content).click();
		wait(3);
		driver.findElement(oipa_comments_content).clear();
		wait(3);
		driver.findElement(oipa_comments_content).sendKeys(xls.getCellData("HistoryData", "CommentContent", 2));
		wait(3);
		driver.findElement(oipa_comments_save).click();
		wait(5);

		System.out.println("---------Comments Add--------------");

		/* Add test data details to list then add that list to main list */

		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("PolicyCommentField");
		lista1.add("-");
		lista1.add(xls.getCellData("HistoryData", "PolicyCommentField", 2));
		lista2.add("Category");
		lista2.add("-");
		lista2.add(xls.getCellData("HistoryData", "CommentsCategory", 2));
		changedDetailsa.add(lista1);
		changedDetailsa.add(lista2);
		System.out.println(changedDetailsa);

		driver.findElement(oipa_comments_history).click();
		wait(10);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "CommentsHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Comments-Add-Success");
				takeScreenShot(driver, "Comments-Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("Comments-Add-HistoryDetails-Fail");
				takeScreenShot(driver, "Comments-Add");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Comments-Add-HistoryType-Fail");
			takeScreenShot(driver, "Comments-Add");
			Assert.assertTrue(false);
		}

		driver.findElement(oipa_comments_all_comments).click();
		wait(10);
		driver.findElement(oipa_comments_expander).click();
		wait(5);

		String old_comments = driver.findElement(oipa_comments_content).getAttribute("value");
		wait(3);
		driver.findElement(oipa_comments_content).click();
		wait(3);
		driver.findElement(oipa_comments_content).clear();
		wait(3);
		String new_comments = xls.getCellData("HistoryData", "CommentContent", 2) + rnd.nextInt(999);
		driver.findElement(oipa_comments_content).sendKeys(new_comments);
		wait(3);
		driver.findElement(oipa_comments_save).click();
		wait(5);

		System.out.println("---------Comments Update----------");

		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("Comments");
		listu1.add(old_comments);
		listu1.add(new_comments);
		System.out.println(changedDetailsu);

		driver.findElement(oipa_comments_history).click();
		wait(10);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "CommentsHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Comments-Update-Success");
				Assert.assertTrue(true);
			} else {
				System.out.println("Comments-Update-HistoryDetails-Fail");
				takeScreenShot(driver, "Comments-Update");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Comments-Update-HistoryType-Fail");
			takeScreenShot(driver, "Comments-Update");
			Assert.assertTrue(false);
		}

		System.out.println("---------Comments Delete------------");
		driver.findElement(oipa_comments_all_comments).click();
		wait(3);
		driver.findElement(oipa_comments_delete).click();
		wait(3);
		driver.switchTo().activeElement();
		wait(3);
		driver.findElement(oipa_comments_delete_ok).click();
		wait(3);
		driver.findElement(oipa_comments_history).click();
		wait(3);
		List<String> listd1 = new ArrayList<String>();
		List<List> changedDetailsd = new ArrayList<List>();
		listd1.add("StatusCode");
		listd1.add("Active");
		listd1.add("Shadow");
		changedDetailsd.add(listd1);

		System.out.println(changedDetailsu);

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryDelete", 2),
				xls.getCellData("HistoryData", "CommentsHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsd)) {
				System.out.println("Comments-Delete-Success");
				driver.findElement(oipa_comments_close).click();
				wait(3);
				takeScreenShot(driver, "Comments-Delete");
				Assert.assertTrue(true);
			} else {
				System.out.println("Comments-Update-HistoryDetails-Fail");
				takeScreenShot(driver, "Comments-Delete");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Comments-Delete-HistoryType-Fail");
			takeScreenShot(driver, "Comments-Delete");
			Assert.assertTrue(false);

		}
		System.out.println("***** End: Verify Policy Comments History*********");
	}

	@Test
	public void testSuspenseHistory() throws IOException {
		wait(15);
		driver.findElement(oipa_createDD).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "CreateSuspense", 2));
		wait(3);
		driver.findElement(oipa_CreateButton).click();
		wait(3);
		driver.findElement(oipa_suspense_policy_num).sendKeys(xls.getCellData("HistoryData", "FPPName", 2));
		wait(3);
		driver.findElement(oipa_suspense_effective_date).clear();
		wait(3);
		driver.findElement(oipa_suspense_effective_date).sendKeys(xls.getCellData("HistoryData", "EffectiveDate", 2));
		wait(3);
		driver.findElement(oipa_suspense_amount).clear();
		wait(3);
		driver.findElement(oipa_suspense_amount).sendKeys(xls.getCellData("HistoryData", "Amount", 2));
		wait(3);
		driver.findElement(oipa_suspense_firstname).click();
		wait(3);
		driver.findElement(oipa_suspense_firstname).sendKeys(xls.getCellData("HistoryData", "FirstName", 2));
		wait(3);
		driver.findElement(oipa_suspense_lastname).click();
		wait(3);
		driver.findElement(oipa_suspense_lastname).sendKeys(xls.getCellData("HistoryData", "LastName", 2));
		wait(3);
		driver.findElement(oipa_suspense_acc_num).click();
		wait(5);
		driver.findElement(oipa_suspense_acc_num).sendKeys(xls.getCellData("HistoryData", "AccountNumber", 2));
		wait(5);
		driver.findElement(oipa_suspense_check_num).click();
		wait(3);
		driver.findElement(oipa_suspense_check_num).sendKeys(xls.getCellData("HistoryData", "CheckNumber", 2));
		wait(3);
		driver.findElement(oipa_suspense_bank_name).click();
		wait(3);
		driver.findElement(oipa_suspense_bank_name).sendKeys(xls.getCellData("HistoryData", "BankName", 2));
		wait(3);
		driver.findElement(oipa_suspense_bank_num).click();
		wait(3);
		driver.findElement(oipa_suspense_bank_num).sendKeys(xls.getCellData("HistoryData", "BankNumber", 2));
		wait(10);
		driver.findElement(oipa_suspense_save).click();
		wait(10);
		try {
			System.out
					.println("SuspenseID: " + driver.findElement(oipa_suspense_id).getText().replaceAll("[^0-9]", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* Add test data details to list then add that list to main list */

		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<List> changedDetails = new ArrayList<List>();
		list1.add("FirstName");
		list1.add("-");
		list1.add(xls.getCellData("HistoryData", "FirstName", 2));
		list2.add("LastName");
		list2.add("-");
		list2.add(xls.getCellData("HistoryData", "LastName", 2));
		changedDetails.add(list1);
		changedDetails.add(list2);

		/* Add test data details to list then add that list to main list */

		System.out.println("---------Verifying Suspense Add History-------------");
		driver.findElement(oipa_suspense_history_tab).click();
		wait(5);
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "SuspenseHistory", 2))) {
			wait(5);
			driver.findElement(oipa_history_expander).click();
			wait(5);
			if (verifyHistoryDetailsTable(changedDetails)) {
				System.out.println("Suspense Add Success!");
				takeScreenShot(driver, "Suspense Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("INFO!: Add - Operation details are wrong!");
				takeScreenShot(driver, "Suspense Add");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("History details are wrong!");
			takeScreenShot(driver, "Suspense Add");
			Assert.assertTrue(false);
		}

		System.out.println("-------Verifying Suspense Update History------");
		driver.findElement(oipa_suspense_suspense_tab).click();
		wait(5);
		String old_first_name = driver.findElement(oipa_suspense_firstname).getAttribute("value");
		driver.findElement(oipa_suspense_firstname).clear();
		wait(5);
		String new_first_name = old_first_name + rnd.nextInt(999);
		driver.findElement(oipa_suspense_firstname).sendKeys(new_first_name);
		wait(5);
		driver.findElement(oipa_suspense_save).click();
		wait(5);
		driver.findElement(oipa_suspense_history_tab).click();
		wait(5);
		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("FirstName");
		listu1.add(old_first_name);
		listu1.add(new_first_name);
		changedDetailsu.add(listu1);

		/* Add test data details to list then add that list to main list */

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "SuspenseHistory", 2))) {
			wait(5);
			driver.findElement(oipa_history_expander).click();
			wait(5);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Suspense Update Success");
				takeScreenShot(driver, "Suspense Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("INFO!: Update - Operation details are wrong!");
				takeScreenShot(driver, "Suspense Update");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("History details are wrong!");
			takeScreenShot(driver, "Suspense Update");
			Assert.assertTrue(false);
		}

		System.out.println("-----Verifying Suspense Delete History------");

		driver.findElement(oipa_suspense_suspense_tab).click();
		wait(5);
		driver.findElement(oipa_suspense_suspense_delete).click();
		wait(5);
		driver.switchTo().activeElement();
		wait(5);
		driver.findElement(oipa_suspense_suspense_delete_ok).click();
		wait(10);
		driver.findElement(oipa_suspense_history_tab).click();
		wait(10);

		List<String> listd1 = new ArrayList<String>();
		List<List> changedDetailsd = new ArrayList<List>();
		listd1.add("StatusCode");
		listd1.add("Open");
		listd1.add("Shadow");
		changedDetailsu.add(listd1);

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "SuspenseHistory", 2))) {
			wait(5);
			driver.findElement(oipa_history_expander).click();
			wait(5);
			if (verifyHistoryDetailsTable(changedDetailsd)) {
				System.out.println("Suspense Delete Success");
				Assert.assertTrue(true);
			} else {
				System.out.println("Suspense Deleted Details- Fail!");
				takeScreenShot(driver, "Suspense Delete");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Suspense-Delete-Fail!");
			takeScreenShot(driver, "Suspense Delete");
			Assert.assertTrue(false);
		}

	}

	@Test
	public void testAllocationHistory() throws IOException {
		System.out.println("***** Start: Verify Allocation History*********");
		createVdaPolicy();
		wait(5);
		driver.findElement(oipa_allocation_sidelist).click();
		wait(5);
		driver.findElement(oipa_allocation_type).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "AllocationType", 2));
		driver.findElement(oipa_allocation_click_to_add).click();
		wait(5);
		driver.findElement(oipa_allocation_fund_type).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "FundType", 2));
		wait(3);
		driver.findElement(oipa_allocation_fund_percent1).clear();
		driver.findElement(oipa_allocation_fund_percent1)
				.sendKeys(xls.getCellData("HistoryData", "AllocationPercent", 2));
		wait(5);
		driver.findElement(oipa_allocation_save).click();

		/* Add test data details to list then add that list to main list */

		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("FundGUID");
		lista1.add("-");
		lista1.add(xls.getCellData("HistoryData", "FundType", 2));
		lista2.add("AllocationPercent");
		lista2.add("-");
		lista2.add("1");
		changedDetailsa.add(lista1);
		changedDetailsa.add(lista2);
		System.out.println("--------Add Allocation--------");
		/* Add test data details to list then add that list to main list */
		driver.findElement(oipa_allocation_history_tab).click();
		wait(5);
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "AllocationHistory", 2))) {

			driver.findElement(oipa_gc_history_expander).click();
			wait(5);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Allocation add history succsess");
				takeScreenShot(driver, "Allocation Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("Allocation history details are wrong!");
				takeScreenShot(driver, "Allocation Add");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Allocation add history operation failed");
			takeScreenShot(driver, "Allocation Add");
			Assert.assertTrue(false);
		}

		driver.findElement(oipa_allocation_tab).click();
		wait(5);
		driver.findElement(oipa_allocation_saved_fund).click();
		wait(5);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "FundType", 3));
		wait(5);
		driver.findElement(oipa_allocation_save).click();

		/* Add test data details to list then add that list to main list */
		System.out.println("---------------Update Allocation--------------");
		List<String> listu1 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("FundGUID");
		listu1.add(xls.getCellData("HistoryData", "FundType", 2));
		listu1.add(xls.getCellData("HistoryData", "FundType", 3));
		changedDetailsu.add(listu1);
		/* Add test data details to list then add that list to main list */
		driver.findElement(oipa_allocation_history_tab).click();
		wait(5);

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "AllocationHistory", 2))) {

			driver.findElement(oipa_gc_history_expander).click();
			wait(5);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Allocation update history operation succsessful");
				takeScreenShot(driver, "Allocation Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("Allocation update history details are wrong!");
				takeScreenShot(driver, "Allocation Update");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Allocation update history operation failed");
			takeScreenShot(driver, "Allocation Update");
			Assert.assertTrue(false);
		}
		System.out.println("--------Delete Allocation---------");

		driver.findElement(oipa_allocation_tab).click();
		wait(5);
		driver.findElement(oipa_allocation_delete).click();
		wait(5);
		driver.switchTo().activeElement();
		wait(3);
		driver.findElement(oipa_allocation_delete_ok).click();
		wait(5);
		driver.findElement(oipa_allocation_save).click();
		driver.findElement(oipa_allocation_history_tab).click();
		wait(5);
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryDelete", 2),
				xls.getCellData("HistoryData", "AllocationHistory", 2))) {
			System.out.println("Delete allocation is successful!");
			takeScreenShot(driver, "Allocation Delete");
			Assert.assertTrue(true);
		} else {
			System.out.println("Delete allocation got failed!");
			takeScreenShot(driver, "Allocation Delete");
			Assert.assertTrue(false);
		}
		System.out.println("***** End: Verify Allocation History*********");
	}

	@Test()
	public void testAgreementHistory() throws IOException {
		System.out.println("***** Start: Verify Agreement History*********");
		createGroupCustomer();
		wait(10);
		driver.findElement(oipa_agreement_sidelist).click();
		wait(5);
		driver.findElement(oipa_agreement_bl).click();
		wait(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(oipa_agreement_new_bl)).click().build().perform();
		wait(5);
		String agn = driver.findElement(oipa_agreement_name).getAttribute("value");
		String agName = "Agreement";
		if (agn == null) {
			driver.findElement(oipa_agreement_name).sendKeys(agName);
			agn = agName;
		}
		driver.findElement(oipa_agreement_status).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "AgreementStatus", 2));
		driver.findElement(oipa_agreement_save).click();
		wait(10);
		System.out.println("---------Agreement Add----------");
		/* Add test data details to list then add that list to main list */

		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("AgreementName");
		lista1.add("-");
		lista1.add(agn);
		lista2.add("StatusCode");
		lista2.add("-");
		lista2.add(xls.getCellData("HistoryData", "AgreementStatus", 2));
		changedDetailsa.add(lista1);
		changedDetailsa.add(lista2);
		System.out.println(changedDetailsa);
		/* Add test data details to list then add that list to main list */

		driver.findElement(oipa_agreement_history).click();

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "AgreementHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Agreement-Add-Success");
				takeScreenShot(driver, "Agreement Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("Agreement-Add-HistoryDetails-Fail");
				takeScreenShot(driver, "Agreement Add");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Agreement-Add-HistoryType-Fail");
			takeScreenShot(driver, "Agreement Add");
			Assert.assertTrue(false);
		}

		System.out.println("---------Agreement Update--------------");
		driver.findElement(oipa_agreement_agreements).click();
		wait(10);
		String old = driver.findElement(oipa_agreement_status).getText();
		wait(8);
		driver.findElement(oipa_agreement_status).click();
		wait(1);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "AgreementStatus", 3));
		wait(1);
		driver.findElement(oipa_agreement_save).click();
		wait(10);

		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("StatusCode");
		listu1.add(old);
		listu1.add(xls.getCellData("HistoryData", "AgreementStatus", 3));
		changedDetailsu.add(listu1);

		/* Add test data details to list then add that list to main list */
		System.out.println("uplist: " + changedDetailsu);
		driver.findElement(oipa_agreement_history).click();

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "AgreementHistory", 2))) {
			wait(10);
			driver.findElement(oipa_history_expander).click();
			wait(10);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Agreement-Update-Success");
				takeScreenShot(driver, "Agreement Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("Agreement-Update-HistoryDetails-Fail");
				takeScreenShot(driver, "Agreement Update");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Agreement-Update-HistoryType-Fail");
			takeScreenShot(driver, "Agreement Update");
			Assert.assertTrue(false);
		}

		System.out.println("---------Agreement Delete--------------");
		wait(5);
		driver.findElement(oipa_agreement_agreements).click();
		wait(5);
		driver.findElement(oipa_agreement_delete).click();
		wait(5);
		driver.switchTo().activeElement();
		wait(5);
		driver.findElement(oipa_agreement_delete_ok).click();
		wait(5);
		driver.findElement(oipa_agreement_history).click();

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryDelete", 2),
				xls.getCellData("HistoryData", "AgreementHistory", 2))) {

			System.out.println("Agreement-Delete-HistoryDetails-Success");
			Assert.assertTrue(true);
		} else {
			System.out.println("Agreement-Delete-HistoryType-Fail");
			Assert.assertTrue(false);

		}
		System.out.println("***** End: Verify Agreement History*********");
	}

	@Test
	public void testGroupCustomerHistory() throws IOException {
		System.out.println("***** Start: Verify Group Customer History*********");
		createGroupCustomer();
		wait(10);
		driver.findElement(oipa_gc_history_tab).click();
		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("CustomerLegalName");
		lista1.add("-");
		lista1.add(customerName);
		lista2.add("CustomerNumber");
		lista2.add("-");
		lista2.add(customerName);
		changedDetailsa.add(lista1);
		changedDetailsa.add(lista2);
		/* Add test data details to list then add that list to main list */
		System.out.println("---------Verifying GC History Add-------------");

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "GroupCustomerHistory", 2))) {
			wait(5);
			driver.findElement(oipa_history_expander).click();
			wait(5);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("GC History Successfully Verified!");
				takeScreenShot(driver, "GC History Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("GC History Details Failed!");
				takeScreenShot(driver, "GC History Add");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("GC History Operation Type Failed!");
			takeScreenShot(driver, "GC History Add");
			Assert.assertTrue(false);
		}
		wait(5);
		driver.findElement(oipa_gc_customer_tab).click();
		wait(5);
		String old_taxid = driver.findElement(oipa_gc_taxid).getAttribute("value");
		String old_customer_number = driver.findElement(oipa_gc_customernumber).getAttribute("value");
		wait(3);
		String new_taxid = String.valueOf(1000 + rnd.nextInt(900000));
		String new_customer_number = "Test" + rnd.nextInt(900);
		wait(3);
		// Changing TaxID
		driver.findElement(oipa_gc_customernumber).clear();
		wait(3);
		driver.findElement(oipa_gc_customernumber).sendKeys(new_customer_number);
		wait(3);
		// Changing TaxID
		driver.findElement(oipa_gc_taxid).clear();
		wait(3);
		driver.findElement(oipa_gc_taxid).sendKeys(new_taxid);
		wait(3);
		driver.findElement(oipa_gc_save_button).click();
		wait(1);
		driver.findElement(oipa_gc_history_tab).click();
		wait(5);

		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<String> listu2 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("TaxID");
		listu1.add(old_taxid);
		listu1.add(new_taxid);
		listu2.add("CustomerNumber");
		listu2.add(old_customer_number);
		listu2.add(new_customer_number);
		changedDetailsu.add(listu1);
		changedDetailsu.add(listu2);

		/* Add test data details to list then add that list to main list */

		System.out.println("------Verifying GC History-------");

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "GroupCustomerHistory", 2))) {
			wait(5);
			driver.findElement(oipa_history_expander).click();
			wait(5);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("GC History Successfully Verified!");
				takeScreenShot(driver, "GC History Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("GC History Details Failed!");
				takeScreenShot(driver, "GC History Update");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("GC History Operation Type Failed!");
			takeScreenShot(driver, "GC History Update");
			Assert.assertTrue(false);
		}
		System.out.println("***** End: Verify Group Customer History******");
	}

	@Test()
	public void testGroupCustomerAddressHistory() throws IOException {
		System.out.println("** Start: Verify Group Customer Address History*****");
		createGroupCustomer();
		wait(10);
		driver.findElement(oipa_gc_address).click();
		wait(3);
		// Adding a new address
		driver.findElement(oipa_gc_add_address).click();
		wait(5);
		driver.findElement(oipa_gc_select_address_type).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "AddressType", 3));
		wait(3);
		driver.findElement(oipa_gc_select_address_country).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "Country", 2));
		wait(3);
		driver.findElement(oipa_gc_address_line1)
				.sendKeys(xls.getCellData("HistoryData", "AddressLine", 2) + rnd.nextInt(2));
		wait(3);
		driver.findElement(oipa_gc_address_line2)
				.sendKeys(xls.getCellData("HistoryData", "AddressLine", 2) + rnd.nextInt(5));
		wait(3);
		driver.findElement(oipa_gc_address_line3)
				.sendKeys(xls.getCellData("HistoryData", "AddressLine", 2) + rnd.nextInt(5));
		wait(3);
		driver.findElement(oipa_gc_address_line4)
				.sendKeys(xls.getCellData("HistoryData", "AddressLine", 2) + rnd.nextInt(5));
		wait(3);
		driver.findElement(oipa_gc_address_city).sendKeys(xls.getCellData("HistoryData", "City", 2));
		wait(3);
		driver.findElement(oipa_gc_address_state).click();
		wait(3);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "State", 2));
		wait(3);
		driver.findElement(oipa_gc_address_zip).sendKeys(xls.getCellData("HistoryData", "Zip", 2));
		wait(3);
		driver.findElement(oipa_gc_address_email).sendKeys(xls.getCellData("HistoryData", "Email", 2));
		wait(3);
		driver.findElement(oipa_gc_address_fax).sendKeys(xls.getCellData("HistoryData", "FaxNumber", 2));
		wait(3);
		driver.findElement(oipa_gc_address_effective_date).clear();
		wait(3);
		driver.findElement(oipa_gc_address_effective_date).sendKeys(xls.getCellData("HistoryData", "EffectiveDate", 2));
		wait(3);
		driver.findElement(oipa_gc_address_expiration_date).clear();
		wait(3);
		driver.findElement(oipa_gc_address_expiration_date).sendKeys(xls.getCellData("HistoryData", "EndDate", 2));
		wait(5);
		driver.findElement(oipa_gc_address_save_button).click();
		wait(5);
		driver.findElement(oipa_gc_customer_history_tab).click();
		wait(5);

		/* Add test data details to list then add that list to main list */

		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<List> changedDetails = new ArrayList<List>();
		list1.add("Email");
		list1.add("-");
		list1.add(xls.getCellData("HistoryData", "Email", 2));
		list2.add("City");
		list2.add("-");
		list2.add(xls.getCellData("HistoryData", "City", 2));
		changedDetails.add(list1);
		changedDetails.add(list2);

		/* Add test data details to list then add that list to main list */

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "GroupCustomerAddressHistory", 2))) {
			wait(5);
			driver.findElement(oipa_history_expander).click();
			wait(5);
			if (verifyHistoryDetailsTable(changedDetails)) {
				System.out.println("New Address Added Successfully & History Verified !");
				System.out.println("INFO!: Add -Operation verified successfully!");
				takeScreenShot(driver, "GC Address History Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("INFO!: Add - Operation details are wrong!");
				takeScreenShot(driver, "GC Address History Add");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("History details are wrong!");
			takeScreenShot(driver, "GC Address History Add");
			Assert.assertTrue(false);
		}

		System.out.println("-----Verifying Update operation in GC Address Histoty------");

		System.out.println("Updating recently added address");
		wait(3);
		driver.findElement(oipa_gc_customer_address_tab).click();
		wait(3);
		driver.findElement(oipa_gc_address_expander).click();
		wait(3);
		String old_adl1 = driver.findElement(oipa_gc_address_line1).getAttribute("value");
		String new_adl1 = old_adl1 + rnd.nextInt(100);
		driver.findElement(oipa_gc_address_line1).clear();
		wait(1);
		driver.findElement(oipa_gc_address_line1).sendKeys(new_adl1);
		wait(1);
		String old_adl2 = driver.findElement(oipa_gc_address_line2).getAttribute("value");
		String new_adl2 = old_adl2 + rnd.nextInt(100);
		driver.findElement(oipa_gc_address_line2).clear();
		wait(1);
		driver.findElement(oipa_gc_address_line2).sendKeys(new_adl2);
		wait(1);
		try {
			driver.findElement(oipa_gc_address_letter_mail_date).clear();
			wait(1);
			driver.findElement(oipa_gc_address_letter_mail_date)
					.sendKeys(xls.getCellData("HistoryData", "LetterMailDate", 2));
			wait(1);
		} catch (Exception e) {
			System.out.println("LetterMailDate is not present for this client");
		}
		driver.findElement(oipa_gc_address_save_button).click();
		wait(5);
		driver.findElement(oipa_gc_customer_history_tab).click();
		wait(3);

		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<String> listu2 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("AddressLine1");
		listu1.add(old_adl1);
		listu1.add(new_adl1);
		listu2.add("AddressLine2");
		listu2.add(old_adl2);
		listu2.add(new_adl2);
		changedDetailsu.add(listu1);
		changedDetailsu.add(listu2);

		/* Add test data details to list then add that list to main list */
		System.out.println(changedDetailsu);
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "GroupCustomerAddressHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("GC Address Update Success!");
				takeScreenShot(driver, "GC Address History Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("GC Address Update Fail!");
				takeScreenShot(driver, "GC Address History Update");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("History details are wrong!");
			takeScreenShot(driver, "GC Address History Update");
			Assert.assertTrue(false);
		}

		System.out.println("*** End: Verify Group Customer Address History****");
	}

	@Test()
	public void testIntakeHistory() throws IOException {
		System.out.println("***** Start: Verify intake profile history******");
		createGroupCustomer();
		wait(10);
		driver.findElement(oipa_dataintake_sidelist).click();
		wait(5);
		driver.findElement(oipa_dataintake_profile).click();
		wait(1);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "IntakeProfile", 2));
		wait(1);
		driver.findElement(oipa_dataintake_add_profile).click();
		wait(10);
		driver.findElement(oipa_dataintake_profile_name).click();
		wait(3);
		driver.findElement(oipa_dataintake_profile_name).clear();
		wait(3);
		driver.findElement(oipa_dataintake_profile_name)
				.sendKeys(xls.getCellData("HistoryData", "IntakeProfileName", 2));
		wait(3);

		driver.findElement(oipa_dataintake_record_member_id).click();
		wait(3);
		driver.findElement(oipa_dataintake_record_member_id).clear();
		wait(3);
		driver.findElement(oipa_dataintake_record_member_id)
				.sendKeys(xls.getCellData("HistoryData", "IntakeRecordMemberId", 2));
		wait(3);

		driver.findElement(oipa_dataintake_effective_date).click();
		wait(3);
		driver.findElement(oipa_dataintake_effective_date).clear();
		wait(3);
		driver.findElement(oipa_dataintake_effective_date).sendKeys(xls.getCellData("HistoryData", "EffectiveDate", 2));
		wait(3);
		driver.findElement(oipa_dataintake_save).click();
		wait(5);

		System.out.println("---------Intake Add--------------");

		/* Add test data details to list then add that list to main list */

		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("IntakeProfileName");
		lista1.add("-");
		lista1.add(xls.getCellData("HistoryData", "IntakeProfileName", 2));
		lista2.add("RecordMemberIdField");
		lista2.add("-");
		lista2.add(xls.getCellData("HistoryData", "IntakeRecordMemberId", 2));
		changedDetailsa.add(lista1);
		changedDetailsa.add(lista2);
		System.out.println(changedDetailsa);

		driver.findElement(oipa_dataintake_history).click();
		wait(10);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "IntakeProfileHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Intake-Add-Success");
				takeScreenShot(driver, "Intake-Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("Intake-Add-HistoryDetails-Fail");
				takeScreenShot(driver, "Intake-Add");
				Assert.assertTrue(false);
			}

		}

		else {
			System.out.println("Intake-Add-HistoryType-Fail");
			takeScreenShot(driver, "Intake-Add");
			Assert.assertTrue(false);
		}

		driver.findElement(oipa_dataintake_profiles).click();
		wait(10);
		driver.findElement(oipa_dataintake_profile_expand).click();
		wait(5);

		String old_profile_name = driver.findElement(oipa_dataintake_profile_name).getAttribute("value");
		wait(3);
		driver.findElement(oipa_dataintake_profile_name).click();
		wait(3);
		driver.findElement(oipa_dataintake_profile_name).clear();
		wait(3);
		String new_profile_name = old_profile_name + rnd.nextInt(999);
		driver.findElement(oipa_dataintake_profile_name).sendKeys(new_profile_name);
		wait(3);
		String old_record_member_id = driver.findElement(oipa_dataintake_record_member_id).getAttribute("value");
		String new_record_member_id = old_record_member_id + rnd.nextInt(999);
		wait(3);
		driver.findElement(oipa_dataintake_record_member_id).click();
		wait(3);
		driver.findElement(oipa_dataintake_record_member_id).clear();
		wait(3);
		driver.findElement(oipa_dataintake_record_member_id).sendKeys(new_record_member_id);
		wait(3);
		driver.findElement(oipa_dataintake_save).click();
		wait(5);

		System.out.println("---------Intake Update------------");

		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<String> listu2 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("IntakeProfileName");
		listu1.add(old_profile_name);
		listu1.add(new_profile_name);
		listu2.add("RecordMemberIdField");
		listu2.add(old_record_member_id);
		listu2.add(new_record_member_id);
		changedDetailsu.add(listu1);
		changedDetailsu.add(listu2);
		System.out.println(changedDetailsu);

		driver.findElement(oipa_dataintake_history).click();
		wait(10);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "IntakeProfileHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Intake-Update-Success");
				takeScreenShot(driver, "Intake-Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("Intake-Update-HistoryDetails-Fail");
				takeScreenShot(driver, "Intake-Update");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Intake-Update-HistoryType-Fail");
			takeScreenShot(driver, "Intake-Update");
			Assert.assertTrue(false);
		}

		System.out.println("---------Intake Delete--------------");
		driver.findElement(oipa_dataintake_profiles).click();
		wait(3);
		driver.findElement(oipa_dataintake_delete).click();
		wait(3);
		driver.switchTo().activeElement();
		wait(3);
		driver.findElement(oipa_dataintake_delete_ok).click();
		wait(3);
		driver.findElement(oipa_dataintake_history).click();

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryDelete", 2),
				xls.getCellData("HistoryData", "IntakeProfileHistory", 2))) {
			System.out.println("Intake-Delete-HistoryDetails-Success");
			takeScreenShot(driver, "Intake-Delete");
			Assert.assertTrue(true);
		} else {
			System.out.println("Intake-Delete-HistoryType-Fail");
			takeScreenShot(driver, "Intake-Delete");
			Assert.assertTrue(false);

		}
		System.out.println("***** End: Verify intake profile history******");
	}

	@Test
	public void testRoleHistory() throws IOException {
		System.out.println("***** Start: Verify Role History******");
		wait(5);
		createVdaPolicy();
		wait(5);
		addRoleToPolicy();
		wait(5);
		driver.findElement(oipa_policy_role_client_history).click();
		wait(5);
		/* Add test data details to list then add that list to main list */
		List<String> lista1 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("RoleCode");
		lista1.add("-");
		lista1.add(xls.getCellData("HistoryData", "RoleCode", 2));
		changedDetailsa.add(lista1);
		/* Add test data details to list then add that list to main list */
		System.out.println("---------------Verifying Adding a role ----------------!");
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2), xls.getCellData("HistoryData", "RoleHistory", 2))) {
			System.out.println("history operation validated");
			driver.findElement(oipa_history_expander).click();
			wait(5);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("history details validated");
				takeScreenShot(driver, "Role History Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("history details not validated");
				takeScreenShot(driver, "Role History Add");
				Assert.assertTrue(false);
			}

		}

		else {
			System.out.println("history operation failed");
		}

		System.out.println("---------Verifying Delete a role -----------!");

		driver.findElement(oipa_policy_role_client_tab).click();
		wait(5);
		driver.findElement(oipa_policy_role_fourlines).click();
		wait(5);
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(oipa_policy_role_delete);
		action.moveToElement(we).click().build().perform();
		driver.switchTo().activeElement();
		wait(3);
		driver.findElement(oipa_role_delete_ok).click();
		wait(5);

		driver.findElement(oipa_policy_role_client_history).click();
		wait(5);

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryDelete", 2), xls.getCellData("HistoryData", "RoleHistory", 2))) {
			System.out.println("Role History Delete Success");
			takeScreenShot(driver, "Role History Delete");
			Assert.assertTrue(true);
		}

		else {
			System.out.println("Role History Delete Fail");
			takeScreenShot(driver, "Role History Delete");
			Assert.assertTrue(false);
		}
		System.out.println("***** End: Verify Role History******");
	}

	@Test
	public void testRequirementHistory() {
		createFppPolicy();
		wait(5);
		addInsuredRole();
		driver.findElement(oipa_policy_req_sidelist).click();
		wait(5);
		driver.findElement(oipa_policy_add_req).click();
		wait(5);
		driver.switchTo().activeElement();
		driver.findElement(oipa_policy_reqDD).click();
		wait(1);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "RequirementType", 2));
		wait(5);
		driver.findElement(oipa_policy_req_vendor_name).sendKeys(xls.getCellData("HistoryData", "VendorName", 2));
		wait(5);
		driver.findElement(oipa_policy_req_vendor_phone).sendKeys(xls.getCellData("HistoryData", "VendorPhone", 2));
		wait(5);
		driver.findElement(oipa_policy_req_ok).click();
		wait(9);
		driver.findElement(oipa_policy_req_history).click();

		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("VendorName");
		lista1.add("-");
		lista1.add(xls.getCellData("HistoryData", "VendorName", 2));
		lista2.add("Phone");
		lista2.add("-");
		lista2.add(xls.getCellData("HistoryData", "VendorPhone", 2));
		changedDetailsa.add(lista1);
		changedDetailsa.add(lista2);
		System.out.println(changedDetailsa);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "RequirementHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Policy Requirement-Add-Success");
				Assert.assertTrue(true);
			} else {
				System.out.println("Policy Requirement-Add-HistoryDetails-Fail");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Policy Requirement-Add-HistoryType-Fail");
			Assert.assertTrue(false);
		}
		driver.findElement(oipa_policy_req_allreqs).click();
		wait(5);
		driver.findElement(oipa_policy_req_expander).click();
		wait(5);
		String old_vendor_name = driver.findElement(oipa_policy_req_vendor_name).getAttribute("value");
		wait(5);
		driver.findElement(oipa_policy_req_vendor_name).clear();
		wait(5);
		String new_vendor_name = old_vendor_name + rnd.nextInt(999);
		driver.findElement(oipa_policy_req_vendor_name).sendKeys(new_vendor_name);
		wait(5);
		driver.findElement(oipa_policy_req_ok).click();

		driver.findElement(oipa_policy_req_history).click();

		List<String> listu1 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("VendorName");
		listu1.add(old_vendor_name);
		listu1.add(new_vendor_name);
		changedDetailsu.add(listu1);
		System.out.println(changedDetailsu);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "RequirementHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Policy Requirement-Update-Success");
				Assert.assertTrue(true);
			} else {
				System.out.println("Policy Requirement-Update-HistoryDetails-Fail");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Policy Requirement-Update-HistoryType-Fail");
			Assert.assertTrue(false);
		}

	}

	@Test
	public void testSegmentRoleHistory() throws IOException {
		System.out.println("***** Start: Verify Segment Role History*********");
		createFppPolicy();
		wait(10);
		driver.findElement(oipa_segments_sidelist).click();
		wait(5);
		driver.findElement(oipa_segments_type).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "SegmentType", 3));
		wait(5);
		driver.findElement(oipa_segments_add).click();
		wait(5);
		driver.findElement(oipa_segments_amount).sendKeys(xls.getCellData("HistoryData", "SegmentAmount", 3));
		wait(5);
		driver.findElement(oipa_segments_tobaco_use).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "SegmentTobacoUse", 2));
		wait(5);
		driver.findElement(oipa_segments_text_field).sendKeys(xls.getCellData("HistoryData", "SegmentsTextField", 2));
		wait(5);
		driver.findElement(oipa_segments_save).click();
		wait(15);
		driver.findElement(oipa_segmentrole_expander).click();
		wait(15);
		driver.findElement(oipa_segmentrole_roles).click();
		wait(15);
		addInsuredRoleByFind();
		wait(15);
		driver.findElement(oipa_segmentrole_history).click();
		wait(15);
		System.out.println("-------Segment Role Add------");
		/* Add test data details to list then add that list to main list */

		List<String> lista1 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("StatusCode");
		lista1.add("-");
		lista1.add("Active");
		changedDetailsa.add(lista1);
		System.out.println(changedDetailsa);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "SegmentRoleHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Segment Role-Add-Success");
				takeScreenShot(driver, "Segment Role-Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("Segment Role-Add-HistoryDetails-Fail");
				takeScreenShot(driver, "Segment Role-Add");
				Assert.assertTrue(false);
			}

		} else {
			System.out.println("Segment Role-Add-HistoryType-Fail");
			takeScreenShot(driver, "Segment Role-Add");
			Assert.assertTrue(false);
		}
		wait(5);
		driver.findElement(oipa_segmentrole_roles).click();
		wait(5);
		driver.findElement(oipa_segmentrole_roleview).click();
		wait(5);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "RoleView", 2));
		wait(5);
		driver.findElement(oipa_segmentrole_client_expander).click();
		wait(5);
		System.out.println("-------Segment Role Update------");
		String old_value = driver.findElement(oipa_segmentrole_relationship).getText();
		System.out.println("oldvalue:" + old_value);
		driver.findElement(oipa_segmentrole_relationship).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "RoleRelationship", 2));
		wait(5);
		driver.findElement(oipa_segmentrole_save).click();
		wait(5);

		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("RoleRelationship");
		listu1.add(old_value);
		listu1.add(xls.getCellData("HistoryData", "RoleRelationship", 2));
		changedDetailsu.add(listu1);
		System.out.println(changedDetailsu);
		driver.findElement(oipa_segmentrole_history).click();
		wait(5);
		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "SegmentRoleHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Segment Role-Update-Success");
				takeScreenShot(driver, "Segment Role-Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("Segment Role-Update-HistoryDetails-Fail");
				takeScreenShot(driver, "Segment Role-Update");
				Assert.assertTrue(false);
			}

		}

		else {
			System.out.println("Segment Role-Update-HistoryType-Fail");
			takeScreenShot(driver, "Segment Role-Update");
			Assert.assertTrue(false);
		}

		wait(10);
		driver.findElement(oipa_segmentrole_roles).click();
		wait(10);
		driver.findElement(oipa_segmentrole_roleview).click();
		wait(10);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "RoleView", 2));
		wait(10);

		System.out.println("-------Segment Role Delete------");

		driver.findElement(oipa_segmentrole_linesmenu).click();
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(oipa_segmentrole_delete);
		action.moveToElement(we).click().build().perform();
		wait(3);
		driver.switchTo().activeElement();
		wait(3);
		driver.findElement(oipa_segmentrole_delete_ok).click();
		wait(5);

		driver.findElement(oipa_segmentrole_history).click();
		wait(5);

		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryDelete", 2),
				xls.getCellData("HistoryData", "SegmentRoleHistory", 2))) {
			System.out.println("Segment Role-Delete-Success");
			takeScreenShot(driver, "Segment Role-Delete");
			Assert.assertTrue(true);
		}

		else {
			System.out.println("Segment Role-Delete-Fail");
			takeScreenShot(driver, "Segment Role-Delete");
			Assert.assertTrue(false);
		}
		System.out.println("*****End: Verify Segment Role History******");
	}
	
	@Test
	public void testPolicyImpairmentHistory() throws IOException {
		createFppPolicy();
		wait(5);
		driver.findElement(oipa_assessment_sidelist).click();
		wait(5);
		System.out.println("------Policy Impairment Add-------");
		driver.findElement(oipa_assessment_add_impairment).click();
		wait(5);
		driver.switchTo().activeElement();
		wait(5);
		driver.findElement(oipa_impairment_level).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ImpairmentLevel", 2));
		wait(1);
		driver.findElement(oipa_impairment_category).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ImpairmentCategory", 2));
		wait(1);
		driver.findElement(oipa_impairment_comments).sendKeys(xls.getCellData("HistoryData", "ImpairmentComments", 2));
		wait(1);
		driver.findElement(oipa_impairment_debit).sendKeys(xls.getCellData("HistoryData", "ImpairmentDebit", 2));
		wait(1);
		driver.findElement(oipa_impairment_save).click();
		wait(10);
		driver.findElement(oipa_impairment_history).click();
		wait(10);

		/* Add test data details to list then add that list to main list */

		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("Comments");
		lista1.add("-");
		lista1.add(xls.getCellData("HistoryData", "ImpairmentComments", 2));
		lista2.add("Debit");
		lista2.add("-");
		lista2.add(xls.getCellData("HistoryData", "ImpairmentDebit", 2));
		changedDetailsa.add(lista1);
		changedDetailsa.add(lista2);
		System.out.println(changedDetailsa);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "ImpairmentHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Policy Impairment-Add-Success");
				takeScreenShot(driver, "Policy Impairment-Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("Policy Impairment-Add-HistoryDetails-Fail");
				takeScreenShot(driver, "Policy Impairment-Add");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Policy Impairment-Add-HistoryType-Fail");
			takeScreenShot(driver, "Policy Impairment-Add");
			Assert.assertTrue(false);
		}
		driver.findElement(oipa_impairment_allimpairments).click();
		System.out.println("------Policy Impairment Update-------");
		driver.findElement(oipa_assessment_expand).click();
		wait(5);
		String old_comments = driver.findElement(oipa_impairment_comments).getAttribute("value");
		driver.findElement(oipa_impairment_comments).clear();
		wait(2);
		driver.findElement(oipa_impairment_comments).sendKeys(xls.getCellData("HistoryData", "ImpairmentComments", 3));
		wait(5);
		String old_debit = driver.findElement(oipa_impairment_debit).getAttribute("value");
		wait(5);
		driver.findElement(oipa_impairment_debit).clear();
		wait(2);
		driver.findElement(oipa_impairment_debit).sendKeys(xls.getCellData("HistoryData", "ImpairmentDebit", 3));
		wait(5);
		driver.findElement(oipa_impairment_save).click();
		wait(10);
		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<String> listu2 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("Comments");
		listu1.add(old_comments);
		listu1.add(xls.getCellData("HistoryData", "ImpairmentComments", 3));
		listu2.add("Debit");
		listu2.add(old_debit);
		listu2.add(xls.getCellData("HistoryData", "ImpairmentDebit", 3));
		changedDetailsu.add(listu1);
		changedDetailsu.add(listu2);
		System.out.println(changedDetailsu);

		driver.findElement(oipa_impairment_history).click();
		wait(10);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "ImpairmentHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Policy Impairment-Update-Success");
				takeScreenShot(driver, "Policy Impairment-Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("Policy Impairment-Update-HistoryDetails-Fail");
				takeScreenShot(driver, "Policy Impairment-Update");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Policy Impairment-Update-HistoryType-Fail");
			takeScreenShot(driver, "Policy Impairment-Update");
			Assert.assertTrue(false);
		}
		System.out.println("---------Policy Impairment Delete--------------");
		driver.findElement(oipa_impairment_allimpairments).click();
		wait(3);
		driver.findElement(oipa_impairment_delete).click();
		wait(3);
		driver.switchTo().activeElement();
		wait(3);
		driver.findElement(oipa_impairment_delete_ok).click();
		wait(3);
		driver.findElement(oipa_impairment_history).click();
		List<String> listd1 = new ArrayList<String>();
		List<List> changedDetailsd = new ArrayList<List>();
		listd1.add("StatusCode");
		listd1.add("Active");
		listd1.add("Shadow");
		changedDetailsd.add(listd1);
		System.out.println(changedDetailsu);
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryDelete", 2),
				xls.getCellData("HistoryData", "ImpairmentHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsd)) {
				System.out.println("Policy Impairment-Delete-Success");
				takeScreenShot(driver, "Policy Impairment-Delete");
				Assert.assertTrue(true);
			} else {
				System.out.println("Comments-Update-HistoryDetails-Fail");
				takeScreenShot(driver, "Policy Impairment-Delete");
				Assert.assertTrue(false);
			}
		} else {
			System.out.println("Policy Impairment-Delete-HistoryType-Fail");
			takeScreenShot(driver, "Policy Impairment-Delete");
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testClientImpairmentHistory() throws IOException {
		wait(10);
		createFppPolicy();
		wait(10);
		driver.findElement(oipa_policy_role).click();
		wait(10);
		addInsuredRoleByFind();
		wait(10);
		driver.findElement(oipa_role_roleview).click();
		wait(1);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "RoleView", 2));
		wait(10);
		String insuredClient = driver.findElement(oipa_role_insured_client).getText();
		System.out.println("InsuredClient is:" + insuredClient);
		wait(10);
		driver.findElement(oipa_assessment_sidelist).click();
		wait(5);
		System.out.println("------Client Impairment Add-------");
		driver.findElement(oipa_assessment_add_impairment).click();
		wait(5);
		driver.switchTo().activeElement();
		wait(5);
		driver.findElement(oipa_impairment_level).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ImpairmentLevel", 3));
		wait(3);
		driver.findElement(oipa_impairment_category).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ImpairmentCategory", 2));
		wait(10);
		driver.findElement(oipa_impairment_comments).sendKeys(xls.getCellData("HistoryData", "ImpairmentComments", 2));
		wait(10);
		driver.findElement(oipa_impairment_debit).sendKeys(xls.getCellData("HistoryData", "ImpairmentDebit", 2));
		wait(10);
		String data = driver.findElement(oipa_impairment_insured).getText();
		wait(10);
		driver.findElement(oipa_impairment_save).click();
		wait(10);
		driver.findElement(oipa_impairment_history).click();
		wait(10);

		/* Add test data details to list then add that list to main list */

		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("Comments");
		lista1.add("-");
		lista1.add(xls.getCellData("HistoryData", "ImpairmentComments", 2));
		lista2.add("Debit");
		lista2.add("-");
		lista2.add(xls.getCellData("HistoryData", "ImpairmentDebit", 2));
		changedDetailsa.add(lista1);
		changedDetailsa.add(lista2);
		System.out.println(changedDetailsa);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTableWithDetailsColoumn(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2), xls.getCellData("HistoryData", "ImpairmentHistory", 2),
				insuredClient)) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Client Impairment-Add-Success");
				takeScreenShot(driver, "Client Impairment Add");
				Assert.assertTrue(true);
			} else {
				System.out.println("Client Impairment-Add-HistoryDetails-Fail");
				takeScreenShot(driver, "Client Impairment Add");
				Assert.assertTrue(false);
			}

		}

		else {
			System.out.println("Client Impairment-Add-HistoryType-Fail");
			takeScreenShot(driver, "Client Impairment Add");
			Assert.assertTrue(false);
		}

		driver.findElement(oipa_impairment_allimpairments).click();
		System.out.println("------Client Impairment Update-------");
		driver.findElement(oipa_assessment_expand).click();

		wait(5);
		String old_comments = driver.findElement(oipa_impairment_comments).getAttribute("value");
		driver.findElement(oipa_impairment_comments).clear();
		wait(2);
		driver.findElement(oipa_impairment_comments).sendKeys(xls.getCellData("HistoryData", "ImpairmentComments", 3));
		wait(5);
		String old_debit = driver.findElement(oipa_impairment_debit).getAttribute("value");
		wait(5);
		driver.findElement(oipa_impairment_debit).clear();
		wait(2);
		driver.findElement(oipa_impairment_debit).sendKeys(xls.getCellData("HistoryData", "ImpairmentDebit", 3));
		wait(5);
		driver.findElement(oipa_impairment_save).click();
		wait(5);
		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<String> listu2 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("Comments");
		listu1.add(old_comments);
		listu1.add(xls.getCellData("HistoryData", "ImpairmentComments", 3));
		listu2.add("Debit");
		listu2.add(old_debit);
		listu2.add(xls.getCellData("HistoryData", "ImpairmentDebit", 3));
		changedDetailsu.add(listu1);
		changedDetailsu.add(listu2);
		System.out.println(changedDetailsu);

		driver.findElement(oipa_impairment_history).click();
		wait(10);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTableWithDetailsColoumn(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "ImpairmentHistory", 2), insuredClient)) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Client Impairment-Update-Success");
				takeScreenShot(driver, "Client Impairment Update");
				Assert.assertTrue(true);
			} else {
				System.out.println("Client Impairment-Update-HistoryDetails-Fail");
				takeScreenShot(driver, "Client Impairment Update");
				Assert.assertTrue(false);
			}

		}

		else {
			System.out.println("Client Impairment-Update-HistoryType-Fail");
			takeScreenShot(driver, "Client Impairment Update");
			Assert.assertTrue(false);
		}

		System.out.println("---------Client Impairment Delete--------------");
		driver.findElement(oipa_impairment_allimpairments).click();
		wait(3);
		driver.findElement(oipa_impairment_delete).click();
		wait(3);
		driver.switchTo().activeElement();
		wait(3);
		driver.findElement(oipa_impairment_delete_ok).click();
		wait(3);
		driver.findElement(oipa_impairment_history).click();
		List<String> listd1 = new ArrayList<String>();
		List<List> changedDetailsd = new ArrayList<List>();
		listd1.add("StatusCode");
		listd1.add("Active");
		listd1.add("Shadow");
		changedDetailsd.add(listd1);
		System.out.println(changedDetailsu);
		if (verifyHistoryTableWithDetailsColoumn(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryDelete", 2),
				xls.getCellData("HistoryData", "ImpairmentHistory", 2), insuredClient)) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsd)) {
				System.out.println("Client Impairment-Delete-Success");
				takeScreenShot(driver, "Client Impairment Delete");
				Assert.assertTrue(true);
			} else {
				System.out.println("Comments-Update-HistoryDetails-Fail");
				takeScreenShot(driver, "Client Impairment-Delete");
				Assert.assertTrue(false);
			}
		}

		else {
			System.out.println("Client Impairment-Delete-HistoryType-Fail");
			takeScreenShot(driver, "Client Impairment-Delete");
			Assert.assertTrue(false);

		}

	}

	@Test
	public void testSegmentImpairmentHistory() throws IOException {
		wait(10);
		createFppPolicy();
		wait(10);
		driver.findElement(oipa_segments_sidelist).click();
		wait(5);
		driver.findElement(oipa_segments_type).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "SegmentType", 3));
		wait(5);
		driver.findElement(oipa_segments_add).click();
		wait(5);
		driver.findElement(oipa_segments_amount).sendKeys(xls.getCellData("HistoryData", "SegmentAmount", 3));
		wait(5);
		driver.findElement(oipa_segments_tobaco_use).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "SegmentTobacoUse", 2));
		wait(5);
		driver.findElement(oipa_segments_text_field)
				.sendKeys(xls.getCellData("HistoryData", "SegmentsTextField",2));
		wait(5);
		driver.findElement(oipa_segments_save).click();
		wait(10);
		driver.findElement(oipa_assessment_sidelist).click();
		wait(10);
		System.out.println("------Segment Impairment Add-------");
		driver.findElement(oipa_assessment_add_impairment).click();
		wait(10);
		driver.switchTo().activeElement();
		wait(5);
		driver.findElement(oipa_impairment_level).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ImpairmentLevel", 4));
		wait(5);
		driver.findElement(oipa_impairment_category).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ImpairmentCategory", 2));
		wait(5);
		driver.findElement(oipa_impairment_comments)
				.sendKeys(xls.getCellData("HistoryData", "ImpairmentComments", 2));
		wait(5);
		driver.findElement(oipa_impairment_debit).sendKeys(xls.getCellData("HistoryData", "ImpairmentDebit", 2));
		wait(5);

		driver.findElement(oipa_impairment_insured).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "SegmentType", 3));
		wait(5);

		String data = driver.findElement(oipa_impairment_insured).getText();
		wait(5);
		driver.findElement(oipa_impairment_save).click();
		wait(5);
		driver.findElement(oipa_impairment_history).click();
		wait(5);

		/* Add test data details to list then add that list to main list */

		List<String> lista1 = new ArrayList<String>();
		List<String> lista2 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("Comments");
		lista1.add("-");
		lista1.add(xls.getCellData("HistoryData", "ImpairmentComments", 2));
		lista2.add("Debit");
		lista2.add("-");
		lista2.add(xls.getCellData("HistoryData", "ImpairmentDebit", 2));
		changedDetailsa.add(lista1);
		changedDetailsa.add(lista2);
		System.out.println(changedDetailsa);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTableWithDetailsColoumn(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "ImpairmentHistory", 2), data)) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Segment Impairment-Add-Success");
				Assert.assertTrue(true);
			} else {
				System.out.println("Segment Impairment-Add-HistoryDetails-Fail");
				Assert.assertTrue(false);
			}

		}

		else {
			System.out.println("Segment Impairment-Add-HistoryType-Fail");
			Assert.assertTrue(false);
		}

		driver.findElement(oipa_impairment_allimpairments).click();
		System.out.println("------Segment Impairment Update-------");
		driver.findElement(oipa_assessment_expand).click();

		wait(5);
		String old_comments = driver.findElement(oipa_impairment_comments).getAttribute("value");
		driver.findElement(oipa_impairment_comments).clear();
		wait(2);
		driver.findElement(oipa_impairment_comments)
				.sendKeys(xls.getCellData("HistoryData", "ImpairmentComments", 3));
		wait(5);
		String old_debit = driver.findElement(oipa_impairment_debit).getAttribute("value");
		wait(5);
		driver.findElement(oipa_impairment_debit).clear();
		wait(2);
		driver.findElement(oipa_impairment_debit).sendKeys(xls.getCellData("HistoryData", "ImpairmentDebit",3));
		wait(5);
		driver.findElement(oipa_impairment_save).click();
		wait(5);
		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<String> listu2 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("Comments");
		listu1.add(old_comments);
		listu1.add(xls.getCellData("HistoryData", "ImpairmentComments",3));
		listu2.add("Debit");
		listu2.add(old_debit);
		listu2.add(xls.getCellData("HistoryData", "ImpairmentDebit",3));
		changedDetailsu.add(listu1);
		changedDetailsu.add(listu2);
		System.out.println(changedDetailsu);

		driver.findElement(oipa_impairment_history).click();
		wait(10);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTableWithDetailsColoumn(xls.getCellData("HistoryData", "ClientNumber",2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "ImpairmentHistory", 2), data)) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Segment Impairment-Update-Success");
				Assert.assertTrue(true);
			} else {
				System.out.println("Segment Impairment-Update-HistoryDetails-Fail");
				Assert.assertTrue(false);
			}

		}

		else {
			System.out.println("Segment Impairment-Update-HistoryType-Fail");
			Assert.assertTrue(false);
		}

		System.out.println("---------Segment Impairment Delete--------------");
		driver.findElement(oipa_impairment_allimpairments).click();
		wait(3);
		driver.findElement(oipa_impairment_delete).click();
		wait(3);
		driver.switchTo().activeElement();
		wait(3);
		driver.findElement(oipa_impairment_delete_ok).click();
		wait(3);
		driver.findElement(oipa_impairment_history).click();
		List<String> listd1 = new ArrayList<String>();
		List<List> changedDetailsd = new ArrayList<List>();
		listd1.add("StatusCode");
		listd1.add("Active");
		listd1.add("Shadow");
		changedDetailsd.add(listd1);
		System.out.println(changedDetailsu);
		if (verifyHistoryTableWithDetailsColoumn(xls.getCellData("HistoryData", "ClientNumber",2),
				xls.getCellData("HistoryData", "HistoryDelete",2),
				xls.getCellData("HistoryData", "ImpairmentHistory", 2), data)) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsd)) {
				System.out.println("Segment Impairment-Delete-Success");
				Assert.assertTrue(true);
			} else {
				System.out.println("Segment-Update-HistoryDetails-Fail");
				takeScreenShot(driver, "Segment Impairment-Delete");
				Assert.assertTrue(false);
			}
		}

		else {
			System.out.println("Segment Impairment-Delete-HistoryType-Fail");
			takeScreenShot(driver, "Segment Impairment-Delete");
			Assert.assertTrue(false);

		}

	}
	
	
	
	
	//@Test
	public void testWorkflowHistory() {
		System.out.println("***** Start: Verify workflow history*********");
		wait(5);
		createClient();
		wait(5);	
		String cname=driver.findElement(oipa_client_FirstName).getAttribute("value");
		wait(5);	
		driver.findElement(oipa_workflow_sidelist).click();
		wait(5);
		driver.switchTo().activeElement();
		wait(5);
		driver.findElement(oipa_workflow_sidelist).click();
		wait(5);
		System.out.println("------Client Workflow Add-------");
		driver.findElement(oipa_workflow_date).clear();
		wait(5);
		driver.findElement(oipa_workflow_date).sendKeys(xls.getCellData("HistoryData", "WorkflowDate", 2));

		driver.findElement(oipa_workflow_comments).clear();
		wait(5);
		driver.findElement(oipa_workflow_comments).sendKeys(xls.getCellData("HistoryData", "WorkflowComments", 2));

		driver.findElement(oipa_workflow_taskname).click();
		wait(5);
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "WorkflowTaskType", 2));
		wait(5);
		driver.findElement(oipa_workflow_save).click();
		wait(10);
		driver.findElement(oipa_home).click();

		wait(5);
		driver.findElement(oipa_workflow_filters).click();

		wait(5);
		driver.switchTo().activeElement();
		wait(5);

		driver.findElement(oipa_workflow_filter_name).click();
		wait(5);
		selectItemInDropdown(driver, "Contains");
		wait(5);
		driver.findElement(oipa_workflow_filter_input).sendKeys(cname);
		wait(5);

		driver.findElement(oipa_workflow_filter_ok).click();
		wait(5);

		driver.findElement(oipa_workflow_result_menu).click();
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(oipa_workflow_filter_goto_history);
		action.moveToElement(we).click().build().perform();
		driver.switchTo().activeElement();
		wait(3);

		/* Add test data details to list then add that list to main list */

		List<String> lista1 = new ArrayList<String>();
		List<List> changedDetailsa = new ArrayList<List>();
		lista1.add("TaskType");
		lista1.add("-");
		lista1.add(xls.getCellData("HistoryData", "WorkflowTaskType",2));
		changedDetailsa.add(lista1);
		System.out.println(changedDetailsa);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryAdd", 2),
				xls.getCellData("HistoryData", "WorkflowHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsa)) {
				System.out.println("Client Workflow-Add-Success");
				Assert.assertTrue(true);
			} else {
				System.out.println("Client Workflow-Add-HistoryDetails-Fail");
				try {
					takeScreenShot(driver, "Client Workflow-Add-HistoryDetails-Fail");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Assert.assertTrue(false);
			}

		}

		else {
			System.out.println("Client Workflow-Add-HistoryType-Fail");
			try {
				takeScreenShot(driver, "Client Workflow-Add-HistoryType-Fail");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertTrue(false);
		}

		driver.findElement(oipa_workflow_filter_close_history).click();
		wait(5);
		System.out.println("------Client Workflow Update-------");
		driver.findElement(oipa_workflow_result_menu).click();
		action = new Actions(driver);
		we = driver.findElement(oipa_workflow_edit_task);
		action.moveToElement(we).click().build().perform();
		driver.switchTo().activeElement();
		wait(5);
		driver.findElement(oipa_workflow_assigned_to).click();
		selectItemInDropdown(driver, xls.getCellData("HistoryData", "ClientNumber", 2));
		wait(5);
		driver.findElement(oipa_workflow_save).click();
		wait(10);
		driver.findElement(oipa_workflow_result_menu).click();
		action = new Actions(driver);
		we = driver.findElement(oipa_workflow_filter_goto_history);
		action.moveToElement(we).click().build().perform();
		driver.switchTo().activeElement();
		wait(3);

		/* Add test data details to list then add that list to main list */

		List<String> listu1 = new ArrayList<String>();
		List<List> changedDetailsu = new ArrayList<List>();
		listu1.add("AssignedTo");
		listu1.add("-");
		listu1.add(xls.getCellData("HistoryData", "ClientNumber", 2));
		changedDetailsa.add(listu1);
		System.out.println(changedDetailsa);

		/* Add test data details to list then add that list to main list */
		if (verifyHistoryTable(xls.getCellData("HistoryData", "ClientNumber", 2),
				xls.getCellData("HistoryData", "HistoryUpdate", 2),
				xls.getCellData("HistoryData", "WorkflowHistory", 2))) {
			driver.findElement(oipa_history_expander).click();
			wait(2);
			if (verifyHistoryDetailsTable(changedDetailsu)) {
				System.out.println("Client Workflow-Update-Success");
				Assert.assertTrue(true);
			} else {
				System.out.println("Client Workflow-Update-HistoryDetails-Fail");
				try {
					takeScreenShot(driver, "Client Workflow-Update-HistoryDetails-Fail");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Assert.assertTrue(false);
			}

		}

		else {
			System.out.println("Client Workflow-Update-HistoryType-Fail");
			try {
				takeScreenShot(driver, "Client Workflow-Update-HistoryType-Fail");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertTrue(false);
		}
		System.out.println("***** End: Verify workflow history*********");
	}
	
	

	// Method to handle leave page alert
	@AfterMethod
	public void handleLeavePage() {
		try {
			wait(15);
			driver.switchTo().activeElement();
			if (driver.findElement(oipa_leave_page).isDisplayed()) {
				driver.findElement(oipa_leave_page).click();
				driver.findElement(oipa_home_page).click();
				wait(30);
			}
		} 	
		finally {
			wait(15);
			driver.findElement(oipa_home_page).click();
			wait(30);
		}
	}

	// Method to verify history table
	public Boolean verifyHistoryTable(String clientNumber, String historyOperation, String historyType) {
		String pattern = "MMM d";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		// && driver.findElement(oipa_history_date).getText().contains(date)
		if (driver.findElement(oipa_history_client).getText().equalsIgnoreCase(clientNumber)
				&& driver.findElement(oipa_history_operation).getText().equalsIgnoreCase(historyOperation)
				&& driver.findElement(oipa_history_type).getText().equalsIgnoreCase(historyType)) {
			System.out.println("Success! : History Operation Type");

			return true;
		}

		else {

			System.out.println("Failed! : History Operation Type");
			return false;
		}

	}

	// Method to verify history table details
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Boolean verifyHistoryDetailsTable(List<List> changedDetails) {
		List<WebElement> rows = driver.findElements(oipa_history_details);
		List<List> totalHistoryList = new ArrayList<List>();
		List<String> hist_details = new ArrayList<String>();

		for (int i = 1; i <= rows.size(); i++) {
			hist_details = new ArrayList<String>();
			for (int j = 1; j <= 3; j++) {
				int z = 1 + j;
				hist_details.add(driver
						.findElement(
								By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[" + i + "]//td[" + z + "]"))
						.getText());

			}
			totalHistoryList.add(hist_details);
		}
		if (totalHistoryList.containsAll(changedDetails)) {

			System.out.println("History Details Updated Properly");
			return true;

		} else {
			System.out.println("History Details Not Updated Properly");
			return false;
		}

	}

	// Method to verify history table details with details coloumn
	public Boolean verifyHistoryTableWithDetailsColoumn(String clientNumber, String historyOperation,
			String historyType, String historyDetailsCol) {
		String pattern = "MMM d";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		// && driver.findElement(oipa_history_date).getText().contains(date)
		if (driver.findElement(oipa_history_client).getText().equalsIgnoreCase(clientNumber)
				&& driver.findElement(oipa_history_operation).getText().equalsIgnoreCase(historyOperation)
				&& driver.findElement(oipa_history_type).getText().equalsIgnoreCase(historyType)
				&& driver.findElement(oipa_history_details_col).getText().equalsIgnoreCase(historyDetailsCol)) {
			return true;
		} else {
			return false;
		}
	}
}
