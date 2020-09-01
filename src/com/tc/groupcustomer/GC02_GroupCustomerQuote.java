package com.tc.groupcustomer;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Library.OIPA_Library;

import xls.ShineXlsReader; 


public class GC02_GroupCustomerQuote extends OIPA_Library {
	
	//Tester : Abhishek	
	//Functionality : Group Customer Quote
	Random rand = new Random();
	int randomNum = ThreadLocalRandom.current().nextInt(100,1000);
	String CustNumber;
	public static WebDriver driver;
	public static ShineXlsReader xls=new ShineXlsReader(".\\src\\com\\Utilities\\TestData.xlsx");
	
	@BeforeClass
	public void StartTC() throws Throwable{
		
		System.out.println("**************************In GC02_GroupCustomer Quote Before Class*****************************");
		openBrowser();
		driver = login(driver);
	}
	
	@Test
	public void ATCPreReq() throws IOException {
		driver.findElement(oipa_createDD).click();
		wait(2);
		selectItemInDropdown(driver, "Customer");
		wait(2);
		driver.findElement(oipa_CreateButton).click();
		wait(5);
		driver.findElement(oipa_customer_typeDD).click();
		wait(2);
		selectItemInDropdown(driver, "Group Customer");
		wait(2);
		int x = rand.nextInt(999);
		String s = String.valueOf(x);
		wait(2);
		String GCName = xls.getCellData("GroupCustomerQuote", "customerName", 2).concat(s);
		wait(2);
		String GCNum = xls.getCellData("GroupCustomerQuote", "customerNumber", 2).concat(s);
		wait(2);
		String taxId = String.valueOf(randomNum);
		wait(2);
		taxId = taxId.concat(taxId.concat(taxId));
		wait(3);
		driver.findElement(oipa_customer_customerName).sendKeys(GCName);
		wait(3);
		driver.findElement(oipa_customer_customerName).clear();
		driver.findElement(oipa_customer_customerName).sendKeys(GCName);
		wait(2);
		if(driver.findElement(oipa_customer_customerNumber).isEnabled()) {
			driver.findElement(oipa_customer_customerNumber).sendKeys(GCNum);
		}
		wait(3);
		driver.findElement(oipa_customer_taxID).sendKeys(taxId);
		wait(3);
		driver.findElement(oipa_customer_customerLegalName).sendKeys(GCName);
		wait(3);
		driver.findElement(oipa_customer_customerLegalName).clear();
		driver.findElement(oipa_customer_customerLegalName).sendKeys(GCName);
		wait(3);
		driver.findElement(oipa_customer_taxID).clear();
		driver.findElement(oipa_customer_taxID).sendKeys(taxId);
		wait(3);
		if(driver.findElement(oipa_customer_customerNumber).isEnabled()) {
			driver.findElement(oipa_customer_customerNumber).sendKeys(GCNum);
		}
		wait(5);
		driver.findElement(oipa_customer_taxID).clear();
		driver.findElement(oipa_customer_taxID).sendKeys(taxId);
		driver.findElement(oipa_customer_taxID).sendKeys(taxId);
		wait(3);
		driver.findElement(oipa_customer_saveButton).click();
		wait(4);
		if(exists(newCust_summary_newCustNumber)) {
			CustNumber = driver.findElement(newCust_summary_newCustNumber).getText();
			System.out.println("Customer number is "+CustNumber);
		}
//		if(exists(newCust_summary_newCustNumberAuto)) {
//			CustNumber = driver.findElement(newCust_summary_newCustNumberAuto).getText();
//			System.out.println("Customer number is "+CustNumber);	
//		}
		takeScreenShot(driver1, "CustomerCreated");
	}
	
	
	/*1.Verify the quote screen UI when no quote is present*/	
	@Test
	public void BTC001() throws IOException {
		if(driver.findElement(newCust_Quote_NavLink).isDisplayed()) {
			driver.findElement(newCust_Quote_NavLink).click();
			wait(5);
		}
		if(driver.findElement(newCust_QuoteFilter_CollpaseAllSearch).isDisplayed()) {
			Assert.assertTrue(exists(newCust_QuoteFilter_CollpaseAllSearch), "Collapsable Quote section is not present");
			Assert.assertTrue(exists(newCust_QuoteFilter_QuoteNumber), "Quote Number field not present");
			Assert.assertTrue(exists(newCust_QuoteFilter_Type), "Type dropdown field not present");
			Assert.assertTrue(exists(newCust_QuoteFilter_From), "From field not present");
			Assert.assertTrue(exists(newCust_QuoteFilter_To), "To field not present");
			Assert.assertTrue(exists(newCust_QuoteFilter_RefreshButton), "Refresh Button not present");
		}
		Assert.assertTrue(exists(newCust_Quote_AddIcon), "Add Icon not present");
		Assert.assertTrue(exists(newCust_Quote_QuoteTypeDropdown), "Quote Type Dropdown not present");
		Assert.assertTrue(exists(newCust_Quote_QuoteName), "Quote Name field not present");
		Assert.assertTrue(exists(newCust_Quote_AddQuoteButton), "Add Quote Button not present");
		Assert.assertFalse(driver.findElement(newCust_Quote_QuoteTable).isDisplayed(), "Quote Table is displayed even when Quotes are not present");	
		takeScreenShot(driver,"QuoteScreenWithoutData");
		System.out.println("Quote Screen is displayed as per specifications when no data is entered.");
}
	/*1 . Create a new Quote*/	
	@Test
	public void CTC002() throws IOException {
		String s = String.valueOf(randomNum);
		driver.findElement(newCust_Quote_QuoteTypeDropdownClick).click();
		selectItemInDropdown(driver, "New Business");
		wait(2);
		driver.findElement(newCust_Quote_QuoteNameClick).click();
		selectItemInDropdown(driver, "New Business CQ");
		wait(2);
		driver.findElement(newCust_Quote_AddQuoteButton).click();;
		wait(5);
		driver.findElement(newCust_Quote_NewQuote_EffectiveDate).sendKeys(xls.getCellData("GroupCustomerQuote", "EffectiveDate", 2));
		wait(2);
		driver.findElement(newCust_Quote_NewQuote_ExpirationDate).sendKeys(xls.getCellData("GroupCustomerQuote", "ExpirationDate", 2));
		wait(2);
		if(driver.findElement(newCust_Quote_NewQuote_QuoteNumber).isEnabled()) {
			driver.findElement(newCust_Quote_NewQuote_QuoteNumber).sendKeys(xls.getCellData("GroupCustomerQuote", "QuoteNumber", 2).concat(s));
			wait(2);
		}
		if(driver.findElement(newCust_Quote_NewQuote_Version).isEnabled()) {
			Assert.fail("Version Field Should be disabled");
			takeScreenShot(driver,"VersionFieldEnabled_Failed");
		}
		if(driver.findElement(newCust_Quote_NewQuote_StatusCode).isEnabled()) {
			Assert.fail("Status Code Field Should be disabled");
			takeScreenShot(driver,"StatusCodeFieldEnabled_Failed");
		}
		driver.findElement(newCust_Quote_NewQuote_saveButton).click();
		if(exists(Quote_AddBenefits_Confirmation)) {				
			NavigateToElement(driver,Quote_AddBenefits_Confirmation);
			HighlightElement(driver,Quote_AddBenefits_Confirmation);
			takeScreenShot(driver,"Quote_NewQuoteAdded_Confirmation");
			UnhighlightElement(driver,Quote_AddBenefits_Confirmation);			
		}
		else {
			System.out.println("No Confirmation message displayed.");
		}
		wait(5);
		String prop = driver.findElement(newCust_Quote_QuoteExpander).getAttribute("aria-expanded");
		Assert.assertTrue(prop.equalsIgnoreCase("true"), "Created Quote is not expanded");
		takeScreenShot(driver,"QuoteCreated");
	}
	
	/* 2. Verify that fixed fields are disabled after the quote is created.*/	
	@Test
	public void DTC003() throws IOException {
		Assert.assertTrue(! driver.findElement(newCust_Quote_NewQuote_EffectiveDate).isEnabled(), "EffectiveDate shouldn't be enabled after Save.");
		Assert.assertTrue(! driver.findElement(newCust_Quote_NewQuote_QuoteNumber).isEnabled(), "QuoteNumber shouldn't be enabled after Save.");
		Assert.assertTrue(driver.findElement(newCust_Quote_QuoteTable).isDisplayed(), "QuoteTable is not displayed after Save.");
		takeScreenShot(driver,"QuoteFixedFieldsDisabled");		
	}
	
	/*	1 .Verify the Quote table after Save on Quote screen in OIPA.*/
	@Test
	public void ETC004() throws IOException {
		Assert.assertTrue(exists(newCust_Quote_QuoteTable), "QuoteTable is not displayed after Save.");
		Assert.assertTrue(driver.findElement(newCust_Quote_QuoteTable).isDisplayed(), "QuoteTable is not displayed after Save.");
		String prop = driver.findElement(newCust_Quote_QuoteExpander).getAttribute("aria-expanded");
		Assert.assertTrue(prop.equalsIgnoreCase("true"), "Created Quote is not expanded");
		takeScreenShot(driver,"QuoteTable_Expanded_Displayed");
	}
	
	/*2 . Verify the behaviour of hamberger Menu*/
		@Test
		public void FTC005() throws IOException {
			if(exists(Quote_NewQuote_HamburgerMenu)) {
				Assert.assertTrue(driver.findElement(Quote_NewQuote_HamburgerMenu).isDisplayed(), "Hamburger Menu is not displayed after Save.");
				driver.findElement(Quote_NewQuote_HamburgerMenu).click();	
				driver.findElement(Quote_NewQuote_HamburgerMenu).click();
				wait(5);
			}
			else {
				System.out.println("Hamburger Menu is not displayed after Save.");
			}
		
	}
		
	/*3. Verify the elements on Hamberger Menu*/
		@Test
		public void GTC006() throws IOException {	
			Assert.assertTrue(exists(Quote_NewQuote_HamBurger_CreateNewVersion), "Create New Version Option is not displayed in Hamburger Menu");
			Assert.assertTrue(exists(Quote_NewQuote_HamBurger_ProcessQuote), "Process Quote Option is not displayed in Hamburger Menu");
	}
		
	/*1 . Verify the hamburger menu options for pending quote */
		@Test
		public void HTC007() throws IOException {	
			Assert.assertTrue(exists(Quote_NewQuote_HamBurger_CreateNewVersion), "Create New Version Option is not displayed in Hamburger Menu");
			Assert.assertTrue(exists(Quote_NewQuote_HamBurger_ProcessQuote), "Process Quote Option is not displayed in Hamburger Menu");
			NavigateToElement(driver,Quote_NewQuote_HamBurger_CreateNewVersion);
			
			takeScreenShot(driver,"QuoteSaved_HamburgerMenu_Pending");
	}	
	
	/* 1 . TC-01-Check that the second tab in quotes screen is Benefits */
		@Test
		public void ITC008() throws IOException{
			String Bene = driver.findElement(Quote_GetBenefitsTabTextByTabNumber).getText();
			Assert.assertTrue(Bene.equalsIgnoreCase("Benefits"), "Second Tab is not Benefits");
			driver.findElement(newCust_Quote_NewQuote_benefits).click();
			wait(5);
			NavigateToElement(driver,Quote_GetBenefitsTabTextByTabNumber);
			HighlightElement(driver,Quote_GetBenefitsTabTextByTabNumber);
			takeScreenShot(driver,"Second_Tab_Benefits");	
			UnhighlightElement(driver,Quote_GetBenefitsTabTextByTabNumber);
		}
		
	/*2 . TC-02-check that 'Add Benefit' button is present in benefits screen*/
		@Test
		public void JTC009() throws IOException{			
			Assert.assertTrue(exists(Quote_AddBenefit_AddBenefitButton), "Add Benefit Button is not present in ");
			driver.findElement(Quote_AddBenefit_AddBenefitButton).click();
			wait(3);
			Assert.assertTrue(driver.findElement(Quote_AddBenefit_Description).isDisplayed(), "Clicking on Add Benefit doesn't display new benefit fields for entry.");
			Assert.assertTrue(driver.findElement(Quote_AddBenefit_Description).isEnabled(), "Clicking on Add Benefit doesn't enable new benefit fields for entry.");
			NavigateToElement(driver,Quote_AddBenefit_AddBenefitButton);
			HighlightElement(driver,Quote_AddBenefit_AddBenefitButton);
			takeScreenShot(driver,"AddBenefits_Button_Present_Editable");
			UnhighlightElement(driver,Quote_AddBenefit_AddBenefitButton);
		}
		
	/* 5 . TC-10-Check that save and cancel buttons are displayed and accessible */		
		@Test
		public void KTC010() throws IOException{
			Assert.assertTrue(exists(Quote_AddBenefit_BenefitSave), "Save Button is not present While adding Benefit");
			NavigateToElement(driver,Quote_AddBenefit_BenefitSave);
			HighlightElement(driver,Quote_AddBenefit_BenefitSave);
			takeScreenShot(driver,"AddBenefits_Save_Button_Present");
			UnhighlightElement(driver,Quote_AddBenefit_BenefitSave);
			Assert.assertTrue(exists(Quote_AddBenefits_BenefitCancel), "Cancel Button is not present While adding Benefit");
			NavigateToElement(driver,Quote_AddBenefits_BenefitCancel);
			HighlightElement(driver,Quote_AddBenefits_BenefitCancel);
			takeScreenShot(driver,"AddBenefits_Cancel_Button_Present");
			UnhighlightElement(driver,Quote_AddBenefits_BenefitCancel);			
		}
		
	/* 3 . TC-03-If data is saved successfully,then user should receive Success message*/		
		@Test
		public void LTC011() throws IOException{
			String s = String.valueOf(randomNum);
			driver.findElement(Quote_AddBenefit_Description).sendKeys("Benefit_Auto_Test".concat(s));
			wait(2);
			driver.findElement(Quote_AddBenefit_BenefitSave).click();
			if(exists(Quote_AddBenefits_Confirmation)) {				
				NavigateToElement(driver,Quote_AddBenefits_Confirmation);
				HighlightElement(driver,Quote_AddBenefits_Confirmation);
				takeScreenShot(driver,"Quote_AddBenefits_Confirmation");
				UnhighlightElement(driver,Quote_AddBenefits_Confirmation);			
			}
			else {
				System.out.println("No Confirmation message displayed.");
			}
		}
		
	/* 1 . Adding benefit to quote succesfully and viewing existing benefits */		
		@Test
		public void MTC012() throws IOException{
			wait(3);
			Assert.assertTrue(exists(Quote_AddBenefits_DescriptionAfterSave),"Saved benefit not displayed.");
			NavigateToElement(driver,Quote_AddBenefits_DescriptionAfterSave);
			HighlightElement(driver,Quote_AddBenefits_DescriptionAfterSave);
			takeScreenShot(driver,"Quote_AddBenefits_DescriptionAfterSave");
			UnhighlightElement(driver,Quote_AddBenefits_DescriptionAfterSave);			
		}
		
	/*4 . TC-05-Check that user will see the 'Delete' icon in the Action column of the saved Benefit */		
		@Test
		public void NTC013() throws IOException{		
			Assert.assertTrue(exists(Quote_AddBenefits_DescriptionAfterSave),"Delete Icon not displayed for row.");
			NavigateToElement(driver,Quote_AddBenefits_DeleteRow);
			HighlightElement(driver,Quote_AddBenefits_DeleteRow);
			takeScreenShot(driver,"Quote_AddBenefits_DeleteRow");
			UnhighlightElement(driver,Quote_AddBenefits_DeleteRow);			
		}
		
	/* 2 . Deleting existing Benefit from benefit screen */
		@Test
		public void OTC014() throws IOException{
			
			driver.findElement(Quote_AddBenefits_DeleteRow).click();
			wait(5);
			driver.findElement(Quote_AddBenefits_ConfirmDelete).click();
			wait(10);
			Assert.assertFalse(exists(Quote_AddBenefits_DescriptionAfterSave),"Deleted benefit Still getting");
			NavigateToElement(driver,Quote_AddBenefits_BenefitTable);
			HighlightElement(driver,Quote_AddBenefits_BenefitTable);
			takeScreenShot(driver,"Quote_AddBenefits_BenefitTable");
			UnhighlightElement(driver,Quote_AddBenefits_BenefitTable);
		}
			
	/* 1 . Tc_01_Check that third tab in Quotes screen is Members */
		@Test
		public void PTC0015() throws IOException{
			String Bene = driver.findElement(Quote_GetMembersTabTextByTabNumber).getText();
			Assert.assertTrue(Bene.equalsIgnoreCase("Members"), "Third Tab is not Members");
			driver.findElement(Quote_GetMembersTabTextByTabNumber).click();
			wait(3);
			HighlightElement(driver,Quote_GetMembersTabTextByTabNumber);
			takeScreenShot(driver,"Third_Tab_Members");	
			UnhighlightElement(driver,Quote_GetMembersTabTextByTabNumber);
		}
		
	/*2 . TC_02_Check that Browse field is present and editable in members Screen*/
		@Test
		public void QTC0016() throws IOException{
			Assert.assertTrue(exists(Quote_BrowseFieldMemberClaim), "Browse field is not present in Quote Member Screen");
			Assert.assertTrue(driver.findElement(Quote_BrowseFieldMemberClaim).isDisplayed(), "Browse field is not Displayed in Quote Member Screen");
			Assert.assertTrue(driver.findElement(Quote_BrowseFieldMemberClaim).isEnabled(), "Browse field is not enabled in Quote Member Screen");
			NavigateToElement(driver,Quote_BrowseFieldMemberClaim);
			HighlightElement(driver,Quote_BrowseFieldMemberClaim);
			takeScreenShot(driver,"MembersBrowseEnabledDisplayed");	
			UnhighlightElement(driver,Quote_BrowseFieldMemberClaim);
		}
		
	/* 5 . TC_05_Check that Import button is visible and enabled */
		@Test
		public void RTC0017 () throws IOException{
			Assert.assertTrue(exists(Quote_UploadButtonMemberClaim), "Upload Button is not present in Quote Member Screen");
			Assert.assertTrue(driver.findElement(Quote_UploadButtonMemberClaim).isDisplayed(), "Upload Button is not Displayed in Quote Member Screen");
			Assert.assertTrue(driver.findElement(Quote_UploadButtonMemberClaim).isEnabled(), "Upload Button is not enabled in Quote Member Screen");
			NavigateToElement(driver,Quote_UploadButtonMemberClaim);
			HighlightElement(driver,Quote_UploadButtonMemberClaim);
			takeScreenShot(driver,"MembersImportEnabledDisplayed");	
			UnhighlightElement(driver,Quote_UploadButtonMemberClaim);			
		}
		
	/* 4 . TC_04_Check that delete button is not visible prior to import */
		@Test
		public void STC0018() throws IOException{
			Assert.assertFalse(exists(Quote_DeleteButtonMemberClaim), "Delete Button is displayed before member data Upload.");
		}
		
	/* 1 . Check that Fourth tab in Quotes screen is Claims */
		@Test
		public void TTC020() throws IOException{
			String Bene = driver.findElement(Quote_GetClaimsTabTextByTabNumber).getText();
			Assert.assertTrue(Bene.equalsIgnoreCase("Claims"), "Fourth Tab is not Claims");
			driver.findElement(Quote_GetClaimsTabTextByTabNumber).click();
			wait(3);
			HighlightElement(driver,Quote_GetClaimsTabTextByTabNumber);
			takeScreenShot(driver,"Fourth_Tab_Claims");	
			UnhighlightElement(driver,Quote_GetClaimsTabTextByTabNumber);
		}
		
		/*2 . Check that Browse field is present and editable in ClaimsScreen*/
		@Test
		public void UTC0021() throws IOException{
			Assert.assertTrue(exists(Quote_BrowseFieldMemberClaim), "Browse field is not present in Quote Member Screen");
			Assert.assertTrue(driver.findElement(Quote_BrowseFieldMemberClaim).isDisplayed(), "Browse field is not Displayed in Quote Member Screen");
			Assert.assertTrue(driver.findElement(Quote_BrowseFieldMemberClaim).isEnabled(), "Browse field is not enabled in Quote Member Screen");
			NavigateToElement(driver,Quote_BrowseFieldMemberClaim);
			HighlightElement(driver,Quote_BrowseFieldMemberClaim);
			takeScreenShot(driver,"MembersBrowseEnabledDisplayed");	
			UnhighlightElement(driver,Quote_BrowseFieldMemberClaim);
		}
		
	/* 5 . TC_05_Check that Import button is visible and enabled */
		@Test
		public void VTC0022 () throws IOException{
			Assert.assertTrue(exists(Quote_UploadButtonMemberClaim), "Upload Button is not present in Quote Member Screen");
			Assert.assertTrue(driver.findElement(Quote_UploadButtonMemberClaim).isDisplayed(), "Upload Button is not Displayed in Quote Member Screen");
			Assert.assertTrue(driver.findElement(Quote_UploadButtonMemberClaim).isEnabled(), "Upload Button is not enabled in Quote Member Screen");
			NavigateToElement(driver,Quote_UploadButtonMemberClaim);
			HighlightElement(driver,Quote_UploadButtonMemberClaim);
			takeScreenShot(driver,"MembersImportEnabledDisplayed");	
			UnhighlightElement(driver,Quote_UploadButtonMemberClaim);			
		}
		
	/* 4 . TC_04_Check that delete button is not visible prior to import */
		@Test
		public void WTC0023() throws IOException{
			Assert.assertFalse(exists(Quote_DeleteButtonMemberClaim), "Delete Button is displayed before member data Upload.");
		}
		
	/*1 . Process the quote*/
		@Test
		public void XTC0024 () throws IOException{
			driver.findElement(Quote_NewQuote_HamburgerMenu).click();
			driver.findElement(Quote_NewQuote_HamburgerMenu).click();
			wait(2);
			driver.findElement(Quote_NewQuote_HamBurger_ProcessQuote).click();
			wait(10);
			NavigateToElement(driver,Quote_DisplayQuote_QuoteStatusProcessed);
			HighlightElement(driver,Quote_DisplayQuote_QuoteStatusProcessed);
			takeScreenShot(driver,"QuoteStausProcessed");	
			UnhighlightElement(driver,Quote_DisplayQuote_QuoteStatusProcessed);
			Assert.assertTrue(driver.findElement(Quote_DisplayQuote_QuoteStatusProcessed).getText().equalsIgnoreCase("Processed"), "Quote Status is not updated to processed.");
		}
		
	/* 3 . Verify that summary section on result screen can be collapsed & expanded. */
		@Test
		public void YTC0025 () throws IOException{
			String prop = driver.findElement(Quote_Results_SummaryCollapseButton).getAttribute("aria-expanded");
			Assert.assertTrue(prop.equalsIgnoreCase("true"), "Result Summary is not expanded");
			NavigateToElement(driver,Quote_Results_SummaryCollapseButton);
			HighlightElement(driver,Quote_Results_SummaryCollapseButton);
			takeScreenShot(driver,"ResultSummaryExpanded");	
			UnhighlightElement(driver,Quote_Results_SummaryCollapseButton);
			wait(2);
			driver.findElement(Quote_Results_SummaryCollapseButton).click();
			wait(3);
			prop = driver.findElement(Quote_Results_SummaryCollapseButton).getAttribute("aria-expanded");
			Assert.assertFalse(prop.equalsIgnoreCase("true"), "Result Summary is not Collapsible");
			NavigateToElement(driver,Quote_Results_SummaryCollapseButton);
			HighlightElement(driver,Quote_Results_SummaryCollapseButton);
			takeScreenShot(driver,"ResultSummaryCollapsed");	
			UnhighlightElement(driver,Quote_Results_SummaryCollapseButton);
			wait(2);
			driver.findElement(Quote_Results_SummaryCollapseButton).click();
			wait(3);
			prop = driver.findElement(Quote_Results_SummaryCollapseButton).getAttribute("aria-expanded");
			Assert.assertTrue(prop.equalsIgnoreCase("true"), "Result Summary is not expanded");
			NavigateToElement(driver,Quote_Results_SummaryCollapseButton);
			HighlightElement(driver,Quote_Results_SummaryCollapseButton);
			takeScreenShot(driver,"ResultSummaryExpanded_1");	
			UnhighlightElement(driver,Quote_Results_SummaryCollapseButton);
		}
		
	/*4 . Verify hamburger menu options based on quote status*/
		@Test
		public void ZTC0026_a () throws IOException {
			driver.findElement(Quote_NewQuote_HamburgerMenu).click();	
			driver.findElement(Quote_NewQuote_HamburgerMenu).click();
			wait(2);
			Assert.assertTrue(exists(Quote_NewQuote_HamBurger_CreateNewVersion), "Create New Version Option is not displayed in Hamburger Menu");
			Assert.assertTrue(exists(Quote_NewQuote_Hamburger_Accept), "Accept Quote Option is not displayed in Hamburger Menu");
			Assert.assertTrue(exists(Quote_NewQuote_Hamburger_Decline), "Decline Quote Option is not displayed in Hamburger Menu");
			NavigateToElement(driver,Quote_NewQuote_Hamburger_Decline);
			HighlightElement(driver,Quote_NewQuote_Hamburger_Decline);
			takeScreenShot(driver,"HamburgerMenu_Processed");	
			UnhighlightElement(driver,Quote_NewQuote_Hamburger_Decline);			
		}
		
	/*2 . Accept the quote*/
		@Test
		public void aTC0027() throws IOException {
			System.out.println("Ananht");
			driver.findElement(Quote_NewQuote_Hamburger_Accept).click();
			wait(5);
			Assert.assertTrue(exists(Quote_AcceptQuote_Confirmation), "Pop Up to confirm acceptance not displayed.");
			Assert.assertTrue(driver.findElement(Quote_AcceptQuote_Confirmation).getText().equalsIgnoreCase("Are you sure you want to accept this quote?"), "Pop Up to confirm Accept, Text not correct");
			NavigateToElement(driver,Quote_AcceptQuote_Confirmation);
			HighlightElement(driver,Quote_AcceptQuote_Confirmation);
			takeScreenShot(driver,"AcceptQuote_Confirm");	
			UnhighlightElement(driver,Quote_AcceptQuote_Confirmation);
			driver.findElement(Quote_AcceptQuote_Confirmation_Ok).click();
			wait(5);
			Assert.assertTrue(exists(Quote_DisplayQuote_QuoteStatusAccepted), "Quote Status not changed to Accepted");
			NavigateToElement(driver,Quote_DisplayQuote_QuoteStatusAccepted);
			HighlightElement(driver,Quote_DisplayQuote_QuoteStatusAccepted);
			takeScreenShot(driver,"AcceptedQuote");	
			UnhighlightElement(driver,Quote_DisplayQuote_QuoteStatusAccepted);
		}
		
	/*4 . Verify hamburger menu options based on quote status*/
		@Test
		public void bTC0026_b () throws IOException {
			driver.findElement(Quote_NewQuote_HamburgerMenu).click();	
			driver.findElement(Quote_NewQuote_HamburgerMenu).click();
			wait(2);
			Assert.assertTrue(exists(Quote_NewQuote_HamBurger_CreateNewVersion), "Create New Version Option is not displayed in Hamburger Menu");
			NavigateToElement(driver,Quote_NewQuote_HamBurger_CreateNewVersion);
			HighlightElement(driver,Quote_NewQuote_HamBurger_CreateNewVersion);
			takeScreenShot(driver,"HamburgerMenu_Accepted");	
			UnhighlightElement(driver,Quote_NewQuote_HamBurger_CreateNewVersion);			
		}	
	
	/*4 . Create new version of the quote*/
		@Test
		public void cTC0028 () throws IOException{
			driver.findElement(Quote_NewQuote_HamBurger_CreateNewVersion).click();
			wait(8);
			Assert.assertTrue(driver.findElement(newCust_Quote_NewQuote_saveButton).isEnabled(), "Save button should be enabled to save new version");
			Assert.assertTrue(driver.findElement(newCust_Quote_NewQuote_EffectiveDate).isEnabled(),"Effective Date field should be enabled for new Version");
			Assert.assertTrue(driver.findElement(newCust_Quote_NewQuote_ExpirationDate).isEnabled(),"Expiration Date field should be enabled for new Version");
			Assert.assertTrue(driver.findElement(newCust_Quote_NewQuote_Description).isEnabled(),"Description field should be enabled for new Version");
			Assert.assertFalse(driver.findElement(newCust_Quote_NewQuote_QuoteNumber).isEnabled(), "Quote Number field should be disabled");
			Assert.assertFalse(driver.findElement(newCust_Quote_NewQuote_Version).isEnabled(), "Version field should be disabled");
			Assert.assertFalse(driver.findElement(newCust_Quote_NewQuote_StatusCode).isEnabled(), "Status Code field should be disabled");
			String prop = driver.findElement(newCust_Quote_NewQuote_Version).getAttribute("aria-valuenow");
			Assert.assertTrue(prop.equalsIgnoreCase("2"), "Version Number should be 2");
			NavigateToElement(driver,newCust_Quote_NewQuote_Version);
			takeScreenShot(driver,"NewVersionCreation");
			driver.findElement(newCust_Quote_NewQuote_saveButton).click();
			wait(8);
			NavigateToElement(driver,Quote_NewQuote_LatestQuote);
			HighlightElement(driver,Quote_NewQuote_LatestQuote);
			takeScreenShot(driver,"NewVersionCreated");	
			UnhighlightElement(driver,Quote_NewQuote_LatestQuote);			
		}
	/*3 . Decline the quote*/
		@Test
		public void dTC0029 () throws IOException{
			driver.findElement(Quote_HamburgerMenu_LatestQuote).click();
			driver.findElement(Quote_HamburgerMenu_LatestQuote).click();
			wait(2);
			driver.findElement(Quote_NewQuote_HamBurger_ProcessQuote).click();
			wait(8);
			Assert.assertTrue(driver.findElement(Quote_DisplayQuote_QuoteStatusProcessed).getText().equalsIgnoreCase("Processed"), "Quote Status is not updated to processed.");
			driver.findElement(Quote_HamburgerMenu_LatestQuote).click();
			driver.findElement(Quote_HamburgerMenu_LatestQuote).click();
			wait(2);
			driver.findElement(Quote_NewQuote_Hamburger_Decline).click();
			wait(5);
			Assert.assertTrue(exists(Quote_AcceptQuote_Confirmation), "Pop Up to confirm acceptance not displayed.");
			Assert.assertTrue(driver.findElement(Quote_AcceptQuote_Confirmation).getText().equalsIgnoreCase("Are you sure you want to Decline this Quote?"), "Pop Up to confirm Decline, Text not correct");
			NavigateToElement(driver,Quote_AcceptQuote_Confirmation);
			HighlightElement(driver,Quote_AcceptQuote_Confirmation);
			takeScreenShot(driver,"DeclineQuote_Confirm");	
			UnhighlightElement(driver,Quote_AcceptQuote_Confirmation);
			driver.findElement(Quote_AcceptQuote_Confirmation_Ok).click();
			wait(5);
			Assert.assertTrue(exists(Quote_DisplayQuote_QuoteStatusRejected), "Quote Status not changed to Rejected");		
			NavigateToElement(driver,Quote_DisplayQuote_QuoteStatusRejected);
			HighlightElement(driver,Quote_DisplayQuote_QuoteStatusRejected);
			takeScreenShot(driver,"DeclinedQuote");	
			UnhighlightElement(driver,Quote_DisplayQuote_QuoteStatusRejected);
		}	
		
	/*4 . Verify hamburger menu options based on quote status*/
		@Test
		public void eTC0026_c () throws IOException {
			driver.findElement(Quote_HamburgerMenu_LatestQuote).click();	
			driver.findElement(Quote_HamburgerMenu_LatestQuote).click();
			wait(2);
			Assert.assertTrue(exists(Quote_NewQuote_HamBurger_CreateNewVersion), "Create New Version Option is not displayed in Hamburger Menu");
			NavigateToElement(driver,Quote_NewQuote_HamBurger_CreateNewVersion);
			HighlightElement(driver,Quote_NewQuote_HamBurger_CreateNewVersion);
			takeScreenShot(driver,"HamburgerMenu_Declined");	
			UnhighlightElement(driver,Quote_NewQuote_HamBurger_CreateNewVersion);			
		}
		
		@AfterClass
		public void aClass(){
			closeBrowser(driver);
		}
}