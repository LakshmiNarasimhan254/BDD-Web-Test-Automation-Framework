Feature: To click on my Popular Products

    @functional
  Scenario: TCS3
    #To click on my Popular Products
    Given The user is in MyStore Page
    When Clicks on <Blouse> in MyStore Page
    Then Verify if the <Blouse> Quickview is displayed
    And Verify if the <Name:=Blouse,Model Name:=demo_2,Condition:=New,Price:=$27> are displayed in Quickview
    When Enter <Quantity:=2,Size:=M,color:=black> in Quickview
    And Clicks on <Add to Cart> in Quickview
    Then Verify if the Layared_Cart Page is displayed
    And Verify if the successmessage is displayed in Layared_cart Page
    And Verify the details <Name:=Blouse,Quantity:=2,Size:=M,color:=black,Price:=$54>  in Layared_Cart Page
    When Clicks on <Proceed to Checkout> in Layared_Cart Page
    Then Verify if the user is in Cart_Summary Page
    
   @smoke   @functional 
  Scenario: TCS4
    #To Add to cart via click on my Popular Products in Add to cart
    Given The user is in MyStore Page
    When Clicks on <Blouse> in MyStore Page
    Then Verify if the <Blouse> Quickview is displayed
    And Verify if the <Name:=Blouse,Model Name:=demo_2,Condition:=New,Price:=$27.00> are displayed in Quickview
    When Enter <Quantity:=2,Size:=M,color:=black> in Quickview
    And Clicks on <Add to Cart> in Quickview
    Then Verify if the Layared_Cart Page is displayed
    And Verify if the successmessage is displayed in Layared_cart Page
    And Verify the details <Name:=Blouse,Quantity:=2,Size:=M,color:=Black,Price:=$54.00>  in Layared_Cart Page
    When Clicks on <Continue Shopping> in Layared_Cart Page
    When Clicks on <Printed Dress> in MyStore Page
    Then Verify if the <Printed Dress> Quickview is displayed
    And Verify if the <Name:=Printed Dress,Model Name:=demo_3,Condition:=New,Price:=$26> are displayed in Quickview
    When Enter <Quantity:=1,Size:=L,color:=Orange> in Quickview
    And Clicks on <Add to Cart> in Quickview
    Then Verify if the Layared_Cart Page is displayed
    And Verify if the successmessage is displayed in Layared_cart Page
  # And Verify the details <Name:=Blouse,Quantity:=2,Size:=M,color:=black,Price:=$54>  in Layared_Cart Page
    When Clicks on <Proceed to Checkout> in Layared_Cart Page
    Then Verify if the user is in Cart_Summary Page
    And Verify if the <1.Name:=Blouse,1.size:=M,1.AVAIL:=In stock|2.Name:=Printed Dress,2.color:=Orange,2.product total:=$26.00> are displayed in Cart_Summary Page
		When Clicks on <Proceed to Checkout> in Cart_Summary Page
		
		
		 @functional 
		  Scenario: TCS6
    #To Add to cart via click on my Popular Products in Add to cart
    Given The user is in MyStore Page
    When Clicks on <Blouse> in MyStore Page
    Then Verify if the <Blouse> Quickview is displayed
    And Verify if the <Name:=Blouse,Model Name:=demo_2,Condition:=New,Price:=$27.00> are displayed in Quickview
    When Enter <Quantity:=2,Size:=M,color:=black> in Quickview
    And Clicks on <Add to Cart> in Quickview
    Then Verify if the Layared_Cart Page is displayed
    And Verify if the successmessage is displayed in Layared_cart Page
    And Verify the details <Name:=Blouse,Quantity:=2,Size:=M,color:=Black,Price:=$54.00>  in Layared_Cart Page
    When Clicks on <Continue Shopping> in Layared_Cart Page
    When Clicks on <Printed Dress> in MyStore Page
    Then Verify if the <Printed Dress> Quickview is displayed
    And Verify if the <Name:=Printed Dress,Model Name:=demo_3,Condition:=New,Price:=$26> are displayed in Quickview
    When Enter <Quantity:=1,Size:=L,color:=Orange> in Quickview
    And Clicks on <Add to Cart> in Quickview
    Then Verify if the Layared_Cart Page is displayed
    And Verify if the successmessage is displayed in Layared_cart Page
  # And Verify the details <Name:=Blouse,Quantity:=2,Size:=M,color:=black,Price:=$54>  in Layared_Cart Page
    When Clicks on <Proceed to Checkout> in Layared_Cart Page
    Then Verify if the user is in Cart_Summary Page
    And Verify if the <1.Name:=Blouse,1.size:=M,1.AVAIL:=In stock|2.Name:=Printed Dress,2.color:=Orange,2.product total:=$26.00> are displayed in Cart_Summary Page
		When Clicks on <Proceed to Checkout> in Cart_Summary Page
		And User is in loginpage
    When User enters username and password
      | username          | password     |
      | lakme88@gmail.com | Password@123 |
    And clicks on login
   # Then user should be navigated to home page
		