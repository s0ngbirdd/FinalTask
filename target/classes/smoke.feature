Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  #Scenario Outline: Check wishlist amount
    #Given User opens '<homePage>' page
    #When User makes search by keyword '<keyword>'
    #And User clicks search button
    #And User adds all products on page to wishlist
    #And User clicks on wishlist page
    #Then User checks that amount of products in wishlist are '<amountOfProducts>'

    #Examples:
     # | homePage              | keyword | amountOfProducts |
     # | https://www.asos.com/ | nike    | 72               |

  Scenario Outline: Check wishlist text message after cleaning
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User adds all products on page to wishlist
    And User clicks on wishlist page
    And User deletes all products from wishlist
    Then User checks that '<textMessage>' message is displayed

    Examples:
      | homePage              | keyword | textMessage                           |
      | https://www.asos.com/ | nike    | You have no Saved Items               |

  #Scenario Outline: Check sign in with invalid email
    #Given User opens '<homePage>' page
    #And User clicks on the sign in button
    #When User enter '<email>' into email field
    #Then User can see the '<errorMessage>' message

    #Examples:
      #| homePage              | email | errorMessage                                                       |
      #| https://www.asos.com/ | @ww   | Email fail! Please type in your correct email address              |

  #Scenario Outline: Check sign in with invalid email
    #Given User opens '<homePage>' page
    #And User clicks on the sign in button
    #When User enter '<email>' into email field
    #Then User can not see the '<errorMessage>' message

    #Examples:
      #| homePage              | email            | errorMessage                                                       |
      #| https://www.asos.com/ | test@gmail.com   | Email fail! Please type in your correct email address              |

  #Scenario Outline: