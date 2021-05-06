Feature: Test

  Background:
    Given I open web browser
      | source | browser |
      | Native | chrome  |
    When I login to AL account
      | username                              | password                              |
      | native+automation12@activelylearn.com | native+automation12@activelylearn.com |

  @AL @Catalog @Common @1
  Scenario Outline: verify the the catalog filter functionality
    Then I verify catalog filters are working successfully for "<gradelevel>", "<lexilelevel>"

    Examples:
      | gradelevel      | lexilelevel  |
      | 7th - 8th grade | 955L - 1155L |