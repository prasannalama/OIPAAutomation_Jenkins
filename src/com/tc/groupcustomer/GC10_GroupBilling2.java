package com.tc.groupcustomer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.sql.Connection;
import java.sql.SQLException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Library.OIPA_Library;

import xls.ShineXlsReader; 

/* Pre-requisite : MaintainBill ,GenerateBill and GenerateBillDetail transactions must be present at Group Customer Level for these TCs to be executed Successfully
 * Config can be found in TC for GenerateBill and Generate Bill detail : Oracle Insurance Policy Administration » Regression Tests » Regression Library » OIPA End to End Testing » Special Regression » DI Regression » Billing »08. Verify "Reconciliation" transaction is triggered at Customer entity level through Data Intake
 * Config can be found in TC for MaintainBill : Oracle Insurance Policy Administration » Functional » Group Bill » 26032401 - Ability to Add New Bill Detail Record to Existing Bill » Remove a billdetail from existing bill.
 * BillGUID in MaintainBill attachRule has to be hardcoded, hence in config provided in utilities replace BillGUID with "ReplaceThis" and Make sure that initially the Rule is set to remove BillDetails
 */

public class GC10_GroupBilling2 extends OIPA_Library {
	//Tester : Abhishek	
	//Functionality : Group Customer Quote
	Random rand = new Random();
	int randomNum = ThreadLocalRandom.current().nextInt(100,1000);
	String CustNumber,CustName,CustomerClientGUID,BillRefNum;
	String BillDetailGUID1,BillDetailGUID2,BillDetailGUID3,BillDetailGUID4,BillGUID;
	ArrayList<String> QueryResult;
	String Query;
	Connection con;
	public static WebDriver driver;
	public static ShineXlsReader xls=new ShineXlsReader(".\\src\\com\\Utilities\\TestData.xlsx");	 
    static void updateConfig(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);
         
        String oldConfig = "";
         
        BufferedReader reader = null;
         
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
             
            while (line != null) 
            {
            	oldConfig = oldConfig + line + System.lineSeparator();
                 
                line = reader.readLine();
            }
             
            //Replacing oldString with newString in the oldContent
             
            String newConfig = oldConfig.replaceAll(oldString, newString);
             
            //Rewriting the input text file with newContent
             
            writer = new FileWriter(fileToBeModified);
             
            writer.write(newConfig);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                 
                reader.close();
                 
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
	@BeforeClass
	public void StartTC() throws Throwable{
		System.out.println("***************GC10 GroupBilling2******************************");

		openBrowser();
		driver = login(driver);
		connectToDb();
	}
	
	@Test
	public void GB01_TCPreReq() throws IOException, SQLException {
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
		String GCName = xls.getCellData("GroupCustomerQuote", "customerName", 2).concat(s);
		String GCNum = xls.getCellData("GroupCustomerQuote", "customerNumber", 2).concat(s);
		String taxId = String.valueOf(randomNum);
		taxId = taxId.concat(taxId.concat(taxId));
		CustName = GCName;
		driver.findElement(oipa_customer_customerName).sendKeys(GCName);
		wait(3);
		driver.findElement(oipa_customer_customerName).clear();
		driver.findElement(oipa_customer_customerName).sendKeys(GCName);
		wait(2);
		wait(3);
		driver.findElement(oipa_customer_taxID).clear();
		driver.findElement(oipa_customer_taxID).sendKeys(taxId);
		wait(2);
		driver.findElement(oipa_customer_taxID).clear();
		driver.findElement(oipa_customer_taxID).sendKeys(taxId);
		wait(3);
		driver.findElement(oipa_customer_customerLegalName).sendKeys(GCName);
		wait(3);
		driver.findElement(oipa_customer_customerLegalName).clear();
		driver.findElement(oipa_customer_customerLegalName).sendKeys(GCName);
		wait(3);
		if(driver.findElement(oipa_customer_customerNumber).isEnabled()) {
			driver.findElement(oipa_customer_customerNumber).clear();
			driver.findElement(oipa_customer_customerNumber).sendKeys(GCNum);
			wait(2);
		}
		wait(3);
		driver.findElement(oipa_customer_saveButton).click();
		wait(5);
		if(exists(newCust_summary_newCustNumber)) {
			CustNumber = driver.findElement(newCust_summary_newCustNumber).getText();
			System.out.println("Customer number is "+CustNumber);
		}
		if(exists(newCust_summary_newCustNumberAuto)) {
			CustNumber = driver.findElement(newCust_summary_newCustNumberAuto).getText();
			System.out.println("Customer number is "+CustNumber);	
		}
		takeScreenShot(driver,"CustomerCreated");
		Query = "Select * from asclient where companyname = '"+GCName+"'";
		QueryResult = RunQuery(Query);
		CustomerClientGUID = QueryResult.get(0);
	}
	/*Test 14275: Billing tab populated for GenerateBillDetails and Generate bill at Client level*/
	@Test
	public void GB02_TC001() throws IOException {
		
		/*Generate Bill Details - 001 - First activity*/
		
		driver.findElement(newCust_Activities_NavLink).click();
		wait(5);
		driver.findElement(Cust_AddActivity_AddActivityButton).click();
		wait(5);
		driver.findElement(Cust_AddActivity_ActivityDropDown).click();
		wait(2);
		selectItemInDropdown(driver, "GenerateBillDetail");
		wait(5);
		driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).clear();
		wait(3);
		driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBillDetails_EffectiveDate",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillDueDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBillDetails_BillDueDate",2));
		wait(2);
		int sum = 0;
		int x = rand.nextInt(999);
		sum += x;
		String s = String.valueOf(x);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillDueAmount).sendKeys(s);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillGroupType).click();
		selectItemInDropdown(driver,xls.getCellData("CustomerBilling","GenerateBillDetails_BillGroupType",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_GroupCustomerGUID).sendKeys(CustomerClientGUID);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillEntityType).click();
		selectItemInDropdown(driver,xls.getCellData("CustomerBilling","GenerateBillDetails_BillEntityType",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_GroupCust).sendKeys(CustomerClientGUID);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_OkButton).click();
		wait(5);
		driver.findElement(Cust_Activity_GenerateBillDetails_Process).click();
		wait(8);
		driver.findElement(Cust_Activity_expander001).click();
		wait(5);
		Assert.assertTrue(exists(Cust_Activity_BillingTab), "Billing tab is not present for this activity.");
		driver.findElement(Cust_Activity_BillingTab).click();
		HighlightElement(driver,Cust_Activity_BillingTab);
		takeScreenShot(driver,"Billing Tab is present");
		UnhighlightElement(driver,Cust_Activity_BillingTab);
		if(driver.findElement(Cust_Activity_BillingDetails_Expander).getAttribute("aria-expanded") == "false") {
			driver.findElement(Cust_Activity_BillingDetails_Expander).click();
		Assert.assertTrue(driver.findElement(Cust_Activity_BillingDetails_Expander).getAttribute("aria-expanded") == "true", "Activity is not getting expanded.");
		}
		
		/*Validation of billing section for Generate Bill Detail transaction*/
		
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_Amount).getText(),"Amount:");
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_AmountValue).getText(), s+".00");
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_RDT).getText(), "Receivable Due Type:");
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_RDTValue).getText(), "Premium");
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_DueDate).getText(), "Due Date:");
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_DueDateValue).getText(), xls.getCellData("CustomerBilling","GenerateBillDetails_BillDueDate",2));
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_EntityType).getText(),"Entity Type:");
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_EntityTypeValue).getText(), "GROUPCUST");
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_Entity).getText(), "Entity:");
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_EntityValue).getText(), CustName);
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_Status).getText(), "Status:");
		Assert.assertEquals(driver.findElement(Cust_Activity_BillingDetails_StatusValue).getText(), "PENDING");
		NavigateToElement(driver,Cust_Activity_BillingDetails_AllData);
		HighlightElement(driver,Cust_Activity_BillingDetails_AllData);
		takeScreenShot(driver,"GenerateBillDetail_BillingTab");
		UnhighlightElement(driver,Cust_Activity_BillingDetails_AllData);
		
		/*Generate Bill Details - 002 - Second activity*/
		
		driver.findElement(Cust_AddActivity_AddActivityButton).click();
		wait(5);
		driver.findElement(Cust_AddActivity_ActivityDropDown).click();
		wait(2);
		selectItemInDropdown(driver, "GenerateBillDetail");
		wait(5);
		driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).clear();
		wait(3);
		driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBillDetails_EffectiveDate",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillDueDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBillDetails_BillDueDate",2));
		wait(2);
		x = rand.nextInt(999);
		sum += x;
		String s1 = String.valueOf(x);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillDueAmount).sendKeys(s1);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillGroupType).click();
		selectItemInDropdown(driver,xls.getCellData("CustomerBilling","GenerateBillDetails_BillGroupType",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_GroupCustomerGUID).sendKeys(CustomerClientGUID);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillEntityType).click();
		selectItemInDropdown(driver,xls.getCellData("CustomerBilling","GenerateBillDetails_BillEntityType",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_GroupCust).sendKeys(CustomerClientGUID);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_OkButton).click();
		wait(5);
		driver.findElement(Cust_Activity_GenerateBillDetails_Process).click();
		wait(8);
		
		/*Generate Bill Details - 003 - Third activity*/
		
		driver.findElement(Cust_AddActivity_AddActivityButton).click();
		wait(5);
		driver.findElement(Cust_AddActivity_ActivityDropDown).click();
		wait(2);
		selectItemInDropdown(driver, "GenerateBillDetail");
		wait(5);
		driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).clear();
		wait(3);
		driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBillDetails_EffectiveDate",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillDueDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBillDetails_BillDueDate",2));
		wait(2);
		x = rand.nextInt(999);
		sum += x;
		String s2 = String.valueOf(x);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillDueAmount).sendKeys(s2);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillGroupType).click();
		selectItemInDropdown(driver,xls.getCellData("CustomerBilling","GenerateBillDetails_BillGroupType",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_GroupCustomerGUID).sendKeys(CustomerClientGUID);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillEntityType).click();
		selectItemInDropdown(driver,xls.getCellData("CustomerBilling","GenerateBillDetails_BillEntityType",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_GroupCust).sendKeys(CustomerClientGUID);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_OkButton).click();
		wait(5);
		driver.findElement(Cust_Activity_GenerateBillDetails_Process).click();
		wait(8);
		
		/*Generate Bill Details - 004 - Fourth activity*/
		
		driver.findElement(Cust_AddActivity_AddActivityButton).click();
		wait(5);
		driver.findElement(Cust_AddActivity_ActivityDropDown).click();
		wait(2);
		selectItemInDropdown(driver, "GenerateBillDetail");
		wait(5);
		driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).clear();
		wait(3);
		driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBillDetails_EffectiveDate",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillDueDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBillDetails_BillDueDate",2));
		wait(2);
		x = rand.nextInt(999);
		sum += x;
		String s3 = String.valueOf(x);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillDueAmount).sendKeys(s3);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillGroupType).click();
		selectItemInDropdown(driver,xls.getCellData("CustomerBilling","GenerateBillDetails_BillGroupType",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_GroupCustomerGUID).sendKeys(CustomerClientGUID);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_BillEntityType).click();
		selectItemInDropdown(driver,xls.getCellData("CustomerBilling","GenerateBillDetails_BillEntityType",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_GroupCust).sendKeys(CustomerClientGUID);
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_OkButton).click();
		wait(5);
		driver.findElement(Cust_Activity_GenerateBillDetails_Process).click();
		wait(8);
		
		/* Generate Bill */
		
		driver.findElement(Cust_AddActivity_AddActivityButton).click();
		wait(5);
		driver.findElement(Cust_AddActivity_ActivityDropDown).click();
		wait(2);
		selectItemInDropdown(driver, "GenerateBill");
		wait(5);
		driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).clear();
		wait(3);
		driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBill_EffectiveDate",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBill_BillStartDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBill_BillStartDate",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBill_BillEndDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBill_BillEndDate",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBill_BillOwnerType).click();
		wait(2);
		selectItemInDropdown(driver, "Group Customer");
		wait(2);
		driver.findElement(Cust_Activity_GenerateBill_MinThresholdAmount).clear();
		wait(2);
		driver.findElement(Cust_Activity_GenerateBill_MinThresholdAmount).sendKeys(xls.getCellData("CustomerBilling","GenerateBill_MinThresholdAmount",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBill_MaxThresholdAmount).clear();
		wait(2);
		driver.findElement(Cust_Activity_GenerateBill_MaxThresholdAmount).sendKeys(xls.getCellData("CustomerBilling","GenerateBill_MaxThresholdAmount",2));
		wait(2);
		driver.findElement(Cust_Activity_GenerateBillDetails_OkButton).click();
		wait(8);
		driver.findElement(Cust_Activity_GenerateBillDetails_Process).click();
		wait(8);
		driver.findElement(Cust_Activity_expander001).click();
		wait(5);
		Assert.assertTrue(exists(Cust_Activity_BillingTab), "Billing tab is not present for this activity.");
		driver.findElement(Cust_Activity_BillingTab).click();
		HighlightElement(driver,Cust_Activity_BillingTab);
		takeScreenShot(driver,"Billing Tab is present");
		UnhighlightElement(driver,Cust_Activity_BillingTab);
		if(driver.findElement(Cust_Activity_Billing_Expander).getAttribute("aria-expanded") == "false") {
			driver.findElement(Cust_Activity_Billing_Expander).click();
		Assert.assertTrue(driver.findElement(Cust_Activity_Billing_Expander).getAttribute("aria-expanded") == "true", "Activity is not getting expanded.");
		}
		
		/* Generate Bill Billing Section validations*/
		
		Assert.assertEquals(driver.findElement(Cust_Activity_Billing_BillRefId).getText(),"Bill Reference ID:");
		BillRefNum = driver.findElement(Cust_Activity_Billing_BillRefIdValue).getText();
		Assert.assertEquals(driver.findElement(Cust_Activity_Billing_OwnerType).getText(), "Owner Type:");
		Assert.assertEquals(driver.findElement(Cust_Activity_Billing_OwnerTypeValue).getText(), "GROUPCUST");
		Assert.assertEquals(driver.findElement(Cust_Activity_Billing_BillOwner).getText(), "Bill Owner:");
		Assert.assertEquals(driver.findElement(Cust_Activity_Billing_BillOwnerValue).getText(), CustName);
		Assert.assertEquals(driver.findElement(Cust_Activity_Billing_Records).getText(),"Records:");
		Assert.assertEquals(driver.findElement(Cust_Activity_Billing_RecordsValue).getText(), "4");
		Assert.assertEquals(driver.findElement(Cust_Activity_Billing_Amount).getText(), "Amount:");
		int i = Integer.parseInt(driver.findElement(Cust_Activity_Billing_AmountValue).getText().replaceAll(",","").replaceAll(".00", ""));
		Assert.assertEquals(i,sum);
		Assert.assertEquals(driver.findElement(Cust_Activity_Billing_Reconciled).getText(), "Reconciled:");
		Assert.assertEquals(driver.findElement(Cust_Activity_Billing_ReconciledValue).getText(), "0.00");
		NavigateToElement(driver,Cust_Activity_Billing_AllData);
		HighlightElement(driver,Cust_Activity_Billing_AllData);
		takeScreenShot(driver,"GenerateBill_BillingTab");
		UnhighlightElement(driver,Cust_Activity_Billing_AllData);	
		
		/* Generate Bill Billing Detail Section validations*/
		
		for (int j=1 ; j<5 ; j++) {
			
			By Cust_Activity_GB_BillingDetails_Amount = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[1]//div[1]");
			By Cust_Activity_GB_BillingDetails_AmountValue = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[1]//div[2]");
			By Cust_Activity_GB_BillingDetails_RDT = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[3]//div[1]");
			By Cust_Activity_GB_BillingDetails_RDTValue = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[3]//div[2]");
			By Cust_Activity_GB_BillingDetails_DueDate = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[5]//div[1]");
			By Cust_Activity_GB_BillingDetails_DueDateValue = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[5]//div[2]");
			By Cust_Activity_GB_BillingDetails_EntityType = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[7]//div[1]");
			By Cust_Activity_GB_BillingDetails_EntityTypeValue = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[7]//div[2]");
			By Cust_Activity_GB_BillingDetails_Entity = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[9]//div[1]");
			By Cust_Activity_GB_BillingDetails_EntityValue = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[9]//div[2]");
			By Cust_Activity_GB_BillingDetails_Status = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[11]//div[1]");
			By Cust_Activity_GB_BillingDetails_StatusValue = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']//div[11]//div[2]");
			By Cust_Activity_GB_BillingDetails_AllData = By.xpath("//div[@id='billingDetail']["+j+"]//div[@id='billingDetailHeading']");
			
			/*Validation of billing section for Generate Bill Detail transaction*/
			
			Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_Amount).getText(),"Amount:");
			Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_RDT).getText(), "Receivable Due Type:");
			Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_RDTValue).getText(), "Premium");
			Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_DueDate).getText(), "Due Date:");
			Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_DueDateValue).getText(), xls.getCellData("CustomerBilling","GenerateBillDetails_BillDueDate",2));
			Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_EntityType).getText(),"Entity Type:");
			Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_EntityTypeValue).getText(), "GROUPCUST");
			Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_Entity).getText(), "Entity:");
			Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_EntityValue).getText(), CustName);
			Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_Status).getText(), "Status:");
			Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_StatusValue).getText(), "BILLED");
			switch (j) {
			case 1:
				Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_AmountValue).getText(), s+".00");
				break;
			case 2:
				Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_AmountValue).getText(), s1+".00");
				break;
			case 3:
				Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_AmountValue).getText(), s2+".00");
				break;
			case 4:
				Assert.assertEquals(driver.findElement(Cust_Activity_GB_BillingDetails_AmountValue).getText(), s3+".00");
				break;
			}
			NavigateToElement(driver,Cust_Activity_GB_BillingDetails_AllData);
			HighlightElement(driver,Cust_Activity_GB_BillingDetails_AllData);
			takeScreenShot(driver,"GenerateBill_BillDetail_BillingTab"+j);
			UnhighlightElement(driver,Cust_Activity_GB_BillingDetails_AllData);
		}
			
		}
	
	/*Remove Single BillDetail from Bill*/	
		@Test
		public void GB03_TC003() throws IOException, SQLException {
			/*Get BillDetail GUIDs*/
			wait(2);
			Query = "select BillDetailGuid from AsBillDetail where BillEntityGUID='"+CustomerClientGUID+"'";
			QueryResult = RunQuery(Query);
			BillDetailGUID1 = QueryResult.get(0);
			BillDetailGUID2 = QueryResult.get(1);
			BillDetailGUID3 = QueryResult.get(2);
			BillDetailGUID4 = QueryResult.get(3);
			
			/*Get Bill GUID*/
			Query = "select Billguid from AsBill where BillReferenceId='"+BillRefNum+"'";
			QueryResult = RunQuery(Query);
			BillGUID = QueryResult.get(0);
			
			/*Update MaintainBill Transaction*/
			Query = "update astransaction set xmldata =? where transactionname = 'MaintainBill'";
			db_updateClob(".\\src\\com\\Utilities\\MaintainBillTrxn.txt", Query);
			db_refresh();
			
			/*Update Maintain Bill attach rule*/
			Query = "select companyguid from ascompany where companyname = 'Prototype'";
			QueryResult = RunQuery(Query);
			String companyGUID = QueryResult.get(0);
			
			Query = "select planguid from asplan where planname like 'Customer Plan' and companyguid='"+companyGUID+"'";
			QueryResult = RunQuery(Query);
			String customerPlanGUID = QueryResult.get(0);
			
			Query = "select transactionguid from astransaction where transactionname = 'MaintainBill' and PlanGuid = '"+customerPlanGUID+"'";
			QueryResult = RunQuery(Query);
			String transactionGUID = QueryResult.get(0);
			
			updateConfig(".\\src\\com\\Utilities\\MaintainBillRule.txt","ReplaceThis",BillGUID);
			//RemoveFromBill
			Query = "update asbusinessrules set xmldata =? where rulename = 'MaintainBill' and transactionguid ='"+transactionGUID+"'";
			db_updateClob(".\\src\\com\\Utilities\\MaintainBillRule.txt", Query);
			db_refresh();
			
			driver.findElement(OIPA_UserMenu_Button).click();
			wait(2);
			selectItemInDropdown(driver,"Sign Out");
			driver = login(driver);
			wait(5);
			driver.findElement(OIPA_Home_AdvancedSerach_Link).click();
			wait(10);
			driver.findElement(OIPA_AdvancedSearch_CustomerSearch).click();
			wait(5);
			driver.findElement(OIPA_AdvancedSearch_CustomerType).click();
			wait(2);
			selectItemInDropdown(driver,"Group Customer");
			wait(3);
			if(driver.findElement(OIPA_AdvancedSerach_ExpandSearchFields).getAttribute("aria-expanded").equalsIgnoreCase("false")) {
				driver.findElement(OIPA_AdvancedSerach_ExpandSearchFields).click();
			}
			driver.findElement(OIPA_AdvancedSearch_GroupCustomerNumber).sendKeys(CustNumber);
			driver.findElement(OIPA_AdvancedSearch_SearchButton).click();
			wait(10);
			driver.findElement(newCust_Activities_NavLink).click();
			wait(5);
			
			/*Add and Process Maintain Bill*/
			driver.findElement(Cust_AddActivity_AddActivityButton).click();
			wait(5);
			driver.findElement(Cust_AddActivity_ActivityDropDown).click();
			wait(2);
			selectItemInDropdown(driver, "MaintainBill");
			wait(5);
			driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).clear();
			wait(3);
			driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBill_EffectiveDate",2));
			wait(2);
			driver.findElement(Cust_Activity_MaintainBill_BillDetailGUID01).sendKeys(BillDetailGUID1);
			wait(2);
			driver.findElement(Cust_Activity_MaintainBill_BillGUID).sendKeys(BillGUID);
			wait(2);
			driver.findElement(Cust_Activity_GenerateBillDetails_OkButton).click();
			wait(8);
			driver.findElement(Cust_Activity_GenerateBillDetails_Process).click();
			wait(8);
			
			/*Check Bill Detail Removal*/
			
			int n = driver.findElements(By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']//div[@class='oj-rowexpander-icon-spacer']//a")).size();
			n += 1;
			int j=0;
			for (j=1 ; j<n ; j++) {
				By GenerateBillExpander = By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']["+j+"]//div[@class='oj-rowexpander-icon-spacer']//a");
				By  GenerateBillFinder = By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']["+j+"]//td[3]//span[contains(text(),'GenerateBill')]");
				if(exists(GenerateBillFinder)) {
					driver.findElement(GenerateBillExpander).click();
					break;
				}		
			}
			wait(5);
			driver.findElement(Cust_Activity_BillingTab).click();
			wait(5);
			Assert.assertEquals(driver.findElement(Cust_Activity_Billing_RecordsValue).getText(), "3");

			NavigateToElement(driver,Cust_Activity_Billing_RecordsValue);
			HighlightElement(driver,Cust_Activity_Billing_RecordsValue);
			takeScreenShot(driver,"GenerateBill_SingleBillRemoved");
			UnhighlightElement(driver,Cust_Activity_Billing_RecordsValue);
		}
		/*Remove Multiple BillDetail from Bill*/	
		@Test
		public void GB04_TC004() throws IOException, SQLException {
			wait(5);
			driver.findElement(Cust_AddActivity_AddActivityButton).click();
			wait(5);
			driver.findElement(Cust_AddActivity_ActivityDropDown).click();
			wait(2);
			selectItemInDropdown(driver, "MaintainBill");
			wait(5);
			driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).clear();
			wait(3);
			driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBill_EffectiveDate",2));
			wait(2);
			driver.findElement(Cust_Activity_MaintainBill_BillDetailGUID01).sendKeys(BillDetailGUID2);
			wait(2);
			driver.findElement(Cust_Activity_MaintainBill_BillDetailGUID02).sendKeys(BillDetailGUID3);
			wait(2);
			driver.findElement(Cust_Activity_MaintainBill_BillGUID).sendKeys(BillGUID);
			wait(2);
			driver.findElement(Cust_Activity_GenerateBillDetails_OkButton).click();
			wait(8);
			driver.findElement(Cust_Activity_GenerateBillDetails_Process).click();
			wait(8);
			int n = driver.findElements(By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']//div[@class='oj-rowexpander-icon-spacer']//a")).size();
			n += 1;
			int j=0;
			for (j=2 ; j<n ; j++) {
				By GenerateBillExpander = By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']["+j+"]//div[@class='oj-rowexpander-icon-spacer']//a");
				By  GenerateBillFinder = By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']["+j+"]//td[3]//span[contains(text(),'GenerateBill')]");
				if(exists(GenerateBillFinder)) {
					driver.findElement(GenerateBillExpander).click();
					break;
				}		
			}
			wait(5);
			driver.findElement(Cust_Activity_BillingTab).click();
			wait(5);
			Assert.assertEquals(driver.findElement(Cust_Activity_Billing_RecordsValue).getText(), "1");
			
			NavigateToElement(driver,Cust_Activity_Billing_RecordsValue);
			HighlightElement(driver,Cust_Activity_Billing_RecordsValue);
			takeScreenShot(driver,"GenerateBill_MultipleBillRemoved");
			UnhighlightElement(driver,Cust_Activity_Billing_RecordsValue);
			
		}
		
		/*Add Single BillDetail to a Bill*/	
		@Test
		public void GB05_TC005() throws IOException, SQLException {
			
			/*Update Maintain Bill attach rule*/
			Query = "select companyguid from ascompany where companyname = 'Prototype'";
			QueryResult = RunQuery(Query);
			String companyGUID = QueryResult.get(0);
			
			Query = "select planguid from asplan where planname like 'Customer Plan' and companyguid='"+companyGUID+"'";
			QueryResult = RunQuery(Query);
			String customerPlanGUID = QueryResult.get(0);
			
			Query = "select transactionguid from astransaction where transactionname = 'MaintainBill' and PlanGuid = '"+customerPlanGUID+"'";
			QueryResult = RunQuery(Query);
			String transactionGUID = QueryResult.get(0);
			
			updateConfig(".\\src\\com\\Utilities\\MaintainBillRule.txt","RemoveFromBill","AddToBill");
			
			//Add To Bill
			Query = "update asbusinessrules set xmldata =? where rulename = 'MaintainBill' and transactionguid ='"+transactionGUID+"'";
			db_updateClob(".\\src\\com\\Utilities\\MaintainBillRule.txt", Query);
			db_refresh();
			
			driver.findElement(OIPA_UserMenu_Button).click();
			wait(2);
			selectItemInDropdown(driver,"Sign Out");
			driver = login(driver);
			wait(5);
			driver.findElement(OIPA_Home_AdvancedSerach_Link).click();
			wait(5);
			driver.findElement(OIPA_AdvancedSearch_CustomerSearch).click();
			wait(5);
			driver.findElement(OIPA_AdvancedSearch_CustomerType).click();
			wait(2);
			selectItemInDropdown(driver,"Group Customer");
			wait(3);
			if(driver.findElement(OIPA_AdvancedSerach_ExpandSearchFields).getAttribute("aria-expanded").equalsIgnoreCase("false")) {
				driver.findElement(OIPA_AdvancedSerach_ExpandSearchFields).click();
			}
			driver.findElement(OIPA_AdvancedSearch_GroupCustomerNumber).sendKeys(CustNumber);
			driver.findElement(OIPA_AdvancedSearch_SearchButton).click();
			wait(10);
			driver.findElement(newCust_Activities_NavLink).click();
			wait(5);
			
			/*Add and Process Maintain Bill*/
			driver.findElement(Cust_AddActivity_AddActivityButton).click();
			wait(5);
			driver.findElement(Cust_AddActivity_ActivityDropDown).click();
			wait(2);
			selectItemInDropdown(driver, "MaintainBill");
			wait(5);
			driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).clear();
			wait(3);
			driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBill_EffectiveDate",2));
			wait(2);
			driver.findElement(Cust_Activity_MaintainBill_BillDetailGUID01).sendKeys(BillDetailGUID1);
			wait(2);
			driver.findElement(Cust_Activity_MaintainBill_BillGUID).sendKeys(BillGUID);
			wait(2);
			driver.findElement(Cust_Activity_GenerateBillDetails_OkButton).click();
			wait(8);
			driver.findElement(Cust_Activity_GenerateBillDetails_Process).click();
			wait(8);
			
			/*Check Bill Detail Addition*/
			
			int n = driver.findElements(By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']//div[@class='oj-rowexpander-icon-spacer']//a")).size();
			n += 1;
			int j=0;
			for (j=3 ; j<n ; j++) {
				By GenerateBillExpander = By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']["+j+"]//div[@class='oj-rowexpander-icon-spacer']//a");
				By  GenerateBillFinder = By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']["+j+"]//td[3]//span[contains(text(),'GenerateBill')]");
				if(exists(GenerateBillFinder)) {
					driver.findElement(GenerateBillExpander).click();
					break;
				}		
			}
			wait(5);
			driver.findElement(Cust_Activity_BillingTab).click();
			wait(5);
			Assert.assertEquals(driver.findElement(Cust_Activity_Billing_RecordsValue).getText(), "2");
			
			NavigateToElement(driver,Cust_Activity_Billing_RecordsValue);
			HighlightElement(driver,Cust_Activity_Billing_RecordsValue);
			takeScreenShot(driver,"GenerateBill_SingleBillAdded");
			UnhighlightElement(driver,Cust_Activity_Billing_RecordsValue);
	}
		/*Add Multiple BillDetail To Bill*/	
		@Test
		public void GB06_TC006() throws IOException, SQLException {
			wait(5);
			driver.findElement(Cust_AddActivity_AddActivityButton).click();
			wait(5);
			driver.findElement(Cust_AddActivity_ActivityDropDown).click();
			wait(2);
			selectItemInDropdown(driver, "MaintainBill");
			wait(5);
			driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).clear();
			wait(3);
			driver.findElement(Cust_Activity_GenerateBillDetails_EffectiveDate).sendKeys(xls.getCellData("CustomerBilling","GenerateBill_EffectiveDate",2));
			wait(2);
			driver.findElement(Cust_Activity_MaintainBill_BillDetailGUID01).sendKeys(BillDetailGUID2);
			wait(2);
			driver.findElement(Cust_Activity_MaintainBill_BillDetailGUID02).sendKeys(BillDetailGUID3);
			wait(2);
			driver.findElement(Cust_Activity_MaintainBill_BillGUID).sendKeys(BillGUID);
			wait(2);
			driver.findElement(Cust_Activity_GenerateBillDetails_OkButton).click();
			wait(8);
			driver.findElement(Cust_Activity_GenerateBillDetails_Process).click();
			wait(8);
			
			/*Check Bill Detail Addition*/
			
			int n = driver.findElements(By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']//div[@class='oj-rowexpander-icon-spacer']//a")).size();
			n += 1;
			int j=0;
			for (j=4 ; j<n ; j++) {
				By GenerateBillExpander = By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']["+j+"]//div[@class='oj-rowexpander-icon-spacer']//a");
				By  GenerateBillFinder = By.xpath("//tr[@class='oj-table-body-row oj-table-hgrid-lines']["+j+"]//td[3]//span[contains(text(),'GenerateBill')]");
				if(exists(GenerateBillFinder)) {
					driver.findElement(GenerateBillExpander).click();
					break;
				}		
			}
			wait(5);
			driver.findElement(Cust_Activity_BillingTab).click();
			wait(5);
			Assert.assertEquals(driver.findElement(Cust_Activity_Billing_RecordsValue).getText(), "4");
			
			NavigateToElement(driver,Cust_Activity_Billing_RecordsValue);
			HighlightElement(driver,Cust_Activity_Billing_RecordsValue);
			takeScreenShot(driver,"GenerateBill_MultipleBillAdded");
			UnhighlightElement(driver,Cust_Activity_Billing_RecordsValue);
		}
		
		@AfterClass
		public void EndTC() throws Throwable{
			closeDbConnection();
			closeBrowser(driver);
			updateConfig(".\\src\\com\\Utilities\\MaintainBillRule.txt",BillGUID,"ReplaceThis");
			updateConfig(".\\src\\com\\Utilities\\MaintainBillRule.txt","AddToBill","RemoveFromBill");
		}
}
