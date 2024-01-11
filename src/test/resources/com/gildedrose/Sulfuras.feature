Feature: Sulfuras quality

  Scenario Outline: the quality is not decreased
    Given an <item> with quality <quality> to be sold by <sell-by> days
    When a day passes
    Then the quality should remain the same
    And the sell-by date should remain the same

    Examples:
      | item                       | sell-by | quality |
      | Sulfuras, Hand of Ragnaros | 0       | 80      |
      | Sulfuras, Hand of Ragnaros | -1      | 80      |
      | Sulfuras, Hand of Ragnaros | 1       | 49      |
