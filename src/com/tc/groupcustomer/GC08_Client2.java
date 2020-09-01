package com.tc.groupcustomer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Library.OIPA_Library;

public class GC08_Client2 extends OIPA_Library {

	// Tester : Suparna
	// Functionality : GC_Client-2

	public static WebDriver driver;
	Random rand = new Random();
	
	String clientName, clientFirstName, clientLastName;
	String[] splitClientName;
	ArrayList<String> str1;

	@BeforeClass
	public void bClass() throws IOException {
		
		System.out.println("***************GC08 Client2******************************");

		
		
		System.out.println("I am the Before Class opening the Browser");
		rand = new Random();
		try {
			openBrowser();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			System.out.println("Opening the Browser got Failed");
			e.printStackTrace();
		}
		driver = driver1;
		login(driver);
		wait(5);
	}

	// Prerequisite
	@Test
	public void GC200_createClient() throws ClassNotFoundException, SQLException, Exception {
		 
		 int rand_int = rand.nextInt(1000);
		 int rand_int1 = rand.nextInt(99);
			
		System.out.println("Creating Client");
		driver.findElement(oipa_createDD).click();
		wait(2);
		selectItemInDropdown(driver, "Client");
		wait(2);
		driver.findElement(oipa_CreateButton).click();
		wait(3);
		takeScreenShot(driver, "NewClientScreen");
		wait(4);
		driver.findElement(oipa_client_typeDD).click();
		wait(2);
		selectItemInDropdown(driver, "Individual");
		wait(2);
		driver.findElement(oipa_client_legalResidenceCountry).click();
		wait(2);
		selectItemInDropdown(driver, "Japan");
		wait(2);
		driver.findElement(oipa_client_prefix).click();
		wait(2);
		selectItemInDropdown(driver, "Ms");
		wait(2);
		driver.findElement(oipa_client_FirstName).sendKeys(xls.getCellData("GC_Client_2", "FirstName", 2) + rand_int);
		wait(2);
		driver.findElement(oipa_client_LastName).sendKeys(xls.getCellData("GC_Client_2", "LastName", 2) + rand_int1);
		wait(2);
		driver.findElement(oipa_client_Gender).click();
		wait(2);
		driver.findElement(oipa_client_MartialDD).click();
		wait(2);
		selectItemInDropdown(driver, "Unknown");
		wait(2);
		driver.findElement(oipa_client_DOB).sendKeys(xls.getCellData("GC_Client_2", "dateOfBirth", 2));
		wait(2);
		driver.findElement(oipa_client_TaxID).sendKeys(xls.getCellData("GC_Client_2", "taxId", 2) + rand_int);
		wait(2);
		driver.findElement(oipa_client_BirthCountry).click();
		wait(2);
		selectItemInDropdown(driver, "Japan");
		wait(2);
		driver.findElement(oipa_client_citizenShipCountry).click();
		wait(2);
		selectItemInDropdown(driver, "Japan");
		wait(2);
		driver.findElement(oipa_client_savebutton).click();
		wait(3);

		clientName = driver.findElement(oipa_client_clientName).getText();
		System.out.println(clientName);
		splitClientName = clientName.split(",");
		clientFirstName = splitClientName[0];
		clientLastName = splitClientName[1];

		takeScreenShot(driver, "ClientScreen");
		wait(4);

	}

	// ******************************************************************CLIENT SCREEN************************************************//
	/*
	 * Bug 22300295 - PROVISION TO SHADOW CLIENT TC-02:Verify that the existing
	 * Client is shadowed or not. TC-03:Verify that the user is able to reverse the
	 * shadow transaction.
	 * 
	 */
	@Test
	public void GC201_verifyExistingClientShadowed() throws ClassNotFoundException, Exception {
		
		wait(4);
		driver.findElement(oipa_client_admin).click();
		wait(4);
		selectItemInDropdown(driver, "Misc");
		wait(2);
		selectItemInDropdown(driver, "Company Activity");
		wait(4);
		driver.findElement(oipa_client_SelectCompanyActivity).click();
		wait(4);
		driver.findElement(oipa_client_SelectCompany).click();
		wait(2);
		selectItemInDropdown(driver, "Prototype");
		wait(4);
		driver.findElement(oipa_client_SelectPlan).click();
		wait(2);
		selectItemInDropdown(driver, "Client Plan");
		wait(4);

		driver.findElement(oipa_client_AddActivity).click();
		wait(4);
		driver.findElement(oipa_client_SearchActivity).click();
		wait(2);
		selectItemInDropdown(driver, "ClientShadow");
		wait(2);
		driver.findElement(oipa_client_ActivitySearchClient).click();
		wait(2);

		selectItemInDropdown(driver, clientFirstName);
		wait(2);
		driver.findElement(oipa_client_CompanyActivityOk).click();
		wait(4);
		driver.findElement(oipa_client_CompanyActivityProcess).click();
		wait(4);

		takeScreenShot(driver, "CLientShadowActivity");

		connectToDb();
		str1 = RunQuery("select clientguid,firstname,lastname,statuscode from asclient where firstname ='"+ clientFirstName + "' and lastname = '" + clientLastName + "' ");
		System.out.println(str1);

		driver.findElement(oipa_client_CompanyActivityReverse).click();
		wait(4);
		takeScreenShot(driver, "CLientShadowActivityReverse");

		connectToDb();
		str1 = RunQuery("select clientguid,firstname,lastname,statuscode from asclient where firstname ='"+ clientFirstName + "' and lastname = '" + clientLastName + "' ");
		System.out.println(str1);

	}

	// TC-07:Verify that the Summary screen on Client Screen
	@Test
	public void GC202_verifySummaryPane() throws ClassNotFoundException, SQLException, Exception {
		
		wait(4);
		GC200_createClient();
		wait(4);
		driver.findElement(oipa_client_summaryPane).click();
		wait(3);
		takeScreenShot(driver, "SummaryPane");
		wait(4);
		selectItemInDropdown(driver, "Address");
		wait(3);
		takeScreenShot(driver, "AddressSummaryPane");
		wait(4);
	}

	// TC-08:Verify the Client screen
	@Test
	public void GC203_verifyClientScreen() throws ClassNotFoundException, SQLException, Exception {

		wait(4);
		GC200_createClient();
		wait(4);
		GC202_verifySummaryPane();

	}

	// TC-15 To Verify the Date Picker for Date fields in Client Screen
	@Test
	public void GC204_verifyDatePicker() throws ClassNotFoundException, SQLException, Exception {

		wait(5);
		driver.findElement(By.xpath("//div[@id='oj-select-choice-Menu_Search']//a")).click();
		wait(3);
		selectItemInDropdown(driver, "Client");
		wait(3);
		driver.findElement(By.xpath("//input[@id='unifiedsearchinput']")).sendKeys(clientFirstName);
		wait(5);
		driver.findElement(By.xpath("//button[@id='unifiedSearchIcon']")).click();
		wait(4);
		//createClient();
		//wait(4);
		driver.findElement(oipa_client_datePicker).click();
		wait(2);
		takeScreenShot(driver, "DatePickerBeforeScrolling");
		wait(3);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		wait(10);
		takeScreenShot(driver, "DatePickerAfterScrolling");
	}

	// ********************************************** CLIENT ADDRESS ****************************************************//

	/*
	 * TC-01: Verify that the Client Address Screen.
	 * 
	 */
	@Test
	public void GC205_clientAddressScreen() throws ClassNotFoundException, SQLException, Exception {
		
		GC200_createClient();
		wait(5);
		driver.findElement(oipa_Client_ClientAddress).click();
		wait(4);
		driver.findElement(oipa_Client_AddAdress).click();
		wait(4);
		driver.findElement(oipa_Client_AddressType).click();
		wait(2);
		selectItemInDropdown(driver, "Home");
		wait(3);
		driver.findElement(oipa_Client_AddressLine1).sendKeys(xls.getCellData("GC_Client_2", "AddressLine1", 2));
		wait(3);
		driver.findElement(oipa_Client_AddressCity).sendKeys(xls.getCellData("GC_Client_2", "AddressCity", 2));
		wait(3);
		driver.findElement(oipa_Client_AddressPostalID).sendKeys(xls.getCellData("GC_Client_2", "AddressPostalID", 2));
		wait(3);
		driver.findElement(oipa_Client_AddressSave).click();
		wait(7);
		takeScreenShot(driver, "ClientAddressScreen");
		wait(4);

		/*
		 * TC-04: Add an address to an existing Client.
		 */

		/*driver.findElement(By.xpath("//div[@id='oj-select-choice-Menu_Search']//a")).click();
		wait(3);
		selectItemInDropdown(driver, "Client");
		wait(3);
		driver.findElement(By.xpath("//input[@id='unifiedsearchinput']")).sendKeys(clientFirstName);
		wait(5);
		driver.findElement(By.xpath("//button[@id='unifiedSearchIcon']")).click();*/
		wait(5);
		driver.findElement(oipa_Client_ClientAddress).click();
		wait(4);
		driver.findElement(oipa_Client_AddAdress).click();
		wait(4);
		driver.findElement(oipa_Client_AddressType).click();
		wait(4);
		selectItemInDropdown(driver, "Billing");
		wait(3);
		driver.findElement(oipa_Client_AddressLine1).sendKeys(xls.getCellData("GC_Client_2", "AddressLine1", 2));
		wait(3);
		driver.findElement(oipa_Client_AddressCity).sendKeys(xls.getCellData("GC_Client_2", "AddressCity", 2));
		wait(3);
		driver.findElement(oipa_Client_AddressPostalID).sendKeys(xls.getCellData("GC_Client_2", "AddressPostalID", 2));
		wait(3);
		driver.findElement(oipa_Client_AddressSave).click();
		wait(7);
		takeScreenShot(driver, "ExistingClientAddressScreen");
		wait(4);

	}

	// ********************************************** CLIENT WITHHOLDING ****************************************************//
	/*
	 * TC-04 To Verify 'Save' button on Withholding Screen
	 * 
	 */
	@Test
	public void GC206_clientWithholdingScreen() throws ClassNotFoundException, SQLException, Exception {
		GC200_createClient();
		wait(4);
				
		driver.findElement(oipa_Client_SelectWithholdingScreen).click();
		wait(4);
		driver.findElement(oipa_Client_WithholdingFederalPercent).clear();
		driver.findElement(oipa_Client_WithholdingFederalPercent).sendKeys(xls.getCellData("GC_Client_2", "FederalPercent", 2));
		wait(2);
		driver.findElement(oipa_Client_WithholdingFederalAmount).clear();
		driver.findElement(oipa_Client_WithholdingFederalAmount).sendKeys(xls.getCellData("GC_Client_2", "FederalAmount", 2));
		wait(2);
		driver.findElement(oipa_Client_WithholdingStatePercent).clear();
		driver.findElement(oipa_Client_WithholdingStatePercent).sendKeys(xls.getCellData("GC_Client_2", "StatePercent", 2));
		wait(2);
		driver.findElement(oipa_Client_WithholdingStateAmount).clear();
		driver.findElement(oipa_Client_WithholdingStateAmount).sendKeys(xls.getCellData("GC_Client_2", "StateAmount", 2));
		wait(2);
		driver.findElement(oipa_Client_WithholdingOptoutYes).click();
		wait(2);
		driver.findElement(oipa_Client_WithholdingStateFormRecievedNo).click();
		wait(2);
		driver.findElement(oipa_Client_WithholdingSave).click();
		wait(7);
		takeScreenShot(driver, "ClientWithholdingScreen");
		wait(4);
	}
	
	// ********************************************** CLIENT REQUIREMENTS ****************************************************//
		/*
		 * TC13-To Verify that Client Level Requirements can be added in OIPA
		 * 
		 */
		@Test
		public void GC207_addClientLevelRequirements() throws ClassNotFoundException, SQLException, Exception {
			
			 int rand_int = rand.nextInt(1000);
			 int rand_int1 = rand.nextInt(99);
			 
			//createClient();
			wait(4);
			driver.findElement(By.xpath("//div[@id='oj-select-choice-Menu_Search']//a")).click();
			wait(3);
			selectItemInDropdown(driver, "Client");
			wait(3);
			driver.findElement(By.xpath("//input[@id='unifiedsearchinput']")).sendKeys(clientFirstName);
			wait(5);
			driver.findElement(By.xpath("//button[@id='unifiedSearchIcon']")).click();
			wait(5);
							
			driver.findElement(oipa_Client_RequirementScreen).click();
			wait(4);
			driver.findElement(oipa_Client_AddRequirement).click();
			wait(3);
			driver.findElement(oipa_Client_RequirementSearch).click();
			wait(3);
			selectItemInDropdown(driver, "MIB Inquiry Client");
			wait(3);
			driver.findElement(oipa_Client_RequirementStatus).click();
			wait(3);
			selectItemInDropdown(driver, "Pending");
			wait(3);
			driver.findElement(oipa_Client_RequirementVendorName).sendKeys(xls.getCellData("GC_Client_2", "VendorName", 2) + rand_int);
			wait(3);
			driver.findElement(oipa_Client_RequirementPhone).sendKeys(xls.getCellData("GC_Client_2", "RequirementPhone", 2)+ rand_int1);
			wait(3);
			driver.findElement(oipa_Client_RequirementOkButton).click();
			wait(3);
					
		}
		
		/*
		 * TC24-To Verify that Client Level Requirement can be added through an Activity using "Add Requirement" attached rule
		 * 
		 */
		@Test
		public void GC208_addRequirementAttachedRule() throws ClassNotFoundException, SQLException, Exception {
			
			System.out.println("Add Requirement Attached Rule TC running");
			wait(4);
			GC200_createClient();
			wait(4);
			
			addActivity();
			wait(4);
			selectItemInDropdown(driver, "TestClientReq");
			wait(4);
			driver.findElement(oipa_Client_ClientRequirementName).sendKeys("MIB Inquiry Client");
			wait(4);
			driver.findElement(oipa_Client_ClientRequirementStatus).click();
			wait(4);
			selectItemInDropdown(driver, "Pending");
			wait(4);
			driver.findElement(oipa_Client_ClientRequirementAllowDuplicates).sendKeys("Yes");
			wait(4);
			processActivity();
			wait(4);
			takeScreenShot(driver, "Add requirement Through Activity");
			wait(4);
			driver.findElement(oipa_Client_RequirementScreen).click();
			wait(4);
			takeScreenShot(driver, "Added requirement Through Activity");
			wait(4);
			
		}
		
		/*
		 * TC31-User should be able to add ProcessRequirements BR to client level activity to trigger the processing of requirements
		 * 
		 */
		@Test
		public void GC209_processPendingRequirement() throws ClassNotFoundException, SQLException, Exception {
			
			wait(4);
			GC200_createClient();
			wait(4);
			GC207_addClientLevelRequirements();
			wait(4);
			
			addActivity();
			wait(4);
			selectItemInDropdown(driver, "ProcessPendingRequirements");
			wait(4);
			processActivity();
			wait(4);
			takeScreenShot(driver, "ProcessPendingRequirements Activity");
			wait(4);
			driver.findElement(oipa_Client_RequirementScreen).click();
			wait(4);
			takeScreenShot(driver, "Status changed After Running the ProcessPendingRequirements Activity");
			wait(4);
			
		}
		
		/*
		 * TC32-To verify spawning of Client requirement from a client level requirement
		 * 
		 */
		@Test
		public void GC210_spawnClientLevelReqUsingCopyBook() throws ClassNotFoundException, SQLException, Exception {
			wait(5);
			GC200_createClient();
			wait(5);
							
			driver.findElement(oipa_Client_RequirementScreen).click();
			wait(4);
			driver.findElement(oipa_Client_AddRequirement).click();
			wait(4);
			driver.findElement(oipa_Client_RequirementSearch).click();
			wait(4);
			selectItemInDropdown(driver, "Client Motor Vehicle Report");
			wait(4);
			driver.findElement(oipa_Client_ClientRequirementName).sendKeys("MIB Inquiry Client");
			wait(4);
			driver.findElement(oipa_Client_ClientRequirementStatus).click();
			wait(4);
			selectItemInDropdown(driver, "Pending");
			wait(4);
			driver.findElement(oipa_Client_ClientRequirementAllowDuplicates).sendKeys("Yes");
			wait(4);			
			driver.findElement(oipa_Client_RequirementOkButton).click();
			wait(10);
			takeScreenShot(driver, "spawnClientLevelReqUsingCopyBook");
			wait(4);
			
			driver.findElement(oipa_Client_SelectMIBInquiryClientAdded).click();
			wait(4);
			takeScreenShot(driver, "MIBInquiryClientRequirementAdded");
			
			
			
		}
		
		/*
		 * TC37-Verify if user is able to call copybook in AddRequirements at Requirement Level
		 * 
		 */
		@Test
		public void GC211_addRequirementCopyBook() throws ClassNotFoundException, SQLException, Exception {
			//createClient();
			driver.findElement(By.xpath("//div[@id='oj-select-choice-Menu_Search']//a")).click();
			wait(3);
			selectItemInDropdown(driver, "Client");
			wait(3);
			driver.findElement(By.xpath("//input[@id='unifiedsearchinput']")).sendKeys(clientFirstName);
			wait(5);
			driver.findElement(By.xpath("//button[@id='unifiedSearchIcon']")).click();
			wait(5);
							
			driver.findElement(oipa_Client_RequirementScreen).click();
			wait(4);
			driver.findElement(oipa_Client_AddRequirement).click();
			wait(4);
			driver.findElement(oipa_Client_RequirementSearch).click();
			wait(4);
			selectItemInDropdown(driver, "Client Motor Vehicle Report");
			wait(4);
			driver.findElement(oipa_Client_ClientRequirementName).sendKeys("MIB Inquiry Client");
			wait(4);
			driver.findElement(oipa_Client_ClientRequirementStatus).click();
			wait(4);
			selectItemInDropdown(driver, "Pending");
			wait(4);
			driver.findElement(oipa_Client_ClientRequirementAllowDuplicates).sendKeys("Yes");
			wait(4);			
			driver.findElement(oipa_Client_RequirementOkButton).click();
			wait(10);
			takeScreenShot(driver, "spawnClientLevelReqUsingCopyBook");
			wait(4);
			
			driver.findElement(oipa_Client_SelectMIBInquiryClientAdded).click();
			wait(4);
			takeScreenShot(driver, "MIBInquiryClientRequirementAdded");
			
			
			
		}
		
		/*
		 * TC42_GeneratePendingRequirements-BR
		 * 
		 */
		@Test
		public void GC212_generatePendingrequirement() throws ClassNotFoundException, SQLException, Exception {
			wait(5);
			GC200_createClient();
			wait(5);
							
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "GPRTrx");
			wait(4);
			processActivity();
			driver.findElement(oipa_Client_RequirementOutstanding).click();
			wait(4);
			
			takeScreenShot(driver, "GeneratePendingrequirement");
			wait(3);			
			
		}
		
		
		// ********************************************** CLIENT ACTIVITIES ****************************************************//
		
		public void addActivity() {
			driver.findElement(oipa_Client_ClientActivityScreen).click();
			wait(4);
			driver.findElement(oipa_Client_ClientAddActivity).click();
			wait(4);
			driver.findElement(oipa_Client_ClientSearchActivity).click();
			wait(5);
		}
		
		public void processActivity() {
			
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(3);				
			driver.findElement(oipa_Client_ActivityProcessButton).click();
			wait(3);
			
		}
		
		/*
		 * 04 .Validate whether the Redo of the activities that are lying above the activity is done, once the activity 
		 * is Recycled if the activity is of type "Client-Financial" which is Reversible-Reversing Type
		 * 
		 */
		@Test
		public void GC213_activityRedoReversibleReversing() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
			
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "ClientTransactionTimes1");
			wait(4);
			driver.findElement(oipa_Client_ActivityEffectiveDate).clear();
			wait(2);
			driver.findElement(oipa_Client_ActivityEffectiveDate).sendKeys("7/9/2001");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_FirstActivityProcess).click();
			wait(4);
			
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "ClientTransactionTimes2");
			wait(4);
			driver.findElement(oipa_Client_ActivityEffectiveDate).clear();
			wait(2);
			driver.findElement(oipa_Client_ActivityEffectiveDate).sendKeys("7/9/2001");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_SecondActivityProcess).click();
			wait(4);
			
			driver.findElement(oipa_Client_FirstActivityRecycle).click();
			wait(4);
			driver.findElement(oipa_Client_ActivityEffectiveDate).clear();
			wait(2);
			driver.findElement(oipa_Client_ActivityEffectiveDate).sendKeys("7/9/2002");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			
			driver.findElement(oipa_Client_FirstActivityPendingReversal).click();
			wait(4);
			driver.findElement(oipa_Client_SecondActivityProcess).click();
			wait(4);
			driver.findElement(oipa_Client_FirstActivityProcess).click();
			wait(4);
			
			takeScreenShot(driver, "activityRecycledReversibleReversing");
			wait(4);
			
		}
		
		/*
		 * 05. Validate whether the Undo of the activities that are lying above the activity is done, 
		 * once the activity is Reversed if the activity is of type "Client-Financial" which is Reversible-Reversing Type
		 * 
		 */		
		@Test
		public void GC214_activityUndoReversibleReversing() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
					
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "ClientTransactionTimes1");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_FirstActivityProcess).click();
			wait(4);
			
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "ClientTransactionTimes2");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(8);
			driver.findElement(oipa_Client_SecondActivityProcess).click();
			wait(4);
			
			driver.findElement(oipa_Client_FirstActivityReverse).click();
			wait(4);
			driver.findElement(oipa_Client_FirstActivityPendingReversal).click();
			wait(4);
			driver.findElement(oipa_Client_SecondActivityProcess).click();
			wait(4);
			
			takeScreenShot(driver, "activityUndoReversibleReversing");
			wait(4);
			
		}
		
		/*
		 * 08 .Validate whether the spawned Client level Activity is getting Back to Prior state automatically upon Recycle, Redo processing of its Source activity
		 * 
		 */	
		@Test(priority = 15)
		public void GC215_SpawnedActivityToPriorStateOnRecycleRedo() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
				
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "SpawnTransaction");
			wait(4);
			driver.findElement(oipa_Client_ActivityEffectiveDate).clear();
			wait(2);
			driver.findElement(oipa_Client_ActivityEffectiveDate).sendKeys("7/9/2001");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_SpawnTransactionProcess).click();
			wait(4);
			
			driver.findElement(oipa_Client_SpawnTransaction1Process).click();
			wait(4);
			driver.findElement(oipa_Client_SpawnTransaction2Process).click();
			wait(4);
			
			driver.findElement(oipa_Client_SpawnTransactionRecycle).click();
			wait(4);
			driver.findElement(oipa_Client_ActivityEffectiveDate).clear();
			wait(2);
			driver.findElement(oipa_Client_ActivityEffectiveDate).sendKeys("7/9/2002");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_SpawnTransactionPendingReversal).click();
			wait(4);
			driver.findElement(oipa_Client_SpawnTransactionProcess).click();
			wait(4);
			
			driver.findElement(oipa_Client_SpawnTransaction1Process).click();
			wait(4);
			driver.findElement(oipa_Client_SpawnTransaction2Process).click();
			wait(4);
			
			takeScreenShot(driver, "SpawnedActivityToPriorStateOnRecycleRedo");
			wait(4);
			
			
			
		}
		
		/*
		 * 09. Validate whether the spawned Client level Activity is getting Back to Prior state automatically upon Reversing, Undo processing of its Source activity
		 * 
		 */
		@Test
		public void GC216_SpawnedActivityToPriorStateOnReversingUndo() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
			
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "SpawnTransaction");
			wait(4);
			driver.findElement(oipa_Client_ActivityEffectiveDate).clear();
			wait(2);
			driver.findElement(oipa_Client_ActivityEffectiveDate).sendKeys("7/9/2001");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_SpawnTransactionProcess).click();
			wait(4);
			
			driver.findElement(oipa_Client_SpawnTransaction1Process).click();
			wait(4);
			driver.findElement(oipa_Client_SpawnTransaction2Process).click();
			wait(4);
			
			driver.findElement(oipa_Client_SpawnTransactionReverse).click();
			wait(4);
			driver.findElement(oipa_Client_SpawnTransactionPendingReversal).click();
			wait(4);
			
			driver.findElement(oipa_Client_DisplayActivity).click();
			wait(4);
			selectItemInDropdown(driver, "Shadows");
			wait(4);
			
			driver.findElement(oipa_Client_DisplayActivity).click();
			wait(4);
			selectItemInDropdown(driver, "Reversals");
			wait(4);
			driver.findElement(oipa_Client_RefreshActivity).click();
			wait(4);
			
			takeScreenShot(driver, "SpawnedActivityToPriorStateOnReversingUndo");
			wait(4);
			
			
			
		}
		
		/*
		 *11. Validate whether the spawned Client level Activity is not getting Back to Prior state automatically upon Reverse,
		 * Undo processing of its Source activity if the activity is Non-Reversible type
		 * 
		 */
		@Test
		public void GC217_SpawnedActivityNotToPriorStateOnNonReversibleType() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
			
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "TransactionSpawn");
			wait(4);
			driver.findElement(oipa_Client_ActivityEffectiveDate).clear();
			wait(2);
			driver.findElement(oipa_Client_ActivityEffectiveDate).sendKeys("7/9/2001");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_TransactionSpawnProcess).click();
			wait(4);
			
			driver.findElement(oipa_Client_TransactionSpawnNRNRProcess).click();
			wait(4);
			
			
			driver.findElement(oipa_Client_TransactionSpawnReverse).click();
			wait(4);
			driver.findElement(oipa_Client_TransactionSpawnPendingReversal).click();
			wait(4);
			
			takeScreenShot(driver, "SpawnedActivityNotToPriorStateOnNonReversibleType");
			wait(4);
			
			
			
		}
		
		/*
		 *12 .Validate whether the Reversal of activities spawned from a Client Level to Policy level is getting affected by Reversing the Source Transaction
		 * 
		 */
		@Test
		public void GC218_SpawnedActivityFromClientToPolicyLevel() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
					
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "SL_Spawn_PolicyClient");
			wait(4);
			driver.findElement(oipa_Client_SLTransactionClientPolicyTest).sendKeys("Test");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_SL_Spawn_PolicyClientProcess).click();
			
			//Go to PolicyActivity and Check
			driver.findElement(oipa_Client_SearchMenu).click();
			wait(3);
			selectItemInDropdown(driver, "Policy");
			wait(3);
			driver.findElement(oipa_Client_EnterToSearch).sendKeys("FPP_Test_003");
			wait(5);
			driver.findElement(oipa_Client_UnifiedSearchButton).click();
			wait(10);
			driver.findElement(oipa_Client_PolicyActivity).click();
			wait(4);
			driver.findElement(oipa_Client_PolicyActivityDetails).click();
			wait(4);
			takeScreenShot(driver, "SpawnedActivityFromClientToPolicyLevel1");
			wait(4);
			
			driver.findElement(oipa_Client_SearchMenu).click();
			wait(3);
			selectItemInDropdown(driver, "Client");
			wait(3);
			driver.findElement(oipa_Client_EnterToSearch).sendKeys(clientFirstName);
			wait(5);
			driver.findElement(oipa_Client_UnifiedSearchButton).click();
			wait(5);
			driver.findElement(oipa_Client_ClientActivityScreen).click();
			wait(4);
			
			driver.findElement(oipa_Client_SLTransactionClientPolicyReverse).click();
			wait(4);
			driver.findElement(oipa_Client_SLTransactionClientPolicyPendingReversal).click();
			wait(4);
			
			//Go to PolicyActivity and Check
			driver.findElement(oipa_Client_SearchMenu).click();
			wait(3);
			selectItemInDropdown(driver, "Policy");
			wait(3);
			driver.findElement(oipa_Client_EnterToSearch).sendKeys("FPP_Test_003");
			wait(5);
			driver.findElement(oipa_Client_UnifiedSearchButton).click();
			wait(5);
			driver.findElement(oipa_Client_PolicyActivity).click();
			wait(4);
			takeScreenShot(driver, "SpawnedActivityFromClientToPolicyLevel2");
			wait(4);
			
			
		}
		/* *******************************************Bug 23510200-Client Activities Phase I-JET*********************************************/

		/*
		 *TC 4:Verify the activity is added from Activities page.
		 * 
		 */
		@Test
		public void GC219_addClientActivity() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
					
			addActivity();
			wait(4);
			selectItemInDropdown(driver, "ClientTransactionTimes1");
			wait(4);
			processActivity();
			wait(4);
			
			takeScreenShot(driver, "addClientActivity");
			wait(4);
			
		}
		
		/*
		 * TC 5:Verify the Activities are added through "Add Activity" link
		 * 
		 */
		@Test
		public void GC220_addClientActivityUsingAddActivityLink() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
					
			addActivity();
			wait(4);
			selectItemInDropdown(driver, "ClientTransactionTimes1");
			wait(4);
			processActivity();
			wait(4);
			
			takeScreenShot(driver, "addClientActivity");
			wait(4);
			
		}
		
		/*
		 *TC 8: Verify transaction cosmetics business rule is working correctly
		 * 
		 */
		@Test
		public void GC221_ClientTransactionCosmeticsBusinessRule() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
					
			addActivity();
			wait(4);
			selectItemInDropdown(driver, "TestTranx");
			wait(4);
			driver.findElement(oipa_Client_ActivityTestTranxAmount).sendKeys("1000");
			wait(4);
			processActivity();
			wait(4);
			
			takeScreenShot(driver, "ClientTransactionCosmeticsBusinessRule");
			wait(4);
			
		}
		
		/*
		 * TC 11:Verify that activities are getting added successfully as per the processing order and effective date
		 * 
		 */
		@Test
		public void GC222_ActivityProcessingOrderCheck() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
					
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "ClientTransactionTimes1");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_FirstActivityProcess).click();
			wait(4);
			
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "ClientTransactionTimes2");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(8);
			driver.findElement(oipa_Client_SecondActivityProcess).click();
			wait(4);
			
			takeScreenShot(driver, "ActivityProcessingOrderCheck");
			wait(4);
			
		}
		
		/*
		 * TC 12:Verify that activities are getting shadowed successfully
		 * 
		 */
		@Test
		public void GC223_ActivitiesGettingShadowed() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
					
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "ClientTransactionTimes1");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_FirstActivityProcess).click();
			wait(4);
			
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "ClientTransactionTimes2");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(8);
			driver.findElement(oipa_Client_SecondActivityProcess).click();
			wait(4);
			
			driver.findElement(oipa_Client_FirstActivityReverse).click();
			wait(4);
			driver.findElement(oipa_Client_FirstActivityPendingReversal).click();
			wait(4);
			
			driver.findElement(oipa_Client_DisplayActivity).click();
			wait(4);
			selectItemInDropdown(driver, "Shadows");
			wait(4);
			driver.findElement(oipa_Client_RefreshActivity).click();
			wait(4);
			
			
			takeScreenShot(driver, "ActivitiesGettingShadowed");
			wait(4);
			
		}
		
		/*
		 * TC 13: Verify that activity reversal is working correctly
		 * 
		 */
		@Test
		public void GC224_ActivityReversalCheck() throws ClassNotFoundException, SQLException, Exception {
			GC200_createClient();
			wait(4);
			
			/*driver.findElement(oipa_Client_SearchMenu).click();
			wait(3);
			selectItemInDropdown(driver, "Client");
			wait(3);
			driver.findElement(oipa_Client_EnterToSearch).sendKeys("OIPAClient1162");
			wait(5);
			driver.findElement(oipa_Client_UnifiedSearchButton).click();
			wait(5);
			driver.findElement(oipa_Client_ClientActivityScreen).click();
			wait(4);*/
					
			addActivity();
			wait(2);
			selectItemInDropdown(driver, "ClientTransactionTimes1");
			wait(4);
			driver.findElement(oipa_Client_ActivityEffectiveDate).clear();
			wait(3);
			driver.findElement(oipa_Client_ActivityEffectiveDate).sendKeys("7/9/2001");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_FirstActivityProcess).click();
			wait(4);
			
			driver.findElement(oipa_Client_FirstActivityRecycle).click();
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			driver.findElement(oipa_Client_DetailsFirstActivityWithPendingStatus).click();
			wait(4);
			driver.findElement(oipa_Client_ActivityEffectiveDate).clear();
			wait(3);
			driver.findElement(oipa_Client_ActivityEffectiveDate).sendKeys("7/9/2002");
			wait(4);
			driver.findElement(oipa_Client_ActivityOkButton).click();
			wait(4);
			
			takeScreenShot(driver, "ActivityReversalCheck");
			wait(4);
			
		}
		
		
			
		

	// ******************************************************************************************************************//

	// Closing Browser After test
	@AfterClass
	public void Logout() {
		closeBrowser(driver);
	}
}
