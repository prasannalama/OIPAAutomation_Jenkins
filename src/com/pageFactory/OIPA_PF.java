package com.pageFactory;

import org.openqa.selenium.By;

public class OIPA_PF {

	// Login Page
	public By oipa_username = By.xpath("//input[@id='userName']");
	public By oipa_password = By.xpath("//input[@id='password']");
	public By oipa_login = By.xpath("//input[@id='submit']");
	public By oipa_cancel = By.xpath("//input[@id='cancel']");

	// OIPAHome Page
	public By oipa_createDD = By.xpath("//div[@id='oj-select-choice-Menu_CreateNew']//a");
	public By oipa_CreateButton = By.xpath("//button[@title='Create']");

	// PolicyCreation
	public By oipa_policy_companyDD = By.xpath("//div[@id='oj-select-choice-CompanyGUID']//a");
	public By oipa_policy_productDD = By.xpath("//div[@id='oj-select-choice-Product']//a");
  	public By oipa_policy_productDD_list=By.xpath("//ul[@id='oj-listbox-results-Product']//li//div");

	public By oipa_policy_planDD = By.xpath("//div[@id='oj-select-choice-PlanGUID']//a");
	public By oipa_policy_policyname = By.xpath("//input[@id='PolicyName']");
	public By oipa_policy_BTBS_policynumber=By.xpath("//input[@id='PolicyNumber']");
	public By oipa_policy_plandate = By.xpath("//input[@id='PlanDate']");
  	public By oipa_policy_RiskCommencementDate=By.xpath("//input[@id='RiskCommencementDate']");
  	public By oipa_policy_RiskCessationDate=By.xpath("//input[@id='RiskCessationDate']");
  	public By oipa_policy_MemberAccountNumber=By.xpath("//input[@id='MemberAccountNumber']");
  	public By oipa_policy_EmployerBenefitCategoryCode=By.xpath("//input[@id='EmployerBenefitCategoryCode']");
	public By oipa_policy_SmokerStatus = By.xpath("//div[@id='oj-select-choice-SmokerStatus']//a");
  	public By oipa_policy_CollarRating=By.xpath("//input[@id='CollarRating']");
  	public By oipa_policy_OccupationCode=By.xpath("//input[@id='OccupationCode']");
  	public By oipa_policy_OccupationDescription=By.xpath("//input[@id='OccupationDescription']");
  	public By oipa_policy_LastAnnualReviewDate=By.xpath("//input[@id='LastAnnualReviewDate']");
  	public By oipa_policy_PremiumEffectiveDate=By.xpath("//input[@id='PremiumEffectiveDate']");
  	public By oipa_policy_TotalPremiumDue=By.xpath("//input[@id='TotalPremiumDue']");
	public By oipa_policy_PremiumFrequency= By.xpath("//div[@id='oj-select-choice-PremiumFrequency']//a");
  	public By oipa_policy_PremiumWaiverapplicableCheckbox=By.xpath("//input[@id='PremiumWaiverapplicable_UNCHECKED']");
  	public By oipa_policy_PremiumWaiverEffectiveDate=By.xpath("//input[@id='PremiumWaiverEffectiveDate']");
  	public By oipa_policy_FrequencyLoading=By.xpath("//input[@id='FrequencyLoading']");
  	public By oipa_policy_ModalPremium=By.xpath("//input[@id='ModalPremium']");
  	public By oipa_policy_ExplicitStampDutyFlagCheckbox=By.xpath("//input[@id='ExplicitStampDutyFlag_UNCHECKED']");
  	public By oipa_policy_RateGuaranteeFlagCheckbox=By.xpath("//input[@id='RateGuaranteeFlag_UNCHECKED']");
  	public By oipa_policy_RateGuaranteEndDate=By.xpath("//input[@id='RateGuaranteeEndDate']");
	public By oipa_policy_EmployerPaysPremium= By.xpath("//div[@id='oj-select-choice-EmployerPaysPremium']//a");
  	public By oipa_policy_PlanRatingFactor=By.xpath("//input[@id='PlanRatingFactor']");
	public By oipa_policy_BenefitPaymentFrequency= By.xpath("//div[@id='oj-select-choice-BenefitPaymentFrequency']//a");
	public By oipa_policy_RegistrySystemCode= By.xpath("//div[@id='oj-select-choice-RegistrySystemCode']//a");
	public By oipa_policy_PremiumPaymentType= By.xpath("//div[@id='oj-select-choice-PremiumPaymentType']//a");

  	public By oipa_policy_UnderwrittenOccupationClass=By.xpath("//input[@id='UnderwrittenOccupationClass']");
  	public By oipa_policy_AnnualisedStampDutyAmountFlagCheckbox=By.xpath("//input[@id='AnnualisedStampDutyAmountFlag_UNCHECKED']");
  	public By oipa_policy_ClaimIndexationBenefitApplicableCheckbox=By.xpath("//input[@id='ClaimIndexationBenefitApplicable_UNCHECKED']");
  	public By oipa_policy_ExcludeFromSCITaxCheckbox=By.xpath("//input[@id='ExcludeFromSCITax_UNCHECKED']");

  	public By oipa_policy_BeneficiaryDetails=By.xpath("//div[@id='ojChoiceId_BeneficiaryDetails']//a");
  	public By oipa_policy_BeneficiaryName=By.xpath("//input[@id='BeneficiaryName']");
  	public By oipa_policy_BeneficiaryRelationship=By.xpath("//div[@id='oj-select-choice-BeneficiaryRelationship']//a");
  	public By oipa_policy_BeneficiarySplitPercentage=By.xpath("//input[@id='BeneficiarySplitPercentage']");
	public By oipa_policy_savebutton = By.xpath("//button[@id='policySaveButtonTop']");

	public By oipa_policy_policynumber = By.xpath("//div[contains(text(),'WP')]");

	// ClientCreation
	public By oipa_client_typeDD = By.xpath("//div[@id='oj-select-choice-clientType']//a");
	public By oipa_client_legalResidenceCountry = By.xpath("//div[@id='oj-select-choice-legalResidence']//a");
	public By oipa_client_prefix = By.xpath("//div[@id='oj-select-choice-Prefix']//a");
	public By oipa_client_FirstName = By.xpath("//input[@id='FirstName']");
	public By oipa_client_MiddleName = By.xpath("//input[@id='MiddleInitial']");
	public By oipa_client_LastName = By.xpath("//input[@id='LastName']");
	public By oipa_client_Gender = By.xpath("//input[@id='Sex_01']");
	public By oipa_client_MartialDD = By.xpath("//div[@id='ojChoiceId_MaritalStatus']//a");
	public By oipa_client_DOB = By.xpath("//input[@id='DateOfBirth']");
	public By oipa_client_TaxID = By.xpath("//input[@id='TaxID']");
	public By oipa_client_BirthCountry = By.xpath("//div[@id='ojChoiceId_BirthCountryCode']//a");
	public By oipa_client_citizenShipCountry = By.xpath("//div[@id='ojChoiceId_CitizenshipCountryCode']//a");
	public By oipa_client_TextField = By.xpath("//input[@id='TextField1']");
	public By oipa_client_AccountName = By.xpath("//input[@id='BankAccountName-0']");
	public By oipa_client_AccountNumber = By.xpath("//input[@id='BankAccountNumber-0']");
	public By oipa_client_savebutton = By.xpath("//button[@id='saveButton']");
	public By oipa_client_clientnamedashboard = By.xpath("//div[contains(text(),'Test')]");


	//*****************************Sushma Page Factory************************************************//
	
	// Group Customer Address creation objects
    public By oipa_GC_CustomerAddressLink=By.xpath("//li[@id='groupcustomeraddress']//div//span[contains(text(),'Address')]");
    public By oipa_GC_CustomerAddress_AddAddressButton=By.xpath("//button[@id='addAddress']");
    public By oipa_GC_CustomerAddress_AddressTypeDD=By.xpath("//div[@id='oj-select-choice-addressRoleType']//a");
    public By oipa_GC_CustomerAddress_CountryDD=By.xpath("//div[@id='oj-select-choice-addressCountryType']//a");
    public By oipa_GC_CustomerAddress_AddressLine1=By.xpath("//input[@id='AddressLine1']");
    public By oipa_GC_CustomerAddress_AddressLine2=By.xpath("//input[@id='AddressLine2']");
    public By oipa_GC_CustomerAddress_AddressLine3=By.xpath("//input[@id='AddressLine3']");
    public By oipa_GC_CustomerAddress_AddressLine4=By.xpath("//input[@id='AddressLine4']");
    public By oipa_GC_CustomerAddress_City=By.xpath("//input[@id='City']");
    public By oipa_GC_CustomerAddress_StateDD=By.xpath("//div[@id='oj-select-choice-StateCode']//a");
    public By oipa_GC_CustomerAddress_Zip=By.xpath("//input[@id='PostalID']");
    public By oipa_GC_CustomerAddress_Email=By.xpath("//input[@id='Email']");
    public By oipa_GC_CustomerAddress_Fax=By.xpath("//input[@id='FaxNumber']");
    public By oipa_GC_CustomerAddress_EffectiveDate=By.xpath("//input[@id='EffectiveDate']");
    public By oipa_GC_CustomerAddress_Savebutton=By.xpath("//button[@id='CustomerAddressSaveButtonBottom']");
    
	//Objects added by Sushma
    // Group Customer Search Objects
    public By oipa_SearchDD=By.xpath("//div[@id='oj-select-choice-Menu_Search']//a");
	public By oipa_SearchData=By.xpath("//input[@id='unifiedsearchinput']");
	public By oipa_SearchButton=By.xpath("//button[@id='unifiedSearchIcon']");
	
	// Objects added by Sushma
	// Group Customer address delete functionality objects
	
	public By oipa_GC_CustomerAddress_DeleteOKButton=By.xpath("//button[@id='deleteOkButton']");
	public By oipa_GC_CustomerAddress_DeleteCancelButton=By.xpath("//button[@id='deleteCancelButton']");
	
	// Objects added by Sushma
	//Group Customer adding phone number objects
	public By oipa_GC_CustomerPhoneLink=By.xpath("//li[@id='phone']//span[contains(text(),'Phone Numbers')]");
	public By oipa_GC_CustomerPhone_AddPhoneButton=By.xpath("//button[@id='addPhone']");
	public By oipa_GC_CustomerPhone_PhoneTypeDD=By.xpath("//div[@id='oj-select-choice-phoneSelect']//a");
	public By oipa_GC_CustomerPhone_CountryDD=By.xpath("//div[@id='oj-select-choice-countrySelect']//a");
	public By oipa_GC_CustomerPhone_Number=By.xpath("//input[@id='PhoneNumber']");
	public By oipa_GC_CustomerPhone_SaveButton=By.xpath("//button[@id='phoneNumberSaveButtonBottom']");
    
	//Objects added by Sushma
	// Group Customer Phone Number Edit Objects
	public By oipa_GC_PhoneEdit_ExpandIcon=By.xpath("//table[@id='phoneTable']//tbody//tr[1]//a");
	public By oipa_GC_PhoneEdit_PhoneTypeDD=By.xpath("//div[@id='oj-select-choice-phoneSelect']//a");
	public By oipa_GC_PhoneEdit_CountryDD=By.xpath("//div[@id='oj-select-choice-countrySelect']//a");
	public By oipa_GC_PhoneEdit_ExtensionTxt=By.xpath("//input[@id='Extension']");
	public By oipa_GC_PhoneEdit_SaveButton=By.xpath("//button[@id='phoneNumberSaveButtonBottom']");
	
	//Objects added by Sushma
	//Group Customer Phone Number delete objects
	public By OIPA_GC_Phone_DeleteCancelButton=By.xpath("//div[@class='oj-button-label']//span[contains(text(),'Cancel')]");
	public By OIPA_GC_Phone_DeleteOKButton=By.xpath("//div[@class='oj-button-label']//span[contains(text(),'OK')]");
	
	//Objects added by Sushma
	//Group Customer AddActivity menu objects
	public By oipa_GC_AddActivity_ActivitiesLink=By.xpath("//li[@id='MenuItem_AddActivity']//span[contains(text(),'Add Activity')]");
	public By oipa_GC_AddActivity_ActivitySelectionDD=By.xpath("//div[@id='oj-select-choice-selectActivityCombo']//a");
	public By oipa_GC_AddActivity_AddActivityCancelButton=By.xpath("//button[@id='activityCancelButton']//span[contains(text(),'Cancel')]");
	public By oipa_GC_AddActivity_ActivityOKButton=By.xpath("//button[@id='activityOkButton']");
	public By oipa_GC_AddActivity_ActivityCancelButton=By.xpath("//button[@id='activityCancelButton']");
	public By oipa_GC_AddActivity_ActivityOkNavigateButton=By.xpath("//button[@id='activityOkNavigateButton']");
	
	//Objects added by Sushma
	//Group Customer Activities screen objects
	public By oipa_GC_Activities_Link=By.xpath("//li[@id='activities']//span[contains(text(),'Activities')]");
	public By oipa_GC_Activities_RowExpandIcon=By.xpath("//table//tbody//tr[1]//td[2]//a");
	public By oipa_GC_Activities_ActivityProcessButton=By.xpath("//button[@data-id='activityProcess']");
	public By oipa_GC_Activities_ActivityRecycleButton=By.xpath("//button[@data-id='activityRecycle']");
	public By oipa_GC_Activities_ActivityRecycleOKButton=By.xpath("//button[@id='activityOkButton']");
	public By oipa_GC_Actvities_PendingReversal_ProcessButton=By.xpath("//table[@id='groupCustomerActivitiesTable']//tbody//tr[3]//td[8]//button[@data-id='activityProcess']");
	public By oipa_GC_Activities_Rows=By.xpath("//table[@id=('groupCustomerActivitiesTable')]//tbody//tr");
	
	//Objects added by Sushma
	//Group Customer Add Comments Objects
	public By oipa_GC_Comments_AddCommentsLink=By.xpath("//li[@id='MenuItem_AddComments']//span[contains(text(),'Add Comment')]");
	public By oipa_GC_AddComments_NewButton=By.xpath("//div[@id='segmentContainer']//span[contains(text(),'New')]");
	public By oipa_GC_AddComments_FunctionalDepartmentDD=By.xpath("//div[@id='oj-select-choice-addCommentsTable_FunctionalDepartment']//a");
	public By oipa_GC_AddComments_Categroy=By.xpath("//div[@id='oj-select-choice-addCommentsTable_Category']//a");
	public By oipa_GC_AddComments_ClientCommentsField=By.xpath("//input[@id='addCommentsTable_ClientCommentField']");
	public By oipa_GC_AddComments_CommentName=By.xpath("//div[@id='oj-select-choice-addCommentsTablecommentDetailsTemplate']//a");
	public By oipa_GC_AddComments_CommentTextArea=By.xpath("//textarea[@id='addCommentsTableComment']");
	public By oipa_GC_AddComments_SaveButton=By.xpath("//input[@id='addCommentsTablesaveButton']");
	public By oipa_GC_AddComments_CloseWindowButton=By.xpath("//button[@title='Close']");
	
	//Objects added by Sushma
	//Group Customer Upload Comments Objects
	public By oipa_GC_Comments_UploadButton=By.xpath("//input[@id='addCommentsTableuploadButton']");
	public By oipa_GC_Comments_ChooseFileButton=By.xpath("//input[@id='addCommentsTableupload_file']");
	
	//Objects added by Sushma
	//Group Customer Update comments Objects
	public By oipa_GC_Comments_CommentsLink=By.xpath("//li[@id='comments']//span[contains(text(),'Comments')]");
	public By oipa_GC_Comments_DisplayBoxDD=By.xpath("//div[@id='ojChoiceId_displayCombobox']");
	public By oipa_GC_Comments_DisplayBoxSelect=By.xpath("//input[@id='oj-combobox-input-36']");
	public By oipa_GC_Comments_RefreshButton=By.xpath("//input[@id='findButton']");
	public By oipa_GC_Comments_RowExpandIcon=By.xpath("//table[@id='commentsTable']//tbody//tr[1]//a");
	public By oipa_GC_EditComments_FunctionalDepartmentDD=By.xpath("//div[@id='oj-select-choice-commentsTable_FunctionalDepartment']//a");
	public By oipa_GC_EditComments_Categroy=By.xpath("//div[@id='oj-select-choice-commentsTable_Category']//a");
	public By oipa_GC_EditComments_ClientCommentsField=By.xpath("//input[@id='commentsTable_ClientCommentField']");
	public By oipa_GC_EditComments_CommentName=By.xpath("//div[@id='oj-select-choice-commentsTablecommentDetailsTemplate']//a");
	public By oipa_GC_EditComments_CommentTextArea=By.xpath("//textarea[@id='commentsTableComment']");
	public By oipa_GC_EditComments_SaveButton=By.xpath("//input[@id='commentsTablesaveButton']");
	
	
	

	//Objects added by Sushma
	//Group Customer Agreement Roles Objectes
	public By oipa_GC_AgreementRoles_RolesLink=By.xpath("//strong//a[contains(text(),'Roles')]");
	public By oipa_GC_AgreementRoles_FindClientButton=By.xpath("//button[@id='findClient']//span[contains(text(),'Find Client')]");
	public By oipa_GC_AgreementRoles_NewClientButton=By.xpath("//button[@id='addClient']//span[contains(text(),'New Client')]");
	public By oipa_GC_AgreementRoles_FindCustomerButton=By.xpath("//button[@id='findCustomer']//span[contains(text(),'Find Customer')]");
	
	//Find Client Objects
	public By Oipa_GC_AgreementRoles_FindClient_TypeDD=By.xpath("//div[@id='oj-select-choice-agreementRoleFindClientList']//a");
	public By oipa_GC_AgreementRoles_FindClient_Type=By.xpath("//div[@id='oj-select-choice-agreementRoleFindClientList']");
	public By oipa_GC_AgreementRoles_FindClient_ClientType=By.xpath("//div[@id='oj-select-choice-clientType']//a");
	public By oipa_GC_AgreementRoles_FindClient_FirstName=By.xpath("//input[@id='FirstName_Client']");
	public By oipa_GC_AgreementRoles_FindClient_SearchButton=By.xpath("//button[@id='buttonSubmit']");
	public By oipa_GC_AgreementRoles_SearchResult=By.xpath("//table[@id='searchresultstable']//tbody//tr");
	public By oipa_GC_AgreementRoles_FindClientSaveButton=By.xpath("//button[contains(@data-bind,'AddClientRole')]");
	public By oipa_GC_AgreementRoles_RolesTable_AgreementType=By.xpath("//table[@id='agreementRolesTable']//tbody//tr//td//span[contains(text(),'Agreement Role Type 1')]");
	
	//New Client Objects
	public By oipa_GC_AgreementRoles_NewClient_TypeDD=By.xpath("//div[@id='oj-select-choice-agreementRoleAddList']//a");
	public By oipa_GC_AgreementRoles_NewClient_Type=By.xpath("//div[@id='oj-select-choice-agreementRoleAddList']");
	public By oipa_GC_AgreementRoles_NewClient_ClientTypeDD=By.xpath("//div[@id='oj-select-choice-clientType']//a");
	public By oipa_GC_AgreementRoles_NewClient_AddressTypeDD=By.xpath("//div[@id='oj-select-choice-addressRoleType']//a");
    public By oipa_GC_AgreementRoles_NewClient_CountryDD=By.xpath("//div[@id='oj-select-choice-addressCountryType']//a");
    public By oipa_GC_AgreementRoles_NewClient_AddressLine1=By.xpath("//input[@id='AddressLine1']");
    public By oipa_GC_AgreementRoles_NewClient_AddressLine2=By.xpath("//input[@id='AddressLine2']");
    public By oipa_GC_AgreementRoles_NewClient_AddressLine3=By.xpath("//input[@id='AddressLine3']");
    public By oipa_GC_AgreementRoles_NewClient_AddressLine4=By.xpath("//input[@id='AddressLine4']");
    public By oipa_GC_AgreementRoles_NewClient_City=By.xpath("//input[@id='City']");
    public By oipa_GC_AgreementRoles_NewClient_StateDD=By.xpath("//div[@id='oj-select-choice-StateCode']//a");
    public By oipa_GC_AgreementRoles_NewClient_Zip=By.xpath("//input[@id='PostalID']");
    public By oipa_GC_AgreementRoles_NewClient_Email=By.xpath("//div[@id='addressfixedFields']//input[@id='Email']");
    public By oipa_GC_AgreementRoles_NewClient_Fax=By.xpath("//input[@id='FaxNumber']");
	public By oipa_GC_AgreementRoles_NewClientSaveButton=By.xpath("//button[contains(@data-bind,'AddNewClientRole')]//div");
	public By oipa_GC_AgreementRoles_RowExpandIcon=By.xpath("//table[@id='agreementRolesTable']//tbody//tr[1]//a");
	public By oipa_GC_AgreementRoleDetail_IdentifierNumber=By.xpath("//input[@id='IdentifierNumber']");
	public By oipa_GC_AgreementRoleDetail_AgreementRoleType=By.xpath("//input[@id='AgreementRoleType']");
	public By oipa_GC_AgreementRoleDetail_AgreementRoleStatusDD=By.xpath("//div[@id='oj-select-choice-AgreementRoleStatus']//a");
	public By oipa_GC_AgreementRoleDetail_AgreementRoleStatusActive=By.xpath("//ul[@class='oj-listbox-results']//li[1]//div");
	public By oipa_GC_AgreementRoleDetail_AgreementRoleStatusDeleted=By.xpath("//ul[@class='oj-listbox-results']//li[2]//div");
	public By oipa_GC_AgreementRoleDetail_AgreementRoleStatusInactive=By.xpath("//ul[@class='oj-listbox-results']//li[3]//div");
	public By oipa_GC_AgreementRoleDetail_AgreementRoleSaveButton=By.xpath("//button[@id='agreementRoleSave']");
	public By oipa_GC_AgreementRoleDetail_AgreementDetailSection=By.xpath("//div[@id='subsectionDynamicFields']");
			
	//Objects Added By Sushma
	//Group Customer Agreement Roles Edit objects
	public By oipa_GC_AgreementRole_EditHamburgerMenu=By.xpath("//table[@id='agreementRolesTable']//div[@class='oi-text-align-center']");
	public By oipa_GC_AgreementRole_EditRoleDetail=By.xpath("//ul[@id='roleActionMenu']//li[@id='editRole']//span[contains(text(),'Edit Role Details')]");
	public By oipa_GC_AgreementRole_EditAgreementRoleType=By.xpath("//input[@id='AgreementRoleType']");
	public By oipa_GC_AgreementRole_EditRole_AgreementStatus=By.xpath("//div[@id='oj-select-choice-AgreementRoleStatus']//a");
	public By oipa_GC_AgreementRole_EditRole_Save=By.xpath("//button[@id='agreementRoleSave']");
	public By oipa_GC_AgreementRole_AgreementRoleStatus=By.xpath("//div[@id='oj-select-choice-AgreementRoleStatus']//span");
	
	//Objects Added By Sushma
	//Group Customer Agreement Roles Delete Objects
	public By oipa_GC_AgreementRole_DeleteRole=By.xpath("//li[@id='deleteRole']//span[contains(text(),'Delete Role')]");
	public By oipa_GC_AgreementRole_DeleteRole_AgremmentStatus=By.xpath("//table[@id='agreementRolesTable']//tbody//tr[1]//td[5]");
	public By oipa_GC_AgreementRole_DeleteRole_AgreementRoleStatusinTable=By.xpath("//table[@id='agreementRolesTable']//tbody//tr[1]//td[5]//span");
	
	//Objects Added By Sushma
	//Group Customer Agreement Roles Find Customer Objects
	public By oipa_GC_AgreementRole_FindCustomer_SearchExpandIcon=By.xpath("//div[@id='bsdynamicFields']//a");
	public By oipa_GC_AgreementRole_FindCustomer_CustomerNumber=By.xpath("//input[@id='CustomerNumber_GroupCustomer']");
	public By oipa_GC_AgreementRole_FindCustomer_Search=By.xpath("//button[@id='bsbtnSearch']");
	public By oipa_GC_AgreementRole_Findustomer_SearchResult=By.xpath("//table[@id='searchresultstable']//tbody//tr");
	public By oipa_GC_AgreementRole_FindCustomer_SearchOKButton=By.xpath("//button[contains(@data-bind,'processAddCustomerRole')]");
	

		
		//public By oipa_GC_planProcessAddSegment = By.xpath("//button[contains(@data-bind,'processAddSegment')]");
		
		//Objects added by Sushma
		//Group Customer Plans Objects
		public By oipa_GC_Plans_PlansLeftsideLink=By.xpath("//li[@id='customerplans']//span[contains(text(),'Plans')]"); 
		public By oipa_GC_Plans_PlanRowExpandIcon=By.xpath("//div[@class='oj-collapsible-header']//a[@role='button']");
		public By oipa_GC_plans_PlanSliceRowExpandIcon=By.xpath("//div[contains(@data-bind,'collapseTimeSlice')]//a");
		public By oipa_GC_Plans_PlanSegmentsLink=By.xpath("//div[@id='planDetailMenuBar']//a[@id='plansegment']");
		public By oipa_GC_Plans_PlanSegments_AddSegmentButton=By.xpath("//button[@id='addSegmentCommandButton']");
		public By oipa_GC_Plans_PlanSegments_SegmentNameDD=By.xpath("//div[@id='ojChoiceId_SegmentNameCombo']//a");
		public By oipa_GC_Plans_PlanSegments_PlanSegmentName=By.xpath("//input[@id='PlanSegmentName']");
		public By oipa_GC_Plans_PlanSegments_PlanSegmentTypeDD=By.xpath("//div[@id='oj-select-choice-PlanSegmentType']//a");
		public By oipa_GC_Plans_PlanSegments_SaveButton=By.xpath("//button[@id='savePlanSegmentButton']");
		public By oipa_GC_Plans_PlanSegments_SegmentTextVerify=By.xpath("(//div[contains(@class,'oi-inline oi-wordbreak ')])[1]");
		public By oipa_GC_Plans_PlanSegments_HamburgerMenuIcon=By.xpath("(//span[contains(@class,'oi-icon-func_fourhorizontallines')])[2]");
		public By oipa_GC_Plans_PlanSegments_DeleteButton=By.xpath("//ul[@id='deleteMenu']//a[contains(text(),'Delete')]");
		
		//Group Customer Activate Plan Objects

		
		
	
		
		//Group Customer Class Group Copy Objects
		public By oipa_GC_ClassGroups_HamburgerMenuIcon=By.xpath("//span[@class='oi-icon-func_fourhorizontallines']");
		public By oipa_GC_ClassGroups_Hamburger_GoToAgreement=By.xpath("//li[@id='goToAgreement']");
		public By oipa_GC_CopyClassGroups_AsofDateTxtBox=By.xpath("//input[@id='classGroupAsOfDate']");
		public By oipa_GC_CopyClassGroups_RefreshButton=By.xpath("//button[@id='refreshButton']");
		public By oipa_GC_CopyClassGroups_ClassGroupCollapseIcon=By.xpath("//div[contains(@data-bind,'classGroupCollapsible')]//a");
		public By oipa_GC_CopyClassGroups_ClassGroupSelectCheckbox=By.xpath("//input[@aria-label='Compare checkbox']");
		public By oipa_GC_CopyClassGroups_ClassGroupExpandIcon=By.xpath("//table[@id='classGrouptimeSliceTable']//tbody//tr[1]//td[2]");
		public By oipa_GC_CopyClassGroups_NextButton=By.xpath("//button[@id='nextButton']");
		public By oipa_GC_CopyClassGroups_SaveButton=By.xpath("(//button[contains(@data-bind,'processSave')])[1]");
		
	
		
		//Client to customer Relationship and Plan Members Objects  
		public By oipa_Client_RelationshipLeftsideLink=By.xpath("//li[@id='relationships']//span[contains(text(),'Relationships')]");
		public By oipa_Client_UpstreamRelationships=By.xpath("//a[@id='UpStreamTab']");
		public By oipa_Client_RelationShipHamburgerIcon=By.xpath("//span[contains(@data-bind,'relationshipHamburger')]");
		public By oipa_Client_HamburgerMenu_EditCustomer=By.xpath("//li[@id='goToEditCustomerMenuItem']");
		public By oipa_GC_Plans_PlansExpanderinsideIcon=By.xpath("//table[@id='planHistoryTable']//tbody//tr[1]//td[2]");
		public By oipa_GC_Plans_PlanMembersLink=By.xpath("//strong//a[@id='planmember']");
		public By oipa_GC_Plans_PlanMembers_TableHamburgerMenuIcon=By.xpath("//table[@id='planMemberTable']//tbody//tr[1]//td[6]//span[contains(@data-bind,'showHamburgerMenu')]");
		public By oipa_GC_Plans_PlanMembers_HamburgerList_GotoClient=By.xpath("//ul[@id='goToMenu']//li[contains(@data-bind,'goToClientHandler')]//a");
		public By oipa_GC_Plans_PlanMembers_HamburgerList_GotoPolicy=By.xpath("		//ul[@id='goToMenu']//li[contains(@data-bind,'goToPolicyHandler')]//a");
		public By oipa_ClientNavigationCheck=By.xpath("//span[contains(@data-bind,'titleClient()')]");
		public By oipa_PolicyNavigationCheck=By.xpath("//span[contains(@data-bind,'titlePolicy()')]");

	//********************************************Abhishek Page Factory ******************************************//
	
    public By oipa_customer_typeDD= By.xpath("//div[@id='oj-select-choice-CustomerType']//a");
    public By oipa_customer_customerName= By.xpath("//input[@id='CompanyName']");
    public By oipa_customer_customerNumber= By.xpath("//input[@id='CustomerNumber']");
    public By oipa_customer_taxID= By.xpath("//input[@id='TaxID']");
    public By oipa_customer_customerLegalName= By.xpath("//input[@id='CustomerLegalName']");
    public By oipa_customer_saveButton= By.xpath("//button[@id='GroupCustomerSaveButtonBottom']");
    public By newCust_summary_newCustNumber = By.xpath("//div[contains(text(),'AutoGrpCustNum')]");
    public By newCust_summary_newCustNumberAuto = By.xpath("//div[contains(text(),'GC')]");
    public By newCust_Quote_NavLink = By.xpath("//li[@id='groupcustomerquote']");
    public By newCust_QuoteFilter_CollpaseAllSearch = By.xpath("//div[@id='oj-collapsible-gcquote-header']//a");
    public By newCust_QuoteFilter_QuoteNumber = By.xpath("//label[contains(text(),'Quote Number')]");
    public By newCust_QuoteFilter_Type = By.xpath("//label[contains(text(),'Type')]");
    public By newCust_QuoteFilter_From = By.xpath("//label[contains(text(),'From')]");
    public By newCust_QuoteFilter_To = By.xpath("//label[contains(text(),'To')]");
    public By newCust_QuoteFilter_RefreshButton = By.xpath("//button[@id='quoterefreshButton']");
    public By newCust_Quote_AddIcon = By.xpath("//span[@class='oi-icon-plus']");
    public By newCust_Quote_AddQuoteLabel = By.xpath("//label[contains(text(),'Add Quote')]");
    public By newCust_Quote_QuoteTypeDropdown = By.xpath("//div[@id='oj-select-choice-quoteType']");
    public By newCust_Quote_QuoteTypeDropdownClick = By.xpath("//div[@id='oj-select-choice-quoteType']//a");
    public By newCust_Quote_QuoteName = By.xpath("//div[@id='oj-select-choice-quoteName']");
    public By newCust_Quote_QuoteNameClick = By.xpath("//div[@id='oj-select-choice-quoteName']//a");
    public By newCust_Quote_AddQuoteButton = By.xpath("//button[@id='addQuote']");
    public By newCust_Quote_QuoteTable = By.xpath("//table[@id='quoteTable']");
    public By newCust_Quote_NewQuote_EffectiveDate = By.xpath("//input[@id='EffectiveDate']");
    public By newCust_Quote_NewQuote_ExpirationDate = By.xpath("//input[@id='ExpirationDate']");
    public By newCust_Quote_NewQuote_QuoteNumber = By.xpath("//input[@id='QuoteNumber']");
    public By newCust_Quote_NewQuote_Version = By.xpath("//input[@id='Version']");
    public By newCust_Quote_NewQuote_StatusCode = By.xpath("//input[@id='StatusCode']");
    public By newCust_Quote_NewQuote_Description = By.xpath("//input[@id='Description']");
    public By newCust_Quote_NewQuote_saveButton = By.xpath("//button[@id='saveButton']");
    public By newCust_Quote_NewQuote_benefits = By.xpath("//a[@id='benefits']");
    public By newCust_Quote_QuoteExpander = By.xpath("//div[@aria-label='groupcustomerquoterow']//a");
    public By Quote_NewQuote_HamburgerMenu = By.xpath("//a[@aria-label='quotehamburgermenu']");
    public By Quote_NewQuote_HamBurger_CreateNewVersion = By.xpath("//a[@id='createNewVersion']");
    public By Quote_NewQuote_HamBurger_ProcessQuote = By.xpath("//a[@id='processQuote']");
    public By Quote_NewQuote_Hamburger_Accept = By.xpath("//a[@id='acceptQuote']");
    public By Quote_NewQuote_Hamburger_Decline = By.xpath("//a[@id='declineQuote']");
    public By Quote_GetBenefitsTabTextByTabNumber = By.xpath("//div[@class='oj-flex']//div[@class='oi-inline'][2]//strong");
    public By Quote_GetMembersTabTextByTabNumber = By.xpath("//div[@class='oj-flex']//div[@class='oi-inline'][3]//strong");
    public By Quote_GetClaimsTabTextByTabNumber = By.xpath("//div[@class='oj-flex']//div[@class='oi-inline'][4]//strong");
    public By Quote_GetResultsTabTextByTabNumber = By.xpath("//div[@class='oj-flex']//div[@class='oi-inline'][5]//strong");
    public By Quote_AddBenefit_AddBenefitButton = By.xpath("//button[@id='addQuoteBenefit']");
    public By Quote_AddBenefit_Description = By.xpath("//input[@id='Description']");
    public By Quote_AddBenefit_BenefitSave = By.xpath("//button[@id='benefitSaveButton']");
    public By Quote_AddBenefits_BenefitCancel = By.xpath("//button[@id='benefitCancelButton']");
    public By Quote_AddBenefits_Confirmation = By.xpath("//strong[contains(text(),'successfully')]");
    public By Quote_AddBenefits_BenefitTable = By.xpath("//table[@id='quoteBenefitsTable']");
    public By Quote_AddBenefits_DescriptionAfterSave = By.xpath("//td//span[contains(text(),'Benefit_Auto_Test')]");
    public By Quote_AddBenefits_DeleteRow = By.xpath("//td//a[@title='Delete']");
    public By Quote_AddBenefits_ConfirmDelete = By.xpath("//button[@id='ok_popup_button']");
    public By Quote_BrowseFieldMemberClaim = By.xpath("//input[@id='upload_file']");
    public By Quote_UploadButtonMemberClaim = By.xpath("//button[@id='uploadButton']");
    public By Quote_DeleteButtonMemberClaim = By.xpath("//button[@id='deleteButton']");
    public By Quote_ResultsTab = By.xpath("//a[@id='results']");
    public By Quote_Results_SummaryCollapseButton = By.xpath("//h4[contains(@id,'oj-collapsible')]//a");
    public By Quote_DisplayQuote_QuoteStatusProcessed = By.xpath("//td//span[contains(text(),'Processed')]");
    public By Quote_AcceptQuote_Confirmation = By.xpath("//div[@id='actionWarningDialog']//div//p");
    public By Quote_AcceptQuote_Confirmation_Ok = By.xpath("//button//div//span[contains(text(),'OK')]");
    public By Quote_DisplayQuote_QuoteStatusAccepted = By.xpath("//td//span[contains(text(),'Accepted')]");
    public By Quote_DisplayQuote_QuoteStatusPending = By.xpath("//td//span[contains(text(),'Pending')]");
    public By Quote_DisplayQuote_QuoteStatusRejected = By.xpath("//td//span[contains(text(),'Rejected')]");
    public By Quote_HamburgerMenu_LatestQuote = By.xpath("//tr[1]//a[@aria-label='quotehamburgermenu']");
    public By Quote_NewQuote_LatestQuote = By.xpath("//tbody//tr[1]");

    
    //Abhishek Group Billing Page Factory
    public By newCust_Activities_NavLink = By.xpath("//li[@id='activities']");
	public By Cust_AddActivity_AddActivityButton = By.xpath("//button//div//span[contains(text(),'Add Activity')]");
	public By Cust_AddActivity_ActivityDropDown = By.xpath("//div[@id='oj-select-choice-selectActivityCombo']//a");
	public By Cust_Activity_GenerateBillDetails_EffectiveDate = By.xpath("//input[@id='EffectiveDate']");
	public By Cust_Activity_GenerateBillDetails_BillDueDate = By.xpath("//input[@id='BillDueDate']");
	public By Cust_Activity_GenerateBillDetails_BillDueAmount = By.xpath("//input[@id='BillDueAmount']");
	public By Cust_Activity_GenerateBillDetails_BillGroupType = By.xpath("//div[@id='oj-select-choice-BillGroupType']//a");
	public By Cust_Activity_GenerateBillDetails_GroupCustomerGUID = By.xpath("//input[@id='GroupCustomerGUID']");
	public By Cust_Activity_GenerateBillDetails_BillEntityType = By.xpath("//div[@id='oj-select-choice-BillEntityType']//a");
	public By Cust_Activity_GenerateBillDetails_GroupCust = By.xpath("//input[@id='GroupCust']");
	public By Cust_Activity_GenerateBillDetails_OkButton = By.xpath("//button[@id='activityOkButton']");
	public By Cust_Activity_GenerateBillDetails_Process = By.xpath("//button[@title='Process']");
	public By Cust_Activity_expander001 = By.xpath("//td[@id='groupCustomerActivitiesTable:_hdrCol0_108508543']//div//div//div//a");
	public By Cust_Activity_BillingTab = By.xpath("//strong//a[@id='activitybilling']");
	public By Cust_Activity_BillingDetails_Expander = By.xpath("//div[@id='oj-collapsible-BillingDetail-header']//a");
	public By Cust_Activity_BillingDetails_Expander001 = By.xpath("//div[@id='billingDetailHeading']//a");
	public By Cust_Activity_BillingDetails_Expander002 = By.xpath("//div[@id='oj-collapsible-DynamicFieldDetail-header']//a");
	public By Cust_Activity_BillingDetails_Amount = By.xpath("//div[@id='billingDetailHeading']//div[1]//div[1]");
	public By Cust_Activity_BillingDetails_AmountValue = By.xpath("//div[@id='billingDetailHeading']//div[1]//div[2]");
	public By Cust_Activity_BillingDetails_RDT = By.xpath("//div[@id='billingDetailHeading']//div[3]//div[1]");
	public By Cust_Activity_BillingDetails_RDTValue = By.xpath("//div[@id='billingDetailHeading']//div[3]//div[2]");
	public By Cust_Activity_BillingDetails_DueDate = By.xpath("//div[@id='billingDetailHeading']//div[5]//div[1]");
	public By Cust_Activity_BillingDetails_DueDateValue = By.xpath("//div[@id='billingDetailHeading']//div[5]//div[2]");
	public By Cust_Activity_BillingDetails_EntityType = By.xpath("//div[@id='billingDetailHeading']//div[7]//div[1]");
	public By Cust_Activity_BillingDetails_EntityTypeValue = By.xpath("//div[@id='billingDetailHeading']//div[7]//div[2]");
	public By Cust_Activity_BillingDetails_Entity = By.xpath("//div[@id='billingDetailHeading']//div[9]//div[1]");
	public By Cust_Activity_BillingDetails_EntityValue = By.xpath("//div[@id='billingDetailHeading']//div[9]//div[2]");
	public By Cust_Activity_BillingDetails_Status = By.xpath("//div[@id='billingDetailHeading']//div[11]//div[1]");
	public By Cust_Activity_BillingDetails_StatusValue = By.xpath("//div[@id='billingDetailHeading']//div[11]//div[2]");
	public By Cust_Activity_BillingDetails_AllData = By.xpath("//div[@id='billingDetailHeading']");
	public By Cust_Activity_GenerateBill_BillStartDate = By.xpath("//input[@id='BillingStartDate']");
	public By Cust_Activity_GenerateBill_BillEndDate = By.xpath("//input[@id='BillingEndDate']");
	public By Cust_Activity_GenerateBill_BillOwnerType = By.xpath("//div[@id='oj-select-choice-BillGroupType']//a");
	public By Cust_Activity_GenerateBill_MinThresholdAmount = By.xpath("//input[@id='ThreshholdMinimumAmount']");
	public By Cust_Activity_GenerateBill_MaxThresholdAmount = By.xpath("//input[@id='ThreshholdMaximumAmount']");
	public By Cust_Activity_Billing_Expander = By.xpath("//div[@id='oj-collapsible-Billing-header']//a");
	public By Cust_Activity_Billing_BillRefId = By.xpath("//div[@id='billingHeading']//div[1]//div[1]");
	public By Cust_Activity_Billing_BillRefIdValue = By.xpath("//div[@id='billingHeading']//div[1]//div[2]");
	public By Cust_Activity_Billing_OwnerType = By.xpath("//div[@id='billingHeading']//div[3]//div[1]");
	public By Cust_Activity_Billing_OwnerTypeValue = By.xpath("//div[@id='billingHeading']//div[3]//div[2]");
	public By Cust_Activity_Billing_BillOwner = By.xpath("//div[@id='billingHeading']//div[5]//div[1]");
	public By Cust_Activity_Billing_BillOwnerValue = By.xpath("//div[@id='billingHeading']//div[5]//div[2]");
	public By Cust_Activity_Billing_Records = By.xpath("//div[@id='billingHeading']//div[7]//div[1]");
	public By Cust_Activity_Billing_RecordsValue = By.xpath("//div[@id='billingHeading']//div[7]//div[2]");
	public By Cust_Activity_Billing_Amount = By.xpath("//div[@id='billingHeading']//div[9]//div[1]");
	public By Cust_Activity_Billing_AmountValue = By.xpath("//div[@id='billingHeading']//div[9]//div[2]");
	public By Cust_Activity_Billing_Reconciled = By.xpath("//div[@id='billingHeading']//div[11]//div[1]");
	public By Cust_Activity_Billing_ReconciledValue = By.xpath("//div[@id='billingHeading']//div[11]//div[2]");
	public By Cust_Activity_Billing_AllData = By.xpath("//div[@id='billingHeading']");
	public By Cust_Activity_MaintainBill_BillDetailGUID01 = By.xpath("//input[@id='BillDetailGUID01']");
	public By Cust_Activity_MaintainBill_BillDetailGUID02 = By.xpath("//input[@id='BillDetailGUID02']");
	public By Cust_Activity_MaintainBill_BillGUID = By.xpath("//input[@id='BillGUID']");
	public By OIPA_UserMenu_Button = By.xpath("//div[@id='userMenuButton']");
	public By OIPA_Home_AdvancedSerach_Link = By.xpath("//span[contains(text(),'Advanced')]");
	public By OIPA_AdvancedSearch_CustomerSearch = By.xpath("//span[contains(text(),'Customer')]");
	public By OIPA_AdvancedSearch_CustomerType = By.xpath("//div[@id='oj-select-choice-clientType']//a");
	public By OIPA_AdvancedSerach_ExpandSearchFields = By.xpath("//div[@id='bsdynamicFields']//a");//aria-expanded
	public By OIPA_AdvancedSearch_GroupCustomerNumber = By.xpath("//input[@id='CustomerNumber_GroupCustomer']");
	public By OIPA_AdvancedSearch_SearchButton = By.xpath("//button[@id='bsbtnSearch']");	

    
    //*****************************Haritha Page Factory******************************************//
    
  //Added by Haritha
  //client
  	public By oipa_client_phoneclick=By.xpath("//li[@id='phone']");
  	public By oipa_client_addphone=By.xpath("//button[@id='addPhone']/div");
  	public By oipa_client_phonetype=By.xpath("//div[@id='oj-select-choice-phoneSelect']");
  	public By oipa_client_phonecountry=By.xpath("//div[@id='oj-select-choice-countrySelect']");
  	public By oipa_client_phoneNumber=By.xpath("//input[@id='PhoneNumber']");
  	public By oipa_client_phoneExtension=By.xpath("//input[@id='Extension']");
  	public By oipa_client_phonesavebutton=By.xpath("//button[@id='phoneNumberSaveButtonBottom']");
  	public By oipa_client_phonenumber_error=By.xpath("//strong[contains(text(),'Phone Number is required')]");
  	public By oipa_client_phonenumber_success=By.xpath("//strong[contains(text(),'Phone number added successfully')]");
  	public By oipa_client_activities_link=By.xpath("//li[@id='activities']");
  	public By oipa_client_addactivity=By.xpath("//span[contains(text(),'Add Activity')]");
  	public By oipa_client_activityDD=By.xpath("//div[@id='oj-select-choice-selectActivityCombo']");
  	public By oipa_client_activity_OKNavigate=By.xpath("//button[@id='activityOkNavigateButton']");
  	public By oipa_client_activity_process=By.xpath("//button[@title='Process']");
  	public By oipa_client_phonenumber_column=By.xpath("//tbody/tr/td[5]");
  	public By oipa_client_phone_countrycode=By.xpath("//input[@id='CountryCode']");
  	public By oipa_client_addressline1=By.xpath("//input[@id='Address_AddressLine1']");
  	public By oipa_client_addresscity=By.xpath("//input[@id='Address_City']");
  	public By oipa_client_addresszip=By.xpath("//input[@id='Address_PostalID']");
  	public By oipa_client_addressReturnedMail=By.xpath("//input[@id='Address_ReturnedMailDate']");
  	public By oipa_client_policyrolestab=By.xpath("//div[@id='policyrolestab']");
  	public By oipa_client_rolestab_firstrcolumn=By.xpath("//table/thead/tr/th[2]");
  	public By oipa_client_rolestab_secondcolumn=By.xpath("//table/thead/tr/th[3]");
  	public By oipa_client_rolestab_thirdcolumn=By.xpath("//table/thead/tr/th[4]");
  	public By oipa_client_rolestab_firstcolumn_value=By.xpath("//table/tbody/tr/td[2]");
  	public By oipa_client_policyrolestab_secondcolumn_value=By.xpath("//table/tbody/tr/td[3]");
  	public By oipa_client_policyrolestab_thirdcolumn_value=By.xpath("//table/tbody/tr/td[4]");
  	public By oipa_client_agreementrolestab=By.xpath("//li[@aria-controls='agreementrolestab']");
  	public By oipa_client_classmembersrolestab=By.xpath("//li[@aria-controls='classmemberrolestab']");
  	public By oipa_client_Activity_effectivedate=By.xpath("//input[@id='EffectiveDate']");
  	public By oipa_client_activity_cancel_button=By.xpath("//button[@id='activityCancelButton']");
  	public By oipa_client_tab_haritha=By.xpath("//a[@id='clientTab']");
  	public By oipa_client_fetchedrows=By.xpath("//div[@id='clientActivitiesTablefetchrows']");
  	public By oipa_client_processAll_button=By.xpath("//button[@id='processAll']");
  	public By oipa_client_NewGB_Activity_Billing_StartDate=By.xpath("//input[@id='BillingStartDate']");
  	public By oipa_client_NewGB_Activity_BillingEndDate=By.xpath("//input[@id='BillingEndDate']");
  	public By oipa_client_NewGB_Activity_GroupCustomer_number=By.xpath("//input[@id='GroupCustomer']");
  	public By oipa_client_NewGB_Activity_ThreshholdMaximumAmount=By.xpath("//input[@id='ThreshholdMaximumAmount']");
  	public By oipa_client_NewGB_Activity_Billingtab=By.xpath("//a[@id='activitybilling']");
  	public By oipa_client_NewGB_Activity_Billingtab_customername=By.xpath("//div[@id='billingHeadingWithoutCollapsible']//span[8]");
  	public By oipa_client_Groupname=By.xpath("//input[@id='CompanyName']");
  	public By oipa_client_Groupnumber=By.xpath("//input[@id='GroupNumber']");
  	public By oipa_client_TypeDD=By.xpath("//div[@id='oj-select-choice-clientType']");
  	
  	//Unifiedsearch
  	public By oipa_searchmenu=By.xpath("//div[@id='oj-select-choice-Menu_Search']");
  	public By oipa_serch_input=By.xpath("//input[@id='unifiedsearchinput']");
  	public By oipa_unifiedsearch=By.xpath("//button[@id='unifiedSearchIcon']");
  	//Policy
  	public By oipa_policy_FPP_policynumber=By.xpath("//input[@id='PolicyNumber']");
  

  	public By oipa_policy_RolesMenuclick=By.xpath("//li[@id='role']");
  	public By oipa_policy_Roles_newclient=By.xpath("//span[contains(text(),'New Client')]");
  	public By oipa_policy_rolesDD=By.xpath("//div[@id='oj-select-choice-singleselect']");
  	public By oipa_policy_roleviewDD=By.xpath("//div[@id='oj-select-choice-selectView']");
  	public By oipa_policyroles_newclient_add=By.xpath("//button[@id='bottomclientAdd']");
  	public By oipa_policyroles_policyrolerecord=By.xpath("//table/tbody/tr");
  	public By oipa_policyroles_clientdata=By.xpath("//tbody/tr/td[3]");
  	public By oipa_policymenu=By.xpath("//li[@id='policydetail']");
  	public By oipa_policyText=By.xpath("//div[@id='leftNavigationContent']//h3");
  	public By oipa_clientText=By.xpath("//div[@id='leftNavigationContent']//h3//span[3]");
  	public By oipa_policy_activity_failuremessage_returntoapp=By.xpath("//div[@id='failureMessage']//button");
  	public By oipa_policy_activity_failuremessage=By.xpath("//div[@id='failureMessage']//span[2]");
  	public By oipa_policy_activity_OK_button=By.xpath("//button[@id='activityOkButton']");
  	
  	//Customer
  	public By oipa_cx_cxname=By.xpath("//input[@id='CompanyName']");
  	public By oipa_cx_cxnumber=By.xpath("//input[@id='CustomerNumber']");
  	public By oipa_cx_taxid=By.xpath("//input[@id='TaxID']");
  	public By oipa_cx_HierarchyRelationshipDD=By.xpath("//div[@id='oj-select-choice-HierarchyRelationship']");
  	public By oipa_cx_PrimaryEnrollmentRelationshipDD=By.xpath("//div[@id='oj-select-choice-PrimaryEnrollmentRelationship']");
  	public By oipa_cx_EnrollmentClassGroupDD=By.xpath("//div[@id='oj-select-choice-EnrollmentClassGroup']");
  	public By oipa_cx_cxlegalname=By.xpath("//input[@id='CustomerLegalName']");
  	public By oipa_cx_savebutton=By.xpath("//button[@id='GroupCustomerSaveButtonBottom']");
  	public By oipa_cxname_Text=By.xpath("//div[@id='leftNavigationContent']//h3//span[4]");
  	public By oipa_cx_AgreementMenuclick=By.xpath("//li[@id='agreement']");
  	public By oipa_cx_contractadd=By.xpath("//button[@id='addNewNodeCNTR']");
  	public By oipa_cx_contract=By.xpath("//li[@id='CNTR']");
  	public By oipa_cx_agreement_typeDD=By.xpath("//div[@id='oj-select-choice-TypeCode']");
  	public By oipa_cx_agreementname=By.xpath("//input[@id='AgreementName']");
  	public By oipa_cx_statusDD=By.xpath("//div[@id='oj-select-choice-StatusCode']");
  	public By oipa_cx_agreement_savebutton=By.xpath("//button[@id='agreementDetailSaveBottom']");
  	public By oipa_cx_agreement_roletab=By.xpath("//a[@id='agreementrole']");
  	public By oipa_cx_agreement_roletab_findclient=By.xpath("//button[@id='findClient']");
  	public By oipa_cx_agreement_roletab_newclient=By.xpath("//button[@id='addClient']");
  	public By oipa_cx_agreement_roletab_findcx=By.xpath("//button[@id='findCustomer']");
  	public By oipa_cx_addagreementroletypeDD=By.xpath("//div[@id='oj-select-choice-agreementRoleAddList']");
  	public By oipa_cx_agreementrole_okbutton=By.xpath("//div[@class='oj-flex-item oi-margin-right-small']//button");
  	public By oipa_cx_aggreementrole_detailsection_expandableicon=By.xpath("//a[@role='button']");
  	public By oipa_cx_aggreementrole_detailsection_heading=By.xpath("//h4[contains(text(),'Detail Section 1')]");
  	public By oipa_cx_agreement_roletab_IdentifierNumber=By.xpath("//input[@id='IdentifierNumber']");
  	public By oipa_cx_agreement_roletab_AgreementRoleType=By.xpath("//input[@id='AgreementRoleType']");
  	public By oipa_cx_agreement_roletab_AgreementRoleStatus=By.xpath("//div[@id='oj-select-choice-AgreementRoleStatus']");
      public By oipa_cx_agreement_roletab_table_expandableicon=By.xpath("//div[@class='oj-rowexpander-touch-area']//a");
  	public By oipa_cx_agreement_roletab_savebutton=By.xpath("//button[@id='agreementRoleSave']");
  	public By oipa_cx_agreement_roletab_AgreementRoleStatusDD_list=By.xpath("//ul[@id='oj-listbox-results-AgreementRoleStatus']//li//div");
  	public By oipa_cx_agreement_roletab_table_Typecolumn=By.xpath("//table/thead/tr/th[3]");
  	public By oipa_cx_agreement_roletab_table_namecolumn=By.xpath("//table/thead/tr/th[4]");
  	public By oipa_cx_agreement_roletab_table_statuscolumn=By.xpath("//table/thead/tr/th[5]");
      public By oipa_cx_agreement_roletab_table_actioncolumn=By.xpath("//table/thead/tr/th[6]");
       public By oipa_cx_agreement_roletab_table_Typecolumnvalue=By.xpath("//tbody/tr/td[3]");
  	public By oipa_cx_agreement_roletab_table_namecolumnvalue=By.xpath("//tbody/tr/td[4]");
  	public By oipa_cx_agreement_roletab_table_statuscolumnvalue=By.xpath("//tbody/tr/td[5]");
       public By oipa_cx_agreement_roletab_table_hamburgermenuvalue=By.xpath("//div[@class='oi-text-align-center']//span");
  	public By oipa_cx_agreement_roletab_hamburger_gotoclient=By.xpath("//li[@id='goToClient']");
  	public By oipa_cx_aggreementrole_addressline1=By.xpath("//input[@id='AddressLine1']");
  	public By oipa_cx_aggreementrole_addresscity=By.xpath("//input[@id='City']");
  	public By oipa_cx_aggreementrole_addresszip=By.xpath("//input[@id='PostalID']");
  	public By ooipa_cx_aggreementrole_addressReturnedMail=By.xpath("//input[@id='ReturnedMailDate']");
  	public By oipa_cx_agreement_role_findclientsearch_firstname=By.xpath("//input[@id='FirstName_Client']");
  	public By oipa_cx_agreement_role_findclientsearch_button=By.xpath("//button[@id='buttonSubmit']//span");
  	public By oipa_cx_aggreementrole_findclientserach_firstrow=By.xpath("//tbody/tr");
  	public By ooipa_cx_aggreementrole_findclientserach_OKbutton=By.xpath("//span[contains(text(),'OK')]");
  	public By ooipa_cx_aggreementrole_findclientserach_clientdeatils=By.xpath("//tbody/tr/td[3]//span");
  	public By oipa_cx_relationship_leftnavigation_click=By.xpath("//li[@id='relationships']");
      public By oipa_cx_relationship_add_relationshiptypeDD=By.xpath("//div[@id='oj-select-choice-relationShipType']");
  	public By oipa_cx_relationship_add_relationship_button=By.xpath("//span[@id='plusIcon']");
  	public By oipa_cx_relationship_effectivedate=By.xpath("//input[@id='effective']");
  	public By oipa_cx_relationship_clienticon=By.xpath("//span[@class='oi-icon-person-plus']");
  	public By oipa_cx_relationship_addclient_link=By.xpath("//a[@id='newClient']");
  	public By oipa_cx_relationship_client_savebutton=By.xpath("//button[@id='clientAdd']");
  	public By oipa_cx_relationship_savebutton=By.xpath("//div[@class='oj-md-text-align-end']//button");
  	public By oipa_cx_relationship_added_message=By.xpath("//strong[contains(text(),'Relationship added successfully')]");
  	public By oipa_cx_agreement_productslink=By.xpath("//a[@id='agreementproducts']");
  	public By oipa_cx_agreement_addproduct_button=By.xpath("//button[@id='addProductButton']//span");
  	public By oipa_cx_agreement_GroupPrototypeProduct=By.xpath("//span[contains(text(),'Group Prototype Product')]");
  	public By oipa_cx_agreement_product_okbutton=By.xpath("//button[@id='addProductOkButton']");
  	public By oipa_cx_agreement_product_status=By.xpath("//div[@id='oj-select-choice-BusinessStatusCode']");
  	public By oipa_cx_agreement_product_EffectiveDate=By.xpath("//input[@id='EffectiveFrom']");
  	public By oipa_cx_agreement_product_savebutton=By.xpath("//button[@id='productSaveButton']");
  	public By oipa_cx_product_added_message=By.xpath("//strong[contains(text(),'Product added successfully')]");
  	
  	public By oipa_cx_agreement_planslink=By.xpath("//a[@id='agreementplans']");
  	public By oipa_cx_agreement_addplanDD=By.xpath("//div[@id='oj-select-choice-addnewPlanSelect']");
  	public By oipa_cx_agreement_plan_createbutton=By.xpath("//button[@id='addNewPlanButton']");
  	public By oipa_cx_agreement_plan_companyDD=By.xpath("//div[@id='oj-select-choice-Company']//a");
  	public By oipa_cx_agreement_plan_productDD=By.xpath("//div[@id='oj-select-choice-Product']//a");
  	public By oipa_cx_agreement_Planname=By.xpath("//input[@id='PlanName']");
  	public By oipa_cx_agreement_plan_statusDD=By.xpath("//div[@id='oj-select-choice-PlanStatus']");
  	public By oipa_cx_product_plan_currencyDD=By.xpath("//div[@id='oj-select-choice-DefaultCurrencyCode']");
  	public By oipa_cx_product_plan_AllocationMethodDD=By.xpath("//div[@id='oj-select-choice-PlanAllocationMethodCode']");
  	public By oipa_cx_product_plan_MarketMakerDD=By.xpath("//div[@id='oj-select-choice-MarketMakerGUID']");
  	public By oipa_cx_agreement_Plansegment_button=By.xpath("//button[@aria-label='Add Plan Segement']");
  	public By oipa_cx_agreement_plansegmentnameDD=By.xpath("//div[@id='oj-select-choice-SegmentName']");
  	public By oipa_cx_agreement_plansegmentname=By.xpath("//input[@id='PlanSegmentName']");
  	public By oipa_cx_agreement_plansegment_savebutton=By.xpath("//div[@class='oi-inline oi-header-button-align']//button");
  	public By oipa_cx_agreement_plan_savebutton=By.xpath("//div[@class='oi-text-align-right oi-padding-bottom-small']//button");
  	public By oipa_cx_final_plan_savebutton=By.xpath("//button[@id='btnSavePlan']");
  	public By oipa_cx_plan_added_message=By.xpath("//strong[contains(text(),'Plan added successfully')]");
  	public By oipa_cx_agreement_Plantable_hamburgericon=By.xpath("//tbody/tr/td[7]");
  	public By oipa_cx_agreement_Plantable_hamburgericon_gotoplan=By.xpath("//li[@id='goToPlan']");
  	public By oipa_cx_plan_process1button=By.xpath("//button[@id='submit1']");
  	public By oipa_cx_plan_process2button=By.xpath("//button[@title='Process']");
  	public By oipa_cx_agreement_expandicon_contract=By.xpath("//li[@id='CNTR']//ins");
  	public By oipa_cx_agreement_classgroups_link=By.xpath("//a[@id='agreementclassgroups']");
  	public By oipa_cx_agreement_classgroupsDD=By.xpath("//div[@id='oj-select-choice-classGroupAddSelect']");
  	public By oipa_cx_agreement_classgroups_createbutton=By.xpath("//button[@id='classGroupAddButton']");
  	public By oipa_cx_agreement_classgroups_typeDD=By.xpath("//div[@id='oj-select-choice-TypeCode']");
  	public By oipa_cx_agreement_classgroups_classgroupname=By.xpath("//input[@id='ClassGroupName']");
  	public By oipa_cx_agreement_classgroups_description=By.xpath("//input[@id='ClassGroupDescription']");
  	public By oipa_cx_agreement_classgroups_effectivedate=By.xpath("//input[@id='EffectiveFrom']");
  	public By oipa_cx_agreement_addclasslink=By.xpath("//a[@id='addClassGroupTab']");
  	public By oipa_cx_agreement_classname=By.xpath("//input[@id='ClassName']");
  	public By oipa_cx_agreement_class_description=By.xpath("//input[@id='ClassDescription']");
  	
  	public By oipa_cx_agreement_class_savebutton=By.xpath("//div[@class='oj-sm-float-end oi-inline oi-header-button-align oj-padding']//button");
  	public By oipa_cx_agreement_classgroup_statusDD=By.xpath("//div[@id='oj-select-choice-agStatusCode']");
  	public By oipa_cx_agreement_classgroup_savebutton=By.xpath("//button[@id='agreementClassgroupSave']");
  	//public By oipa_cx_classgroup_added_message=By.xpath("//strong[contains(text(),'Plan added successfully')]");
  	public By oipa_cx_agreement_classgrouptable_hamburgericon_gotoclassgroup=By.xpath("//li[@id='gotoClassgroup']");
  	public By oipa_cx_agreement_classgrouptable_hamburgericon=By.xpath("//tbody/tr/td[7]//span");
  	public By oipa_classgrouptable_childexpandicon=By.xpath("//div[@class='oj-rowexpander-icon-spacer']//a");
  	public By oipa_classgrouptable_parentexpandicon=By.xpath("//div[@class='oj-collapsible-header']//a");
  	public By oipa_cx_classgrouptable_classes_tab=By.xpath("//a[@id='classes']");
  	public By oipa_cx_classgrouptable_mousehover_class=By.xpath("//div[@class='oi-treenode']");
  	public By oipa_cx_class_plusicon=By.xpath("//div[@class='oi-treenode']//button[2]");
  	public By oipa_cx_classgrouptable_classokbutton=By.xpath("//button[@id='okButton']");
  	public By oipa_cx_classgroup_ClassRuleVariables_link=By.xpath("//a[@id='classgrouprules']");
  	public By oipa_cx_ClassRuleVariables_plusicon=By.xpath("//button[@id='addVariableButtonclassGroupRules']//span");
  	public By oipa_cx_ClassRuleVariables_typeDD=By.xpath("//div[@id='oj-select-choice-mathTableNameSelect1']");
  	public By oipa_cx_ClassRuleVariables_name=By.xpath("//input[@id='mathTableNameInput0']");
  	public By oipa_cx_ClassRuleVariables_datatypeDD=By.xpath("//div[@id='oj-select-choice-mathTableNameSelect2']");
  	public By oipa_cx_ClassRuleVariables_Text=By.xpath("//input[@id='mathTableNameInput3']");
  	public By oipa_cx_ClassRuleVariables_savebutton=By.xpath("//button[@id='saveRuleButton']");
  	public By oipa_cx_class_parentclassclick=By.xpath("//div[@id='classtreestructure']//ul//button");
  	public By oipa_cx_class_childclassclick=By.xpath("//div[@id='classtreestructure']//ul/li/ul//button");
  	public By oipa_cx_Class_plancovearages=By.xpath("//a[@id='classsubplan']");
  	public By oipa_cx_Class_associate_button=By.xpath("//a[@id='plancoverage']");
  	public By oipa_cx_Class_associate_doublearrow=By.xpath("//button[@id='addALLToTarget']");
  	public By  oipa_cx_Class_associate_effectivedate=By.xpath("//input[@id='associationEffectiveDate']");
  	public By  oipa_cx_Class_plancovearag_savebutton=By.xpath("//button[@id='savePlanCoverageButton']");
  	public By oipa_cx_Class_child_classvariable_addbutton=By.xpath("//button[@id='addVariableButtonclassVariable']");
  	public By oipa_cx_Class_membershiprules_link=By.xpath("//a[@id='classrule']");
  	public By oipa_cx_Class_membershiprules_childselection=By.xpath("//ul[@id='sourcelistview']//li[2]");
  	public By oipa_cx_Class_membershiprules_arrow=By.xpath("//button[@id='classRulesShuttle']");
  	public By oipa_cx_Class_membershiprules_savebutton=By.xpath("//button[@id='saveButton']");
  	public By oipa_cx_classgroup_processbutton=By.xpath("//button[@id='submit1']");
  	public By oipa_cx_relationship_expandicon=By.xpath("//div[@id='relationshipsContainer']/div//a");
  	public By oipa_cx_relationship_processbutton=By.xpath("//button[@title='Submit']");
  	public By oipa_cx_relationship_hamburger=By.xpath("//span[@id='relationshipHamburger0']");
  	public By oipa_cx_relationship_hamburger_gotoclient=By.xpath("//ul[@id='goToClientMenu']");
  	public By oipa_cx_evaluatemembership_activity_EffectiveUntil=By.xpath("//input[@id='EffectiveTo']");
  	public By oipa_cx_classgroup_child_ClassRuleVariables_link=By.xpath("//a[@id='classvariable']");
  	public By oipa_cx_Relationships_alert_ok_button=By.xpath("//button[@id='warningokButton']//span");
  	public By oipa_cx_plan_alert_ok_button=By.xpath("//span[contains(text(),'OK')]");
  	public By oipa_client_activity_row2_expander=By.xpath("//tbody/tr[2]//td[2]//a");
  	public By oipa_client_activity_row1_expander=By.xpath("//tbody/tr//td[2]//a");
  	public By oipa_activity_classmembership_tab=By.xpath("//a[@id='activityclassmembership']");
  	public By oipa_classmembership_tab_firstrowvalue=By.xpath("//tbody/tr/td[2]/span");
  	public By oipa_classmembership_tab_secondrowvalue=By.xpath("//tbody/tr[2]/td[2]/span");
  	public By oipa_classmembership_tab_thirdvalue=By.xpath("//tbody/tr[3]/td[2]/span");
  	public By oipa_clinet_enrollemnt_leftlink=By.xpath("//li[@id='enrollment']");
  	public By oipa_client_enrollemnt_checkbox=By.xpath("//tbody/tr/td[2]/input");
  	public By oipa_enrollemnt_policyname=By.xpath("//input[@id='NewPolicyName']");
  	public By oipa_enrollement_savebutton=By.xpath("//button[@id='saveButton']");
  	public By oipa_enrollement_date=By.xpath("//tbody/tr[2]//td[6]");
  	public By oipa_activities_link=By.xpath("//li[@id='activities']");
  	public By oipa_enrollementprototype_activity=By.xpath("//tbody/tr//td[3]");
  	public By oipa_enrollementprototype_status=By.xpath("//tbody/tr//td[6]");
  	public By oipa_enroll_button=By.xpath("//div[@class='oj-button-label']//span[contains(text(),'Enroll')]");
  	
  	//List Bill
  	public By oipa_listbill_leftlink=By.xpath("//li[@id='listbill']");
  	public By oipa_listbill_addbutton=By.xpath("//button[@id='addListBill']");
  	public By oipa_listbill_policyNumber=By.xpath("//input[@id='input']");
  	public By oipa_listbill_dueDate=By.xpath("//input[@id='DueDate']");
  	public By oipa_listbill_amount=By.xpath("//input[@id='AmountDue']");
  	public By oipa_listbill_save=By.xpath("//button[@id='save_button']");
  	public By oipa_listbill_policynumber_errormessage=By.xpath("//strong[contains(text(),'Policy already exists in the List Bill.')]");
  	public By oipa_listbill_policynumber_updatemessage=By.xpath("//strong[contains(text(),'List bill modified successfully')]");
  	public By oipa_listbill_delete=By.xpath("//tbody/tr/td[8]//span");
  	public By oipa_listbill_delete_popup_ok=By.xpath("//button[@id='ok_popup_button']");
  	public By oipa_listbill_1strow_expand=By.xpath("//tbody/tr/td[2]//a");
  	
  	public By oipa_UpdateListbill_activity_firstname=By.xpath("//input[@id='FirstName']");
  	public By oipa_UpdateListbill_activity_amount=By.xpath("//input[@id='Amount']");
  	public By oipa_UpdateListbill_activity_Due=By.xpath("//input[@id='Due']");
  	public By oipa_listbill_1strow_amount_value=By.xpath("//tbody/tr/td[5]//span");
  	public By oipa_listbill_1strow_dueDate_value=By.xpath("//tbody/tr/td[7]//span");
  	public By oipa_listbill_1strow_nodata_display=By.xpath("//tbody/tr/td");
  	public By oipa_AddListbill_activity_policynumber=By.xpath("//input[@id='PolicyNumber']");
  	public By oipa_listbill_1strow_policynumber_value=By.xpath("//tbody/tr/td[3]//span");

  	//********************************Ganesh Balasti Page Factory******************************//
  	
 // Added by Ganesh Balasti
 // OIPA Home Page
 	public By oipa_search_input_field = By.id("unifiedsearchinput");
 	public By oipa_search_button_icon = By
 			.xpath("//span[@class='oj-button-icon oj-start oj-fwk-icon oj-fwk-icon-magnifier']");
 	public By oipa_search_menu_dropdown = By.id("ojChoiceId_Menu_Search_selected");
 	public By oipa_home_page = By.id("ojChoiceId_Menu_Search_selected");
 	
 	// Added by Ganesh Balasti
 	// Client Screen PF
 	//public By oipa_client_tab = By.id("historyTab");
 	public By oipa_client_tab = By.id("clientTab");
 	public By oipa_client_history_tab = By.id("historyTab");
 	public By oipa_client_last_name = By.id("LastName");
 	public By oipa_client_middle_initial = By.id("MiddleInitial");
 	public By oipa_client_save_button = By.id("saveButton");
 	public By oipa_client_MaritialDD = By.xpath("//div[@id='ojChoiceId_MaritalStatus']//a");
 	public By oipa_client_history_expander = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[2]");
 	public By oipa_client_history_client_number = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[3]");
 	public By oipa_client_history_operation = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[4]");
 	public By oipa_client_history_type = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[5]");
 	public By oipa_client_history_date = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[7]");

 	public By oipa_client_history_child_1_2 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[1]//td[2]");
 	public By oipa_client_history_child_1_3 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[1]//td[3]");
 	public By oipa_client_history_child_1_4 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[1]//td[4]");

 	public By oipa_client_history_child_2_2 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[2]//td[2]");
 	public By oipa_client_history_child_2_3 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[2]//td[3]");
 	public By oipa_client_history_child_2_4 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[2]//td[4]");

 	// GC Screen PF
 	public By oipa_gc_customer_tab = By.id("groupCustomerTab");
 	public By oipa_gc_history_tab = By.id("groupCustomerHistoryTab");
 	public By oipa_gc_taxid = By.id("TaxID");
 	public By oipa_gc_customernumber = By.id("CustomerNumber");
 	public By oipa_gc_save_button = By.xpath("//span[contains(text(),'Save')]");

 	public By oipa_gc_history_expander = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[2]");
 	public By oipa_gc_history_gc_number = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[3]");
 	public By oipa_gc_history_operation = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[4]");
 	public By oipa_gc_history_type = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[5]");
 	public By oipa_gc_history_date = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[7]");

 	public By oipa_gc_history_child_1_2 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[1]//td[2]");
 	public By oipa_gc_history_child_1_3 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[1]//td[3]");
 	public By oipa_gc_history_child_1_4 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[1]//td[4]");

 	public By oipa_gc_history_child_2_2 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[2]//td[2]");
 	public By oipa_gc_history_child_2_3 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[2]//td[3]");
 	public By oipa_gc_history_child_2_4 = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr[2]//td[4]");

 	// GC Address Screen PF
 	public By oipa_gc_address = By.id("groupcustomeraddress");
 	public By oipa_gc_address_expander = By
 			.xpath("//a[@class='oj-component-icon oj-clickable-icon-nocontext oj-rowexpander-expand-icon']");
 	public By oipa_gc_add_address = By.id("addAddress");
 	public By oipa_gc_select_address_type = By.id("ojChoiceId_addressRoleType_selected");
 	public By oipa_gc_select_address_country = By.id("ojChoiceId_addressCountryType_selected");
 	public By oipa_gc_address_line1 = By.id("AddressLine1");
 	public By oipa_gc_address_line2 = By.id("AddressLine2");
 	public By oipa_gc_address_line3 = By.id("AddressLine3");
 	public By oipa_gc_address_line4 = By.id("AddressLine4");
 	public By oipa_gc_address_city = By.id("City");
 	public By oipa_gc_address_state = By.id("ojChoiceId_StateCode_selected");
 	public By oipa_gc_address_zip = By.id("PostalID");
 	public By oipa_gc_address_email = By.id("Email");
 	public By oipa_gc_address_fax = By.id("FaxNumber");
 	public By oipa_gc_address_effective_date = By.id("EffectiveDate");
 	public By oipa_gc_address_expiration_date = By.id("ExpirationDate");
 	public By oipa_gc_address_letter_mail_date = By.id("LetterMailDate");
 	public By oipa_gc_address_save_button = By.id("CustomerAddressSaveButtonBottom");
 	public By oipa_gc_customer_history_tab = By.id("customerHistoryTab");
 	public By oipa_gc_customer_address_tab = By.id("customerAddressTab");
 	public By oipa_gc_customer_address_delete = By
 			.xpath("//span[@class='oi-icon-delete' and not(contains(@style,'none'))] ");
 	public By oipa_gc_customer_address_delete_ok = By.id("deleteOkButton");

 	// ReusableFunction-data
 	public By oipa_history_expander = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[2]");
 	public By oipa_history_client = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[3]");
 	public By oipa_history_operation = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[4]");
 	public By oipa_history_details_col = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[6]");
 	public By oipa_history_type = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[5]");
 	public By oipa_history_date = By.xpath("//table[@id=('HistoryTable')]//tbody//tr[1]//td[7]");
 	public By oipa_history_details = By.xpath("//table[@id=('HistoryDetailsTable')]//tbody//tr");
 	public By oipa_history_tab = By.xpath("//*[contains(text(),'History')]");

 	// Client Address Screen
 	public By oipa_cl_address = By.id("address");
 	public By oipa_cl_address_expander = By
 			.xpath("//a[@class='oj-component-icon oj-clickable-icon-nocontext oj-rowexpander-expand-icon']");
 	public By oipa_cl_add_address = By.id("addAddress");
 	public By oipa_cl_select_address_type = By.id("ojChoiceId_addressRoleType_selected");
 	public By oipa_cl_select_address_country = By.id("ojChoiceId_addressCountryType_selected");
 	public By oipa_cl_address_line1 = By.id("AddressLine1");
 	public By oipa_cl_address_line2 = By.id("AddressLine2");
 	public By oipa_cl_address_line3 = By.id("AddressLine3");
 	public By oipa_cl_address_line4 = By.id("AddressLine4");
 	public By oipa_cl_address_city = By.id("City");
 	public By oipa_cl_address_state = By.id("ojChoiceId_StateCode_selected");
 	public By oipa_cl_address_zip = By.id("PostalID");
 	public By oipa_cl_address_email = By.id("Email");
 	public By oipa_cl_address_fax = By.id("FaxNumber");
 	public By oipa_cl_address_effective_date = By.id("EffectiveDate");
 	public By oipa_cl_address_expiration_date = By.id("ExpirationDate");
 	public By oipa_cl_address_letter_mail_date = By.id("LetterMailDate");
 	public By oipa_cl_address_save_button = By.xpath("//*[contains(text(),'Save')]");
 	public By oipa_cl_customer_address_history = By.id("historyTab");
 	public By oipa_cl_customer_address_tab = By.id("addressTab");
 	public By oipa_cl_customer_address_delete = By
 			.xpath("//span[@class='oi-icon-delete' and not(contains(@style,'none'))] ");
 	public By oipa_cl_customer_address_delete_ok = By.id("deleteOkButton");

 	// Policy Screen

 	public By oipa_policy_policy_number = By.id("PolicyNumber");
 	public By oipa_policy_company = By.id("ojChoiceId_CompanyGUID_selected");
 	public By oipa_policy_prodcut = By.id("oj-select-choice-Product");
 	public By oipa_policy_plan = By.id("oj-select-choice-PlanGUID");
 	public By oipa_policy_policy_name = By.id("PolicyName");
 	public By oipa_policy_policy_issue_date = By.id("PolicyIssueDate");
 	public By oipa_policy_vda_policy_number = By.xpath("//div[contains(text(),'VDA')]");
 	public By oipa_policy_save_button = By.xpath("//button[@id='policySaveButtonTop']");
 	public By oipa_policy_role = By.id("role");
 	public By oipa_policy_role_new_client = By.xpath("//span[contains(text(),'New Client')]");
 	public By oipa_policy_role_types = By.id("ojChoiceId_multiselect");
 	public By oipa_policy_role_single_type = By.id("oj-select-choice-singleselect");
 	public By oipa_policy_role_find_client = By.xpath("//span[text()='Find Client']");
 	public By oipa_policy_role_find_firstname = By.id("FirstName_Client");
 	public By oipa_policy_client_search = By.id("buttonSubmit");
 	public By oipa_policy_role_found_client = By.xpath("//table[@id='searchresultstable']/tbody[1]/tr[1]/td[3]");
 	public By oipa_policy_add_client = By.xpath("//span[text()='Add']");
 	
 	//Policy Requirement Screen
 	public By oipa_policy_req_sidelist = By.xpath("(//span[text()='Requirements'])[2]");
 	public By oipa_policy_add_req = By.xpath("(//span[text()='Add Requirement'])[2]");
 	public By oipa_policy_reqDD = By.id("oj-inputsearch-choice-inputRequirementSearch");
 	public By oipa_policy_req_vendor_name = By.id("VendorName");
 	public By oipa_policy_req_vendor_phone = By.id("Phone");
 	public By oipa_policy_req_ok = By.id("requirementOkButton");
 	public By oipa_policy_req_history = By.id("policyRequirementHistoriesTab");
 	public By oipa_policy_req_allreqs= By.id("policyRequirementTab");
 	public By oipa_policy_req_expander = By.xpath("(//div[@class='oj-rowexpander-touch-area']//a)[1]");
 	
 	
 	
 	
 	public By oipa_policy_add_client_in_role = By.id("bottomclientAdd");
 	public By oipa_policy_role_client_adrress_type = By.xpath("//div[@id='oj-select-choice-addressRoleType']");
 	public By oipa_policy_role_client_adrress_country = By
 			.xpath("//span[@id='ojChoiceId_addressCountryType_selected']");
 	public By oipa_policy_role_client_adrress_line1 = By
 			.xpath("//*[@class='oj-flex oj-flex-items-pad']//input[@id='Address_AddressLine1']");
 	public By oipa_policy_role_client_adrress_line2 = By.xpath("//input[@id='Address_AddressLine2']");
 	public By oipa_policy_role_client_adrress_city = By.xpath("//input[@id='Address_City']");
 	public By oipa_policy_role_client_adrress_zip = By.xpath("//input[@id='Address_PostalID']");

 	public By oipa_policy_role_client_history = By.id("historyTab");
 	public By oipa_policy_role_client_tab = By.id("rolesTab");
 	public By oipa_policy_role_fourlines = By.xpath("(//div[@class='oi-icon-func_fourhorizontallines'])[2]");
 	public By oipa_policy_role_hover_menu = By.xpath("//tbody//tr[1]//td[8]//span");
 	public By oipa_policy_role_delete = By.id("deleteRole");

 	public By oipa_role_delete_ok = By.id("rolesOkButton");

 	// Suspense PF

 	public By oipa_suspense_policy_num = By.id("PolicyNumber");
 	public By oipa_suspense_effective_date = By.id("EffectiveDate");
 	public By oipa_suspense_firstname = By.id("FirstName");
 	public By oipa_suspense_lastname = By.id("LastName");
 	public By oipa_suspense_acc_num = By.id("AccountNumber");
 	public By oipa_suspense_check_num = By.id("CheckNumber");
 	public By oipa_suspense_bank_name = By.id("BankName");
 	public By oipa_suspense_bank_num = By.id("BankNumber");
 	public By oipa_suspense_save = By.id("bottomSave");
 	public By oipa_suspense_id = By.xpath("//h3[@class='oi-inline']");
 	public By oipa_suspense_history_tab = By.id("historyTab");
 	public By oipa_suspense_suspense_tab = By.id("suspenseTab");
 	public By oipa_suspense_suspense_delete = By.id("deleteButton");
 	public By oipa_suspense_suspense_delete_ok = By.id("deleteOkButton");

 	// Allocation PF

 	public By oipa_allocation_sidelist = By.id("allocations");
 	public By oipa_allocation_type = By.id("ojChoiceId_newAllocationDropdown");
 	public By oipa_allocation_click_to_add = By.xpath("//*[@id='ojChoiceId_newAllocationDropdown']//following::button");
 	public By oipa_allocation_fund_type = By.xpath("//span[contains(text(),'Select Fund')]");
 	public By oipa_allocation_saved_fund = By.xpath("(//span[@class='oj-select-chosen'])[5]");

 	public By oipa_allocation_fund_percent1 = By
 			.xpath("(//input[contains(@class,'oi-text-align-right oj-inputnumber-input')])[1]");
 	public By oipa_allocation_fund_percent2 = By
 			.xpath("(//input[contains(@class,'oi-text-align-right oj-inputnumber-input')])[2]");
 	public By oipa_allocation_save = By.xpath("//span[contains(text(),'Save')]");
 	public By oipa_allocation_history_tab = By.id("policyAllocationHistoriesTab");
 	public By oipa_allocation_tab = By.id("policyAllocationTab");
 	public By oipa_allocation_delete = By.className("oi-icon-delete");
 	public By oipa_allocation_delete_ok = By.xpath("//span[text()='OK']");

 	// Agreement pf
 	public By oipa_agreement_sidelist = By.id("agreement");
 	public By oipa_agreement_bl = By.id("BL");
 	public By oipa_agreement_new_bl = By.id("addNewNodeBL");
 	public By oipa_agreement_type = By.id("ojChoiceId_TypeCode_selected");
 	public By oipa_agreement_name = By.id("AgreementName");
 	public By oipa_agreement_status = By.id("ojChoiceId_StatusCode_selected");
 	public By oipa_agreement_agreementnumber = By.id("AgreementNumber");
 	public By oipa_agreement_experiencenumber = By.id("ExperienceNumber");
 	public By oipa_agreement_acc_name = By.id("BankAccountName-0");
 	public By oipa_agreement_routing_num = By.id("BankRoutingNumber-0");
 	public By oipa_agreement_acc_num = By.id("BankAccountNumber-0");
 	public By oipa_agreement_save = By.xpath("//span[text()='Save']");
 	public By oipa_agreement_history = By.id("agreementHistoryTab");
 	public By oipa_agreement_agreements = By.id("agreementTab");
 	public By oipa_agreement_delete = By.xpath("//button[contains(@class,'oi-icon-delete oi-icon-pointer')]");
 	public By oipa_agreement_delete_ok = By.xpath("//span[text()='OK']");

 	// Dataintake pf
 	public By oipa_dataintake_sidelist = By.id("dataintake");
 	public By oipa_dataintake_profile = By.id("ojChoiceId_selectIntakeProfiles_selected");
 	public By oipa_dataintake_add_profile = By.xpath("//button[@id='addIntakeProfile']//div[1]");
 	public By oipa_dataintake_profile_name = By.id("IntakeProfileName");
 	public By oipa_dataintake_record_member_id = By.id("RecordMemberIdField");
 	public By oipa_dataintake_effective_date = By.id("EffectiveFrom");
 	public By oipa_dataintake_save = By.xpath("//span[text()='Save']");
 	public By oipa_dataintake_history = By.id("historyTab");
 	public By oipa_dataintake_profiles = By.id("profilesTab");
 	public By oipa_dataintake_profile_expand = By.xpath("//div[@class='oj-rowexpander-touch-area']//a[1]");
 	public By oipa_dataintake_delete = By.xpath("//button[@title='Delete']");
 	public By oipa_dataintake_delete_ok = By.xpath("//span[text()='OK']");

 	// Policy Comments pf
 	public By oipa_comments_sidelist = By.id("comments");
 	public By oipacomments_comments_add = By.xpath("//span[text()='Add Comment']");
 	public By oipacomments_comments_new = By.xpath("//span[text()='New']");
 	public By oipa_comments_functional_dept = By.id("oj-select-choice-addCommentsTable_FunctionalDepartment");
 	public By oipa_comments_category = By.id("oj-select-choice-addCommentsTable_Category");
 	public By oipa_comments_policy_comment_field = By.id("addCommentsTable_PolicyCommentField");
 	public By oipa_comments_comment_name = By.id("oj-select-choice-addCommentsTablecommentDetailsTemplate");
 	public By oipa_comments_content = By.id("addCommentsTableComment");
 	public By oipa_comments_save = By.id("addCommentsTablesaveButton");
 	public By oipa_comments_history = By.id("addCommentHistoryTab");
 	public By oipa_comments_all_comments = By.id("addCommentTab");
 	public By oipa_comments_expander = By.xpath("(//div[@class='oj-rowexpander-touch-area']//a)[1]");
 	public By oipa_comments_delete = By.xpath("(//button[@class='oi-icon-delete button-icon'])[1]");
 	public By oipa_comments_delete_ok = By.xpath("//span[text()='OK']");
 	public By oipa_comments_close = By.xpath("(//div[@class='oj-button-label']//span)[1]");

 	// Program PF
 	public By oipa_programs_sidelist = By.id("program");
 	public By oipa_programs_type = By.id("oj-select-choice-selectPolicySegment");
 	public By oipa_programs_select_program = By.id("oj-select-choice-selectProgram");
 	public By oipa_programs_add_program = By.id("addNewProgram");
 	public By oipa_programs_start_date = By.id("StartDate");
 	public By oipa_programs_effective_date = By.id("EffectiveDate");
 	public By oipa_programs_end_date = By.id("EndDate");
 	public By oipa_programs_amount = By.id("ProgramAmount");
 	public By oipa_programs_save = By.xpath("//span[text()='Save']");
 	public By oipa_programs_expander = By.xpath("//div[@class='oj-rowexpander-touch-area']//a[1]");
 	public By oipa_programs_history = By.id("history");
 	public By oipa_programs_details = By.id("programdetail");

 	// Segments Pf
 	public By oipa_segments_sidelist = By.id("segment");
 	public By oipa_segments_type = By.id("oj-select-choice-addsegmentselect");
 	public By oipa_segments_add = By.id("addSegment");
 	public By oipa_segments_amount = By.id("SegmentAmount");
 	public By oipa_segments_save = By.xpath("//span[text()='Save']");
 	public By oipa_segments_tobaco_use = By.id("ojChoiceId_SegmentTobaccoPremBasis_selected");
 	public By oipa_segments_text_field = By.id("SegmentTextField");

 	// Assessment Pf

 	public By oipa_assessment_sidelist = By.id("assessment");
 	public By oipa_assessment_add_impairment = By.xpath("//span[text()='Add Impairment']");
 	public By oipa_impairment_level = By.id("ojChoiceId_levelCombobox_selected");
 	public By oipa_impairment_category = By.id("oj-combobox-input-categoryCombobox");
 	public By oipa_impairment_priority = By.id("oj-select-choice-PriorityCode");
 	public By oipa_impairment_debit = By.id("Debit");
 	public By oipa_impairment_flatextra = By.id("FlatExtra");
 	public By oipa_impairment_duration = By.id("Duration");
 	public By oipa_impairment_comments = By.id("Comments");
 	public By oipa_impairment_save = By.id("saveButton");
 	public By oipa_impairment_insured = By.id("ojChoiceId_insuredCombobox_selected");
 	public By oipa_impairment_history = By.id("policyAssessmentHistoriesTab");
 	public By oipa_assessment_expand = By.xpath("//div[@class='oj-rowexpander-touch-area']//a[1]");
 	public By oipa_impairment_allimpairments = By.id("policyAssessmentTab");
 	public By oipa_impairment_delete = By.xpath("(//button[contains(@class,'oi-icon-pointer oi-icon-delete')])[1]");
 	public By oipa_impairment_delete_ok = By.xpath("//span[text()='OK']");
 	public By oipa_leave_page = By.xpath("//span[text()='Leave Page']");

 	// Segment role pf
 	public By oipa_segmentrole_expander = By.xpath("//div[@class='oj-rowexpander-touch-area']//a[1]");
 	public By oipa_segmentrole_roles = By.id("roles");
 	public By oipa_segmentrole_history = By.id("roleHistory");
 	public By oipa_segmentrole_roleview = By.id("ojChoiceId_selectView_selected");
 	public By oipa_segmentrole_client_expander = By.xpath("(//div[@class='oj-rowexpander-touch-area']//a)[2]");
 	public By oipa_segmentrole_testfield = By.id("TestField");
 	public By oipa_segmentrole_relationship = By.id("oj-select-choice-RoleRelationship");
 	public By oipa_segmentrole_save = By.xpath("//span[text()='Save']");
 	public By oipa_segmentrole_delete = By.xpath("//span[text()='Delete Role']");
 	public By oipa_segmentrole_linesmenu = By.className("oi-icon-func_fourhorizontallines");
 	public By oipa_segmentrole_delete_ok = By.xpath("//span[text()='OK']");
 	
 	
 	public By oipa_role_roleview = By.id("oj-select-choice-selectView");
 	public By oipa_role_insured_client=By.xpath("//table[@id='role_table_main']/tbody[1]/tr[1]/td[3]");
 	
 	
 	
 	// workflow pf
 	public By oipa_workflow_sidelist = By.xpath("//span[text()='Add Workflow Task']");
 	public By oipa_workflow_date = By.id("CREATEDGMT");
 	public By oipa_workflow_taskname = By.id("oj-select-choice-WORKFLOWTASKNAME");
 	public By oipa_workflow_comments = By.id("Comments");
 	public By oipa_workflow_save = By.xpath("//span[text()='Save']");
 	public By oipa_home = By.id("MenuItem_Home");
 	public By oipa_workflow_filters = By.xpath(
 			"//div[@id='homePage']/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]/div[1]/span[1]");
 	public By oipa_workflow_filter_name = By.id("oj-select-choice-filterTypeSelect7GeneralWorkflow");
 	public By oipa_workflow_filter_input = By
 			.xpath("//*[@id='oj-select-choice-filterTypeSelect7GeneralWorkflow']/following::input");
 	public By oipa_workflow_filter_ok = By.xpath("//span[text()='Ok']");

 	public By oipa_workflow_result_menu = By.className("oi-icon-func_fourhorizontallines");
 	public By oipa_workflow_filter_goto_history = By
 			.xpath("(//a[@data-bind='text:workflowTitles().titleGoToHistory()'])[1]");
 	public By oipa_workflow_filter_close_history = By.xpath("(//div[@class='oj-button-label'])[1]");
 	public By oipa_workflow_edit_task = By.xpath("(//a[@data-bind='text: workflowTitles().titleEditTask()'])[1]");
 	public By oipa_workflow_assigned_to = By.id("oj-select-choice-ASSIGNEDTO");
 	public By oipa_workflow_close = By.xpath("(//div[@class='oj-button-label']//span)[1]");

 	// GroupCustomer Class PF
 	public By oipa_gc_classgroup_sidelist = By.id("classgroup");
 	public By oipa_gc_classgroup_expander = By.xpath("//div[@class='oj-collapsible-header']//a[1]");
 	public By oipa_gc_classgroup_expander2 = By.xpath("(//div[@class='oj-rowexpander-touch-area']//a)[1]");
 	public By oipa_gc_classgroup_submit = By.id("submit1");
 	public By oipa_gc_classgroup_timeslice_status = By
 			.xpath("//table[@id='classGrouptimeSliceTable']/tbody[1]/tr[1]/td[7]");
 	public By oipa_gc_classgroup_timeslice_edit = By
 			.xpath("//button[contains(@class,'oi-icon-pointer oi-icon-pencil')]");
 	public By oipa_gc_classgroup_timeslice_save = By.xpath("	//span[text()='Save']");
 	public By oipa_gc_classgroup_timeslice_delete = By
 			.xpath("(//button[contains(@class,'oi-icon-pointer oi-icon-delete')])[1]");
 	public By oipa_gc_classgroup_timeslice_delete_ok = By.xpath("(//span[text()='OK'])[1]");
 	public By oipa_gc_classgroup_desc = By.id("ClassGroupDescription");
 	public By oipa_gc_classgroup_classes = By.id("classes");
 	public By oipa_gc_classgroup_parent_class = By.xpath("(//button[@class='oi-treeNodeText oi-tree-button-text'])[1]");
 	public By oipa_gc_classgroup_add_class = By.xpath("(//button[contains(@class,'oi-icon-plus oi-icon-pointer')])[1]");
 	public By oipa_gc_classgroup_class_type = By.xpath("(//div[@id='oj-select-choice-TypeCode'])[1]");
 	public By oipa_gc_classgroup_class_name = By.id("ClassName");
 	public By oipa_gc_classgroup_class_desc = By.id("ClassDescription");
 	public By oipa_gc_classgroup_ok = By.xpath("(//span[text()='OK'])[1]");
 	public By oipa_gc_classgroup_process = By.xpath("//button[@title='Process']");
 	public By oipa_gc_classgroup_save = By.xpath("//span[text()='Save']");
 	public By oipa_gc_classgroup_added_class = By.xpath("(//button[@class='oi-treeNodeText oi-tree-button-text'])[2]");

 	
 	
 	public By oipa_gc_classgroup_rules = By.id("classgrouprules");
 	public By oipa_gc_classgroup_variables = By.id("classvariable");
 	public By oipa_gc_classgroup_membership_rules = By.id("classrule");
 	public By oipa_gc_classgroup_add_varialble = By.xpath("(//span[text()='Add Variable'])[2]");
 	public By oipa_gc_classgroup_rule_name = By.id("mathTableNameInput0");
 	public By oipa_gc_classgroup_rule_type = By.id("oj-select-choice-mathTableNameSelect1");
 	public By oipa_gc_classgroup_rule_datatype = By.id("oj-select-choice-mathTableNameSelect2");
 	public By oipa_gc_classgroup_rule_text = By.id("mathTableNameInput3");

 	public By oipa_gc_classgroup_mem_source = By.xpath("//ul[@id='sourcelistview']//li[1]");
 	public By oipa_gc_classgroup_mem_arrow = By.xpath("//span[text()='>']");
 	public By oipa_gc_classgroup_mem_condition = By.id("condition");

 	public By oipa_gc_classgroup_plan_cov = By.id("classsubplan");
 	public By oipa_gc_classgroup_plan_ass = By.id("plancoverage");
 	public By oipa_gc_classgroup_plan_seg = By.xpath("(//li[@role='row'])[1]");

 	public By oipa_gc_classgroup_cov_type_code = By.id("oj-select-choice-typeCode");
 	public By oipa_gc_classgroup_ass_effectivedate = By.id("associationEffectiveDate");

 	public By oipa_gc_classgroup_subplan_name = By.xpath("//table[@id='subplantable']/tbody[1]/tr[1]/td[3]");
 	public By oipa_gc_classgroup_subplan_typecode = By.xpath("//table[@id='subplantable']/tbody[1]/tr[1]/td[4]");
 	public By oipa_gc_classgroup_subplan_date = By.xpath("//table[@id='subplantable']/tbody[1]/tr[1]/td[6]");

 	// GC Overview Screen
 	public By oipa_gc_overview_format = By.id("table");
 	public By oipa_gc_overview_grid = By.id("grid");
 	
 	public By oipa_gc_overview_details = By.xpath("(//h3[@class='oi-inline'])[1]");
 	public By oipa_gc_overview_gc_name = By.id("CustomerLegalName");

 	public By oipa_gc_overview_plans = By.xpath("(//h3[@class='oi-inline'])[3]");
 	public By oipa_gc_overview_plans_name = By.xpath("//table[@id='planTable']/tbody[1]/tr[1]/td[2]");
 	public By oipa_gc_overview_plans_product = By.xpath("//table[@id='planTable']/tbody[1]/tr[1]/td[3]");
 	public By oipa_gc_overview_plans_company = By.xpath("//table[@id='planTable']/tbody[1]/tr[1]/td[4]");

 	public By oipa_gc_overview_agreements = By.xpath("(//h3[@class='oi-inline'])[2]");
 	public By oipa_gc_overview_agreements_name = By.xpath("//table[@id='agreementTable']/tbody[1]/tr[1]/td[2]");
 	public By oipa_gc_overview_agreements_type = By.xpath("//table[@id='agreementTable']/tbody[1]/tr[1]/td[3]");
 	public By oipa_gc_overview_agreements_status = By.xpath("//table[@id='agreementTable']/tbody[1]/tr[1]/td[4]");

 	public By oipa_gc_overview_classgroups = By.xpath("(//h3[@class='oi-inline'])[4]");
 	public By oipa_gc_overview_classgroups_name = By.xpath("//table[@id='classGroupTable']/tbody[1]/tr[1]/td[2]");
 	public By oipa_gc_overview_classgroups_desc = By.xpath("//table[@id='classGroupTable']/tbody[1]/tr[1]/td[3]");
 	public By oipa_gc_overview_classgroups_type = By.xpath("//table[@id='classGroupTable']/tbody[1]/tr[1]/td[4]");
 	public By oipa_gc_overview_classgroups_status = By.xpath("//table[@id='classGroupTable']/tbody[1]/tr[1]/td[5]");
 	public By oipa_gc_overview_classgroups_effdate = By.xpath("//table[@id='classGroupTable']/tbody[1]/tr[1]/td[6]");
 	public By oipa_gc_overview_classgroups_expdate = By.xpath("//table[@id='classGroupTable']/tbody[1]/tr[1]/td[7]");

 	public By oipa_gc_overview_class = By.xpath("(//h3[@class='oi-inline'])[5]");
 	public By oipa_gc_overview_class_name = By.xpath("//table[@id='classTable']/tbody[1]/tr[1]/td[2]");
 	public By oipa_gc_overview_class_desc = By.xpath("//table[@id='classTable']/tbody[1]/tr[1]/td[3]");
 	public By oipa_gc_overview_class_type = By.xpath("//table[@id='classTable']/tbody[1]/tr[1]/td[4]");

 	
 	public By oipa_gc_overview_sidelist =By.xpath("//span[text()='Customer Overview']");
 	
 	
 	
 	
 	
 	
 	// Sidelist
 	public By oipa_gc_agreement_sidelist = By.id("agreement");

 	// Agreement
 	public By oipa_gc_agreement_contract = By.id("CNTR");
 	public By oipa_gc_agreement_add_contract = By.id("addNewNodeCNTR");

 	public By oipa_gc_agreement_type = By.id("oj-select-choice-TypeCode");

 	public By oipa_gc_agreement_name = By.id("AgreementName");
 	public By oipa_gc_agreement_status = By.id("oj-select-choice-StatusCode");
 	public By oipa_save = By.xpath("//span[text()='Save']");

 	public By oipa_gc_agreement_product = By.id("agreementproducts");
 	public By oipa_gc_agreement_add_product = By.id("addProductButton");

 	public By oipa_gc_agreement_product_gpp = By.xpath("//span[text()='Group Prototype Product']");
 	public By oipa_gc_agreement_ok = By.xpath("(//span[text()='OK'])[1]");
 	public By oipa_gc_agreement_product_status = By.id("oj-select-choice-BusinessStatusCode");
 	public By oipa_gc_agreement_product_effdate = By.id("EffectiveFrom");

 	public By oipa_gc_agreement_plans = By.id("agreementplans");
 	public By oipa_gc_agreement_plan_select = By.id("oj-select-choice-addnewPlanSelect");
 	public By oipa_gc_agreement_add_plan = By.id("addNewPlanButton");

 	public By oipa_gc_agreement_plan_segment = By.id("oj-select-choice-SegmentName");
 	public By oipa_gc_agreement_plan_segname = By.id("PlanSegmentName");
 	public By oipa_gc_agreement_seg_type = By.id("oj-select-choice-PlanSegmentType");
 	public By oipa_seg_save = By.xpath("(//span[text()='Save'])[2]");
 	public By oipa_agreement_plan_save = By.xpath("(//span[text()='Save'])[3]");
 	public By oipa_add_plan_segemnt = By.xpath("//span[text()='Add Plan Segment']");

 	// All Agreement Data Details

 	// Product details
 	public By oipa_agreement_product_name = By.xpath("//table[@id='agreementProductsTable']/tbody[1]/tr[1]/td[3]");
 	public By oipa_agreement_product_status = By.xpath("//table[@id='agreementProductsTable']/tbody[1]/tr[1]/td[4]");
 	public By oipa_agreement_product_effdate = By.xpath("//table[@id='agreementProductsTable']/tbody[1]/tr[1]/td[5]");
 	public By oipa_agreement_product_expdate = By.xpath("//table[@id='agreementProductsTable']/tbody[1]/tr[1]/td[6]");

 	// Plan details
 	public By oipa_agreement_plan_name = By.xpath("//table[@id='plansTable']/tbody[1]/tr[1]/td[3]");
 	public By oipa_agreement_plan_status = By.xpath("//table[@id='plansTable']/tbody[1]/tr[1]/td[4]");
 	public By oipa_agreement_plan_effdate = By.xpath("//table[@id='plansTable']/tbody[1]/tr[1]/td[5]");
 	public By oipa_agreement_plan_expdate = By.xpath("//table[@id='plansTable']/tbody[1]/tr[1]/td[6]");

 	// Class group details
 	public By oipa_agreement_cg_name = By.xpath("//table[@id='agreementClassGroupTable']/tbody[1]/tr[1]/td[3]");
 	public By oipa_agreement_cg_status = By.xpath("//table[@id='agreementClassGroupTable']/tbody[1]/tr[1]/td[4]");
 	public By oipa_agreement_cg_effdate = By.xpath("//table[@id='agreementClassGroupTable']/tbody[1]/tr[1]/td[5]");
 	public By oipa_agreement_cg_expdate = By.xpath("//table[@id='agreementClassGroupTable']/tbody[1]/tr[1]/td[6]");
 	
 	//***************************Suparna Page Factory******************************************//

 	public By oipa_client_summaryPane= By.xpath("//div[@id='clientSummary']");
	public By oipa_client_clientName = By.xpath("//div[contains(text(),'Client Name:')]/following-sibling::div");
	public By oipa_client_datePicker= By.xpath("//span[@title='Select Date']");	
	public By oipa_client_admin = By.xpath("//span[@id='ui-id-12']");
	public By oipa_client_SelectCompanyActivity = By.xpath("//a[@id='ui-id-29']");
	public By oipa_client_SelectCompany = By.xpath("//div[@id='oj-select-choice-CompanyGUID']");
	public By oipa_client_SelectPlan = By.xpath("//div[@id='oj-select-choice-PlanGUID']");
	public By oipa_client_AddActivity = By.xpath("//span[text()='Add Activity']");
	public By oipa_client_SearchActivity = By.xpath("//div[@id='oj-select-choice-selectActivityCombo']");
	public By oipa_client_ActivitySearchClient = By.xpath("//div[@id='oj-select-choice-Client']");
	public By oipa_client_CompanyActivityOk = By.xpath("//button[@id='activityOkButton']//div[@class='oj-button-label']");
	public By oipa_client_CompanyActivityProcess = By.xpath("//span[text() ='ClientShadow']//ancestor::tr//td[8]//span[@title='Process']");
	public By oipa_client_CompanyActivityReverse = By.xpath("//span[@title = 'Reverse']");
	
	//ClientAddress
	public By oipa_Client_ClientAddress = By.xpath("//span[contains(text(),'Address')]");
	public By oipa_Client_AddAdress = By.xpath("//span[contains(text(),'Add Address')]");
	public By oipa_Client_AddressType = By.xpath("//div[@id='oj-select-choice-addressRoleType']");
	public By oipa_Client_AddressLine1 = By.xpath("//input[@id='AddressLine1']");
	public By oipa_Client_AddressCity = By.xpath("//input[@id='City']");
	public By oipa_Client_AddressPostalID = By.xpath("//input[@id='PostalID']");
	public By oipa_Client_AddressSave = By.xpath("//span[contains(text(),'Save')]");
	
	//ClientWithHolding
	public By oipa_Client_SelectWithholdingScreen = By.xpath("//span[@class='oj-navigationlist-item-label'][contains(text(),'Withholding')]");
	public By oipa_Client_WithholdingFederalPercent = By.xpath("//input[@id='FederalPercent']");
	public By oipa_Client_WithholdingFederalAmount = By.xpath("//input[@id='FederalAmount']");
	public By oipa_Client_WithholdingStatePercent = By.xpath("//input[@id='StatePercent']");
	public By oipa_Client_WithholdingStateAmount = By.xpath("//input[@id='StateAmount']");
	public By oipa_Client_WithholdingOptoutYes = By.xpath("//input[@id='OptOut_02']");
	public By oipa_Client_WithholdingOptoutNo = By.xpath("//input[@id='OptOut_01']");
	public By oipa_Client_WithholdingStateFormRecievedYes = By.xpath("//input[@id='StateForm_02']");
	public By oipa_Client_WithholdingStateFormRecievedNo = By.xpath("//input[@id='StateForm_01']");
	
	public By oipa_Client_WithholdingSave = By.xpath("//button[@id='saveButton']");
	public By oipa_Client_WithholdingCancel= By.xpath("//button[@id='cancelButton']");
	
	//ClientRequirement
	public By oipa_Client_RequirementScreen = By.xpath("//span[@class='oj-navigationlist-item-label'][contains(text(),'Requirements')]");
	public By oipa_Client_AddRequirement = By.xpath("//span[@class='oj-button-icon oj-start oi-icon-plus oj-fwk-icon']");
	
	public By oipa_Client_RequirementSearch = By.xpath("//div[@id='oj-inputsearch-choice-inputRequirementSearch']");
	public By oipa_Client_RequirementStatus = By.xpath("//div[@id='oj-select-choice-StatusCode']");
	public By oipa_Client_RequirementVendorName = By.xpath("//input[@id='VendorName']");
	public By oipa_Client_RequirementPhone = By.xpath("//input[@id='Phone']");
	public By oipa_Client_RequirementOkButton = By.xpath("//button[@id='requirementOkButton']");
	
	public By oipa_Client_ClientRequirementName = By.xpath("//input[@id='RequirementName']");
	public By oipa_Client_ClientRequirementStatus = By.xpath("//div[@id='oj-select-choice-RequirementStatus']");
	public By oipa_Client_ClientRequirementAllowDuplicates = By.xpath("//input[@id='AllowDuplicates']");
	
	public By oipa_Client_SelectMIBInquiryClientAdded = By.xpath(".//*[text()='MIB Inquiry Client']//ancestor::tr//td[2]");
	public By oipa_Client_RequirementOutstanding = By.xpath("//span[@class='oi-icon-pointer oi-icon-clipboarderror oi-flag-icon-justify']");	
		
	//ClientActivity
	public By oipa_Client_ClientActivityScreen = By.xpath("//span[@class='oj-navigationlist-item-label'][contains(text(),'Activities')]");
	public By oipa_Client_ClientAddActivity = By.xpath("//span[contains(text(),'Add Activity')]");
	public By oipa_Client_ClientSearchActivity = By.xpath("//div[@id='oj-select-choice-selectActivityCombo']");
	public By oipa_Client_ActivityOkButton = By.xpath("//button[@id='activityOkButton']");
	public By oipa_Client_ActivityProcessButton = By.xpath("//button[@class='oi-icon-pointer oi-icon-process oi-icon-justify button-icon']");
	
	public By oipa_Client_FirstActivityProcess = By.xpath("//span[text() ='ClientTransactionTimes1']/parent::td/following-sibling::td[5]//button[@title='Process']");
	public By oipa_Client_SecondActivityProcess = By.xpath("//span[text() ='ClientTransactionTimes2']/parent::td/following-sibling::td[5]//button[@title='Process']");
	public By oipa_Client_FirstActivityRecycle = By.xpath("//span[text() ='ClientTransactionTimes1']/parent::td/following-sibling::td[5]//*[@title='Recycle']");
	public By oipa_Client_ActivityEffectiveDate = By.xpath("//input[@id='EffectiveDate']");
	public By oipa_Client_FirstActivityPendingReversal = By.xpath("//span[text()='Pending [Reversal]']//parent::td//following-sibling::td[2]//*[@title='Process']");
	public By oipa_Client_FirstActivityReverse = By.xpath("//span[text() ='ClientTransactionTimes1']/parent::td/following-sibling::td[5]//button[@data-id='activityReverse']");
	public By oipa_Client_DetailsFirstActivityWithPendingStatus = By.xpath("//span[text() ='Pending']//ancestor::tr//td[2]");
	
	public By oipa_Client_SpawnTransactionProcess = By.xpath("//span[text() ='SpawnTransaction']/parent::td/following-sibling::td[5]//button[@title='Process']");
	public By oipa_Client_SpawnTransaction1Process = By.xpath("//span[text() ='SpawnTransaction1']/parent::td/following-sibling::td[5]//button[@title='Process']");
	public By oipa_Client_SpawnTransaction2Process = By.xpath("//span[text() ='SpawnTransaction2']/parent::td/following-sibling::td[5]//button[@title='Process']");
	public By oipa_Client_SpawnTransactionRecycle = By.xpath("//span[text() ='SpawnTransaction']/parent::td/following-sibling::td[5]//*[@title='Recycle']");
	public By oipa_Client_SpawnTransactionPendingReversal = By.xpath("//span[text()='Pending [Reversal]']//parent::td//following-sibling::td[2]//*[@title='Process']");
	
	public By oipa_Client_SpawnTransactionReverse = By.xpath("//span[text() ='SpawnTransaction']/parent::td/following-sibling::td[5]//button[@data-id='activityReverse']");
	public By oipa_Client_DisplayActivity = By.xpath("//div[@id='ojChoiceId_displayCombo']");
	public By oipa_Client_RefreshActivity = By.xpath("//span[text()='Refresh']");
	
	public By oipa_Client_TransactionSpawnProcess = By.xpath("//span[text() ='TransactionSpawn']/parent::td/following-sibling::td[5]//button[@title='Process']");
	public By oipa_Client_TransactionSpawnNRNRProcess = By.xpath("//span[text() ='TransactionSpawn-NRNR']/parent::td/following-sibling::td[5]//button[@title='Process']");
	public By oipa_Client_TransactionSpawnReverse = By.xpath("//span[text() ='TransactionSpawn']/parent::td/following-sibling::td[5]//button[@data-id='activityReverse']");
	public By oipa_Client_TransactionSpawnPendingReversal = By.xpath("//span[text()='Pending [Reversal]']//parent::td//following-sibling::td[2]//*[@title='Process']");
	
	public By oipa_Client_SLTransactionClientPolicyTest = By.xpath("//input[@id='Test']");
	public By oipa_Client_SL_Spawn_PolicyClientProcess = By.xpath("//span[text() ='SL_Spawn_PolicyClient']/parent::td/following-sibling::td[5]//button[@title='Process']");
	public By oipa_Client_SLTransactionClientPolicyReverse = By.xpath("//span[text() ='SL_Spawn_PolicyClient']/parent::td/following-sibling::td[5]//button[@data-id='activityReverse']");
	public By oipa_Client_SLTransactionClientPolicyPendingReversal = By.xpath("//span[text()='Pending [Reversal]']//parent::td//following-sibling::td[2]//*[@title='Process']");
	
	public By oipa_Client_SearchMenu = By.xpath("//div[@id='oj-select-choice-Menu_Search']//a");
	public By oipa_Client_EnterToSearch = By.xpath("//input[@id='unifiedsearchinput']");
	public By oipa_Client_UnifiedSearchButton = By.xpath("//span[@class='oj-button-icon oj-start oj-fwk-icon oj-fwk-icon-magnifier']");
	public By oipa_Client_PolicyActivity = By.xpath("//span[text()='Activities']");
	public By oipa_Client_PolicyActivityDetails = By.xpath("//span[text() ='SL_Spawn_Policy']//ancestor::tr//td[2]");
	
	public By oipa_Client_ActivityTestTranxAmount = By.xpath("//input[@id='Amount']");
	
	
	/**********************Objects Added By Poornima*************************************/
	public By oipa_addMessage = By.xpath("//strong[contains(text(),'successfully')]");
	public By oipa_popUpConfimartion = By.xpath("//div[@id='ojDialogWrapper-exitModuleConfirmation']");
	public By oipa_popUpLeaveBtn = By.xpath("//button[contains(@data-bind,'titleLeave')]//div");

	// Group Customer objects
	public By oipa_GC_customerType = By.xpath("//div[@id='oj-select-choice-CustomerType']//a");
	public By oipa_GC_customerName = By.xpath("//input[@id='CompanyName']");
	public By oipa_GC_customerNumber = By.xpath("//input[@id='CustomerNumber']");
	public By oipa_GC_TaxID = By.xpath("//input[@id='TaxID']");
	public By oipa_GC_legalResidenceCountryCodeDD = By.xpath("//div[@id='oj-select-choice-LegalResidenceCountryCode']//a");
	public By oipa_GC_primaryEnrollmentRelationshipDD = By.xpath("//div[@id='oj-select-choice-PrimaryEnrollmentRelationship']//a");
	public By oipa_GC_enrollmentClassGroupDD = By.xpath("//div[@id='oj-select-choice-EnrollmentClassGroup']//a");
	public By oipa_GC_hierarchyRelationshipDD = By.xpath("//div[@id='oj-select-choice-HierarchyRelationship']//a");
	public By oipa_GC_originalEffectiveDate = By.xpath("//input[@id='OriginalEffectiveDate']");
	public By oipa_GC_customerLegalName = By.xpath("//input[@id='CustomerLegalName']");
	public By oipa_GC_customerStatusDD = By.xpath("//div[@id='oj-select-choice-CustomerStatus']//a");
	public By oipa_GC_savebutton = By.xpath("//button[@id='GroupCustomerSaveButtonBottom']");
	public By oipa_GC_customerNameEntered = By.xpath("//div[contains(text(),'GC_Cust_')]");
	public By oipa_GC_customerSummaryButton = By.xpath("//button[@id='toggleDrawerButton']");

	// Group Customer left navigation link objects
	public By oipa_GC_alternateName = By.xpath("//li[@id='alternatename']//span");
	public By oipa_GC_relationships = By.xpath("//li[@id='relationships']//span");
	public By oipa_GC_dataIntake = By.xpath("//li[@id='dataintake']//span");
	public By oipa_GC_agreement = By.xpath("//li[@id='agreement']//span");
	public By oipa_GC_plan = By.xpath("//li[@id='customerplans']//span");
	public By oipa_GC_activities = By.xpath("//li[@id='activities']//span");

	// Group Customer - AlternateName Screen objects
	public By oipa_GC_addAlternateNameButton = By.xpath("//button[@id='alternateNameNewButton']");
	public By oipa_GC_altIdTypeCode = By.xpath("//div[contains(@id,'oj-select-choice-02colID')]//a");
	public By oipa_GC_altName = By.xpath("//input[contains(@id,'Enter name herecolID')]");
	public By oipa_GC_altModifiedName = By.xpath("//input[contains(@id,'colID')]");
	public By oipa_GC_altIdEffectiveFrom = By.xpath("//input[contains(@id,'1colID')]");
	public By oipa_GC_altStatusDD = By.xpath("//div[contains(@id,'oj-select-choice-01colID')]//a");
	public By oipa_GC_altSaveButton = By.xpath("//button//span[contains(text(),'Save')]");
	public By oipa_GC_altSavedName = By.xpath("//span[contains(text(),'GC_AltName_')]");
	public By oipa_GC_altEditButton = By.xpath("//button[@title='Edit']");
	public By oipa_GC_alternateNameDisplay = By.xpath("//div[@id='ojChoiceId_alternateNameDisplay']");
	public By oipa_GC_alternateNameDisplayDDRemove = By.xpath("//div[@id='ojChoiceId_alternateNameDisplay']//a[contains(@aria-label,'remove')]");
	public By oipa_GC_altRefreshButton = By.xpath("//button//span[contains(text(),'Refresh')]");
	public By oipa_GC_altStatusInactive = By.xpath("//div//span[contains(text(),'Inactive')]");
	public By oipa_GC_altStatusPending = By.xpath("//div//span[contains(text(),'Pending')]");
	public By oipa_GC_altStatusDeleted = By.xpath("//div//span[contains(text(),'Deleted')]");
	public By oipa_GC_altStatusActive = By.xpath("//div//span[contains(text(),'Active')]");
	public By oipa_GC_altDefaultRadioButton = By.xpath("//table/tbody/tr[2]/td//input[contains(@id,'rowID')]");
	public By oipa_GC_altDeleteButton = By.xpath("//button[@title='Delete']");
	public By oipa_GC_altWarningOkButton = By.xpath("//button[contains(@data-bind,'WarningOk')]//span[contains(text(),'OK')]");

	// Group Customer - Relationships screen objects
	public By oipa_GC_addRelationshipDD = By.xpath("//div[@id='oj-select-choice-relationShipType']//a");
	public By oipa_GC_addRelationshipPlusIcon = By.xpath("//div[@id='addRelationshipCollapsiblePage']//span[@id='plusIcon']");
	public By oipa_GC_addRelationshipSubTypeDD = By.xpath("//div[@id='oj-select-choice-addRelationshipSubType']//a");
	public By oipa_GC_addRelationshipEffectiveDate = By.xpath("//input[@id='effective']");
	public By oipa_GC_addRelationshipClientSearchIcon = By.xpath("//span[@data-bind='click:clientIconClickHandler']");
	public By oipa_GC_addRelationshipNewClient = By.xpath("//a[@id='newClient']");
	public By oipa_GC_addRelationshipFindClient = By.xpath("//a[@id='findClient']");
	public By oipa_client_newClientPopUpSaveButton = By.xpath("//button[@id='clientAdd']");
	public By oipa_GC_addRelationshipEmploymentStatusDD = By.xpath("//div[@id='oj-select-choice-EmploymentStatus']//a");
	public By oipa_GC_addRelationshipEmploymentStatusData = By.xpath("//div[@id='oj-select-choice-EmploymentStatus']");
	public By oipa_GC_addRelationshipSaveButton = By.xpath("//button//span[contains(text(),'Save')]");
	public By oipa_GC_addRelationshipCancelButton = By.xpath("//button//span[contains(text(),'Cancel')]");
	public By oipa_GC_addRelationshipResult = By.xpath("//div[@id='relationshipsContainer']");
	public By oipa_GC_relationshipCreated = By.xpath("//span[contains(text(),'FN_CL')]");
	public By oipa_GC_relationshipFirstnameSearchInput = By.xpath("//input[@id='FirstName_Client']");
	public By oipa_GC_relationshipSearchButton = By.xpath("//button[@id='buttonSubmit']");
	public By oipa_GC_relationshipselectClient = By.xpath("//tr[@role='row']");
	public By oipa_GC_addRelationshipDisplayPastBox = By.xpath("//input[@id='past']");
	public By oipa_GC_addRelationshipDisplayFutureBox = By.xpath("//input[@id='future']");
	public By oipa_GC_addRelationshipRefreshButton = By.xpath("//button//span[contains(text(),'Refresh')]");
	public By oipa_GC_addRelationshipResultExpandIcon = By.xpath("//div[@id='relationshipsContainer']//div[@class='oj-collapsible-header']//a");
	public By oipa_GC_addRelationshipFullTimeText = By.xpath("//span[contains(text(),'Full Time')]");
	public By oipa_GC_addRelationshipHamburgerMenu = By.xpath("//span[contains(@id,'relationshipHamburger')]");
	public By oipa_GC_addRelationshipGoToClient = By.xpath("//li[@id='goToClientMenuItem']");
	public By oipa_GC_addRelationshipShadowCheckBox = By.xpath("//input[@id='chkDisplayShadow']");
	public By oipa_GC_addRelationshipCompareButton = By.xpath("//button[@id='compareButton']");
	public By oipa_GC_addRelationshipAddNewRecordButton = By.xpath("//button[@id='addNewRow']");
	public By oipa_GC_addRelationshipSubType = By.xpath("//div[contains(text(),'Relationship Sub Type/Name')]");
	public By oipa_GC_addRelationshipTableEffectiveDate = By.xpath("//div[contains(text(),'Effective Date')]");
	public By oipa_GC_addRelationshipTableExpirationDate = By.xpath("//div[contains(text(),'Expiration Date')]");
	public By oipa_GC_addRelationshipRecordStatus = By.xpath("//div[contains(text(),'Record Status')]");
	public By oipa_GC_addRelationshipAction = By.xpath("//div[contains(text(),'Action')]");
	public By oipa_GC_addRelationshipTimeSliceTableExpander = By.xpath("//a[@aria-labelledby='TimeSliceTable']");
	public By oipa_GC_addRelationshipWarningOkButton = By.xpath("//button[@id='warningokButton']");
	public By oipa_GC_addRelationshipSliceEffectiveDate = By.xpath("//input[@id='Effectivedate']");
	public By oipa_GC_addRelationshipSliceNextButton = By.xpath("//button[@id='NextButton']");
	public By oipa_GC_addRelationshipBusinessUnitField = By.xpath("//input[@id='BusinessUnit']");

	// Group Customer - DataIntake screen objects
	public By oipa_GC_DI_addIntakeProfileDD = By.xpath("//div[@id='oj-select-choice-selectIntakeProfiles']//a");
	public By oipa_GC_DI_addProfileButton = By.xpath("//button[@id='addIntakeProfile']");
	public By oipa_GC_DI_intakeProfileName = By.xpath("//input[@id='IntakeProfileName']");
	public By oipa_GC_DI_recordMemberIdField = By.xpath("//input[@id='RecordMemberIdField']");
	public By oipa_GC_DI_recordMemberIdFieldTypeCodeDD = By.xpath("//div[@id='oj-select-choice-RecordMemberIdFieldTypeCode']//a");
	public By oipa_GC_DI_effectiveFromDate = By.xpath("//input[@id='EffectiveFrom']");
	public By oipa_GC_DI_saveButton = By.xpath("//button[@id='dataIntakesaveButton']");
	public By oipa_GC_DI_RowExpander = By.xpath("//table/tbody/tr[1]/td[2]/div");
	public By oipa_GC_DI_activateButton = By.xpath("//button//span[contains(text(),'Activate')]");
	public By oipa_GC_DI_deactivateButton = By.xpath("//button//span[contains(text(),'Deactivate')]");
	public By oipa_GC_DI_statusActiveRadioButton = By.xpath("//input[@id='ACTIVE']");
	public By oipa_GC_DI_statusInactiveRadioButton = By.xpath("//input[@id='INACTIVE']");
	public By oipa_GC_DI_statusAllRadioButton = By.xpath("//input[@id='All']");
	public By oipa_GC_DI_shadowWarningOKButton = By.xpath("//button[@id='okShadowButton']");
	public By oipa_GC_DI_noDataDisplayText = By.xpath("//table//td[contains(text(),'No data to display.')]");

	// Group Customer - Agreements screen objects
	public By oipa_GC_agreementBLTreetext = By.xpath("//div[@id='tree']//button[@id='BL']");
	public By oipa_GC_agreementBLPlusIcon = By.xpath("//div[@id='tree']//button[@id='addNewNodeBL']");
	public By oipa_GC_agreementBLText = By.xpath("//span[contains(text(),'Bank Letter')]");
	public By oipa_GC_agreementDetailsText = By.xpath("//strong//a[@id='agreementdetail']");
	public By oipa_GC_agreementTypeDD = By.xpath("//div[@id='ojChoiceId_TypeCode']//a");
	public By oipa_GC_agreementName = By.xpath("//input[@id='AgreementName']");
	public By oipa_GC_agreementStatusDD = By.xpath("//div[@id='ojChoiceId_StatusCode']//a");
	public By oipa_GC_agreementNumber = By.xpath("//input[@id='AgreementNumber']");
	public By oipa_GC_agreementExperienceNumber = By.xpath("//input[@id='ExperienceNumber']");
	public By oipa_GC_agreementSaveButton = By.xpath("//button[@id='agreementDetailSave']");
	public By oipa_GC_agreementAddAgmtName = By.xpath("//strong//span[contains(@data-bind,'selectedAgreementName')]");

	public By oipa_GC_agreementContractTreetext = By.xpath("//div[@id='tree']//button[@id='CNTR']");
	public By oipa_GC_agreementContractPlusIcon = By.xpath("//div[@id='tree']//button[@id='addNewNodeCNTR']");
	public By oipa_GC_agreementContractText = By.xpath("//span[contains(text(),'Contract')]");
	public By oipa_GC_agreementUndefinedExpandIcon = By.xpath("//div[contains(@id,'subsectionDynamicFields')]//a");
	public By oipa_GC_agreementContractParentPlusIcon = By.xpath("//div[@id='tree']//button[@title='Add Child']");
	// public By oipa_GC_agreementMAText = By.xpath("//div[@id='tree']//button[@aria-label='Master Agreement - Insured_Parent01']");
	public By oipa_GC_agreementMAExpandIcon = By.xpath("//ul[contains(@data-bind,'agreementArray')]//ins");
	public By oipa_GC_agreementDeleteIcon = By.xpath("//ul[contains(@data-bind,'agreementCategory')]//button[@title='Delete']");
	public By oipa_GC_agreementChildDeleteIcon = By.xpath("//ul[contains(@data-bind,'parent.agreementCategory')]//button[@title='Delete']");
	public By oipa_GC_agreementDeleteOkButton = By.xpath("//button[contains(@data-bind,'processDeleteAgreementHierarchyNodes')]");
	public By oipa_GC_agreementContractCount = By.xpath("//span[contains(@id, 'CNTR')]");

	// Group Customer - Products screen objects
	public By oipa_GC_productLink = By.xpath("//strong//a[contains(text(),'Products')]");
	public By oipa_GC_productAddProductButton = By.xpath("//button[@id='addProductButton']");
	public By oipa_GC_productcompanyDD = By.xpath("//div[@id='oj-select-choice-Company']//a");
	public By oipa_GC_productGPPlink = By.xpath("//span[contains(text(),'Group Prototype Product')]");
	public By oipa_GC_productTermProductlink = By.xpath("//span[contains(text(),'TERM Products')]");
	public By oipa_GC_productAddProductOkButton = By.xpath("//button[@id='addProductOkButton']");
	public By oipa_GC_productStatusDD = By.xpath("//div[@id='oj-select-choice-BusinessStatusCode']//a");
	public By oipa_GC_productEffectivefromDate = By.xpath("//input[@id='EffectiveFrom']");
	public By oipa_GC_productExpirationDate = By.xpath("//input[@id='EffectiveTo']");
	public By oipa_GC_productSaveButton = By.xpath("//button[@id='productSaveButton']");
	public By oipa_GC_prodcuttabledata = By.xpath("//table//tbody");
	public By oipa_GC_productDeleteOKButton = By.xpath("//div[@id='timeslicewarningDialog']//span[contains(text(),'OK')]");

	// Group Customer - Agreement Plan screen objects
	public By oipa_GC_agreementplanLink = By.xpath("//strong//a[contains(text(),'Plans')]");
	public By oipa_GC_planAddNewPlanDD = By.xpath("//div[@id='oj-select-choice-addnewPlanSelect']//a");
	public By oipa_GC_planAddNewPlanButton = By.xpath("//button[@id='addNewPlanButton']");
	public By oipa_GC_plancompanyDD = By.xpath("//div[@id='oj-select-choice-Company']//a");
	public By oipa_GC_planproductDD = By.xpath("//div[@id='oj-select-choice-Product']//a");
	public By oipa_GC_planName = By.xpath("//input[@id='PlanName']");
	public By oipa_GC_planStatusDD = By.xpath("//div[@id='oj-select-choice-PlanStatus']//a");
	public By oipa_GC_planEffectiveFrom = By.xpath("//input[@id='EffectiveFrom']");
	public By oipa_GC_planCurrencyDD = By.xpath("//div[@id='oj-select-choice-DefaultCurrencyCode']//a");
	public By oipa_GC_planExpirationDatePopUp = By.xpath("//input[@id='EffectiveTo']");
	public By oipa_GC_planAlocationMethodDD = By.xpath("//div[@id='oj-select-choice-PlanAllocationMethodCode']//a");
	public By oipa_GC_planMarketMakerDD = By.xpath("//div[@id='oj-select-choice-MarketMakerGUID']//a");
	public By oipa_GC_planAddSegmentPlusIcon = By.xpath("//button[@aria-label='Add Plan Segement']");
	public By oipa_GC_planSegmentNameDD = By.xpath("//div[@id='oj-select-choice-SegmentName']//a");
	public By oipa_GC_planSegmentName = By.xpath("//input[@id='PlanSegmentName']");
	public By oipa_GC_planSegmentTypeDD = By.xpath("//div[@id='oj-select-choice-PlanSegmentType']//a");
	public By oipa_GC_planProcessAddSegment = By.xpath("(//span[text()='Save'])[2]");
	public By oipa_GC_planProcessSaveAddPlan = By.xpath("(//span[text()='Save'])[1]");
	public By oipa_GC_planSegmentExpand = By.xpath("//table//tr[2]//a[contains(@aria-labelledby,'plansegmenttable')]");
	public By oipa_GC_planBusinessStatusDD = By.xpath("//div[@id='oj-select-choice-BusinessStatusCode']//a");
	public By oipa_GC_planExpirationDate = By.xpath("//input[@id='EffectiveTo']");
	public By oipa_GC_planSaveButton = By.xpath("//button[@id='btnSavePlan']");
	public By oipa_GC_planEdit = By.xpath("//li[@id='editPlan']");
	public By oipa_GC_planGoToPlanscreen = By.xpath("//li[@id='goToPlan']");
	public By oipa_GC_plan_RefreshBtn = By.xpath("//button[@id='refreshPlansButton']");

	public By oipa_GC_planSegmentWarningOK = By.xpath("//button[contains(@data-bind,'WarningOkHandler')]//div");
	public By oipa_GC_planSegmentTable = By.xpath("//table[@id='plansegmenttable']//tbody");

	// Group Customer - Plan screen objects
	public By oipa_GC_plan_expandIcon = By.xpath("//div[@id='plansContainer']//a[@role='button']");
	public By oipa_GC_plan_name = By.xpath("//input[@id='PlanName']");
	public By oipa_GC_plan_save = By.xpath("//button[@id='savePlanDetailsButton']//div");
	public By oipa_GC_plan_alertOkbutton = By.xpath("//button[contains(@data-bind,'actionDialogOkHandler')]//div");
	public By oipa_GC_plan_alertCancelbutton = By.xpath("//button[contains(@data-bind,'actionDialogCancelHandler')]//div");
	public By oipa_GC_plan_maximumFaceAmountTextField = By.xpath("//input[@id='MaximumFaceAmount']");
	public By oipa_GC_plan_warningMessage = By.xpath("//div[contains(@data-bind,'WarningMessageContainer')]");
	public By oipa_GC_plan_tabledata = By.xpath("//table//tbody");

	// Client left navigation link objects
	public By oipa_client_activities = By.xpath("//li[@id='activities']//span");

	// Policy left navigation link objects
	public By oipa_policy_activities = By.xpath("//li[@id='activities']//span");
	public By oipa_policy_segments = By.xpath("//li[@id='segment']//span");

	// Common Activities Page objects
	public By oipa_addActivityButton = By.xpath("//button//span[contains(text(),'Add Activity')]");
	public By oipa_activitydropdown = By.xpath("//div[@id='oj-select-choice-selectActivityCombo']//a");
	public By oipa_activityEffectiveDate = By.xpath("//input[@id='EffectiveDate']");
	public By oipa_activityOKButton = By.xpath("//button[@id='activityOkButton']");
	public By oipa_GC_activityBillingTab = By.xpath("//a[@id='activitybilling']");

	// Client Activities Page objects
	public By oipa_client_activityEffectiveTo = By.xpath("//input[@id='EffectiveTo']");
	public By oipa_client_activityEnrollPolicyName = By.xpath("//input[@id='NewPolicyName']");
	public By oipa_client_activityEnrollCoverageDD = By.xpath("//div[@id='oj-select-choice-Coverage']//a");
	public By oipa_client_activitySegmentFaceAmount = By.xpath("//input[@id='SegmentFaceAmount']");

	// Policy Activities Page objects
	public By oipa_policy_activityBillDueDate = By.xpath("//input[@id='BillDueDate']");
	public By oipa_policy_activityBillDueAmount = By.xpath("//input[@id='BillDueAmount']");
	public By oipa_policy_activityBillGroupTypeDD = By.xpath("//div[@id='oj-select-choice-BillGroupType']//a");
	public By oipa_policy_activityBillEntityTypeDD = By.xpath("//div[@id='oj-select-choice-BillEntityType']//a");
	public By oipa_policy_activityAmount1 = By.xpath("//input[@id='Amount1']");
	public By oipa_policy_activityAmount2 = By.xpath("//input[@id='Amount2']");
	public By oipa_policy_activityBillDetailDD = By.xpath("//div[@id='oj-select-choice-BillDetailGUID']//a");
	public By oipa_policy_activityNewAmount = By.xpath("//input[@id='NewAmount']");
	public By oipa_policy_activityBillDetail1DD = By.xpath("//div[@id='oj-select-choice-BillDetailGUID1']//a");
	public By oipa_policy_activityNewAmount1 = By.xpath("//input[@id='NewAmount1']");
	public By oipa_policy_activityBillDetail2DD = By.xpath("//div[@id='oj-select-choice-BillDetailGUID2']//a");
	public By oipa_policy_activityNewAmount2 = By.xpath("//input[@id='NewAmount2']");
	public By oipa_policy_activityShadowBillDetailChkBox = By.xpath("//input[contains(@id,'ShadowBillDetail')]");
	public By oipa_policy_activityReconcileBillDetailChkBox = By.xpath("//input[contains(@id,'ReconcileBillDetail')]");
	public By oipa_policy_activityShowErrorBtn = By.xpath("//button[@id='toggleError']");
	public By oipa_policy_activityStackTraceBill = By.xpath("//span[contains(text(),'Bill Detail Array error')]");
	public By oipa_policy_activityRefreshBtn = By.xpath("//button[@id='refreshButton']");

	// Policy Segment Page Objects
	public By oipa_segment_segmentDD = By.xpath("//div[@id='oj-select-choice-addsegmentselect']//a");
	public By oipa_segment_addSegmentButton = By.xpath("//button[@id='addSegment']");
	public By oipa_segment_saveBtn = By.xpath("//button[contains(@data-bind,'processSave')]");

	// Customer Activities Page objects
	public By oipa_GC_activityBillingStartDate = By.xpath("//input[@id='BillingStartDate']");
	public By oipa_GC_activityBillingEndDate = By.xpath("//input[@id='BillingEndDate']");
	public By oipa_GC_activityBillOwnerTypeDD = By.xpath("//div[@id='oj-select-choice-BillGroupType']//a");
	public By oipa_GC_activityThreshholdMinimumAmount = By.xpath("//input[@id='ThreshholdMinimumAmount']");
	public By oipa_GC_activityThreshholdMaximumAmount = By.xpath("//input[@id='ThreshholdMaximumAmount']");
	public By oipa_GC_activityMathTab = By.xpath("//a[@id='activitymath']");

	// OIPA Home Main Menu Search Objects
	public By oipa_searchDD = By.xpath("//div[@id='oj-select-choice-Menu_Search']//a");
	public By oipa_searchInput = By.xpath("//input[@id='unifiedsearchinput']");
	public By oipa_searchButton = By.xpath("//button[@id='unifiedSearchIcon']");
	public By oipa_summaryExpandButton = By.xpath("//button[@id='toggleDrawerButton']");

	// Group/ListBill Objects
	public By oipa_bill_adjustLink = By.xpath("//a[contains(@data-bind,'adjustLink')]");
	public By oipa_bill_linkSuspenseLink = By.xpath("//a[@id='link']");

	public By oipa_bill_TotalAmountDue = By.xpath("//div[@id='billreconciliationcontent']//b[contains(text(),'Total Amount Due')]");
	public By oipa_bill_CurrentReconciledAmount = By.xpath("//div[@id='billreconciliationcontent']//span[contains(text(),'Current Reconciled Amount')]");
	public By oipa_bill_GroupBillReferenceID = By.xpath("//div[@id='drawer']//b[contains(text(),'Group Bill Reference ID')]");
	public By oipa_bill_GroupBillDate = By.xpath("//div[@id='drawer']//b[contains(text(),'Group Bill Date')]");
	public By oipa_bill_GroupCustomerNumber = By.xpath("//div[@id='drawer']//b[contains(text(),'Group Customer Number')]");

	public By oipa_bill_typeofSuspense = By.xpath("//div[@id='oj-select-choice-ReconciliationSuspenseType']");
	public By oipa_bill_typeofSuspenseDD = By.xpath("//div[@id='oj-select-choice-ReconciliationSuspenseType']//a");
	public By oipa_bill_typeofSuspenseText = By.xpath("//label[@id='oj-select-label-ReconciliationSuspenseType']");
	public By oipa_bill_reconciliationEffectiveDateText = By.xpath("//label[@for='ReconciliationEffectiveDate']");
	public By oipa_bill_reconciliationEffectiveDatefield = By.xpath("//input[@id='ReconciliationEffectiveDate']");
	public By oipa_bill_reconciliationEffectiveDateErrorMsg = By.xpath("//strong[contains(text(),'Invalid Date')]");
	public By oipa_bill_linkSuspenseOkBtn = By.xpath("//button[@id='linkSuspenseOk']");
	public By oipa_bill_linkSuspenseCancelBtn = By.xpath("//button[@id='linkSuspenseCancelBtn']//div");

	public By oipa_bill_linkSuspense_suspenseNumber = By.xpath("//input[@id='SuspenseNumber']");
	public By oipa_bill_linkSuspense_searchButton = By.xpath("//button[@id='ssbtnSearch']");
	public By oipa_bill_linkSuspense_selectrow = By.xpath("//tr[@role='row']");

	public By oipa_bill_reconciliationEffectiveDate = By.xpath("//span[contains(text(),'Reconciliation Effective Date')]");
	public By oipa_bill_suspenseNumber = By.xpath("//b[contains(text(),'Suspense Number')]");
	public By oipa_bill_unattachedAmount = By.xpath("//span[contains(text(),'Unattached Amount')]");
	public By oipa_bill_totalofAmounttoReconcile = By.xpath("//b[contains(text(),'Total of Amount to Reconcile')]");
	public By oipa_bill_reconcileallcheckBox = By.xpath("//div[@id='checkboxSetReconcileAll']");

	public By oipa_bill_searchCriteria_BillGroupType = By.xpath("//input[@id='BillGroupType_BillDetail']");
	public By oipa_bill_searchCriteria_Amount = By.xpath("//input[@id='Amount_BillDetail']");
	public By oipa_bill_searchCriteria_PolicyNumber = By.xpath("//input[@id='PolicyNumber_Policy']");
	public By oipa_bill_searchCriteria_FindBtn = By.xpath("//button[@id='billreDetail']");

	public By oipa_bill_searchResult_Amount = By.xpath("//table/thead/tr/th[@title='Amount']");
	public By oipa_bill_searchResult_PolicyNumber = By.xpath("//table/thead/tr/th[@title='Policy Number']");
	public By oipa_bill_searchResult_AmountForReconciliation = By.xpath("//table/thead/tr/th[@title='Amount For Reconciliation']");
	public By oipa_bill_searchResult_Action = By.xpath("//table/thead/tr/th[@title='Action']");
	public By oipa_bill_searchResult_editIcon = By.xpath("//span[contains(@data-bind,'editBillDetailAmountReconcilation')]");
	public By oipa_bill_searchResult_pendingReconciliationAmount = By.xpath("//input[contains(@data-bind,'pendingReconciliationAmount')]");
	public By oipa_bill_searchResult_actionIcon = By.xpath("//span[contains(@data-bind,'linkSuspenseReconcileActionHandler')]");
	public By oipa_bill_searchResult_saveBtn = By.xpath("//button[@id='billSearchDetailSave']");

	public By oipa_bill_errorMessage = By.xpath("//div[@id='errorMessages']//strong");
	public By oipa_bill_processOkBtn = By.xpath("//button[contains(@data-bind,'processLinkSuspenseSubmitConfirmationAction')]//div");
	public By oipa_bill_processOkConfirmBtn = By.xpath("//button[contains(@data-bind,'processLinkSuspenseReconcilationSaveOkConfirmation')]//div");

	public By oipa_bill_adjust_billDetailReferenceNumber = By.xpath("//input[@id='BillReferenceID_Bill']");
	public By oipa_bill_adjust_billDetailReconciliationAmount = By.xpath("//input[@id='Amount_BillDetail']");
	public By oipa_bill_adjust_billStatusDD = By.xpath("//div[@id='oj-select-choice-Status_BillDetail']");
	public By oipa_bill_adjust_billExpandCollapseBtn = By.xpath("//h4[contains(@data-bind,'titleBillReconciliationSearch')]//a");
	public By oipa_bill_adjust_billFindBtn = By.xpath("//button[@id='button']");
	public By oipa_bill_adjust_editIcon = By.xpath("//span[contains(@data-bind,'editAmountReconcilation')]");
	public By oipa_bill_adjust_searchResult_reconciliationAmount = By.xpath("//input[contains(@data-bind,'billDetailReconciliationAmount')]");
	public By oipa_bill_adjust_searchResult_actionIcon = By.xpath("//span[contains(@data-bind,'adjustReconcileActionHandler')]");
	public By oipa_bill_adjust_saveBtn = By.xpath("//button[contains(@data-bind,'processAdjustReconcilationSave')]");
	public By oipa_bill_adjust_cancelBtn = By.xpath("//button[contains(@data-bind,'processAdjustReconcilationCancel')]");
	public By oipa_bill_adjust_processOkBtn = By.xpath("//button[contains(@data-bind,'savePopUpOk')]//div");
	public By oipa_bill_adjust_processOkConfirmBtn = By.xpath("//button[@id='billReconcilationOkDeleteButtonConfirmation']");

	// Suspense Page Objects
	public By oipa_suspense_policyNumber = By.xpath("//input[@id='PolicyNumber']");
	public By oipa_suspense_amount = By.xpath("//input[@id='Amount']");
	public By oipa_suspense_firstName = By.xpath("//input[@id='FirstName']");
	public By oipa_suspense_lastName = By.xpath("//input[@id='LastName']");
	public By oipa_suspense_accountNumber = By.xpath("//input[@id='AccountNumber']");
	public By oipa_suspense_checkNumber = By.xpath("//input[@id='CheckNumber']");
	public By oipa_suspense_bankName = By.xpath("//input[@id='BankName']");
	public By oipa_suspense_bankNumber = By.xpath("//input[@id='BankNumber']");
	public By oipa_suspense_saveButton = By.xpath("//button[@id='saveButton']//span[contains(text(), 'Save')]");
	public By oipa_suspense_suspenseCreated = By.xpath("//div[@data-bind='if: suspenseGuid()']");

	//// Objects Added By Sushma
	// Group Customer Agreement Class Groups adding objects
	public By oipa_GC_Agreement_ClassGroupsLink = By.xpath("//strong//a[@id='agreementclassgroups']");
	public By oipa_GC_Agreement_ClassGroups_AddDD = By.xpath("//div[@id='oj-select-choice-classGroupAddSelect']/a");
	public By oipa_GC_Agreement_ClassGroups_AddButton = By.xpath("//button[@id='classGroupAddButton']");
	public By oipa_GC_Agreement_AddClassGroups_TypeDD = By.xpath("//div[@id='oj-select-choice-TypeCode']/a");
	public By oipa_GC_Agreement_AddClassGroups_StatusDD = By.xpath("//div[@id='oj-select-choice-BusinessStatusCode']/a");
	public By oipa_GC_Agreement_AddClassGroups_ClassGroupName = By.xpath("//input[@id='ClassGroupName']");
	public By oipa_GC_Agreement_AddClassGroups_ClassGroupDescription = By.xpath("//input[@id='ClassGroupDescription']");
	public By oipa_GC_Agreement_AddClassGroups_EffectiveFrom = By.xpath("//input[@id='EffectiveFrom']");
	public By oipa_GC_Agreement_AddClassGroups_ExpirationDate = By.xpath("//input[@id='EffectiveTo']");
	public By oipa_GC_Agreement_AddClassGroups_ClassGroupSubType = By.xpath("//input[@id='ClassGroupSubType']");
	public By oipa_GC_Agreement_AddClassGroups_ClassGroupCriteria = By.xpath("//input[@id='ClassGroupCriteria']");
	public By oipa_GC_Agreement_AddClassGroups_SaveButton = By.xpath("//button[contains(@data-bind,'processSave')]");
	public By oipa_GC_Agreement_AddClassGroups_CancelButton = By.xpath("//button[contains(@data-bind,'cancelAddClass')]");
	public By oipa_GC_Agreement_AddClassGroups_SaveStatusDD = By.xpath("//div[@id='oj-select-choice-agStatusCode']//a");
	public By oipa_GC_Agreement_AddClassGroups_agreementClassgroupSaveButton = By.xpath("//button[@id='agreementClassgroupSave']");
	public By oipa_GC_Agreement_AddClassGroups_agreementClassGroupCancelButton = By.xpath("//button[@id='agreementClassgroupCancel']");
	public By oipa_GC_Agreement_AddClassGroups_HamburgerMenuIcon = By.xpath("//span[@id='agreementClassgroup-1']");
	public By oipa_GC_Agreement_AddClassGroups_Hamburger_Edit = By.xpath("//li[@id='editClassgroup']//a//span");
	public By oipa_GC_Agreement_AddClassGroups_Hamburger_Delete = By.xpath("//li[@id='deleteClassgroup']//a//span");
	public By oipa_GC_Agreement_AddClassGroups_Hamburger_GoToClass = By.xpath("//li[@id='gotoClassgroup']//a//span");

	// Group Customer Agreement Class Groups Add Class objects
	public By oipa_GC_Agreement_AddClassGroups_AddClassLink = By.xpath("//div[@id='newAgreementSection']//a[@id='addClassGroupTab']");
	public By oipa_GC_Agreement_AddClass_ClassTypeDD = By.xpath("//div[@id='oj-select-choice-TypeCode']//a");
	public By oipa_GC_Agreement_AddClass_ClassName = By.xpath("//input[@id='ClassName']");
	public By oipa_GC_Agreement_AddClass_ClassDescription = By.xpath("//input[@id='ClassDescription']");
	public By oipa_GC_Agreement_AddClass_UndefinedSectionExpander = By.xpath("(//div[@id='subSectionCollapsible']//a)[2]");
	public By oipa_GC_Agreement_AddClass_RelatedClass = By.xpath("//input[@id='RelatedClass']");
	public By oipa_GC_Agreement_AddClass_ProductType = By.xpath("//input[@id='ProductType']");
	public By oipa_GC_Agreement_AddClass_ProductSubType = By.xpath("//input[@id='ProductSubType']");
	public By oipa_GC_Agreement_AddClass_Reporting1 = By.xpath("//input[@id='Reporting1']");
	public By oipa_GC_Agreement_AddClass_Reporting2 = By.xpath("//input[@id='Reporting2']");
	public By oipa_GC_Agreement_AddClass_Billing1 = By.xpath("//input[@id='Billing1']");
	public By oipa_GC_Agreement_AddClass_Billing2 = By.xpath("//input[@id='Billing2']");
	public By oipa_GC_Agreement_AddClass_CustomerNumber = By.xpath("//input[@id='CustomerNumber']");
	public By oipa_GC_Agreement_AddClass_ExperienceNumber = By.xpath("//input[@id='ExperienceNumber']");
	public By oipa_GC_Agreement_AddClass_ReportNumber = By.xpath("//input[@id='ReportNumber']");
	public By oipa_GC_Agreement_AddClass_SubCode = By.xpath("//input[@id='SubCode']");
	public By oipa_GC_Agreement_AddClass_Branch = By.xpath("//input[@id='Branch']");
	public By oipa_GC_Agreement_AddClass_KeyNum = By.xpath("//input[@id='KeyNum']");
	public By oipa_GC_Agreement_AddClass_RateGroup = By.xpath("//input[@id='RateGroup']");

	// Group Customer Edit and Delete Class Groups Objects
	public By oipa_GC_Agreement_ClassGroups_StatusFilter = By.xpath("//div[@id='oj-select-choice-filterCombo']//a");
	public By oipa_GC_Agreement_ClassGroups_RefreshButton = By.xpath("//button[@id='classGroupRefreshButton']");
	public By oipa_GC_Agreement_EditClassGroups_Status = By.xpath("//div[@id='oj-select-choice-agStatusCode']");
	public By oipa_GC_Agreement_EditClassGroups_EffectiveDate = By.xpath("//input[@id='agEffectiveDate']");
	public By oipa_GC_Agreement_EditClassGroups_ExpirationDate = By.xpath("//input[@id='agEffectiveToDate']");
	public By oipa_GC_Agreement_EditClassGroups_StatusDD = By.xpath("//div[@id='oj-select-choice-agStatusCode']//a");
	public By oipa_GC_Agreement_EditClassGroups_SaveButton = By.xpath("//button[@id='agreementClassgroupSave']");
	public By oipa_GC_Agreement_EditClassGroups_RowExpandIcon = By.xpath("//table//tbody//tr[1]//td[2]//a");
	public By oipa_GC_Agreement_EditClassGroups_ClassGrpName = By.xpath("//input[@id='agClassGroupName']");
	public By oipa_GC_Agreement_DeleteClassGroup_OKButton = By.xpath("//button[@id='classGroupOkButton']");

	// Group Customer Class Group Submit objects
	public By oipa_GC_ClassGroups_LeftLink = By.xpath("//li[@id='classgroup']//span[contains(text(),'Class Groups')]");
	public By oipa_GC_ClassGroups_AsOfDate = By.xpath("//input[@id='classGroupAsOfDate']");
	public By oipa_GC_ClassGroups_RefreshButton = By.xpath("//button[@id='refreshButton']");
	public By oipa_GC_ClassGroups_RowExpandIcon = By.xpath("//div[@id='middleContent']//div[@class='oj-collapsible-header']//a");
	public By oipa_GC_ClassGroups_RowExpand_ReturnApplicationButton = By.xpath("//button[contains(@data-bind,'returnToApplication')]");
	public By oipa_GC_ClassGroups_SubmitButton = By.xpath("//button[@id='submit1']");

	// Group Customer Enrollment Objects

	// Objects added by Sushma
	// Group Customer ChildClass creation Objects
	public By oipa_GC_ClassGroups_ClassGroupRowExpander = By.xpath("//table//tbody//tr[1]//td[2]");
	public By oipa_GC_ClassGroups_ClassesLink = By.xpath("//strong//a[@id='classes']");
	public By oipa_GC_ClassGroups_NewClass = By.xpath("(//button[@title='New'])[1]");
	public By oipa_GC_ClassGrooups_ChildClass_ClasstypeDD = By.xpath("//div[@id='oj-select-choice-TypeCode']/a");
	public By oipa_GC_ClassGroups_ChildClass_ClassName = By.xpath("//input[@id='ClassName']");
	public By oipa_GC_ClassGroups_ChildClass_ClassDescription = By.xpath("//input[@id='ClassDescription']");
	public By oipa_GC_ClassGroups_ChildClass_SaveButton = By.xpath("//button[@id='okButton']");
	public By oipa_GC_ClassGroups_ChildClass_NameButton = By.xpath("(//button[@class='oi-treeNodeText oi-tree-button-text'])[2]");
	public By oipa_GC_ClassGroups_ClassRuleVariables = By.xpath("//strong//a[@id='classgrouprules']");
	public By oipa_GC_ClassGroups_AddVariableButton = By.xpath("//button[@id='addVariableButtonclassGroupRules']");
	public By oipa_GC_ClassRuleVariables_RuleName = By.xpath("//input[@id='mathTableNameInput0']");
	public By oipa_GC_ClassRuleVariables_RuleType = By.xpath("//div[@id='oj-select-choice-mathTableNameSelect1']/a");
	public By oipa_GC_ClassRuleVariables_RuleDataType = By.xpath("//div[@id='oj-select-choice-mathTableNameSelect2']/a");
	public By oipa_GC_ClassRuleVariables_RuleText = By.xpath("//input[@id='mathTableNameInput3']");
	public By oipa_GC_ClassRuleVariables_RuleSaveButton = By.xpath("//button[@id='saveRuleButton']");

	public By oipa_gc_classgroup_rule_name_data = By.xpath("//table[@id='mathvariablestable']//tbody//tr[1]//td[3]//span");
	public By oipa_gc_classgroup_rule_type_data = By.xpath("//table[@id='mathvariablestable']//tbody//tr[1]//td[4]//span");
	public By oipa_gc_classgroup_rule_datatype_data = By.xpath("//table[@id='mathvariablestable']//tbody//tr[1]//td[5]//span");
	public By oipa_gc_classgroup_rule_text_data = By.xpath("//table[@id='mathvariablestable']//tbody//tr[1]//td[6]//span");
	public By oipa_GC_ClassGroup_ParentClassLink = By.xpath("(//button[@class='oi-treeNodeText oi-tree-button-text'])[1]");
	public By oipa_GC_ClassGroup_PlanCoverageLink = By.xpath("//a[@id='classsubplan']");
	public By oipa_GC_ClassGroup_Assosiate = By.xpath("//a[@id='plancoverage']");
	public By oipa_GC_ClassGroup_Coverage_MoveButton = By.xpath("//button[@id='addALLToTarget']");
	public By oipa_GC_ClassGroup_Coverage_TypeDD = By.xpath("//div[@id='oj-select-choice-typeCode']//a");
	public By oipa_GC_ClassGroup_Coverage_Typetext = By.xpath("//input[@id='associationEffectiveDate']");
	public By oipa_GC_ClassGroup_Coverage_SaveButton = By.xpath("//button[@id='savePlanCoverageButton']");

	// Membership Rule Objects
	public By oipa_GC_ClassGroup_Classes_MembershipRulesLink = By.xpath("//a[@id='classrule']");
	public By oipa_GC_ClassGroups_Classes_MembershipVariables = By.xpath("//ul[@id='sourcelistview']//li[@id='0']");
	public By oipa_GC_ClassGroup_Classes_RulesMoveButton = By.xpath("//button[@id='classRulesShuttle']");
	public By oipa_GC_ClassGroup_Classes_SaveButton = By.xpath("//button[@id='saveButton']");

	// ChildClass Plan Coverage Objects
	public By oipa_GC_ChildClassGroup_ClassLink = By.xpath("(//button[@class='oi-treeNodeText oi-tree-button-text'])[2]");
	public By oipa_GC_ClassGroups_SubmitIcon = By.xpath("//button[@id='submit1']");

	public By oipa_GC_CopyClassGroups_ContractExpandIcon = By.xpath("(//li[@id='CNTR']//ins)[1]");
	public By oipa_GC_Customer_CustomerNumber = By.xpath("//input[@id='CustomerNumber']");
	
	// Group Customer Activate Plan Objects
	public By oipa_gc_plans_sidelist = By.id("customerplans");
	public By oipa_gc_Plans_expander = By.xpath("//div[@class='oj-collapsible-header']//a[1]");
	public By oipa_gc_Plans_submit = By.id("submit1");
	public By oipa_gc_Plans_ok = By.xpath("(//span[text()='OK'])[1]");
	public By oipa_gc_Plans_process = By.xpath("//button[@title='Process']");

  	
}
