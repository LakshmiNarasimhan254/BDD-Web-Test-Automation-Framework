Feature: To click on my Popular Products

  @smoke
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
    
    @functional 
  Scenario: TCS4
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
