#language: en
Feature: Payment checks
  Background: Test payments with mollie

  Scenario: Starting a new payment from invoice
    When invoice 'invoice.json' is used to send payment
    Then the response should be 'expectedresponsenewpayment.json'

  Scenario: Retrieving a new payment status
    When the payment status for payment 'tr_WDqYK6vllg' is being retrieved
    Then the response should be 'expectedresponsestatuspayment.json'
