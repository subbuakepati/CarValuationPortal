Feature: Car Details Check feature

  @test
  Scenario Outline: Verify Vehicle details
    Given I have list of Car Registration Numbers in the "<input_file>"
    And I have expected car details are there in the "<output_file>"
    When I check car details in the webuyanycar website
    Then Actual vehicle details are verified with expected details
    Examples:
      | input_file                                | output_file                                |
      | src/test/resources/testdata/car_input.txt | src/test/resources/testdata/car_output.txt |
