package BillRunFirst;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import Re_usable.Assertion_Test;
import Re_usable.Repo_Test;
import Re_usable.Sauce_Instance_Login_Test;

public class New_reminder_functionality extends Sauce_Instance_Login_Test {
	public String custid="";
	static String mno="";
	 public String stno;
	JavascriptExecutor executor;
	public String customerNumber;
	public String Statements2;
	
  @Test(priority=1)
  public void autogenrate_Four_plans() throws Exception {
	  action_obj = new Repo_Test(driver);
		Assertion_obj = new Assertion_Test(driver);
		//plan_mainmebu click 
		action_obj.plan_mainmebu().click();
		//find autogenrated plans 
	WebElement searchplan=action_obj.SearchPlan();
	searchplan.clear();
	searchplan.sendKeys("autogenerated");
	Thread.sleep(2000);
// gass plan Search 
	WebElement searchplan1=action_obj.SearchPlan();
	searchplan1.clear();
	searchplan1.sendKeys("Gas Flat Template Plan");
	Thread.sleep(2000);
	
	Assertion_obj.GasPlan();
	
// peak,off Peak, Shoulder plan Search 
		WebElement searchplan2=action_obj.SearchPlan();
		searchplan2.clear();
		searchplan2.sendKeys("Electricity Peak/Off Peak/Shoulder Template Plan");
		Thread.sleep(2000);
	Assertion_obj.peak_ofpeak_Elec1();
//Electricity plan
	   WebElement searchplan3=action_obj.SearchPlan();
	   searchplan3.clear();
	   searchplan3.sendKeys("Electricity Flat Template Plan");
	   Thread.sleep(2000);
	Assertion_obj.ElectricityPlan_Elec2();
	//Water plan
	   WebElement searchplan4=action_obj.SearchPlan();
	   searchplan4.clear();
	   searchplan4.sendKeys("Water Flat Template Plan");
	   Thread.sleep(2000);
	   
Assertion_obj.waterplan();

  }
 
  @Test(priority = 2)
  public void Customer_Services_and_mtrNO() throws Exception {
		action_obj = new Repo_Test(driver);
		Assertion_obj = new Assertion_Test(driver);
		driver.navigate().refresh();
		
 System.out.println(":--------------:Create Customers:-----------------------:");
		Thread.sleep(2000);
		
		//customer main menu
		action_obj.customer_main_menu().click();
		//enter customer name
		action_obj.customer_F_name().sendKeys("Mark");
		//last name
		action_obj.customer_sure_name().sendKeys("Anderson");
		//select category
		Select select = new Select(action_obj.Category_drop());
			//enter customer address1
		action_obj.cus_address1().sendKeys("Lombard Street");
		//enter customer address2
		action_obj.cus_address2().sendKeys("San Francisco");
		//enter state
		action_obj.billingState().sendKeys("CA");
		//click toggle button
		action_obj.toggle_button().click();
		//enter country
		action_obj.customer_country().sendKeys("US");
		//mobile 
		action_obj.Phone_Mobile().sendKeys("9948545875");
		//select plan
	//	Select select2 = new Select(action_obj.select_plan());
		//select2.selectByVisibleText("Multiple Tariff Plan");
	//	select2.selectByVisibleText("Multiple");
		//enter email
		action_obj.Email().sendKeys("sampro97@yopmail.com");
		action_obj.BEmail().sendKeys("sampro97@yopmail.com");
		//scroll
		JavascriptExecutor executor=(JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,1500)", "");
		//save customer
		action_obj.save_customer().click();
		//ok
		action_obj.ok().click();
		
		//validate message
		Assertion_obj.sucessfully_saved_customer_message_validation();
		//get customer number
		customerNumber=driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div[2]/div[4]/form[1]/div/div/div[1]/div[2]/div[1]/label/a")).getText();
//		customerNumber1=driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div[2]/div[4]/form[1]/div/div/div[1]/div[2]/div[1]/label/a")).getText();
//		customerNumber2=driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div[2]/div[4]/form[1]/div/div/div[1]/div[2]/div[1]/label/a")).getText();
		Thread.sleep(3000);
		
		
		System.out.println("**********************Add Electricity service(Electricity Residential plan)**********************"); 
		
		
		//click on overview menu
		action_obj.overview_menu().click();
		//ele + icon
		action_obj.electricity_plus_icon().click(); 
		//validate page header
		Assertion_obj.Electricity_service_page_header_validation(); 
		//select plan
		Select mydrpdwn = new Select(action_obj.plan_drop_down()); 
      
		///////updation required
		//mydrpdwn.selectByVisibleText("Multiple Tariff Plan");// 17 nov changed on Electricity Flat Template Plan
		
		mydrpdwn.selectByVisibleText("Electricity Peak/Off Peak/Shoulder Template Plan");
		//date format for editing the string value    
		DateFormat dateFormat = new SimpleDateFormat("HHmmss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		mno=cal.getTime().toString(); 
		System.out.println(mno=mno.substring(11,19));
		mno=mno.replaceAll(":","");
		
		//enter meter number
		action_obj.meter_number().sendKeys("108_"+mno); 
		//select meter configuration type
		Select mydrpdwn1 = new Select(action_obj.meterConfig_drop_down()); 
		mydrpdwn1.selectByVisibleText("Peak, Off Peak, Shoulder"); 
		//Select read type   786
		Select mydrpdwn2 = new Select(driver.findElement(By.id("readType")));
		mydrpdwn2.selectByVisibleText("Reads");
		Thread.sleep(2000);
		//toggle button
		action_obj.toggle_button().click(); 

		//save service
		executor.executeScript("window.scrollBy(0,1500)", "");
		executor.executeScript("javascript:sendForm();",action_obj.create_service_button());

		//ok
		action_obj.ok().click(); 
		//validate overview text
		Assertion_obj.Overview_text_Validation(); 
		
		Thread.sleep(3000);
		
     System.out.println(":-----------------:Add Meter Read:-------------------:");
		
		//**********************Meter Reads **********************
		
		
		//meter read main menu
		action_obj.Meter_Reads_mainMenu().click();
		//select meter no for reading
		driver.navigate().refresh();
		Thread.sleep(700);
		Select mydrpdwn2s = new Select(action_obj.MeterNoForMeterReading());
		Thread.sleep(2000);
		mydrpdwn2s.selectByVisibleText("108_"+mno);
		//validate view read text
		Assertion_obj.viewRead_Text_validation();
		//add read button
		Thread.sleep(2000);
		action_obj.addReadButton().click();
		//validate add meter read text
		Assertion_obj.AddMeterReadPopupText_validation();
		//select reading type
		Select mydrpdwn25 = new Select(action_obj.readTypeDropDown());
		mydrpdwn25.selectByVisibleText("Initial");
//		String vali_str=mydrpdwn25.getFirstSelectedOption().getText();
		//select today date
		action_obj.Meter_readdate().click();
		Thread.sleep(2000);
		action_obj.monthsfirstdate().click();
				
		//action_obj.select_today_date().click();
	
		//enter reading  for Flat rate
		//action_obj.Meter_Read().sendKeys("10");
		//save read button
		//enter meater read (peak)
		action_obj.peak_MeaterRead().sendKeys("10");
		//Meter Read (Off Peak)
		action_obj.OffPeak_MeaterRead().sendKeys("20");
		//shoulder Meare Reading
		action_obj.shoulder_MeaterRead().sendKeys("30");
		
		
		Thread.sleep(2000);
		
		
//		executor.executeScript("javascript:saveRead();",  action_obj.save_Read_button());
		
		action_obj.save_Read_button().click();
		
		//validate by read type text selected in dropdown
	//	Assert.assertEquals(driver.findElement(By.xpath("(.//*[text()='"+vali_str+"'])[1]")).getText(),vali_str);
      Select mydrpdwn14 = new Select(action_obj.MeterNoForMeterReading());
		Thread.sleep(2000);
		mydrpdwn14.selectByVisibleText("108_"+mno);
		//validate view read text
		Assertion_obj.viewRead_Text_validation();
		Thread.sleep(2000);
      //add read button
		action_obj.addReadButton().click();
		//validate add meter read popup message
		Assertion_obj.AddMeterReadPopupText_validation();
		//select read type
		Select mydrpdwn25a = new Select(action_obj.readTypeDropDown());
		mydrpdwn25a.selectByIndex(1);      
		////////////3
		Thread.sleep(2000);
//		String vali_str1=mydrpdwn25a.getFirstSelectedOption().getText();
		
		//click on merter read date field
		action_obj.Meter_readdate().click();
		//click on next button of calander
		//action_obj.next_button_calander().click();
		//select any random date
		//action_obj.select_end_date_as().click();
		//meter read enter
		action_obj.monthslastdate().click();
		//action_obj.Meter_Read().sendKeys("120");
		
		//enter meater read (peak)
		action_obj.peak_MeaterRead().sendKeys("10");
		//Meter Read (Off Peak)
		action_obj.OffPeak_MeaterRead().sendKeys("20");
		//shoulder Meare Reading
		action_obj.shoulder_MeaterRead().sendKeys("30");
		
		
		
		//save read button
		action_obj.save_Read_button().click();
		//validate by read type text selected in dropdown
//	Assert.assertEquals(driver.findElement(By.xpath("(.//*[text()='"+vali_str1+"'])[1]")).getText(),vali_str1);
		//validate view read text
		Assertion_obj.viewRead_Text_validation();
		driver.navigate().refresh();
      Thread.sleep(5000);
 
 System.out.println(":---------------: Add Customer Services(Water Flat Template Plan))  :---------------------:");

 //click on overview menu
		action_obj.overview_menu().click(); 
		
		//Select Water Service
		action_obj.waterIcon().click();
	//Assertion on Water Service 
  Assertion_obj.water_service_page_header_validation();  
  Select mydrpdwn11 = new Select(action_obj.plan_drop_down()); 
  
	///////updation required
  
	//mydrpdwn.selectByVisibleText("Multiple Tariff Plan");// 17 nov changed on Electricity Flat Template Plan
	
	mydrpdwn11.selectByVisibleText("Water Flat Template Plan");
			
	//enter meter number
	action_obj.meter_number().sendKeys("107_"+mno); 
	//select meter configuration type
	Select mydrpdwn12 = new Select(action_obj.meterConfig_drop_down()); 
	mydrpdwn12.selectByVisibleText("Peak, Off Peak, Shoulder"); 
	//Select 'Reading Type'
	Select mydrpdwn13 = new Select(driver.findElement(By.id("readType")));
	mydrpdwn13.selectByVisibleText("Reads");
	//save service
	executor.executeScript("window.scrollBy(0,1500)", "");
	executor.executeScript("javascript:sendForm();",
	action_obj.create_service_button());

	//ok 
	action_obj.ok().click(); 
	//validate overview text
	Assertion_obj.Overview_text_Validation(); 
	
	//add Meter Reads 
	
	//meter read main menu
	action_obj.Meter_Reads_mainMenu().click();
	//select meter no for reading
	driver.navigate().refresh();
	Thread.sleep(700);
	Select mydrpdwn140 = new Select(action_obj.MeterNoForMeterReading());
	Thread.sleep(2000);
	mydrpdwn140.selectByVisibleText("107_"+mno);
	//validate view read text
	Assertion_obj.viewRead_Text_validation();
	//add read button
	Thread.sleep(2000);
	action_obj.addReadButton().click();
	//validate add meter read text
	Assertion_obj.AddMeterReadPopupText_validation();
	//select reading type
	Select mydrpdwn321 = new Select(action_obj.readTypeDropDown());
	mydrpdwn321.selectByVisibleText("Initial");
//	String vali_str=mydrpdwn25.getFirstSelectedOption().getText();
	//select today date
	action_obj.Meter_readdate().click();
	Thread.sleep(2000);
	
	action_obj.monthsfirstdate().click();
	//action_obj.select_today_date().click();

	//enter reading 
	//action_obj.Meter_Read().sendKeys("10");
	//save read button
	//enter meater read (peak)
			action_obj.peak_MeaterRead().sendKeys("10");
			//Meter Read (Off Peak)
			action_obj.OffPeak_MeaterRead().sendKeys("20");
			//shoulder Meare Reading
			action_obj.shoulder_MeaterRead().sendKeys("30");
	
  Thread.sleep(3000);
   action_obj.save_Read_button().click();
	 
	
 Assertion_obj.viewRead_Text_validation();

	
	//add read button
  
	Thread.sleep(2000);
	action_obj.addReadButton().click();
	//validate add meter read popup message
	Assertion_obj.AddMeterReadPopupText_validation();
	
	//select read type water Service
	Select mydrpdwn123 = new Select(action_obj.readTypeDropDown());
	mydrpdwn123.selectByIndex(1);      
	
	
	//click on merter read date field
	action_obj.Meter_readdate().click();
	//click on next button of calander
	action_obj.next_button_calander().click();
	//select any random date
	action_obj.monthslastdate().click();
	
	//action_obj.select_end_date_as().click();
	//meter read enter
	//action_obj.Meter_Read().sendKeys("120");
	//enter meater read (peak)
			action_obj.peak_MeaterRead().sendKeys("10");
			//Meter Read (Off Peak)
			action_obj.OffPeak_MeaterRead().sendKeys("20");
			//shoulder Meare Reading
			action_obj.shoulder_MeaterRead().sendKeys("30");
	
	//save read button
	action_obj.save_Read_button().click();
	//validate by read type text selected in dropdown
	//Assert.assertEquals(driver.findElement(By.xpath("(.//*[text()='"+vali_str1+"'])[1]")).getText(),vali_str1);
	//validate view read text
	Assertion_obj.viewRead_Text_validation();
	Thread.sleep(2000);
	driver.navigate().refresh();
  Thread.sleep(8000);
  }
  @Test(priority = 3)
	public void addOnceOffCharges() throws Exception {

		//charges main menu
		action_obj.ChargesMainMenu().click();
		//charges + icon
		JavascriptExecutor  executor = (JavascriptExecutor) driver;
		executor.executeScript("javascript:goAdd_Onceoff()",  action_obj.ChargesPlusIcon());
		//select service
		Select mydrpdown = new Select(action_obj.select_service());
		mydrpdown.selectByIndex(1);
		//get selected option
		String string=mydrpdown.getOptions().get(1).getText();
		System.out.println(string+"Second");
		Thread.sleep(5000);
		//select charges description
		action_obj.once_description().sendKeys("Onceoff- charge description");
		//Enter Roll up Description
		action_obj.rollupDescription().sendKeys("Rollup Description");

		//select today date
		action_obj.ChargeStartDate().click();
		action_obj.select_today_date().click();
		//click in end date charge field
		action_obj.ChargesEndDate().click();
		Thread.sleep(2000);
		//next button of calendar
		action_obj.next_button_calander().click();
		Thread.sleep(2000);
		//select any random date
		action_obj.monthslastdate().click();
		//action_obj.select_end_date_as().click();
		//enter charge unit
		action_obj.charges_unit().sendKeys("20");
		//enter charge rate
		action_obj.ChargesRate().sendKeys("20");
		//enter charge
		action_obj.Charges().sendKeys("200");
		//save button
		executor.executeScript("javascript:formsubmit();", action_obj.ChargesSaveButton());
		//select all option from dropdown
		Thread.sleep(5000); //sleep time
//		12 dec		Select  electMeter = new Select(action_obj.AllDrop_Down());
//		12 dec		electMeter.selectByIndex(1);// selectByVisibleText("All");
		//validate by meter number
		Thread.sleep(5000);
		String string1=driver.findElement(By.xpath(".//*[text()='"+string+"'] ")).getText();
		System.out.println(string1+"First");
		Assert.assertEquals(string1, string); 
		
		//String obj = driver.findElement(By.xpath(".//*[text()='Success!']")).getText(); //get the text which you want to assert
		 //	Assert.assertEquals("Success!", obj);
       // String onceoffcharg= driver.findElement(By.xpath(".//*[contains(text(),'Successfully once-off Charges')]")).getText();
		//  Assert.assertEquals(onceoffcharg, "Successfully once-off Charges");
		
	}

	@Test(priority = 4)
	public void addRecurringCharges() throws Exception {
	
		//charge main menu
		action_obj.ChargesMainMenu().click();
		//charges + icon
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("javascript:goAdd_Recurring();", action_obj.ChargesPlusIcon());
		//select service
		Select mydrpdown = new Select( action_obj.select_service());
		mydrpdown.selectByIndex(1);
		//select selected option
		String string=mydrpdown.getOptions().get(1).getText();
		Thread.sleep(15000);
		//select charges description
		Select mydrpdown1 = new Select(action_obj.RecurringChargesDesacriptionDrop());
		mydrpdown1.selectByIndex(1);//08 dec changed index value 1
		//select today date
		action_obj.ChargeStartDate().click();
		action_obj.select_today_date().click();
		//enter no of period 
		action_obj.No_of_period().sendKeys("1");
		//check ongoing check box
		action_obj.ongoing_CheckBox().click();
		Thread.sleep(3000);
		//save button
		executor.executeScript("javascript:formsubmit();", action_obj.ChargesSaveButton());
		Thread.sleep(14000);
		//select all option
//	12 dec Select  electMeter = new Select(action_obj.AllDrop_Down());
//	electMeter.selectByIndex(1);//changed with All//electMeter.selectByVisibleText("Usage charge"); 
		//validate by meter number
		Thread.sleep(5000);

		String string1=driver.findElement(By.xpath(".//*[text()='"+string+"'] ")).getText();
		Assert.assertEquals(string1, string);
		Thread.sleep(10000);
	  
	  
	} 
@Test(priority=5)
public void BillRun_Cycle_Customer() throws InterruptedException
{
// Search Customer for get latest three cusomtet id 

action_obj.admin_mainMenu().click();
	action_obj.billRunCycle().click();
	action_obj.createNewPlan().click();
	action_obj.cycleName().sendKeys(customerNumber);
	
	action_obj.cycleDropDown().click();
	//Select select = new Select(driver.findElement(By.xpath(".//*[@id='custnos']")));
	//select.selectByVisibleText(customerNumber+"- Chandan verma");
	//add one 
	driver.findElement(By.xpath(".//*[contains(span,'"+customerNumber+"')]")).click();
	Actions action3 = new Actions(driver);
	action3.sendKeys(Keys.ESCAPE).build().perform();
		
	action_obj.saveCycle().click();
//	Assertion_obj.cycleConfirmationMessage_text_assertion();
	action_obj.cycleID().isDisplayed();
}
@Test(priority =6)
public void Bill_Run_Customer() throws Exception
{
Thread.sleep(3000);
action_obj.bill_run_mainMenu().click();
Assertion_obj.Welcome_to_Bill_Run_Wizard_text_assertion();

JavascriptExecutor executor = (JavascriptExecutor)driver;
executor.executeScript("javascript:skipMeterBulkImport()", action_obj.run_the_bill_button());
action_obj.Recurring_harge_Date().click();
action_obj.select_today_date().click();
action_obj.issueDate().click();
action_obj.select_today_date().click();
action_obj.dueDate().click();
action_obj.select_today_date().click();

Select cycle = new Select(action_obj.cycleno());
cycle.selectByVisibleText(customerNumber );

action_obj.bill_run_button().click();
Thread.sleep(10000);
action_obj.viewButton().click();

Assertion_obj.Bill_Run_Results_text_assertion();


/*String no = driver.findElement(By.xpath("html/body/div[5]/div/div[2]/div[2]/div[2]/div[2]/"
		+ "div/div[2]/div[2]/div/div[3]/div/div/div[2]/div[1]/div[2]/div/div[1]/h2")).getText();

Thread.sleep(10000);
//commit button
action_obj.commitButton().click();
//click on continue commit
action_obj.Continue_commit().click();

//get commit message text
String commit= action_obj.commitmessage().getText();
Assert.assertEquals(commit, "Successfully committed statement no. "+no.split("#")[1]);
//click ok
action_obj.ok().click();*/
//get statement number 
		String no = driver.findElement(By.xpath(".//*[contains(text(),'Statement Summary #') ]")).getText();

		//select all check boxes from left side
		action_obj.select_allcheck().click();
		//click on email dropdown
		action_obj.emailDrop_down().click();
		//click on all customer for email
		JavascriptExecutor executor2 = (JavascriptExecutor)driver;
		executor2.executeScript("javascript:showEmailDialog('emailAll');", action_obj.allcutomerforemail());
		//click on continue email button
		action_obj.Continue_email().click();
		//close email pop up
		action_obj.closeemail().click();
		
		Thread.sleep(3000);
		//close bill run popup
		action_obj.closeBillrun().click();
		//yes button
		action_obj.yesButton().click();
		//click on bill run search button
		action_obj.billRunsearchButton().click();
		//enter statement number
		action_obj.searchnewcreatedUser().sendKeys(no.split("#")[1]);
		//click on view details icon
		action_obj.viewDetailsIcon().click();
		//validate header of the page
		String Actualtext04 = action_obj.statementPageHeader().getText();
		Assert.assertEquals(Actualtext04,no);
}
	
@Test(priority=7)
public void Reminder_section() throws Exception
{
	//admin main menu
 action_obj.admin_mainMenu().click(); 
		
		//click reminders button
			action_obj.Reminders_click().click();
	//Updates days under Reminders
		WebElement selectdays=action_obj.addreminderdays();
	 selectdays.clear();
	 selectdays.sendKeys("1");
	 //update button click
	 action_obj.UpdateReminder().click();
	 Thread.sleep(1000);
   //'OK'
	 action_obj.OKUpdatesfreminder().click();
	 Thread.sleep(15000);
	 Statements2=driver.findElement(By.xpath("html/body/div[5]/div[1]/div[2]/div[2]/div[3]/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td[2]/input")).getText();
	 Assertion_obj.ChangesReminder();
	 driver.navigate().refresh();
	 Thread.sleep(2000);
}
@Test(priority=8)
public void Generate_Reminders() throws Exception
{ 
//Outbox Button click
	  action_obj.outboxbutton().click();
	  
	  //assertion on Outbox section open
	  Assertion_obj.assertionoutbox();
	  
	  //Select generate Reminder button 
	  action_obj.generatereminderbtn().click();
	  //confirm section
	  action_obj.okConfirm_GenerateReminder().click();
	  
	 stno=driver.findElement(By.xpath(".//*[@id='outboxTable']/tbody/tr[1]/td[4]")).getText();
	
	 Thread.sleep(6000);
	 driver.navigate().refresh();
	 Thread.sleep(6000);
	  //select customer
	  action_obj.selectCustomer_outbox().click();  
	
	  //Window focus to new tab
		ArrayList<String> window_number = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(window_number.get(1));
	   //click on communication
	    
	    action_obj.communicationclick().click();
	    //assertion on customer record under Communication 
	    Assertion_obj.assertionrecordCUMMN();
}
 
}