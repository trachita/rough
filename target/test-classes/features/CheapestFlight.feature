Feature: Search Cheapest Flight on phptravel

  @flightSearch
  Scenario Outline: Search for the cheapest flight
    Given launch browser '<browserName>'
    When I visit "https://www.phptravels.net" website
    And I click FLIGHTS
    And I select from "London City Arpt"
    And I select to "Dubai Intl Arpt"
    And I select Return Trip
    #For the below date select, please use the the date picker
    And I select departure date 2 weeks from today's date
    And I select return date 2 weeks from departure date
    And I select 2 Adult
    And I select 2 Child
    When I click SEARCH button
    And I filter by the following flight carrier
      | Malaysia Airlines  |
      | Tunisair           |
      | Air France         |
      | Air Astana         |
      | Singapore Airlines |
      | Air Italy          |
      | Emirates           |
      | Virgin Atlantic    |
    And I click on BOOK NOW with the cheapest price
    Then I am taken to booking page

    Examples: 
      | browserName |
      | chrome      |
      | firefox     |
