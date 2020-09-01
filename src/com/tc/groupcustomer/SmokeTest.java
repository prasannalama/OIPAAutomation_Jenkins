package com.tc.groupcustomer;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.interactions.Actions;


import com.Library.OIPA_Library;

public class SmokeTest extends OIPA_Library {

	// Tester : Prasanna L
	// Functionality : Smoke Test
	Random rand = new Random();
	public String[] PolicyText, ClientText, CustomerText, CustomerText2;
	public String policyHeading, clientHeading, CustomerHeading, clientfirstname, pname, CX_number,
			existed_policyNumber;
	Actions a;

	@BeforeClass
	public void login() throws Throwable {
		
		System.out.println("***************Smoke Test******************************");

		openBrowser();
		login(driver1);
		//clientCreation();
		policycreation();
		

	}

	@Test
	public void policycreation() throws IOException {
		// creating WIS policy
		driver1.findElement(oipa_createDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Policy");
		wait(3);
		driver1.findElement(oipa_CreateButton).click();
		wait(3);
		driver1.findElement(oipa_policy_companyDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Westpac");
		wait(3);
		driver1.findElement(oipa_policy_productDD).click();
		wait(3);
		selectItemInDropdown(driver1, "BTBS-Standard");
		wait(5);
		driver1.findElement(oipa_policy_planDD).click();
		wait(5);
		selectItemInDropdown(driver1, "AGL-BTBS-ST-Plan1");
		wait(5);
		driver1.findElement(oipa_policy_policyname).sendKeys(xls.getCellData("Policy", 0, 2) + rand.nextInt(1000));
		wait(3);
		driver1.findElement(oipa_policy_BTBS_policynumber).sendKeys(xls.getCellData("Policy", 1, 2) + rand.nextInt(1000));
		wait(3);
		/**driver1.findElement(oipa_policy_RiskCommencementDate).sendKeys(xls.getCellData("Policy", "RiskCommencementDate", 2));
        wait(3);
        driver1.findElement(oipa_policy_RiskCessationDate).sendKeys(xls.getCellData("Policy", "RiskCessationDate", 2));
        wait(3);
        driver1.findElement(oipa_policy_MemberAccountNumber).sendKeys(xls.getCellData("Policy", "MemberAccountNumber", 2));
        wait(3);
        driver1.findElement(oipa_policy_EmployerBenefitCategoryCode).sendKeys(xls.getCellData("Policy", "EmployerBenefitCategoryCode", 2));
        wait(3);
        driver1.findElement(oipa_policy_SmokerStatus).click();
		wait(4);
		selectItemInDropdown(driver1, "Smoker");
		wait(3);
		driver1.findElement(oipa_policy_CollarRating).sendKeys(xls.getCellData("Policy", "CollarRating", 2));
	        wait(3);
	    driver1.findElement(oipa_policy_OccupationCode).sendKeys(xls.getCellData("Policy", "OccupationCode", 2));
	    wait(3); 
	    driver1.findElement(oipa_policy_OccupationDescription).sendKeys(xls.getCellData("Policy", "OccupationDescription", 2));
	    wait(3);
	    driver1.findElement(oipa_policy_LastAnnualReviewDate).sendKeys(xls.getCellData("Policy", "LastAnnualReviewDate", 2));
	    wait(3);
	    driver1.findElement(oipa_policy_PremiumEffectiveDate).sendKeys(xls.getCellData("Policy", "PremiumEffectiveDate", 2));
	    wait(3);
		driver1.findElement(oipa_policy_TotalPremiumDue).clear();
	    wait(3);
	    driver1.findElement(oipa_policy_TotalPremiumDue).sendKeys(xls.getCellData("Policy", "TotalPremiumDue", 2));
	    wait(3);
	    driver1.findElement(oipa_policy_PremiumFrequency).click();
		wait(3);
		selectItemInDropdown(driver1, "Annual");
		wait(3);
			driver1.findElement(oipa_policy_PremiumWaiverapplicableCheckbox).click();
		wait(3);
		 driver1.findElement(oipa_policy_PremiumWaiverEffectiveDate).sendKeys(xls.getCellData("Policy", "PremiumWaiverEffectiveDate", 2));
		    wait(3);
		
		    driver1.findElement(oipa_policy_FrequencyLoading).sendKeys(xls.getCellData("Policy","FrequencyLoading", 2));
		    wait(3);
		    driver1.findElement(oipa_policy_ModalPremium).clear();
		    wait(3);
			driver1.findElement(oipa_policy_ModalPremium).sendKeys(xls.getCellData("Policy","ModalPremium", 2));
			wait(3);
		    driver1.findElement(oipa_policy_ExplicitStampDutyFlagCheckbox).click();
		    wait(3);
		    driver1.findElement(oipa_policy_RateGuaranteeFlagCheckbox).click();
		    wait(3);*/
		    driver1.findElement(oipa_policy_RateGuaranteEndDate).sendKeys(xls.getCellData("Policy", "RateGuaranteEndDate", 2));
		    wait(3);
		    driver1.findElement(oipa_policy_EmployerPaysPremium).click();
		    wait(3);
		    selectItemInDropdown(driver1, "Y");
			wait(3);
			driver1.findElement(oipa_policy_PlanRatingFactor).sendKeys(xls.getCellData("Policy", "PlanRatingFactor", 2));
		    wait(3);
		    driver1.findElement(oipa_policy_RegistrySystemCode).click();
		    wait(3);
		    selectItemInDropdown(driver1, "OIPA");
			wait(3);
		    driver1.findElement(oipa_policy_PremiumPaymentType).click();
		    wait(3);
		    selectItemInDropdown(driver1, "Advance");
			wait(3);
			driver1.findElement(oipa_policy_UnderwrittenOccupationClass).sendKeys(xls.getCellData("Policy", "UnderwrittenOccupationClass", 2));
		    wait(3);
		    driver1.findElement(oipa_policy_AnnualisedStampDutyAmountFlagCheckbox).click();
		    wait(3);
		    driver1.findElement(oipa_policy_ClaimIndexationBenefitApplicableCheckbox).click();
		    wait(3);
		    driver1.findElement(oipa_policy_ExcludeFromSCITaxCheckbox).click();
		    wait(3);
		    /*driver1.findElement(oipa_policy_BeneficiaryDetails).click();
		    wait(3);
		    selectItemInDropdown(driver1, "1");
			wait(3);
		    driver1.findElement(oipa_policy_BeneficiaryName).sendKeys(xls.getCellData("Policy", "BeneficiaryName", 2));
		    wait(3);
		    driver1.findElement(oipa_policy_BeneficiaryRelationship).click();
		    wait(3);
		    selectItemInDropdown(driver1, "Husband");
			wait(3);
			driver1.findElement(oipa_policy_BeneficiarySplitPercentage).clear();
		    wait(3);
		    driver1.findElement(oipa_policy_BeneficiarySplitPercentage).sendKeys(xls.getCellData("Policy", "BeneficiarySplitPercentage", 2));
		    wait(3);*/
		driver1.findElement(oipa_policy_savebutton).click();
		wait(3);
		// taking screenshot of policy creation
		takeScreenShot(driver1, "WSI_policy_creation");
		wait(3);
		policyHeading = driver1.findElement(oipa_policyText).getText();
		wait(3);
		PolicyText = policyHeading.split("#");
		System.out.println("Policy Number is: " + PolicyText[1]);

	}
/*
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
	*/

	@AfterClass
	public void postTestCase() {
		closeBrowser(driver1);
	}

}
