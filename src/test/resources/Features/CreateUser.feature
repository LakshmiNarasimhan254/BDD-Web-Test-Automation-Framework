#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: This is a feature file for Creating an user

  @functional @Smoke
  Scenario:TCS1
   #To give a email id to create a user
    Given The user is in MyStore Page
    When Clicks on <Sign in> in MyStore Page
    Then the user should be navigated to <Login_MyStorePage>
    And Enters the <ExcelData|UserEmail> in <Email address> in Login_MyStore Page
    And Clicks on <Create Email> in Login_MyStore Page
    Then the user should be navigated to <Login_MyStorePageExt>
    When The user Clicks on <MR> in Login_MyStoreExt Page
    And User enters the value <First name:=ExcelData|First name,Last name:=Mohan,Password:=Password@123,Date Of Birth:=25-04-1988,Address First Name:=Lakshmi,Address Last Name:=Mohan,Company:=SRI Tech Solutions,Address Line1:=9811 Fan Palm Way,Address Line2:= 1st Street ,City:=Tampa,State:=Florida,Country:=United States,Zip/Postal Code:=33610,Additional information:=Additional Info,Home phone:= 1111111112,Mobile phone:=8137163942,Assign an address alias for future reference:=MyAddress> in Login_MyStoreExt Page
  #	And The user Clicks on <Register> in Login_MyStoreExt Page
  	

  @functional @Smoke
  Scenario:TCS2 
  #To give a email id to create a users
    Given The user is in MyStore Page
    When Clicks on <Sign in> in MyStore Page
    Then the user should be navigated to <Login_MyStorePage>
    And Enters the <MyTestset1@gmail.com> in <Email address> in Login_MyStore Page
    And Clicks on <Create Email> in Login_MyStore Page
    Then the user should be navigated to <Login_MyStorePageExt>
    When The user Clicks on <MR> in Login_MyStoreExt Page
    And User enters the value <First name:=ExcelData|First name,Last name:=Mohan,Password:=Password@123,Date Of Birth:=25-04-1988,Address First Name:=Lakshmi,Address Last Name:=Mohan,Company:=SRI Tech Solutions,Address Line1:=9811 Fan Palm Way,Address Line2:= 1st Street ,City:=Tampa,State:=Florida,Country:=United States,Zip/Postal Code:=33610,Additional information:=Additional Info,Home phone:= 1111111112,Mobile phone:=8137163942,Assign an address alias for future reference:=MyAddress> in Login_MyStoreExt Page
  #	And The user Clicks on <Register> in Login_MyStoreExt Page