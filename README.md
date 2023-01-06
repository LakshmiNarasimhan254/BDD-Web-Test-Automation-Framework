
# BDD Web Test Automation Framework   

## Description
This is a BDD Style test automation framework for web application using Selenium,Java and cucumber.
Please read the document for its features, capability and usage 


## Libraries Used:
    1. Selenium - Web Automation
    2. Cucumber -   Feature file for BDD Style
    3. Extent Reports - Reporting Library to generate beautiful html reports
    4. WebDriverManager - Executables management
    5. Apache poi -To control the text execution run and test data in excel
    

## Capabilities:
    1. Configuration can be changed from config. properties inside src/test/resources
    2. Step wise execution and screenshot are stored separately for every run apart from the 
       reports generated through extent reports and html reports 
    3. Log4j logs are also captured for every step
          
   
## Adding more tests:
        Prerequisites: Java 8+, Cucumber Plugin ,Maven 3.6.3 Installed and path set
    1. Tests should be added as per the convention followed. 
    2. New testng tests should be created as feature file/scenario under the src/test/resources
       /features folder.
    3. The correspoding step defnition to be defined in sharedStepDef.java in src/test/java/com.mln
    4. All the tests should have an Assertion. Have maximum of one to two assertions per test.
    

## Including a Test 
    1. Updated the TestRunner.java under src/test/java/com.mln.runners with corresponding feature file/files to be included 

## Running Tests:
        Prerequisites: Java 8+, Maven 3.6.3 Installed and path set
    1. There are multiple ways to run the tests. During development phase you can use the testng.xml present in the root folder to run. Right click and choose run.
    2. run the mvn clean test - To run all the tests available runner file

## Final Notes:
I have used AutomationPractice.com as the web application under test 

### Questions:
For any further clarification - please reach out to Lakshminarasimhan254@gmail.com

    
