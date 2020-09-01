package com.tc.groupcustomer;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Library.OIPA_Library;

public class GC06_GroupCustomer3 extends OIPA_Library {
	// Tester : Ganesh Balasti
	// Functionality : GC ClassGroup & Overview
	public static WebDriver driver;
	Random rand = new Random();

	/* GC Data */
	String gcName = null;
	String agName = null;
	String agType = null;
	String agStatus = null;
	String planName = null;
	String planStatus = null;
	String planEffdate = null;
	String planExpdate = null;
	String planProduct = null;
	String planCompany = null;
	String classGrpName = null;
	String classGrpStatus = null;
	String classGrpEffdate = null;
	String classGrpExpdate = null;
	String classGrpDesc = null;
	String classGrpType = null;
	String className = null;
	String classType = null;
	String classDesc = null;
	private boolean tc18731Flag = false;
	String gcNameTC27723 = null;
	private String gcNameTC07 = null;

	@BeforeClass
	public void setup() throws Throwable {
		System.out.println("***************GC06 GroupCustomer3******************************");

		openBrowser();
		driver = login(driver1);
		wait(10);
	}

	@Test(dependsOnMethods = "TC11")
	public void TC04() throws IOException {
		System.out.println("TC04 Start");
		wait(5);
		driver.findElement(oipa_search_menu_dropdown).click();
		selectItemInDropdown(driver, "Customer");
		wait(1);
		driver.findElement(oipa_search_input_field).sendKeys(gcNameTC07);
		wait(1);
		driver.findElement(oipa_search_button_icon).click();
		wait(10);
		driver.findElement(oipa_gc_classgroup_sidelist).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander2).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_classes).click();
		wait(5);

		driver.findElement(oipa_gc_classgroup_parent_class).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_add_class).click();
		wait(5);
		driver.switchTo().activeElement();
		wait(5);
		driver.findElement(oipa_gc_classgroup_class_type).click();
		selectItemInDropdown(driver, xls.getCellData("ClassGroupTestData", "ClassGroupType", 2));
		wait(5);
		String className = "Class Name" + rand.nextInt(999);
		driver.findElement(oipa_gc_classgroup_class_name).sendKeys(className);
		wait(5);
		String classDesc = "Class Name" + rand.nextInt(999);
		driver.findElement(oipa_gc_classgroup_class_desc).sendKeys(classDesc);
		wait(5);
		driver.findElement(oipa_gc_classgroup_ok).click();
		wait(5);
		String added_class_name = driver.findElement(oipa_gc_classgroup_added_class).getText();
		if (added_class_name.equalsIgnoreCase(className)) {
			System.out.println("TC04 Pass");
			Assert.assertTrue(true);
		} else {
			System.out.println("TC04 Fail");
			takeScreenShot(driver, "Adding child class from parent");
			Assert.assertTrue(false);
		}

	}

	@Test()
	public void TC07() throws IOException {
		System.out.println("Plan Coverage Test Start");
		gcNameTC07 = createGroupCustomer();
		wait(5);
		addMasterAgreementInsured();
		wait(5);
		addProduct();
		wait(5);
		addPlan();
		wait(5);
		addClassGroup();
		wait(5);
		activatePlan();
		wait(5);
		driver.findElement(oipa_gc_classgroup_sidelist).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander2).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_classes).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_parent_class).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_plan_cov).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_plan_ass).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_plan_seg).click();
		String segInfo = driver.findElement(oipa_gc_classgroup_plan_seg).getText();
		String[] list = segInfo.split(" - ");
		wait(5);
		driver.findElement(oipa_gc_classgroup_mem_arrow).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_cov_type_code).click();
		selectItemInDropdown(driver, xls.getCellData("ClassGroupTestData", "TypeCode", 2));
		wait(5);
		driver.findElement(oipa_gc_classgroup_ass_effectivedate)
				.sendKeys(xls.getCellData("ClassGroupTestData", "EffectiveDate", 2));
		wait(5);
		driver.findElement(oipa_gc_classgroup_ok).click();
		wait(10);

		Assert.assertTrue(list[0].equals(driver.findElement(oipa_gc_classgroup_subplan_name).getText()),
				"Plan coverage - segment name mismatch");
		Assert.assertTrue(
				xls.getCellData("ClassGroupTestData", "EffectiveDate", 2)
						.equals(driver.findElement(oipa_gc_classgroup_subplan_date).getText()),
				"Plan coverage - segment date mismatch");
		Assert.assertTrue(
				xls.getCellData("ClassGroupTestData", "TypeCode", 2)
						.equals(driver.findElement(oipa_gc_classgroup_subplan_typecode).getText()),
				"Plan coverage - segment type code mismatch");
		System.out.println("Plan Coverage Test End");
	}

	@Test(dependsOnMethods = "TC07")
	public void TC10() {
		System.out.println("---Adding class variable---");
		wait(5);
		driver.findElement(oipa_search_menu_dropdown).click();
		selectItemInDropdown(driver, "Customer");
		wait(1);
		driver.findElement(oipa_search_input_field).sendKeys(gcNameTC07);
		wait(1);
		driver.findElement(oipa_search_button_icon).click();
		wait(10);
		driver.findElement(oipa_gc_classgroup_sidelist).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander2).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_classes).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_parent_class).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_variables).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_add_varialble).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_rule_name)
				.sendKeys(xls.getCellData("ClassGroupTestData", "VariableName", 2));
		wait(5);
		driver.findElement(oipa_gc_classgroup_rule_type).click();
		selectItemInDropdown(driver, xls.getCellData("ClassGroupTestData", "VariableType", 2));
		wait(5);
		driver.findElement(oipa_gc_classgroup_rule_datatype).click();
		selectItemInDropdown(driver, xls.getCellData("ClassGroupTestData", "VariableDataType", 2));
		wait(5);
		driver.findElement(oipa_gc_classgroup_rule_text)
				.sendKeys(xls.getCellData("ClassGroupTestData", "VariableText", 2));
		wait(5);
		driver.findElement(oipa_gc_classgroup_save).click();
		wait(10);

		String ruleName = driver.findElement(oipa_gc_classgroup_rule_name_data).getText();
		String ruleType = driver.findElement(oipa_gc_classgroup_rule_type_data).getText();
		String ruleDataType = driver.findElement(oipa_gc_classgroup_rule_datatype_data).getText();
		String ruleText = driver.findElement(oipa_gc_classgroup_rule_text_data).getText();

		if (ruleName.equalsIgnoreCase(xls.getCellData("ClassGroupTestData", "VariableName", 2))
				&& ruleType.equalsIgnoreCase(xls.getCellData("ClassGroupTestData", "VariableType", 2))
				&& ruleDataType.equalsIgnoreCase(xls.getCellData("ClassGroupTestData", "VariableDataType", 2))
				&& ruleText.equalsIgnoreCase(xls.getCellData("ClassGroupTestData", "VariableText", 2))) {

			System.out.println("Class group varibale added successfully!");
			Assert.assertTrue(true);
		} else {
			System.out.println("Adding class group varibale failed!");
			Assert.assertTrue(false);
		}

	}

	@Test(dependsOnMethods = "TC10")
	public void TC11() {
		System.out.println("---Adding membership variable---");
		wait(5);
		driver.findElement(oipa_search_menu_dropdown).click();
		selectItemInDropdown(driver, "Customer");
		wait(1);
		driver.findElement(oipa_search_input_field).sendKeys(gcNameTC07);
		wait(1);
		driver.findElement(oipa_search_button_icon).click();
		wait(10);
		driver.findElement(oipa_gc_classgroup_sidelist).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander2).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_classes).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_parent_class).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_membership_rules).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_mem_condition).clear();
		wait(5);
		driver.findElement(oipa_gc_classgroup_mem_source).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_mem_arrow).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_save).click();
		wait(5);

		if (driver.findElement(oipa_gc_classgroup_mem_source).getText()
				.equals(driver.findElement(oipa_gc_classgroup_mem_condition).getAttribute("value"))) {
			System.out.println("Membership rule addedd successfully!");
			Assert.assertTrue(true);
		} else {
			System.out.println("Adding Membership rule failed!");
			Assert.assertTrue(false);
		}

	}

	@Test()
	public void TC18730() throws IOException {
		/* Prerequisite */
		System.out.println("GC Overview Prerequisite Start!");
		createGroupCustomer();
		wait(5);
		addMasterAgreementInsured();
		wait(5);
		addProduct();
		wait(5);
		addPlan();
		wait(5);
		addClassGroup();
		wait(5);
		activatePlan();
		wait(5);
		activateClassGroup();
		System.out.println("GC Overview Prerequisite End!");
		wait(5);
		driver.findElement(oipa_gc_overview_sidelist).click();
		wait(5);

		try {
			driver.findElement(oipa_gc_overview_format).click();
		} catch (Exception e) {
			e.getSuppressed();
		}

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(oipa_gc_overview_agreements_name));
		takeScreenShot(driver, "GC Overview Agreement Validations");
		System.out.println("Validating GC Overview!");
		// GC Overview Agreement Validations
		Assert.assertEquals(agName, driver.findElement(oipa_gc_overview_agreements_name).getText(),
				"Agreement Name Mismatch");
		Assert.assertEquals(agType, driver.findElement(oipa_gc_overview_agreements_type).getText(),
				"Agreement Type Mismatch");
		Assert.assertEquals(agStatus, driver.findElement(oipa_gc_overview_agreements_status).getText(),
				"Agreement Status Mismatch");

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(oipa_gc_overview_plans_name));
		takeScreenShot(driver, "GC Overview Plan Validations");
		// GC Overview Plan Validations
		Assert.assertEquals(planName, driver.findElement(oipa_gc_overview_plans_name).getText(), "Plans Name Mismatch");
		Assert.assertEquals(planProduct, driver.findElement(oipa_gc_overview_plans_product).getText(),
				"Plans Product Mismatch");
		Assert.assertEquals(planCompany, driver.findElement(oipa_gc_overview_plans_company).getText(),
				"Plans Company Mismatch");

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(oipa_gc_overview_classgroups_name));
		takeScreenShot(driver, "GC Overview ClassGroup Validations");
		// GC Overview ClassGroup Validations
		Assert.assertEquals(classGrpName, driver.findElement(oipa_gc_overview_classgroups_name).getText(),
				"ClassGroup Name Mismatch");
		Assert.assertEquals(classGrpDesc, driver.findElement(oipa_gc_overview_classgroups_desc).getText(),
				"ClassGroup Description Mismatch");
		Assert.assertEquals(classGrpType, driver.findElement(oipa_gc_overview_classgroups_type).getText(),
				"ClassGroup Type Mismatch");
		Assert.assertEquals(classGrpStatus, driver.findElement(oipa_gc_overview_classgroups_status).getText(),
				"ClassGroup Status Mismatch");
		Assert.assertEquals(classGrpEffdate, driver.findElement(oipa_gc_overview_classgroups_effdate).getText(),
				"ClassGroup Effective Date Mismatch");
		Assert.assertEquals(classGrpExpdate, driver.findElement(oipa_gc_overview_classgroups_expdate).getText(),
				"ClassGroup Expiry Date Mismatch");

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(oipa_gc_overview_class_name));
		takeScreenShot(driver, "GC Overview Class Validations");
		// GC Overview Class Validations
		Assert.assertEquals(className, driver.findElement(oipa_gc_overview_class_name).getText(),
				"Class Name Mismatch");
		Assert.assertEquals(classDesc, driver.findElement(oipa_gc_overview_class_desc).getText(),
				"Class Description Mismatch");
		Assert.assertEquals(classType, driver.findElement(oipa_gc_overview_class_type).getText(),
				"Class Type Mismatch");
		System.out.println("GC ValidatiON Success!");

		if (driver.findElement(oipa_gc_overview_grid).isSelected()) {
			tc18731Flag = true;
		}
	}

	@Test(dependsOnMethods = "TC18730")
	public void TC18731() {
		Assert.assertTrue(tc18731Flag, "Class Group Details are not displayed in Grid");
	}

	@Test()
	public void TC27723() throws IOException {
		System.out.println("-------Verify Submit Class Group Time Slice--------");
		gcNameTC27723 = createGroupCustomer();
		wait(5);
		addMasterAgreementInsured();
		wait(10);
		addProduct();
		wait(5);
		addPlan();
		wait(5);
		addClassGroup();
		wait(10);
		driver.findElement(oipa_gc_classgroup_sidelist).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander).click();
		wait(5);
		String old_record_status = driver.findElement(oipa_gc_classgroup_timeslice_status).getText();
		takeScreenShot(driver, "TC27723 Class Time Slice Before Clicking Submit");
		Assert.assertEquals(old_record_status, "Draft", "Class Is Not In Draft Status");
		wait(10);
		WebElement bluepaper = driver.findElement(oipa_gc_classgroup_submit);
		wait(1);
		bluepaper.click();
		wait(10);
		String new_record_status = driver.findElement(oipa_gc_classgroup_timeslice_status).getText();
		takeScreenShot(driver, "TC27723 Class Time Slice After Clicking Submit");
		Assert.assertEquals(new_record_status, "Active", "Class Is Not In Active Status After Processing");

	}

	@Test(dependsOnMethods = "TC27723")
	public void TC27724() throws IOException {
		System.out.println("----Verify Delete Class Group Time Slice-----");
		wait(5);
		driver.findElement(oipa_search_menu_dropdown).click();
		selectItemInDropdown(driver, "Customer");
		wait(1);
		driver.findElement(oipa_search_input_field).sendKeys(gcNameTC27723);
		wait(1);
		driver.findElement(oipa_search_button_icon).click();
		wait(10);
		driver.findElement(oipa_gc_classgroup_sidelist).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_timeslice_edit).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_timeslice_save).click();
		wait(5);
//		driver.navigate().refresh();
//		wait(5);
//		driver.findElement(oipa_gc_classgroup_expander).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_timeslice_delete).click();
		wait(3);
		driver.findElement(oipa_gc_classgroup_timeslice_delete_ok).click();
		wait(3);
		Assert.assertFalse(driver.findElement(oipa_gc_classgroup_timeslice_delete).isDisplayed(),
				"Class Group Time Slice - Delete Failed");

	}

	@Test(dependsOnMethods = "TC27724")
	public void TC27725() throws IOException {
		System.out.println("-------Verify Edit Class Group Time Slice--------");
		driver.findElement(oipa_search_menu_dropdown).click();
		selectItemInDropdown(driver, "Customer");
		wait(1);
		driver.findElement(oipa_search_input_field).sendKeys(gcNameTC27723);
		wait(1);
		driver.findElement(oipa_search_button_icon).click();
		wait(5);
		wait(5);
		driver.findElement(oipa_gc_classgroup_sidelist).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_timeslice_edit).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_desc).clear();
		wait(1);
		String desc_value = "test" + rand.nextInt(999);
		driver.findElement(oipa_gc_classgroup_desc).sendKeys(desc_value);
		wait(5);
		driver.findElement(oipa_gc_classgroup_timeslice_save).click();
		wait(5);
//		driver.navigate().refresh();
//		wait(10);
//		driver.findElement(oipa_gc_classgroup_expander).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_submit).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_timeslice_edit).click();
		wait(5);
		String updated_desc = driver.findElement(oipa_gc_classgroup_desc).getAttribute("value");
		Assert.assertEquals(updated_desc, desc_value, "Verify Edit Class Group Time Slice -Fail");

	}

	// Reusable Methods

	public void activateClassGroup() {
		System.out.println("--Activate Class Group");
		driver.findElement(oipa_gc_classgroup_sidelist).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_submit).click();
	}

	public void activatePlan() {
		System.out.println("--Activate Class Group");
		driver.findElement(oipa_gc_plans_sidelist).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_expander).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_submit).click();
		wait(2);
		driver.switchTo().activeElement();
		driver.findElement(oipa_gc_classgroup_ok).click();
		wait(5);
		driver.findElement(oipa_gc_classgroup_process).click();
		wait(2);
	}

	public String createGroupCustomer() {
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
		String customerName = "Group_Cust_Auto_" + rand.nextInt(9999);
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
		// gcName = driver.findElement(oipa_GC_customerNameEntered).getText();
		System.out.println("Group Customer is Created: " + customerName);
		wait(4);
		driver.findElement(oipa_GC_customerSummaryButton).click();
		wait(5);
		return customerName;

	}

	public void addMasterAgreementInsured() {

		/* Master Agreement Insured Variables */
		agType = "Master Agreement Insured";
		agName = "Master Agreement Insured";
		agStatus = "Pending";

		/* Adding Master Agreement Insured */
		System.out.println("--Adding Master Agreement Insured");
		driver.findElement(oipa_gc_agreement_sidelist).click();
		wait(5);
		driver.findElement(oipa_gc_agreement_contract).click();
		wait(5);
		driver.findElement(oipa_gc_agreement_add_contract).click();
		wait(5);
		driver.findElement(oipa_gc_agreement_type).click();
		selectItemInDropdown(driver, agType);
		wait(5);
		driver.findElement(oipa_gc_agreement_name).clear();
		wait(5);
		driver.findElement(oipa_gc_agreement_name).sendKeys(agName);
		wait(5);
		driver.findElement(oipa_gc_agreement_status).click();
		selectItemInDropdown(driver, agStatus);
		wait(5);
		driver.findElement(oipa_save).click();
		wait(5);
		System.out.println("----Master Agreement Insured Added!");

	}

	public void addProduct() {
		driver.findElement(oipa_gc_agreement_product).click();
		wait(5);
		driver.findElement(oipa_gc_agreement_add_product).click();
		wait(5);
		driver.switchTo().activeElement();
		wait(5);
		driver.findElement(oipa_gc_agreement_product_gpp).click();
		wait(5);
		driver.findElement(oipa_gc_agreement_ok).click();
		wait(5);
		driver.findElement(oipa_gc_agreement_product_status).click();
		selectItemInDropdown(driver, "Active");
		wait(5);
		driver.findElement(oipa_gc_agreement_product_effdate).clear();
		driver.findElement(oipa_gc_agreement_product_effdate).sendKeys("1/1/2000");
		wait(5);
		driver.findElement(oipa_save).click();
		wait(5);
		wait(5);
	}

	public void addPlan() {

		System.out.println("Adding Plan under Agreements");
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
		planCompany = xls.getCellData("GroupCustomer_1", "GC_plancompanyDD", 2);
		selectItemInDropdown(driver, planCompany);
		wait(6);
		driver.findElement(oipa_GC_planproductDD).click();
		wait(5);
		planProduct = xls.getCellData("GroupCustomer_1", "GC_planproductDD", 2);
		selectItemInDropdown(driver, planProduct);
		wait(6);
		planName = xls.getCellData("GroupCustomer_1", "GC_planName", 2) + rand.nextInt(1000);
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

		driver.findElement(oipa_add_plan_segemnt).click();
		wait(4);
		driver.switchTo().activeElement();

		System.out.println("-- Adding 'Base Coverage Plus' segment to the Plan");
		driver.findElement(oipa_gc_agreement_plan_segment).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentNameDD", 2));
		wait(6);
		driver.findElement(oipa_gc_agreement_plan_segname)
				.sendKeys(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 2));
		wait(4);
		driver.findElement(oipa_gc_agreement_plan_segname).sendKeys(Keys.TAB);
		wait(2);
		driver.findElement(oipa_gc_agreement_seg_type).click();
		wait(8);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentTypeDD", 2));
		wait(6);
		driver.findElement(oipa_seg_save).click();
		wait(4);

		driver.findElement(oipa_add_plan_segemnt).click();
		wait(4);

		System.out.println("-- Adding 'Base Coverage Basic' segment to the Plan");
		driver.findElement(oipa_gc_agreement_plan_segment).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentNameDD", 3));
		wait(6);
		driver.findElement(oipa_gc_agreement_plan_segname)
				.sendKeys(xls.getCellData("GroupCustomer_1", "GC_planSegmentName", 3));
		wait(4);
		driver.findElement(oipa_gc_agreement_plan_segname).sendKeys(Keys.TAB);
		wait(2);
		driver.findElement(oipa_gc_agreement_seg_type).click();
		wait(6);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planSegmentTypeDD", 2));
		wait(6);
		driver.findElement(oipa_seg_save).click();
		wait(4);
		driver.findElement(oipa_save).click();
		wait(4);
		driver.findElement(oipa_GC_planBusinessStatusDD).click();
		wait(4);
		selectItemInDropdown(driver, xls.getCellData("GroupCustomer_1", "GC_planBusinessStatusDD", 2));
		wait(4);
		driver.findElement(oipa_agreement_plan_save).click();
		System.out.println("Plan Added!");
	}

	public void getGroupCustomer() {
		driver.findElement(oipa_search_menu_dropdown).click();
		selectItemInDropdown(driver, xls.getCellData("ClassGroupTestData", "SearchChoice", 2));
		wait(1);
		driver.findElement(oipa_search_input_field).sendKeys(xls.getCellData("ClassGroupTestData", "SearchName", 2));
		wait(1);
		driver.findElement(oipa_search_button_icon).click();
		wait(5);
	}

	public void addClassGroup() {
		System.out.println("--Adding Class Group");
		wait(5);
		driver.findElement(oipa_GC_Agreement_ClassGroupsLink).click();
		wait(3);
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
		classGrpType = xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Type", 2);
		selectItemInDropdown(driver, classGrpType);
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_StatusDD).click();
		wait(2);
		String classGrpStatus1 = xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Status", 2);
		selectItemInDropdown(driver, classGrpStatus1);
		wait(5);
		classGrpName = "Class Group" + rand.nextInt(100);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupName).sendKeys(classGrpName);
		wait(5);
		classGrpDesc = xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_Description", 2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_ClassGroupDescription).sendKeys(classGrpDesc);
		wait(2);
		classGrpEffdate = xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_EffectiveFrom", 2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_EffectiveFrom).sendKeys(classGrpEffdate);
		wait(3);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_ExpirationDate).click();
		wait(2);
		classGrpExpdate = xls.getCellData("GroupCustomer_2", "GC_Agreements_ClassGroups_ExpirationDate", 2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_ExpirationDate).sendKeys(classGrpExpdate);
		wait(5);

		// Adding Class

		driver.findElement(oipa_GC_Agreement_AddClassGroups_AddClassLink).click();
		wait(4);
		driver.findElement(oipa_GC_Agreement_AddClass_ClassTypeDD).click();
		wait(2);
		classType = xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ClassType", 2);
		selectItemInDropdown(driver, classType);
		wait(3);
		className = xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ClassName", 2) + rand.nextInt(100);
		driver.findElement(oipa_GC_Agreement_AddClass_ClassName).sendKeys(className);
		wait(2);
		classDesc = xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClass_ClassDescription", 2) + rand.nextInt(100);
		driver.findElement(oipa_GC_Agreement_AddClass_ClassDescription).sendKeys(classDesc);
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_SaveButton).click();
		wait(2);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_SaveStatusDD).click();
		wait(3);
		classGrpStatus = xls.getCellData("GroupCustomer_2", "GC_Agreement_AddClassGroup_SaveStatus", 2);
		selectItemInDropdown(driver, classGrpStatus);
		wait(5);
		driver.findElement(oipa_GC_Agreement_AddClassGroups_agreementClassgroupSaveButton).click();
		wait(10);
		System.out.println("--Class Group Added");
	}

	@AfterClass
	public void teardown() throws Throwable {
		closeBrowser(driver);
	}

}