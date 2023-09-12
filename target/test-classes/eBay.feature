Feature: Apply filters and Search product on eBay

  Scenario: Apply screen size, price and item location filters
    Given I navigate to www.ebay.com
    When I navigate to Search by category > Electronics > Cell Phones & accessories
    And I click Cell Phones & Smartphones in the left-hand side navigation section
    And I scroll down to "All listings"
    And I click "All filters"
    And I select screen size as "5.5 inches"
    And I select price range as "\$100 - \$200"
    And I select item location as "United States"
    And I now click "Apply"
    Then I should see the filter tags applied
    
  Scenario: Search for MacBook in Computers/Tablets & Networking category
    Given I am on the eBay homepage
    When I type "MacBook" in the search bar
    And I select the "Computers/Tablets & Networking" category
    And I click the "Search" button
    Then the page should load completely
    And the first result name should match with the search string
    
