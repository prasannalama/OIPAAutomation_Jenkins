package com.JenkinsAutomation;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Library.OIPA_Library;

public class CreateClient extends OIPA_Library {

	// Tester : Prasanna
	// Functionality : Create_Client-1

	Random rand = new Random();
	public String[] PolicyText, ClientText, CustomerText, CustomerText2;
	public String policyHeading, clientHeading, CustomerHeading, clientfirstname, pname, CX_number,
			existed_policyNumber;
	Actions a;

	@BeforeClass
	public void login() throws Throwable {
		
		System.out.println("***************Create Client1******************************");

		openBrowser();
		login(driver1);		

	}

	
   @Test
	public void clientCreation() throws IOException {
		// creating 02(individual) type client

		driver1.findElement(oipa_createDD).click();
		wait(3);
		selectItemInDropdown(driver1, "Client");
		wait(3);
		driver1.findElement(oipa_CreateButton).click();
		wait(3);
		driver1.findElement(oipa_client_FirstName).sendKeys(xls.getCellData("Client", 0, 2) + rand.nextInt(1000));
		wait(2);
		driver1.findElement(oipa_client_LastName).sendKeys(xls.getCellData("Client", 1, 2));
		wait(2);
		driver1.findElement(oipa_client_DOB).sendKeys(xls.getCellData("Client", 2, 2));
		wait(2);
		driver1.findElement(oipa_client_TaxID).sendKeys(xls.getCellData("Client", 3, 2));
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

	

	@AfterClass
	public void postTestCase() {
		closeBrowser(driver1);
	}

}
