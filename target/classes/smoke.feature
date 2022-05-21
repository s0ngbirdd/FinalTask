Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check wishlist amount
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User adds all products on page to wishlist
    And User clicks on wishlist page
    Then User checks that amount of products in wishlist are '<amountOfProducts>'

    Examples:
      | homePage              | keyword | amountOfProducts |
      | https://www.asos.com/ | Adidas    | 72               |

  Scenario Outline: Check wishlist text message after cleaning
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User adds all products on page to wishlist
    And User clicks on wishlist page
    And User deletes all products from wishlist
    Then User checks that '<textMessage>' message is displayed

    Examples:
      | homePage              | keyword | textMessage             |
      | https://www.asos.com/ | nike    | You have no Saved Items |

  Scenario Outline: Check sign in with invalid email
    Given User opens '<homePage>' page
    And User clicks on sign in button
    When User enter '<email>' into email field
    Then User can see '<errorMessage>' error message

    Examples:
      | homePage              | email | errorMessage                                          |
      | https://www.asos.com/ | @ww   | Email fail! Please type in your correct email address |

  Scenario Outline: Check sign in with valid email
    Given User opens '<homePage>' page
    And User clicks on sign in button
    When User enter '<email>' into email field
    Then User can not see error message

    Examples:
      | homePage              | email          |
      | https://www.asos.com/ | test@gmail.com |

  Scenario Outline: Check total products number in the cart
    Given User opens '<homePage>' page
    And User click on Women page
    And User clicks on biggest deals button
    And User adds all products on page to wishlist
    And User clicks on wishlist page
    When User adds all products to cart
    And Clicks on cart button
    Then User can see total products number

    Examples:
       | homePage              |
       | https://www.asos.com/ |

  Scenario Outline: Check that nothing matches your search
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User clicks search button
    Then User can see '<textMessage>' message

    Examples:
      | homePage              | keyword    | textMessage                 |
      | https://www.asos.com/ | dddddddddd | NOTHING MATCHES YOUR SEARCH |

  Scenario Outline: Check language change
    Given User opens '<homePage>' page
    When User change country to France
    Then User can see '<text>' text on Help & FAQs button

    Examples:
      | homePage              | text        |
      | https://www.asos.com/ | Aide et FAQ |

  Scenario Outline: Check number of products on search page
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User clicks search button
    Then User can see '<number>' number of products on search page

    Examples:
      | homePage              | keyword | number |
      | https://www.asos.com/ | Adidas    | 72     |

  Scenario Outline: Check number of topics on help page
    Given User opens '<homePage>' page
    When User clicks Help & FAQs button
    Then User can see '<topicNumber>' topics

    Examples:
      | homePage              | topicNumber |
      | https://www.asos.com/ | 6           |

  Scenario Outline: Check number of questions on delivery questions page
    Given User opens '<homePage>' page
    And User clicks Help & FAQs button
    When user clicks on Delivery topic page
    Then User can see '<questionNumber>' on page

    Examples:
      | homePage              | questionNumber |
      | https://www.asos.com/ | 12             |