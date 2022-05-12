Feature: Viewing the home page
  In order to buy a product I like
  As a busy online shopper
  I would like to see the range of available products in the store on the application home page

  Scenario: Viewing highlighted products on the home page
    Given Sherry has opened the application home page
    When she views the highlighted products
    Then she should see an image, title and price for each product

  Scenario: Home page should allow navigation to other modules
