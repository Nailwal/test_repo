Feature: Verification of Add to cart feature

  Scenario: Verify the cart updated value
    Given Navigate to ebay.com
    When Search for "book" and click on the first option
    And click on add to cart button
    Then verify the added cart items