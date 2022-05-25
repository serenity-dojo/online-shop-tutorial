Feature: Viewing the home page
  In order to buy a product I like
  As a busy online shopper
  I would like to see the range of available products in the store on the application home page

  Scenario: Viewing highlighted products on the home page
    Given Sherry has opened the application home page
    When she views the highlighted products
    Then she should see an image, title and price for each product

  Scenario Outline: Home page should allow navigation to other parts of the store
    Given Sherry has opened the application home page
    When she navigates to the <Section> section
    Then she should be shown the <Section URL> page
    Examples:
      | Section  | Section URL      |
      | All      | /search          |
      | Apparel  | /search/apparel  |
      | Shop All | /search/shop-all |
