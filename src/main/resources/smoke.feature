Feature: Smoke
  As a user
  I want to test the main functionality of the site
  So that I can be sure that the site works correctly

 Scenario Outline: The user adds an item to the cart and compares the amount of the item
   Given User opens '<homePage>' page
   And User searches for a product by its name '<product>'
   And User clicks search button
   And User checks that the search field is visible
   And User enters the brand name '<brand>' in the search field
   And User checks that the checkbox is visible and click on first one
   And User clicks on the filter dropdown
   And User selects from expensive to cheap
   When User adds the first product from the list to the cart
   And User opens a shopping cart
   Then User compares the given price '<sum>' with the price of the item in the cart

   Examples:
   | homePage                | product    | brand   | sum   |
   | https://rozetka.com.ua/ | ноутбук    | HP      | 50000 |
   | https://rozetka.com.ua/ | телефон    | samsung | 41000 |
   | https://rozetka.com.ua/ | навушники  | razer   | 9500  |
   | https://rozetka.com.ua/ | перфоратор | bosch   | 28000 |
   | https://rozetka.com.ua/ | пилосос    | Philips | 18000 |
