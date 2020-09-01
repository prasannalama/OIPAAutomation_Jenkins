package com.tc.groupcustomer;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.Library.OIPA_Library;

public class GC07_Client_1 extends OIPA_Library {

	// Tester : Haritha
	// Functionality : GC_Client-1

	Random rand = new Random();
	public String[] PolicyText, ClientText, CustomerText, CustomerText2;
	public String policyHeading, clientHeading, CustomerHeading, clientfirstname, pname, CX_number,
			existed_policyNumber;
	Actions a;

	@BeforeClass
	public void login() throws Throwable {
		
		System.out.println("***************GC07_Client1******************************");

		openBrowser();
		login(driver1);
		clientCreation();
		policycreation();
		customerCreation();
		evaluatemembership();

	}

	public void policycreation() throws IOException {
		// creating FPP policy
		driver1.findElement(oipa_createDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Policy");
		wait(3);
		driver1.findElement(oipa_CreateButton).click();
		wait(3);
		driver1.findElement(oipa_policy_companyDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Prototype Individual Child Company");
		wait(3);
		driver1.findElement(oipa_policy_productDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Individual Prototype Product");
		wait(3);
		driver1.findElement(oipa_policy_planDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Functional Prototype Plan");
		wait(3);

		driver1.findElement(oipa_policy_policyname).sendKeys(xls.getCellData("GC_Client_1", 0, 5) + rand.nextInt(1000));
		wait(3);
		driver1.findElement(oipa_policy_FPP_policynumber)
				.sendKeys(xls.getCellData("GC_Client_1", 1, 5) + rand.nextInt(1000));
		wait(3);
		driver1.findElement(oipa_policy_savebutton).click();
		wait(3);
		// taking screenshot of policy creation
		takeScreenShot(driver1, "FPP_policy_creation");
		wait(3);
		policyHeading = driver1.findElement(oipa_policyText).getText();
		wait(3);
		PolicyText = policyHeading.split("#");
		System.out.println("Policy Number is: " + PolicyText[1]);

	}

	public void clientCreation() throws IOException {
		// creating 02(individual) type client

		driver1.findElement(oipa_createDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Client");
		wait(3);
		driver1.findElement(oipa_CreateButton).click();
		wait(3);
		driver1.findElement(oipa_client_FirstName).sendKeys(xls.getCellData("GC_Client_1", 0, 2) + rand.nextInt(1000));
		wait(2);
		driver1.findElement(oipa_client_LastName).sendKeys(xls.getCellData("GC_Client_1", 1, 2));
		wait(2);
		driver1.findElement(oipa_client_DOB).sendKeys(xls.getCellData("GC_Client_1", 2, 2));
		wait(2);
		driver1.findElement(oipa_client_TaxID).sendKeys(xls.getCellData("GC_Client_1", 3, 2));
		wait(2);
		driver1.findElement(oipa_client_savebutton).click();
		wait(5);
		// taking screenshot of 02_type client creation
		takeScreenShot(driver1, "Client_type_02");
		wait(3);
		clientHeading = driver1.findElement(oipa_clientText).getText();
		ClientText = clientHeading.split(",");
		System.out.println("Client firstname is:" + ClientText[0]);
	}

	public void customerCreation() throws IOException {
		// creating customer
		a = new Actions(driver1);
		driver1.findElement(oipa_createDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Customer");
		wait(2);
		driver1.findElement(oipa_CreateButton).click();
		wait(3);
		driver1.findElement(oipa_cx_cxname).sendKeys(xls.getCellData("GC_Client_1", 1, 8) + rand.nextInt(1000));
		wait(2);
		CX_number = xls.getCellData("GC_Client_1", 0, 8) + rand.nextInt(1000);
		driver1.findElement(oipa_cx_cxnumber).sendKeys(CX_number);
		wait(3);
		driver1.findElement(oipa_cx_cxnumber).sendKeys(CX_number);
		wait(3);
		driver1.findElement(oipa_cx_taxid).sendKeys(xls.getCellData("GC_Client_1", 2, 8));
		wait(3);
		driver1.findElement(oipa_cx_PrimaryEnrollmentRelationshipDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Employment");
		wait(3);
		driver1.findElement(oipa_cx_EnrollmentClassGroupDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Eligibility");
		wait(3);
		driver1.findElement(oipa_cx_HierarchyRelationshipDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Employment");
		wait(3);
		driver1.findElement(oipa_cx_cxlegalname).sendKeys(xls.getCellData("GC_Client_1", 3, 8));
		wait(3);
		driver1.findElement(oipa_cx_savebutton).click();
		wait(7);
		// taking screenshot of customer creation
		takeScreenShot(driver1, "Customer_creation");
		wait(3);
		CustomerHeading = driver1.findElement(oipa_policyText).getText();
		CustomerText = CustomerHeading.split(":");

	}

	public void evaluatemembership() {
		unifiedSearch("Customer", CustomerText[1].trim());
		wait(3);
		System.out.println(CustomerText[1].trim());
		driver1.findElement(oipa_cx_AgreementMenuclick).click();
		wait(5);
		// Adding Master Agreement Insured type agreement

		a.moveToElement(driver1.findElement(oipa_cx_contract)).perform();
		wait(2);
		driver1.findElement(oipa_cx_contractadd).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Master Agreement Insured");
		wait(5);
		driver1.findElement(oipa_cx_agreementname).clear();
		wait(2);
		driver1.findElement(oipa_cx_agreementname).sendKeys(xls.getCellData("GC_Client_1", 4, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_savebutton).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_productslink).click();
		wait(3);

		// Adding Group Prototype Product
		driver1.findElement(oipa_cx_agreement_addproduct_button).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_GroupPrototypeProduct).click();
		wait(2);
		driver1.findElement(oipa_cx_agreement_product_okbutton).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_product_status).click();
		wait(2);
		selectItemInDropdown(driver1, "Active");
		wait(2);
		driver1.findElement(oipa_cx_agreement_product_EffectiveDate).sendKeys(xls.getCellData("GC_Client_1", 5, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_product_savebutton).click();
		wait(3);
		String successmessage = driver1.findElement(oipa_cx_product_added_message).getText();
		System.out.println(successmessage);
		wait(5);

		// Adding Plan to the product
		driver1.findElement(oipa_cx_agreement_planslink).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_addplanDD).click();
		wait(2);
		selectItemInDropdown(driver1, "New");
		wait(2);
		driver1.findElement(oipa_cx_agreement_plan_createbutton).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_plan_companyDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Prototype Group Child Company");
		wait(3);
		driver1.findElement(oipa_cx_agreement_plan_productDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Group Prototype Product");
		wait(3);
		driver1.findElement(oipa_cx_agreement_Planname)
				.sendKeys(xls.getCellData("GC_Client_1", 6, 8) + rand.nextInt(1000));
		wait(6);
		a.moveToElement(driver1.findElement(oipa_cx_agreement_Planname));
		// driver1.findElement(oipa_cx_agreement_plan_statusDD).click();
		// wait(6);

		a.sendKeys(Keys.TAB, Keys.DOWN, Keys.DOWN, Keys.ENTER).build().perform();
		// selectItemInDropdown(driver1,"Pending");
		wait(6);
		driver1.findElement(oipa_cx_product_plan_currencyDD).click();
		wait(6);
		selectItemInDropdown(driver1, "US Dollar");
		wait(6);
		driver1.findElement(oipa_cx_product_plan_AllocationMethodDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Default");
		wait(6);
		driver1.findElement(oipa_cx_product_plan_MarketMakerDD).click();
		wait(3);
		selectItemInDropdown(driver1, "USA");
		wait(6);

		// adding plan segments
		driver1.findElement(oipa_cx_agreement_Plansegment_button).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_plansegmentnameDD).click();
		wait(3);
		selectItemInDropdown(driver1, "BaseCoverageBasic");
		wait(3);
		driver1.findElement(oipa_cx_agreement_plansegmentname).sendKeys(xls.getCellData("GC_Client_1", 7, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_plansegment_savebutton).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_Plansegment_button).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_plansegmentnameDD).click();
		wait(3);
		selectItemInDropdown(driver1, "BaseCoveragePlus");
		wait(3);
		driver1.findElement(oipa_cx_agreement_plansegmentname).sendKeys(xls.getCellData("GC_Client_1", 8, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_plansegment_savebutton).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_plan_savebutton).click();
		wait(2);
		driver1.findElement(oipa_cx_agreement_product_status).click();
		wait(3);
		selectItemInDropdown(driver1, "Active");
		wait(2);
		driver1.findElement(oipa_cx_final_plan_savebutton).click();
		wait(2);
		String successmessage1 = driver1.findElement(oipa_cx_plan_added_message).getText();
		System.out.println(successmessage1);
		wait(5);

		// processing plan to active
		a.moveToElement(driver1.findElement(oipa_cx_agreement_Plantable_hamburgericon)).perform();
		wait(2);
		driver1.findElement(oipa_cx_agreement_Plantable_hamburgericon_gotoplan).click();
		wait(5);
		driver1.findElement(oipa_cx_plan_process1button).click();
		wait(5);

		driver1.findElement(oipa_cx_plan_alert_ok_button).click();
		wait(4);
		driver1.findElement(oipa_cx_plan_process2button).click();
		wait(5);

		// adding ClassGroup
		driver1.findElement(oipa_cx_AgreementMenuclick).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_expandicon_contract).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_classgroups_link).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_classgroupsDD).click();
		selectItemInDropdown(driver1, "New");
		wait(3);
		driver1.findElement(oipa_cx_agreement_classgroups_createbutton).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_classgroups_typeDD).click();
		selectItemInDropdown(driver1, "Eligibility");
		wait(4);
		driver1.findElement(oipa_cx_agreement_product_status).click();
		selectItemInDropdown(driver1, "Approved");
		wait(4);
		driver1.findElement(oipa_cx_agreement_classgroups_classgroupname)
				.sendKeys(xls.getCellData("GC_Client_1", 9, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_classgroups_description).sendKeys(xls.getCellData("GC_Client_1", 10, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_classgroups_effectivedate)
				.sendKeys(xls.getCellData("GC_Client_1", 11, 8));
		wait(2);

		// adding class
		driver1.findElement(oipa_cx_agreement_addclasslink).click();
		wait(2);
		driver1.findElement(oipa_cx_agreement_classgroups_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Eligibility");
		wait(4);
		driver1.findElement(oipa_cx_agreement_classname).sendKeys(xls.getCellData("GC_Client_1", 12, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_class_description).sendKeys(xls.getCellData("GC_Client_1", 13, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_class_savebutton).click();
		wait(4);
		driver1.findElement(oipa_cx_agreement_classgroup_statusDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Active");
		wait(2);
		driver1.findElement(oipa_cx_agreement_classgroup_savebutton).click();
		// String successmessage2=
		// driver1.findElement(oipa_cx_product_added_message).getText();
		// System.out.println(successmessage2);
		wait(5);

		a.moveToElement(driver1.findElement(oipa_cx_agreement_classgrouptable_hamburgericon)).perform();
		wait(2);
		driver1.findElement(oipa_cx_agreement_classgrouptable_hamburgericon_gotoclassgroup).click();
		wait(5);
		driver1.findElement(oipa_classgrouptable_parentexpandicon).click();
		wait(3);
		driver1.findElement(oipa_classgrouptable_childexpandicon).click();
		wait(3);
		driver1.findElement(oipa_cx_classgrouptable_classes_tab).click();
		wait(3);
		a.moveToElement(driver1.findElement(oipa_cx_classgrouptable_mousehover_class)).perform();
		wait(2);
		driver1.findElement(oipa_cx_class_plusicon).click();
		wait(4);

		driver1.findElement(oipa_cx_agreement_classgroups_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Eligibility");
		wait(4);

		// adding child class
		driver1.findElement(oipa_cx_agreement_classname).sendKeys(xls.getCellData("GC_Client_1", 14, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_class_description).sendKeys(xls.getCellData("GC_Client_1", 15, 8));
		wait(2);
		driver1.findElement(oipa_cx_classgrouptable_classokbutton).click();
		wait(4);

		// adding global class Rule variables
		driver1.findElement(oipa_cx_classgroup_ClassRuleVariables_link).click();

		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_plusicon).click();
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_name).sendKeys(xls.getCellData("GC_Client_1", 16, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_Text).sendKeys(xls.getCellData("GC_Client_1", 17, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "EXPRESSION");
		wait(3);
		driver1.findElement(oipa_cx_ClassRuleVariables_datatypeDD).click();
		selectItemInDropdown(driver1, "BOOLEAN");
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_savebutton).click();
		wait(4);
		driver1.findElement(oipa_cx_classgrouptable_classes_tab).click();
		wait(3);

		// adding plan coverage
		driver1.findElement(oipa_cx_class_parentclassclick).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_plancovearages).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_associate_button).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_associate_doublearrow).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_associate_effectivedate).sendKeys(xls.getCellData("GC_Client_1", 18, 8));
		wait(2);
		driver1.findElement(oipa_cx_Class_plancovearag_savebutton).click();
		wait(3);
		driver1.findElement(oipa_cx_classgroup_child_ClassRuleVariables_link).click();

		wait(2);
		driver1.findElement(oipa_cx_Class_child_classvariable_addbutton).click();
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_name).sendKeys(xls.getCellData("GC_Client_1", 19, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_Text).sendKeys(xls.getCellData("GC_Client_1", 20, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "EXPRESSION");
		wait(3);
		driver1.findElement(oipa_cx_ClassRuleVariables_datatypeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "BOOLEAN");
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_savebutton).click();
		wait(4);
		driver1.findElement(oipa_cx_Class_membershiprules_link).click();
		wait(4);
		driver1.findElement(oipa_cx_Class_membershiprules_childselection).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_membershiprules_arrow).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_membershiprules_savebutton).click();
		wait(3);
		// childclass
		driver1.findElement(oipa_cx_class_childclassclick).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_plancovearages).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_associate_button).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_associate_doublearrow).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_associate_effectivedate).sendKeys(xls.getCellData("GC_Client_1", 18, 8));
		wait(2);
		driver1.findElement(oipa_cx_Class_plancovearag_savebutton).click();
		wait(3);
		driver1.findElement(oipa_cx_classgroup_child_ClassRuleVariables_link).click();

		wait(2);
		driver1.findElement(oipa_cx_Class_child_classvariable_addbutton).click();
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_name).sendKeys(xls.getCellData("GC_Client_1", 21, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_Text).sendKeys(xls.getCellData("GC_Client_1", 22, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "EXPRESSION");
		wait(3);
		driver1.findElement(oipa_cx_ClassRuleVariables_datatypeDD).click();
		selectItemInDropdown(driver1, "BOOLEAN");
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_savebutton).click();
		wait(4);
		driver1.findElement(oipa_cx_Class_membershiprules_link).click();
		wait(4);
		driver1.findElement(oipa_cx_Class_membershiprules_childselection).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_membershiprules_arrow).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_membershiprules_savebutton).click();
		wait(4);
		driver1.findElement(oipa_cx_classgroup_processbutton).click();
		wait(4);

		// adding relationship
		System.out.println("Clicking on Relationships");
		driver1.findElement(oipa_cx_relationship_leftnavigation_click).click();
		wait(4);
		driver1.findElement(oipa_cx_relationship_add_relationshiptypeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Employment");
		wait(3);
		driver1.findElement(oipa_cx_relationship_add_relationship_button).click();
		wait(4);
		driver1.findElement(oipa_cx_relationship_effectivedate).sendKeys(xls.getCellData("GC_Client_1", 5, 8));
		wait(2);
		driver1.findElement(oipa_cx_relationship_clienticon).click();
		wait(4);
		driver1.findElement(oipa_cx_relationship_addclient_link).click();
		wait(3);
		clientfirstname = xls.getCellData("GC_Client_1", "FirstName", 2) + rand.nextInt(1000);
		driver1.findElement(oipa_client_FirstName).sendKeys(clientfirstname);
		wait(2);
		driver1.findElement(oipa_client_LastName).sendKeys(xls.getCellData("GC_Client_1", "LastName", 2));
		wait(2);
		driver1.findElement(oipa_client_DOB).sendKeys(xls.getCellData("GC_Client_1", "DOB", 2));
		wait(2);
		driver1.findElement(oipa_client_TaxID).sendKeys(xls.getCellData("GC_Client_1", "TaxID", 2));
		wait(2);
		driver1.findElement(oipa_cx_relationship_client_savebutton).click();
		wait(5);
		driver1.findElement(oipa_cx_relationship_savebutton).click();
		wait(5);

		// making relationship active
		driver1.findElement(oipa_cx_relationship_expandicon).click();
		wait(5);
		driver1.findElement(oipa_cx_relationship_processbutton).click();
		wait(4);
		driver1.findElement(oipa_cx_Relationships_alert_ok_button).click();
		wait(4);
		a.moveToElement(driver1.findElement(oipa_cx_relationship_hamburger)).perform();
		wait(3);
		driver1.findElement(oipa_cx_relationship_hamburger_gotoclient).click();
		wait(4);

		// adding EvaluateMembership activity to client
		driver1.findElement(oipa_client_addactivity).click();
		wait(2);
		driver1.findElement(oipa_client_activityDD).click();
		selectItemInDropdown(driver1, "EvaluateMembership");
		wait(3);
		driver1.findElement(oipa_cx_evaluatemembership_activity_EffectiveUntil)
				.sendKeys(xls.getCellData("GC_Client_1", 0, 11));
		wait(2);
		driver1.findElement(oipa_client_activity_OKNavigate).click();
		wait(4);
		driver1.findElement(oipa_client_activity_process).click();
		wait(10);

	}

	public void evaluatemembership2() throws IOException {

		a = new Actions(driver1);
		driver1.findElement(oipa_createDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Customer");
		wait(2);
		driver1.findElement(oipa_CreateButton).click();
		wait(3);
		driver1.findElement(oipa_cx_cxname).sendKeys(xls.getCellData("GC_Client_1", 1, 8) + rand.nextInt(1000));
		wait(2);

		driver1.findElement(oipa_cx_cxnumber).sendKeys(xls.getCellData("GC_Client_1", 0, 8) + rand.nextInt(1000));
		wait(3);
		driver1.findElement(oipa_cx_cxnumber).sendKeys(xls.getCellData("GC_Client_1", 0, 8) + rand.nextInt(1000));
		wait(3);
		driver1.findElement(oipa_cx_taxid).sendKeys(xls.getCellData("GC_Client_1", 2, 8));
		wait(3);
		driver1.findElement(oipa_cx_PrimaryEnrollmentRelationshipDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Employment");
		wait(3);
		driver1.findElement(oipa_cx_EnrollmentClassGroupDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Eligibility");
		wait(3);
		driver1.findElement(oipa_cx_HierarchyRelationshipDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Employment");
		wait(3);
		driver1.findElement(oipa_cx_cxlegalname).sendKeys(xls.getCellData("GC_Client_1", 3, 8));
		wait(3);
		driver1.findElement(oipa_cx_savebutton).click();
		wait(7);
		// taking screenshot of customer creation
		takeScreenShot(driver1, "Customer_creation2");
		wait(3);
		CustomerHeading = driver1.findElement(oipa_policyText).getText();
		CustomerText2 = CustomerHeading.split(":");

		wait(3);
		driver1.findElement(oipa_cx_AgreementMenuclick).click();
		wait(5);
		// Adding Master Agreement Insured type agreement

		a.moveToElement(driver1.findElement(oipa_cx_contract)).perform();
		wait(2);
		driver1.findElement(oipa_cx_contractadd).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Master Agreement Insured");
		wait(5);
		driver1.findElement(oipa_cx_agreementname).clear();
		wait(2);
		driver1.findElement(oipa_cx_agreementname).sendKeys(xls.getCellData("GC_Client_1", 4, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_savebutton).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_productslink).click();
		wait(3);

		// Adding Group Prototype Product
		driver1.findElement(oipa_cx_agreement_addproduct_button).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_GroupPrototypeProduct).click();
		wait(2);
		driver1.findElement(oipa_cx_agreement_product_okbutton).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_product_status).click();
		wait(2);
		selectItemInDropdown(driver1, "Active");
		wait(2);
		driver1.findElement(oipa_cx_agreement_product_EffectiveDate).sendKeys(xls.getCellData("GC_Client_1", 5, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_product_savebutton).click();
		wait(3);
		String successmessage = driver1.findElement(oipa_cx_product_added_message).getText();
		System.out.println(successmessage);
		wait(5);

		// Adding Plan to the product
		driver1.findElement(oipa_cx_agreement_planslink).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_addplanDD).click();
		wait(2);
		selectItemInDropdown(driver1, "New");
		wait(2);
		driver1.findElement(oipa_cx_agreement_plan_createbutton).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_plan_companyDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Prototype Group Child Company");
		wait(3);
		driver1.findElement(oipa_cx_agreement_plan_productDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Group Prototype Product");
		wait(3);
		driver1.findElement(oipa_cx_agreement_Planname)
				.sendKeys(xls.getCellData("GC_Client_1", 6, 8) + rand.nextInt(1000));
		wait(6);
		a.moveToElement(driver1.findElement(oipa_cx_agreement_Planname));
		// driver1.findElement(oipa_cx_agreement_plan_statusDD).click();
		// wait(6);

		a.sendKeys(Keys.TAB, Keys.DOWN, Keys.DOWN, Keys.ENTER).build().perform();
		// selectItemInDropdown(driver1,"Pending");
		wait(6);
		driver1.findElement(oipa_cx_product_plan_currencyDD).click();
		wait(6);
		selectItemInDropdown(driver1, "US Dollar");
		wait(6);
		driver1.findElement(oipa_cx_product_plan_AllocationMethodDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Default");
		wait(6);
		driver1.findElement(oipa_cx_product_plan_MarketMakerDD).click();
		wait(3);
		selectItemInDropdown(driver1, "USA");
		wait(6);

		// adding plan segments
		driver1.findElement(oipa_cx_agreement_Plansegment_button).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_plansegmentnameDD).click();
		wait(3);
		selectItemInDropdown(driver1, "BaseCoverageBasic");
		wait(3);
		driver1.findElement(oipa_cx_agreement_plansegmentname).sendKeys(xls.getCellData("GC_Client_1", 7, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_plansegment_savebutton).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_Plansegment_button).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_plansegmentnameDD).click();
		wait(3);
		selectItemInDropdown(driver1, "BaseCoveragePlus");
		wait(3);
		driver1.findElement(oipa_cx_agreement_plansegmentname).sendKeys(xls.getCellData("GC_Client_1", 8, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_plansegment_savebutton).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_plan_savebutton).click();
		wait(2);
		driver1.findElement(oipa_cx_agreement_product_status).click();
		wait(3);
		selectItemInDropdown(driver1, "Active");
		wait(2);
		driver1.findElement(oipa_cx_final_plan_savebutton).click();
		wait(2);
		String successmessage1 = driver1.findElement(oipa_cx_plan_added_message).getText();
		System.out.println(successmessage1);
		wait(5);

		// processing plan to active
		a.moveToElement(driver1.findElement(oipa_cx_agreement_Plantable_hamburgericon)).perform();
		wait(2);
		driver1.findElement(oipa_cx_agreement_Plantable_hamburgericon_gotoplan).click();
		wait(5);
		driver1.findElement(oipa_cx_plan_process1button).click();
		wait(5);

		driver1.findElement(oipa_cx_plan_alert_ok_button).click();
		wait(4);
		driver1.findElement(oipa_cx_plan_process2button).click();
		wait(5);

		// adding ClassGroup
		driver1.findElement(oipa_cx_AgreementMenuclick).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_expandicon_contract).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_classgroups_link).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_classgroupsDD).click();
		selectItemInDropdown(driver1, "New");
		wait(3);
		driver1.findElement(oipa_cx_agreement_classgroups_createbutton).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_classgroups_typeDD).click();
		selectItemInDropdown(driver1, "Eligibility");
		wait(4);
		driver1.findElement(oipa_cx_agreement_product_status).click();
		selectItemInDropdown(driver1, "Approved");
		wait(4);
		driver1.findElement(oipa_cx_agreement_classgroups_classgroupname)
				.sendKeys(xls.getCellData("GC_Client_1", 9, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_classgroups_description).sendKeys(xls.getCellData("GC_Client_1", 10, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_classgroups_effectivedate)
				.sendKeys(xls.getCellData("GC_Client_1", 11, 8));
		wait(2);

		// adding class
		driver1.findElement(oipa_cx_agreement_addclasslink).click();
		wait(2);
		driver1.findElement(oipa_cx_agreement_classgroups_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Eligibility");
		wait(4);
		driver1.findElement(oipa_cx_agreement_classname).sendKeys(xls.getCellData("GC_Client_1", 12, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_class_description).sendKeys(xls.getCellData("GC_Client_1", 13, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_class_savebutton).click();
		wait(4);
		driver1.findElement(oipa_cx_agreement_classgroup_statusDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Active");
		wait(2);
		driver1.findElement(oipa_cx_agreement_classgroup_savebutton).click();
		// String successmessage2=
		// driver1.findElement(oipa_cx_product_added_message).getText();
		// System.out.println(successmessage2);
		wait(5);

		a.moveToElement(driver1.findElement(oipa_cx_agreement_classgrouptable_hamburgericon)).perform();
		wait(2);
		driver1.findElement(oipa_cx_agreement_classgrouptable_hamburgericon_gotoclassgroup).click();
		wait(5);
		driver1.findElement(oipa_classgrouptable_parentexpandicon).click();
		wait(3);
		driver1.findElement(oipa_classgrouptable_childexpandicon).click();
		wait(3);
		driver1.findElement(oipa_cx_classgrouptable_classes_tab).click();
		wait(3);
		a.moveToElement(driver1.findElement(oipa_cx_classgrouptable_mousehover_class)).perform();
		wait(2);
		driver1.findElement(oipa_cx_class_plusicon).click();
		wait(4);

		driver1.findElement(oipa_cx_agreement_classgroups_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Eligibility");
		wait(4);

		// adding child class
		driver1.findElement(oipa_cx_agreement_classname).sendKeys(xls.getCellData("GC_Client_1", 14, 8));
		wait(2);
		driver1.findElement(oipa_cx_agreement_class_description).sendKeys(xls.getCellData("GC_Client_1", 15, 8));
		wait(2);
		driver1.findElement(oipa_cx_classgrouptable_classokbutton).click();
		wait(4);

		// adding global class Rule variables
		driver1.findElement(oipa_cx_classgroup_ClassRuleVariables_link).click();

		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_plusicon).click();
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_name).sendKeys(xls.getCellData("GC_Client_1", 16, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_Text).sendKeys(xls.getCellData("GC_Client_1", 17, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "EXPRESSION");
		wait(3);
		driver1.findElement(oipa_cx_ClassRuleVariables_datatypeDD).click();
		selectItemInDropdown(driver1, "BOOLEAN");
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_savebutton).click();
		wait(4);
		driver1.findElement(oipa_cx_classgrouptable_classes_tab).click();
		wait(3);

		// adding plan coverage
		driver1.findElement(oipa_cx_class_parentclassclick).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_plancovearages).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_associate_button).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_associate_doublearrow).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_associate_effectivedate).sendKeys(xls.getCellData("GC_Client_1", 18, 8));
		wait(2);
		driver1.findElement(oipa_cx_Class_plancovearag_savebutton).click();
		wait(3);
		driver1.findElement(oipa_cx_classgroup_child_ClassRuleVariables_link).click();

		wait(2);
		driver1.findElement(oipa_cx_Class_child_classvariable_addbutton).click();
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_name).sendKeys(xls.getCellData("GC_Client_1", 19, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_Text).sendKeys(xls.getCellData("GC_Client_1", 20, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "EXPRESSION");
		wait(3);
		driver1.findElement(oipa_cx_ClassRuleVariables_datatypeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "BOOLEAN");
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_savebutton).click();
		wait(4);
		driver1.findElement(oipa_cx_Class_membershiprules_link).click();
		wait(4);
		driver1.findElement(oipa_cx_Class_membershiprules_childselection).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_membershiprules_arrow).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_membershiprules_savebutton).click();
		wait(3);
		// childclass
		driver1.findElement(oipa_cx_class_childclassclick).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_plancovearages).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_associate_button).click();
		wait(3);
		driver1.findElement(oipa_cx_Class_associate_doublearrow).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_associate_effectivedate).sendKeys(xls.getCellData("GC_Client_1", 18, 8));
		wait(2);
		driver1.findElement(oipa_cx_Class_plancovearag_savebutton).click();
		wait(3);
		driver1.findElement(oipa_cx_classgroup_child_ClassRuleVariables_link).click();

		wait(2);
		driver1.findElement(oipa_cx_Class_child_classvariable_addbutton).click();
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_name).sendKeys(xls.getCellData("GC_Client_1", 21, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_Text).sendKeys(xls.getCellData("GC_Client_1", 22, 8));
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "EXPRESSION");
		wait(3);
		driver1.findElement(oipa_cx_ClassRuleVariables_datatypeDD).click();
		selectItemInDropdown(driver1, "BOOLEAN");
		wait(2);
		driver1.findElement(oipa_cx_ClassRuleVariables_savebutton).click();
		wait(4);
		driver1.findElement(oipa_cx_Class_membershiprules_link).click();
		wait(4);
		driver1.findElement(oipa_cx_Class_membershiprules_childselection).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_membershiprules_arrow).click();
		wait(2);
		driver1.findElement(oipa_cx_Class_membershiprules_savebutton).click();
		wait(4);
		driver1.findElement(oipa_cx_classgroup_processbutton).click();
		wait(4);

		// adding relationship
		System.out.println("Clicking on Relationships");
		driver1.findElement(oipa_cx_relationship_leftnavigation_click).click();
		wait(4);
		driver1.findElement(oipa_cx_relationship_add_relationshiptypeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Employment");
		wait(3);
		driver1.findElement(oipa_cx_relationship_add_relationship_button).click();
		wait(4);
		driver1.findElement(oipa_cx_relationship_effectivedate).sendKeys(xls.getCellData("GC_Client_1", 5, 8));
		wait(2);
		driver1.findElement(oipa_cx_relationship_clienticon).click();
		wait(4);
		driver1.findElement(oipa_cx_agreement_role_findclientsearch_firstname).sendKeys(clientfirstname);
		wait(2);
		driver1.findElement(oipa_cx_agreement_role_findclientsearch_button).click();
		wait(5);
		driver1.findElement(oipa_cx_aggreementrole_findclientserach_firstrow).click();

		wait(5);
		driver1.findElement(oipa_cx_relationship_savebutton).click();
		wait(5);

		// making relationship active
		driver1.findElement(oipa_cx_relationship_expandicon).click();
		wait(5);
		driver1.findElement(oipa_cx_relationship_processbutton).click();
		wait(4);
		driver1.findElement(oipa_cx_Relationships_alert_ok_button).click();
		wait(4);
		a.moveToElement(driver1.findElement(oipa_cx_relationship_hamburger)).perform();
		wait(3);
		driver1.findElement(oipa_cx_relationship_hamburger_gotoclient).click();
		wait(4);

		// adding EvaluateMembership activity to client
		driver1.findElement(oipa_client_addactivity).click();
		wait(2);
		driver1.findElement(oipa_client_activityDD).click();
		selectItemInDropdown(driver1, "EvaluateMembership");
		wait(3);
		driver1.findElement(oipa_cx_evaluatemembership_activity_EffectiveUntil)
				.sendKeys(xls.getCellData("GC_Client_1", 0, 11));
		wait(2);
		driver1.findElement(oipa_client_activity_OKNavigate).click();
		wait(4);
		driver1.findElement(oipa_client_activity_process).click();
		wait(10);

	}

	public void unifiedSearch(String entity, String input) {
		driver1.findElement(oipa_searchmenu).click();
		wait(3);
		selectItemInDropdown(driver1, entity);
		wait(4);
		driver1.findElement(oipa_serch_input).sendKeys(input);
		wait(3);
		driver1.findElement(oipa_unifiedsearch).click();
		wait(6);
	}

	public void listBillCreation() throws IOException {

		// creating FPP policy

		driver1.findElement(oipa_createDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Policy");
		wait(3);
		driver1.findElement(oipa_CreateButton).click();
		wait(3);
		driver1.findElement(oipa_policy_companyDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Prototype Individual Child Company");
		wait(3);
		driver1.findElement(oipa_policy_productDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Individual Prototype Product");
		wait(3);
		driver1.findElement(oipa_policy_planDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Functional Prototype Plan");
		wait(3);

		driver1.findElement(oipa_policy_policyname).sendKeys(xls.getCellData("GC_Client_1", 1, 5) + rand.nextInt(1000));
		wait(3);
		existed_policyNumber = xls.getCellData("GC_Client_1", 6, 11) + rand.nextInt(1000);
		driver1.findElement(oipa_policy_FPP_policynumber).sendKeys(existed_policyNumber);
		wait(3);
		driver1.findElement(oipa_policy_savebutton).click();
		wait(3);

		// List bill creation
		unifiedSearch("Client", ClientText[0]);
		wait(2);
		driver1.findElement(oipa_listbill_leftlink).click();
		wait(4);
		driver1.findElement(oipa_listbill_addbutton).click();
		wait(4);

		driver1.findElement(oipa_listbill_policyNumber).sendKeys(PolicyText[1]);
		wait(3);
		driver1.findElement(oipa_listbill_dueDate).sendKeys(xls.getCellData("GC_Client_1", 1, 11));
		wait(4);
		driver1.findElement(oipa_listbill_dueDate).sendKeys(xls.getCellData("GC_Client_1", 1, 11));
		wait(3);
		driver1.findElement(oipa_listbill_amount).clear();
		wait(2);
		driver1.findElement(oipa_listbill_amount).sendKeys(xls.getCellData("GC_Client_1", 2, 11));
		wait(3);
		driver1.findElement(oipa_listbill_save).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "listBillCreation");
		wait(2);

	}

	// Verify the Phone fields are updated for Client Screen after an activity
	// having CopyToPhoneFields with <From> and To> is processed
	@Test
	public void TC01() throws IOException {
		System.out.println("TC1");
		wait(2);
		unifiedSearch("Client", ClientText[0]);
		wait(2);
		driver1.findElement(oipa_client_phoneclick).click();
		wait(5);
		driver1.findElement(oipa_client_addphone).click();
		wait(2);

		driver1.findElement(oipa_client_phoneNumber).sendKeys(xls.getCellData("GC_Client_1", "PhoneNumber", 2));
		wait(2);
		driver1.findElement(oipa_client_phonesavebutton).click();
		wait(2);
		boolean success_message = driver1.findElement(oipa_client_phonenumber_success).isDisplayed();
		Assert.assertEquals(success_message, true, "Phone number added successfully");
		driver1.findElement(oipa_client_addactivity).click();
		wait(3);
		driver1.findElement(oipa_client_activityDD).click();
		selectItemInDropdown(driver1, "CopyToPhoneFields");
		wait(2);
		driver1.findElement(oipa_client_phoneNumber).sendKeys(xls.getCellData("GC_Client_1", "NewPhoneNumber", 2));
		wait(2);
		driver1.findElement(oipa_client_phone_countrycode).sendKeys(xls.getCellData("GC_Client_1", "CountryCode", 2));
		wait(2);
		// screenshot
		takeScreenShot(driver1, "TC1_Output1");
		wait(4);
		driver1.findElement(oipa_client_activity_OKNavigate).click();
		wait(3);
		driver1.findElement(oipa_client_activity_process).click();
		wait(4);
		driver1.findElement(oipa_client_phoneclick).click();
		wait(5);
		// screenshot
		takeScreenShot(driver1, "TC1_Output2");
		wait(4);
		String newPhoneumber1 = driver1.findElement(oipa_client_phonenumber_column).getText();
		Assert.assertEquals(newPhoneumber1, "1-(123)456-7890");

	}

	// TC-03 To verify 'Add Phone' in Phone Screen
	@Test
	public void TC02() throws IOException {

		wait(2);
		unifiedSearch("Client", ClientText[0]);
		wait(2);
		driver1.findElement(oipa_client_phoneclick).click();
		wait(5);
		driver1.findElement(oipa_client_addphone).click();
		wait(2);
		driver1.findElement(oipa_client_phonetype).click();
		wait(2);
		selectItemInDropdown(driver1, "Home");
		wait(2);
		driver1.findElement(oipa_client_phonecountry).click();
		wait(2);
		selectItemInDropdown(driver1, "USA");
		wait(2);
		driver1.findElement(oipa_client_phoneNumber).sendKeys(xls.getCellData("GC_Client_1", "PhoneNumber", 2));
		// driver1.findElement(oipa_client_phoneExtension).sendKeys(xls.getCellData("GC_Client_1","PhoneExtension",2));
		wait(2);
		driver1.findElement(oipa_client_phonesavebutton).click();
		wait(2);
		// screenshot
		takeScreenShot(driver1, "TC2_Output");
		wait(2);
		wait(4);
	}

	// TC-04 To verify 'Save' Button in Phone Screen
	@Test
	public void TC03() throws IOException {

		wait(2);
		unifiedSearch("Client", ClientText[0]);
		wait(2);
		driver1.findElement(oipa_client_phoneclick).click();

		wait(5);
		driver1.findElement(oipa_client_addphone).click();
		wait(2);
		driver1.findElement(oipa_client_phonesavebutton).click();
		wait(2);
		// screenshot
		takeScreenShot(driver1, "TC3_Output");
		wait(4);
		boolean error_message = driver1.findElement(oipa_client_phonenumber_error).isDisplayed();
		Assert.assertEquals(error_message, true, "Phone Number is required");
		wait(2);
		driver1.findElement(oipa_client_phoneNumber).sendKeys(xls.getCellData("GC_Client_1", "PhoneNumber", 2));
		wait(2);
		driver1.findElement(oipa_client_phonesavebutton).click();
		wait(2);
		boolean success_message = driver1.findElement(oipa_client_phonenumber_success).isDisplayed();
		Assert.assertEquals(success_message, true, "Phone number added successfully");
		wait(5);

	}

	// TC-04 Verify that user is able to Open Client Roles Screen and navigate
	// to policy in Chrome browser
	@Test
	public void TC04() throws Throwable {

		wait(5);
		unifiedSearch("Policy", PolicyText[1]);
		wait(4);
		driver1.findElement(oipa_policy_RolesMenuclick).click();
		wait(5);
		driver1.findElement(oipa_policy_roleviewDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Insured Role View");
		wait(5);
		driver1.findElement(oipa_policy_Roles_newclient).click();
		wait(5);
		driver1.findElement(oipa_policy_rolesDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Insured");
		wait(3);
		driver1.findElement(oipa_client_FirstName)
				.sendKeys(xls.getCellData("GC_Client_1", "FirstName", 2) + rand.nextInt(1000));
		wait(2);
		driver1.findElement(oipa_client_LastName).sendKeys(xls.getCellData("GC_Client_1", "LastName", 2));
		wait(2);
		driver1.findElement(oipa_client_DOB).sendKeys(xls.getCellData("GC_Client_1", "DOB", 2));
		wait(2);
		driver1.findElement(oipa_client_TaxID).sendKeys(xls.getCellData("GC_Client_1", "TaxID", 2));
		wait(3);
		driver1.findElement(oipa_client_addressline1).sendKeys(xls.getCellData("GC_Client_1", "AddressLine1", 2));
		wait(3);
		driver1.findElement(oipa_client_addresscity).sendKeys(xls.getCellData("GC_Client_1", "City", 2));
		wait(2);
		driver1.findElement(oipa_client_addresszip).sendKeys(xls.getCellData("GC_Client_1", "Zip", 2));
		wait(2);
		driver1.findElement(oipa_client_addressReturnedMail)
				.sendKeys(xls.getCellData("GC_Client_1", "ReturnMailDate", 2));
		wait(2);
		driver1.findElement(oipa_policyroles_newclient_add).click();
		wait(6);
		// screenshot
		takeScreenShot(driver1, "TC4_Output1");
		wait(2);
		String ClientName = driver1.findElement(oipa_policyroles_clientdata).getText();
		String[] CL = ClientName.split(",");
		wait(5);
		unifiedSearch("Client", CL[0]);
		wait(5);

		driver1.findElement(oipa_policy_RolesMenuclick).click();
		wait(5);
		boolean policyroletab = driver1.findElement(oipa_client_policyrolestab).isDisplayed();
		wait(2);
		boolean policynamecolumn = driver1.findElement(oipa_client_rolestab_secondcolumn).isDisplayed();
		wait(2);
		boolean policynumbercolumn = driver1.findElement(oipa_client_rolestab_firstrcolumn).isDisplayed();
		wait(2);
		boolean rolenamecolumn = driver1.findElement(oipa_client_rolestab_thirdcolumn).isDisplayed();
		wait(2);

		String record = driver1.findElement(oipa_policyroles_policyrolerecord).getText();
		String[] data = record.split(" ");

		Assert.assertEquals(policyroletab, true);
		wait(2);
		Assert.assertEquals(policynamecolumn, true);
		wait(2);
		Assert.assertEquals(policynumbercolumn, true);
		wait(2);
		Assert.assertEquals(rolenamecolumn, true);
		wait(2);
		Assert.assertEquals(data[0], PolicyText[1]);
		wait(2);
		Assert.assertEquals(data[2], "Insured");
		wait(2);
		// screenshot
		takeScreenShot(driver1, "TC4_Output1");
		wait(2);
		driver1.findElement(oipa_policyroles_policyrolerecord).click();
		wait(5);
		boolean policymenudisplay = driver1.findElement(oipa_policymenu).isDisplayed();
		wait(2);
		Assert.assertEquals(policymenudisplay, true);
		wait(2);

	}

	// TC-01 Verify that user is able to see the Agreement Roles details in
	// Client Roles table when a client is added in agreements roles through New
	// client tab
	@Test
	public void TC05() throws IOException {

		wait(2);
		unifiedSearch("Customer", CustomerText[1].trim());
		wait(5);
		driver1.findElement(oipa_cx_AgreementMenuclick).click();
		wait(5);
		a.moveToElement(driver1.findElement(oipa_cx_contract)).perform();
		driver1.findElement(oipa_cx_contractadd).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_typeDD).click();
		selectItemInDropdown(driver1, "Master Agreement Insured");
		wait(5);
		driver1.findElement(oipa_cx_agreementname).clear();
		wait(3);
		String agname = xls.getCellData("GC_Client_1", 3, 11);
		driver1.findElement(oipa_cx_agreementname).sendKeys(agname);
		wait(3);
		driver1.findElement(oipa_cx_agreement_savebutton).click();
		wait(5);
		driver1.findElement(By.xpath("//button[contains(text(),'" + agname + "')]")).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_roletab).click();
		wait(3);
		// verifying buttons in agreement roles
		boolean findcl = driver1.findElement(oipa_cx_agreement_roletab_findclient).isDisplayed();
		wait(2);
		boolean newcl = driver1.findElement(oipa_cx_agreement_roletab_newclient).isDisplayed();
		wait(2);
		boolean findcx = driver1.findElement(oipa_cx_agreement_roletab_findcx).isDisplayed();
		wait(2);
		Assert.assertEquals(findcl, true);
		wait(2);
		Assert.assertEquals(newcl, true);
		wait(2);
		Assert.assertEquals(findcx, true);
		wait(2);
		driver1.findElement(oipa_cx_agreement_roletab_newclient).click();
		wait(4);
		driver1.findElement(oipa_cx_addagreementroletypeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Agreement Role Type 1");
		wait(3);
		String x = xls.getCellData("GC_Client_1", "FirstName", 2) + rand.nextInt(1000);
		driver1.findElement(oipa_client_FirstName).sendKeys(x);
		wait(2);
		String y = xls.getCellData("GC_Client_1", "LastName", 2);
		wait(2);
		driver1.findElement(oipa_client_LastName).sendKeys(y);
		wait(2);
		driver1.findElement(oipa_client_DOB).sendKeys(xls.getCellData("GC_Client_1", "DOB", 2));
		wait(2);
		driver1.findElement(oipa_client_TaxID).sendKeys(xls.getCellData("GC_Client_1", "TaxID", 2));
		wait(3);
		driver1.findElement(oipa_cx_aggreementrole_addressline1)
				.sendKeys(xls.getCellData("GC_Client_1", "AddressLine1", 2));
		wait(3);
		driver1.findElement(oipa_cx_aggreementrole_addresscity).sendKeys(xls.getCellData("GC_Client_1", "City", 2));
		wait(2);
		driver1.findElement(oipa_cx_aggreementrole_addresszip).sendKeys(xls.getCellData("GC_Client_1", "Zip", 2));
		wait(2);
		driver1.findElement(ooipa_cx_aggreementrole_addressReturnedMail)
				.sendKeys(xls.getCellData("GC_Client_1", "ReturnMailDate", 2));
		wait(2);
		driver1.findElement(oipa_cx_agreementrole_okbutton).click();
		wait(4);

		boolean indentifier_disabled = driver1.findElement(oipa_cx_agreement_roletab_IdentifierNumber).isEnabled();
		wait(2);
		Assert.assertEquals(indentifier_disabled, false);
		wait(2);
		driver1.findElement(oipa_cx_agreement_roletab_AgreementRoleType).sendKeys("Test");
		wait(2);
		driver1.findElement(oipa_cx_agreement_roletab_AgreementRoleStatus).click();
		wait(2);
		List<WebElement> list = driver1.findElements(oipa_cx_agreement_roletab_AgreementRoleStatusDD_list);
		System.out.println(list.size());
		System.out.println("Drop down values of AgreementRoleStatus are");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());

		}
		wait(2);
		Assert.assertEquals(list.get(0).getText(), "Active");
		wait(2);
		Assert.assertEquals(list.get(1).getText(), "Deleted");
		wait(2);
		Assert.assertEquals(list.get(2).getText(), "Inactive");

		wait(2);
		selectItemInDropdown(driver1, "Active");
		wait(2);
		driver1.findElement(oipa_cx_agreement_roletab_savebutton).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC5_Output1");
		wait(2);
		boolean typecolumn = driver1.findElement(oipa_cx_agreement_roletab_table_Typecolumn).isDisplayed();
		wait(2);
		boolean namecolumn = driver1.findElement(oipa_cx_agreement_roletab_table_namecolumn).isDisplayed();
		wait(2);
		boolean statuscolumn = driver1.findElement(oipa_cx_agreement_roletab_table_statuscolumn).isDisplayed();
		wait(2);
		boolean actioncolumn = driver1.findElement(oipa_cx_agreement_roletab_table_actioncolumn).isDisplayed();
		wait(2);
		Assert.assertEquals(typecolumn, true);
		wait(2);
		Assert.assertEquals(namecolumn, true);
		wait(2);
		Assert.assertEquals(statuscolumn, true);
		wait(2);
		Assert.assertEquals(actioncolumn, true);
		wait(2);

		String typecolumnvalue = driver1.findElement(oipa_cx_agreement_roletab_table_Typecolumnvalue).getText();
		wait(2);
		String namecolumnvalue = driver1.findElement(oipa_cx_agreement_roletab_table_namecolumnvalue).getText();
		wait(2);
		String statuscolumnvalue = driver1.findElement(oipa_cx_agreement_roletab_table_statuscolumnvalue).getText();
		wait(2);
		Assert.assertEquals(typecolumnvalue, "Agreement Role Type 1");
		wait(2);
		Assert.assertEquals(statuscolumnvalue, "Active");
		wait(2);
		Assert.assertEquals(namecolumnvalue, x + "," + y);
		wait(2);
		System.out.println("clientdata is" + x + "," + y);
		wait(2);
		a.moveToElement(driver1.findElement(oipa_cx_agreement_roletab_table_hamburgermenuvalue)).perform();
		wait(2);
		driver1.findElement(oipa_cx_agreement_roletab_hamburger_gotoclient).click();

		wait(4);

		driver1.findElement(oipa_policy_RolesMenuclick).click();
		wait(5);
		driver1.findElement(oipa_client_agreementrolestab).click();
		wait(5);
		// screenshot
		takeScreenShot(driver1, "TC5_Output2");
		wait(2);
		boolean agreementroletab = driver1.findElement(oipa_client_agreementrolestab).isDisplayed();
		wait(2);
		boolean groupcxcolumn = driver1.findElement(oipa_client_rolestab_firstrcolumn).isDisplayed();
		wait(2);
		boolean agreementcolumn = driver1.findElement(oipa_client_rolestab_secondcolumn).isDisplayed();
		wait(2);
		boolean agreementrolecolumn = driver1.findElement(oipa_client_rolestab_thirdcolumn).isDisplayed();
		wait(2);
		String column1 = driver1.findElement(oipa_client_rolestab_firstcolumn_value).getText();
		wait(2);
		String column2 = driver1.findElement(oipa_client_policyrolestab_thirdcolumn_value).getText();
		wait(2);

		Assert.assertEquals(agreementroletab, true);
		wait(2);
		Assert.assertEquals(groupcxcolumn, true);
		wait(2);
		Assert.assertEquals(agreementcolumn, true);
		wait(2);
		Assert.assertEquals(agreementrolecolumn, true);
		wait(2);
		Assert.assertEquals(column1, CustomerText[1].trim());
		wait(2);
		Assert.assertEquals(column2, "Agreement Role Type 1");
		wait(2);

	}

	// TC-02 Verify that user is able to see the Agreement Roles details in
	// Client Roles table when a client is added in agreements roles through
	// Find client tab

	@Test
	public void TC06() throws IOException {
		wait(3);
		unifiedSearch("Customer", CustomerText[1].trim());
		wait(2);
		System.out.println(CustomerText[1].trim());
		wait(5);
		driver1.findElement(oipa_cx_AgreementMenuclick).click();
		wait(5);
		a.moveToElement(driver1.findElement(oipa_cx_contract)).perform();
		wait(3);
		driver1.findElement(oipa_cx_contractadd).click();
		wait(3);
		driver1.findElement(oipa_cx_agreement_typeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Master Agreement Insured");
		wait(5);
		driver1.findElement(oipa_cx_agreementname).clear();
		wait(2);
		String agname = xls.getCellData("GC_Client_1", 4, 11);
		driver1.findElement(oipa_cx_agreementname).sendKeys(agname);
		wait(3);
		driver1.findElement(oipa_cx_agreement_savebutton).click();
		wait(5);

		driver1.findElement(By.xpath("//button[contains(text(),'" + agname + "')]")).click();
		wait(5);
		driver1.findElement(oipa_cx_agreement_roletab).click();
		wait(3);
		boolean findcl = driver1.findElement(oipa_cx_agreement_roletab_findclient).isDisplayed();
		wait(2);
		boolean newcl = driver1.findElement(oipa_cx_agreement_roletab_newclient).isDisplayed();
		wait(2);
		boolean findcx = driver1.findElement(oipa_cx_agreement_roletab_findcx).isDisplayed();
		wait(2);
		Assert.assertEquals(findcl, true);
		wait(2);
		Assert.assertEquals(newcl, true);
		wait(2);
		Assert.assertEquals(findcx, true);
		wait(2);
		driver1.findElement(oipa_cx_agreement_roletab_findclient).click();
		wait(4);

		driver1.findElement(oipa_cx_agreement_role_findclientsearch_firstname).sendKeys(ClientText[0]);
		wait(2);
		driver1.findElement(oipa_cx_agreement_role_findclientsearch_button).click();
		wait(5);
		// String
		// clientdata=driver1.findElement(ooipa_cx_aggreementrole_findclientserach_clientdeatils).getText();
		// String[] getclient=clientdata.split(",");

		driver1.findElement(oipa_cx_aggreementrole_findclientserach_firstrow).click();
		wait(4);
		driver1.findElement(ooipa_cx_aggreementrole_findclientserach_OKbutton).click();
		wait(5);

		String detailsection = driver1.findElement(oipa_cx_aggreementrole_detailsection_heading).getText();
		wait(2);
		System.out.println(detailsection);
		wait(2);
		Assert.assertEquals(detailsection, "Detail Section 1", "Detail Section 1 is displayed");
		wait(2);
		driver1.findElement(oipa_cx_aggreementrole_detailsection_expandableicon).click();
		wait(5);
		WebElement IdentifierNumber = driver1.findElement(oipa_cx_agreement_roletab_IdentifierNumber);
		wait(2);
		WebElement AgreementRoleType = driver1.findElement(oipa_cx_agreement_roletab_AgreementRoleType);
		wait(2);
		WebElement AgreementRoleStatus = driver1.findElement(oipa_cx_agreement_roletab_AgreementRoleStatus);
		wait(2);
		boolean identifiercollapse = IdentifierNumber.isDisplayed();
		wait(2);
		boolean RoleTypecollapse = AgreementRoleType.isDisplayed();
		wait(2);
		boolean RoleStatuscollapse = AgreementRoleStatus.isDisplayed();
		wait(2);
		Assert.assertEquals(identifiercollapse, false);
		wait(2);
		Assert.assertEquals(RoleTypecollapse, false);
		wait(2);
		Assert.assertEquals(RoleStatuscollapse, false);
		wait(2);
		System.out.println(identifiercollapse + " " + RoleTypecollapse + " " + RoleStatuscollapse);
		wait(2);
		driver1.findElement(oipa_cx_aggreementrole_detailsection_expandableicon).click();

		wait(5);
		boolean identifier_expand = IdentifierNumber.isDisplayed();
		wait(2);
		boolean RoleType_expand = AgreementRoleType.isDisplayed();
		wait(2);
		boolean RoleStatus_expand = AgreementRoleStatus.isDisplayed();
		wait(2);
		System.out.println(identifier_expand + " " + RoleType_expand + " " + RoleStatus_expand);
		wait(2);
		Assert.assertEquals(identifier_expand, true);
		wait(2);
		Assert.assertEquals(RoleType_expand, true);
		wait(2);
		Assert.assertEquals(RoleStatus_expand, true);
		wait(2);
		boolean indentifier_disabled = driver1.findElement(oipa_cx_agreement_roletab_IdentifierNumber).isEnabled();
		wait(2);
		Assert.assertEquals(indentifier_disabled, false);
		wait(2);
		driver1.findElement(oipa_cx_agreement_roletab_AgreementRoleType).sendKeys("Test");
		wait(2);
		driver1.findElement(oipa_cx_agreement_roletab_AgreementRoleStatus).click();
		wait(2);
		List<WebElement> list = driver1.findElements(oipa_cx_agreement_roletab_AgreementRoleStatusDD_list);
		System.out.println(list.size());
		System.out.println("Drop down values of AgreementRoleStatus are");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());

		}
		wait(2);
		Assert.assertEquals(list.get(0).getText(), "Active");
		wait(2);
		Assert.assertEquals(list.get(1).getText(), "Deleted");
		wait(2);
		Assert.assertEquals(list.get(2).getText(), "Inactive");
		wait(2);

		selectItemInDropdown(driver1, "Active");
		wait(2);
		driver1.findElement(oipa_cx_agreement_roletab_savebutton).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC6_Output1");
		wait(2);

		boolean typecolumn = driver1.findElement(oipa_cx_agreement_roletab_table_Typecolumn).isDisplayed();
		wait(2);
		boolean namecolumn = driver1.findElement(oipa_cx_agreement_roletab_table_namecolumn).isDisplayed();
		wait(2);
		boolean statuscolumn = driver1.findElement(oipa_cx_agreement_roletab_table_statuscolumn).isDisplayed();
		wait(2);
		boolean actioncolumn = driver1.findElement(oipa_cx_agreement_roletab_table_actioncolumn).isDisplayed();
		wait(2);
		Assert.assertEquals(typecolumn, true);
		wait(2);
		Assert.assertEquals(namecolumn, true);
		wait(2);
		Assert.assertEquals(statuscolumn, true);
		wait(2);
		Assert.assertEquals(actioncolumn, true);
		wait(2);
		String typecolumnvalue = driver1.findElement(oipa_cx_agreement_roletab_table_Typecolumnvalue).getText();
		wait(2);
		String namecolumnvalue = driver1.findElement(oipa_cx_agreement_roletab_table_namecolumnvalue).getText();
		wait(2);
		String statuscolumnvalue = driver1.findElement(oipa_cx_agreement_roletab_table_statuscolumnvalue).getText();
		wait(2);

		Assert.assertEquals(typecolumnvalue, "Agreement Role Type 1");
		wait(2);
		Assert.assertEquals(statuscolumnvalue, "Active");
		wait(2);
		String clientfull = ClientText[0] + "," + xls.getCellData("GC_Client_1", "LastName", 2);
		wait(2);
		Assert.assertEquals(namecolumnvalue, clientfull);
		wait(2);
		a.moveToElement(driver1.findElement(oipa_cx_agreement_roletab_table_hamburgermenuvalue)).perform();
		wait(2);
		driver1.findElement(oipa_cx_agreement_roletab_hamburger_gotoclient).click();
		wait(4);

		driver1.findElement(oipa_policy_RolesMenuclick).click();
		wait(5);
		driver1.findElement(oipa_client_agreementrolestab).click();
		wait(5);
		// screenshot
		takeScreenShot(driver1, "TC6_Output2");
		wait(2);

		boolean agreementroletab = driver1.findElement(oipa_client_agreementrolestab).isDisplayed();
		wait(2);
		boolean groupcxcolumn = driver1.findElement(oipa_client_rolestab_firstrcolumn).isDisplayed();
		wait(2);
		boolean agreementcolumn = driver1.findElement(oipa_client_rolestab_secondcolumn).isDisplayed();
		wait(2);
		boolean agreementrolecolumn = driver1.findElement(oipa_client_rolestab_thirdcolumn).isDisplayed();
		wait(2);

		Assert.assertEquals(agreementroletab, true);
		wait(2);
		Assert.assertEquals(groupcxcolumn, true);
		wait(2);
		Assert.assertEquals(agreementcolumn, true);
		wait(2);
		Assert.assertEquals(agreementrolecolumn, true);
		wait(2);
		String column1 = driver1.findElement(oipa_client_rolestab_firstcolumn_value).getText();
		wait(2);
		String column2 = driver1.findElement(oipa_client_policyrolestab_thirdcolumn_value).getText();
		wait(2);

		Assert.assertEquals(column1, CustomerText[1].trim());
		wait(2);
		Assert.assertEquals(column2, "Agreement Role Type 1");
		wait(2);
		/*
		 * List<WebElement> list1=driver1.findElements(By.xpath("//tbody/tr"));
		 * System.out.println(list1); int countrow=0; for(int
		 * i=1;i<=list1.size();i++) { String c=list1.get(i).getText();
		 * 
		 * if(c.contains(CustomerText[1].trim())) { countrow=i+1; break; }
		 * 
		 * }
		 * 
		 * String column1=driver1.findElement(By.xpath("//tbody/tr["+countrow+
		 * "]//td[2]")).getText(); System.out.println(column1);
		 * Assert.assertEquals(column1,CustomerText[1].trim()); //String
		 * column3=driver1.findElement(By.xpath("//tbody/tr["+countrow+
		 * "]//td[4]")).getText(); //
		 * Assert.assertEquals(column3,"Agreement Role Type 1");
		 */

	}

	// TC-09-Validate that when enrollment details are saved, then a pending
	// enrollment transaction is created
	@Test
	public void TC07() throws IOException {

		unifiedSearch("Client", clientfirstname);

		wait(5);

		driver1.findElement(oipa_clinet_enrollemnt_leftlink).click();
		wait(5);
		driver1.findElement(oipa_client_enrollemnt_checkbox).click();
		wait(5);
		pname = xls.getCellData("GC_Client_1", 5, 11) + rand.nextInt(1000);
		wait(2);
		driver1.findElement(oipa_enrollemnt_policyname).sendKeys(pname);
		wait(2);
		// screenshot
		takeScreenShot(driver1, "TC7_Output1");
		wait(2);
		driver1.findElement(oipa_enrollement_savebutton).click();
		wait(5);
		// screenshot
		takeScreenShot(driver1, "TC7_Output2");
		wait(2);
		String enrolldate = driver1.findElement(oipa_enrollement_date).getText();
		wait(2);
		System.out.println("enrollemnt date is " + enrolldate);
		wait(2);

		// enrolldate may depends on system date
		Assert.assertEquals(enrolldate, "7/9/2002");
		wait(2);
		driver1.findElement(oipa_activities_link).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC7_Output3");
		wait(2);
		String activityname = driver1.findElement(oipa_enrollementprototype_activity).getText();
		wait(2);
		Assert.assertEquals(activityname, "EnrollmentPrototype");
		wait(2);
		String activityStatus = driver1.findElement(oipa_enrollementprototype_status).getText();
		wait(2);
		Assert.assertEquals(activityStatus, "Enrollment Pending");
		wait(2);

	}

	// TC-11-Validate that an active enrollment transaction is created when
	// Enroll button is clicked on the enrollment screen
	@Test
	public void TC08() throws IOException {
		wait(2);
		unifiedSearch("Client", clientfirstname);

		wait(5);
		driver1.findElement(oipa_clinet_enrollemnt_leftlink).click();
		wait(5);
		driver1.findElement(oipa_client_enrollemnt_checkbox).click();
		wait(5);

		driver1.findElement(oipa_enrollemnt_policyname).sendKeys(pname);
		wait(2);
		// screenshot
		takeScreenShot(driver1, "TC8_Output1");
		wait(2);
		driver1.findElement(oipa_enroll_button).click();
		wait(5);
		// screenshot
		takeScreenShot(driver1, "TC8_Output2");
		wait(2);
		String enrolldate = driver1.findElement(oipa_enrollement_date).getText();
		wait(2);
		System.out.println("enrollemnt date is " + enrolldate);
		wait(2);
		Assert.assertEquals(enrolldate, "7/9/2002");
		wait(2);
		driver1.findElement(oipa_activities_link).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC8_Output3");
		wait(2);
		String activityname = driver1.findElement(oipa_enrollementprototype_activity).getText();
		wait(2);
		Assert.assertEquals(activityname, "EnrollmentPrototype");
		wait(2);
		String activityStatus = driver1.findElement(oipa_enrollementprototype_status).getText();
		wait(2);
		Assert.assertEquals(activityStatus, "Active");
		wait(2);

	}

	// TC-03.Verify Adding PrimaryRelationship to a customer.
	@Test
	public void TC09() throws IOException {
		wait(2);
		unifiedSearch("Customer", CustomerText[1].trim());
		wait(2);
		System.out.println("customer is" + CustomerText[1].trim());
		System.out.println("Created group customer name is:" + CustomerText[1].trim());
		wait(2);

		// Clicking on Relationships
		driver1.findElement(oipa_cx_relationship_leftnavigation_click).click();
		wait(4);
		// driver1.findElement(oipa_cx_relationship_add_relationshiptypeDD).click();
		// selectItemInDropdown(driver1,"Employment");
		// wait(3);
		driver1.findElement(oipa_cx_relationship_add_relationship_button).click();
		wait(4);
		driver1.findElement(oipa_cx_relationship_effectivedate).sendKeys(xls.getCellData("GC_Client_1", 5, 8));
		wait(2);
		driver1.findElement(oipa_cx_relationship_clienticon).click();
		wait(4);
		driver1.findElement(oipa_cx_relationship_addclient_link).click();
		wait(3);
		driver1.findElement(oipa_client_FirstName)
				.sendKeys(xls.getCellData("GC_Client_1", "FirstName", 2) + rand.nextInt(1000));
		wait(2);
		driver1.findElement(oipa_client_LastName).sendKeys(xls.getCellData("GC_Client_1", "LastName", 2));
		wait(2);
		driver1.findElement(oipa_client_DOB).sendKeys(xls.getCellData("GC_Client_1", "DOB", 2));
		wait(2);
		driver1.findElement(oipa_client_TaxID).sendKeys(xls.getCellData("GC_Client_1", "TaxID", 2));
		wait(2);
		driver1.findElement(oipa_cx_relationship_client_savebutton).click();
		wait(3);
		driver1.findElement(oipa_cx_relationship_savebutton).click();
		String successmessage = driver1.findElement(oipa_cx_relationship_added_message).getText();

		// screenshot
		takeScreenShot(driver1, "TC9_Output1");
		wait(2);

		System.out.println(successmessage);
		wait(2);

	}

	// TC-15.Verify Adding PrimaryRelationship to a customer using Find Client
	// tab.
	@Test
	public void TC10() throws IOException {
		wait(2);
		unifiedSearch("Customer", CustomerText[1].trim());
		wait(2);
		System.out.println("customer is" + CustomerText[1].trim());
		System.out.println("Created group customer name is:" + CustomerText[1].trim());

		wait(4);
		// Clicking on Relationships
		driver1.findElement(oipa_cx_relationship_leftnavigation_click).click();
		wait(4);
		// driver1.findElement(oipa_cx_relationship_add_relationshiptypeDD).click();
		// selectItemInDropdown(driver1,"Employment");
		// wait(3);
		driver1.findElement(oipa_cx_relationship_add_relationship_button).click();
		wait(4);
		driver1.findElement(oipa_cx_relationship_effectivedate).sendKeys(xls.getCellData("GC_Client_1", 5, 8));
		wait(2);
		driver1.findElement(oipa_cx_relationship_clienticon).click();
		wait(4);

		driver1.findElement(oipa_cx_agreement_role_findclientsearch_firstname).sendKeys("%");
		wait(2);
		driver1.findElement(oipa_cx_agreement_role_findclientsearch_button).click();
		wait(5);
		driver1.findElement(oipa_cx_aggreementrole_findclientserach_firstrow).click();
		wait(3);
		driver1.findElement(oipa_cx_relationship_savebutton).click();
		wait(5);
		// screenshot
		takeScreenShot(driver1, "TC10_Output1");
		wait(2);

	}

	// TC 06 Verify the Class Members Role in client role screen
	@Test
	public void TC11() throws IOException {
		wait(2);
		unifiedSearch("Client", clientfirstname);
		wait(5);
		driver1.findElement(oipa_policy_RolesMenuclick).click();
		wait(5);
		driver1.findElement(oipa_client_classmembersrolestab).click();
		wait(5);
		// screenshot
		takeScreenShot(driver1, "TC11_Output1");
		wait(2);
		boolean groupcxcolumn = driver1.findElement(oipa_client_rolestab_firstrcolumn).isDisplayed();
		wait(2);
		boolean classgroupcolumn = driver1.findElement(oipa_client_rolestab_secondcolumn).isDisplayed();
		wait(2);
		boolean classcolumn = driver1.findElement(oipa_client_rolestab_thirdcolumn).isDisplayed();
		wait(2);
		String column1 = driver1.findElement(oipa_client_rolestab_firstcolumn_value).getText();
		wait(2);
		String column2 = driver1.findElement(oipa_client_policyrolestab_secondcolumn_value).getText();
		wait(2);

		Assert.assertEquals(groupcxcolumn, true);
		wait(2);
		Assert.assertEquals(classgroupcolumn, true);
		wait(2);
		Assert.assertEquals(classcolumn, true);
		wait(2);
		Assert.assertEquals(column1, CustomerText[1].trim());
		wait(2);
		// should match with class group name
		Assert.assertEquals(column2, xls.getCellData("GC_Client_1", 9, 8));
		wait(2);

	}

	// TC-06.Verify whether client is associated to one or more group customer.
	@Test
	public void TC12() throws IOException {

		// firstCustomer
		wait(2);
		unifiedSearch("Client", clientfirstname);
		wait(5);
		driver1.findElement(oipa_activities_link).click();
		wait(4);
		driver1.findElement(oipa_client_activity_row2_expander).click();
		wait(3);
		driver1.findElement(oipa_activity_classmembership_tab).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC12_Output1");
		wait(2);
		String value1 = driver1.findElement(oipa_classmembership_tab_firstrowvalue).getText();
		wait(2);
		Assert.assertEquals(value1, CustomerText[1].trim());
		wait(2);

		// 2ndCustomer
		wait(4);
		evaluatemembership2();
		wait(4);

		driver1.findElement(oipa_client_activity_row1_expander).click();
		wait(3);
		driver1.findElement(oipa_activity_classmembership_tab).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC12_Output2");
		wait(2);
		String value2 = driver1.findElement(oipa_classmembership_tab_thirdvalue).getText();
		String value3 = driver1.findElement(oipa_classmembership_tab_secondrowvalue).getText();
		wait(3);
		// System.out.println("customer names under classmembership_tab are :"+
		// value1 +" " +value2);
		String Cxvalue = null;
		if (value2.equals(CustomerText2[1].trim())) {
			Cxvalue = value2;

		}
		if (value3.equals(CustomerText2[1].trim())) {
			Cxvalue = value3;

		}

		Assert.assertEquals(value1, CustomerText[1].trim());
		wait(3);
		Assert.assertEquals(Cxvalue, CustomerText2[1].trim());
		wait(3);

	}

	// 14813:Add record for existing client list bill
	@Test
	public void TC13() throws IOException {
		wait(3);
		listBillCreation();
		wait(3);
		driver1.findElement(oipa_listbill_addbutton).click();
		wait(4);
		driver1.findElement(oipa_listbill_policyNumber).sendKeys(PolicyText[1]);
		wait(3);
		driver1.findElement(oipa_listbill_dueDate).sendKeys(xls.getCellData("GC_Client_1", 1, 11));
		wait(4);
		driver1.findElement(oipa_listbill_dueDate).sendKeys(xls.getCellData("GC_Client_1", 1, 11));
		wait(3);
		driver1.findElement(oipa_listbill_amount).clear();
		driver1.findElement(oipa_listbill_amount).sendKeys(xls.getCellData("GC_Client_1", 2, 11));
		wait(3);
		driver1.findElement(oipa_listbill_save).click();
		wait(3);
		// screenshot
		takeScreenShot(driver1, "TC13_Output1");
		wait(2);
		String error = driver1.findElement(oipa_listbill_policynumber_errormessage).getText();
		wait(2);
		Assert.assertEquals(error, "Policy already exists in the List Bill.");
		wait(4);
		driver1.findElement(oipa_listbill_policyNumber).clear();
		wait(3);
		driver1.findElement(oipa_listbill_policyNumber).sendKeys(existed_policyNumber);
		wait(3);
		driver1.findElement(oipa_listbill_save).click();
		wait(3);
		// screenshot
		takeScreenShot(driver1, "TC13_Output2");
		wait(2);

	}

	// Update a field from an existing list bill group.
	@Test
	public void TC14() throws IOException {
		wait(2);
		unifiedSearch("Client", ClientText[0]);
		wait(4);
		driver1.findElement(oipa_listbill_leftlink).click();
		wait(4);

		driver1.findElement(oipa_listbill_1strow_expand).click();

		wait(4);
		driver1.findElement(oipa_listbill_dueDate).clear();
		driver1.findElement(oipa_listbill_dueDate).sendKeys(xls.getCellData("GC_Client_1", 7, 11));
		wait(3);
		driver1.findElement(oipa_listbill_amount).clear();
		wait(3);
		driver1.findElement(oipa_listbill_amount).sendKeys(xls.getCellData("GC_Client_1", 8, 11));
		wait(3);
		driver1.findElement(oipa_listbill_save).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC14_Output1");
		wait(2);

		// String
		// update=driver1.findElement(oipa_listbill_policynumber_updatemessage).getText();
		// Assert.assertEquals(update, "List bill modified successfully");
		// wait(3);

	}

	// Delete a policy from an existing list bill group
	@Test
	public void TC15() throws IOException {
		wait(2);
		unifiedSearch("Client", ClientText[0]);
		wait(4);
		driver1.findElement(oipa_listbill_leftlink).click();
		wait(4);
		driver1.findElement(oipa_listbill_delete).click();
		wait(4);
		driver1.findElement(oipa_listbill_delete_popup_ok).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC15_Output1");
		wait(2);
		driver1.navigate().refresh();
		wait(6);
		// screenshot
		takeScreenShot(driver1, "TC15_Output2");
		wait(2);

	}

	// Update a listbill record from activity
	@Test
	public void TC16() throws IOException {
		wait(2);
		unifiedSearch("Client", ClientText[0]);
		wait(4);
		driver1.findElement(oipa_client_addactivity).click();
		wait(3);
		driver1.findElement(oipa_client_activityDD).click();
		wait(2);
		selectItemInDropdown(driver1, "UpdateListBill");
		wait(5);
		driver1.findElement(oipa_UpdateListbill_activity_firstname).sendKeys(ClientText[0]);
		wait(2);
		driver1.findElement(oipa_UpdateListbill_activity_amount).clear();
		wait(2);
		driver1.findElement(oipa_UpdateListbill_activity_amount).sendKeys(xls.getCellData("GC_Client_1", 8, 11));
		wait(3);
		driver1.findElement(oipa_UpdateListbill_activity_Due).sendKeys(xls.getCellData("GC_Client_1", 7, 11));
		wait(3);
		// screenshot
		takeScreenShot(driver1, "TC16_Output1");
		wait(2);
		driver1.findElement(oipa_client_activity_OKNavigate).click();
		wait(3);
		driver1.findElement(oipa_client_activity_process).click();
		wait(4);
		driver1.findElement(oipa_listbill_leftlink).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC16_Output2");
		wait(2);
		String amount = driver1.findElement(oipa_listbill_1strow_amount_value).getText();
		wait(2);
		String due = driver1.findElement(oipa_listbill_1strow_dueDate_value).getText();
		wait(2);
		String new_amount = xls.getCellData("GC_Client_1", 8, 11) + "0 USD";
		wait(2);
		Assert.assertEquals(amount, new_amount);
		wait(2);
		Assert.assertEquals(due, xls.getCellData("GC_Client_1", 7, 11));
		wait(2);

	}

	// Delete a policy from an existing list bill group through an activity
	@Test
	public void TC17() throws IOException {
		wait(2);
		unifiedSearch("Client", ClientText[0]);
		wait(4);
		driver1.findElement(oipa_listbill_leftlink).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC17_Output1");
		wait(2);
		driver1.findElement(oipa_client_addactivity).click();
		wait(3);
		driver1.findElement(oipa_client_activityDD).click();
		wait(2);
		selectItemInDropdown(driver1, "RemoveListBill");
		wait(5);
		driver1.findElement(oipa_UpdateListbill_activity_firstname).sendKeys(ClientText[0]);
		wait(3);

		driver1.findElement(oipa_client_activity_OKNavigate).click();
		wait(3);
		driver1.findElement(oipa_client_activity_process).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC17_Output2");
		wait(2);
		driver1.findElement(oipa_listbill_leftlink).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC17_Output3");
		wait(2);
		String nodata = driver1.findElement(oipa_listbill_1strow_nodata_display).getText();
		wait(2);
		Assert.assertEquals(nodata, "No data to display.");
		wait(2);

	}

	// Add a policy to the list bill from policy level activity

	@Test
	public void TC18() throws IOException {

		// Client_05 is 05 type client creation

		driver1.findElement(oipa_createDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Client");
		wait(3);
		driver1.findElement(oipa_CreateButton).click();
		wait(3);
		driver1.findElement(oipa_client_TypeDD).click();
		wait(2);
		selectItemInDropdown(driver1, "Group");
		wait(3);
		String client_05Type = xls.getCellData("GC_Client_1", 9, 11) + rand.nextInt(1000);
		driver1.findElement(oipa_client_Groupname).sendKeys(client_05Type);
		wait(2);
		driver1.findElement(oipa_client_Groupnumber)
				.sendKeys(xls.getCellData("GC_Client_1", 9, 11) + rand.nextInt(1000));
		wait(2);
		driver1.findElement(oipa_client_savebutton).click();
		wait(5);

		unifiedSearch("Policy", PolicyText[1]);
		wait(4);
		// adding listbill to policy
		driver1.findElement(oipa_client_addactivity).click();
		wait(3);
		driver1.findElement(oipa_client_activityDD).click();
		selectItemInDropdown(driver1, "AddListBill");
		wait(5);
		driver1.findElement(oipa_UpdateListbill_activity_firstname).sendKeys(ClientText[0]);
		wait(2);

		driver1.findElement(oipa_UpdateListbill_activity_amount).sendKeys(xls.getCellData("GC_Client_1", 8, 11));
		wait(3);
		driver1.findElement(oipa_UpdateListbill_activity_Due).sendKeys(xls.getCellData("GC_Client_1", 7, 11));
		wait(3);
		driver1.findElement(oipa_AddListbill_activity_policynumber).sendKeys(PolicyText[1]);
		wait(3);
		driver1.findElement(oipa_client_activity_OKNavigate).click();
		wait(3);
		driver1.findElement(oipa_client_activity_process).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC18_Output1");
		wait(2);
		unifiedSearch("Client", ClientText[0]);
		wait(4);
		driver1.findElement(oipa_listbill_leftlink).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC18_Output2");
		wait(2);
		String policynumber = driver1.findElement(oipa_listbill_1strow_policynumber_value).getText();
		wait(2);
		Assert.assertEquals(policynumber, PolicyText[1]);
		wait(2);
		// verifying AddToListBill attachedrule errors
		unifiedSearch("Policy", PolicyText[1]);
		wait(4);
		driver1.findElement(oipa_client_addactivity).click();
		wait(3);
		driver1.findElement(oipa_client_activityDD).click();
		selectItemInDropdown(driver1, "AddListBill1");
		wait(4);

		driver1.findElement(oipa_UpdateListbill_activity_firstname).sendKeys(client_05Type);
		wait(2);

		driver1.findElement(oipa_UpdateListbill_activity_amount).sendKeys(xls.getCellData("GC_Client_1", 8, 11));
		wait(3);
		driver1.findElement(oipa_UpdateListbill_activity_Due).sendKeys(xls.getCellData("GC_Client_1", 7, 11));
		wait(3);
		driver1.findElement(oipa_AddListbill_activity_policynumber).sendKeys(PolicyText[1]);
		wait(3);
		driver1.findElement(oipa_client_activity_OKNavigate).click();
		wait(3);
		driver1.findElement(oipa_client_activity_process).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC18_Output3");
		wait(2);
		String error1 = driver1.findElement(oipa_policy_activity_failuremessage).getText();
		wait(2);
		if (error1.contains("Client ineligible for List Bill records")) {
			error1 = "Client ineligible for List Bill records";
		}
		wait(2);
		Assert.assertEquals(error1, "Client ineligible for List Bill records");
		wait(2);
		driver1.findElement(oipa_policy_activity_failuremessage_returntoapp).click();
		wait(5);
		// error2 validation
		driver1.findElement(oipa_client_addactivity).click();
		wait(3);
		driver1.findElement(oipa_client_activityDD).click();
		selectItemInDropdown(driver1, "AddList2");
		wait(3);
		driver1.findElement(oipa_UpdateListbill_activity_firstname).sendKeys(ClientText[0]);
		wait(2);

		driver1.findElement(oipa_UpdateListbill_activity_amount).sendKeys(xls.getCellData("GC_Client_1", 8, 11));
		wait(3);
		driver1.findElement(oipa_UpdateListbill_activity_Due).sendKeys(xls.getCellData("GC_Client_1", 7, 11));
		wait(3);
		driver1.findElement(oipa_AddListbill_activity_policynumber).sendKeys(PolicyText[1]);
		wait(3);
		driver1.findElement(oipa_policy_activity_OK_button).click();
		wait(5);
		driver1.findElement(oipa_client_activity_process).click();
		wait(4);
		// screenshot
		takeScreenShot(driver1, "TC18_Output4");
		wait(2);
		String error2 = driver1.findElement(oipa_policy_activity_failuremessage).getText();
		wait(2);
		if (error2.contains("Invalid List Bill Field Name")) {
			error2 = "Invalid List Bill Field Name";
		}
		wait(2);
		Assert.assertEquals(error2, "Invalid List Bill Field Name");
		wait(2);

	}

	// TC 19 :Verify that activity is added and processed successfully

	@Test
	public void TC19() throws IOException {

		// client creation
		driver1.findElement(oipa_createDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Client");
		wait(3);
		driver1.findElement(oipa_CreateButton).click();
		wait(3);
		driver1.findElement(oipa_client_FirstName).sendKeys(xls.getCellData("GC_Client_1", 0, 2) + rand.nextInt(1000));
		wait(2);
		driver1.findElement(oipa_client_LastName).sendKeys(xls.getCellData("GC_Client_1", 1, 2));
		wait(2);
		driver1.findElement(oipa_client_DOB).sendKeys(xls.getCellData("GC_Client_1", 2, 2));
		wait(2);
		driver1.findElement(oipa_client_TaxID).sendKeys(xls.getCellData("GC_Client_1", 3, 2));
		wait(2);
		driver1.findElement(oipa_client_savebutton).click();
		wait(5);

		driver1.findElement(oipa_client_addactivity).click();
		wait(3);
		driver1.findElement(oipa_client_activityDD).click();
		wait(2);
		selectItemInDropdown(driver1, "TestTransaction2");
		wait(5);

		// step5
		boolean oknavigate = driver1.findElement(oipa_client_activity_OKNavigate).isDisplayed();
		boolean ok = driver1.findElement(oipa_policy_activity_OK_button).isDisplayed();
		boolean cancel = driver1.findElement(oipa_client_activity_cancel_button).isDisplayed();
		String effectivedate = driver1.findElement(oipa_client_Activity_effectivedate).getText();

		// Assert.assertEquals(effectivedate, "7/9/2002");
		Assert.assertEquals(oknavigate, true);
		Assert.assertEquals(ok, true);
		Assert.assertEquals(cancel, true);

		wait(2);
		driver1.findElement(oipa_client_activity_cancel_button).click();
		wait(3);
		boolean clienttab = driver1.findElement(oipa_client_tab_haritha).isDisplayed();
		Assert.assertEquals(clienttab, true);

		// step6
		driver1.findElement(oipa_client_addactivity).click();
		wait(2);
		driver1.findElement(oipa_client_activityDD).click();
		wait(3);
		selectItemInDropdown(driver1, "TestTransaction2");
		wait(4);
		driver1.findElement(oipa_client_activity_OKNavigate).click();
		wait(4);
		boolean Addactivity_button = driver1.findElement(oipa_client_addactivity).isDisplayed();
		Assert.assertEquals(Addactivity_button, true);
		wait(3);

		// step9
		driver1.findElement(oipa_client_addactivity).click();
		wait(3);
		driver1.findElement(oipa_client_activityDD).click();
		wait(3);
		selectItemInDropdown(driver1, "TestTransaction2");
		wait(4);

		driver1.findElement(oipa_client_activity_cancel_button).click();
		wait(3);
		String fetchedrows = driver1.findElement(oipa_client_fetchedrows).getText();
		Assert.assertEquals(fetchedrows, "Fetched 1 of 1 Activity");

		// step10
		driver1.findElement(oipa_client_addactivity).click();
		wait(3);
		driver1.findElement(oipa_client_activityDD).click();
		wait(3);
		selectItemInDropdown(driver1, "TestTransaction2");
		wait(4);
		driver1.findElement(oipa_client_Activity_effectivedate).clear();
		wait(3);
		driver1.findElement(oipa_client_Activity_effectivedate).sendKeys("1/1/2000");
		wait(3);
		driver1.findElement(oipa_policy_activity_OK_button).click();
		wait(4);

		// step12
		driver1.findElement(oipa_client_processAll_button).click();
		wait(4);
		String fetchedrows2 = driver1.findElement(oipa_client_fetchedrows).getText();
		Assert.assertEquals(fetchedrows2, "Fetched 2 of 2 Activity");
		// screenshot
		takeScreenShot(driver1, "TC19_Output1");
		wait(2);

	}

	// TC 9:Verify that activity with attached rules are processing successfully

	@Test
	public void TC20() throws IOException {
		wait(3);
		unifiedSearch("Client", clientfirstname);
		wait(5);

		driver1.findElement(oipa_client_activities_link).click();
		wait(4);
		driver1.findElement(oipa_client_addactivity).click();
		wait(3);
		driver1.findElement(oipa_client_activityDD).click();
		wait(3);
		selectItemInDropdown(driver1, "NewGB");
		wait(4);
		driver1.findElement(oipa_client_NewGB_Activity_Billing_StartDate)
				.sendKeys(xls.getCellData("GC_Client_1", 10, 11));
		wait(2);
		driver1.findElement(oipa_client_NewGB_Activity_BillingEndDate).sendKeys(xls.getCellData("GC_Client_1", 11, 11));
		wait(2);
		driver1.findElement(oipa_client_NewGB_Activity_GroupCustomer_number).sendKeys(CX_number);
		wait(2);
		driver1.findElement(oipa_client_NewGB_Activity_ThreshholdMaximumAmount).clear();
		wait(2);
		driver1.findElement(oipa_client_NewGB_Activity_ThreshholdMaximumAmount)
				.sendKeys(xls.getCellData("GC_Client_1", 12, 11));
		wait(2);
		driver1.findElement(oipa_policy_activity_OK_button).click();
		wait(5);
		driver1.findElement(oipa_client_activity_process).click();
		wait(6);
		driver1.findElement(oipa_client_activity_row1_expander).click();
		wait(4);
		boolean billingtab = driver1.findElement(oipa_client_NewGB_Activity_Billingtab).isDisplayed();
		Assert.assertEquals(billingtab, true);

		driver1.findElement(oipa_client_NewGB_Activity_Billingtab).click();
		wait(5);
		String cxname = driver1.findElement(oipa_client_NewGB_Activity_Billingtab_customername).getText();
		Assert.assertEquals(cxname, CustomerText[1].trim());

		// screenshot
		takeScreenShot(driver1, "TC20_Output1");
		wait(2);

	}

	@AfterClass
	public void postTestCase() {
		closeBrowser(driver1);
	}

}
